#ifndef shadow_renderer_SFModel_H_
#define shadow_renderer_SFModel_H_

#include "SFGeometry.h"
#include "SFPipeline.h"
#include "SFProgram.h"
#include "SFResource.h"
#include "SFUpdatable.h"
#include "SFProgramModuleStructures.h"

#include <map>
using namespace std;

namespace sf{

class SFModel : public SFUpdatable {

  	SFProgramModuleStructures transform;

  	SFProgramModuleStructures material;

  	map<string, SFProgram*> programs;

  	SFGeometry* rootGeometry;

  	SFResource resource;

public:
  	SFGeometry* getRootGeometry();

  	SFProgramModuleStructures getMaterialComponent();

  	void setMaterialComponent(SFProgramModuleStructures* material);

  	void setRootGeometry(SFGeometry* rootGeometry);

  	void setTransformComponent(SFProgramModuleStructures transformComponent);

  	SFProgramModuleStructures getTransformComponent();

  	void destroyPrograms();

  	SFProgram* evaluateProgram(SFProgramModuleStructures* light);

  	void cleanPrograms();

  	SFProgram* getProgram(SFProgramModuleStructures* light);

	
  	void update();

  	SFModel* clone();

};

}
#endif
