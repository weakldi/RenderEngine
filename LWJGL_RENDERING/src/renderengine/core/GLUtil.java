package renderengine.core;

import static org.lwjgl.opengl.GL11.GL_BACK;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glCullFace;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL13.GL_TEXTURE_CUBE_MAP;
import static org.lwjgl.opengl.GL32.GL_DEPTH_CLAMP;
public class GLUtil {
	public static void initGL(){
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_TEXTURE_CUBE_MAP);
		glEnable(GL_CULL_FACE);
		glEnable(GL_DEPTH_CLAMP);
		glCullFace(GL_BACK);
		setClearColor(0, 0, 0);
	}
	
	public static void setClearColor(float r, float g,float b){
		glClearColor(r, g, b, 1);
	}
	
	public static void setClearColor(Color c){
		glClearColor(c.getR(), c.getG(), c.getB(), 1);
	}
	
	public static void clearScreen(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
}
