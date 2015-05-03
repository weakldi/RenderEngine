package renderengine.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import renderengine.core.Vertex;
import renderengine.res.ResLoader;

public class OBJLoader {
	private static List<Vertex> vert = new ArrayList<>();
	private static List<Vector3f> vertPos = new ArrayList<>();
	private static List<Vector2f> vertUV = new ArrayList<>();
	private static List<Vector3f> vertNormal = new ArrayList<>();
	private static List<Integer> ind = new ArrayList<>();
	private static Map<Vertex, Integer> vertexMap = new HashMap<>();
	private static int lastInd = 0;
	private static String lastModelAsCode = "";
	public static OBJModel loadOBJ(String file){
		OBJModel m = new OBJModel();
		String line = "";
				try {
			BufferedReader in = new BufferedReader(new FileReader(new File(file)));
			while((line = in.readLine())!=null){
				if(line.startsWith("v ")){
					String[] coords = line.split(" ");
					Vector3f vertex = new Vector3f(Float.valueOf(coords[1]), Float.valueOf(coords[2]), Float.valueOf(coords[3]));
					vertPos.add(vertex);
				}else if(line.startsWith("vt ")){
					String[] uvs = line.split(" ");
					Vector2f uv = new Vector2f(Float.valueOf(uvs[2]), Float.valueOf(uvs[1]));
					vertUV.add(uv);
 				}else if(line.startsWith("vn ")){
					String[] normalDir = line.split(" ");
					Vector3f normal = new Vector3f(Float.valueOf(normalDir[1]), Float.valueOf(normalDir[2]), Float.valueOf(normalDir[3]));
					vertNormal.add(normal);
				}else if(line.startsWith("f ")){
					String[] face = line.split(" ");
					String[] vertexData1 = face[1].split("/");
					String[] vertexData2 = face[2].split("/");
					String[] vertexData3 = face[3].split("/");
					
					Vertex vertex1 = getVertex(vertexData1);
					Vertex vertex2 = getVertex(vertexData2);
					Vertex vertex3 = getVertex(vertexData3);
					processVertex(vertex1);
					processVertex(vertex2);
					processVertex(vertex3);
					
				}
			}
			in.close();
		} catch (IOException e) {
			vert.clear();
			vertexMap.clear();
			vertNormal.clear();
			vertPos.clear();
			vertUV.clear();
			ind.clear();
			lastInd = 0;
			m.delete();
			m = null;
			e.printStackTrace();
		}	
		
				
		m.addVertices(VertexListToArray(vert));
		m.addIndicies(intListToarray(ind));
		vert.clear();
		vertexMap.clear();
		vertNormal.clear();	
		vertPos.clear();
		vertUV.clear();
		ind.clear();
		lastInd = 0;
		return m;
	}
	
	public static String loadOBJToString(String file){
		lastModelAsCode = "";
		OBJModel m = new OBJModel();
		String line = "";
				try {
			BufferedReader in = new BufferedReader(new FileReader(new File(file)));
			while((line = in.readLine())!=null){
				if(line.startsWith("v ")){
					String[] coords = line.split(" ");
					Vector3f vertex = new Vector3f(Float.valueOf(coords[1]), Float.valueOf(coords[2]), Float.valueOf(coords[3]));
					vertPos.add(vertex);
				}else if(line.startsWith("vt ")){
					String[] uvs = line.split(" ");
					Vector2f uv = new Vector2f(Float.valueOf(uvs[1]), Float.valueOf(uvs[2]));
					vertUV.add(uv);
 				}else if(line.startsWith("vn ")){
					String[] normalDir = line.split(" ");
					Vector3f normal = new Vector3f(Float.valueOf(normalDir[1]), Float.valueOf(normalDir[2]), Float.valueOf(normalDir[3]));
					vertNormal.add(normal);
				}else if(line.startsWith("f ")){
					String[] face = line.split(" ");
					String[] vertexData1 = face[1].split("/");
					String[] vertexData2 = face[2].split("/");
					String[] vertexData3 = face[3].split("/");
					
					Vertex vertex1 = getVertex(vertexData1);
					Vertex vertex2 = getVertex(vertexData2);
					Vertex vertex3 = getVertex(vertexData3);
					processVertex(vertex1);
					processVertex(vertex2);
					processVertex(vertex3);
					
				}
			}
			in.close();
		} catch (IOException e) {
			vert.clear();
			vertexMap.clear();
			vertNormal.clear();
			vertPos.clear();
			vertUV.clear();
			ind.clear();
			lastInd = 0;
			m.delete();
			m = null;
			e.printStackTrace();
		}	
				lastModelAsCode = "Vertex[] vert = new Vertex["+vert.size()+"];\n";
		int i = 0;
		for (Vertex v : vert) {
			lastModelAsCode+="vert["+(i)+"] = new Vertex(" + v.getVertexX() + "f, " + v.getVertexY() + "f, " + v.getVertexZ()  +"f, " + v.getVertexNormalX() + "f, " + v.getVertexNormalY() + "f, " + v.getVertexNormalZ() + "f, "
					+ v.getVertexU() + "f, " + v.getVertexV() + "f);\n";
			i++;
		}
		lastModelAsCode += "\nint[] ind = new int[]{\n";
		for (Integer ind : ind) {
			lastModelAsCode +="\t" + ind + ",\n";
		}
		lastModelAsCode +="};\n";
				
		
		vert.clear();
		vertexMap.clear();
		vertNormal.clear();	
		vertPos.clear();
		vertUV.clear();
		ind.clear();
		lastInd = 0;
		
		return lastModelAsCode;
	}
	
	private static void processVertex(Vertex v){
		if(vertexMap.containsKey(v)){
			ind.add(vertexMap.get(v));
		}else{
			vert.add(v);
			ind.add(lastInd);
			vertexMap.put(v, lastInd);
			lastInd++;
		}
		
	}
	
	private static Vertex getVertex(String[] vertData){
		int posInd = Integer.valueOf(vertData[0]);
		int normalInd = Integer.valueOf(vertData[2]);
		int uvInd = Integer.valueOf(vertData[1]);
		
		Vector3f pos = vertPos.get(posInd-1);
		Vector3f normal = vertNormal.get(normalInd-1);
		Vector2f uv = vertUV.get(uvInd-1);
		
		Vertex vertex = new Vertex(pos.getX(), pos.getY(), pos.getZ(), normal.getX(), normal.getY(), normal.getZ(), uv.getX(), uv.getY());
		return vertex;
		
	}
	
	public static Vertex[] VertexListToArray(List<Vertex> vList){
		Vertex[] vArray = new Vertex[vList.size()];
		for (int i = 0; i < vArray.length; i++) {
			vArray[i] = vList.get(i);
		}
		return vArray;
	}
	
	public static int[] intListToarray(List<Integer> iList){
		int[] iArray = new int[iList.size()];
		for(int i = 0; i < iList.size();i++){
			iArray[i] = iList.get(i);
		}
		return iArray;
	}

	public static String getLastModelAsCode() {
		return lastModelAsCode;
	}

}
