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
		Vertex[] vert = new Vertex[36];
		
		vert[0] = new Vertex(w/2f, -h/2f, l/2f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f);
		vert[1] = new Vertex(-w/2f, -h/2f, l/2f, 0.0f, -1.0f, 0.0f, 1.0f, 1.0f);
		vert[2] = new Vertex(-w/2f, -h/2f, -l/2f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f);
		vert[3] = new Vertex(-w/2f, h/2f, -l/2f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f);
		vert[4] = new Vertex(-w/2f, h/2f, l/2f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f);
		vert[5] = new Vertex(w/2f, h/2f, l/2f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f);
		vert[6] = new Vertex(w/2f, -h/2f, -l/2f, 1.0f, -0.0f, 0.0f, 0.0f, 0.0f);
		vert[7] = new Vertex(w/2f, h/2f, -l/2f, 1.0f, -0.0f, 0.0f, 1.0f, 0.0f);
		vert[8] = new Vertex(w/2f, h/2f, l/2f, 1.0f, -0.0f, 0.0f, 1.0f, 1.0f);
		vert[9] = new Vertex(w/2f, -h/2f, l/2f, -0.0f, -0.0f, 1.0f, 0.0f, 0.0f);
		vert[10] = new Vertex(w/2f, h/2f, l/2f, -0.0f, -0.0f, 1.0f, 1.0f, 0.0f);
		vert[11] = new Vertex(-w/2f, h/2f, l/2f, -0.0f, -0.0f, 1.0f, 1.0f, 1.0f);
		vert[12] = new Vertex(-w/2f, h/2f, l/2f, -1.0f, -0.0f, -0.0f, 1.0f, 0.0f);
		vert[13] = new Vertex(-w/2f, h/2f, -l/2f, -1.0f, -0.0f, -0.0f, 1.0f, 1.0f);
		vert[14] = new Vertex(-w/2f, -h/2f, -l/2f, -1.0f, -0.0f, -0.0f, 0.0f, 1.0f);
		vert[15] = new Vertex(w/2f, -h/2f, -l/2f, 0.0f, 0.0f, -1.0f, 1.0f, 0.0f);
		vert[16] = new Vertex(-w/2f, -h/2f, -l/2f, 0.0f, 0.0f, -1.0f, 1.0f, 1.0f);
		vert[17] = new Vertex(-w/2f, h/2f, -l/2f, 0.0f, 0.0f, -1.0f, 0.0f, 1.0f);
		vert[18] = new Vertex(w/2f, -h/2f, -l/2f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f);
		vert[19] = new Vertex(w/2f, -h/2f, l/2f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f);
		vert[20] = new Vertex(-w/2f, -h/2f, -l/2f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f);
		vert[21] = new Vertex(w/2f, h/2f, -l/2f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f);
		vert[22] = new Vertex(-w/2f, h/2f, -l/2f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f);
		vert[23] = new Vertex(w/2f, h/2f, l/2f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f);
		vert[24] = new Vertex(w/2f, -h/2f, l/2f, 1.0f, -0.0f, 0.0f, 0.0f, 1.0f);
		vert[25] = new Vertex(w/2f, -h/2f, -l/2f, 1.0f, -0.0f, 0.0f, 0.0f, 0.0f);
		vert[26] = new Vertex(w/2f, h/2f, l/2f, 1.0f, -0.0f, 0.0f, 1.0f, 1.0f);
		vert[27] = new Vertex(-w/2f, -h/2f, l/2f, -0.0f, -0.0f, 1.0f, 0.0f, 1.0f);
		vert[28] = new Vertex(w/2f, -h/2f, l/2f, -0.0f, -0.0f, 1.0f, 0.0f, 0.0f);
		vert[29] = new Vertex(-w/2f, h/2f, l/2f, -0.0f, -0.0f, 1.0f, 1.0f, 1.0f);
		vert[30] = new Vertex(-w/2f, -h/2f, l/2f, -1.0f, -0.0f, -0.0f, 0.0f, 0.0f);
		vert[31] = new Vertex(-w/2f, h/2f, l/2f, -1.0f, -0.0f, -0.0f, 1.0f, 0.0f);
		vert[32] = new Vertex(-w/2f, -h/2f, -l/2f, -1.0f, -0.0f, -0.0f, 0.0f, 1.0f);
		vert[33] = new Vertex(w/2f, h/2f, -l/2f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f);
		vert[34] = new Vertex(w/2f, -h/2f, -l/2f, 0.0f, 0.0f, -1.0f, 1.0f, 0.0f);
		vert[35] = new Vertex(-w/2f, h/2f, -l/2f, 0.0f, 0.0f, -1.0f, 0.0f, 1.0f);
		int[] ind = new int[]{
			0,
			1,
			2,
			3,
			4,
			5,
			6,
			7,
			8,
			9,
			10,
			11,
			12,
			13,
			14,
			15,
			16,
			17,
			18,
			19,
			20,
			21,
			22,
			23,
			24,
			25,
			26,
			27,
			28,
			29,
			30,
			31,
			32,
			33,
			34,
			35,
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
