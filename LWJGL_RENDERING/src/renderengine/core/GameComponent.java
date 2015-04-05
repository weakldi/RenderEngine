package renderengine.core;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.vector.Matrix4f;

public abstract class GameComponent{
	private GameComponent parent;
	private List<GameComponent> children;
	private Transformation transformation;
	
	public GameComponent(Transformation transformation) {
		this();
		this.transformation = transformation;
		
	}
	

	public GameComponent(GameComponent parent) {
		this();
		this.parent = parent;
		parent.addChild(this);
	}



	public GameComponent() {
		super();
		children = new ArrayList<GameComponent>();
		transformation = new Transformation(0, 0, 0);
	}

	public void addChild(GameComponent child){
		children.add(child);
		child.setParent(this);
	}
	
	public void renoveChild(GameComponent child){
		children.remove(child);
		child.parent = null;
	}
	
	public void setParent(GameComponent parent){
		this.parent = parent;
	}
	
	public abstract void update(float tslf);
	
	public void updateAll(float tslf){
		update(tslf);
		for (GameComponent gameComponent : children) {
			gameComponent.updateAll(tslf);
		}
	}
	
	public Matrix getTransFormationMatrix(){
		Matrix mat = transformation.getTransmat();
		if(parent!=null){
			Matrix4f.mul(mat.getMatGL(), parent.getTransFormationMatrix().getMatGL(), mat.getMatGL());
		}
		return mat;
	}

	public Transformation getTransformation() {
		return transformation;
	}

	public void setTransformation(Transformation transformation) {
		this.transformation = transformation;
	}

	public GameComponent getParent() {
		return parent;
	}
	
	public void setTranslation(float x,float y,float z){
		transformation.setTranslation(x, y, z);
	}
	
	public void translate(float x,float y,float z){
		transformation.translate(x, y, z);
	}
	
	public void setRotation(float rX,float rY,float rZ){
		transformation.setRotation(rX, rY, rZ);
	}
	
	public void rotate(float rX,float rY,float rZ){
		transformation.rotate(rX, rY, rZ);
	}
	
	public void setRotation(float x,float y,float z,float rX,float rY,float rZ){
		transformation.setRotation(x, y, z, rX, rY, rZ);
	}
	
	public void rotate(float x,float y,float z,float rX,float rY,float rZ){
		transformation.rotate(x, y, z, rX, rY, rZ);
	}
	
	public void setScale(float sX,float sY,float sZ){
		transformation.setScale(sX, sY, sZ);
	}
	
	public void scale(float sX,float sY,float sZ){
		transformation.scale(sX, sY, sZ);
	}
	
	
	
}
