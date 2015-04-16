package renderengine.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import renderengine.core.Vertex;

public class Kugel extends Model{
	private float radius;
	private int quality;
	 
	public Kugel(float r,int q) {
		this.radius = r;
		this.quality = q;
		genVertex();
	}
	private void genVertex(){
		
		
	}
}
