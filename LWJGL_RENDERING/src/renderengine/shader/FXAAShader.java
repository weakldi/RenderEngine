package renderengine.shader;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import renderengine.core.Color;
import renderengine.core.Matrix;
import renderengine.core.Shader;

public class FXAAShader extends Shader{
	private int textureDim_loc;
	private int fxaaSpanMax_loc;
	private int fxaaReduceMin_loc;
	private int fxaaReduceMul_loc;
	public FXAAShader() {

		super("res/shaders/FXAA.vert", "res/shaders/FXAA.frag");
	}

	@Override
	protected void bindAttribs() {
		bindAttrib(0, "pos");
		
		bindAttrib(1, "uv");
	}

	@Override
	protected void getAllUniforms() {
		textureDim_loc = getUniformLocation("inverseFilterTextureSize");
		fxaaSpanMax_loc = getUniformLocation("fxaaSpanMax");
		fxaaReduceMin_loc = getUniformLocation("fxaaReduceMin");
		fxaaReduceMul_loc = getUniformLocation("fxaaReduceMul");
	}
	
	public void loadUpTextureDim(float x,float y){
		loadUpVec3(textureDim_loc, new Vector3f(x, y,0.0f));
	}
	
	public void loadUpSpanMax(float max){
		loadUpFloat(fxaaSpanMax_loc, max);
	}
	public void loadUpReduceMin_loc(float min){
		loadUpFloat(fxaaReduceMin_loc, min);
	}
	public void loadUpReduceMul_loc(float mul){
		loadUpFloat(fxaaReduceMul_loc, mul);
	}

	
	

}
