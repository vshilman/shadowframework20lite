#ifndef shadow_renderer_data_H_
#define shadow_renderer_data_H_

#include "shadow/geometry/SFGeometry.h"
#include "shadow/math/SFMatrix3f.h"
#include "shadow/math/SFTransform3f.h"
#include "shadow/math/SFVertex3f.h"
#include "shadow/nodes/SFObjectModel.h"
#include "shadow/renderer/SFNode.h"
#include "shadow/renderer/SFProgramModuleStructures.h"
#include "shadow/renderer/SFTransformResource.h"
#include "shadow/renderer/data.transforms.SFNoTransformData.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFDataAssetObject.h"
#include "shadow/system/data.SFDataset.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFNamedParametersObject.h"

namespace sf{
class SFObjectModelData extends SFDataAsset<SFNode> {

	SFLibraryReference<SFTransformResource> transform=new SFLibraryReference<SFTransformResource>(new SFNoTransformData());

	SFLibraryReference<SFGeometry> rootGeometryReference=new SFLibraryReference<SFGeometry>();

	SFDataAssetObject<SFProgramModuleStructures> transformComponent=new SFDataAssetObject<SFProgramModuleStructures>(new SFProgramAssetData());

	SFDataAssetObject<SFProgramModuleStructures> materialComponent=new SFDataAssetObject<SFProgramModuleStructures>(new SFProgramAssetData());

	SFObjectModelData(){
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("transform", transform);
		parameters.addObject("geometry", rootGeometryReference);
		parameters.addObject("transformComponent", transformComponent);
		parameters.addObject("materialComponent", materialComponent);
		setData(parameters);
	}

	void setGeometry(String geometryName) {
		getRootGeometryReference().setReference(geometryName);
	}

	void setGeometry(SFGraphicsAsset<SFGeometry> geometry) {
		getRootGeometryReference().setDataset(geometry);
	}

	void setTransform(SFDataAsset<SFTransformResource> transform){
		this->transform.setDataset(transform);
	}

//	void addMaterial(SFDataAsset<SFStructureArray> materialLibrary,int materialIndex){
//		addMaterialsStructures( new SFStructureReferenceData(materialLibrary, materialIndex));
}
//	
//	void addMaterial(String materialLibrary,int materialIndex){
//		addMaterialsStructures( new SFStructureReferenceData(materialLibrary, materialIndex));
}

	SFProgramAssetData getTransformComponent(){
		return (SFProgramAssetData)(transformComponent.getDataset());
	}

	SFProgramAssetData getMaterialComponent(){
		return (SFProgramAssetData)(materialComponent.getDataset());
	}

	void setMaterialComponent(
			SFProgramAssetData materialComponent) {
		this->materialComponent.setDataset(materialComponent);
	}

//	void addMaterial(String programComponent){
//		getMaterialsProgramComponents().setString(programComponent);
}

	
	SFDataset generateNewDatasetInstance() {
		return new SFObjectModelData();
	}

	SFObjectModel node;

	SFLibraryReference<SFGeometry> getRootGeometryReference() {
		return rootGeometryReference;
	}

//	SFString getTransformsProgramComponents() {
//		return transformsProgramComponents;
}
//
//	SFString getMaterialsProgramComponents() {
//		return materialsProgramComponents;
}
//	
//	void setMaterialProgramComponent(String material){
//		this->materialsProgramComponents.setString(material);
}
//	
//	void setTransformProgramComponent(String transform){
//		this->transformComponent.getDataset()(dataset)(transform);
}
//
//	SFDataAssetList<SFStructureReference> getMaterialsStructures() {
//		return materialsStructures;
}
//
//	void addMaterialsStructures(SFStructureReferenceData structure) {
//		materialsStructures.add(structure);
}

	
	SFObjectModel buildResource() {
		node=new SFObjectModel();

		SFTransform3f transform=this->transform.getResource();
		SFVertex3f vertex=new SFVertex3f();
		transform.getPosition(vertex);
		node.getTransform().setPosition(vertex);
		SFMatrix3f matrix=new SFMatrix3f();
		transform.getMatrix(matrix);
		node.getTransform().setOrientation(matrix);

		SFGeometry geometry = rootGeometryReference.getResource();

		node.getModel().setRootGeometry(geometry);

		node.getModel().setMaterialComponent(materialComponent.getResource());
		node.getModel().setTransformComponent(transformComponent.getResource());

		getDataAssetResource().setResource(1, this->transform.getDataset().getDataAssetResource());
		getDataAssetResource().setResource(2, this->rootGeometryReference.getDataset().getDataAssetResource());
		getDataAssetResource().setResource(3, ((SFDataAsset<?>)this->materialComponent.getDataset()).getDataAssetResource());
		getDataAssetResource().setResource(4, ((SFDataAsset<?>)this->transformComponent.getDataset()).getDataAssetResource());

		//System.err.println("\trootGeometryReference "+rootGeometryReference.getDataset());

		//getDataAssetResource().setResource(0, rootGeometryReference.getDataset().getDataAssetResource());

		return node;
	}

	
	void updateResource(SFNode node) {

		SFObjectModel model=(SFObjectModel)node;

		SFTransform3f transform=this->transform.getResource();
		SFVertex3f vertex=new SFVertex3f();
		transform.getPosition(vertex);
		node.getTransform().setPosition(vertex);
		SFMatrix3f matrix=new SFMatrix3f();
		transform.getMatrix(matrix);
		node.getTransform().setOrientation(matrix);

		node.getModel().setRootGeometry(rootGeometryReference.getResource());
	}

}
;
}
#endif
