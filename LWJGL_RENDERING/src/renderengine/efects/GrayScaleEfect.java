package renderengine.efects;

import renderengine.core.AppHandler;
import renderengine.shader.Shader;
import renderengine.texture.Texture;

public class GrayScaleEfect extends Efect{
	private Shader shader;
	public GrayScaleEfect(boolean finalRender) {
		super(finalRender);
		shader = AppHandler.mainApp.renderEngine.loadShader("grayScaleShader", "res/shaders/GrayScale.vert", "res/shaders/GrayScale.frag");
	}

	@Override
	protected void afterReder() {
		shader.unbind();
		
	}

	@Override
	protected void preRender(Texture input) {
		shader.bind();
		input.bind();
		
	}

}
