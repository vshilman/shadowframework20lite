#ifndef shadow_renderer_H_
#define shadow_renderer_H_

#include "java/util/ArrayList.h"
#include "java/util/LinkedList.h"
#include "java/util/List.h"

#include "shadow/image/SFTexture.h"
#include "shadow/system/SFGraphicsResource.h"
#include "shadow/system/SFResource.h"

class SFProgramModuleStructures implements SFGraphicsResource{

	ArrayList<SFStructureReference> data=new ArrayList<SFStructureReference>();
//	List<SFTexture> textures = new LinkedList<SFTexture>();
//	String program;

//	SFResource resource=new SFResource(12);

//	SFProgramModuleStructures() {
	}


//	SFResource getResource() {
//		return resource;
	}

//	SFProgramModuleStructures clone(){
//		SFProgramModuleStructures element=new SFProgramModuleStructures(program);
//		for (SFStructureReference reference : data) {
//			element.data.add(reference);
		}
//		for (SFTexture reference : textures) {
//			element.textures.add(reference);
		}
//		return element;
	}

//	SFProgramModuleStructures(String program) {
		this->program=program;
	}

//	ArrayList<SFStructureReference> getData() {
//		return data;
	}

//	void addData(SFStructureReference reference) {
//		this->data.add(reference);
//		resource.setResource(this->data.size()-1, reference.getResource());
	}

//	String getProgram() {
//		return program;
	}

//	void setProgram(String program) {
		this->program = program;
	}

//	void addTexture(SFTexture texture) {
//		textures.add(texture);
//		resource.setResource(8+this->textures.size()-1, texture.getResource());
	}

//	List<SFTexture> getTextures() {
//		return textures;
	}

	
//	void destroy() {
//		//TODO !!!
	}

	
//	void init() {	
	}
}
;
}
#endif
