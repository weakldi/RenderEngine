package renderengine.model;

import renderengine.core.Vertex;

public class Plane extends Model{
	
	private float w,h;
	
	public Plane() {
		super();
	}


	public Plane(float w, float h) {
		super();
		this.w = w;
		this.h = h;
		Vertex[] vert = new Vertex[]{
				new Vertex(-w/2, -h/2, 0, 0, 0, 1, 0, 0),
				new Vertex(-w/2,  h/2, 0, 0, 0, 1, 0, 1),
				new Vertex(w/2,   h/2, 0, 0, 0, 1, 1, 1),
				new Vertex(w/2,  -h/2, 0, 0, 0, 1, 1, 0),
				
				new Vertex(-w/2, -h/2, -0.01f, 0, 0, -1, 0, 0),
				new Vertex(-w/2,  h/2, -0.01f, 0, 0, -1, 0, 1),
				new Vertex(w/2,   h/2, -0.01f, 0, 0, -1, 1, 1),
				new Vertex(w/2,  -h/2, -0.01f, 0, 0, -1, 1, 0)
		};
		
		int[] ind = new int[]{
			0,2,1,
			2,0,3,
			
			4,5,6,
			6,4,7
		};
		addVertices(vert);
		addIndicies(ind);
	}

	

	public float getW() {
		return w;
	}

	public void setW(float w) {
		this.w = w;
	}

	public float getH() {
		return h;
	}

	public void setH(float h) {
		this.h = h;
	}

}
