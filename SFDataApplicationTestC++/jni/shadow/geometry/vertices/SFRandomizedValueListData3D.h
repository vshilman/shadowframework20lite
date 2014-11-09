#ifndef shadow_geometry_vertices_SFRandomizedValueListData3D_H_
#define shadow_geometry_vertices_SFRandomizedValueListData3D_H_

#include "shadow/geometry/vertices/SFArrayListValueList.h"
#include "shadow/geometry/SFValuesList.h"
#include "shadow/math/SFRandomizer.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/math/SFVertex3f.h"
#include "shadow/renderer/data/SFGraphicsAsset.h"
#include "shadow/system/data/SFNamedParametersObject.h"
#include "shadow/system/data/objects/SFInt.h"
#include "shadow/system/data/objects/SFShort.h"

namespace sf{
class SFRandomizedValueListData3D : public SFGraphicsAsset<SFValuesList>{

	SFInt* seed=new SFInt(9000);
	SFShort* size=new SFShort((short)0);

public:
	SFRandomizedValueListData3D() {
		seed=new SFInt(9000);
		size=new SFShort((short)0);
		SFNamedParametersObject* parameters=new SFNamedParametersObject();
		parameters->addObject( size);
		parameters->addObject( seed);
		setData(parameters);
	}

	
	SFValuesList buildResource() {
		vector<SFValuenf> vertices;
		SFRandomizer randomizer=new SFRandomizer(seed->getIntValue());
		for (int i = 0; i < size->getShortValue(); i++) {
			vertices.push_back(SFVertex3f(randomizer.randomFloat(),randomizer.randomFloat(),randomizer.randomFloat()));
		}

		return new SFArrayListValueList(vertices);
	}

	
	void updateResource(SFValuesList<SFValuenf> resource) {

		vector<SFValuenf> vertices;
		SFRandomizer randomizer=new SFRandomizer(seed->getIntValue());
		for (int i = 0; i < size->getShortValue(); i++) {
			vertices.push_back(SFVertex3f(randomizer.randomFloat(),randomizer.randomFloat(),randomizer.randomFloat()));
		}

		SFArrayListValueList* values=(SFArrayListValueList*)resource;
		values->setVertices(vertices);
	}

};

}
#endif
