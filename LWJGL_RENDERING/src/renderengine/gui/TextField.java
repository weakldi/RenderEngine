package renderengine.gui;

import renderengine.core.Color;
import renderengine.texture.Texture;

public class TextField extends AreaComponent{
	protected String text;
	protected TextComponent textComponent;
	public TextField(Texture texture,String text) {
		super(texture);
		this.text = text;
		textComponent = new TextComponent(new Font(), text,1);
		addChild(textComponent);
	}
	
	@Override
	public void update() {
		textComponent.scaleX = 1/scaleX;
		textComponent.scaleY = 1/scaleY;
		
	}
	
	public void setTextSize(float size){
		textComponent.setSize(size);
	}
	
	public void setText(String text){
		textComponent.setText(text);
	}
	
	public void setColor(Color color){
		textComponent.setTextColor(color);
	}
	
	public void setFont(Font f){
		textComponent.setFont(f);
	}
	
	
	
}
