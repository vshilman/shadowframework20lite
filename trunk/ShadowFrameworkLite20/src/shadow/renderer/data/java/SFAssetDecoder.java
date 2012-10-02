package shadow.renderer.data.java;

import java.util.LinkedList;

import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFDataAssetList;
import shadow.renderer.data.SFDataAssetObject;
import shadow.renderer.data.SFLibraryReference;
import shadow.renderer.data.SFLibraryReferenceList;
import shadow.system.SFException;
import shadow.system.SFInitiable;
import shadow.system.data.SFCharsetObject;
import shadow.system.data.SFCharsetObjectList;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataObject;
import shadow.system.data.SFDataset;
import shadow.system.data.objects.SFBinaryObject;

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
	
	private LinkedList<Element> elements=new LinkedList<SFAssetDecoder.Element>();
	
	
	@Override
	public void insertElement(String name, String info) {
		//System.out.println("inserting data "+name+" "+info);
		Object relatedObject=elements.getLast().relatedObject;
		
		if(relatedObject instanceof SFDataAsset<?>){
			relatedObject=((SFDataAsset<?>)relatedObject).getSFDataObject().getObject(name);
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
	@SuppressWarnings("unchecked")
	public void pushElement(String type, String name) {
		
		if(name==null)
			name=type;

		//System.out.println("pushElement "+type+" "+name+" "+elements);
		if(elements.size()==0){
			decodedAsset=generateAsset(type);
			elements.add(new Element(type, name, decodedAsset));
		}else{

			Object relatedObject=elements.getLast().relatedObject;
			
			if(relatedObject instanceof SFLibraryReference<?>){
				//System.out.println("A SFLibraryReference ");
				SFLibraryReference<SFInitiable> reference=(SFLibraryReference<SFInitiable>)relatedObject;
				try {
					SFDataAsset<SFInitiable> asset=(SFDataAsset<SFInitiable>)generateAsset(elements.getLast().name);
					reference.setDataset(asset);
					relatedObject=asset;
				} catch (SFException e) {
					//TODO : still not implemented the case in which the reference is kept by name
					throw new SFException("Not yet implemented");
				}
			}
			
			if(relatedObject instanceof SFDataAsset<?>){
				SFDataObject object=((SFDataAsset<?>) relatedObject).getSFDataObject().getObject(name);
				elements.add(new Element(type, name, object));
				//System.out.println("\t\t\t Found object "+name+": "+object);
				
				if(object instanceof SFLibraryReference<?>){
					
					SFLibraryReference<SFInitiable> reference=(SFLibraryReference<SFInitiable>)object;
					try {
						SFDataAsset<SFInitiable> asset=(SFDataAsset<SFInitiable>)generateAsset(elements.getLast().name);
						reference.setDataset(asset);
						//System.out.println("\t\t\t And it is a reference to.. "+asset);
						elements.getLast().relatedObject=asset;
					} catch (SFException e) {
						//TODO : still not implemented the case in which the reference is kept by name
						System.err.println("type "+type+" name "+name);
						throw new SFException("Not yet implemented");
					}
				}
				
				if(object instanceof SFDataAssetObject<?>){
					SFDataAssetObject<SFInitiable> reference=(SFDataAssetObject<SFInitiable>)object;
					try {
						SFDataAsset<SFInitiable> asset=(SFDataAsset<SFInitiable>)generateAsset(elements.getLast().name);
						reference.setDataset(asset);
						//System.out.println("\t\t\t And it is a reference to.. "+asset);
						elements.getLast().relatedObject=asset;
					} catch (SFException e) {
						//TODO : still not implemented the case in which the reference is kept by name
						throw new SFException("Not yet implemented");
					}
				}
				
			}else if(relatedObject instanceof SFDataAssetList<?>){
				SFDataAssetList<SFInitiable> dataAssetList=(SFDataAssetList<SFInitiable>)relatedObject;
				SFDataAsset<SFInitiable> asset=(SFDataAsset<SFInitiable>)generateAsset(type);
				elements.add(new Element(type, name, asset));
				dataAssetList.add(asset);
			}
		}
	}

	public SFDataAsset<?> generateAsset(String type) throws SFException{
		SFDataset dataset=SFDataCenter.getDataCenter().createDataset(type);
		if(!(dataset instanceof SFDataAsset<?>))
			throw new SFException("SFAssetDecoder : available Dataset Factory could not create an Asset of type "+type);
		return (SFDataAsset<?>)dataset;
	}

	public SFDataAsset<?> getDecodedAsset() {
		return decodedAsset;
	}
	
	
}
