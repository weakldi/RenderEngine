package renderengine.light;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import renderengine.core.AppHandler;
import renderengine.core.Camera;
import renderengine.core.Color;
import renderengine.core.Entity;
import renderengine.model.Model;
import renderengine.shader.Shader;

public class PointLight extends Light{
	
	private float attenuationConst ,attenuationLinear,attenuationExp;
	private float range;
	private Shader shader;
	public PointLight(Color color, float x, float y, float z,float attenuationConst,float attenuationLinear,float attenuationExp,float range) {
		super(color, x, y, z);

		this.attenuationConst = attenuationConst;
		this.attenuationLinear = attenuationLinear;
		this.attenuationExp = attenuationExp;
		this.range = range;
		
		shader = AppHandler.mainApp.renderEngine.loadShader("pointShader", "res/shaders/forPoint.vert", "res/shaders/forPoint.frag");
		
	}
	
	public PointLight(Color color, float x, float y, float z,float attenuationConst,float attenuationLinear,float attenuationExp) {
		super(color, x, y, z);

		this.attenuationConst = attenuationConst;
		this.attenuationLinear = attenuationLinear;
		this.attenuationExp = attenuationExp;
		this.range = 100;
		shader = AppHandler.mainApp.renderEngine.loadShader("pointShader", "res/shaders/forPoint.vert", "res/shaders/forPoint.frag");
	}
	private Vector3f buffer;
	
	@Override
	public void updateLight(Camera cam) {
		shader = AppHandler.mainApp.renderEngine.getShader("pointShader");
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
		
	}

	@Override
	public void renderModel(Model model, List<Entity> e) {
		shader.bind();
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
		shader.unbind();
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
