package renderengine.shader;

import org.lwjgl.util.vector.Vector3f;

import renderengine.core.Color;
import renderengine.core.Matrix;
import renderengine.core.Shader;

public class GUIShader extends Shader{
	private int projMat_loc;
	private int modelMat_loc;
	private int viewMat_loc;
	private int color_loc;
	private int ambientLightIntensity_loc;
	public GUIShader() {

		super("res/shaders/GUIShader.vert", "res/shaders/GUIShader.frag");
	}

	@Override
	protected void bindAttribs() {
		bindAttrib(0, "pos");
		
		bindAttrib(1, "uv");
	}

	@Override
	protected void getAllUniforms() {
		modelMat_loc = getUniformLocation("modelMat");
	}
	
	
	public void loadModelMat(Matrix mat){
		loadUpMat4(modelMat_loc, mat.getMatGL());
	}
	
	

}
