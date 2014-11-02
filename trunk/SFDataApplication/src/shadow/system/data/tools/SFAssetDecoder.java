package shadow.system.data.tools;

import java.util.LinkedList;

import shadow.system.SFException;
import shadow.system.SFInitiable;
import shadow.system.data.SFCharsetObject;
import shadow.system.data.SFCharsetObjectList;
import shadow.system.data.SFDataAsset;
import shadow.system.data.SFDataAssetList;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataObject;
import shadow.system.data.SFLibraryReference;
import shadow.system.data.SFLibraryReferenceList;
import shadow.system.data.objects.SFBinaryObject;
import shadow.system.data.objects.SFDataAssetObject;

/* This is a very bad class, but {@link SFDataBuilder} is the worst!! */
public class SFAssetDecoder implements SFDataInterpreter{

	private static class Element{
		public String name;
		public String info;
		public Object relatedObject;
		public Element(String name, String info,Object relatedObject) {
			super();
			this.name = name;
			this.info = info;
			this.relatedObject=relatedObject;
		}
		@Override
		public String toString() {
			return name+":"+info;
		}
	}
	
	private SFDataAsset<?> decodedAsset;
	private String assetName;
	
	public SFAssetDecoder(String assetName) {
		this.assetName=assetName;
	}
	
	private LinkedList<Element> elements=new LinkedList<SFAssetDecoder.Element>();
	
	
	@Override
	public void insertElement(String name, String info) {
		//System.out.println("inserting data "+name+" "+info);
		Object relatedObject=elements.getLast().relatedObject;
		
		if(relatedObject instanceof SFDataAsset){
			relatedObject=((SFDataAsset<?>)relatedObject).getNewValueObject(name);
		}
		
		if(relatedObject instanceof SFCharsetObjectList){
			//System.out.println("Charset object List ("+relatedObject+")");
			((SFCharsetObjectList)relatedObject).addCharSetObjects(info);
		}else if(relatedObject instanceof SFCharsetObject){
			//System.out.println("Charset Object");
			((SFCharsetObject)relatedObject).setStringValue(info);
		}else if(relatedObject instanceof SFLibraryReference<?>){
			((SFLibraryReference<?>)relatedObject).setReference(info);
		}else if(relatedObject instanceof SFLibraryReferenceList<?>){
			((SFLibraryReferenceList<?>)relatedObject).add(info);
		}else if(relatedObject instanceof SFBinaryObject<?>){
			((SFBinaryObject<?>)relatedObject).getBinaryValue().setStringValue(info);
		}else{
			//System.out.println("It is... "+relatedObject+" "+relatedObject.getClass()+" "+elements.getLast().name);
		}
	}
	
	public String getSize(){
		return elements.size()+" "+elements;
	}
	
	@Override
	public void popElement(String type) {
		//System.out.println("remove element "+type);
		elements.removeLast();
	}
	
	@Override
	@SuppressWarnings("all")
	public void pushElement(String type, String name) {
		
		if(name==null)
			name=type;

		//System.out.println("pushElement "+type+" "+name+" "+elements);
		if(elements.size()==0){
			decodedAsset=generateAsset(type,assetName);
			elements.add(new Element(type, name, decodedAsset));
		}else{

			Object relatedObject=elements.getLast().relatedObject;
			
			if(relatedObject instanceof SFLibraryReference<?>){
				//System.out.println("A SFLibraryReference ");
				
				SFLibraryReference<?> reference=(SFLibraryReference<?>)relatedObject;
				try {
					SFDataAsset asset=(SFDataAsset<?>)generateAsset(elements.getLast().name,null);
					reference.setDataset(asset);
					relatedObject=asset;
				} catch (SFException e) {
					//TODO : still not implemented the case in which the reference is kept by name
					throw new SFException("Not yet implemented");
				}
			}else if(relatedObject instanceof SFDataAsset){
				SFDataObject object=((SFDataAsset<?>) relatedObject).getNewValueObject(name);
				elements.add(new Element(type, name, object));
				//System.out.println("\t\t\t Found object "+name+": "+object);
				
				if(object instanceof SFLibraryReference<?>){
					
					SFLibraryReference<SFInitiable> reference=(SFLibraryReference<SFInitiable>)object;
					try {
						SFDataAsset<SFInitiable> asset=(SFDataAsset<SFInitiable>)generateAsset(elements.getLast().name,null);
						reference.setDataset(asset);
						//System.out.println("\t\t\t And it is a reference to.. "+asset);
						elements.getLast().relatedObject=asset;
					} catch (SFException e) {
						//TODO : still not implemented the case in which the reference is kept by name
						System.err.println("type "+type+" name "+name);
						e.printStackTrace();
						throw new SFException("Not yet implemented");
					}
				}
				
				if(object instanceof SFDataAssetObject<?>){
					SFDataAssetObject reference=(SFDataAssetObject<?>)object;
					try {
						SFDataAsset asset=(SFDataAsset<?>)generateAsset(elements.getLast().name,null);
						reference.setDataAsset(asset);
						//System.out.println("\t\t\t And it is a reference to.. "+asset);
						elements.getLast().relatedObject=asset;
					} catch (SFException e) {
						//TODO : still not implemented the case in which the reference is kept by name
						//SURE it is not?? 
						
						throw new SFException("Not yet implemented");
					}
				}
				
			}else if(relatedObject instanceof SFDataAssetList<?>){
				SFDataAssetList<SFInitiable> dataAssetList=(SFDataAssetList<SFInitiable>)relatedObject;
				SFDataAsset<SFInitiable> asset=(SFDataAsset<SFInitiable>)generateAsset(type,null);
				elements.add(new Element(type, name, asset));
				dataAssetList.add(asset);
			}else if(relatedObject instanceof SFLibraryReferenceList<?>){
				
				SFLibraryReferenceList<SFInitiable> dataAssetList=(SFLibraryReferenceList<SFInitiable>)relatedObject;
				SFDataAsset<SFInitiable> asset=(SFDataAsset<SFInitiable>)generateAsset(type,null);
				elements.add(new Element(type, name, asset));

				SFLibraryReference<SFInitiable> reference=new SFLibraryReference<SFInitiable>(asset);
				dataAssetList.add(reference);
				//System.out.println("\t doing something else "+relatedObject.getClass());
				
			}
		}
	}

	public SFDataAsset<?> generateAsset(String type,String name) throws SFException{
		SFDataAsset<?> dataset=SFDataCenter.getDataCenter().createDataset(type,name);
		if(!(dataset instanceof SFDataAsset<?>)){
			System.err.println("dataset is "+ dataset);
			throw new SFException("SFAssetDecoder : available Dataset Factory could not create an Asset of type "+type);
		}
		return (SFDataAsset<?>)dataset;
	}

	public SFDataAsset<?> getDecodedAsset() {
		return decodedAsset;
	}
	
	
}
