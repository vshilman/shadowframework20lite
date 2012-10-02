package shadow.renderer.data.proxies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import shadow.system.data.SFCharsetObject;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataset;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;
import shadow.system.data.java.SFStringTokenizerInputStream;
import shadow.system.data.java.SFStringWriterStream;
import shadow.system.data.objects.SFPrimitiveType;

public class SFIndexedProxyDataObject extends SFPrimitiveType implements SFCharsetObject{

	private SFIndexedProxyDataCenter proxyDataCenter=new SFIndexedProxyDataCenter();
	
	@Override
	public SFPrimitiveType clone() {
		return new SFIndexedProxyDataObject();
	}
	
	@Override
	public void readFromStream(SFInputStream stream) {
		
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
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
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

	public SFIndexedProxyDataCenter getProxyDataCenter() {
		return proxyDataCenter;
	}

	@Override
	public void setStringValue(String value) {
		SFStringTokenizerInputStream stream=new SFStringTokenizerInputStream(value);
		readFromStream(stream);
	}
	
	@Override
	public String toStringValue() {
		SFStringWriterStream stream=new SFStringWriterStream();
		writeOnStream(stream);
		return stream.getString();
	}
}
