package shadow.integration.data;

import shadow.system.data.SFDictionary;
import shadow.system.data.SFLibrary;

public class SFGraphicsDictionary extends SFDictionary{

	public SFGraphicsDictionary(SFLibrary library) {
		super(library);
		addSFDataAsset(new ArrayObjectData());
		addSFDataAsset(new MeshData());
		addSFDataAsset(new ModelData());
		addSFDataAsset(new Transform3fData());
		addSFDataAsset(new NodeData());
		addSFDataAsset(new MaterialData());
		addSFDataAsset(new BitmapTextureData());
	}
}
