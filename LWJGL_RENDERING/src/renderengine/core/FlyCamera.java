package renderengine.core;

import renderengine.input.Keyboard;
import renderengine.input.Mouse;



public class FlyCamera extends Camera{
	private float speed = 100;
	private float rotSpeed = 30;
	public FlyCamera(float w, float h) {
		super(w, h);
		Keyboard.addKey(Keyboard.KEY_W);
		Keyboard.addKey(Keyboard.KEY_A);
		Keyboard.addKey(Keyboard.KEY_S);
		Keyboard.addKey(Keyboard.KEY_D);
		Keyboard.addKey(Keyboard.KEY_SPACE);
		Keyboard.addKey(Keyboard.KEY_RCONTROL);
		Keyboard.addKey(Keyboard.KEY_ESCAPE);
	}
	private boolean ESC_pressed = false;
	@Override
	public void update(float tslf) {
		super.update(tslf);
		if(Keyboard.isKeyPressed(Keyboard.KEY_ESCAPE)){
			if(!ESC_pressed){
				Mouse.hide(!Mouse.isHide());
				ESC_pressed = true;
			}
		}else{
			ESC_pressed = false;
		}
		if(Mouse.isHide()==false){
			return;
		}
		rY+=Mouse.getDx()*rotSpeed*tslf;
		rX+= Mouse.getDy()*rotSpeed*tslf*-1;
		if(rX<-90)rX=-90;
		if(rX>90)rX=90;
			if(Keyboard.isKeyPressed(Keyboard.KEY_W))
			{
				
				x += speed * tslf * (float)Math.sin(Math.toRadians(rY));
				z -= speed * tslf * (float)Math.cos(Math.toRadians(rY));
		
			}
			if(Keyboard.isKeyPressed(Keyboard.KEY_S))
			{
		
				x += Math.cos(Math.toRadians(rY - 90)) * speed * tslf;
				z -= Math.sin(Math.toRadians(rY - 90)) * speed * tslf;
			}
			if(Keyboard.isKeyPressed(Keyboard.KEY_D))
			{
			
				x += Math.cos(Math.toRadians(rY)) * speed * tslf;
				z += Math.sin(Math.toRadians(rY)) * speed * tslf;
			}
			if(Keyboard.isKeyPressed(Keyboard.KEY_A))
			{
		
				x -= Math.cos(Math.toRadians(rY)) * speed * tslf;
				z -= Math.sin(Math.toRadians(rY)) * speed * tslf;
			}
			
			
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public float getRotSpeed() {
		return rotSpeed;
	}
	public void setRotSpeed(float rotSpeed) {
		this.rotSpeed = rotSpeed;
	}
	
}
