package renderengine.core;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_NONE;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;
public class VAO {
	private int ID = 0;
	private int[] vboIDs;;
	private int elementArray = 0;
	private int vertexCount = 0;
	public VAO(){
		genVAO();
		vboIDs = new int[16];
		for (int i = 0; i < vboIDs.length; i++) {
			vboIDs[i] = 0;
		}
	}
	
	private void genVAO(){
		ID = glGenVertexArrays();
	}
	
	private int genVBO(){
		return glGenBuffers();
	}
	
	public void delete(){
		
		
		unBind();
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		for (int i = 0; i < vboIDs.length; i++) {
			if(vboIDs[i]!=0){
				glDeleteBuffers(vboIDs[i]);
			}
		}
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		if(elementArray!=0)
			glDeleteBuffers(elementArray);
		glDeleteVertexArrays(ID);
	}
	
	public void addData(float[] data,int attrib,int size){
		bind();
		int vbo = genVBO();
		addVbo(attrib, vbo);
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, Buffers.genFlippedFloatBuffer(data), GL_STATIC_DRAW);
		glVertexAttribPointer(attrib, size, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		unBind();
	}
	
	
	
	private void addVbo(int attrib,int id){
		if(attrib >= 0 && attrib < 16){			
			vboIDs[attrib] = id;
		}
	}
	
	public void addElementArray(int[] data){
		bind();
		this.elementArray = genVBO();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, elementArray);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, Buffers.genFlippedIntBuffer(data), GL_STATIC_DRAW);
		vertexCount = data.length;
		unBind();
	}

	public int getID() {
		return ID;
	}
	
	public int getVertexCount(){
		return vertexCount;
	}
	
	public void setVertexCount(int count){
		this.vertexCount = count;
	}

	public int getElementArray() {
		return elementArray;
	}
	
	public void bind(){
		glBindVertexArray(ID);
		for (int i = 0; i < vboIDs.length; i++) {
			if(vboIDs[i]!=0){
				glEnableVertexAttribArray(i);
			}
		}
	}
	
	public void unBind(){
		for (int i = 0; i < vboIDs.length; i++) {
			if(vboIDs[i]!=0){
				glDisableVertexAttribArray(i);
			}
		}
		glBindVertexArray(GL_NONE);
	}
	
	
	
	
}
