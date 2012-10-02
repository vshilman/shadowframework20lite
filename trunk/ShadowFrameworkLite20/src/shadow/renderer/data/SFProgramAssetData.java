package shadow.renderer.data;

import shadow.renderer.SFProgramModuleStructures;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFString;

public class SFProgramAssetData extends SFDataAsset<SFProgramModuleStructures>{

	protected SFString program = new  SFString("");
	protected SFDataObjectsList<SFStructureReferenceDataObject> structures =new SFDataObjectsList<SFStructureReferenceDataObject>(new SFStructureReferenceDataObject());
	private SFDataObjectsList<SFTextureData> usedTexture = new SFDataObjectsList<SFTextureData>(new SFTextureData());

	public SFProgramAssetData(){
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("program", program);
		parameters.addObject("structures", structures);
		parameters.addObject("textures", usedTexture);
		setData(parameters);
	}
	
	public void setProgram(String programName){
		program.setString(programName);
	}

	public void addStructure(String tableName,int index) {
		SFStructureReferenceDataObject object=new SFStructureReferenceDataObject();
		object.setRefIndex(index);
		object.setTableName(tableName);
		this.structures.add(object);
	}
	

	public void addTexture(String textureName) {
		SFTextureData object=new SFTextureData();
		object.getReference().setReference(textureName);
		this.usedTexture.add(object);
	}
	
	public void addTexture(int index, String textureSet) {
		SFTextureData texture = new SFTextureData();
		texture.setTextureIndex(index);
		texture.getReference().setReference(textureSet);
		usedTexture.add(texture);
	}
	
	@Override
	protected SFProgramModuleStructures buildResource() {
		SFProgramModuleStructures element=new SFProgramModuleStructures();
		
		element.setProgram(program.getString());
		
		for (SFStructureReferenceDataObject structure:structures) {
			element.getData().add(structure.buildReference());
		}
		
		//texturesCount=0;
		for (int i = 0; i < usedTexture.size(); i++) {
			element.getTextures().add(usedTexture.get(i).buildTextureReference());
		}
		
		return element;
	}
}