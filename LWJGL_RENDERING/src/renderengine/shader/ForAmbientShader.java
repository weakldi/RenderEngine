package renderengine.shader;

import org.lwjgl.util.vector.Vector3f;

import renderengine.core.Color;
import renderengine.core.Matrix;
import renderengine.core.Shader;

public class ForAmbientShader extends Shader{
	private int projMat_loc;
	private int modelMat_loc;
	private int viewMat_loc;
	private int color_loc;
	private int ambientLightIntensity_loc;
	public ForAmbientShader() {
//		super("res/shaders/shadowMap.vert", "res/shaders/shadowMap.frag");
		super("res/shaders/forAmbient.vert", "res/shaders/forAmbient.frag");
	}

	@Override
	protected void bindAttribs() {
		bindAttrib(0, "pos");
		bindAttrib(1, "normal");
		bindAttrib(2, "uv");
	}

	@Override
	protected void getAllUniforms() {
		projMat_loc = getUniformLocation("projMat");
		modelMat_loc = getUniformLocation("modelMat");
		viewMat_loc = getUniformLocation("viewMat");
		color_loc = getUniformLocation("color");
		ambientLightIntensity_loc = getUniformLocation("ambientLightIntensity");
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
	
	public void loadColor(Color color){
		Vector3f c = new Vector3f(color.getR(),color.getG(), color.getB());
		loadUpVec3(color_loc, c);
	}
	
	public void loadAmbientInt(Color color){
		Vector3f c = new Vector3f(color.getR(),color.getG(), color.getB());
		loadUpVec3(ambientLightIntensity_loc , c);
	}

}
