#ifndef shadow_geometry_vertices_SFRandomizedValueListData2D_H_
#define shadow_geometry_vertices_SFRandomizedValueListData2D_H_


#include "shadow/geometry/vertices/SFArrayListValueList.h"
#include "shadow/geometry/SFValuesList.h"
#include "shadow/math/SFRandomizer.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/math/SFVertex2f.h"
#include "shadow/renderer/data/SFGraphicsAsset.h"
#include "shadow/system/data/SFNamedParametersObject.h"
#include "shadow/system/data/objects/SFInt.h"
#include "shadow/system/data/objects/SFShort.h"

namespace sf{
class SFRandomizedValueListData2D : public SFGraphicsAsset<SFValuesList>{

	SFInt* seed=new SFInt(9000);
	SFShort* size=new SFShort((short)0);

public:
	SFRandomizedValueListData2D() {
		seed=new SFInt(9000);
		size=new SFShort((short)0);
		SFNamedParametersObject* parameters=new SFNamedParametersObject();
		parameters->addObject(size);
		parameters->addObject(seed);
		setData(parameters);
	}


	~SFRandomizedValueListData2D(){
		delete seed;
		delete size;
	}

	SFValuesList* buildResource() {
		vector<SFValuenf> vertices;
		SFRandomizer* randomizer=new SFRandomizer(seed->getIntValue());
		for (int i = 0; i < size->getShortValue(); i++) {
			vertices.push_back(SFVertex2f(randomizer->randomFloat(),randomizer->randomFloat()));
		}
		SFArrayListValueList* values;
		values=new SFArrayListValueList(vertices);
		return values;
	}

	
	void updateResource(SFValuesList* resource) {
		vector<SFValuenf> vertices;
		SFRandomizer* randomizer=new SFRandomizer(seed->getIntValue());
		for (int i = 0; i < size->getShortValue(); i++) {
			vertices.push_back(SFVertex2f(randomizer->randomFloat(),randomizer->randomFloat()));
		}
		SFArrayListValueList* values=(SFArrayListValueList)resource;
		values->setVertices(vertices);
	}

};

}
#endif
