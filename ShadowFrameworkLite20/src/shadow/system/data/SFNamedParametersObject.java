package shadow.system.data;

import java.util.LinkedList;

import shadow.system.SFException;


public class SFNamedParametersObject implements SFDataObject{

	private LinkedList<String> objectNames=new LinkedList<String>();
	private LinkedList<SFDataObject> dataObjects=new LinkedList<SFDataObject>();
	
	public void addObject(String objectName,SFDataObject dataObject){
		objectNames.add(objectName);
		dataObjects.add(dataObject);
	}
	
	@Override
	public SFNamedParametersObject clone()  {
		SFNamedParametersObject namedParametricObject=new SFNamedParametersObject();
		for (int i = 0; i < dataObjects.size(); i++) {
			namedParametricObject.addObject(objectNames.get(i), dataObjects.get(i).clone());
		}
		
		return namedParametricObject;
	}
	
	public int size(){
		return objectNames.size();
	}
	
	public int getIndexOf(String parameter){
		return objectNames.indexOf(parameter);
	}
	
	public SFDataObject getObject(String parameter){
		try {
			return dataObjects.get(objectNames.indexOf(parameter));
		} catch (Exception e) {
			System.err.println("Warning! SFNamedParametersObject : The parameter named '"+parameter+"' " +
					"could not be found since available parameters are "+objectNames);
			return null;
		}
	}
	
	public SFWritableDataObject getWritableObject(String parameter){
		try {
			return (SFWritableDataObject)dataObjects.get(objectNames.indexOf(parameter));
		} catch (Exception e) {
			throw new SFException(parameter+" is not a writable data Object");
		}
	}
	
	public String getName(int index) throws IndexOutOfBoundsException{
		return this.objectNames.get(index);
	}
	
	public SFDataObject getDataObject(int index) throws IndexOutOfBoundsException{
		return this.dataObjects.get(index);
	}
	
	public void setParameter(String parameterName,String value){
		SFDataObject dataObject=dataObjects.get(getIndexOf(parameterName));
		if(dataObject instanceof SFCharsetObject){
			((SFCharsetObject)dataObject).setStringValue(value);	
		}
	}
	
	@Override
	public void readFromStream(SFInputStream stream) {
		int n=objectNames.size();
		for (int i = 0; i < n; i++) {
			dataObjects.get(i).readFromStream(stream);
		}
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		int n=objectNames.size();
		for (int i = 0; i < n; i++) {
			dataObjects.get(i).writeOnStream(stream);
		}
	}
	
}
