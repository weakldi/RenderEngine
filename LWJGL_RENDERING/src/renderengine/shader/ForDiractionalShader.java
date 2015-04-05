package renderengine.shader;

import org.lwjgl.util.vector.Vector3f;

import renderengine.core.Color;
import renderengine.core.Matrix;
import renderengine.core.Shader;

public class ForDiractionalShader extends Shader{
	private int projMat_loc;
	private int modelMat_loc;
	private int viewMat_loc;
	private int depthProjMat_loc;
	private int depthModelMat_loc;
	private int depthViewMat_loc;
	private int color_loc;
	private int lightInt_loc;
	private int lightDir_loc;
	public ForDiractionalShader() {
		super("res/shaders/forDiractional.vert", "res/shaders/forDiractional.frag");
//		super("res/shaders/shadowMap.vert", "res/shaders/shadowMap.frag");
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
		depthProjMat_loc = getUniformLocation("depthProjMat");
		depthModelMat_loc = getUniformLocation("depthModelMat");
		depthViewMat_loc = getUniformLocation("depthViewMat");
		color_loc = getUniformLocation("color");
		lightInt_loc = getUniformLocation("lightInt");
		lightDir_loc = getUniformLocation("lightDir");
		useShader();
		loadUpInt(getUniformLocation("textureSampler"), 0);
		loadUpInt(getUniformLocation("shadowMap"), 1);
		unbindShader();
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
	
	public void loadDepthProjectionMatrix(Matrix mat){
		loadUpMat4(depthProjMat_loc, mat.getMatGL());
	}
	
	public void loadDepthModelMat(Matrix mat){
		loadUpMat4(depthModelMat_loc, mat.getMatGL());
	}
	
	public void loadDepthViewMat(Matrix mat){
		loadUpMat4(depthViewMat_loc, mat.getMatGL());
	}
	
	public void loadColor(Color color){
		Vector3f c = new Vector3f(color.getR(),color.getG(), color.getB());
		loadUpVec3(color_loc, c);
	}
	
	public void loadLightInt(Color color){
		Vector3f c = new Vector3f(color.getR(),color.getG(), color.getB());
		loadUpVec3(lightInt_loc , c);
	}
	
	public void loadLightDir(float x,float y,float z){
		Vector3f dir = new Vector3f(x, y, z);
		loadUpVec3(lightDir_loc, dir);
	}

}
