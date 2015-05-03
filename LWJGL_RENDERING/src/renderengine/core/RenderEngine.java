package renderengine.core;

import static org.lwjgl.opengl.GL11.GL_BACK;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_EQUAL;
import static org.lwjgl.opengl.GL11.GL_FILL;
import static org.lwjgl.opengl.GL11.GL_FRONT;
import static org.lwjgl.opengl.GL11.GL_LESS;
import static org.lwjgl.opengl.GL11.GL_LINEAR;
import static org.lwjgl.opengl.GL11.GL_ONE;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glCullFace;
import static org.lwjgl.opengl.GL11.glDepthFunc;
import static org.lwjgl.opengl.GL11.glDepthMask;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL30.GL_COLOR_ATTACHMENT0;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.lwjgl.opengl.GL11;

import renderengine.efects.Efect;
import renderengine.gui.GUIComponent;
import renderengine.light.AmbientLight;
import renderengine.light.Light;
import renderengine.model.Model;
import renderengine.model.Models;
import renderengine.model.SkyBox;
import renderengine.shader.Shader;
import renderengine.texture.Texture;
public class RenderEngine {
	private GUIComponent root;
	private Texture buffer1;
	private Texture buffer2;
	private List<Efect> efects;
	private HashMap<String, renderengine.shader.Shader> shaders;
	public RenderEngine() {
		efects = new ArrayList<Efect>();
		shaders = new HashMap<>();
		
		//Die zwei Framebuffer erstellen, die für das postprocessing genutzt werden.
		buffer1 = new Texture(Window.getW(),Window.getH(),GL_COLOR_ATTACHMENT0,GL_LINEAR,false);
		buffer2 = new Texture(Window.getW(),Window.getH(),GL_COLOR_ATTACHMENT0,GL_LINEAR,false);
	}
	
	/**
	 * * Rendert die Szene durch aufrufen der Einzenen Schritte.<br>
	 * 	1. Die Shadowmaps für die Lichter erstellen! (createShadowMaps)<br>
	 * 	2. Die Szene in ein FrameBuffer rendern! (renderScene)<br>
	 * 	3. Mit Postprocessing den frambuffer bearbeiten die GUI rendern! (finalRender)<br>
	 * @param cam Die Kamera die für das rendern benutzt werden soll.
	 * @param models Alle models die gerendert werden sollen
	 * @param ambientLight das Ambielte licht
	 * @param lights Alle anderen Lichter der Szene
	 */
	 
	 
	public void render(Camera cam,List<Model> models,AmbientLight ambientLight,List<Light> lights){
		if(root==null){
			root = new GUIComponent(true);
		}
		createShadowMaps(lights, models);
		renderScene(cam, models, ambientLight, lights);
		finalRender();
	}
	
	public void addShader(String name,renderengine.shader.Shader shader){
		shaders.put(name, shader);
	}
	
	public void replaceShader(String name,renderengine.shader.Shader shader){
		shaders.replace(name, shader);
	}
	public renderengine.shader.Shader getShader(String name){
		return shaders.get(name);
	}
	
	public Shader loadShader(String name,String vertexShaderFile,String fragmentShaderFile){
		if(shaders.get(name)==null){
			shaders.put(name, new Shader(vertexShaderFile, fragmentShaderFile));
		}
		return shaders.get(name);
	}
	public void render(Camera cam,List<Model> models,AmbientLight ambientLight,List<Light> lights,SkyBox sky){
		
		
		if(root==null){
			root = new GUIComponent(true);
		}
		createShadowMaps(lights, models);
		renderScene(cam, models, ambientLight, lights,sky);
		
		finalRender();
		
	}
	public GUIComponent getRoot() {
		if(root==null){
			root = new GUIComponent(true);
		}
		return root;
	}
	public void addEffect(Efect efect) {
		efects.add(efect);
	}	
	public void cleanUp(){
		for(String name : shaders.keySet())
			getShader(name).deleteShader();;
	}
	/**
	 * 

	 * @param models Alle models die gerendert werden sollen
	 * @param lights Alle Lichter für die eine Shadowmap generriert werden soll
	 */
	private void createShadowMaps(List<Light> lights,List<Model> models){
		glEnable(GL_DEPTH_TEST);
		for (Light light : lights) {
			
			if(light.isEnabeld()){
				if(light.getShadowInfo()!=null){
					light.getShadowInfo().getShadowMap().bindAsRenderTarget();
					for (Model model : models) {
						GLUtil.setClearColor(1, 1, 0);
						light.updateShadowInfo();
						light.renderShadowMap(model,Models.getEntitys(model.getModelID()));
					}
					
					light.getShadowInfo().getShadowMap().unbindFrambuffer();
					
				}
			}
		}
	}
	
	private void finalRender(){
		GL11.glPolygonMode(GL_FRONT, GL_FILL);
		boolean buffer1asTarget = false;
		for (Efect efect : efects) {
			if (buffer1asTarget) {
				efect.renderEfect(buffer1, buffer2);
				
			}else{
				efect.renderEfect(buffer2, buffer1);
				
			}
			buffer1asTarget = !buffer1asTarget;
		}
		if(efects.size()==0){
			root.setTexture(buffer1);
		}
		else if (buffer1asTarget) {
			root.setTexture(buffer2);
		}else{
			root.setTexture(buffer1);
		}
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		Window.bindAsRenderTarget();
		GLUtil.setClearColor(0, 0, 0);
		GLUtil.clearScreen();
		root.renderAll();
		
		
		glDisable(GL_BLEND);
	}
	
	/**
	 * Rendert die Szene mit Forwardrendering
	 * @param cam Die Kamera die für das rendern benutzt werden soll.
	 * @param models Alle models die gerendert werden sollen
	 * @param ambientLight das Ambielte licht
	 * @param lights Alle anderen Lichter der Szene
	 */
	 
	 
	private void renderScene(Camera cam,List<Model> models,AmbientLight ambientLight,List<Light> lights){
		//Framebuffer binden.
		buffer1.bindAsRenderTarget();
		
		//Den Framebuffer lehren und das Rendern Forbereiten
		GLUtil.setClearColor(0, 0, 0);
		GLUtil.clearScreen();
		GL11.glPolygonMode(GL_FRONT, GL_FILL);
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
		//Szene mit ambienten Licht rendern darbei wird der Depthtest durchgeführt.
		ambientLight.updateLight(cam);
		for (Model model : models) {
			ambientLight.renderModel(model,Models.getEntitys(model.getModelID()));
		}
		//Rendereinstellungen für die Lichter vorbereiten und nur Fragments mit gleichem Z-Wert wie im Depthbuffer rendern.
		//Dies sorgt dafür, dass nur sichtbare flächen gerendert werden und somit viel zeit für die Lichtberechnung eingespart wird.
		//Durch das addieren der neuen farbe auf den Ausgenswert wird das endgültige Bild erzeugt.
		glEnable(GL_BLEND);
		glBlendFunc(GL_ONE, GL_ONE);
		glDepthMask(false);
		glDepthFunc(GL_EQUAL);
		for (Light light : lights) {
			
			if(light.isEnabeld()){
				
				for (Model model : models) {
					light.updateLight(cam);
					light.renderModel(model,Models.getEntitys(model.getModelID()));
				}
			}
		}
		glDepthFunc(GL_LESS);
		glDepthMask(true);
		glDisable(GL_DEPTH_TEST);
		glDisable(GL_BLEND);
		buffer1.unbindFrambuffer();
		
	}
	
	private void renderScene(Camera cam,List<Model> models,AmbientLight ambientLight,List<Light> lights,SkyBox sky){
		//Framebuffer binden.
		buffer1.bindAsRenderTarget();
		
		//Den Framebuffer lehren und das Rendern Forbereiten
		GLUtil.setClearColor(0, 0, 0);
		GLUtil.clearScreen();
		GL11.glPolygonMode(GL_FRONT, GL_FILL);
		glDisable(GL_CULL_FACE);
		sky.render(cam);
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
		
		//Szene mit ambienten Licht rendern darbei wird der Depthtest durchgeführt.
		ambientLight.updateLight(cam);
		for (Model model : models) {
			ambientLight.renderModel(model,Models.getEntitys(model.getModelID()));
		}
		
		//Rendereinstellungen für die Lichter vorbereiten und nur Fragments mit gleichem Z-Wert wie im Depthbuffer rendern.
		//Dies sorgt dafür, dass nur sichtbare flächen gerendert werden und somit viel zeit für die Lichtberechnung eingespart wird.
		//Durch das addieren der neuen farbe auf den Ausgenswert wird das endgültige Bild erzeugt.
		glEnable(GL_BLEND);
		glBlendFunc(GL_ONE, GL_ONE);
		glDepthMask(false);
		glDepthFunc(GL_EQUAL);
		for (Light light : lights) {
			
			if(light.isEnabeld()){
				
				for (Model model : models) {
					light.updateLight(cam);
					light.renderModel(model,Models.getEntitys(model.getModelID()));
				}
			}
		}
		
		glDepthFunc(GL_LESS);
		
		glDepthMask(true);
		
		glDisable(GL_DEPTH_TEST);
		glDisable(GL_BLEND);
		buffer1.unbindFrambuffer();
		
	}
	
	
	
	
}
