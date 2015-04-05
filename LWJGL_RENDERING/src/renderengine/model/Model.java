package renderengine.model;

import renderengine.core.AppHandler;
import renderengine.core.Color;
import renderengine.core.Material;
import renderengine.core.Matrix;
import renderengine.core.VAO;
import renderengine.core.Vertex;
import renderengine.texture.Texture;
import static org.lwjgl.opengl.GL11.*;
public class Model {
	protected VAO vao;
	
	
	private final int modelID;
	
	public Model(){
		vao = new VAO();
		
		AppHandler.mainApp.addModel(this);
		modelID = Models.getModelCount();
		Models.addModel(this);
	}
	
	protected void addVertices(Vertex[] vertices){
		float[] vertexPos = new float[vertices.length*3];
		float[] normals = new float[vertices.length*3];
		float[] uvs = new float[vertices.length*2];
		
		
		for (int i = 0; i < vertices.length; i++) {
			vertexPos[3*i+0] = vertices[i].getVertexX();
			vertexPos[3*i+1] = vertices[i].getVertexY();
			vertexPos[3*i+2] = vertices[i].getVertexZ();
			if(vertices[i].hasNormal()){
				normals[i*3+0] = vertices[i].getVertexNormalX();
				normals[i*3+1] = vertices[i].getVertexNormalY();
				normals[i*3+2] = vertices[i].getVertexNormalZ();
			}
			if(vertices[i].hasUV()){
				uvs[i*2+0] = vertices[i].getVertexU();
				uvs[i*2+1] = vertices[i].getVertexV();
			}
		}
		
		vao.addData(vertexPos, 0, 3);
		vao.addData(normals, 1, 3);
		vao.addData(uvs, 2, 2);
	}
	
	protected void addIndicies(int[] ind){
		vao.addElementArray(ind);
	}
	
	public void render(){
		bindModel();
		renderEntities();
		unbindModel();
	}
	
	public void bindModel(){
		
		vao.bind();
	}
	
	public void renderEntities(){
		
		glDrawElements(GL_TRIANGLES, vao.getVertexCount(), GL_UNSIGNED_INT, 0L);
		
	}
	
	public void unbindModel(){
		vao.unBind();
		
	}
	
	
	public void delete(){
		AppHandler.mainApp.removeModel(this);
		vao.delete();
	}

	public int getModelID() {
		return modelID;
	}
	
	
	
}
	