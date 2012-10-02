package shadow.renderer.data.proxies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import shadow.renderer.data.SFDataAsset;
import shadow.system.data.SFAlternativeDataCenter;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFDataset;
import shadow.system.data.SFIDataCenter;

public class SFIndexedProxyDataCenter extends SFAlternativeDataCenter implements
		SFIDataCenter {

	private HashMap<String, ArrayList<SFDataset>> allData = new HashMap<String, ArrayList<SFDataset>>();

	private ArrayList<String> constants=new ArrayList<String>();
	
	private int index = 0;
	private int size = 0;

	public void setData(String name, int index, SFDataset dataset)
			throws RuntimeException {
		ArrayList<SFDataset> dataList = allData.get(name);
		if (dataList == null) {
			dataList = new ArrayList<SFDataset>();
			allData.put(name, dataList);
		}
		if (dataList.size() == 0) {
			dataList.add(dataset);
		} else {
			String typeName = dataList.get(0).getType();
			if (!dataset.getType().equalsIgnoreCase(typeName)) {
				throw new RuntimeException("Data is not available!");
			}
		}
		if (index >= dataList.size()) {
			for (int i = dataList.size(); i <= index; i++) {
				dataList.add(dataset);
			}
		} else if (dataList.size() > 1) {
			dataList.add(index, dataset);
		}

		if (size <= index)
			size = index + 1;
	}

	public void updateSize() {
		Set<String> keys = allData.keySet();
		for (String key : keys) {
			int size = allData.get(key).size();
			if (size > this.size) {
				this.size = size;
			}
		}
	}

	public SFDataset getData(String name, int index) throws RuntimeException {
		ArrayList<SFDataset> dataList = allData.get(name);
		if (dataList == null || dataList.size() == 0) {
			throw new RuntimeException("Data Unavailable: " + name + " ");
		}
		if (index < dataList.size()) {
			return dataList.get(index);
		} else {
			return dataList.get(dataList.size() - 1);
		}
	}

	public int size() {
		return size;
	}

	protected HashMap<String, ArrayList<SFDataset>> getAllData() {
		return allData;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public void addConstant(String constant){
		this.constants.add(constant);
	}

	@Override
	@SuppressWarnings("all")
	public void makeDatasetAvailable(String name, final SFDataCenterListener listener) {
		
		if(allData.get(name)==null && dataCenter!=null){
			dataCenter.makeDatasetAvailable(name, new SFDataCenterListener<SFDataset>() {
				@Override
				public void onDatasetAvailable(String name, SFDataset dataset) {
					if(constants.contains(name))
						SFDataAsset.setUpdateMode(false);
					listener.onDatasetAvailable(name, dataset);
					SFDataAsset.setUpdateMode(true);
				}
			});
		}else{
			SFDataAsset<?> dataset = (SFDataAsset<?>) (getData(name, index));
			dataset.invalidate();
			listener.onDatasetAvailable(name, dataset);	
		}
	}
}
