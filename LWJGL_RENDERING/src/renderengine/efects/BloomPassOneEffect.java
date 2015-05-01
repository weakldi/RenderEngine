package renderengine.efects;

import renderengine.core.AppHandler;
import renderengine.shader.Shader;
import renderengine.texture.Texture;

public class BloomPassOneEffect extends Efect{
	private float minBloomValue = 0.8f;
	private Shader shader;
	public BloomPassOneEffect() {
		super(false);
		shader = AppHandler.mainApp.renderEngine.loadShader("bloomPassOne", "res/shaders/GrayScale.vert", "res/shaders/bloomPassOne.frag");
	}

	@Override
	protected void afterReder() {
		shader.unbind();
	}

	@Override
	protected void preRender(Texture input) {
		shader.bind();
		shader.loadUpFloat("minValue", minBloomValue);
	}

	public float getMinBloomValue() {
		return minBloomValue;
	}

	public void setMinBloomValue(float minBloomValue) {
		this.minBloomValue = minBloomValue;
	}

}
