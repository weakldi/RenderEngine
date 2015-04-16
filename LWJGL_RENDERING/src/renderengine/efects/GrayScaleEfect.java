package renderengine.efects;

import renderengine.core.AppHandler;
import renderengine.texture.Texture;

public class GrayScaleEfect extends Efect{

	public GrayScaleEfect(boolean finalRender) {
		super(finalRender);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void afterReder() {
		AppHandler.grayScale.unbindShader();
		
	}

	@Override
	protected void preRender(Texture input) {
		AppHandler.grayScale.useShader();
		input.bind();
		
	}

}
