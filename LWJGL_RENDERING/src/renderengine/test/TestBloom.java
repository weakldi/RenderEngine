package renderengine.test;

import renderengine.core.Color;
import renderengine.core.Entity;
import renderengine.core.FlyCamera;
import renderengine.core.MainApplication;
import renderengine.core.Transformation;
import renderengine.efects.BloomEfect;
import renderengine.efects.FXAAEfect;
import renderengine.input.Mouse;
import renderengine.light.PointLight;
import renderengine.light.SpotLight;
import renderengine.model.Model;
import renderengine.model.OBJLoader;

public class TestBloom extends MainApplication{

	
	Entity affe;
	PointLight p;
	FlyCamera c;

	

	@Override
	protected void updateApp(float tslf) {
		c = (FlyCamera) cam;
		c.setSpeed(c.getSpeed()+Mouse.getWheelRot()*0.01f);
		if(c.getSpeed()>100)
			c.setSpeed(100);
		else if(c.getSpeed()<0)
			c.setSpeed(0);
		p.setX(c.getX());
		p.setY(c.getY());
		p.setZ(c.getZ());
	}
	
	@Override
	protected void initApp() {
		
		cam = new FlyCamera(getW(), getH());
		
		Model affe = OBJLoader.loadOBJ("res/models/affe.obj");
		ambientLight.setLightInt(new Color(0.1f,0.1f,0.1f));
	
		this.affe = new Entity(new Transformation(0, -5f, -20), affe.getModelID());
		this.affe.setColor(0.5f, 1, 0.25f);
		this.affe.getMat().setSpecularExponent(128);
		this.affe.getMat().setSpecularIntensity(10);
		p = new PointLight(new Color(1f,1,1), 3, 10, -20, 1, 0, 0);
		new BloomEfect(true);
		new FXAAEfect(true);
	}
	
	@Override
	protected void exit() {
		
	}
}
