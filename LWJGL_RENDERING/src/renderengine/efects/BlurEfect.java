package renderengine.efects;

import renderengine.core.AppHandler;
import renderengine.core.Window;
import renderengine.texture.Texture;

public class BlurEfect extends Efect{
	private float radius;
	private float dirX;
	private float dirY;
	
	public BlurEfect(boolean finalRender,float radius,float dirX,float dirY) {
		super(finalRender);
		this.radius = radius;
		this.dirX = dirX;
		this.dirY = dirY;
	}

	@Override
	protected void afterReder() {
		AppHandler.blurShader.unbindShader();
		
	}
	
	@Override
	protected void preRender(Texture input) {
		AppHandler.blurShader.useShader();

		AppHandler.blurShader.setDir(dirX, dirY);
		AppHandler.blurShader.setRadius(radius);
		AppHandler.blurShader.setResolution(input.getWidth(),input.getHeight());
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
