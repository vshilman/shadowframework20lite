#ifndef shadow_geometry_vertices_H_
#define shadow_geometry_vertices_H_

#include "java/util/ArrayList.h"

#include "shadow/geometry/SFValuesList.h"
#include "shadow/math/SFRandomizer.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/math/SFVertex2f.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFInt.h"
#include "shadow/system/data.objects.SFShort.h"

namespace sf{
class SFRandomizedValueListData2D extends SFGraphicsAsset<SFValuesList<SFValuenf>>{

	SFInt seed=new SFInt(9000);
	SFShort size=new SFShort((short)0);

	SFRandomizedValueListData2D() {
		super();
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("size", size);
		parameters.addObject("seed", seed);
		setData(parameters);
	}

	
	SFValuesList<SFValuenf> buildResource() {
		ArrayList<SFValuenf> vertices=new ArrayList<SFValuenf>();
		SFRandomizer randomizer=new SFRandomizer(seed.getIntValue());
		for (int i = 0; i < size.getShortValue(); i++) {
			vertices.add(new SFVertex2f(randomizer.randomFloat(),randomizer.randomFloat()));
		}

		return new SFArrayListValueList(vertices);
	}

	
	void updateResource(SFValuesList<SFValuenf> resource) {
		ArrayList<SFValuenf> vertices=new ArrayList<SFValuenf>();
		SFRandomizer randomizer=new SFRandomizer(seed.getIntValue());
		for (int i = 0; i < size.getShortValue(); i++) {
			vertices.add(new SFVertex2f(randomizer.randomFloat(),randomizer.randomFloat()));
		}
		SFArrayListValueList values=(SFArrayListValueList)resource;
		values.setVertices(vertices);
	}
}
;
}
#endif
