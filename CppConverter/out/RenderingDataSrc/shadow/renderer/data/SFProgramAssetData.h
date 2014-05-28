#ifndef shadow_renderer_data_H_
#define shadow_renderer_data_H_

#include "shadow/renderer/SFProgramModuleStructures.h"
#include "shadow/system/data.SFDataObjectsList.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFString.h"

namespace sf{
class SFProgramAssetData extends SFGraphicsAsset<SFProgramModuleStructures>{

	SFString program = new  SFString("");
	SFDataObjectsList<SFStructureReferenceDataObject> structures =new SFDataObjectsList<SFStructureReferenceDataObject>(new SFStructureReferenceDataObject());
	SFDataObjectsList<SFTextureData> usedTexture = new SFDataObjectsList<SFTextureData>(new SFTextureData());

	SFProgramAssetData(){
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("program", program);
		parameters.addObject("structures", structures);
		parameters.addObject("textures", usedTexture);
		setData(parameters);
	}

	void setProgram(String programName){
		program.setString(programName);
	}

	
	SFProgramModuleStructures buildResource() {
		SFProgramModuleStructures element=new SFProgramModuleStructures();

		updateResource(element);

		return element;
	}

	
	void updateResource(SFProgramModuleStructures resource) {
		SFProgramModuleStructures element=resource;

		element.setProgram(program.getString());

		//int index=1;

		element.getData().clear();
		for (SFStructureReferenceDataObject structure:structures) {
			element.getData().add(structure.buildReference());
			//System.err.println("Setting the resource !! "+structure.tableData.getDataset()+" "+structure.tableData.elementsSize());
			//if(structure.tableData.getDataset()!=null)
				//	getDataAssetResource().setResource(index, structure.tableData.getDataset().getDataAssetResource());
			//index++;
		}

		//texturesCount=0;
		element.getTextures().clear();
		for (int i = 0; i < usedTexture.size(); i++) {
			element.addTexture(usedTexture.get(i).buildTextureReference());
			//if(usedTexture.get(i).getReference().getDataset()!=null)
			//	getDataAssetResource().setResource(index, usedTexture.get(i).getReference().getDataset().getDataAssetResource());
			//index++;
		}
	}
}
;
}
#endif
