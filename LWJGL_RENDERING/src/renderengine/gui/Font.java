package renderengine.gui;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import renderengine.core.AppHandler;
import renderengine.core.Color;
import renderengine.core.Matrix;
import renderengine.core.VAO;
import renderengine.shader.Shader;
import renderengine.texture.Texture;

public class Font {
	private Texture texture;
	private Shader shader;
	public Font() {
		this.texture = new Texture("res/textures/font.png");
		if(quad==null){
			genVAO();
		}
		shader = AppHandler.mainApp.renderEngine.loadShader("guiTextureAtlasShader", "res/shaders/GUITextureAtlasShader.vert", "res/shaders/GUITextureAtlasShader.frag");
	}
	public Font(Texture texture) {
		this.texture = texture;
		if(quad==null){
			genVAO();
		}
		shader = AppHandler.mainApp.renderEngine.loadShader("guiTextureAtlasShader", "res/shaders/GUITextureAtlasShader.vert", "res/shaders/GUITextureAtlasShader.frag");

	}
	private static VAO quad;
	private static float[] pos = new float[]{
		-1,1, -1,-1, 1,1, 1,-1
	};
	
	private static float[] uvs = new float[]{
		  0,0 ,0,1 ,1,0, 1,01
	};
	
	private static void genVAO(){
		quad = new VAO();
		quad.addData(pos, 0, 2);
		quad.addData(uvs, 1, 2);
	}
	
	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	public float getYOffset(int char_){
		int spalte = char_/16;
		return (float)spalte/16f;
	}
	public float getXOffset(int char_){
		int zeile = char_ % 16;
		return (float)zeile/16f;
	}
	Matrix mat = new Matrix();
	public void drawTextM(String text,float x,float y,float sizeX,float sizeY,Color c){
		x = x-text.length()/2f*sizeX;
		shader.bind();
		shader.loadUpFloat("rows", 16);
		texture.bind();
		quad.bind();
		for (int i = 0; i < text.length(); i++) {
			mat.setIdentity();
			mat.translate(x, y, 0);
			mat.scale(sizeX, sizeY, 0);
			shader.loadUpMat4("modelMat", mat);
			shader.loadUpVec3("color", new Vector3f(c.getR(),c.getG(),c.getB()));
			shader.loadUpVec2("offset", new Vector2f(getXOffset((int) text.charAt(i)), getYOffset((int) text.charAt(i))));
			
			GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);

			x+=sizeX;
		}
		quad.unBind();
		texture.unbind();
	}
	
	public void drawTextM(String text,float x,float y,float sizeX,float sizeY,Matrix parent,Color c){
		x = x-text.length()/2f*sizeX;
		
		shader.bind();
		shader.loadUpFloat("rows", 16);
		
		texture.bind();
		quad.bind();
		for (int i = 0; i < text.length(); i++) {
		
			mat.getMatGL().load(parent.getMatGL());
			mat.translate(x, y, 0);
			mat.scale(sizeX, sizeY, 0);

			shader.loadUpMat4("modelMat", mat);
			shader.loadUpVec3("color", new Vector3f(c.getR(),c.getG(),c.getB()));
			shader.loadUpVec2("offset", new Vector2f(getXOffset((int) text.charAt(i)), getYOffset((int) text.charAt(i))));
			
			GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);
			x+=sizeX;
		}
		quad.unBind();
		texture.unbind();
	}
	
	public void drawTextL(String text,float x,float y,float sizeX,float sizeY,Color c){
		shader.bind();
		shader.loadUpFloat("rows", 16);
		texture.bind();
		quad.bind();
		for (int i = 0; i < text.length(); i++) {
			mat.setIdentity();
			mat.translate(x, y, 0);
			mat.scale(sizeX, sizeY, 0);
			shader.loadUpMat4("modelMat", mat);
			shader.loadUpVec3("color", new Vector3f(c.getR(),c.getG(),c.getB()));
			shader.loadUpVec2("offset", new Vector2f(getXOffset((int) text.charAt(i)), getYOffset((int) text.charAt(i))));
			
			GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);

			x+=sizeX;
		}
		quad.unBind();
		texture.unbind();
	}
	
	public void drawTextL(String text,float x,float y,float sizeX,float sizeY,Matrix parent,Color c){
		shader.bind();
		shader.loadUpFloat("rows", 16);
		
		texture.bind();
		quad.bind();
		for (int i = 0; i < text.length(); i++) {
		
			mat.getMatGL().load(parent.getMatGL());
			mat.translate(x, y, 0);
			mat.scale(sizeX, sizeY, 0);

			shader.loadUpMat4("modelMat", mat);
			shader.loadUpVec3("color", new Vector3f(c.getR(),c.getG(),c.getB()));
			shader.loadUpVec2("offset", new Vector2f(getXOffset((int) text.charAt(i)), getYOffset((int) text.charAt(i))));
			
			GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);
			x+=sizeX;
		}
		quad.unBind();
		texture.unbind();
	}
	
	public void drawTextR(String text,float x,float y,float sizeX,float sizeY,Color c){

		x = x-text.length()*sizeX;
		shader.bind();
		shader.loadUpFloat("rows", 16);
		texture.bind();
		quad.bind();
		for (int i = 0; i < text.length(); i++) {
			mat.setIdentity();
			mat.translate(x, y, 0);
			mat.scale(sizeX, sizeY, 0);
			shader.loadUpMat4("modelMat", mat);
			shader.loadUpVec3("color", new Vector3f(c.getR(),c.getG(),c.getB()));
			shader.loadUpVec2("offset", new Vector2f(getXOffset((int) text.charAt(i)), getYOffset((int) text.charAt(i))));
			
			GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);

			x+=sizeX;
		}
		quad.unBind();
		texture.unbind();
	}
	
	public void drawTextR(String text,float x,float y,float sizeX,float sizeY,Matrix parent,Color c){
		x = x-text.length()*sizeX;
		shader.bind();
		shader.loadUpFloat("rows", 16);
		
		texture.bind();
		quad.bind();
		for (int i = 0; i < text.length(); i++) {
		
			mat.getMatGL().load(parent.getMatGL());
			mat.translate(x, y, 0);
			mat.scale(sizeX, sizeY, 0);

			shader.loadUpMat4("modelMat", mat);
			shader.loadUpVec3("color", new Vector3f(c.getR(),c.getG(),c.getB()));
			shader.loadUpVec2("offset", new Vector2f(getXOffset((int) text.charAt(i)), getYOffset((int) text.charAt(i))));
			
			GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);
			x+=sizeX;
		}
		quad.unBind();
		texture.unbind();
	}
}
