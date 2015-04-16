package renderengine.efects;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import renderengine.core.AppHandler;
import renderengine.core.GLUtil;
import renderengine.core.Matrix;
import renderengine.core.VAO;
import renderengine.core.Window;
import renderengine.gui.GUIComponent;
import renderengine.texture.Texture;

public abstract class Efect {
	private static int quadList;
	
	
	private static VAO quad;
	private List<GUIComponent> children;
	private GUIComponent parent;
	private Matrix transMat;
	private float scaleX  = 1;
	private float scaleY = 1;
	private float x = 0,y = 0;
	private float rot = 0;
	
	private static float[] pos = new float[]{
		-1,1, -1,-1, 1,1, 1,-1
	};
	
	private static float[] uvs = new float[]{
		0,1 ,0,0,1,1, 1,0  
	};
	
	
	
	private static void genVAO(){
		quad = new VAO();
		quad.addData(pos, 0, 2);
		quad.addData(uvs, 1, 2);
	}
	
	public Efect(boolean finalRender) {
		if(quad == null){
			genVAO();
		}
		if(finalRender)
			AppHandler.mainApp.getRenderEngine().addEffect(this);
	}
	public void renderEfect(Texture target, Texture input){
		preRender(input);
		if(target == null){
			Window.bindAsRenderTarget();
			
		}else{
			target.bindAsRenderTarget();
			
		}
		GLUtil.setClearColor(0, 0, 0);
		GLUtil.clearScreen();
		
		if(input !=null)input.bind();
		quad.bind();
		GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);
		quad.unBind();
		if(input !=null)input.unbind();
		target.unbindFrambuffer();
		afterReder();
		
	}
	protected abstract void afterReder();
		
		
	

	protected abstract void preRender(Texture input );
		
}
