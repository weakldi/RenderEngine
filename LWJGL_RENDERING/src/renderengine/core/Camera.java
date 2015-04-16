package renderengine.core;


import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Matrix4f;
public class Camera {
	
	private float w,h;
	protected float x,y,z;
	protected float rX,rY,rZ;
	Matrix view ;
	private boolean lookAt = false;
	private Matrix projectionMatrix;
	private boolean usedForWindow;
	
	/**
	 * Erstelt eine neue Kamera mit der Weite w und der Höhe h fur die Projectionmatrix.
	 * Die Variable usedForWindow wird benutzt um festzustellen ob die Kamera bei Veränderung der Displayauflösung upgedated werden soll (Projectionmatrix).
	 * @param w
	 * @param h
	 * @param usedForWindow
	 */
	public Camera(float w,float h,boolean usedForWindow) {
		this.w = w;
		this.h = h;
		reCalcProjection();
		view = new Matrix();
		this.usedForWindow = usedForWindow;
		AppHandler.mainApp.addCamera(this);
	}
	/**
	 * Erstelt eine neue Kamera mit der Weite w und der Höhe h fur die Projectionmatrix.
	 * Standartmäsig wird die Kamera bei Veränderung der Displayauflösung upgedated.
	 * @param w
	 * @param h
	 * @param usedForWindow
	 */
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
	
	public void lookAt(final Vector3f eye, final Vector3f dir, final Vector3f up)
    {
		
//        final Vector3f f = (Vector3f.sub(dir, eye, null)).normalise(null);
		final Vector3f f = dir.normalise(null);
        final Vector3f upn = up.normalise(null); // <--
        final Vector3f s = (Vector3f.cross(f, upn, null)).normalise(null); // <--
        final Vector3f u = Vector3f.cross(s, f, null); // <--
 
     //System.out.println("s = " + s + " upn = " + upn + " f = " + f + " u = " + u + " up = " + up);
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
//        System.out.println(eye.z);
        view.getMatGL() .translate(new Vector3f(-eye.x, -eye.y, -eye.z));
//       
		
        	
        	
//        Vector3f right = new Vector3f();
//        dir.normalise();
//        System.out.println(dir + "\n" + up);
//        Vector3f.cross(dir,up,right);
//        System.out.println(right);
//        right.normalise();
//
//        Vector3f.cross(right,dir,up);
//        up.normalise();
//
//        Matrix4f aux = new Matrix4f();
//
//        view.getMatGL().m00 = right.getX();
//        view.getMatGL().m01  = right.getY();
//        view.getMatGL().m02 = right.getZ();
//        view.getMatGL().m03 = 0.0f;
//
//        view.getMatGL().m10  = up.getX();
//        view.getMatGL().m11  = up.getY();
//        view.getMatGL().m12 = up.getZ();
//        view.getMatGL().m13 = 0.0f;
//
//        view.getMatGL().m20 = -dir.getX();
//        view.getMatGL().m21  = -dir.getY();
//        view.getMatGL().m22 = -dir.getZ();
//        view.getMatGL().m23 =  0.0f;
//
//        view.getMatGL().m30  = 0.0f;
//        view.getMatGL().m31  = 0.0f;
//        view.getMatGL().m32 = 0.0f;
//        view.getMatGL().m33 = 1.0f;
//
//        //setup aux as a translation matrix by placing positions in the last column
//        aux.m30 = -eye.getX();
//        aux.m31 = -eye.getY();
//        aux.m32 = -eye.getZ();
//
//        //multiplication(in fact translation) view.getMatGL() with aux
//        Matrix4f.mul(view.getMatGL(), aux, view.getMatGL());

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
