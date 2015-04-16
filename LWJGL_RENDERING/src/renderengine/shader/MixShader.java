package renderengine.shader;

import renderengine.core.Shader;

public class MixShader extends Shader{

	public MixShader( ) {
		super("res/shaders/MixShader.vert", "res/shaders/MixShader.frag");
		System.out.println("loaded");
	}

	@Override
	protected void bindAttribs() {
		bindAttrib(0, "pos");
		bindAttrib(1, "uv");
	}

	@Override
	protected void getAllUniforms() {
		loadUpInt(getUniformLocation("texture1"), 0);
		loadUpInt(getUniformLocation("texture2"), 1);
	}

}
