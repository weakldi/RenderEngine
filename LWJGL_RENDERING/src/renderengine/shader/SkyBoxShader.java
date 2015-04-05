package renderengine.shader;

import org.lwjgl.util.vector.Matrix4f;

import renderengine.core.Matrix;
import renderengine.core.Shader;

public class SkyBoxShader extends Shader{
	private int projMat_loc;
	private int viewMat_loc;
	public SkyBoxShader() {
		super("res/shaders/Skybox.vert","res/shaders/Skybox.frag");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void bindAttribs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void getAllUniforms() {
		projMat_loc = getUniformLocation("projMat");
		viewMat_loc = getUniformLocation("viewMat");
	}
	
	public void loadProjectionMat(Matrix mat){
		loadUpMat4(projMat_loc, mat.getMatGL());
	}
	
	public void loadViewMat(Matrix view){
		Matrix4f viewMat = view.getMatGL();
		viewMat.m30 = 0;
		viewMat.m31 = 0;
		viewMat.m32 = 0;
		loadUpMat4(viewMat_loc, viewMat);
	}
	
}
