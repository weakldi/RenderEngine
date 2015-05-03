package renderengine.test;

import renderengine.core.Color;
import renderengine.core.Entity;
import renderengine.core.FlyCamera;
import renderengine.core.MainApplication;
import renderengine.core.Transformation;
import renderengine.efects.FXAAEfect;
import renderengine.light.PointLight;
import renderengine.light.SpotLight;
import renderengine.model.Model;
import renderengine.model.Models;
import renderengine.model.OBJLoader;
import renderengine.model.Quader;
import renderengine.model.SkyBox;
import renderengine.texture.Texture;

public class TestOBJLoader extends MainApplication{
	private PointLight light;
	private Entity obj;
	public TestOBJLoader(OBJLoaderFrame f) {
		super(f.getRenderCanvas(),f);
	}
	@Override
	protected void initApp() {
		ambientLight.setLightInt(new Color(0.3f,0.3f,0.3f));
		light = new PointLight(new Color(1, 1, 1), 0, 0, 0, 1, 0, 0,500);
		new PointLight(new Color(0.05f,0.05f,0.05f), 0, 10, -50, 1, 0, 0,300);
		cam = new FlyCamera(getW(), getH());
		cam.setZ(10);
		cam.setX(10);
		cam.setY(10);
		cam.setrX(27);
		cam.setrY(315);
		obj = new Entity(new Transformation(0, 0, 0), OBJLoader.loadOBJ("res/models/tardisMain.obj").getModelID());
		obj.setTexture(new Texture("res\\Textures\\TARDIS.png",Texture.FILTER_MIPMAP_NEAREST_NEAREST,false));
		Entity e = new Entity(new Transformation(0, -10, 0, 0, 0, 0), new Quader(500,0.05f,500).getModelID());
		
		new FXAAEfect(true);
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
			
			obj.setScale((float)sX, (float)sY, (float)sZ);
			obj.setRotation((float)rX, (float)rY, (float)rZ);
			obj.setColor(modelColor);
			
			light.setColor(lightColor);
			f.update = false;
		}
		
	}

	@Override
	protected void exit() {
		
	}

}
