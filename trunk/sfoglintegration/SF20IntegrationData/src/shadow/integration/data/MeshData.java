package shadow.integration.data;

import sfogl.integration.ArrayObject;
import sfogl.integration.Mesh;
import shadow.system.SFContext;
import shadow.system.data.SFDataAsset;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFDataAssetObject;

public class MeshData extends SFDataAsset<Mesh>{

	public MeshData() {
		setName("Mesh");
		addObject("mesh", new SFDataAssetObject<ArrayObject>(new ArrayObjectData()));
	}
	
	@Override
	public Mesh createResource(SFContext context,SFNamedParametersObject sfDataObject) {
		
		SFDataAssetObject<ArrayObject> array =sfDataObject.getDataObject(0);
		
		Mesh mesh=new Mesh(array.getDataAsset().getResource(context));
		
		context.getEngine().addInitiable(mesh);
		
		return mesh;
	}
}
