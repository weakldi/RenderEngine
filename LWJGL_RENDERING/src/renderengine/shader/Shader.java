package renderengine.shader;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import renderengine.core.Matrix;
import renderengine.res.ResLoader;

public class Shader {
	private final int programID;
	private int[] shaderIDs;
	private HashMap<String, Integer> uniforms;
	private List<String> uniformList = new ArrayList<>();
	public Shader(String vertexShaderFile,String fragmentShaderFile){
		System.out.println("Loading new Shader...");
		uniforms = new HashMap<>();
		programID = generateProgram();
		shaderIDs = new int[2];
		System.out.println("ID= " + programID);
		System.out.println("Loading vertexshader ( "+vertexShaderFile+" ) ... ");
		shaderIDs[0] = addVertexShader(ResLoader.loadFile(vertexShaderFile));
		System.out.println("Done!");
		System.out.println("Loading fragmentShader ( "+fragmentShaderFile+" ) ... ");
		shaderIDs[1] = addFragmentShader(ResLoader.loadFile(fragmentShaderFile));
		System.out.println("Done!");
		
		for (int id : shaderIDs) 
			glAttachShader(programID, id);
		
		glLinkProgram(programID);
		glValidateProgram(programID);
		for (String uniform : uniformList) {
			int uniforID = glGetUniformLocation(programID, uniform);
			uniforms.put(uniform, uniforID);
		}
		
		System.out.println("Shader loaded!");
		
	}
	
	public Shader(String vertexShaderFile,String fragmentShaderFile,String geometrieShaderFile){
		System.out.println("Loading new Shader...");
		uniforms = new HashMap<>();
		programID = generateProgram();
		shaderIDs = new int[3];
		System.out.println("Loading vertexshader ( "+vertexShaderFile+" ) ... \n");
		shaderIDs[0] = addVertexShader(ResLoader.loadFile(vertexShaderFile));
		System.out.println("Done!");
		System.out.println("Loading vertexshader ( "+vertexShaderFile+" ) ... ");
		shaderIDs[1] = addFragmentShader(ResLoader.loadFile(fragmentShaderFile));
		System.out.println("Done!");
		shaderIDs[2] = addGeometrieShader(ResLoader.loadFile(geometrieShaderFile));
		for (int id : shaderIDs) 
			glAttachShader(programID, id);
		
		glLinkProgram(programID);
		glValidateProgram(programID);
		for (String uniform : uniformList) {
			int uniforID = glGetUniformLocation(programID, uniform);
			uniforms.put(uniform, uniforID);
		}
		
		System.out.println("Shader loaded!");
	}
	
	public void bind(){
		glUseProgram(programID);
	}
	
	public void unbind(){
		glUseProgram(0);
	}
	
	public void deleteShader(){
		for (int id : shaderIDs) {
			glDeleteShader(id);
		}
		glDeleteProgram(programID);
		System.out.println("Shader was deleted! (ID=" + programID+")");
	}
	
	public int getuniformID(String uniformName){
		
		return uniforms.get(uniformName);
	}
	public void loadUpFloat(int loc,float value){
		glUniform1f(loc, value);
	}
	public void loadUpInt(int loc,int value){
		glUniform1i(loc, value);
	}
	public void loadUpVec2(int loc , Vector2f value){
		glUniform2f(loc, value.x, value.y);
	}
	public void loadUpVec3(int loc , Vector3f value){
		glUniform3f(loc, value.x, value.y,value.z);
	}
	public void loadUpVec4(int loc , Vector4f value){
		glUniform4f(loc, value.x, value.y,value.z,value.w);
	}
	FloatBuffer matBuffer = BufferUtils.createFloatBuffer(16);
	public void loadUpMat4(int loc,Matrix mat){
		mat.getMatGL().store(matBuffer);
		matBuffer.flip();
		glUniformMatrix4(loc, false, matBuffer);
	}
	public void loadUpBooelan(int loc , boolean value){
		int b = value?1:0;
		glUniform1i(loc, b);
	}
	
	public void loadUpFloat(String uniform,float value){
		glUniform1f(getuniformID(uniform), value);
	}
	public void loadUpInt(String uniform,int value){
		glUniform1i(getuniformID(uniform), value);
	}
	public void loadUpVec2(String uniform , Vector2f value){
		glUniform2f(getuniformID(uniform), value.x, value.y);
	}
	public void loadUpVec3(String uniform , Vector3f value){
		glUniform3f(getuniformID(uniform), value.x, value.y,value.z);
	}
	public void loadUpVec4(String uniform , Vector4f value){
		glUniform4f(getuniformID(uniform), value.x, value.y,value.z,value.w);
	}
	
	public void loadUpMat4(String uniform,Matrix mat){
		mat.getMatGL().store(matBuffer);
		matBuffer.flip();
		glUniformMatrix4(getuniformID(uniform), false, matBuffer);
	}
	public void loadUpBooelan(String uniform , boolean value){
		int b = value?1:0;
		glUniform1i(getuniformID(uniform), b);
	}
	
	
	private int addVertexShader(String shaderCode){
		int shaderID = glCreateShader(GL_VERTEX_SHADER);
		glShaderSource(shaderID, shaderCode);
		glCompileShader(shaderID);
		if(glGetShaderi(shaderID, GL_COMPILE_STATUS)==GL_FALSE){
			System.err.println(glGetShaderInfoLog(shaderID, 500));
			System.exit(-1);
		}
		getUniforms(shaderCode);
		return shaderID;
	}
	
	private int addFragmentShader(String shaderCode){
		int shaderID = glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(shaderID, shaderCode);
		glCompileShader(shaderID);
		if(glGetShaderi(shaderID, GL_COMPILE_STATUS)==GL_FALSE){
			System.err.println(glGetShaderInfoLog(shaderID, 500));
			System.exit(-1);
		}
		getUniforms(shaderCode);
		return shaderID;
	}

	private int addGeometrieShader(String shaderCode){
		System.exit(-1);
		return 0;
	}
	
	private int generateProgram(){
		return glCreateProgram();
	}
	
	private void getUniforms(String code){
		code = code.replace("\n","");
		String[] lines = code.split(";");
		for (String line : lines) {
			
			if(line.startsWith("uniform")){
				String[] pieces = line.split(" ");
				String uniform = pieces[pieces.length-1];
				uniformList.add(uniform);
				System.out.println("new Uniform: " + uniform);
				
			}
		}
	}
}
