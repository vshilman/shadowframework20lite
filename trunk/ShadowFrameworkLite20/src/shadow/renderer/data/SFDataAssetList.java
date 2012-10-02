package shadow.renderer.data;

import java.util.LinkedList;

import shadow.system.SFInitiable;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataObject;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;

public class SFDataAssetList<T extends SFInitiable> extends LinkedList<SFDataAsset<T>> implements SFDataObject{

	private static final long serialVersionUID=0;
	
	public SFDataAssetList() {
		super();
	}

	@Override
	@SuppressWarnings("unchecked")
	public void readFromStream(SFInputStream stream) {
		int n=stream.readShort();
		for (int i = 0; i < n; i++) {
			add((SFDataAsset<T>)SFDataCenter.getDataCenter().readDataset(stream));
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
	public SFDataAssetList<T> clone(){
		return new SFDataAssetList<T>();
	}
	
}
