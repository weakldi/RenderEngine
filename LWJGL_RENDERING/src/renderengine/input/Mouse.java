package renderengine.input;

import org.lwjgl.opengl.Display;

public class Mouse {
	public static final int BUTTON_LEFT = 0;
	public static final int BUTTON_RIGHT = 0;
	private static int dx = 0;
	private static int dy = 0;
	private static boolean hide  = false;
	
	public static void addDX(int dxToAdd){
		dx+=dxToAdd;
	}
	public static void addDY(int dyToAdd){
		dy+=dyToAdd;
	}
	
	
	public static int getDx() {
		
		return dx;
		
		
	}
	public static int getDy() {
		
		return dy;
	}
	
	public static void hide(boolean hide){
		Mouse.hide = hide;
		org.lwjgl.input.Mouse.setGrabbed(hide);
		
	}
	public static boolean isHide() {
		return hide;
	}
	
	public static void reset(){
		dx = 0;
		dy = 0;
	}
	
	public static float getX(){
		return ((float)org.lwjgl.input.Mouse.getX()/(float)Display.getWidth()-0.5f)*2f; //Koordinaten zwischen in Die Koordinaten der GUI umrechnen! (zwischen -1 und 1)
	}
	
	public static float getY(){
		return((float)org.lwjgl.input.Mouse.getY()/(float)Display.getHeight()-0.5f)*2f; //Koordinaten zwischen in Die Koordinaten der GUI umrechnen! (zwischen -1 und 1)
	}
	
	public static boolean isPressed(int button){
		return org.lwjgl.input.Mouse.isButtonDown(button);
	}
	
	public static int getWheelRot(){
		return org.lwjgl.input.Mouse.getDWheel();
	}
	
	
}
