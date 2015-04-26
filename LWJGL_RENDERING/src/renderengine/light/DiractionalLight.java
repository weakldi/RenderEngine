package renderengine.light;


import java.util.List;

import org.lwjgl.opengl.GL11;

import renderengine.core.AppHandler;
import renderengine.core.Camera;
import renderengine.core.Color;
import renderengine.core.Entity;
import renderengine.core.Window;
import renderengine.model.Model;
import renderengine.shader.ForDiractionalShader;
import renderengine.shader.Shader;

public class DiractionalLight extends Light{
	private Shader shader,shadow;
	private float xDir,yDir,zDir;
	public DiractionalLight(Color color, float xDir, float yDir, float zDir) {
		super(color, 0, 100, -100);
		this.xDir = xDir;
		this.yDir = yDir;
		this.zDir = zDir;
		shadowInfo = new ShadowInfo(Window.getW(),Window.getH());
		System.out.println("Light");
		if(AppHandler.mainApp.renderEngine.getShader("diractionalShader")==null){
			AppHandler.mainApp.renderEngine.addShader("diractionalShader", new Shader("res/shaders/forDiractional.vert", "res/shaders/forDiractional.frag"));
		}
		if(AppHandler.mainApp.renderEngine.getShader("shadowShader")==null){
			AppHandler.mainApp.renderEngine.addShader("shadowShader", new Shader("res/shaders/shadowMap.vert", "res/shaders/shadowMap.frag"));
		}
		shader = AppHandler.mainApp.renderEngine.getShader("diractionalShader");
		shadow = AppHandler.mainApp.renderEngine.getShader("shadowShader");
	}

	@Override
	public void updateLight(Camera cam) {
		shader = AppHandler.mainApp.renderEngine.getShader("diractionalShader");
		shader.bind();
		shader.loadUpMat4("projMat", cam.getProjectionMatrix());
		AppHandler.diractionalShader.useShader();
		AppHandler.diractionalShader.loadProjectionMatrix(cam.getProjectionMatrix());
		AppHandler.diractionalShader.loadViewMat(cam.getViewMatrix());
		AppHandler.diractionalShader.loadDepthViewMat(shadowInfo.getCam().getViewMatrix());
		AppHandler.diractionalShader.loadDepthProjectionMatrix(shadowInfo.getCam().getProjectionMatrix());
		AppHandler.diractionalShader.loadLightInt(color);
		AppHandler.diractionalShader.loadLightDir(xDir, yDir, zDir);
		AppHandler.diractionalShader.unbindShader();
	}

	@Override
	public void renderModel(Model model, List<Entity> e) {
		AppHandler.diractionalShader.useShader();
		
		
//		shadowInfo.getFinalShadowMap().bind(1);
		shadowInfo.getShadowMap().bind(1);
		model.bindModel();
		for (Entity entity : e) {
			entity.getTexture().bind();
			AppHandler.diractionalShader.loadColor(entity.getColor());
			AppHandler.diractionalShader.loadModelMat(entity.getTransFormationMatrix());
			
			AppHandler.diractionalShader.loadDepthModelMat(entity.getTransFormationMatrix());
			model.renderEntities();
		}
		model.unbindModel();
		shadowInfo.getShadowMap().unbind();
		AppHandler.diractionalShader.unbindShader();
	}

	public float getxDir() {
		return xDir;
	}

	public void setxDir(float xDir) {
		this.xDir = xDir;
	}

	public float getyDir() {
		return yDir;
	}

	public void setyDir(float yDir) {
		this.yDir = yDir;
	}

	public float getzDir() {
		return zDir;
	}

	public void setzDir(float zDir) {
		this.zDir = zDir;
	}

	
	@Override
	public void updateShadowInfo() {
		shadowInfo.updateCamera(x, y, z, xDir, yDir, zDir);
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
	

}
