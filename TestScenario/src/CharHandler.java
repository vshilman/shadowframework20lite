import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

import shadow.math.SFVertex3f;


public class CharHandler {
	private static final CharHandler instance = new CharHandler();
	ConcurrentHashMap<Integer, CharacterDef> mapChar = new ConcurrentHashMap<Integer, CharacterDef>();
	private static ArrayList<ModelNotif> objList = new ArrayList<ModelNotif>();

	
	private CharHandler() {
	}

	public static CharHandler getInstance() {
		
		return instance;
		
	}
public static CharHandler getInstance(ModelNotif me) {
		
		objList.add(me);
		return instance;

	}
	
	
	public void setPositionAt(int id, float x, float y, float z) {

		SFVertex3f position = new SFVertex3f(x, y, z);
		
		
			if (mapChar.containsKey(id)) {

			mapChar.get(id).setPosition(position);
			notifyChgPos(id);
			
		} else {
			
			CharacterDef character = new CharacterDef();
			character.setPosition(position);
			mapChar.put(id, character);
			notifyNewChar(id);
		}
		
		
	}
	
	public SFVertex3f getPositionAt(int id){
		
		SFVertex3f position = null;
		
		if (mapChar.containsKey(id)) {

		position = mapChar.get(id).getPosition();
		}	
	
		return position;
	}
	public void notifyRemoveChar(int id){
		
		for (ModelNotif obs : objList) {
			
			obs.notifyRemoveChar(id);

		}
	}
	
	
	public void notifyNewChar(int id){
		
		for (ModelNotif obs : objList) {
			
			obs.notifyNewChar(id);

		}
	}
		public void notifyChgPos(int id){
			
			for (ModelNotif obs : objList) {
				obs.notifyChgPos(id);
			}

		
	}
	
	public  ConcurrentHashMap<Integer, CharacterDef> getAllChar(){
		
		return mapChar;
		
	}
	
	
	public void removeChar(int id){
	
		if (mapChar.containsKey(id)) {
			mapChar.remove(id);
			notifyRemoveChar(id);
			
		}
		
	}
	
}
