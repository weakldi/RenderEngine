package renderengine.model;

import java.awt.image.BufferedImage;

public class Terrain extends Model{
	public Terrain(BufferedImage heightMap,float size) {
		super();
		for (int x = 0; x < heightMap.getWidth()-2; x++) {
			for (int z = 0; z < heightMap.getHeight()-2; z++) {
				float xPos1 = (float)x/(float)heightMap.getWidth()*size;
				float yPos1 = (float) z/(float)heightMap.getHeight()*size;
				
				
				
			}
		}
	}
}
