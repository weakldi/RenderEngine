package renderengine.test;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.AWTGLCanvas;
import org.lwjgl.opengl.Display;

import renderengine.core.Color;
import renderengine.core.Entity;
import renderengine.core.FlyCamera;
import renderengine.core.MainApplication;
import renderengine.core.Transformation;
import renderengine.light.PointLight;
import renderengine.model.Model;
import renderengine.model.Models;
import renderengine.model.OBJLoader;
import renderengine.model.Plane;
import renderengine.model.Quader;
import renderengine.texture.Texture;

public class TestOBJLoader extends MainApplication{
	private PointLight light;
	private Entity obj;
	public TestOBJLoader(OBJLoaderFrame f) {
		super(f.getRenderCanvas(),f);
	}
	@Override
	protected void initApp() {
		light = new PointLight(new Color(1, 1f, 1), 0, 0, 0, 1, 0, 0,500);
		cam = new FlyCamera(getW(), getH());
		cam.setZ(10);
		cam.setX(10);
		cam.setY(10);
		
		
		obj = new Entity(new Transformation(0, 0, 0), new Quader().getModelID());
		
		new Entity(new Transformation(0, -30, 0, 0, 0, 0), new Quader(500,0.05f,500).getModelID()).setColor(0.25f, 0.5f, 0.75f);
	}

	@Override
	protected void updateApp(float tslf) {
		light.setX(cam.getX());
		light.setY(cam.getY());
		light.setZ(cam.getZ());
		OBJLoaderFrame f = (OBJLoaderFrame) getF();
		if(f.updateModel){
			Model m = OBJLoader.loadOBJ(f.getModelFile());
			
			int oldID = obj.getModelID();
			
			obj.setModelID(m.getModelID());
			Models.deletModel(oldID);
			System.out.println("new Model");
			f.updateModel = false;
		}if(f.updateTexture){
			Texture t = new Texture(f.getTextureFile());
			Texture old = obj.getTexture();
			obj.setTexture(t);
			old.delete();
			old = null;
			f.updateTexture = false;
		}if(f.update){
			double sX = (Double)f.getScaleX().getValue();
			double sY = (Double)f.getScaleY().getValue();
			double sZ = (Double)f.getScaleZ().getValue();
			double rX = (Double)f.getRotX().getValue();
			double rY = (Double)f.getRotY().getValue();
			double rZ = (Double)f.getRotZ().getValue();
			Color modelColor = new Color(f.getModelC().getRed(),f.getModelC().getGreen(),f.getModelC().getBlue());
			Color lightColor = new Color(f.getLightC().getRed(),f.getLightC().getGreen(),f.getLightC().getBlue());
			System.out.println(f.getScaleY().getValue());
			obj.setScale((float)sX, (float)sY, (float)sZ);
			obj.setRotation((float)rX, (float)rY, (float)rZ);
			obj.setColor(modelColor);
			
			light.setColor(lightColor);
			f.update = false;
		}
		System.out.println(cam.getrX());
		System.out.println(cam.getrY());
	}

	@Override
	protected void exit() {
		
		
	}

}
