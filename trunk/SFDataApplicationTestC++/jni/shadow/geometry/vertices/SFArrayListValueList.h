#ifndef shadow_geometry_vertices_SFArrayListValueList_H_
#define shadow_geometry_vertices_SFArrayListValueList_H_

#include "shadow/geometry/SFValuesIterator.h"
#include "shadow/geometry/SFValuesList.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/system/SFResource.h"

namespace sf{
class SFArrayListValueList : public SFValuesList{

	vector<SFValuenf> vertices;

	SFResource resource;

public:
	SFArrayListValueList(vector<SFValuenf> vertices){
		this->vertices=vertices;
	}

	void setVertices(vector<SFValuenf> vertices){
		this->vertices=vertices;
	}

	SFResource getResource() {
		return resource;
	}
	
	int getValueSize() {
		return vertices[0].getSize();
	}

	
	void init() {
	}
	
	void destroy() {
	}
	
	int getSize() {
		return vertices.size();
	}
	
	void setValue(int index, SFValuenf* read) {
	}
	
	int addValue(SFValuenf* read) {
		return getSize();
	}
	
	void getValue(int index, SFValuenf* write) {
		write->set(vertices.at(index));
	}

    class SFArrayListValueListIterator : public SFValuesIterator{
		int index;
		vector<SFValuenf>* vertices;

	public:
		SFArrayListValueListIterator(vector<SFValuenf>* vertices){
			index=0;
			this->vertices=vertices;
		}

		bool hasNext() {
			return index<vertices->size();
		}


		void getNext(SFValuenf* write) {
			write->set(vertices->at(index));
			index++;
		}
	};

	
	SFValuesIterator* getIterator() {
		return new SFArrayListValueListIterator(&vertices);
	}
};

}
#endif
