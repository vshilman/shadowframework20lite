package shadow.system;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeMap;


/**
 * A Database creates resources
 * @author Alessandro Martinelli
 */
public class SFContext implements SFUpdatable{
	
	private static final int MAX_CLEANSE_ELEMENTS_PER_FRAME = 10; 

	private SFDatabase database;
	
	private SFEngine engine;
	
	
	public SFContext(SFDatabase database) {
		super();
		this.engine = new SFEngine();
	}
	
	public SFContext(SFEngine engine,SFDatabase database) {
		super();
		this.engine = engine;
	}
	
	public SFContext(SFDrawable drawable,SFDatabase database){
		this.engine=new SFEngine(drawable);
		engine.addUpdatable(this);
	}
	
	public void setDrawable(SFDrawable drawable) {
		engine.setDrawable(drawable);
	}

	//TODO : must be improved
	private TreeMap<String, SFElement> storedElement=new TreeMap<String, SFElement>();
	private ArrayList<String> elementsNamesList=new ArrayList<String>();
	private LinkedList<String> elementsToBeCleansed=new LinkedList<String>();
	private int cleanerIndex;
	
	public SFEngine getEngine() {
		return engine;
	}
	
	public SFElement getElement(int type,String name){
		SFElement element=database.getElement(this,name);
		registerElement(name,element);
		return element;
	}

	public SFAsset getAsset(int type,String name){
		SFAsset asset=(SFAsset)(database.getElement(this,name));
		registerElement(name,asset);
		engine.addInitiable(asset);
		return asset;
	}
	
	private  void registerElement(String name,SFElement element){
		storedElement.put(name, element);
		//TODO 
	}
	
	@Override
	public synchronized void update() {
		int cleansedElements=0;
		int size=elementsNamesList.size();
		for (int i = 0; i < elementsNamesList.size(); i++) {
			int index=cleanerIndex+i;
			if(index>size){
				index-=size;
			}
			String name=elementsNamesList.get(index);
			SFElement element=storedElement.get(name);
			if(element.isFree()){
				elementsToBeCleansed.add(name);
				cleansedElements++;
			}
			if(cleansedElements>MAX_CLEANSE_ELEMENTS_PER_FRAME){
				cleanerIndex=index;
				i=size;
			}
		}
		int i=0;
		for (; i < elementsToBeCleansed.size() && i<MAX_CLEANSE_ELEMENTS_PER_FRAME; i++) {

			elementsToBeCleansed.remove(0);	
		}
	}
}
