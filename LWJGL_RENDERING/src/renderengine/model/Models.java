package renderengine.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import renderengine.core.Entity;

public class Models {
	private static int modelCount = 0;
	private static Map<Integer, Model> models = new HashMap<Integer,Model>();
	private static Map<Integer, List<Entity>> entities = new HashMap<>();
	
	public static void addModel(Model m){
		models.put(modelCount, m);
		entities.put(modelCount, new ArrayList<Entity>());
		modelCount++;
	}
	
	public static void addEntity(Entity e){
		entities.get(e.getModelID()).add(e);
	}
	
	public static void removeEntity(Entity e){
		entities.get(e.getModelID()).remove(e);
	}
	
	public static List<Entity> getEntitys(int modelID){
		return entities.get(modelID);
	}

	public static int getModelCount() {
		return modelCount;
	}
	
	
}
