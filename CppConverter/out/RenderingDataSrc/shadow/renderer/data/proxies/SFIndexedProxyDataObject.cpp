#ifndef shadow_renderer_data_proxies_H_
#define shadow_renderer_data_proxies_H_

#include "java/util/ArrayList.h"
#include "java/util/HashMap.h"
#include "java/util/Set.h"

#include "shadow/system/data.SFCharsetObject.h"
#include "shadow/system/data.SFDataCenter.h"
#include "shadow/system/data.SFDataset.h"
#include "shadow/system/data.SFInputStream.h"
#include "shadow/system/data.SFOutputStream.h"
#include "shadow/system/data.java.SFStringTokenizerInputStream.h"
#include "shadow/system/data.java.SFStringWriterStream.h"
#include "shadow/system/data.objects.SFPrimitiveType.h"

class SFIndexedProxyDataObject extends SFPrimitiveType implements SFCharsetObject{

	SFIndexedProxyDataCenter proxyDataCenter=new SFIndexedProxyDataCenter();

	
	SFPrimitiveType clone() {
		return new SFIndexedProxyDataObject();
	}

	
	void readFromStream(SFInputStream stream) {

		int mapSize=stream.readInt();
		for (int i = 0; i < mapSize; i++) {

			ArrayList<SFDataset> singleData=new ArrayList<SFDataset>();
			String key=stream.readString();
			int size=stream.readInt();
			if(size>0){
				String type=stream.readString();
				for (int j = 0; j < size; j++) {
					SFDataset dataset=SFDataCenter.getDataCenter().createDataset(type);
					dataset.getSFDataObject().readFromStream(stream);
					singleData.add(dataset);	
				}
			}
			proxyDataCenter.getAllData().put(key, singleData);
		}

		proxyDataCenter.updateSize();
	}

	
	void writeOnStream(SFOutputStream stream) {
		HashMap<String, ArrayList<SFDataset>> allData=proxyDataCenter.getAllData();
		stream.writeInt(allData.size());

		Set<String> keys=allData.keySet();

		for (String key : keys) {
			stream.writeString(key);
			ArrayList<SFDataset> singleData=allData.get(key);
			stream.writeInt(singleData.size());
			if(singleData.size()>0){
				stream.writeString(singleData.get(0).getType());
				for (SFDataset sfDataset : singleData) {
					sfDataset.getSFDataObject().writeOnStream(stream);
				}
			}
		}
	}

	SFIndexedProxyDataCenter getProxyDataCenter() {
		return proxyDataCenter;
	}

	
	void setStringValue(String value) {
		SFStringTokenizerInputStream stream=new SFStringTokenizerInputStream(value);
		readFromStream(stream);
	}

	
	String toStringValue() {
		SFStringWriterStream stream=new SFStringWriterStream();
		writeOnStream(stream);
		return stream.getString();
	}
}
;
}
#endif
