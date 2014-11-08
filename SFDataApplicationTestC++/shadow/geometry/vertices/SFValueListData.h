#ifndef shadow_geometry_vertices_SFValueListData_H_
#define shadow_geometry_vertices_SFValueListData_H_

#include "shadow/geometry/SFValuesIterator.h"
#include "shadow/geometry/SFValuesList.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/renderer/data/SFGraphicsAsset.h"
#include "shadow/system/SFResource.h"
#include "shadow/system/data/SFInputStream.h"
#include "shadow/system/data/SFNamedParametersObject.h"
#include "shadow/system/data/SFOutputStream.h"
#include "shadow/system/data/objects/SFBinaryVertexList.h"
#include "shadow/system/data/objects/SFGenericFixedFloat.h"

namespace sf{
class SFValueListData : public SFGraphicsAsset<SFValuesList>{

	SFBinaryVertexList<SFGenericFixedFloat>* vertices;

	SFValueListData(SFGenericFixedFloat* t){
		vertices=new SFBinaryVertexList<SFGenericFixedFloat>(t);
		SFNamedParametersObject* parameters=new SFNamedParametersObject();
		//parameters.addObject("vertexSize", this->vertexSize);
		parameters->addObject( vertices);
		setData(parameters);
	}

	//TODO: is really all this necessary?

	class SFBinaryVListIterator : public SFValuesIterator{
		int index;

		SFBinaryVertexList<SFGenericFixedFloat>* vertices;
	public:
		SFBinaryVListIterator(SFBinaryVertexList<SFGenericFixedFloat>* vertices){
			this->vertices=vertices;
			index=0;
		}
		
		void getNext(SFValuenf* write) {
			vertices->getValue(index, write);
			index++;
		}

		
		bool hasNext() {
			return index<vertices->getSize();
		}
	};

	//TODO: should be move to its own file
	class SFBinaryVList : public SFValuesList{

		SFResource resource;

		SFBinaryVertexList<SFGenericFixedFloat>* vertices;
	public:
		SFBinaryVList(SFBinaryVertexList<SFGenericFixedFloat>* vertices){
			this->vertices=vertices;
		}
		
		SFResource* getResource() {
			return &resource;
		}
		
		void init() {
			//Nothing special
		}
		
		void destroy() {
			//Nothing special
		}

		
		int getValueSize() {
			return vertices->getVertexSize();
		}
		
		int getSize() {
			return vertices->getSize();
		}
		
		void setValue(int index, SFValuenf* read) {
			//Not Necessary!!
			//vertices->setValue(index, read);
		}
		
		int addValue(SFValuenf* read) {
			//Not necessary!!
			//vertices->addValue(read);
			return getSize();
		}
		
		void getValue(int index, SFValuenf* write) {
			vertices->getValue(index, write);
		}

		
		SFValuesIterator getIterator() {
			return new SFBinaryVListIterator(vertices);
		}
	};

	
	SFValuesList* buildResource() {
		return new SFBinaryVList(vertices);
	}

	
	void updateResource(SFValuesList* resource) {
		// TODO Auto-generated method stub

	}

};

}
#endif
