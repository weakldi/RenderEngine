package renderengine.core;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public class Matrix {
	private Matrix4f mat;
	public Matrix() {
		mat = new Matrix4f();
		setIdentity();
	}
	
	public void setIdentity(){
		mat.setIdentity();
		
	}
	
	public void setProjection(float fov,float near,float far,float aspectRatio){
		
			Matrix4f projectionMatrix = new Matrix4f();
			
			float y_scale = (float) ((1f / Math.tan(Math.toRadians(fov / 2f))) * aspectRatio);
			float x_scale = y_scale / aspectRatio;
			float frustum_length = far - near;
			
			projectionMatrix.m00 = x_scale;
			projectionMatrix.m11 = y_scale;
			projectionMatrix.m22 = -((far + near) / frustum_length);
			projectionMatrix.m23 = -1;
			projectionMatrix.m32 = -((2 * near * far) / frustum_length);
			projectionMatrix.m33 = 0;
			mat = new Matrix4f(projectionMatrix);
		
		System.out.println(aspectRatio + " " + x_scale);
	}
	
	public void initModelMatrix(float translateX, float translateY, float translateZ, float rotX, float rotY, float rotZ,
			float scaleX, float scaleY, float scaleZ,float rpX,float rpY,float rpZ,float rotPX,float rotPY,float rotPZ){

		mat.setIdentity();
		rotate(rpX, rpY, rpZ, rotPX, rotPY, rotPZ);
		translate(translateX, translateY, translateZ);
		rotate(rotX, rotY, rotZ);
		scale(scaleX, scaleY, scaleZ);

	}
	
	public void initModelMatrix(float translateX, float translateY, float translateZ, float rotX, float rotY, float rotZ, float scaleX, float scaleY, float scaleZ){

		mat.setIdentity();
		translate(translateX, translateY, translateZ);
		rotate(rotX, rotY, rotZ);
		scale(scaleX, scaleY, scaleZ);

		
	}
	
	public void rotate(float x,float y,float z){
		Matrix4f.rotate((float) java.lang.Math.toRadians(x), new Vector3f(1, 0, 0),mat,mat);
		Matrix4f.rotate((float) java.lang.Math.toRadians(y), new Vector3f(0, 1, 0),mat,mat);
		Matrix4f.rotate((float) java.lang.Math.toRadians(z), new Vector3f(0, 0, 1),mat,mat);
	}
	
	public void rotate(float x,float y, float z,float rx,float ry,float rz){
		translate(x, y, z);
		rotate(rx, ry, rz);
	}
	
	public void translate(float x,float y,float z){
		mat.translate(new Vector3f(x, y, z));
		
	}
	
	public void scale(float x,float y,float z){
		mat.scale(new Vector3f(x, y, z));
	}
	
	public void mul(Matrix matrix){
		Matrix4f.mul(mat, matrix.getMatGL(), mat);
	}
	
	public Matrix4f getMatGL(){
		return mat;
	}
	
	public FloatBuffer getMatFloatBuffer(){
		FloatBuffer mat = BufferUtils.createFloatBuffer(16);
		this.mat.store(mat);
		mat.flip();
		
		return mat;
	}
	
	@Override
	public String toString() {
		String text = super.toString() + "\n";
		text += mat.toString();
		return text;
	}
	
	
}
