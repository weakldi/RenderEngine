package renderengine.model;

import renderengine.core.Vertex;

public class Quader extends Model{
	private float w,h,l;

	public Quader(float w, float h, float l) {
		super();
		this.w = w;
		this.h = h;
		this.l = l;
		initVAO();
	}
	
	public Quader(){
		this(1,1,1);
	}
	
	private void initVAO(){
		
		Vertex f0 = new Vertex(-w/2, -h/2, -l/2,0,0,1,0,0);
		Vertex f1 = new Vertex(-w/2, h/2, -l/2,0,0,1,0,1);
		Vertex f2 = new Vertex(w/2, h/2, -l/2,0,0,1,1,1);
		Vertex f3 = new Vertex(w/2, -h/2, -l/2,0,0,1,1,0);
		
		Vertex l0 = new Vertex(-w/2, -h/2, -l/2,1,0,0,0,0);
		Vertex l1 = new Vertex(-w/2, -h/2, l/2,1,0,0,0,1);
		Vertex l2 = new Vertex(-w/2, h/2, l/2,1,0,0,1,1);
		Vertex l3 = new Vertex(-w/2, h/2, -l/2,1,0,0,1,0);
		
		Vertex u0 = new Vertex(-w/2, -h/2, -l/2,0,1,0,0,0);
		Vertex u1 = new Vertex(-w/2, -h/2, l/2,0,1,0,0,1);
		Vertex u2 = new Vertex(w/2, -h/2, l/2,0,1,0,1,1);
		Vertex u3 = new Vertex(w/2, -h/2, -l/2,0,1,0,1,0);
		
		Vertex h0 = new Vertex(-w/2, -h/2, l/2,0,0,-1,0,0);
		Vertex h1 = new Vertex(-w/2, h/2, l/2,0,0,-1,0,1);
		Vertex h2 = new Vertex(w/2, h/2, l/2,0,0,-1,1,1);
		Vertex h3 = new Vertex(w/2, -h/2, l/2,0,0,-1,1,0);
		
		Vertex r0 = new Vertex(w/2, -h/2, -l/2,1,0,0,0,0);
		Vertex r1 = new Vertex(w/2, -h/2, l/2,1,0,0,0,1);
		Vertex r2 = new Vertex(w/2, h/2, l/2,1,0,0,1,1);
		Vertex r3 = new Vertex(w/2, h/2, -l/2,1,0,0,1,0);
		
		Vertex o0 = new Vertex(-w/2, h/2, -l/2,0,-1,0,0,0);
		Vertex o1 = new Vertex(-w/2, h/2, l/2,0,-1,0,0,1);
		Vertex o2 = new Vertex(w/2, h/2, l/2,0,-1,0,1,1);
		Vertex o3 = new Vertex(w/2, h/2, -l/2,0,-1,0,1,0);
		
		Vertex[] vert = new Vertex[]{
				f0,f1,f2,f3,
				l0,l1,l2,l3,
				u0,u1,u2,u3,
				h0,h1,h2,h3,
				r0,r1,r2,r3,
				o0,o1,o2,o3
		};
		
		int[] ind = new int[] {
//				0,2,1,2,0,3,
				4,6,5,6,4,7,
//				8,10,9,10,8,11,
//				12,14,13,14,12,15,
//				16,18,17,18,16,19,
//				20,22,21,22,21,24
		};
		addVertices(vert);
		addIndicies(ind);
		
	}

	public float getW() {
		return w;
	}

	public float getH() {
		return h;
	}

	public float getL() {
		return l;
	}
	
}
