package renderengine.efects;

import renderengine.core.AppHandler;
import renderengine.texture.Texture;


public class FXAAEfect extends Efect{
	private boolean hasChanged = true;
	private float spanMax = 1f;
	private float reduceMul = 1.0f/8.0f;
	private float reduceMin = 1.0f/128f;
	public FXAAEfect(boolean finalRender) {
		super(finalRender);
	}
	@Override
	protected void afterReder() {
		AppHandler.fxaaShader.unbindShader();
		
	}

	@Override
	protected void preRender(Texture input) {
		AppHandler.fxaaShader.useShader();
		AppHandler.fxaaShader.loadUpTextureDim(1.0f/input.getWidth(), 1.0f/input.getHeight());
		if(hasChanged){
			AppHandler.fxaaShader.loadUpReduceMin_loc(reduceMin);
			AppHandler.fxaaShader.loadUpReduceMul_loc(reduceMul);
			AppHandler.fxaaShader.loadUpSpanMax(spanMax);
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
