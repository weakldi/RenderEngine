package renderengine.model;

import renderengine.core.Camera;
import renderengine.core.VAO;
public class SkyBox {
	private VAO cube;

	private int TextureID;
	private float[] vert;
	private int[] ind;
	
	private static final float SIZE = 500f;
	
	private static final float[] VERTICES = {        
	    -SIZE,  SIZE, -SIZE,
	    -SIZE, -SIZE, -SIZE,
	    SIZE, -SIZE, -SIZE,
	     SIZE, -SIZE, -SIZE,
	     SIZE,  SIZE, -SIZE,
	    -SIZE,  SIZE, -SIZE,

	    -SIZE, -SIZE,  SIZE,
	    -SIZE, -SIZE, -SIZE,
	    -SIZE,  SIZE, -SIZE,
	    -SIZE,  SIZE, -SIZE,
	    -SIZE,  SIZE,  SIZE,
	    -SIZE, -SIZE,  SIZE,

	     SIZE, -SIZE, -SIZE,
	     SIZE, -SIZE,  SIZE,
	     SIZE,  SIZE,  SIZE,
	     SIZE,  SIZE,  SIZE,
	     SIZE,  SIZE, -SIZE,
	     SIZE, -SIZE, -SIZE,

	    -SIZE, -SIZE,  SIZE,
	    -SIZE,  SIZE,  SIZE,
	     SIZE,  SIZE,  SIZE,
	     SIZE,  SIZE,  SIZE,
	     SIZE, -SIZE,  SIZE,
	    -SIZE, -SIZE,  SIZE,

	    -SIZE,  SIZE, -SIZE,
	     SIZE,  SIZE, -SIZE,
	     SIZE,  SIZE,  SIZE,
	     SIZE,  SIZE,  SIZE,
	    -SIZE,  SIZE,  SIZE,
	    -SIZE,  SIZE, -SIZE,

	    -SIZE, -SIZE, -SIZE,
	    -SIZE, -SIZE,  SIZE,
	     SIZE, -SIZE, -SIZE,
	     SIZE, -SIZE, -SIZE,
	    -SIZE, -SIZE,  SIZE,
	     SIZE, -SIZE,  SIZE
	};
	
	public SkyBox(int sIZE, int textureID) {
		this.cube = new VAO();
		
		TextureID = textureID;
		
		
		
		cube.addData(VERTICES, 0, 3);
		
		
		
	}
	
	public void render(Camera cam){
//		AppHandler.skyShader.useShader();
//		AppHandler.skyShader.loadProjectionMat(cam.getProjectionMatrix());
//		AppHandler.skyShader.loadViewMat(cam.getViewMatrix());
//		GL11.glDisable(GL11.GL_CULL_FACE);
//		glActiveTexture(GL_TEXTURE0);
//		glBindTexture(GL_TEXTURE_CUBE_MAP, TextureID);
//		cube.bind();
//		glDrawArrays(GL_TRIANGLES, 0, VERTICES.length);
//		cube.unBind();
//		AppHandler.skyShader.unbindShader();
	}
	
	
	public int getTextureID() {
		return TextureID;
	}
	public void setTextureID(int textureID) {
		TextureID = textureID;
	}
	
	
}
