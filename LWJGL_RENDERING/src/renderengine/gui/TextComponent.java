package renderengine.gui;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import renderengine.core.AppHandler;
import renderengine.core.Color;
import renderengine.core.Matrix;
import renderengine.core.VAO;
import renderengine.texture.Texture;

public class TextComponent extends GUIComponent{
	private Font font;
	private String text;
	private float size = 1;
	private int orientation;
	private Color textColor;
	public static final int LEFT_ORIENTATION = 0;
	public static final int MIDDEL_ORIENTATION = 1;
	public static final int RIGHT_ORIENTATION = 2;
	
	public TextComponent(Font font,String text) {
		this.font = font;
		this.text = text;
		this.orientation = MIDDEL_ORIENTATION;
		textColor = new Color(255, 255, 255);
	}
	
	public TextComponent(Font font,String text,int orientation) {
		this.font = font;
		this.text = text;
		this.orientation = orientation;
		textColor = new Color(255, 255, 255);
	}
	
	
	@Override
	public void render() {
		String[] textLines = text.split("\n");
		
		float w = 0.0233f*size, h =  0.03f*size;
		float hAll = h * textLines.length;
		
		for (int i = 0; i < textLines.length; i++) {
			float tempH = hAll/2-h/2*(i+1)-0.03f*size*i;

			
			switch (orientation) {
			case LEFT_ORIENTATION:
				font.drawTextL(textLines[i],0, tempH, w ,h, getTransMat(),textColor);
				break;
			case MIDDEL_ORIENTATION:
				font.drawTextM(textLines[i],0, tempH, w ,h, getTransMat(),textColor);
				break;
			case RIGHT_ORIENTATION:
				font.drawTextR(textLines[i],0, tempH, w ,h, getTransMat(),textColor);
				break;
			default:
				break;
			}
			
		}
				
	}


	public Font getFont() {
		return font;
	}


	public void setFont(Font font) {
		this.font = font;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}
	
	public void addText(String text){
		this.text += text;
	}


	public float getSize() {
		return size;
	}
	
	

	public void setSize(float size) {
		this.size = size;
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}
}
