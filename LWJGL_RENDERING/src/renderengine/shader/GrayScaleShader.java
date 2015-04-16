package renderengine.shader;

import renderengine.core.Shader;

public class GrayScaleShader extends Shader{

	public GrayScaleShader( ) {
		super("res/shaders/GrayScale.vert", "res/shaders/GrayScale.frag");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void bindAttribs() {
		bindAttrib(0, "pos");
		bindAttrib(1, "uv");
	}

	@Override
	protected void getAllUniforms() {
		// TODO Auto-generated method stub
		
	}

}
