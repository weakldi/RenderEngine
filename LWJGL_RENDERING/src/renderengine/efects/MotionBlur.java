package renderengine.efects;

import org.lwjgl.util.vector.Vector2f;

import renderengine.core.AppHandler;
import renderengine.input.Mouse;
import renderengine.texture.Texture;

public class MotionBlur extends Efect{
	private final float MAX_RADIUS;
	public MotionBlur(boolean finalRender,float MAX_RADIUS) {
		super(finalRender);
		this.MAX_RADIUS = MAX_RADIUS;
	}

	@Override
	protected void afterReder() {
		AppHandler.blurShader.unbindShader();
		
	}
	
	@Override
	protected void preRender(Texture input) {
		AppHandler.blurShader.useShader();
		Vector2f dir = new Vector2f(Mouse.getDx(), -(Mouse.getDy()));
		float r = Math.min(dir.length(),MAX_RADIUS);
		AppHandler.blurShader.setDir(dir.x,dir.y);
		AppHandler.blurShader.setRadius(r);
		AppHandler.blurShader.setResolution(input.getWidth(),input.getHeight());
	}

	
	

}
