#ifndef shadow_renderer_SFProgramModuleStructures_H_
#define shadow_renderer_SFProgramModuleStructures_H_

#include "SFTexture.h"
#include "SFGraphicsResource.h"
#include "SFResource.h"
#include "SFStructureReference.h"

#include <string>
using namespace std;

namespace sf{
class SFProgramModuleStructures : public SFGraphicsResource{

	vector<SFStructureReference*> data;
	vector<SFTexture> textures;
	string program;

	SFResource resource;

public:
	SFProgramModuleStructures();

	SFProgramModuleStructures(string program);

	SFResource* getResource();

	SFProgramModuleStructures* clone();

  	vector<SFStructureReference*>* getData();

  	void addData(SFStructureReference* reference);

  	string getProgram();

  	void setProgram(string program);

  	void addTexture(SFTexture texture);

	vector<SFTexture> getTextures();

	void destroy();

	void init();

};

}
#endif
