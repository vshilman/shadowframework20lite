package shadow.image.data;

import shadow.image.SFFilteredRenderedTexture;
import shadow.image.SFImageFormat;
import shadow.image.SFPipelineTexture;
import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.image.SFRenderedTexturesSet;
import shadow.image.SFTextureDataToken;
import shadow.renderer.SFProgramModuleStructures;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFDataAssetObject;
import shadow.renderer.data.SFDataObjectsList;
import shadow.renderer.data.SFProgramAssetData;
import shadow.system.data.SFDataset;
import shadow.system.data.SFNamedParametersObject;

public class SFFilteredRenderedTextureData extends SFDataAsset<SFRenderedTexturesSet>{
	
	private SFDataObjectsList<SFTextureDataObject> textures =  new SFDataObjectsList<SFTextureDataObject>(new SFTextureDataObject());

	protected SFDataAssetObject<SFProgramModuleStructures> lightComponent=new SFDataAssetObject<SFProgramModuleStructures>(new SFProgramAssetData());
	protected SFDataAssetObject<SFProgramModuleStructures> materialComponent=new SFDataAssetObject<SFProgramModuleStructures>(new SFProgramAssetData());
	
	public SFFilteredRenderedTextureData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("textures", textures);
		parameters.addObject("lightComponent", lightComponent);
		parameters.addObject("materialComponent", materialComponent);
		setData(parameters);
	}

	@Override
	public SFDataset generateNewDatasetInstance() {
		return new SFFilteredRenderedTextureData();
	}
	
	private SFFilteredRenderedTexture renderedTexture;

	public SFProgramAssetData getLightComponent() {
		return (SFProgramAssetData)(lightComponent.getDataset());
	}

	public SFProgramAssetData getMaterialComponent() {
		return (SFProgramAssetData)(materialComponent.getDataset());
	}

	@Override
	protected SFRenderedTexturesSet buildResource() {
		//this.listener=listener;
		
		renderedTexture=new SFFilteredRenderedTexture();
	
		renderedTexture.setMaterialComponent(materialComponent.getResource());
		
		SFPipelineTexture[] textures=new SFPipelineTexture[this.textures.size()];
		for (int i = 0; i < textures.length; i++) {
			textures[i]=this.textures.get(i).getTexture();
		}
		renderedTexture.setTextures(textures);

		renderedTexture.setLightComponent(lightComponent.getResource());
		
		return renderedTexture;
	}
	
	public void addOutputTexture(int width, int height, SFImageFormat format,
			Filter filters, WrapMode wrapS, WrapMode wrapT){
		addOutputTexture(new SFTextureDataToken(width, height, format, filters, wrapS, wrapT));
	}
	
	public void addOutputTexture(SFPipelineTexture texture){
		SFTextureDataObject dataObject=new SFTextureDataObject();
		dataObject.setTexture(texture);
		this.textures.add(dataObject);
	}
	

	
}
