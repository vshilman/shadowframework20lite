#ifndef shadow_renderer_data_proxies_H_
#define shadow_renderer_data_proxies_H_

#include "java/util/ArrayList.h"
#include "java/util/HashMap.h"
#include "java/util/Set.h"

#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/system/data.SFAlternativeDataCenter.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFDataset.h"
#include "shadow/system/data.SFIDataCenter.h"

class SFIndexedProxyDataCenter extends SFAlternativeDataCenter implements
		SFIDataCenter {

	ArrayList<SFDataset>> allData = new HashMap<String, ArrayList<SFDataset>>();

	ArrayList<String> constants=new ArrayList<String>();

	int index = 0;
	int size = 0;

	void setData(String name, int index, SFDataset dataset)
			throws RuntimeException {
		ArrayList<SFDataset> dataList = allData.get(name);
		if (dataList == null) {
			dataList = new ArrayList<SFDataset>();
			allData.put(name, dataList);
		}
		if (dataList.size() == 0) {
			dataList.add(dataset);
		}
			String typeName = dataList.get(0).getType();
			if (!dataset.getType().equalsIgnoreCase(typeName)) {
				throw new RuntimeException("Data is not available!");
			}
		}
		if (index >= dataList.size()) {
			for (int i = dataList.size(); i <= index; i++) {
				dataList.add(dataset);
			}
		}
			dataList.add(index, dataset);
		}

		if (size <= index)
			size = index + 1;
	}

	void updateSize() {
		Set<String> keys = allData.keySet();
		for (String key : keys) {
			int size = allData.get(key).size();
			if (size > this->size) {
				this->size = size;
			}
		}
	}

	SFDataset getData(String name, int index) throws RuntimeException {
		ArrayList<SFDataset> dataList = allData.get(name);

		if (dataList == null || dataList.size() == 0) {
			throw new RuntimeException("Data Unavailable: " + name + " ");
		}
		if (index < dataList.size()) {
			return dataList.get(index);
		}
			return dataList.get(dataList.size() - 1);
		}
	}

	int size() {
		return size;
	}

	HashMap<String, ArrayList<SFDataset>> getAllData() {
		return allData;
	}

	int getIndex() {
		return index;
	}

	void setIndex(int index) {
		this->index = index;
	}

	void addConstant(String constant){
		this->constants.add(constant);
	}


//	@SuppressWarnings("all")
//	void makeDatasetAvailable(String name, final SFDataCenterListener listener) {
//		if(allData.get(name)==null && dataCenter!=null){
//			dataCenter.makeDatasetAvailable(name, new SFDataCenterListener<SFDataset>() {

//				void onDatasetAvailable(String name, SFDataset dataset) {
//					if(constants.contains(name))
//						SFGraphicsAsset.setUpdateMode(false);
//					listener.onDatasetAvailable(name, dataset);
//					SFGraphicsAsset.setUpdateMode(true);
}
}
}
//			SFGraphicsAsset<?> dataset = (SFGraphicsAsset<?>) (getData(name, index));
//			dataset.invalidate();
//			listener.onDatasetAvailable(name, dataset);	
}
}

	
	@SuppressWarnings("unchecked")
	SFDataAsset<?> makeDatasetAvailable(String name) {

		if(allData.get(name)==null && dataCenter!=null){
			SFDataAsset dataset=dataCenter.makeDatasetAvailable(name);
			if(constants.contains(name))
				SFGraphicsAsset.setUpdateMode(false);
			dataset.getResource();
			SFGraphicsAsset.setUpdateMode(true);
			return dataset;
		}
			SFDataAsset<?> dataset = (SFDataAsset<?>) (getData(name, index));
			return dataset;
		}

	}
}
;
}
#endif
