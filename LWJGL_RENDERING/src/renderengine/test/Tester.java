package renderengine.test;


import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import renderengine.core.Color;
import renderengine.core.Entity;
import renderengine.core.FlyCamera;
import renderengine.core.GameComponent;
import renderengine.core.MainApplication;
import renderengine.core.Transformation;
import renderengine.core.Window;
import renderengine.efects.BlurEfect;
import renderengine.efects.TwoPassBlur;
import renderengine.gui.AreaComponent;
import renderengine.gui.Button;
import renderengine.gui.Font;
import renderengine.gui.GUIComponent;
import renderengine.gui.InputBox;
import renderengine.gui.TextComponent;
import renderengine.gui.TextField;
import renderengine.input.Keyboard;
import renderengine.light.DiractionalLight;
import renderengine.light.PointLight;
import renderengine.light.SpotLight;
import renderengine.model.Model;
import renderengine.model.OBJLoader;
import renderengine.model.OBJModel;
import renderengine.model.Plane;
import renderengine.texture.Texture;

public class Tester {
	public static void main(String[] args) {
		MainApplication app = new MainApplication(1024,720) {
			
			Entity e;
			PointLight light;
			DiractionalLight l;
			SpotLight spotLight;
			TextComponent text;
			float lastPos = 0;
			@Override
			protected void updateApp(float tslf) {
				if(Keyboard.isKeyPressed(Keyboard.KEY_S)){
					spotLight.setEnabeld(false);
					
				}else{
					spotLight.setEnabeld(true);
					
				}
				
//				dragon.setrY(dragon.getrY()+10*tslf);
				lastPos+=tslf;
				light.setX((float) Math.sin(lastPos)*10);
//				cam.setrY(cam.getrY()+10*tslf);
//				Vector3f center = new Vector3f(0, 0, 0);
//				Vector3f eye = new Vector3f(0, 100, -100);
//				Vector3f dir = new Vector3f(l.getxDir(), l.getyDir(), l.getzDir());
//				Vector3f.add(eye, dir, center);
//				cam.lookAt(eye, center, new Vector3f(0,1,0));
				cam.setY(3);
				Color c = new Color((float) ((Math.cos(lastPos))*0.5+0.5), (float) ((Math.sin(lastPos))*0.5+0.5), 1);
				spotLight.setY((float) ((Math.cos(lastPos))*0.5+0.5)*10+20);
				spotLight.setColor(c);
//				e.setTranslation(10, 0, 0);
//				e.setRotation(0, lastPos, 0);
				e.setRotation(10, 0, 0, 0, lastPos, 0);
				text.setText("Camera(x=" + cam.getX()+" y=" + cam.getY() + " z=" + cam.getZ() + ")\n"
						+	"Settings(FOV=" + 45 + " Display[w=" + Display.getWidth() + "|h=" + Display.getHeight() + "])");
//				area.setX((float)(Math.cos(lastPos)));
//				area.setScaleX((float)Math.cos(lastPos)*0.5f+0.5f);
			}
			
			@Override
			protected void initApp() {
				GameComponent comp = new GameComponent(new Transformation(0, 10, 0)) {
					
					@Override
					public void update(float tslf) {
						// TODO Auto-generated method stub
						
					}
				};
//				new TwoPassBlur(1);
				cam.setZ(25);
				cam.setX(10);
				Texture texture =new Texture("res/textures/GUIAtlas.png",Texture.FILTER_NEAREST,true);
				ambientLight.setLightInt(new Color(0.1f,0.1f,0.1f));
				Model dragon = OBJLoader.loadOBJ("res/models/dragon.obj");
				e = new Entity(new Transformation(10, 0, 0), dragon.getModelID());
				e.getMat().setSpecularExponent(128);
				e.getMat().setSpecularIntensity(10);
				e.setColor(1, 0.5f, 0.25f);
//				e.setParent(comp);
//				e.setRotation(10, 0, 0, 0, 30, 0);
//				e.setTexture(texture);
				
				Entity pE = new Entity(new Transformation(0, 0, -10, 270, 0, 0), new Plane(100, 100).getModelID());
				
				
//				pE.setTexture(new Texture("res/textures/top.png",Texture.FILTER_MIPMAP_LINEAR_LINEAR,false));
				pE.getMat().setSpecularIntensity(0.5f);
//				pE.addChild(e);
				light = new PointLight(new Color(1f, 0.5f, 0.35f), 0, 12, -50, 0.5f, 0.5f, 0.0f);
				l = new DiractionalLight(new Color(1f,1,1), 0, -10, 5);
				spotLight = new SpotLight(new Color(0.25f,0.25f,0.25f),0,20,-70,0,-1,1,3,0,0.0f,0.9f);
//				OBJModel cube = OBJLoader.loadOBJ("res/models/cube.obj");
//				cube.setZ(-50);
//				cube.setTexture(texture);
				new SpotLight(new Color(0.25f,0.25f,0.25f),20,20,-70,-1,-1,1,1,0,0.0f,0.5f);
				new PointLight(new Color(1f, 0f, 0f), 10, 1, 0, 1f, 0,0f);
				new PointLight(new Color(0f, 0f, 1f), 10, 1, -30, 1f, 0.5f, 0.0f);
				new PointLight(new Color(0f, 1f, 0f), -10, 1, -30, 1f, 0.5f, 0.0f);
				cam = new FlyCamera(1024, 1024);
				
				Window.setFPS(100);
				
				Keyboard.addKey(Keyboard.KEY_S);
				Font font2 = new Font(new Texture("res/textures/font2.png"));
				TextComponent nastiText = new TextComponent(font2, "Nastasia<3\nNastii\nNastasia",0);
				
				nastiText.setSize(0.5f);
				nastiText.setX(-0.5f);
				nastiText.setY(-0.5f);
				nastiText.setRot(10);
			
				
				text = new TextComponent(new Font(new Texture("res/textures/font.png")), "Camera(x=" + cam.getX()+" y=" + cam.getY() + " z=" + cam.getZ() + ")",0);
				text.setX(-0.9f);
				text.setY(0.9f);
				text.setTextColor(new Color(1f,0.75f,0.65f));
//				text.setSize(3);
//				area = new TextField(new Texture("res/textures/GUIAtlas.png",Texture.FILTER_NEAREST,true),"Dies ist ein Textfeld!\n"
//						+ "es soll die funktionalität des\n"
//						+ "TextFeldes Veranschauichen!\n"
//						+ "Der Text geht über mehre Zeilen.");
//				area.setScaleX(0.5f);
//				area.setScaleY(0.25f);
//				area.setX(0.0f);
//				area.setTextSize(1.25f);
//				area.setFont(font2);
//				area.setColor(new Color(0, 1f, 0));
//				
				Texture[] textures = new Texture[]{texture,texture,texture};
//				Button b = new Button(textures, "hallo");
//				b.setScaleX(0.5f);
//				b.setScaleY(0.5f);
//				b.setX(0.25f);
//				b.setTextSize(4);
//				area = new InputBox(texture, "test");
//				area.setScaleX(0.5f);
//				area.setScaleY(0.25f);
//				area.setY(0.6f);
//				area.setX(-0.3f);
//				area.setTextSize(2);
			}
			TextField area;
			@Override
			protected void exit() {
				
			}
		};
		
		app.start();
	}
}
