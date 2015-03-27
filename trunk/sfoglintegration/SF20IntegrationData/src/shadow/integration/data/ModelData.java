package shadow.integration.data;

import sfogl.integration.Material;
import sfogl.integration.Mesh;
import sfogl.integration.Model;
import shadow.system.SFContext;
import shadow.system.data.SFDataAsset;
import shadow.system.data.SFLibraryReference;
import shadow.system.data.SFNamedParametersObject;

public class ModelData extends SFDataAsset<Model>{

	public ModelData() {
		setName("Model");
		addObject("material", new SFLibraryReference<Material>());
		addObject("geometry", new SFLibraryReference<Mesh>());
	}
	
	@Override
	public Model createResource(SFContext context,SFNamedParametersObject sfDataObject) {
		
		SFLibraryReference<Material> material =sfDataObject.getDataObject(0);
		SFLibraryReference<Mesh> geometry =sfDataObject.getDataObject(1);
		
		Model model=new Model();
		model.setMaterialComponent(material.getResource(context));
		model.setRootGeometry(geometry.getResource(context));
		
		return model;
	}
}
