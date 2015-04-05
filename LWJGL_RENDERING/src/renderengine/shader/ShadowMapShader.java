package renderengine.shader;

import org.lwjgl.util.vector.Vector3f;

import renderengine.core.Color;
import renderengine.core.Matrix;
import renderengine.core.Shader;

public class ShadowMapShader extends Shader{
	private int projMat_loc;
	private int modelMat_loc;
	private int viewMat_loc;
	public ShadowMapShader() {
		super("res/shaders/shadowMap.vert", "res/shaders/shadowMap.frag");
	}

	@Override
	protected void bindAttribs() {
		bindAttrib(0, "pos");
	}

	@Override
	protected void getAllUniforms() {
		projMat_loc = getUniformLocation("projMat");
		modelMat_loc = getUniformLocation("modelMat");
		viewMat_loc = getUniformLocation("viewMat");
		
	}
	
	public void loadProjectionMatrix(Matrix mat){
		loadUpMat4(projMat_loc, mat.getMatGL());
	}
	
	public void loadModelMat(Matrix mat){
		loadUpMat4(modelMat_loc, mat.getMatGL());
	}
	
	public void loadViewMat(Matrix mat){
		loadUpMat4(viewMat_loc, mat.getMatGL());
	}
	
	
}
