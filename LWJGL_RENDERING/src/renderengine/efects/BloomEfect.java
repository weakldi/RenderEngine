package renderengine.efects;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

import renderengine.core.AppHandler;
import renderengine.texture.Texture;

public class BloomEfect extends Efect
{	
	private BloomPassOneEffect passOne;
	private BlurEfect blurX,blurY;
	private Texture buffer1,buffer2;
	public BloomEfect(boolean finalRender) {
		super(finalRender);
		buffer1 = new Texture(Display.getWidth(), Display.getHeight(), GL30.GL_COLOR_ATTACHMENT0, GL11.GL_NEAREST, false);
		buffer2 = new Texture(Display.getWidth(), Display.getHeight(), GL30.GL_COLOR_ATTACHMENT0, GL11.GL_NEAREST, false);
		blurX = new BlurEfect(false, 2, 1, 0);
		blurY = new BlurEfect(false, 2, 0, 1);
		passOne = new BloomPassOneEffect();
		
	}

	@Override
	protected void afterReder() {
		AppHandler.mixShader.unbindShader();
		
	}

	@Override
	protected void preRender(Texture input) {
		passOne.renderEfect(buffer1, input);
		blurX.renderEfect(buffer2, buffer1);
		blurY.renderEfect(buffer1, buffer2);
		buffer1.bind(1);
		AppHandler.mixShader.useShader();
		
	}

}
