package renderengine.model;

import java.awt.image.BufferedImage;

import org.lwjgl.util.vector.Vector3f;

import renderengine.core.Material;

public class Terrain extends Model{
	private final int MAX_PIXEL_COLOR = 256*256*256;
	private static final int VERTEX_COUNT = 64;
	private static final float SIZE = 300;
	public Terrain(BufferedImage heightMap) {
		super();
		int count = VERTEX_COUNT * VERTEX_COUNT;
		float[] vertices = new float[count * 3];
		float[] normals = new float[count * 3];
		float[] textureCoords = new float[count*2];
		int[] indices = new int[6*(VERTEX_COUNT-1)*(VERTEX_COUNT*1)];
		int vertexPointer = 0;
		for(int i=0;i<VERTEX_COUNT;i++){
			for(int j=0;j<VERTEX_COUNT;j++){
				float height = getHeightAt(j, i, 40, heightMap);
				vertices[vertexPointer*3] = (float)j/((float)VERTEX_COUNT - 1) * SIZE-SIZE/2f;
				vertices[vertexPointer*3+1] = height;
				vertices[vertexPointer*3+2] = (float)i/((float)VERTEX_COUNT - 1) * SIZE-SIZE/2f;
				
				Vector3f normal = getNormalAt(j, i, 40, heightMap);
				normals[vertexPointer*3] = normal.x;
				normals[vertexPointer*3+1] = normal.y;
				normals[vertexPointer*3+2] = normal.z;
				textureCoords[vertexPointer*2] = (float)j/((float)VERTEX_COUNT - 1);
				textureCoords[vertexPointer*2+1] = (float)i/((float)VERTEX_COUNT - 1);
				vertexPointer++;
			}
		}
		int pointer = 0;
		for(int gz=0;gz<VERTEX_COUNT-1;gz++){
			for(int gx=0;gx<VERTEX_COUNT-1;gx++){
				int topLeft = (gz*VERTEX_COUNT)+gx;
				int topRight = topLeft + 1;
				int bottomLeft = ((gz+1)*VERTEX_COUNT)+gx;
				int bottomRight = bottomLeft + 1;
				indices[pointer++] = topLeft;
				indices[pointer++] = bottomLeft;
				indices[pointer++] = topRight;
				indices[pointer++] = topRight;
				indices[pointer++] = bottomLeft;
				indices[pointer++] = bottomRight;
			}
		}
		vao.addData(vertices, 0, 3);
		vao.addData(normals, 1, 3);
		vao.addData(textureCoords, 2, 3);
		vao.addElementArray(indices);
	}
	
	private float getHeightAt(int x,int y, float maxHeight,BufferedImage img){
		
		if(x<0||x>=img.getHeight()||y<0||y>=img.getHeight())return 0;
		float height = img.getRGB(x, y);
		height += MAX_PIXEL_COLOR/2f;
		height /= MAX_PIXEL_COLOR/2f;
		height *= maxHeight;
		return height;
	}
	
	private Vector3f getNormalAt(int x,int y, float maxHeight,BufferedImage img){
		float heightL = getHeightAt(x-1, y, maxHeight, img);
		float heightR = getHeightAt(x+1, y, maxHeight, img);
		float heightU = getHeightAt(x, y-1, maxHeight, img);
		float heightD = getHeightAt(x, y+1, maxHeight, img);
		
		Vector3f normal = new Vector3f(heightL-heightR,2,heightD-heightU);
		normal.normalise();
		return normal;
				
	}
}
