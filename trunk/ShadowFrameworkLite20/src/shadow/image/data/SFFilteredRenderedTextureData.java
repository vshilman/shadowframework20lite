package shadow.image.data;

import java.util.LinkedList;
import java.util.List;

import shadow.image.SFFilteredRenderedTexture;
import shadow.image.SFImageFormat;
import shadow.image.SFPipelineTexture;
import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.image.SFRenderedTexturesSet;
import shadow.image.SFTexture;
import shadow.image.SFTextureDataToken;
import shadow.renderer.SFTextureReference;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFLibraryReference;
import shadow.renderer.data.SFStructureReferenceData;
import shadow.renderer.data.SFTextureReferenceData;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFDataset;
import shadow.system.data.objects.SFDataList;
import shadow.system.data.objects.SFDatasetObject;
import shadow.system.data.objects.SFString;
import shadow.system.data.utils.SFGenericInfoObjectBuilder;

public class SFFilteredRenderedTextureData extends SFDataAsset<SFRenderedTexturesSet> implements SFDataCenterListener<SFDataAsset<SFRenderedTexturesSet>>{
	
	private SFString lightStep = new SFString("");
	private SFDataList<SFTextureReferenceData> usedTexture = new SFDataList<SFTextureReferenceData>(new SFTextureReferenceData());
	
	private SFDataList<SFTextureDataObject> textures =  new SFDataList<SFTextureDataObject>(new SFTextureDataObject());
	private SFDataList<SFString> materialsProgramComponents = new SFDataList<SFString>(new SFString(""));
	private SFDataList<SFLibraryReference<SFStructureReferenceData>> materialsStructures=
			new SFDataList<SFLibraryReference<SFStructureReferenceData>>(new SFLibraryReference<SFStructureReferenceData>());
	
	public SFFilteredRenderedTextureData() {
		setData(SFGenericInfoObjectBuilder.generateObjectBuilder(textures,lightStep,usedTexture,materialsProgramComponents,materialsStructures));
	}

	@Override
	public SFDataset generateNewDatasetInstance() {
		return new SFFilteredRenderedTextureData();
	}
	
	private SFFilteredRenderedTexture renderedTexture;
	
	@Override
	public void onDatasetAvailable(String name, SFDataAsset<SFRenderedTexturesSet> dataset) {

		for (int i = 0; i < usedTexture.size(); i++) {
			final int j=i;
			if(name.equals(usedTexture.get(i).getReference().getReference())){
				SFRenderedTexturesSet resource=dataset.getResource();
				renderedTexture.getTextures().add(new SFTextureReference(usedTexture.get(j).getTextureLevel().getIntValue(), 
						new SFTexture(resource, usedTexture.get(j).getTextureIndex().getIntValue())));

			}
		}
	}
	
	public class SFStructureReferenceListener implements SFDataCenterListener<SFStructureReferenceData>{
		@Override
		public void onDatasetAvailable(String name,
				SFStructureReferenceData dataset) {
			renderedTexture.getMaterialsStructures().add(dataset.getResource());
		}
	}

	@Override
	protected SFRenderedTexturesSet buildResource() {
		//this.listener=listener;
		
		renderedTexture=new SFFilteredRenderedTexture();
	
		List<String> materialsComponents=new LinkedList<String>();
		for (SFString component:materialsProgramComponents) {
			materialsComponents.add(component.getString());
		}
		renderedTexture.getMaterialsComponents().addAll(materialsComponents);
		
		for (SFLibraryReference<SFStructureReferenceData> structure:materialsStructures) {
			structure.retrieveDataset(new SFStructureReferenceListener());
		}
		
		SFPipelineTexture[] textures=new SFPipelineTexture[this.textures.size()];
		for (int i = 0; i < textures.length; i++) {
			textures[i]=this.textures.get(i).getTexture();
		}
		renderedTexture.setTextures(textures);
		
		renderedTexture.setLightStep(lightStep.getString());
		
		//texturesCount=0;
		for (int i = 0; i < usedTexture.size(); i++) {
			usedTexture.get(i).getReference().retrieveDataset(this);
		}
		
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
	
	
	
	public void addMaterialsStructures(SFStructureReferenceData structure) {
		materialsStructures.getDataObject().add(new SFLibraryReference<SFStructureReferenceData>(structure));
	}
	
	public void addMaterial(String programComponent,String materialLibrary,int materialIndex){
		addMaterialsStructures( new SFStructureReferenceData(materialLibrary, materialIndex));
		getMaterialsProgramComponents().add(new SFString(programComponent));
	}

	public void setLightStep(String lightStep){
		this.lightStep.setString(lightStep);
	}
	
	public void addTexture(String programComponent,String textureName){
		addTexture(0, 0, textureName);
		//Note: available material programs depends upon the programs loaded into the graphics Pipeline.
		getMaterialsProgramComponents().add(new SFString(programComponent));
	}
	
	public void addTexture(String programComponent,String textureName,int textureLevel,int textureIndex){
		addTexture(0, 0, textureName);
		//Note: available material programs depends upon the programs loaded into the graphics Pipeline.
		getMaterialsProgramComponents().add(new SFString(programComponent));
	}

	public void addTexture(int level, int index, String textureSet) {
		SFTextureReferenceData texture = new SFTextureReferenceData();
		texture.getTextureLevel().setIntValue(level);
		texture.getTextureIndex().setIntValue(index);
		texture.getReference().setReference(textureSet);
		usedTexture.add(texture);
	}

	public SFDataList<SFString> getMaterialsProgramComponents() {
		return materialsProgramComponents;
	}

	public SFDataList<SFLibraryReference<SFStructureReferenceData>> getMaterialsStructures() {
		return materialsStructures;
	}
	
	public SFString getLightsStep(){
		return lightStep;
	}
}
