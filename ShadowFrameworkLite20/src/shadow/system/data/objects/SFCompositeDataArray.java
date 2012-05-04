package shadow.system.data.objects;

import java.util.ArrayList;

import shadow.system.data.SFDataObject;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;

public abstract class SFCompositeDataArray implements SFDataObject{

	private ArrayList<SFDataObject> dataObject=new ArrayList<SFDataObject>();
	
	public abstract void generateData();
	public abstract SFCompositeDataArray clone();
	
	public SFCompositeDataArray() {
		generateData();
	}
	
	protected void addDataObject(SFDataObject object){
		this.dataObject.add(object);
	}
	
	protected ArrayList<SFDataObject> getDataObject() {
		return dataObject;
	}

	public int elementsSize() {
		return dataObject.size();
	}
	
	@Override
	public void readFromStream(SFInputStream stream) {
		for (int i = 0; i < dataObject.size(); i++) {
			dataObject.get(i).readFromStream(stream);
		}
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		for (int i = 0; i < dataObject.size(); i++) {
			dataObject.get(i).writeOnStream(stream);
		}
	}
	
}
