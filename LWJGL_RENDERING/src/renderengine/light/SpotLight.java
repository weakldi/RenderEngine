package renderengine.light;

import java.util.List;

import org.lwjgl.opengl.GL11;

import renderengine.core.AppHandler;
import renderengine.core.Camera;
import renderengine.core.Color;
import renderengine.core.Entity;
import renderengine.core.Material;
import renderengine.core.Window;
import renderengine.model.Model;

public class SpotLight extends Light{
	
	private float attenuationConst ,attenuationLinear,attenuationExp;
	private float range;
	private float cutoff;
	private float dirX,dirY,dirZ;
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
	}

	@Override
	public void updateLight(Camera cam) {
		AppHandler.spotShader.useShader();
		AppHandler.spotShader.loadProjectionMatrix(cam.getProjectionMatrix());
		AppHandler.spotShader.loadViewMat(cam.getViewMatrix());
		AppHandler.spotShader.loadLightInt(color);
		AppHandler.spotShader.loadLightPos(x, y, z);
		AppHandler.spotShader.loadAttenuation(attenuationConst,attenuationLinear,attenuationExp);
		AppHandler.spotShader.loadRange(range);
		AppHandler.spotShader.loadCutoff(cutoff);
		AppHandler.spotShader.loadSpotDir(dirX, dirY, dirZ);
		AppHandler.spotShader.loadCamPos(cam.getX(), cam.getY(), cam.getZ());
		if(shadowInfo!=null){
			AppHandler.spotShader.loadDepthViewMat(shadowInfo.getCam().getViewMatrix());
			AppHandler.spotShader.loadDepthProjectionMatrix(shadowInfo.getCam().getProjectionMatrix());
		}else{
			AppHandler.spotShader.loadDepthViewMat(cam.getViewMatrix());
			AppHandler.spotShader.loadDepthProjectionMatrix(cam.getProjectionMatrix());
		}
		
		AppHandler.spotShader.unbindShader();
	}

	@Override
	public void renderModel(Model model, List<Entity> e) {
		AppHandler.spotShader.useShader();
		
		if(shadowInfo!=null)
			shadowInfo.getFinalShadowmap().bind(1);
		else
			Material.getBaseTexture().bind(1);
//		shadowInfo.getShadowMap().bind(1);
		model.bindModel();
		for (Entity entity : e) {
			entity.getTexture().bind(0);
			AppHandler.spotShader.loadColor(entity.getColor());
			AppHandler.spotShader.loadSpecularData(entity.getMat().getSpecularIntensity(), entity.getMat().getSpecularExponent());
			AppHandler.spotShader.loadModelMat(entity.getTransFormationMatrix());
			model.renderEntities();
			entity.getTexture().unbind();
		}
		model.unbindModel();
		if(shadowInfo!=null)
			shadowInfo.getShadowMap().unbind();
		else
			Material.getBaseTexture().unbind();
		AppHandler.spotShader.unbindShader();
	}

	@Override
	public void updateShadowInfo() {
		shadowInfo.updateCamera(x, y, z, dirX, dirY, dirZ);
		AppHandler.shadowShader.useShader();
		AppHandler.shadowShader.loadProjectionMatrix(shadowInfo.getCam().getProjectionMatrix());
		AppHandler.shadowShader.loadViewMat(shadowInfo.getCam().getViewMatrix());
		AppHandler.shadowShader.unbindShader();
	}


	@Override
	public void renderShadowMap(Model m, List<Entity> e) {
		AppHandler.shadowShader.useShader();
		GL11.glCullFace(GL11.GL_FRONT);
		m.bindModel();
		for (Entity entity : e) {
			AppHandler.shadowShader.loadModelMat(entity.getTransFormationMatrix());
			m.renderEntities();
		}
		m.unbindModel();
		
		AppHandler.shadowShader.unbindShader();
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
