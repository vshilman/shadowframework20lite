//package shadow.renderer.data;
//
//import java.util.ArrayList;
//
//import shadow.system.SFInitiable;
//import shadow.system.SFInitiator;
//import shadow.system.data.SFDataObject;
//import shadow.system.data.SFDataset;
//import shadow.system.data.objects.SFDataList;
//
//public abstract class SFDataAssetArray<T extends SFInitiable> extends SFAssetModule implements SFDataset {
//
//	private T[] resources = null;
//	private SFDataList<SFDataObject> dataList;
//	
//	public SFDataAssetArray(SFDataObject dataObject) {
//		dataList=new SFDataList<SFDataObject>(dataObject);
//		setData(dataList);
//	}
//	
//	protected ArrayList<SFDataObject> getDataList() {
//		return dataList.getDataObject();
//	}
//
//	/**
//	* Build up a valid resource using data content available from
//	* {@linkplain SFDataObject}. This method will be called only once
//	* 
//	* @return a valid resource of type T
//	*/
//	protected abstract T[] buildResources();
//	
//	public T getResource(int index) {
//		if (resources == null) {
//			resources = buildResources();
//			for (int i = 0; i < resources.length; i++) {
//				SFInitiator.addInitiable(resources[i]);
//			}
//		}
//		return resources[index];
//	}
//
//	public void invalidate() {
//	}
//
//}
