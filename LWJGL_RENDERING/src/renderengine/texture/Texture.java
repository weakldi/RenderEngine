package renderengine.texture;

import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL14.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL31.*;
import static org.lwjgl.opengl.GL32.*;
import static org.lwjgl.opengl.GL14.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;

import java.nio.ByteBuffer;

import renderengine.core.GLUtil;
import renderengine.core.Window;
public class Texture {
	public static int FILTER_LINEAR = GL_LINEAR;
	public static int FILTER_NEAREST = GL_NEAREST;
	public static int FILTER_MIPMAP_LINEAR_LINEAR = GL_LINEAR_MIPMAP_LINEAR;
	public static int FILTER_MIPMAP_LINEAR_NEAREST = GL_LINEAR_MIPMAP_NEAREST;
	public static int FILTER_MIPMAP_NEAREST_LINEAR = GL_NEAREST_MIPMAP_LINEAR;
	public static int FILTER_MIPMAP_NEAREST_NEAREST = GL_NEAREST_MIPMAP_NEAREST;

	private int ID = 0;
	private TextureData textureData;
	private int textureUnit = 0;
	private TextureType type;
	private int framebuffer = 0;
	private int renderBuffer = 0;
	
	public Texture(String file){
		this(TextureLoader.decodeTexture(file), FILTER_MIPMAP_LINEAR_LINEAR, false);
	}
	
	public Texture(String file,int filter,boolean clamp){
		this(TextureLoader.decodeTexture(file), filter, clamp);
	}
	
	public Texture(int w,int h,int attachment,int filter,boolean clamp){
		this.textureData = new TextureData(w, h, null);
		initTexture(clamp, filter, GL_RGBA32F, GL_RGBA, GL_UNSIGNED_BYTE);
		initFrameBuffer(attachment);
	}
	
	public Texture(TextureData textureData,int filter,boolean clamp){
		this.textureData = textureData;
		initTexture(clamp, filter,GL_RGBA,GL_RGBA,GL_UNSIGNED_BYTE);
	}
	public Texture(TextureData textureData,int filter,boolean clamp,int type){
		this.textureData = textureData;
		initTexture(clamp, filter,GL_RGBA,GL_RGBA,type);
	}
	
	
	
	private void initTexture(boolean clamp,int filter,int internalFormat,int format,int type){
		genTextureID();
		bind();
		
		if(clamp){
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
		}else{
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
		}
		
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, filter);
		glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, filter);
		
		glTexImage2D(GL_TEXTURE_2D, 0, internalFormat, textureData.getWidth(), textureData.getHeight(), 0,
				format, type, textureData.getData());
		
		if(filter == FILTER_MIPMAP_LINEAR_LINEAR ||
				filter == FILTER_MIPMAP_LINEAR_NEAREST ||
				filter == FILTER_MIPMAP_NEAREST_LINEAR ||
				filter == FILTER_MIPMAP_NEAREST_NEAREST ){
			glGenerateMipmap(GL_TEXTURE_2D);
		}
		this.type = TextureType.TEXTURE_2D;
	}
	
	public void initFrameBuffer(int attachment){
		framebuffer = glGenFramebuffers();
		glBindFramebuffer(GL_FRAMEBUFFER, framebuffer);
		bind();
		glFramebufferTexture2D(GL_FRAMEBUFFER, attachment, GL_TEXTURE_2D, ID, 0);
		if(attachment == GL_DEPTH_ATTACHMENT){
			glReadBuffer(GL_NONE);
			glDrawBuffer(GL_NONE);
		}else{
			renderBuffer = glGenRenderbuffers();
			glBindRenderbuffer(GL_RENDERBUFFER, renderBuffer);
			glRenderbufferStorage(GL_RENDERBUFFER, GL_DEPTH_COMPONENT, textureData.getWidth(), textureData.getHeight());
			glFramebufferRenderbuffer(GL_FRAMEBUFFER, GL_DEPTH_ATTACHMENT, GL_RENDERBUFFER, renderBuffer);
		}
		
		if(glCheckFramebufferStatus(GL_FRAMEBUFFER) == GL_FRAMEBUFFER_COMPLETE){
	         System.out.println("Frame buffer created sucessfully.");
	      }
	    else
	         System.out.println("An error occured creating the frame buffer.");
		glBindFramebuffer(GL_FRAMEBUFFER, GL_NONE);
	}
	
	
	public void bindAsRenderTarget(){
		glBindFramebuffer(GL_FRAMEBUFFER, framebuffer);
		glViewport(0, 0, textureData.getWidth(), textureData.getHeight());
		GLUtil.clearScreen();
	}
	
	public void unbindFrambuffer(){
		Window.bindAsRenderTarget();
	}
	
	private void genTextureID(){
		ID = glGenTextures();
	}
	
	public void bind(int unit){
		textureUnit = unit;
		glActiveTexture(GL_TEXTURE0+unit);
		glBindTexture(GL_TEXTURE_2D, ID);
	}
	
	public void bind(){
		bind(0);
	}
	
	public void unbind(){
		glActiveTexture(GL_TEXTURE0+textureUnit);
		glBindTexture(GL_TEXTURE_2D, GL_NONE);
		textureUnit = 0;
	}

	public int getID() {
		return ID;
	}

	public int getWidth() {
		return textureData.getWidth();
	}

	public int getHeight() {
		return textureData.getHeight();
	}

	public ByteBuffer getData() {
		return textureData.getData();
	}

	public int getTextureUnit() {
		return textureUnit;
	}

	public TextureData getTextureData() {
		return textureData;
	}

	public TextureType getType() {
		return type;
	}
	
	public void delete(){
		try {
			finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}
	
}
