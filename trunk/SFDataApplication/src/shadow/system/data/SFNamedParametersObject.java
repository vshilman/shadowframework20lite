package shadow.system.data;

import java.util.ArrayList;
import java.util.HashMap;

import shadow.system.SFException;

/**
 * A set of parameters, each one having a name.
 * 
 * @author Alessandro Martinelli
 */
public class SFNamedParametersObject implements SFWritableDataObject{

	//Parameters Names
	private ArrayList<String> objectNames=new ArrayList<String>();
	
	//Default Data Object for each parameter
	private ArrayList<SFDataObject> dataObjects=new ArrayList<SFDataObject>();
	private HashMap<String,SFDataObject> effectiveData=new HashMap<String,SFDataObject>();
	
	/**
	 * Add a Default data Object value. This should be used on Default NamedParametersObject
	 * @param objectName
	 * @param dataObject
	 */
	public void addObject(String objectName,SFDataObject dataObject){
		objectNames.add(objectName);
		dataObjects.add(dataObject);
	}
	
	public int getSize(){
		return dataObjects.size();
	}

	/**
	 * Set an alternative dataObject for a previously added element, given its name
	 * @param objectName
	 * @param dataObject
	 */
	public void setDataObject(String objectName,SFDataObject dataObject){
		effectiveData.put(objectName, dataObject);
	}

	/**
	 * Set an alternative value for a previously added Data Object,given its index
	 * @param objectName
	 * @param dataObject
	 */
	public void setDataObject(int index,SFDataObject dataObject){
		effectiveData.put(objectNames.get(index), dataObject);
	}
	
	/**
	 * Copy this SFNamedParametersObject.
	 * Original NamedParametersObjet will store theirs defaultDataObjects when written on some SFOutputStream
	 * Copied NamedParametersObjet will store theirs alternative values when written on some SFOutputStream,
	 * 		because they are supposed to get cloned before being used.
	 */
	public SFNamedParametersObject copyDataObject()  {
		SFNamedParametersObject namedParametricObject=new SFNamedParametersObject();
		return copyDataObject(namedParametricObject);
	}

	public <G extends SFNamedParametersObject> SFNamedParametersObject copyDataObject(
			G namedParametricObject) {
		for (int i = 0; i < dataObjects.size(); i++) {
			String parameter=objectNames.get(i);
			namedParametricObject.addObject(parameter, getObject(parameter));
		}
		return namedParametricObject;
	}
	
	public int size(){
		return objectNames.size();
	}
	
	public HashMap<String, SFDataObject> getEffectiveData() {
		return effectiveData;
	}
	
	/**
	 * Get the index of a parameter
	 * @param parameter
	 * @return
	 */
	public int getIndex(String parameter){
		return objectNames.indexOf(parameter);
	}
	
	

	/**
	 * Given a parameter, create a clone of a dataObject and use it to assign it as alternative data object for that parameter
	 * @param parameter
	 * @return
	 * @throws SFException
	 * @throws ClassCastException
	 */
	@SuppressWarnings("unchecked")
	public <E extends SFDataObject> E getNewValueObject(String parameter) throws SFException,ClassCastException{
		E object=(E)getObject(parameter).copyDataObject();
		setDataObject(parameter,object);
		return object;
	}
	
	public SFDataObject getNewParameterReference(String parameter,String originalName,int index) throws SFException,ClassCastException{
		//int index=SFStaticParametersObject.getParametersObject().getIndex(parameter);
		SFParameterObject param_=new SFParameterObject(index);
		SFDataObject sampleObject=getObject(originalName).copyDataObject();
		setDataObject(originalName,param_);
		return sampleObject;
	}
	
	
	/**
	 * Retrieve the value of a Parameter
	 * @param parameter The name of the parameter
	 * @return The {@link SFDataObject} 
	 * @throws SFException when there is no parameter with that name
	 */
	@SuppressWarnings("unchecked")
	public <E extends SFDataObject> E getObject(String parameter) throws SFException,ClassCastException{
		try {
			E object=(E)effectiveData.get(parameter);
			if(object!=null)
				return object;
			return (E)(dataObjects.get(objectNames.indexOf(parameter)));
		} catch (Exception e) {
			throw new SFException("Warning! SFNamedParametersObject : The parameter named '"+parameter+"' " +
					"could not be found since available parameters are "+objectNames);
		}
	}
	
	public SFWritableDataObject getWritableObject(String parameter){
		try {
			return (SFWritableDataObject)getObject(parameter);
		} catch (ClassCastException e) {
			throw new SFException(parameter+" is not a writable data Object");
		}
	}
	
	public String getName(int index) throws IndexOutOfBoundsException{
		return this.objectNames.get(index);
	}
	
	@SuppressWarnings("unchecked")
	public <E extends SFDataObject> E getDataObject(int index) throws IndexOutOfBoundsException, SFException,ClassCastException{
		try {
			String parameter=this.objectNames.get(index);
			E effectiveObject=(E)this.effectiveData.get(parameter);
			if(effectiveObject!=null){
				if(effectiveObject instanceof SFParameterObject){//TODO : oh, really?
					return SFStaticParametersObject.getParametersObject().getDataObject(((SFParameterObject)effectiveObject).getIndex());
				}
				return effectiveObject;	
			}
			return (E)this.dataObjects.get(index);
		} catch (IndexOutOfBoundsException e) {
			throw e;
		}
	}
	
	@Override
	public void readFromStream(SFInputStream stream) {
		int n=stream.readByte();
		if(n==0){
			for (int i = 0; i < dataObjects.size(); i++) {
				int index=i;
				readDataObjectFromStream(stream, index);
			}
		}else{
			for (int i = 0; i < n; i++) {
				byte index=stream.readByte();
				if(index<0){
					byte paramIndex=stream.readByte();
					setDataObject(-index-1,new SFParameterObject(paramIndex));
				}else{
					readDataObjectFromStream(stream, index);	
				}	
			}
		}
	}

	private void readDataObjectFromStream(SFInputStream stream, int index) {
		SFDataObject dataObject=dataObjects.get(index).copyDataObject();
		dataObject.readFromStream(stream);
		setDataObject(index, dataObject);
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		if(effectiveData.size()==0){
			stream.writeByte(0);
			for (int i = 0; i < dataObjects.size(); i++) {
				getDataObject(i).writeOnStream(stream);
			}
		}else{
			int n=effectiveData.size();//only effective Data are copied into the stream
			stream.writeByte(n);
			for (String name : effectiveData.keySet()) {
				SFDataObject effectiveDatum=effectiveData.get(name);
				int index=objectNames.indexOf(name);
				if(effectiveDatum instanceof SFParameterObject){
					stream.writeByte(-index-1);
					stream.writeByte(((SFParameterObject)effectiveDatum).getIndex());
				}else{
					stream.writeByte(index);
					effectiveDatum.writeOnStream(stream);
				}	
			}
		}
	}
	
	@Override
	public void setStringValue(String value) {
		System.err.println("["+value+"]");
	}
	
	@Override
	public String toStringValue() {
		return "Don't care";
	}
}
