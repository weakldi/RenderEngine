package renderengine.shader;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import renderengine.core.Color;
import renderengine.core.Matrix;
import renderengine.core.Shader;

public class GUITextureAtlasShader extends Shader{
	private int offset_loc;
	private int modelMat_loc;
	private int color_loc;
	private int gridSize_loc;

	public GUITextureAtlasShader() {
		super("res/shaders/GUITextureAtlasShader.vert", "res/shaders/GUITextureAtlasShader.frag");
	}

	@Override
	protected void bindAttribs() {
		bindAttrib(0, "pos");
		bindAttrib(1, "uv");
	}

	@Override
	protected void getAllUniforms() {
		modelMat_loc = getUniformLocation("modelMat");
		offset_loc = getUniformLocation("offset");
		color_loc = getUniformLocation("color");
		gridSize_loc = getUniformLocation("rows");
	}
	
	
	public void loadModelMat(Matrix mat){
		loadUpMat4(modelMat_loc, mat.getMatGL());
	}
	
	public void loadOffset(float x,float y){
		loadUpVec2(offset_loc, new Vector2f(x, y));
	}
	
	public void loadUpColor(float r,float g,float b){
		loadUpVec3(color_loc, new Vector3f(r, g, b));
	}
	
	public void loadUpRows(float rows){
		
		loadUpFloat(gridSize_loc, rows);
		
	}
	
	

}
