#ifndef shadow_renderer_data_java_H_
#define shadow_renderer_data_java_H_

#include "shadow/system/data.SFObjectsLibrary.h"

class SFObjectsLibraryDecoder implements SFDataInterpreter{

	SFObjectsLibrary library;
	String assetName;
	AssetDecoder assetDecoder;
	int pushedSize=0;

	SFObjectsLibraryDecoder(SFObjectsLibrary library) {
		super();
		this->library = library;
	}

	
	void insertElement(String name, String info) {
		assetDecoder.insertElement(name, info);
	}

	
	void popElement(String type) {
		pushedSize--;
		if(pushedSize>0){
			assetDecoder.popElement(type);
		}
		if(pushedSize==1){
			library.put(assetName, assetDecoder.getDecodedAsset());
		}
	}


	
	void pushElement(String type, String name) {
		if(pushedSize==1){
			assetDecoder=new SFAssetDecoder();
			assetName=name;
		}
		if(pushedSize>0){
			assetDecoder.pushElement(type, name);
		}
		pushedSize++;
	}
}
;
}
#endif
