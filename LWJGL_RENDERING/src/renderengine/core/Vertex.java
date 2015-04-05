package renderengine.core;

public class Vertex {
	private float vertexX, vertexY, vertexZ;  					//Vertex Position
	private float vertexNormalX,vertexNormalY,vertexNormalZ;	//Vertex Normal
	private float vertexU,vertexV;								//Vertex Texturecoord
	private boolean hasNormal;
	private boolean hasUV;
	
	public Vertex(float vertexX, float vertexY, float vertexZ,
			float vertexNormalX, float vertexNormalY, float vertexNormalZ,
			float vertexU, float vertexV) {
		super();
		this.vertexX = vertexX;
		this.vertexY = vertexY;
		this.vertexZ = vertexZ;
		this.vertexNormalX = vertexNormalX;
		this.vertexNormalY = vertexNormalY;
		this.vertexNormalZ = vertexNormalZ;
		this.vertexU = vertexU;
		this.vertexV = vertexV;
		hasNormal = true;
		hasUV = true;
	}
	
	public Vertex(float vertexX, float vertexY, float vertexZ,
			float vertexNormalX, float vertexNormalY, float vertexNormalZ) {
		super();
		this.vertexX = vertexX;
		this.vertexY = vertexY;
		this.vertexZ = vertexZ;
		this.vertexNormalX = vertexNormalX;
		this.vertexNormalY = vertexNormalY;
		this.vertexNormalZ = vertexNormalZ;
		hasNormal = true;
		hasUV = false;
	}

	public Vertex(float vertexX, float vertexY, float vertexZ) {
		super();
		this.vertexX = vertexX;
		this.vertexY = vertexY;
		this.vertexZ = vertexZ;
		hasNormal = false;
		hasUV = false;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Vertex){
			Vertex vertex = (Vertex) obj;
			boolean vertexPosEqual = false;
			boolean vertexNormalEqual = false;
			boolean vertexUVEqual = false;
			if(vertex.vertexX == vertexX && vertex.vertexY == vertexY && vertex.vertexZ == vertexZ){
				vertexPosEqual = true;
			}
			if(hasNormal == true && vertex.hasNormal == true){
				if(vertex.vertexNormalX == vertexNormalX && vertex.vertexNormalY == vertexNormalY && vertex.vertexNormalZ == vertexNormalZ){
					vertexNormalEqual = true;
				}
			}
			if(hasUV == true && vertex.hasUV == true){
				if(vertex.vertexU == vertexU && vertex.vertexV == vertexV){
					vertexUVEqual = true;
				}
			}
			
			return vertexPosEqual == true && vertexNormalEqual == true && vertexUVEqual == true;
		}
		return false;
	}

	public float getVertexX() {
		return vertexX;
	}

	public float getVertexY() {
		return vertexY;
	}

	public float getVertexZ() {
		return vertexZ;
	}

	public float getVertexNormalX() {
		return vertexNormalX;
	}

	public float getVertexNormalY() {
		return vertexNormalY;
	}

	public float getVertexNormalZ() {
		return vertexNormalZ;
	}

	public float getVertexU() {
		return vertexU;
	}

	public float getVertexV() {
		return vertexV;
	}

	public boolean hasNormal() {
		return hasNormal;
	}

	public boolean hasUV() {
		return hasUV;
	}
	
	
	
	
	
}
