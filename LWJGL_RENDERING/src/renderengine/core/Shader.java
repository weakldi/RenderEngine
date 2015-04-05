package renderengine.core;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glBindAttribLocation;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glDeleteProgram;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL20.glUniform1f;
import static org.lwjgl.opengl.GL20.glUniform1i;
import static org.lwjgl.opengl.GL20.glUniform2f;
import static org.lwjgl.opengl.GL20.glUniform3f;
import static org.lwjgl.opengl.GL20.glUniform4f;
import static org.lwjgl.opengl.GL20.glUniformMatrix4;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL20.glValidateProgram;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import renderengine.res.ResLoader;

public abstract class Shader {
	private int shaderID = 0;
	private int vShaderID = 0;
	private int fShaderID = 0;
	
	public Shader(String vertexShader,String fragmentShader) {
		String fragShader = ResLoader.loadFile(fragmentShader);
		String vertShader = ResLoader.loadFile(vertexShader);
		
		shaderID = glCreateProgram();
		vShaderID = glCreateShader(GL_VERTEX_SHADER);
		fShaderID = glCreateShader(GL_FRAGMENT_SHADER);
		
		glShaderSource(fShaderID, fragShader);
		
		glCompileShader(fShaderID);
		if(glGetShaderi(fShaderID, GL_COMPILE_STATUS)==GL_FALSE){
			System.err.println(glGetShaderInfoLog(fShaderID, 500));
			System.exit(-1);
		}
		
		glShaderSource(vShaderID, vertShader);
		
		glCompileShader(vShaderID);
		if(glGetShaderi(vShaderID, GL_COMPILE_STATUS)==GL_FALSE){
			System.err.println(glGetShaderInfoLog(vShaderID, 500));
			System.exit(-1);
		}
		
		glAttachShader(shaderID, vShaderID);
		glAttachShader(shaderID, fShaderID);
		bindAttribs();
		glLinkProgram(shaderID);
		glValidateProgram(shaderID);
		getAllUniforms();
		
		
	}
	
	public void useShader(){
		glUseProgram(shaderID);
	}
	
	public void unbindShader(){
		glUseProgram(0);
	}
	
	protected abstract void bindAttribs();
	
	protected void bindAttrib(int index,String name){
		glBindAttribLocation(shaderID, index, name);
	}
	
	protected abstract void getAllUniforms(); 
	
	protected void loadUpFloat(int loc,float value){
		glUniform1f(loc, value);
	}
	protected void loadUpInt(int loc,int value){
		glUniform1i(loc, value);
	}
	protected void loadUpVec2(int loc , Vector2f value){
		glUniform2f(loc, value.x, value.y);
	}
	protected void loadUpVec3(int loc , Vector3f value){
		glUniform3f(loc, value.x, value.y,value.z);
	}
	protected void loadUpVec4(int loc , Vector4f value){
		glUniform4f(loc, value.x, value.y,value.z,value.w);
	}
	FloatBuffer matBuffer = BufferUtils.createFloatBuffer(16);
	protected void loadUpMat4(int loc,Matrix4f mat){
		mat.store(matBuffer);
		matBuffer.flip();
		glUniformMatrix4(loc, false, matBuffer);
	}
	protected void loadUpBooelan(int loc , boolean value){
		int b = value?1:0;
		glUniform1i(loc, b);
	}
	protected int getUniformLocation(String name){
		return glGetUniformLocation(shaderID, name);
	}
	
	public void deleteShader(){
		unbindShader();
		glDeleteShader(fShaderID);
		glDeleteShader(vShaderID);
		glDeleteProgram(shaderID);
		
	}
}
