package renderengine.light;

import java.util.List;

import renderengine.core.AppHandler;
import renderengine.core.Camera;
import renderengine.core.Color;
import renderengine.core.Entity;
import renderengine.model.Model;
import renderengine.shader.Shader;


public abstract class Light {
	protected float x,y,z;
	protected Color color;
	protected ShadowInfo shadowInfo = null;
	protected boolean enabeld;
	public Light(Color color,float x,float y,float z){
		this.color = color;
		this.x = x;
		this.y = y;
		this.z = z;
		enabeld = true;
		AppHandler.mainApp.addLight(this);
	}
	
	public abstract void updateShadowInfo();
	public abstract void renderShadowMap(Model m, List<Entity> e);
	public abstract void updateLight(Camera cam);
	public abstract void renderModel(Model model, List<Entity> e);
	
	public void deleteLight(){
		AppHandler.mainApp.removeLight(this);
	}
	
	
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getZ() {
		return z;
	}
	public void setZ(float z) {
		this.z = z;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}

	public ShadowInfo getShadowInfo() {
		return shadowInfo;
	}

	public void setShadowInfo(ShadowInfo shadowInfo) {
		this.shadowInfo = shadowInfo;
	}

	public boolean isEnabeld() {
		return enabeld;
	}

	public void setEnabeld(boolean enabeld) {
		this.enabeld = enabeld;
	}
	
	
}
