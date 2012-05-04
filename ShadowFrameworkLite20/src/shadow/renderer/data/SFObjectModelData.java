package shadow.renderer.data;

import java.util.LinkedList;
import java.util.List;

import shadow.geometry.SFGeometry;
import shadow.image.SFRenderedTexturesSet;
import shadow.image.SFTexture;
import shadow.math.SFMatrix3f;
import shadow.pipeline.SFStructureArray;
import shadow.renderer.SFBone;
import shadow.renderer.SFNode;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFStructureReference;
import shadow.renderer.SFTextureReference;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFDataset;
import shadow.system.data.objects.SFDataList;
import shadow.system.data.objects.SFDatasetObject;
import shadow.system.data.objects.SFFloat;
import shadow.system.data.objects.SFMatrix3fData;
import shadow.system.data.objects.SFString;
import shadow.system.data.objects.SFVertex3fData;
import shadow.system.data.utils.SFGenericInfoObjectBuilder;

public class SFObjectModelData extends SFDataAsset<SFNode> {


	private SFVertex3fData position=new SFVertex3fData();
	private SFMatrix3fData orientation=new SFMatrix3fData();
	private SFFloat scale=new SFFloat(1);
	private SFLibraryReference<SFDataAsset<SFGeometry>> rootGeometryReference=
			new SFLibraryReference<SFDataAsset<SFGeometry>>();//actually this is the only reference... isn't it
	private SFDataList<SFDatasetObject<SFDataAsset<SFBone>>> nodes=
			new SFDataList<SFDatasetObject<SFDataAsset<SFBone>>>(new SFDatasetObject<SFDataAsset<SFBone>>(null));

	protected SFDataList<SFTextureReferenceData> textures = new SFDataList<SFTextureReferenceData>(new SFTextureReferenceData());
	protected SFDataList<SFString> materialsProgramComponents = new SFDataList<SFString>(new SFString());
	protected SFDataList<SFDatasetObject<SFStructureReferenceData>> materialsStructures = new SFDataList<SFDatasetObject<SFStructureReferenceData>>(new SFDatasetObject<SFStructureReferenceData>(null));

	public SFObjectModelData(){
		super.setData(SFGenericInfoObjectBuilder.generateObjectBuilder(position,orientation,scale,
				rootGeometryReference,textures,nodes,materialsProgramComponents,materialsStructures));;
	}
	
	public SFObjectModelData(String geometryName,float x,float y,float z){
		placeGeometry(geometryName, x, y, z);
	}
	
	public void placeGeometry(String geometryName, float x, float y, float z, SFMatrix3f orientation) {
		setGeometry(geometryName);
		place(x, y, z, orientation);
	}
	
	public void placeGeometry(String geometryName, SFMatrix3f orientation) {
		setGeometry(geometryName);
		place(orientation);
	}

	public void place(SFMatrix3f orientation) {
		this.orientation.getMatrix3f().set(orientation);
	}

	public void placeGeometry(String geometryName, float x, float y, float z) {
		setGeometry(geometryName);
		place(x, y, z);
	}
	
	public void placeGeometryAndScale(String geometryName, float x, float y, float z, float scale) {
		setGeometry(geometryName);
		place(x, y, z);
		setScale(scale);
	}

	public void place(float x, float y, float z, SFMatrix3f orientation) {
		place(x, y, z);
		place(orientation);
	}

	public void setScale(float scale) {
		this.scale.setFloatValue(scale);
	}
	
	public void setOrientation(SFMatrix3f matrix) {
		this.orientation.getMatrix3f().set(matrix);
	}

	public void setGeometry(String geometryName) {
		getRootGeometryReference().setReference(geometryName);
	}
	
	public void setGeometry(SFDataAsset<SFGeometry> geometry) {
		getRootGeometryReference().setDataset(geometry);
	}

	public void place(float x, float y, float z) {
		getPosition().getVertex3f().set3f(x, y, z);
	}
	
	public void addMaterial(String programComponent,SFStructureArrayData materialLibrary,int materialIndex){
		addMaterialsStructures( new SFStructureReferenceData(materialLibrary, materialIndex));
		addMaterialProgram(programComponent);
	}
	
	public void addMaterial(String programComponent,String materialLibrary,int materialIndex){
		addMaterialsStructures( new SFStructureReferenceData(materialLibrary, materialIndex));
		addMaterialProgram(programComponent);
	}

	public void addTexture(String programComponent,String textureName){
		addTexture(0, 0, textureName);
		//Note: available material programs depends upon the programs loaded into the graphics Pipeline.
		addMaterialProgram(programComponent);
	}

	public void addMaterialProgram(String programComponent) {
		getMaterialsProgramComponents().add(new SFString(programComponent));
	}
	
	public void addTexture(String programComponent,String textureName,int textureLevel,int textureIndex){
		addTexture(textureLevel, textureIndex, textureName);
		addMaterialProgram(programComponent);
	}

	public void addTexture(int level,int index,String textureSet){
		SFTextureReferenceData texture=new SFTextureReferenceData();
		texture.getTextureLevel().setIntValue(level);
		texture.getTextureIndex().setIntValue(index);
		texture.getReference().setReference(textureSet);
		textures.add(texture);
	}
	
	public void addMaterial(String programComponent){
		getMaterialsProgramComponents().add(new SFString(programComponent));
	}
	
	@Override
	public SFDataset generateNewDatasetInstance() {
		return new SFObjectModelData();
	}
	
	private SFObjectModel node;
	
	
	private class GeometryListener  implements SFDataCenterListener<SFDataAsset<SFGeometry>>{
		@Override
		public void onDatasetAvailable(String name,
				SFDataAsset<SFGeometry> dataset) {
			node.getModel().setRootGeometry(dataset.getResource());
		}
	}
	
	private class TextureListener  implements SFDataCenterListener<SFDataAsset<SFRenderedTexturesSet>>{
		@Override
		public void onDatasetAvailable(String name,
				SFDataAsset<SFRenderedTexturesSet> dataset) {
			for (int i = 0; i < textures.size(); i++) {
				final int j=i;
				if(name.equals(textures.get(i).getReference().getReference())){
					SFRenderedTexturesSet resource=((SFDataAsset<SFRenderedTexturesSet>)dataset).getResource();
					node.getModel().getTextures().add(new SFTextureReference(textures.get(j).getTextureLevel().getIntValue(), 
							new SFTexture(resource, textures.get(j).getTextureIndex().getIntValue())));

				}
			}
		}
	}
	
	public SFMatrix3fData getOrientation() {
		return orientation;
	}

	public SFVertex3fData getPosition() {
		return position;
	}
	
	public SFFloat getScale(){
		return scale;
	}

	public SFLibraryReference<SFDataAsset<SFGeometry>> getRootGeometryReference() {
		return rootGeometryReference;
	}

	public SFDataList<SFDatasetObject<SFDataAsset<SFBone>>> getNodes() {
		return nodes;
	}
	

	public SFDataList<SFString> getMaterialsProgramComponents() {
		return materialsProgramComponents;
	}

	public SFDataList<SFDatasetObject<SFStructureReferenceData>> getMaterialsStructures() {
		return materialsStructures;
	}
	
	public void addMaterialsStructures(SFStructureReferenceData structure) {
		materialsStructures.getDataObject().add(new SFDatasetObject<SFStructureReferenceData>(structure));
	}
	
	@Override
	protected SFObjectModel buildResource() {
		node=new SFObjectModel();
		
		node.getTransform().setPosition(position.getVertex3f());
		SFMatrix3f matrix=new SFMatrix3f();
		matrix.set(orientation.getMatrix3f());
		matrix.mult(scale.getFloatValue());
		node.getTransform().setOrientation(matrix);
		
		List<String> materialsComponents=new LinkedList<String>();
		for (SFString component:materialsProgramComponents) {
			materialsComponents.add(component.getString());
		}
		node.getModel().getMaterialsComponents().addAll(materialsComponents);
		
		List<SFStructureReference> structures=new LinkedList<SFStructureReference>();
		for (SFDatasetObject<SFStructureReferenceData> structure:materialsStructures) {
			structures.add(structure.getDataset().getResource());
		}
		node.getModel().getMaterialsStructures().addAll(structures);
		
		for (SFDatasetObject<SFDataAsset<SFBone>> model: nodes.getDataObject()) {
			node.addNode(((SFBoneData)model.getDataset()).getResource());
		}
		
		rootGeometryReference.retrieveDataset(new GeometryListener());
		TextureListener textureListener=new TextureListener();
		for (int i = 0; i < textures.size(); i++) {
			textures.get(i).getReference().retrieveDataset(textureListener);
		}
		
		return node;
	}
	
}
