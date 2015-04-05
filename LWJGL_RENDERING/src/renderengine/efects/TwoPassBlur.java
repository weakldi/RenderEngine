package renderengine.efects;

public class TwoPassBlur {
	private BlurEfect blurX;
	private BlurEfect blurY;
	
	private float radius;

	public TwoPassBlur(float radius) {
		super();
		this.radius = radius;
		blurX = new BlurEfect(true, radius, 1, 0);
		blurY = new BlurEfect(true, radius, 0, 1);
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
		blurX.setRadius(radius);
		blurY.setRadius(radius);
	}
	
	
}
