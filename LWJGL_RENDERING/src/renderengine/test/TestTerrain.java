package renderengine.test;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import renderengine.core.Color;
import renderengine.core.Entity;
import renderengine.core.FlyCamera;
import renderengine.core.MainApplication;
import renderengine.core.Transformation;
import renderengine.light.PointLight;
import renderengine.model.Terrain;

public class TestTerrain extends MainApplication {
	private PointLight p;
	@Override
	protected void initApp() {
		Entity terrain = null;
		try {
			terrain = new Entity(new Transformation(0, -40, 0), new Terrain(ImageIO.read(new File("res/textures/heightmap.png"))).getModelID());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ambientLight.setLightInt(new Color(0.2f,0.2f,0.2f));
		p = new PointLight(new Color(0f,1,1), 0, 10, -300, 0.3f, 1, 0,1000);
		new PointLight(new Color(1f,1,0), 0, 10, +300, 1, 0, 0,1000);
		cam = new FlyCamera(getW(), getH());
		cam.setY(100);
	}

	@Override
	protected void updateApp(float tslf) {
//		p.setX(cam.getX());
//		p.setZ(cam.getZ());

	}

	@Override
	protected void exit() {
		// TODO Auto-generated method stub

	}

}
