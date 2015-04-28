package renderengine.efects;

import org.lwjgl.util.vector.Vector2f;

import renderengine.core.AppHandler;
import renderengine.input.Mouse;
import renderengine.shader.Shader;
import renderengine.texture.Texture;

public class MotionBlur extends Efect{
	private final float MAX_RADIUS;
	private Shader shader;
	public MotionBlur(boolean finalRender,float MAX_RADIUS) {
		super(finalRender);
		this.MAX_RADIUS = MAX_RADIUS;
		shader = AppHandler.mainApp.renderEngine.loadShader("blurShader", "res/shaders/blur.vert", "res/shaders/blur.frag");
	}

	@Override
	protected void afterReder() {
		shader.unbind();
	}
	
	@Override
	protected void preRender(Texture input) {
		
		Vector2f dir = new Vector2f(Mouse.getDx(), -(Mouse.getDy()));
		float r = Math.min(dir.length(),MAX_RADIUS);
		
		shader = AppHandler.mainApp.renderEngine.getShader("blurShader");
		shader.bind();
		shader.loadUpVec2("dir", dir);
		shader.loadUpFloat("radius", r);
		shader.loadUpVec2("resolution", new Vector2f(input.getWidth(),input.getHeight()));
	}

	
	

}
