package renderengine.core;

import java.awt.Canvas;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.PixelFormat;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL11.*;
public class Window {
	private static int w;
	private static int h;
	private static boolean updateCams = false;
	private static int FPS = 60;
	private static boolean canvas = false;
	private static int samples = 16;
	public static void createWindow(int w,int h){
		Window.w = w;
		Window.h = h;
		try {
			Display.setDisplayMode(new DisplayMode(w, h));
			Display.create(new PixelFormat(32, 0, 24, 0, 0));
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	public static void createWindow(Canvas c){
		Window.w = c.getWidth();
		Window.h = c.getHeight();
		try {
			Display.setParent(c);
			Display.create(new PixelFormat(32, 0, 24, 0, 0));
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(1);
		}
		canvas = true;
	}
	
	public static void bindAsRenderTarget(){
		glBindFramebuffer(GL_FRAMEBUFFER, 0);
		glViewport(0, 0, w, h);
	}
	
	public static void createWindow(int w,int h,String titel){
		createWindow(w, h);
		setTitle(titel);
	}
	
	public static void setTitle(String title){
		Display.setTitle(title);
	}
	
	public static String getTitle(){
		return Display.getTitle();
	}
	
	public static boolean isWaitingForClose(){
		return Display.isCloseRequested();
	}
	
	public static void update(){
		Display.update();
	}
	
	public static void update(int fps){
		Display.sync(fps);
		update();
	}
	
	public static void close(){
		Display.destroy();
	}

	public static int getW() {
		return w;
	}

	public static int getH() {
		return h;
	}

	public static void setW(int w) {
		Window.w = w;
		updateCams = true;
		if(!canvas)
			updateDisplayMode();
	}

	public static void setH(int h) {
		Window.h = h;
		updateCams = true;
		if(!canvas)
			updateDisplayMode();
	}
	
	public static void setDisplayMode(int w,int h){
		Window.w = w;
		Window.h = h;
		updateCams = true;
		if(!canvas)
			updateDisplayMode();
	}
	
	public static void updateDisplayMode(){
		try {
			Display.setDisplayMode(new DisplayMode(w, h));
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean isUpdateCams() {
		return updateCams;
	}

	public static void setUpdateCams(boolean updateCams) {
		Window.updateCams = updateCams;
	}
	
	

	public static int getFPS() {
		return FPS;
	}

	public static void setFPS(int fPS) {
		FPS = fPS;
	}

	
	

	

	
	
}
