package shadow.renderer.data;

import shadow.geometry.SFGeometry;
import shadow.math.SFMatrix3f;
import shadow.math.SFTransform3f;
import shadow.math.SFVertex3f;
import shadow.renderer.SFNode;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFProgramModuleStructures;
import shadow.renderer.data.transforms.SFNoTransformData;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFDataset;
import shadow.system.data.SFNamedParametersObject;

public class SFObjectModelData extends SFDataAsset<SFNode> {

	private SFLibraryReference<SFTransform3f> transform=new SFLibraryReference<SFTransform3f>(new SFNoTransformData());
	
	private SFLibraryReference<SFGeometry> rootGeometryReference=new SFLibraryReference<SFGeometry>();

	protected SFDataAssetObject<SFProgramModuleStructures> transformComponent=new SFDataAssetObject<SFProgramModuleStructures>(new SFProgramAssetData());
	
	protected SFDataAssetObject<SFProgramModuleStructures> materialComponent=new SFDataAssetObject<SFProgramModuleStructures>(new SFProgramAssetData());

	public SFObjectModelData(){
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("transform", transform);
		parameters.addObject("geometry", rootGeometryReference);
		parameters.addObject("transformComponent", transformComponent);
		parameters.addObject("materialComponent", materialComponent);
		setData(parameters);
		setReferences(rootGeometryReference);
	}

	public void setGeometry(String geometryName) {
		getRootGeometryReference().setReference(geometryName);
	}
	
	public void setGeometry(SFDataAsset<SFGeometry> geometry) {
		getRootGeometryReference().setDataset(geometry);
	}

	public void setTransform(SFDataAsset<SFTransform3f> transform){
		this.transform.setDataset(transform);
	}
	
//	public void addMaterial(SFDataAsset<SFStructureArray> materialLibrary,int materialIndex){
//		addMaterialsStructures( new SFStructureReferenceData(materialLibrary, materialIndex));
//	}
//	
//	public void addMaterial(String materialLibrary,int materialIndex){
//		addMaterialsStructures( new SFStructureReferenceData(materialLibrary, materialIndex));
//	}
	
	public SFProgramAssetData getTransformComponent(){
		return (SFProgramAssetData)(transformComponent.getDataset());
	}
	
	public SFProgramAssetData getMaterialComponent(){
		return (SFProgramAssetData)(materialComponent.getDataset());
	}
	
//	public void addMaterial(String programComponent){
//		getMaterialsProgramComponents().setString(programComponent);
//	}

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
	

	public SFLibraryReference<SFGeometry> getRootGeometryReference() {
		return rootGeometryReference;
	}

	
	
//	public SFString getTransformsProgramComponents() {
//		return transformsProgramComponents;
//	}
//
//	public SFString getMaterialsProgramComponents() {
//		return materialsProgramComponents;
//	}
//	
//	public void setMaterialProgramComponent(String material){
//		this.materialsProgramComponents.setString(material);
//	}
//	
//	public void setTransformProgramComponent(String transform){
//		this.transformComponent.getDataset()(dataset)(transform);
//	}
//
//	public SFDataAssetList<SFStructureReference> getMaterialsStructures() {
//		return materialsStructures;
//	}
//
//	public void addMaterialsStructures(SFStructureReferenceData structure) {
//		materialsStructures.add(structure);
//	}
	
	@Override
	protected SFObjectModel buildResource() {
		node=new SFObjectModel();
		
		this.transform.retrieveDataset(new SFDataCenterListener<SFDataAsset<SFTransform3f>>() {
			@Override
			public void onDatasetAvailable(String name, SFDataAsset<SFTransform3f> dataset) {
				SFTransform3f transform=dataset.getResource();
				SFVertex3f vertex=new SFVertex3f();
				transform.getPosition(vertex);
				node.getTransform().setPosition(vertex);
				SFMatrix3f matrix=new SFMatrix3f();
				transform.getMatrix(matrix);
				node.getTransform().setOrientation(matrix);
			}
		});
		
		rootGeometryReference.retrieveDataset(new GeometryListener());
		
		node.getModel().setMaterialComponent(materialComponent.getResource());
		node.getModel().setTransformComponent(transformComponent.getResource());
		
		return node;
	}
	
}
