
#include "SFProgramModuleStructures.h"


namespace sf{


	SFProgramModuleStructures::SFProgramModuleStructures() {
	}

	SFProgramModuleStructures::SFProgramModuleStructures(string program) {
		this->program=program;
	}

	SFResource* SFProgramModuleStructures::getResource() {
		return &resource;
	}

	SFProgramModuleStructures* SFProgramModuleStructures::clone(){
		SFProgramModuleStructures* element=new SFProgramModuleStructures(program);
		for(unsigned int i=0;i<data.size();i++){
			element->data.push_back(data[i]);
		}
		for(unsigned int i=0;i<textures.size();i++){
			element->textures.push_back(textures[i]);
		}

  		return element;
	}

  	vector<SFStructureReference*>* SFProgramModuleStructures::getData() {
  		return &data;
	}

  	void SFProgramModuleStructures::addData(SFStructureReference* reference) {
  		this->data.push_back(reference);
  		resource.setResource(this->data.size()-1, reference->getResource());
	}

  	string SFProgramModuleStructures::getProgram() {
  		return program;
	}

  	void SFProgramModuleStructures::setProgram(string program) {
		this->program = program;
	}

  	void SFProgramModuleStructures::addTexture(SFTexture texture) {
  		textures.push_back(texture);
  		resource.setResource(8+this->textures.size()-1, texture.getResource());
	}

	vector<SFTexture> SFProgramModuleStructures::getTextures() {
		return textures;
	}


	void SFProgramModuleStructures::destroy() {
	}


	void SFProgramModuleStructures::init() {
	}

}
