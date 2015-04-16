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

public class DiractionalLight extends Light{
	
	private float xDir,yDir,zDir;
	public DiractionalLight(Color color, float xDir, float yDir, float zDir) {
		super(color, 0, 100, -100);
		this.xDir = xDir;
		this.yDir = yDir;
		this.zDir = zDir;
		shadowInfo = new ShadowInfo(Window.getW(),Window.getH());
	}

	@Override
	public void updateLight(Camera cam) {
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
		GL11.glCullFace(GL11.GL_BACK);
		AppHandler.shadowShader.unbindShader();
	}
	

}
