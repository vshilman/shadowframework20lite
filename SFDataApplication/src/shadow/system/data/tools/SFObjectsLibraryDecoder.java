package shadow.system.data.tools;

import shadow.system.data.SFObjectsLibrary;

public class SFObjectsLibraryDecoder implements SFDataInterpreter{

	private SFObjectsLibrary library;
	private String assetName;
	private SFAssetDecoder assetDecoder;
	private int pushedSize=0;
	
	public SFObjectsLibraryDecoder(SFObjectsLibrary library) {
		super();
		this.library = library;
	}

	@Override
	public void insertElement(String name, String info) {
		assetDecoder.insertElement(name, info);
	}
	
	@Override
	public void popElement(String type) {
		pushedSize--;
		if(pushedSize>0){
			assetDecoder.popElement(type);
		}
		if(pushedSize==1){
			library.put(assetName, assetDecoder.getDecodedAsset());
		}
	}
	
	
	@Override
	public void pushElement(String type, String name) {
		if(pushedSize==1){
			assetDecoder=new SFAssetDecoder(name);
			assetName=name;
		}
		if(pushedSize>0){
			assetDecoder.pushElement(type, name);
		}	
		pushedSize++;
	}
}
