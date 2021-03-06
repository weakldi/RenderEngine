package renderengine.core;

import java.awt.Canvas;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import renderengine.input.Keyboard;
import renderengine.light.AmbientLight;
import renderengine.light.Light;
import renderengine.model.Model;
import renderengine.model.SkyBox;
import renderengine.shader.ForDiractionalShader;


public abstract class MainApplication {

	protected boolean run;
	public RenderEngine renderEngine;
	protected List<Model> models;
	protected List<Light> lights;
	protected Camera cam;
	protected AmbientLight ambientLight;
	protected SkyBox sky = null;
	protected List<Camera> cams;
	private int w,h;
	private Canvas c = null;
	private JFrame f = null;
	public GameComponent rootComponent;
	
	public MainApplication(){
		this(800,600); 
	}
	public MainApplication(int w,int h){
		this.w = w;
		this.h = h;
		models = new ArrayList<Model>();
		
		lights = new ArrayList<>();
		cams = new ArrayList<>();
		run = false;
	}
	
	public MainApplication(final Canvas c,final JFrame f){
		this.c = c;
		this.f = f;
		f.setResizable(false);
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				f.dispose();
				stop();
			}
			
		});
		
		this.w = c.getWidth();
		this.h = c.getHeight();
		models = new ArrayList<Model>();
		
		lights = new ArrayList<>();
		cams = new ArrayList<>();
		run = false;
	}
	
	private void init(){
		
		
		NativeLoader.loadNatives();
		if(c==null)
			Window.createWindow(w, h);
		else{
			f.setVisible(true);
			Window.createWindow(c);
		}
			
		GLUtil.initGL();
		AppHandler.mainApp = this;
		renderEngine = new RenderEngine();
		ambientLight = new AmbientLight();
		cam = new Camera(Window.getW(),Window.getH());
		
		AppHandler.diractionalShader = new ForDiractionalShader();
		
		
		rootComponent = new GameComponent() {
			
			@Override
			public void update(float tslf) {
				// TODO Auto-generated method stub
				
			}
		};
		initApp();
	}
	
	public void stop(){
		run = false;
	}
	
	public void start(){
		init();
		run = true;
		updateLoop();
	}
	float count = 0;
	private void updateLoop(){
		long lastFrame = System.currentTimeMillis();
		while(run){
			long thisFrame = System.currentTimeMillis();
			float tslf = (thisFrame-lastFrame)/1000f;
			lastFrame = thisFrame;
			if(Window.isWaitingForClose())
				run = false;
			
			update(tslf);
			Window.bindAsRenderTarget();
			if(sky!=null) renderEngine.render(cam,models,ambientLight,lights,sky);
			else renderEngine.render(cam,models,ambientLight,lights);
			Window.update(Window.getFPS());
			count += tslf;
			
			if(count >= 1.0f){
				count = 0;
				
				Window.setTitle("FPS: " + (int)(1/tslf));
			}
			renderengine.input.Mouse.reset();
		}
		cleanUP();
		if(f==null)
			Window.close();
		
	}
	
	private void update(float tslf) {
		if(Mouse.isCreated()){
			
			int dx = Mouse.getDX();
			int dy = Mouse.getDY();
			
			renderengine.input.Mouse.addDX(dx);
			renderengine.input.Mouse.addDY(dy);
			if(Mouse.isGrabbed()){
				Mouse.setCursorPosition(Display.getWidth()/2, Display.getHeight());
			}
			
		
		}
		Keyboard.update();
		for (Camera camera : cams) {
			camera.update(tslf);
		}
		Window.setUpdateCams(false);
		
		rootComponent.updateAll(tslf);
			
		updateApp(tslf);
	}

	private void cleanUP(){
		
		AppHandler.diractionalShader.deleteShader();
		
		
		

		
	
		
		
		
		renderEngine.cleanUp();
		for (int i = 0; i < models.size(); i++) {
			models.get(i).delete();
		}
		exit();
	}
	
	protected abstract void initApp();
	protected abstract void updateApp(float tslf);
	protected abstract void exit();
	
	//Getter & Setter
	
	public boolean isRun() {
		return run;
	}

	public RenderEngine getRenderEngine() {
		return renderEngine;
	}

	public void addModel(Model model) {
		this.models.add(model);
	}
	
	public void removeModel(Model model) {
		this.models.remove(model);
	}
	
	public void addLight(Light light){
		lights.add(light);
	}
	
	public void removeLight(Light light){
		lights.remove(light);
	}
	
	public void addCamera(Camera cam){
		cams.add(cam);
	}
	
	public void removeCamera(Camera cam){
		cams.remove(cam);
	}

	public Camera getCam() {
		return cam;
	}

	public void setCam(Camera cam) {
		this.cam = cam;
	}

	public AmbientLight getAmbientLight() {
		return ambientLight;
	}

	public void setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
	}

	public SkyBox getSky() {
		return sky;
	}

	public void setRun(boolean run) {
		this.run = run;
	}

	public void setModels(List<Model> models) {
		this.models = models;
	}

	public void setLights(List<Light> lights) {
		this.lights = lights;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public Canvas getC() {
		return c;
	}
	public void setC(Canvas c) {
		this.c = c;
	}
	public JFrame getF() {
		return f;
	}
	public void setF(JFrame f) {
		this.f = f;
	}
}
