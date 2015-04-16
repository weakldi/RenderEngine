package renderengine.core;

import renderengine.texture.Texture;

public class Material {
	private Texture texture;
	private Color color;
	private float specularIntensity;
	private float specularExponent;
	private static Texture baseTexture = null;
	public Material(Color color) {
		super();
		this.color = color;
		this.specularIntensity = 2;
		this.specularExponent = 32;
		if(baseTexture==null){
			baseTexture = new Texture("res/textures/baseTexture.png",Texture.FILTER_NEAREST,false);
		}
		texture = baseTexture;
	}
	
	public Material(Color color, float specularIntensity, float specularExponent,Texture texture) {
		super();
		this.texture = texture;
		this.color = color;
		this.specularIntensity = specularIntensity;
		this.specularExponent = specularExponent;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public float getSpecularIntensity() {
		return specularIntensity;
	}

	public void setSpecularIntensity(float specularIntensity) {
		this.specularIntensity = specularIntensity;
	}

	public float getSpecularExponent() {
		return specularExponent;
	}

	public void setSpecularExponent(float specularExponent) {
		this.specularExponent = specularExponent;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	public static Texture getBaseTexture(){
		if(baseTexture==null){
			baseTexture = new Texture("res/textures/baseTexture.png",Texture.FILTER_NEAREST,false);
		}
		return baseTexture;
	}
	
}
