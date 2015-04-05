package renderengine.gui;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector4f;

import renderengine.core.AppHandler;
import renderengine.core.Color;
import renderengine.core.Matrix;
import renderengine.texture.Texture;

public class AreaComponent extends GUIComponent{
	private Matrix mat = new Matrix();
	private float scaleSideXUD;
	private float scaleSideYUD;
	private float scaleSideXLR;
	private float scaleSideYLR;
	private float scaleMiddelX;
	private float scaleMiddelY;
	private float translationEckeX;
	private float translationEckeY;
	private float translationSideRL;
	private float translationSideUD;
	public AreaComponent(Texture texture) {
		super();
		super.texture = texture;
	}
	
	public float getYOffset(int ID){
		int spalte = ID/2;
		return (float)spalte/2f;
	}
	public float getXOffset(int ID){
		int zeile = ID % 2;
		return (float)zeile/2f;
	}
	
	@Override
	public void render() {
		AppHandler.textShader.useShader();
		AppHandler.textShader.loadUpRows(2);
		texture.bind();
		quad.bind();
		float w = Display.getWidth()*scaleX;
		float h = Display.getHeight()*scaleY;
		float scaleRawX = w/10f;		
		float scaleEckeY = scaleRawX/h;
		
		float scaleEckeX = scaleRawX/w;
		
		scaleSideXUD = 1-scaleEckeX*2;
		scaleSideYUD = scaleEckeY;
		scaleSideXLR = scaleEckeX;
		scaleSideYLR = 1-scaleEckeY*2;
		scaleMiddelX = scaleSideXUD;
		scaleMiddelY = scaleSideYLR;
		Vector4f pos = new Vector4f(0, 0, 0, 1);
		Matrix4f.transform(getTransMat().getMatGL(), pos, pos);
		translationEckeX = (0-scaleSideXUD-scaleEckeX);
		translationEckeY = (0-scaleSideYLR-scaleEckeY);
		translationSideRL = (0-scaleSideXUD-scaleEckeX);
		translationSideUD = (0-scaleSideYLR-scaleEckeY);
		
		//Middel
		mat.setIdentity();
		mat.translate(pos.x, pos.y, 0);
		mat.rotate(0, 0, rot);
		mat.translate(0, 0, 0);
		mat.scale(scaleMiddelX*scaleX, scaleMiddelY*scaleY, 0);
		
		AppHandler.textShader.loadModelMat(mat);
		AppHandler.textShader.loadUpColor(1f,1f,1f);
		AppHandler.textShader.loadOffset(getXOffset(3),getYOffset(3));
		GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);
		//Ecke unten Links
		mat.setIdentity();
		mat.translate(pos.x, pos.y, 0);
		mat.rotate(0, 0, rot);
		mat.translate(translationEckeX*scaleX, translationEckeY*scaleY, 0);
		mat.scale(scaleEckeX*scaleX,scaleEckeY*scaleY , 0);
		
		AppHandler.textShader.loadModelMat(mat);
		AppHandler.textShader.loadUpColor(1f,1f,1f);
		AppHandler.textShader.loadOffset(getXOffset(0),getYOffset(0));
		GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);
		//Ecke unten Rechts
		mat.setIdentity();
		mat.translate(pos.x, pos.y, 0);
		mat.rotate(0, 0, rot);
		mat.translate(-translationEckeX*scaleX, translationEckeY*scaleY, 0);
		mat.scale(scaleEckeX*scaleX,scaleEckeY*scaleY , 0);
		mat.rotate(0, 0, 90);
		
		AppHandler.textShader.loadModelMat(mat);
		AppHandler.textShader.loadUpColor(1f,1f,1f);
		AppHandler.textShader.loadOffset(getXOffset(0),getYOffset(0));
		GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);
		
		//Ecke oben Links
		mat.setIdentity();
		mat.translate(pos.x, pos.y, 0);
		mat.rotate(0, 0, rot);
		mat.translate(translationEckeX*scaleX, -translationEckeY*scaleY, 0);
		mat.scale(scaleEckeX*scaleX,scaleEckeY*scaleY , 0);
		mat.rotate(0, 0, -90);
		
		AppHandler.textShader.loadModelMat(mat);
		
		AppHandler.textShader.loadUpColor(1f,1f,1f);
		AppHandler.textShader.loadOffset(getXOffset(0),getYOffset(0));
		GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);
		
		//Ecke oben Rechts
		mat.setIdentity();
		mat.translate(pos.x, pos.y, 0);
		mat.rotate(0, 0, rot);
		mat.translate(-translationEckeX*scaleX, -translationEckeY*scaleY, 0);
		mat.rotate(0, 0, 180);
		mat.scale(scaleEckeX*scaleX,scaleEckeY*scaleY , 0);
		
		AppHandler.textShader.loadModelMat(mat);
		AppHandler.textShader.loadUpColor(1f,1f,1f);
		AppHandler.textShader.loadOffset(getXOffset(0),getYOffset(0));
		GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);
		
		//Seite Rechts
		mat.setIdentity();
		mat.translate(pos.x, pos.y, 0);
		mat.rotate(0, 0, rot);
		mat.translate(translationSideRL*scaleX, 0, 0);
		mat.scale(scaleSideXLR*scaleX,scaleSideYLR*scaleY , 0);
		
		AppHandler.textShader.loadModelMat(mat);
		AppHandler.textShader.loadUpColor(1f,1f,1f);
		AppHandler.textShader.loadOffset(getXOffset(2),getYOffset(2));
		GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);
		
		//Seite Links
		mat.setIdentity();
		mat.translate(pos.x, pos.y, 0);
		mat.rotate(0, 0, rot);
		mat.translate(-translationSideRL*scaleX, 0, 0);
		mat.rotate(0, 0, 180);
		mat.scale(scaleSideXLR*scaleX,scaleSideYLR*scaleY , 0);
		
		AppHandler.textShader.loadModelMat(mat);
		AppHandler.textShader.loadUpColor(1f,1f,1f);
		AppHandler.textShader.loadOffset(getXOffset(2),getYOffset(2));
		GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);
		
		//Seite Oben
		mat.setIdentity();
		mat.translate(pos.x, pos.y, 0);
		mat.rotate(0, 0, rot);
		mat.translate(0, -translationSideUD*scaleY, 0);
		mat.rotate(0, 0, 180);
		mat.scale(scaleSideXUD*scaleX,scaleSideYUD*scaleY , 0);
		
		AppHandler.textShader.loadModelMat(mat);
		AppHandler.textShader.loadUpColor(1f,1f,1f);
		AppHandler.textShader.loadOffset(getXOffset(1),getYOffset(1));
		GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);
		
		//Seite unten
		mat.setIdentity();
		mat.translate(pos.x, pos.y, 0);
		mat.rotate(0, 0, rot);
		mat.translate(0, translationSideUD*scaleY, 0);
		mat.scale(scaleSideXUD*scaleX,scaleSideYUD*scaleY , 0);
		
		AppHandler.textShader.loadModelMat(mat);
		AppHandler.textShader.loadUpColor(1f,1f,1f);
		AppHandler.textShader.loadOffset(getXOffset(1),getYOffset(1));
		GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, 4);
		quad.unBind();
		texture.unbind();
	}

	public float getScaleSideXUD() {
		return scaleSideXUD;
	}

	public float getScaleSideYUD() {
		return scaleSideYUD;
	}

	public float getScaleSideXLR() {
		return scaleSideXLR;
	}

	public float getScaleSideYLR() {
		return scaleSideYLR;
	}

	public float getScaleMiddelX() {
		return scaleMiddelX;
	}

	public float getScaleMiddelY() {
		return scaleMiddelY;
	}

	public float getTranslationEckeX() {
		return translationEckeX;
	}

	public float getTranslationEckeY() {
		return translationEckeY;
	}

	public float getTranslationSideRL() {
		return translationSideRL;
	}

	public float getTranslationSideUD() {
		return translationSideUD;
	}
}
