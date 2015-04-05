package renderengine.shader;

import org.lwjgl.util.vector.Vector3f;

import renderengine.core.Color;
import renderengine.core.Matrix;
import renderengine.core.Shader;

public class ForPointShader extends Shader{
	private int projMat_loc;
	private int modelMat_loc;
	private int viewMat_loc;
	private int color_loc;
	private int lightInt_loc;
	private int lightPos_loc;
	private int attenuation_loc;
	private int specularInt_loc;
	private int speculaExp_loc;
	private int camPos_loc;
	private int range_loc;
	private static boolean wasCreated = false;
	public ForPointShader() {
		super("res/shaders/forPoint.vert", "res/shaders/forPoint.frag");
		wasCreated = true;
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
		lightInt_loc = getUniformLocation("lightInt");
		lightPos_loc = getUniformLocation("lightPos");
		attenuation_loc = getUniformLocation("attenuation");
		specularInt_loc = getUniformLocation("specularInt");
		speculaExp_loc = getUniformLocation("speculaExp");
		camPos_loc = getUniformLocation("camPos");
		range_loc = getUniformLocation("range");
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
	
	public void loadLightInt(Color color){
		Vector3f c = new Vector3f(color.getR(),color.getG(), color.getB());
		loadUpVec3(lightInt_loc , c);
	}
	
	public void loadLightPos(float x,float y,float z){
		Vector3f dir = new Vector3f(x, y, z);
		loadUpVec3(lightPos_loc, dir);
	}

	public void loadAttenuation(float attenuationConst,
			float attenuationLinear, float attenuationExp) {
		Vector3f attenuation = new Vector3f(attenuationConst, attenuationLinear, attenuationExp);
		loadUpVec3(attenuation_loc, attenuation);
	}
	
	public void loadCamPos(float x,float y,float z){
		Vector3f pos = new Vector3f(x, y, z);
		loadUpVec3(camPos_loc, pos);
	}
	
	public void loadSpecularData(float specularInt,float specularExp){
		loadUpFloat(specularInt_loc, specularInt);
		loadUpFloat(speculaExp_loc, specularExp);
	}
	
	public void loadRange(float range){
		loadUpFloat(range_loc, range);
	}

}
