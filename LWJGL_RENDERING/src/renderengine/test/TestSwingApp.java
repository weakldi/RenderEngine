package renderengine.test;

import java.awt.Canvas;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.lwjgl.util.vector.Vector3f;

import renderengine.core.Color;
import renderengine.core.Entity;
import renderengine.core.FlyCamera;
import renderengine.core.MainApplication;
import renderengine.core.Transformation;
import renderengine.core.Window;
import renderengine.efects.FXAAEfect;
import renderengine.gui.TextField;
import renderengine.input.Keyboard;
import renderengine.input.Mouse;
import renderengine.light.PointLight;
import renderengine.light.SpotLight;
import renderengine.model.Model;
import renderengine.model.OBJLoader;
import renderengine.model.Plane;
import renderengine.model.Quader;
import renderengine.texture.Texture;

public class TestSwingApp extends MainApplication{
	public TestSwingApp(Canvas c,final JFrame jf) {
		
		super(c,jf);
		
		jf.setSize(1024, 1024);
		jf.setLocationRelativeTo(null);
		jf.setTitle("Test lwjgl in Swing app");
		jf.add(c);
		
	}
	Entity player;
	Entity quader;
	SpotLight s;
	FlyCamera c;
	float lastPos = 0;
	

	@Override
	protected void updateApp(float tslf) {
		lastPos+=tslf*0.25f;
		quader.setRotation(0, 0, 0, 0, (float) Math.sin(lastPos)*90f, 0);
		if(Keyboard.isKeyPressed(Keyboard.KEY_L)){
			cam.lookAt(new Vector3f(s.getX(), s.getY(), s.getZ()), new Vector3f(s.getDirX(), s.getDirY(), s.getDirZ()), new Vector3f(0, 1, 0));	
			
		}else{
			player.setTranslation(cam.getX(), 0, cam.getZ());
			player.setRotation(0,-cam.getrY(), 0);
		}
		
		c.setSpeed(c.getSpeed()+Mouse.getWheelRot()*0.01f);
		if(c.getSpeed()>100)
			c.setSpeed(100);
		else if(c.getSpeed()<0)
			c.setSpeed(0);
		
	}
	
	@Override
	protected void initApp() {
		
		s = new SpotLight(new Color(0.25f,0.25f,0.25f),-40,3,-40,3,-1,3,1,0.125f,0f,100,0.3f);
		Model m = OBJLoader.loadOBJ("res/models/affe.obj");
		ambientLight.setLightInt(new Color(0.1f,0.1f,0.1f));
		Quader q = new Quader(1, 1, 1);
		quader = new Entity(new Transformation(-0, 0, -20), m.getModelID());
		quader.setColor(0.5f, 1, 0.25f);
		quader.getMat().setSpecularExponent(128);
		quader.getMat().setSpecularIntensity(10);
		
		player = new Entity(new Transformation(0, 0, 0), new Quader(0.5f, 2, 0.5f).getModelID());
//		new Entity(new Transformation(-20, 0.5f, -10), m.getModelID()).setTexture(new Texture("res/textures/dragon.png"));;
		cam = new FlyCamera(getW(), getH());
		cam.setY(2.f);
		c = (FlyCamera) cam;
		c.setSpeed(3);
		cam.setX(0);
		cam.setY(2);
		cam.setZ(0);
		
		Entity plane = new Entity(new Transformation(0, 0, -20, 90, 0, 0), new Plane(100, 100).getModelID());
		
		plane.getMat().setSpecularIntensity(0.0f);
		PointLight p = new PointLight(new Color(1.5f, 0.5f, 0.5f), -5, 4, -5, 1, 1, 0, 100);
		
		FXAAEfect fxaaAntialising = new FXAAEfect(true);
		fxaaAntialising.setSpanMax(5);
		Keyboard.addKey(Keyboard.KEY_L);
		
		TextField msg = new TextField(new Texture("res/textures/GUIAtlas.png",Texture.FILTER_NEAREST,true),"Um die Sicht der Lichtquelle\n"
				+ "nachzuvolziehen L drücken!");
		msg.setX(-0.475f);
		msg.setY(0.85f);
		msg.setScaleX(0.5f);
		msg.setScaleY(0.125f);
		Window.setFPS(10000);
		
	}
	
	@Override
	protected void exit() {
		
	}

}
