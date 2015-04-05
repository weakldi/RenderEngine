package renderengine.core;

public class Color {
	private float r;
	private float g;
	private float b;
	
	public Color() {
		r = 0.75f;
		g = 0.75f;
		b = 0.75f;
	}

	public Color(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public Color(int r, int g, int b){
		this.r = r/255f;
		this.g = g/255f;
		this.b = b/255f;
	}
	
	public void setColor(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public void setColor(int r, int g, int b){
		this.r = r/255f;
		this.g = g/255f;
		this.b = b/255f;
	}

	public float getR() {
		return r;
	}

	public void setR(float r) {
		this.r = r;
	}

	public float getG() {
		return g;
	}

	public void setG(float g) {
		this.g = g;
	}

	public float getB() {
		return b;
	}

	public void setB(float b) {
		this.b = b;
	}
	
	
	
}
