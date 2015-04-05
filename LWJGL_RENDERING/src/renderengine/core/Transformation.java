package renderengine.core;

public class Transformation {
	private Matrix transmat;
	private float rX,rY,rZ;
	private float x,y,z;
	private float sX = 1,sY = 1, sZ = 1;
	private float rpX,rpY,rpZ;
	private float pX, pY, pZ;
	
	
	public Transformation(float x, float y, float z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		transmat = new Matrix();
	}



	public Transformation(float x, float y, float z,float rX, float rY, float rZ
			) {
		super();
		
		this.rX = rX;
		this.rY = rY;
		this.rZ = rZ;
		this.x = x;
		this.y = y;
		this.z = z;
		transmat = new Matrix();
	}



	public Transformation(float x, float y, float z,float rX, float rY, float rZ,
			 float sX, float sY, float sZ) {
		super();
		
		this.rX = rX;
		this.rY = rY;
		this.rZ = rZ;
		this.x = x;
		this.y = y;
		this.z = z;
		this.sX = sX;
		this.sY = sY;
		this.sZ = sZ;
		transmat = new Matrix();
	}



	public Matrix getTransmat(){
		transmat.initModelMatrix(x, y, z, rX, rY, rZ, sX, sY, sZ,pX,pY,pZ,rpX,rpY,rpZ);
		return transmat;
	}

	public void setTranslation(float x,float y,float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void translate(float x,float y,float z){
		this.x+=x;
		this.y+=y;
		this.z+=z;
	}
	
	public void setRotation(float rX,float rY,float rZ){
		this.rX = rX;
		this.rY = rY;
		this.rZ = rZ;
	}
	
	public void rotate(float rX,float rY,float rZ){
		this.rX+=rX;
		this.rY+=rY;
		this.rZ+=rZ;
	}
	
	public void setRotation(float x,float y,float z,float rX,float rY,float rZ){
		this.pX = x;
		this.pY = y;
		this.pZ = z;
		this.rpX = rX;
		this.rpY = rY;
		this.rpZ = rZ;
	}
	
	public void rotate(float x,float y,float z,float rX,float rY,float rZ){
		this.pX+=x;
		this.pY+=y;
		this.pZ+=z;
		this.rpX+=rX;
		this.rpY+=rY;
		this.rpZ+=rZ;
	}
	
	public void setScale(float sX,float sY,float sZ){
		this.sX = sX;
		this.sY = sY;
		this.sZ = sZ;
	}
	
	public void scale(float sX,float sY,float sZ){
		this.sX+=sX;
		this.sY+=sY;
		this.sZ+=sZ;
	}

	public float getrX() {
		return rX;
	}



	public void setrX(float rX) {
		this.rX = rX;
	}



	public float getrY() {
		return rY;
	}



	public void setrY(float rY) {
		this.rY = rY;
	}



	public float getrZ() {
		return rZ;
	}



	public void setrZ(float rZ) {
		this.rZ = rZ;
	}



	public float getX() {
		return x;
	}



	public void setX(float x) {
		this.x = x;
	}



	public float getY() {
		return y;
	}



	public void setY(float y) {
		this.y = y;
	}



	public float getZ() {
		return z;
	}



	public void setZ(float z) {
		this.z = z;
	}



	public float getsX() {
		return sX;
	}



	public void setsX(float sX) {
		this.sX = sX;
	}



	public float getsY() {
		return sY;
	}



	public void setsY(float sY) {
		this.sY = sY;
	}



	public float getsZ() {
		return sZ;
	}



	public void setsZ(float sZ) {
		this.sZ = sZ;
	}



	public float getRpX() {
		return rpX;
	}



	public void setRpX(float rpX) {
		this.rpX = rpX;
	}



	public float getRpY() {
		return rpY;
	}



	public void setRpY(float rpY) {
		this.rpY = rpY;
	}



	public float getRpZ() {
		return rpZ;
	}



	public void setRpZ(float rpZ) {
		this.rpZ = rpZ;
	}



	public float getpX() {
		return pX;
	}



	public void setpX(float pX) {
		this.pX = pX;
	}



	public float getpY() {
		return pY;
	}



	public void setpY(float pY) {
		this.pY = pY;
	}



	public float getpZ() {
		return pZ;
	}



	public void setpZ(float pZ) {
		this.pZ = pZ;
	}
}
