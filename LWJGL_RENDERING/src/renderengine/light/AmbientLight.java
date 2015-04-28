package renderengine.light;

import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import renderengine.core.AppHandler;
import renderengine.core.Camera;
import renderengine.core.Color;
import renderengine.core.Entity;
import renderengine.model.Model;
import renderengine.shader.Shader;

public class AmbientLight {
	private Color lightInt;
	private Shader ambientShader;
	public AmbientLight(){
		lightInt = new Color(0,0,0);
		ambientShader = AppHandler.mainApp.renderEngine.loadShader("ambientShader", "res/shaders/forAmbient.vert", "res/shaders/forAmbient.frag");
	}
	
	public void updateLight(Camera cam){
		ambientShader = AppHandler.mainApp.renderEngine.getShader("ambientShader");
		ambientShader.bind();
		ambientShader.loadUpMat4("projMat", cam.getProjectionMatrix());
		ambientShader.loadUpMat4("viewMat", cam.getViewMatrix());
		Vector3f c = new Vector3f(lightInt.getR(),lightInt.getG(), lightInt.getB());
		ambientShader.loadUpVec3("ambientLightIntensity",c);
		ambientShader.unbind();
	}
	
	public void renderModel(Model m,List<Entity> e){
		ambientShader.bind();
		
		
		m.bindModel();
		for (Entity entity : e) {
			entity.getTexture().bind();
			Color color = entity.getColor();
			Vector3f c = new Vector3f(color.getR(),color.getG(), color.getB());
			ambientShader.loadUpVec3("color",c);
			
			ambientShader.loadUpMat4("modelMat", entity.getTransFormationMatrix());
			m.renderEntities();
		}
		m.unbindModel();
		
		ambientShader.unbind();
	}
	
	public Color getLightInt() {
		return lightInt;
	}
	public void setLightInt(Color lightInt) {
		this.lightInt = lightInt;
	}
	
	
}
