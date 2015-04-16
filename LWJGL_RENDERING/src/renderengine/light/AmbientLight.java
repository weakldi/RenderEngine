package renderengine.light;

import java.util.List;

import renderengine.core.AppHandler;
import renderengine.core.Camera;
import renderengine.core.Color;
import renderengine.core.Entity;
import renderengine.model.Model;
import renderengine.shader.ForAmbientShader;

public class AmbientLight {
	private Color lightInt;
	private ForAmbientShader shader;
	public AmbientLight(){
		lightInt = new Color(0,0,0);
		shader = AppHandler.ambientShader;
	}
	
	public void updateLight(Camera cam){
		AppHandler.ambientShader.useShader();
		AppHandler.ambientShader.loadProjectionMatrix(cam.getProjectionMatrix());
		AppHandler.ambientShader.loadViewMat(cam.getViewMatrix());
		AppHandler.ambientShader.loadAmbientInt(lightInt);
		AppHandler.ambientShader.unbindShader();
	}
	
	public void renderModel(Model m,List<Entity> e){
		AppHandler.ambientShader.useShader();
		
		
		m.bindModel();
		for (Entity entity : e) {
			entity.getTexture().bind();
			AppHandler.ambientShader.loadColor(entity.getColor());
			AppHandler.ambientShader.loadModelMat(entity.getTransFormationMatrix());
			m.renderEntities();
		}
		m.unbindModel();
		
		AppHandler.ambientShader.unbindShader();
	}
	
	public Color getLightInt() {
		return lightInt;
	}
	public void setLightInt(Color lightInt) {
		this.lightInt = lightInt;
	}
	
	
}
