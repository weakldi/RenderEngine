package renderengine.gui;

import java.util.ArrayList;
import java.util.List;

import renderengine.input.Mouse;
import renderengine.texture.Texture;

public class Button extends TextField{
	private Texture[] states;
	private int state = 0;
	private boolean mouseWasPressed = false;
	private List<ButtonListener> listeners;
	private String inputText = "";
	private String startText;
	public Button(Texture[] texture, String text) {
		super(texture[0], text);
		states = texture;
		listeners = new ArrayList<ButtonListener>();
		startText = text;
	}
	
	@Override
	public void render() {
		super.render();
		setTexture(states[state]);
	}
	
	@Override
	public void update() {
		if(!mouseWasPressed)
			state = 0;
		if(!Mouse.isHide()){
			float x = Mouse.getX();
			float y = Mouse.getY();
			
			float xMin = this.x-scaleX;
			float xMax = this.x+scaleX;
			
			if(x > xMin && x < xMax){
				float yMin = this.y-scaleY;
				float yMax = this.y+scaleY;
				if(y > yMin && y < yMax){
					if(!mouseWasPressed)
						state = 1;
					if(!mouseWasPressed && Mouse.isPressed(Mouse.BUTTON_LEFT)){
						mouseWasPressed = true;
						state = 2;
						for (ButtonListener buttonListener : listeners) {
							buttonListener.onPress();
						}
						return;
					}else if(mouseWasPressed && !Mouse.isPressed(Mouse.BUTTON_LEFT)){
						state = 0;
						System.out.println("klick!");
						mouseWasPressed = false;
						for (ButtonListener buttonListener : listeners) {
							buttonListener.onClick();
						}
						return;
					}
					for (ButtonListener buttonListener : listeners) {
						buttonListener.onMouseInArea();
					}
				}
			}
			
		}
	}

	public Texture[] getStates() {
		return states;
	}

	public void setStates(Texture[] states) {
		this.states = states;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	public void addListener(ButtonListener listener){
		this.listeners.add(listener);
	}
	
	public void removeListener(ButtonListener listener){
		this.listeners.remove(listener);
	}
}
