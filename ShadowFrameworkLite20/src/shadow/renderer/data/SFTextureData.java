package shadow.renderer.data;

import java.util.StringTokenizer;

import shadow.image.SFRenderedTexturesSet;
import shadow.image.SFTexture;
import shadow.pipeline.SFPrimitive;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;
import shadow.system.data.SFWritableDataObject;

public class SFTextureData extends SFPrimitive implements SFWritableDataObject{

	private int textureIndex;
	private SFLibraryReference<SFRenderedTexturesSet> reference=new SFLibraryReference<SFRenderedTexturesSet>();
	
	public SFTextureData() {
	}

	public void setTextureIndex(int textureIndex) {
		this.textureIndex = textureIndex;
	}

	public void setReference(String reference) {
		this.reference.setReference(reference);
	}

	public SFLibraryReference<SFRenderedTexturesSet> getReference() {
		return reference;
	}
	
	public SFTexture buildTextureReference() {
		
		final SFTexture texture=new SFTexture(null, textureIndex);
		
		getReference().retrieveDataset(new SFDataCenterListener<SFDataAsset<SFRenderedTexturesSet>>() {
			@Override
			public void onDatasetAvailable(String name, SFDataAsset<SFRenderedTexturesSet> dataset) {
				texture.setTexturesSet(dataset.getResource());
			}
		});
		
		return texture;
	}
	
	@Override
	public SFTextureData clone() {
		return new SFTextureData();
	}
	
	@Override
	public void readFromStream(SFInputStream stream) {
		textureIndex=stream.readByte();
		reference.setReference(stream.readName());
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeByte(textureIndex);
		stream.writeName(reference.getReference());
	}
	
	@Override
	public void setStringValue(String value) {
		StringTokenizer tok=new StringTokenizer(value,":");
		reference.setReference(tok.nextToken());
		if(tok.hasMoreTokens())
			textureIndex=new Integer(tok.nextToken());
	}
	@Override
	public String toStringValue() {
		return reference.getReference()+":";
	}
}
