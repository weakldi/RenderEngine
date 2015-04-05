package renderengine.light;

import org.lwjgl.util.vector.Vector3f;

import renderengine.core.AppHandler;
import renderengine.core.Camera;
import renderengine.texture.Texture;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL11.*;

public class ShadowInfo {
	private Camera cam;
	private Texture shadowMap;
	
	public ShadowInfo(int w,int h) {
		shadowMap = new Texture(w, h, GL_COLOR_ATTACHMENT0, Texture.FILTER_LINEAR, true);
		
		cam = new Camera(w, h,false);
		
	}
	
	public void updateCamera(float x,float y,float z,float dirX,float dirY,float dirZ){
		Vector3f center = new Vector3f(0, 0, 0);
		Vector3f eye = new Vector3f(x, y, z);
		Vector3f dir = new Vector3f(dirX, dirY, dirZ);
		Vector3f.add(eye, dir, center);
		cam.lookAt(eye, center, new Vector3f(0,1,0));
	}

	public Camera getCam() {
		return cam;
	}

	public void setCam(Camera cam) {
		this.cam = cam;
	}

	public Texture getShadowMap() {
		return shadowMap;
	}

	
}
