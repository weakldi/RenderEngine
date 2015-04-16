package renderengine.shader;

import renderengine.core.Shader;

public class BloomPassOneShader extends Shader{
	private int minValue_Loc;
	public BloomPassOneShader( ) {
		super("res/shaders/GrayScale.vert", "res/shaders/bloomPassOne.frag");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void bindAttribs() {
		bindAttrib(0, "pos");
		bindAttrib(1, "uv");
	}

	@Override
	protected void getAllUniforms() {
		minValue_Loc = getUniformLocation("minValue");	
	}
	
	public void loadMinValue(float value){
		loadUpFloat(minValue_Loc, value);
	}
}
