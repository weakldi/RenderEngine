package renderengine.light;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import renderengine.core.AppHandler;
import renderengine.core.Camera;
import renderengine.core.Color;
import renderengine.core.Entity;
import renderengine.core.Material;
import renderengine.core.Window;
import renderengine.model.Model;
import renderengine.shader.Shader;

public class SpotLight extends Light{
	
	private float attenuationConst ,attenuationLinear,attenuationExp;
	private float range;
	private float cutoff;
	private float dirX,dirY,dirZ;
	private Shader shader;
	private Shader shadow;
	public SpotLight(Color color, float x, float y, float z,float dirX,float dirY,float dirZ,float attenuationConst,float attenuationLinear,float attenuationExp,float range,float cutoff) {
		super(color, x, y, z);

		this.attenuationConst = attenuationConst;
		this.attenuationLinear = attenuationLinear;
		this.attenuationExp = attenuationExp;
		this.range = range;
		this.cutoff = cutoff;
		this.dirX = dirX;
		this.dirY = dirY;
		this.dirZ = dirZ;
		shadowInfo = new ShadowInfo(Window.getW(),Window.getH());
		shader = AppHandler.mainApp.renderEngine.loadShader("spotShader", "res/shaders/forSpot.vert", "res/shaders/forSpot.frag");
		shadow = AppHandler.mainApp.renderEngine.loadShader("shadowShader", "res/shaders/shadowMap.vert", "res/shaders/shadowMap.frag");
		
		
		
		shader.bind();
		shader.loadUpInt("textureSampler", 0);
		shader.loadUpInt("shadowMap", 1);
		shader.unbind();
	
	}
	
	public SpotLight(Color color, float x, float y, float z,float dirX,float dirY,float dirZ,float attenuationConst,float attenuationLinear,float attenuationExp,float cutoff) {
		super(color, x, y, z);

		this.attenuationConst = attenuationConst;
		this.attenuationLinear = attenuationLinear;
		this.attenuationExp = attenuationExp;
		this.range = 100;
		this.cutoff = cutoff;
		this.dirX = dirX;
		this.dirY = dirY;
		this.dirZ = dirZ;
		shadowInfo = new ShadowInfo(Window.getW(),Window.getH());
		
		shader = AppHandler.mainApp.renderEngine.loadShader("spotShader", "res/shaders/forSpot.vert", "res/shaders/forSpot.frag");
		shadow = AppHandler.mainApp.renderEngine.loadShader("shadowShader", "res/shaders/shadowMap.vert", "res/shaders/shadowMap.frag");
		
		
		
		shader.bind();
		shader.loadUpInt("textureSampler", 0);
		shader.loadUpInt("shadowMap", 1);
		shader.unbind();
	
		
	}
	private Vector3f buffer;
	@Override
	public void updateLight(Camera cam) {
		shader = AppHandler.mainApp.renderEngine.getShader("spotShader");
		shader.bind();
		shader.loadUpMat4("projMat", cam.getProjectionMatrix());
		shader.loadUpMat4("viewMat", cam.getViewMatrix());
		buffer = new Vector3f(color.getR(), color.getG(), color.getB());
		shader.loadUpVec3("lightInt", buffer);
		buffer = new Vector3f(x, y, z);
		shader.loadUpVec3("lightPos", buffer);
		buffer = new Vector3f(attenuationConst, attenuationLinear, attenuationExp);
		shader.loadUpVec3("attenuation", buffer);
		shader.loadUpFloat("range", range);
		buffer = new Vector3f(cam.getX(), cam.getY(), cam.getZ());
		shader.loadUpVec3("camPos", buffer);
		shader.loadUpFloat("cutoff", cutoff);
		buffer = new Vector3f(dirX, dirY, dirZ);
		shader.loadUpVec3("spotDir", buffer);

		if(shadowInfo!=null){
			shader.loadUpMat4("depthProjMat", shadowInfo.getCam().getProjectionMatrix());
			shader.loadUpMat4("depthViewMat", shadowInfo.getCam().getViewMatrix());
		}else{
			shader.loadUpMat4("depthProjMat", cam.getProjectionMatrix());
			shader.loadUpMat4("depthViewMat", cam.getViewMatrix());
		}
		
		
	}

	@Override
	public void renderModel(Model model, List<Entity> e) {
		
		shader.bind();
		if(shadowInfo!=null)
			shadowInfo.getFinalShadowmap().bind(1);
		else
			Material.getBaseTexture().bind(1);

		model.bindModel();
		for (Entity entity : e) {
			entity.getTexture().bind(0);
			
			buffer = new Vector3f(entity.getColor().getR(), entity.getColor().getG(), entity.getColor().getB());
			shader.loadUpVec3("color", buffer);
			shader.loadUpFloat("specularInt", entity.getMat().getSpecularIntensity());
			shader.loadUpFloat("specularExp", entity.getMat().getSpecularExponent());
			shader.loadUpMat4("modelMat", entity.getTransFormationMatrix());
			
			model.renderEntities();
			entity.getTexture().unbind();
		}
		model.unbindModel();
		if(shadowInfo!=null)
			shadowInfo.getShadowMap().unbind();
		else
			Material.getBaseTexture().unbind();
		shader.unbind();
	}

	@Override
	public void updateShadowInfo() {
		shadowInfo.updateCamera(x, y, z, dirX, dirY, dirZ);
		shadow.bind();
		shadow.loadUpMat4("projMat", shadowInfo.getCam().getProjectionMatrix());
		shadow.loadUpMat4("viewMat", shadowInfo.getCam().getViewMatrix());
	}


	@Override
	public void renderShadowMap(Model m, List<Entity> e) {
		shadow.bind();
		GL11.glCullFace(GL11.GL_FRONT);
		m.bindModel();
		for (Entity entity : e) {
			shadow.loadUpMat4("modelMat", entity.getTransFormationMatrix());
			m.renderEntities();
		}
		m.unbindModel();
		
		shadow.unbind();
	}
	public float getAttenuationConst() {
		return attenuationConst;
	}

	public void setAttenuationConst(float attenuationConst) {
		this.attenuationConst = attenuationConst;
	}

	public float getAttenuationLinear() {
		return attenuationLinear;
	}

	public void setAttenuationLinear(float attenuationLinear) {
		this.attenuationLinear = attenuationLinear;
	}

	public float getAttenuationExp() {
		return attenuationExp;
	}

	public void setAttenuationExp(float attenuationExp) {
		this.attenuationExp = attenuationExp;
	}

	public float getRange() {
		return range;
	}

	public void setRange(float range) {
		this.range = range;
	}

	public float getCutoff() {
		return cutoff;
	}

	public void setCutoff(float cutoff) {
		this.cutoff = cutoff;
	}

	public float getDirX() {
		return dirX;
	}

	public void setDirX(float dirX) {
		this.dirX = dirX;
	}

	public float getDirY() {
		return dirY;
	}

	public void setDirY(float dirY) {
		this.dirY = dirY;
	}

	public float getDirZ() {
		return dirZ;
	}

	public void setDirZ(float dirZ) {
		this.dirZ = dirZ;
	}

	
}
