package renderengine.texture;

import java.nio.ByteBuffer;
public class TextureData {
	private int width;
	private int height;
	private ByteBuffer data;
	private int textureID;
	public TextureData(int width, int height, ByteBuffer data) {
		super();
		this.width = width;
		this.height = height;
		this.data = data;
	}
	
	
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public ByteBuffer getData() {
		return data;
	}
	

	
	
}
