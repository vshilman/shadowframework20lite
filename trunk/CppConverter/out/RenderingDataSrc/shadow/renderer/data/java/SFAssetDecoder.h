#ifndef shadow_renderer_data_java_H_
#define shadow_renderer_data_java_H_

#include "java/util/LinkedList.h"

#include "shadow/system/SFException.h"
#include "shadow/system/SFGraphicsResource.h"
#include "shadow/system/data.SFCharsetObject.h"
#include "shadow/system/data.SFCharsetObjectList.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFDataAssetList.h"
#include "shadow/system/data.SFDataAssetObject.h"
#include "shadow/system/data.SFDataCenter.h"
#include "shadow/system/data.SFDataModule.h"
#include "shadow/system/data.SFDataObject.h"
#include "shadow/system/data.SFDataset.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFLibraryReferenceList.h"
#include "shadow/system/data.objects.SFBinaryObject.h"

namespace sf{
class SFAssetDecoder implements SFDataInterpreter{

	static class Element{
		String name;
		String info;
		Object relatedObject;
		Element(String name, String info,Object relatedObject) {
			super();
			this->name = name;
			this->info = info;
			this->relatedObject=relatedObject;
		}
		
		String toString() {
			return name+":"+info;
		}
	}

	Asset;

	AssetDecoder.Element>();


	
	void insertElement(String name, String info) {
		//System.out.println("inserting data "+name+" "+info);
		Object relatedObject=elements.getLast().relatedObject;

		if(relatedObject instanceof SFDataModule){
			relatedObject=((SFDataModule)relatedObject).getSFDataObject().getObject(name);
		}

		if(relatedObject instanceof SFCharsetObjectList){
			//System.out.println("Charset object List ("+relatedObject+")");
			((SFCharsetObjectList)relatedObject).addCharSetObjects(info);
		}
			//System.out.println("Charset Object");
			((SFCharsetObject)relatedObject).setStringValue(info);
		}
			((SFLibraryReference<?>)relatedObject).setReference(info);
		}
			((SFLibraryReferenceList<?>)relatedObject).add(info);
		}
			((SFBinaryObject<?>)relatedObject).getBinaryValue().setStringValue(info);
		}
			//System.out.println("It is... "+relatedObject+" "+relatedObject.getClass()+" "+elements.getLast().name);
		}
	}

	String getSize(){
		return elements.size()+" "+elements;
	}

	
	void popElement(String type) {
		//System.out.println("remove element "+type);
		elements.removeLast();
	}

	
	@SuppressWarnings("unchecked")
	void pushElement(String type, String name) {

		if(name==null)
			name=type;

		//System.out.println("pushElement "+type+" "+name+" "+elements);
		if(elements.size()==0){
			decodedAsset=generateAsset(type);
			elements.add(new Element(type, name, decodedAsset));
		}

			Object relatedObject=elements.getLast().relatedObject;

			if(relatedObject instanceof SFLibraryReference<?>){
				//System.out.println("A SFLibraryReference ");

				SFLibraryReference<?> reference=(SFLibraryReference<?>)relatedObject;
				try {
					SFDataAsset asset=(SFDataAsset<?>)generateAsset(elements.getLast().name);
					reference.setDataset(asset);
					relatedObject=asset;
				}
					//TODO : still not implemented the case in which the reference is kept by name
					throw new SFException("Not yet implemented");
				}
			}
				SFDataObject object=((SFDataModule) relatedObject).getSFDataObject().getObject(name);
				elements.add(new Element(type, name, object));
				//System.out.println("\t\t\t Found object "+name+": "+object);

				if(object instanceof SFLibraryReference<?>){

					SFLibraryReference<SFGraphicsResource> reference=(SFLibraryReference<SFGraphicsResource>)object;
					try {
						SFDataAsset<SFGraphicsResource> asset=(SFDataAsset<SFGraphicsResource>)generateAsset(elements.getLast().name);
						reference.setDataset(asset);
						//System.out.println("\t\t\t And it is a reference to.. "+asset);
						elements.getLast().relatedObject=asset;
					}
						//TODO : still not implemented the case in which the reference is kept by name
						System.err.println("type "+type+" name "+name);
						throw new SFException("Not yet implemented");
					}
				}

				if(object instanceof SFDataAssetObject<?>){
					SFDataAssetObject<SFGraphicsResource> reference=(SFDataAssetObject<SFGraphicsResource>)object;
					try {
						SFDataAsset asset=(SFDataAsset<?>)generateAsset(elements.getLast().name);
						reference.setDataset(asset);
						//System.out.println("\t\t\t And it is a reference to.. "+asset);
						elements.getLast().relatedObject=asset;
					}
						//TODO : still not implemented the case in which the reference is kept by name
						//SURE it is not?? 

						throw new SFException("Not yet implemented");
					}
				}

			}
				SFDataAssetList<SFGraphicsResource> dataAssetList=(SFDataAssetList<SFGraphicsResource>)relatedObject;
				SFDataAsset<SFGraphicsResource> asset=(SFDataAsset<SFGraphicsResource>)generateAsset(type);
				elements.add(new Element(type, name, asset));
				dataAssetList.add(asset);
			}

				SFLibraryReferenceList<SFGraphicsResource> dataAssetList=(SFLibraryReferenceList<SFGraphicsResource>)relatedObject;
				SFDataAsset<SFGraphicsResource> asset=(SFDataAsset<SFGraphicsResource>)generateAsset(type);
				elements.add(new Element(type, name, asset));

				SFLibraryReference<SFGraphicsResource> reference=new SFLibraryReference<SFGraphicsResource>(asset);
				dataAssetList.add(reference);
				//System.out.println("\t doing something else "+relatedObject.getClass());

			}
		}
	}

	SFDataModule generateAsset(String type) throws SFException{
		SFDataset dataset=SFDataCenter.getDataCenter().createDataset(type);
		if(!(dataset instanceof SFDataAsset<?>))
			throw new SFException("SFAssetDecoder : available Dataset Factory could not create an Asset of type "+type);
		return (SFDataModule)dataset;
	}

	SFDataModule getDecodedAsset() {
		return decodedAsset;
	}


}
;
}
#endif
