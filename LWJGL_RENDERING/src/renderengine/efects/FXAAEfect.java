package renderengine.efects;

import org.lwjgl.util.vector.Vector3f;

import renderengine.core.AppHandler;
import renderengine.core.MainApplication;
import renderengine.shader.Shader;
import renderengine.texture.Texture;


public class FXAAEfect extends Efect{
	private boolean hasChanged = true;
	private float spanMax = 10f;
	private float reduceMul = 1.0f/8.0f;
	private float reduceMin = 1.0f/128f;
	private Shader shader;
	public FXAAEfect(boolean finalRender) {
		super(finalRender);
		shader = AppHandler.mainApp.renderEngine.loadShader("fxaaShader", "res/shaders/FXAA.vert", "res/shaders/FXAA.frag");
	}
	@Override
	protected void afterReder() {
		shader.unbind();
	}

	@Override
	protected void preRender(Texture input) {
		shader.bind();
		shader.loadUpVec3("inverseFilterTextureSize", new Vector3f(1.0f/input.getWidth(), 1.0f/input.getHeight(), 0));
		
		if(hasChanged){
			shader.loadUpFloat("fxaaReduceMin", reduceMin);
			shader.loadUpFloat("fxaaReduceMul", reduceMul);
			shader.loadUpFloat("fxaaSpanMax", spanMax);
			hasChanged = false;
		}
	}
	public float getSpanMax() {
		return spanMax;
	}
	public void setSpanMax(float spanMax) {
		this.spanMax = spanMax;
		hasChanged = true;
	}
	public float getReduceMul() {
		return reduceMul;
	}
	public void setReduceMul(float reduceMul) {
		this.reduceMul = reduceMul;
		hasChanged = true;
	}
	public float getReduceMin() {
		return reduceMin;
	}
	public void setReduceMin(float reduceMin) {
		this.reduceMin = reduceMin;
		hasChanged = true;
	}

}
