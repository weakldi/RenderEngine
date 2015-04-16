package renderengine.core;

public class Matrix4f {
	private float [] [] matrix;
	
	public Matrix4f() {
		matrix = new float[4][4];
	}
	
	public Matrix4f setIdentity(){
		matrix[0][0] = 1;
		matrix[1][1] = 1;
		matrix[2][2] = 1;
		matrix[3][3] = 1;
		return this;
	}
	
	public Matrix4f translate(float x,float y,float z){
		matrix[3][0] += x;
		matrix[3][1] += y;
		matrix[3][2] += z;
		return this;
	}
	
	public Matrix4f scale(float x,float y,float z){
		matrix[0][0] *= x; 
		matrix[1][1] *= y;
		matrix[2][2] *= z;
		return this;
	}
	
	public void rotate(float angel,float x,float y,float z){
		matrix[0][0] = 0;
		matrix[0][0] = 0;
	}
	
	public Matrix4f mul(Matrix4f mat){
		for(int x = 0; x < 4 ; x++){
			for(int y = 0; y < 4; y++){
				matrix[x][y] = 	matrix[x][0] * mat.get(0, y) + matrix[x][1] * mat.get(1, y) + matrix[x][2] * mat.get(2, y) + matrix[x][3] * mat.get(3, y);
			}
		}
		return this;
	}
	
	public void mul(Vector3f vec){
		
	}
	
	public Matrix4f mul(float value){
		for (int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++){
				matrix[i][j]*=value;
			}
		}
		return this;
	}
	
	public Matrix4f add(Matrix4f mat){
		for (int i = 0; i < matrix.length; i++) {
			for(int j = 0; i < matrix[i].length; j++){
				matrix[i][j]+=mat.get(i, j);
			}
		}
		return this;
	}
	
	public Matrix4f sub(Matrix4f mat){
		for (int i = 0; i < matrix.length; i++) {
			for(int j = 0; i < matrix[i].length; j++){
				matrix[i][j]-=mat.get(i, j);
			}
		}
		return this;
	}
	
	public Matrix4f set(float value, int x,int y){
		matrix[x][y] = value;
		return this;
	}
	
	public float get(int x,int y){
		return matrix[x][y];
	}
	
	@Override
	public String toString() {
		String string = super.toString() +"\n";
		string+="[ " + matrix[0][0] + ", " + matrix[1][0] + ", " + matrix[2][0] + ", " + matrix[3][0] + "]\n";
		string+="[ " + matrix[0][1] + ", " + matrix[1][1] + ", " + matrix[2][1] + ", " + matrix[3][1] + "]\n";
		string+="[ " + matrix[0][2] + ", " + matrix[1][2] + ", " + matrix[2][2] + ", " + matrix[3][2] + "]\n";
		string+="[ " + matrix[0][3] + ", " + matrix[1][3] + ", " + matrix[2][3] + ", " + matrix[3][3] + "]\n";
		return string;
	}
}
