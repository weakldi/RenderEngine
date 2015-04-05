package renderengine.gui;


import renderengine.input.Keyboard;
import renderengine.input.Mouse;
import renderengine.texture.Texture;

public class InputBox extends TextField {
	private boolean mouseWasPressed = false;
	private boolean selected = false;
	private String startText;
	private String inputText;
	private boolean backPressed = false;
	public InputBox(Texture texture, String text) {
		super(texture, text);
		textComponent.setOrientation(TextComponent.LEFT_ORIENTATION);
		startText = text;
		inputText = "";
		Keyboard.addKey(Keyboard.KEY_BACK);
	}
	
	public InputBox(Texture texture) {
		this(texture, "");
		
	}
	
	
	@Override
	public void update() {
		super.update();
		switch (textComponent.getOrientation()) {
		case TextComponent.LEFT_ORIENTATION:
			textComponent.setX(getTranslationSideRL());
			break;
		case TextComponent.RIGHT_ORIENTATION:
			textComponent.setX(-getTranslationSideRL());
			break;
		default:
			textComponent.setX(0);
			break;
		}
		if(selected){
			while(Keyboard.hasNextChar()){
				inputText+= Keyboard.nextChar()+"";
			}
			if(Keyboard.isKeyPressed(Keyboard.KEY_BACK)&&!backPressed){
				try{
					inputText = inputText.substring(0, inputText.length()-2);
				}catch (StringIndexOutOfBoundsException e){};
				
				backPressed = true;
				
			}
//			else if(Keyboard.isKeyPressed(Keyboard.KEY_BACK)&&backPressed){
//				try{
//					inputText = inputText.substring(0, inputText.length()-2);
//				}catch (StringIndexOutOfBoundsException e){};
//				
//			}
			else if(!Keyboard.isKeyPressed(Keyboard.KEY_BACK)&&backPressed){
			
				backPressed = false;
			}
			if(startText.equals("")){
				textComponent.setText(startText);
			}else{
				textComponent.setText(inputText);
			}
		}
		
		if(!Mouse.isHide()){
			float x = Mouse.getX();
			float y = Mouse.getY();
			
			float xMin = this.x-scaleX;
			float xMax = this.x+scaleX;
			
			if(x > xMin && x < xMax){
				float yMin = this.y-scaleY;
				float yMax = this.y+scaleY;
				if(y > yMin && y < yMax){
					
						
					if(!mouseWasPressed && Mouse.isPressed(Mouse.BUTTON_LEFT)){
						mouseWasPressed = true;
						
						return;
					}else if(mouseWasPressed && !Mouse.isPressed(Mouse.BUTTON_LEFT)){
						
						selected = true;
						mouseWasPressed = false;
						Keyboard.deleteBuffer();
						return;
					}
					
				}
			}
			if( Mouse.isPressed(Mouse.BUTTON_LEFT)){
				selected = false;
			}
			
			
			
		}
		
	}
	
	
}
