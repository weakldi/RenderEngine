package renderengine.light;

import java.util.List;

import renderengine.core.AppHandler;
import renderengine.core.Camera;
import renderengine.core.Color;
import renderengine.core.Entity;
import renderengine.model.Model;
import renderengine.shader.ForDiractionalShader;
import renderengine.shader.ForPointShader;

public class PointLight extends Light{
	
	private float attenuationConst ,attenuationLinear,attenuationExp;
	private float range;
	public PointLight(Color color, float x, float y, float z,float attenuationConst,float attenuationLinear,float attenuationExp,float range) {
		super(color, x, y, z);

		this.attenuationConst = attenuationConst;
		this.attenuationLinear = attenuationLinear;
		this.attenuationExp = attenuationExp;
		this.range = range;
	}
	
	public PointLight(Color color, float x, float y, float z,float attenuationConst,float attenuationLinear,float attenuationExp) {
		super(color, x, y, z);

		this.attenuationConst = attenuationConst;
		this.attenuationLinear = attenuationLinear;
		this.attenuationExp = attenuationExp;
		this.range = 100;
	}

	@Override
	public void updateLight(Camera cam) {
		AppHandler.pointShader.useShader();
		AppHandler.pointShader.loadProjectionMatrix(cam.getProjectionMatrix());
		AppHandler.pointShader.loadViewMat(cam.getViewMatrix());
		AppHandler.pointShader.loadLightInt(color);
		AppHandler.pointShader.loadLightPos(x, y, z);
		AppHandler.pointShader.loadAttenuation(attenuationConst,attenuationLinear,attenuationExp);
		AppHandler.pointShader.loadRange(range);
		AppHandler.pointShader.loadCamPos(cam.getX(), cam.getY(), cam.getZ());
		AppHandler.pointShader.unbindShader();
	}

	@Override
	public void renderModel(Model model, List<Entity> e) {
		AppHandler.pointShader.useShader();
		model.bindModel();
		for (Entity entity : e) {
			entity.getTexture().bind(0);
			AppHandler.pointShader.loadColor(entity.getColor());
			AppHandler.pointShader.loadSpecularData(entity.getMat().getSpecularIntensity(), entity.getMat().getSpecularExponent());
			AppHandler.pointShader.loadModelMat(entity.getTransFormationMatrix());
			model.renderEntities();
			entity.getTexture().unbind();
		}
		AppHandler.pointShader.unbindShader();
	}

	@Override
	public void updateShadowInfo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void renderShadowMap(Model m, List<Entity> e) {
		// TODO Auto-generated method stub
		
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

	
}
