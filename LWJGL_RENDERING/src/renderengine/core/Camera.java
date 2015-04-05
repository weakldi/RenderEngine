package renderengine.core;


import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private float w,h;
	protected float x,y,z;
	protected float rX,rY,rZ;
	Matrix view ;
	private boolean lookAt = false;
	private Matrix projectionMatrix;
	private boolean usedForWindow;
	public Camera(float w,float h,boolean usedForWindow) {
		this.w = w;
		this.h = h;
		reCalcProjection();
		view = new Matrix();
		this.usedForWindow = usedForWindow;
		AppHandler.mainApp.addCamera(this);
	}
	
	public Camera(float w,float h){
		this(w,h,true);
	}
	
	private void reCalcProjection(){
		float aspect = w/h;
		projectionMatrix = new Matrix();
		projectionMatrix.setProjection(45, 0.1f, 1000, aspect);
		
		x = 0;
		y = 0;
		z = 0;
		rX = 0;
		rY = 0;
		rZ = 0;
	}
	
	public void update(float tslf){
		if(Window.isUpdateCams()){
			if(usedForWindow){
				w = Window.getW();
				h = Window.getH();
				reCalcProjection();
			}
		}
		lookAt = false;
	}
	
	public void lookAt(final Vector3f eye, final Vector3f center, final Vector3f up)
    {
		
        final Vector3f f = (Vector3f.sub(center, eye, null)).normalise(null);
        final Vector3f upn = up.normalise(null); // <--
        final Vector3f s = (Vector3f.cross(f, upn, null)).normalise(null); // <--
        final Vector3f u = Vector3f.cross(s, f, null); // <--
 
     
        view.getMatGL() .setZero();
 
        view.getMatGL() .m00 = s.x;
        view.getMatGL() .m10 = s.y;
        view.getMatGL() .m20 = s.z;
 
        view.getMatGL() .m01 = u.x;
        view.getMatGL() .m11 = u.y;
        view.getMatGL() .m21 = u.z;
 
        view.getMatGL() .m02 = -f.x;
        view.getMatGL() .m12 = -f.y;
        view.getMatGL() .m22 = -f.z;
 
        view.getMatGL() .m33 = 1; // <--
 
        view.getMatGL() .translate(new Vector3f(-eye.x, -eye.y, -eye.z));
       
        lookAt = true;
    }
	public void updateViewmat(){
		if(!lookAt){
			
			view.setIdentity();
			
			view.rotate(rX, rY, rZ);
//			System.out.println(-x + " " + -y + " " + -z);
			view.translate(-x, -y, -z);
		}
	}
	
	public Matrix getViewMatrix(){
//		if(!lookAt){
//			
//			view.setIdentity();
//			
//			view.rotate(rX, rY, rZ);
//			view.translate(-x, -y, -z);
//		}
		
		updateViewmat();
		
		
		
		return view;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		lookAt = false;
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		lookAt = false;
		this.y = y;
	}
	public float getZ() {
		return z;
	}
	public void setZ(float z) {
		lookAt = false;
		this.z = z;
	}
	public float getrX() {
		return rX;
	}
	public void setrX(float rX) {
		lookAt = false;
		this.rX = rX;
	}
	public float getrY() {
		return rY;
	}
	public void setrY(float rY) {
		lookAt = false;
		this.rY = rY;
	}
	public float getrZ() {
		return rZ;
	}
	public void setrZ(float rZ) {
		lookAt = false;
		this.rZ = rZ;
	}
	public Matrix getProjectionMatrix() {
		return projectionMatrix;
	}
	
	
}
