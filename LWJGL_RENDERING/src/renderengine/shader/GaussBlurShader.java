package renderengine.shader;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import renderengine.core.Color;
import renderengine.core.Matrix;
import renderengine.core.Shader;

public class GaussBlurShader extends Shader{
	private int resolution_loc;
	private int radius_loc;
	private int dir_loc;
	public GaussBlurShader() {

		super("res/shaders/blur.vert", "res/shaders/blur.frag");
	}

	@Override
	protected void bindAttribs() {
		bindAttrib(0, "pos");
		bindAttrib(1, "uv");
	}

	@Override
	protected void getAllUniforms() {
		resolution_loc = getUniformLocation("resolution");
		radius_loc = getUniformLocation("radius");
		dir_loc= getUniformLocation("dir");
	}

	public void setResolution(float resolutionX,float resolutionY) {
		loadUpVec2(resolution_loc, new Vector2f(resolutionX, resolutionY));
	}

	public void setRadius(float r) {
		loadUpFloat(radius_loc, r);
	}

	public void setDir(float x,float y) {
		loadUpVec2(dir_loc, new Vector2f( x , y));
	}
	
	

	
	

}
