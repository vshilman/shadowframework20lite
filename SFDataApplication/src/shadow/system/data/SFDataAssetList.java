package shadow.system.data;

import java.util.LinkedList;

public class SFDataAssetList<T> extends LinkedList<SFDataAsset<T>> implements SFDataObject{

	private static final long serialVersionUID=0;
	
	public SFDataAssetList() {
		super();
	}

	@Override
	@SuppressWarnings("unchecked")
	public void readFromStream(SFInputStream stream) {
		clear();
		int n=stream.readShort();
		for (int i = 0; i < n; i++) {
			add((SFDataAsset<T>)SFDataCenter.getDataCenter().readDataset(stream,null));
		}
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeShort((short)(size()));
		for (int i = 0; i < size(); i++) {
			SFDataCenter.getDataCenter().writeDataset(stream, get(i));
		}
	}
	
	@Override
	public SFDataAssetList<T> copyDataObject(){
		return new SFDataAssetList<T>();
	}
}
