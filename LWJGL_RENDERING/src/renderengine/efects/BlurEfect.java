package renderengine.efects;

import org.lwjgl.util.vector.Vector2f;

import renderengine.core.AppHandler;
import renderengine.shader.Shader;
import renderengine.texture.Texture;

public class BlurEfect extends Efect{
	private float radius;
	private float dirX;
	private float dirY;
	private Shader shader;
	public BlurEfect(boolean finalRender,float radius,float dirX,float dirY) {
		super(finalRender);
		this.radius = radius;
		this.dirX = dirX;
		this.dirY = dirY;
		shader = AppHandler.mainApp.renderEngine.loadShader("blurShader", "res/shaders/blur.vert", "res/shaders/blur.frag");
	}

	@Override
	protected void afterReder() {
		shader.unbind();
	}
	
	@Override
	protected void preRender(Texture input) {
		shader = AppHandler.mainApp.renderEngine.getShader("blurShader");
		shader.bind();
		shader.loadUpVec2("dir", new Vector2f(dirX, dirY));
		shader.loadUpFloat("radius", radius);
		shader.loadUpVec2("resolution", new Vector2f(input.getWidth(),input.getHeight()));

	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
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

	

	

	

	

}
