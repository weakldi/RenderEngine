package renderengine.model;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import renderengine.core.AppHandler;
import renderengine.core.Camera;
import renderengine.core.Matrix;
import renderengine.core.VAO;
import renderengine.shader.Shader;
import renderengine.texture.TextureLoader;
public class SkyBox {
	private Quader q;
	private float size;
	private Shader shader;
	private int texture;
	public SkyBox(float size,String[] textures) {
		q = new Quader(size, size, size);
		this.size = size;
		shader = AppHandler.mainApp.renderEngine.loadShader("skyShader", "res/shaders/skybox.vert", "res/shaders/skybox.frag");
		texture = TextureLoader.loadCubeMap(textures);
	}
	
	public void render(Camera cam){
		
		shader.bind();
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL13.GL_TEXTURE_CUBE_MAP, texture);
		Matrix view = cam.getViewMatrix();
		view.translate(cam.getX(), cam.getY(), cam.getZ());
		shader.loadUpMat4("projMat", cam.getProjectionMatrix());
		shader.loadUpMat4("viewMat", view);
		q.render();
		shader.unbind();
	}
	
	
}
