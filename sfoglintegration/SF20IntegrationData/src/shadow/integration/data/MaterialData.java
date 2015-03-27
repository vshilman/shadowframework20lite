package shadow.integration.data;

import sfogl.integration.BitmapTexture;
import sfogl.integration.Material;
import sfogl.integration.Pipeline;
import shadow.math.SFValue;
import shadow.math.SFValuenf;
import shadow.system.SFContext;
import shadow.system.data.SFDataAsset;
import shadow.system.data.SFLibraryReference;
import shadow.system.data.SFLibraryReferenceList;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.formats.SFFixedFloat16;
import shadow.system.data.objects.SFBinaryVertexArrayList;
import shadow.system.data.objects.SFString;

public class MaterialData extends SFDataAsset<Material>{

	public MaterialData() {
		addObject("program", new SFString(""));
		addObject("values", new SFBinaryVertexArrayList<SFFixedFloat16>(new SFFixedFloat16()));
		addObject("textures", new SFLibraryReferenceList<BitmapTexture>(new SFLibraryReference<BitmapTexture>()));
	}
	
	@Override
	public Material createResource(SFContext context,SFNamedParametersObject sfDataObject) {
		
		SFString program =sfDataObject.getDataObject(0);
		SFBinaryVertexArrayList<SFFixedFloat16> values =sfDataObject.getDataObject(1);
		SFLibraryReferenceList<BitmapTexture> textures =sfDataObject.getDataObject(2);
		
		//Wait on this
		Material material=new Material(Pipeline.getPipeline().get(program.getString()));
		int[] valuesSizes=values.getVertexSize();
		SFValue[] values_=new SFValue[valuesSizes.length];
		for (int i = 0; i < valuesSizes.length; i++) {
			values_[i]=new SFValuenf(valuesSizes[i]);
			values.getValue(0, i, values_[i].getV());
		}
		material.setData(values_);
		
		for (int i = 0; i < textures.size(); i++) {
			material.getTextures().add(textures.get(i).getResource(context));
		}
		
		return material;
	}
}
