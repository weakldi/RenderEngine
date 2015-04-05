package renderengine.core;

import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL32.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.ARBFramebufferSRGB;

public class GLUtil {
	public static void initGL(){
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_CULL_FACE);
		glEnable(GL_DEPTH_CLAMP);
		glCullFace(GL_BACK);
		setClearColor(0, 0, 0);
		
		
	}
	
	public static void setClearColor(float r, float g,float b){
		glClearColor(r, g, b, 1);
	}
	
	public static void clearScreen(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
}
