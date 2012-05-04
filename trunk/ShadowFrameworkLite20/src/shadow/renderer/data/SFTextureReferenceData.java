package shadow.renderer.data;

import shadow.image.SFRenderedTexturesSet;
import shadow.system.data.objects.SFCompositeDataArray;
import shadow.system.data.objects.SFInt;

public class SFTextureReferenceData extends SFCompositeDataArray{

	private SFInt textureLevel;
	private SFInt textureIndex;
	private SFLibraryReference<SFDataAsset<SFRenderedTexturesSet>> reference;
	
	@Override
	public void generateData() {
		reference=new SFLibraryReference<SFDataAsset<SFRenderedTexturesSet>>();
		textureLevel=new SFInt(0);
		textureIndex=new SFInt(0);
		addDataObject(textureIndex);
		addDataObject(textureLevel);
		addDataObject(reference);
	}
	
	public SFInt getTextureLevel() {
		return textureLevel;
	}

	public SFInt getTextureIndex() {
		return textureIndex;
	}

	public SFLibraryReference<SFDataAsset<SFRenderedTexturesSet>> getReference() {
		return reference;
	}

	@Override
	public SFCompositeDataArray clone() {
		return new SFTextureReferenceData();
	}
	
}
