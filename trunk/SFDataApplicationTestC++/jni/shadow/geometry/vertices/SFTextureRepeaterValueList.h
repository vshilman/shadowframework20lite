#ifndef shadow_geometry_vertices_SFTextureRepeaterValueList_H_
#define shadow_geometry_vertices_SFTextureRepeaterValueList_H_

#include "shadow/geometry/SFValuesList.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/math/SFVertex2f.h"
#include "shadow/renderer/data/SFGraphicsAsset.h"
#include "shadow/system/data/SFLibraryReference.h"
#include "shadow/system/data/SFNamedParametersObject.h"

namespace sf{
class SFTextureRepeaterValueList : public SFGraphicsAsset<SFValuesList>{

	SFLibraryReference<SFValuesList>* vertices;


public:
	SFTextureRepeaterValueList() {
		vertices =
				new SFLibraryReference<SFValuesList>();
		SFNamedParametersObject* parameters=new SFNamedParametersObject();
		parameters->addObject( vertices);
		setData(parameters);
	}

	
	SFValuesList* buildResource() {

		SFValuesList* vertices=this->vertices->getDataset().getResource();
		vector<SFValuenf> newVertices;
		for (int i = 0; i < vertices->getSize(); i++) {
			SFVertex2f* vertex=new SFVertex2f(0.0,0.0);
			vertices->getValue(i, vertex);
			newVertices.push_back(*vertex);
			SFVertex2f* vTemp=new SFVertex2f();
			vTemp->set(*vertex);
			vTemp->setX(vertex->getX()+1);
			newVertices.push_back(*vTemp);
			vTemp=new SFVertex2f();
			vTemp->set(*vertex);
			vTemp->setX(vertex->getX()-1);
			newVertices.push_back(*vTemp);
			vTemp=new SFVertex2f();
			vTemp->set(*vertex);
			vTemp->setY(vertex->getY()+1);
			newVertices.push_back(*vTemp);
			vTemp=new SFVertex2f();
			vTemp->set(*vertex);
			vTemp->setY(vertex->getY()-1);
			newVertices.push_back(*vTemp);
		}
		return new SFArrayListValueList(newVertices);
	}

	
	void updateResource(SFValuesList<SFValuenf> resource) {
		//TODO
	}
};

}
#endif
