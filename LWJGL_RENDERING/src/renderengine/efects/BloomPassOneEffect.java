package renderengine.efects;

import renderengine.core.AppHandler;
import renderengine.texture.Texture;

public class BloomPassOneEffect extends Efect{
	private float minBloomValue = 0.8f;
	public BloomPassOneEffect() {
		super(true);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void afterReder() {
		AppHandler.bloomeOne.unbindShader();
	}

	@Override
	protected void preRender(Texture input) {
		AppHandler.bloomeOne.useShader();
		AppHandler.bloomeOne.loadMinValue(minBloomValue);
	}

}
