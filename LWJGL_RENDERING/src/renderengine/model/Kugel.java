package renderengine.model;

import org.lwjgl.util.vector.Vector3f;

public class Kugel {
	private static Model m;
	public Kugel() {
		genVertex();
	}
	private void genVertex(){
		float x = 0,y = 0,z = 0;
		float radius = 0.5f;
		
		for(int xrot = 0; xrot < 360 ; xrot ++){
			for(int yrot = 0; yrot < 360 ; yrot ++){
				float xPos = x + (float)Math.cos(Math.toRadians(yrot))*radius;
				float yPos = y + (float)Math.sin(Math.toRadians(xrot))*radius;
				float zPos = z + (float)Math.sin(Math.toRadians(yrot))*radius;
				System.out.print(xPos + " " + yPos + " " + zPos + ", ");
				Vector3f normal = new Vector3f(xPos, yPos, zPos);
				normal.normalise();
			}
			System.out.println();
		}
	}
}
