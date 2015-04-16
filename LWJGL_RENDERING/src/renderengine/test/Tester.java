package renderengine.test;


import java.beans.Customizer;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import renderengine.core.Color;
import renderengine.core.Entity;
import renderengine.core.FlyCamera;
import renderengine.core.GameComponent;
import renderengine.core.MainApplication;
import renderengine.core.Matrix4f;
import renderengine.core.Transformation;
import renderengine.core.Window;
import renderengine.efects.BlurEfect;
import renderengine.efects.FXAAEfect;
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
import renderengine.model.Quader;
import renderengine.texture.Texture;

public class Tester {
	public static void main(String[] args) {
//		MainApplication app = new MainApplication(1024,720) {
//			
//			Entity e;
//			PointLight light;
//			DiractionalLight l;
//			SpotLight spotLight;
//			TextComponent text;
//			float lastPos = 0;
//			@Override
//			protected void updateApp(float tslf) {
////				if(Keyboard.isKeyPressed(Keyboard.KEY_S)){
//////					spotLight.setEnabeld(false);
////					
////				}else{
//////					spotLight.setEnabeld(true);
////					
////				}
//////				e.getTransformation().setZ((float) (-20+Math.sin(lastPos)));
//////				dragon.setrY(dragon.getrY()+10*tslf);
////				lastPos+=tslf;
//////				light.setX((float) Math.sin(lastPos)*10);
//////				cam.setrY(cam.getrY()+10*tslf);
//////				Vector3f center = new Vector3f(0, 0, 0);
//////				Vector3f eye = new Vector3f(0, 100, -100);
//////				Vector3f dir = new Vector3f(l.getxDir(), l.getyDir(), l.getzDir());
//////				Vector3f.add(eye, dir, center);
//////				cam.lookAt(eye, center, new Vector3f(0,1,0));
////				cam.setY(3);
////				Color c = new Color((float) ((Math.cos(lastPos))*0.5+0.5), (float) ((Math.sin(lastPos))*0.5+0.5), 1);
//////				spotLight.setY((float) ((Math.cos(lastPos))*0.5+0.5)*10+20);
//////				spotLight.setColor(c);
//////				e.setTranslation(10, 0, 0);
//////				e.setRotation(0, lastPos, 0);
//////				e.setRotation(0, 0, 10, 0, lastPos, 0);
////				text.setText("Camera(x=" + cam.getX()+" y=" + cam.getY() + " z=" + cam.getZ() + ")\n"
////						+	"Settings(FOV=" + 45 + " Display[w=" + Display.getWidth() + "|h=" + Display.getHeight() + "])");
//////				area.setX((float)(Math.cos(lastPos)));
////				area.setScaleX((float)Math.cos(lastPos)*0.5f+0.5f);
//			}
//			
//			@Override
//			protected void initApp() {
//				GameComponent comp = new GameComponent(new Transformation(0, 10, 0)) {
//					
//					@Override
//					public void update(float tslf) {
//						// TODO Auto-generated method stub
//						
//					}
//				};
////				new TwoPassBlur(1);
//				cam.setZ(0);
//				cam.setX(0);
//				Texture texture =new Texture("res/textures/GUIAtlas.png",Texture.FILTER_NEAREST,true);
//				ambientLight.setLightInt(new Color(0,0,0));
//				Model dragon = OBJLoader.loadOBJ("res/models/cube.obj");
//				Quader q = new Quader(4, 2, 2);
//				e = new Entity(new Transformation(0, 1, -20), dragon.getModelID());
//				e.getMat().setSpecularIntensity(10);
//				e.setColor(1, 0.5f, 0.25f);
//				e.setTexture(new Texture("res/textures/dragon.png"));
////				e.setParent(comp);
////				e.setRotation(10, 0, 0, 0, 30, 0);
////				e.setTexture(texture);
//				
//				Entity pE = new Entity(new Transformation(0, 0, -10, 270, 0, 0), new Plane(100, 100).getModelID());
//				
//				
////				pE.setTexture(new Texture("res/textures/top.png",Texture.FILTER_MIPMAP_LINEAR_LINEAR,false));
//				pE.getMat().setSpecularIntensity(0.5f);
////				pE.addChild(e);
//				light = new PointLight(new Color(1f, 0.5f, 0.35f), 0, 12, -50, 0.5f, 0.5f, 0.0f);
//				l = new DiractionalLight(new Color(1f,1,1), 0, -10, 5);
//				spotLight = new SpotLight(new Color(0.25f,0.25f,0.25f),0,10,-40,0,-0.2f,1,3,0,0.0f,0.9f);
////				spotLight = new SpotLight(new Color(0.25f,0.25f,0.25f),0,20, 20,0,1,-1,3,0,0.0f,0.9f);
//				new SpotLight(new Color(0.25f,0.25f,0.25f),0,20,-20,0,-1,-1,3,0,0.0f,0.3f);
////				OBJModel cube = OBJLoader.loadOBJ("res/models/cube.obj");
////				cube.setZ(-50);
////				cube.setTexture(texture);
//				new SpotLight(new Color(0.25f,0.25f,0.25f),20,20,-30,0,-0.5f,-1,3,0,0.0f,0.5f);
////				new PointLight(new Color(1f, 0f, 0f), 0, 1, 10, 1f, 0,0f);
//				new PointLight(new Color(0f, 0f, 1f), 10, 1, -30, 1f, 0.5f, 0.0f);
//				new PointLight(new Color(0f, 1f, 0f), -10, 1, -30, 1f, 0.5f, 0.0f);
//				cam = new FlyCamera(1024, 1024);
//				cam.setZ(-60);
//				cam.setrX(0);
////				cam = spotLight.getShadowInfo().getCam();
//				Window.setFPS(100);
//				
//				Keyboard.addKey(Keyboard.KEY_S);
////				new SpotLight(new Color(1f, 1, 1), 0, 4, 30, 0, -1f, -1, 0.1f, 0, 0, 0.5f);
//				
//			
//				
//				text = new TextComponent(new Font(new Texture("res/textures/font.png")), "Camera(x=" + cam.getX()+" y=" + cam.getY() + " z=" + cam.getZ() + ")",0);
//				text.setX(-0.9f);
//				text.setY(0.9f);
//				text.setTextColor(new Color(1f,0.75f,0.65f));
////				text.setSize(3);
////				area = new TextField(new Texture("res/textures/GUIAtlas.png",Texture.FILTER_NEAREST,true),"Dies ist ein Textfeld!\n"
////						+ "es soll die funktionalität des\n"
////						+ "TextFeldes Veranschauichen!\n"
////						+ "Der Text geht über mehre Zeilen.");
////				area.setScaleX(0.5f);
////				area.setScaleY(0.25f);
////				area.setX(0.0f);
////				area.setTextSize(1.25f);
////				area.setFont(font2);
////				area.setColor(new Color(0, 1f, 0));
////				
//				Texture[] textures = new Texture[]{texture,texture,texture};
////				Button b = new Button(textures, "hallo");
////				b.setScaleX(0.5f);
////				b.setScaleY(0.5f);
////				b.setX(0.25f);
////				b.setTextSize(4);
////				area = new InputBox(texture, "test");
////				area.setScaleX(0.5f);
////				area.setScaleY(0.25f);
////				area.setY(0.6f);
////				area.setX(-0.3f);
////				area.setTextSize(2);
//				Matrix4f mat = new Matrix4f().setIdentity().translate(10, 10, 5).scale(0.5f, 0.5f, 0.5f).mul(4);
//				System.out.println(mat);
////				new FXAAEfect(true);
//				SpotLight s = new SpotLight(new Color(0.25f,0.25f,0.25f),-40,10,-40,3,-1,3,1,1,0f,0.9f);
//				ambientLight.setLightInt(new Color(0.1f,0.1f,0.1f));
//				
//				Quader a = new Quader(1, 1, 1);
//				Entity quader = new Entity(new Transformation(0, 0.5f, -20), a.getModelID());
//				quader.setColor(0.5f, 1, 0.25f);
//				cam = new FlyCamera(1024, 1024);
//				cam.setY(1);
//				Entity c = new Entity(new Transformation(0, 0, -10, 270, 0, 0), new Plane(100, 100).getModelID());
//				c.getMat().setSpecularIntensity(0.0f);
//			}
//			TextField area;
//			@Override
//			protected void exit() {
//				
//			}
//		};
//		
//		app.start();
		
		new TestShadowMap(1024,1024).start();
	}
}
