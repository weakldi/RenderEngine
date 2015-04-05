package renderengine.core;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.List;








import renderengine.efects.BlurEfect;
import renderengine.efects.Efect;
import renderengine.efects.FXAAEfect;
import renderengine.gui.Font;
import renderengine.gui.GUIComponent;
import renderengine.light.AmbientLight;
import renderengine.light.Light;
import renderengine.model.Model;
import renderengine.model.Models;
import renderengine.model.SkyBox;
import renderengine.texture.Texture;
public class RenderEngine {
	private GUIComponent root;
	private BlurEfect blur;
	private Texture texture1;
	private Texture texture2;
	private List<Efect> efects;
	
	public RenderEngine() {
		root = new GUIComponent(true);
		texture1 = new Texture(Window.getW(),Window.getH(),GL_COLOR_ATTACHMENT0,GL_LINEAR,false);
		texture2 = new Texture(Window.getW(),Window.getH(),GL_COLOR_ATTACHMENT0,GL_LINEAR,false);
		efects = new ArrayList<Efect>();
//		blur = new BlurEfect(false,0.125f,1,0);
		
	}
	public void render(Camera cam,List<Model> models,AmbientLight ambientLight,List<Light> lights){
		
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
		
		
//		Window.bindAsRenderTarget();
		texture1.bindAsRenderTarget();
		GLUtil.setClearColor(0, 0, 0);
		GLUtil.clearScreen();
		
		ambientLight.updateLight(cam);
		for (Model model : models) {
			ambientLight.renderModel(model,Models.getEntitys(model.getModelID()));
		}
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
		boolean texture1asTarget = false;
		for (Efect efect : efects) {
			if (texture1asTarget) {
				efect.renderEfect(texture1, texture2);
			}else{
				efect.renderEfect(texture2, texture1);
			}
			texture1asTarget = !texture1asTarget;
		}
		if(efects.size()==0){
			root.setTexture(texture1);
		}else if (texture1asTarget) {
			root.setTexture(texture2);
		}else{
			root.setTexture(texture1);
		}
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		Window.bindAsRenderTarget();
		GLUtil.setClearColor(0, 0, 0);
		GLUtil.clearScreen();
		root.renderAll();
		
		
		glDisable(GL_BLEND);
		
	}
	Font f;
	public void render(Camera cam,List<Model> models,AmbientLight ambientLight,List<Light> lights,SkyBox sky){
		
		
		GLUtil.clearScreen();
		
		Window.bindAsRenderTarget();
		ambientLight.updateLight(cam);
//		for (Model model : models) {
//			ambientLight.renderModel(model);
//		}
		glEnable(GL_BLEND);
		glBlendFunc(GL_ONE, GL_ONE);
		glDepthMask(false);
		glDepthFunc(GL_EQUAL);
		for (Light light : lights) {
			
			if(light.isEnabeld()){
				light.updateLight(cam);
					for (Model model : models) {
//						light.renderModel(model);
				}
			}
		}
		sky.render(cam);
		glDepthFunc(GL_LESS);
		glDepthMask(true);
		glDisable(GL_BLEND);
		
	}
	public GUIComponent getRoot() {
		return root;
	}
	public void addEffect(Efect efect) {
		efects.add(efect);
	}
	
	
	
	
}
