package renderengine.core;

import renderengine.model.Models;
import renderengine.texture.Texture;

public class Entity extends GameComponent{
	private int modelID;
	private Material mat;
	public int getModelID() {
		return modelID;
	}

	@Override
	public void update(float tslf) {
		
	}

	public Entity(Transformation transformation, int modelID) {
		super(transformation);
		this.modelID = modelID;
		mat = new Material(new Color(1f,1,1));
		Models.addEntity(this);
	}

	public Material getMat() {
		return mat;
	}

	public void setMat(Material mat) {
		this.mat = mat;
	}
	
	public void setColor(float r,float g, float b){
		mat.getColor().setColor(r, g, b);
	}
	
	public Color getColor() {
		return mat.getColor();
	}

	public void setColor(Color color) {
		mat.setColor(color);
	}
	
	
	public Texture getTexture() {
		return mat.getTexture();
	}

	public void setTexture(Texture texture) {
		this.mat.setTexture(texture);
	}
	
}
