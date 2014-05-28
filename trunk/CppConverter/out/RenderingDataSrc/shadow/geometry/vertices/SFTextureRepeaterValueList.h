#ifndef shadow_geometry_vertices_H_
#define shadow_geometry_vertices_H_

#include "java/util/ArrayList.h"

#include "shadow/geometry/SFValuesList.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/math/SFVertex2f.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFNamedParametersObject.h"

namespace sf{
class SFTextureRepeaterValueList extends SFGraphicsAsset<SFValuesList<SFValuenf>>{

	SFLibraryReference<SFValuesList<SFValuenf>> vertices = 
			new SFLibraryReference<SFValuesList<SFValuenf>>();

	SFTextureRepeaterValueList() {
		super();
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("vertices", vertices);
		setData(parameters);
	}

	
	SFValuesList<SFValuenf> buildResource() {

		SFValuesList<SFValuenf> vertices=this->vertices.getDataset().getResource();
		ArrayList<SFValuenf> newVertices=new ArrayList<SFValuenf>();
		for (int i = 0; i < vertices.getSize(); i++) {
			SFVertex2f vertex=new SFVertex2f(0,0);
			vertices.getValue(i, vertex);
			newVertices.add(vertex);
			SFVertex2f vTemp=new SFVertex2f();
			vTemp.set(vertex);
			vTemp.setX(vertex.getX()+1);
			newVertices.add(vTemp);
			vTemp=new SFVertex2f();
			vTemp.set(vertex);
			vTemp.setX(vertex.getX()-1);
			newVertices.add(vTemp);
			vTemp=new SFVertex2f();
			vTemp.set(vertex);
			vTemp.setY(vertex.getY()+1);
			newVertices.add(vTemp);
			vTemp=new SFVertex2f();
			vTemp.set(vertex);
			vTemp.setY(vertex.getY()-1);
			newVertices.add(vTemp);
		}
		return new SFArrayListValueList(newVertices);
	}

	
	void updateResource(SFValuesList<SFValuenf> resource) {
		SFValuesList<SFValuenf> vertices=this->vertices.getDataset().getResource();
		ArrayList<SFValuenf> newVertices=new ArrayList<SFValuenf>();
		for (int i = 0; i < vertices.getSize(); i++) {
			SFVertex2f vertex=new SFVertex2f(0,0);
			vertices.getValue(i, vertex);
			newVertices.add(vertex);
			SFVertex2f vTemp=new SFVertex2f();
			vTemp.set(vertex);
			vTemp.setX(vertex.getX()+1);
			newVertices.add(vTemp);
			vTemp=new SFVertex2f();
			vTemp.set(vertex);
			vTemp.setX(vertex.getX()-1);
			newVertices.add(vTemp);
			vTemp=new SFVertex2f();
			vTemp.set(vertex);
			vTemp.setY(vertex.getY()+1);
			newVertices.add(vTemp);
			vTemp=new SFVertex2f();
			vTemp.set(vertex);
			vTemp.setY(vertex.getY()-1);
			newVertices.add(vTemp);
		}

		SFArrayListValueList values=(SFArrayListValueList)resource;
		values.setVertices(newVertices);
	}
}
;
}
#endif
