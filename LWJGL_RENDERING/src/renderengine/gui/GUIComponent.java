package renderengine.gui;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector4f;

import renderengine.core.AppHandler;
import renderengine.core.Matrix;
import renderengine.core.VAO;
import renderengine.core.Window;
import renderengine.texture.Texture;

public class GUIComponent {
	protected Texture texture;
	protected static VAO quad;
	private List<GUIComponent> children;
	private GUIComponent parent;
	protected Matrix transMat;
	protected float scaleX  = 1;
	protected float scaleY = 1;
	protected float x = 0,y = 0;
	protected float rot = 0;
	
	private static float[] pos = new float[]{
		-1,1, -1,-1, 1,1, 1,-1
	};
	
	private static float[] uvs = new float[]{
		  0,01 ,0,0 ,01,01, 01,0
	};
	
	public GUIComponent(boolean root) {
		children = new ArrayList<GUIComponent>();
		transMat = new Matrix();
		if(quad == null){
			genVAO();
		}
		if(!root){
			AppHandler.mainApp.getRenderEngine().getRoot().addChild(this);
		}
		
	}
	public GUIComponent() {
		children = new ArrayList<GUIComponent>();
		transMat = new Matrix();
		if(quad == null){
			genVAO();
		}
		AppHandler.mainApp.getRenderEngine().getRoot().addChild(this);
	}
	
	private static void genVAO(){
		quad = new VAO();
		quad.addData(pos, 0, 2);
		quad.addData(uvs, 1, 2);
	}
	
	public void render(){

		
		if(texture!=null){
			AppHandler.guiShader.loadModelMat(getTransMat());
			quad.bind();
			texture.bind();
			GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);
			texture.unbind();
			quad.unBind();
		}
	}
	public void update(){
		
	}
	public void renderAll(){
		update();
		AppHandler.guiShader.useShader();
		render();
		for (GUIComponent child : children) {
			child.renderAll();
		}
		AppHandler.guiShader.unbindShader();
	}

	
	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	public void addChild(GUIComponent comp){
		children.add(comp);
		comp.setParent(this);
	}
	
	public void removeChild(GUIComponent comp){
		children.remove(comp);
	}
	public List<GUIComponent> getChildren() {
		return children;
	}

	public void setChildren(List<GUIComponent> children) {
		this.children = children;
	}

	public GUIComponent getParent() {
		return parent;
	}

	public void setParent(GUIComponent parent) {
		if(this.parent!=null)parent.removeChild(this);
		this.parent = parent;
		
	}

	public Matrix getTransMat() {
		
		transMat.setIdentity();
		if(parent!=null){
			transMat.mul(parent.getTransMat());
		}
		
		transMat.rotate(0, 0, rot);
		transMat.translate(x, y, 0);
		transMat.scale(scaleX , scaleY , 1);
		return transMat;
	}
	public float getScaleX() {
		return scaleX;
	}
	public void setScaleX(float scaleX) {
		this.scaleX = scaleX;
	}
	public float getScaleY() {
		return scaleY;
	}
	public void setScaleY(float scaleY) {
		this.scaleY = scaleY;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getRot() {
		return rot;
	}
	public void setRot(float rot) {
		this.rot = rot;
	}
}
