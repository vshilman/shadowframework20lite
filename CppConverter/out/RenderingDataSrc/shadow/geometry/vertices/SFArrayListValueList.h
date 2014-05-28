#ifndef shadow_geometry_vertices_H_
#define shadow_geometry_vertices_H_

#include "java/util/ArrayList.h"

#include "shadow/geometry/SFValuesIterator.h"
#include "shadow/geometry/SFValuesList.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/system/SFResource.h"

namespace sf{
class SFArrayListValueList  implements SFValuesList<SFValuenf>{

	ArrayList<SFValuenf> vertices=new ArrayList<SFValuenf>();

	SFResource resource=new SFResource(0);


	SFArrayListValueList(ArrayList<SFValuenf> vertices) {
		super();
		this->vertices=vertices;
	}


	SFResource getResource() {
		return resource;
	}

	void setVertices(ArrayList<SFValuenf> vertices) {
		this->vertices = vertices;
	}

	
	int getValueSize() {
		return vertices.get(0).getSize();
	}

	
	void init() {
	}
	
	void destroy() {
	}
	
	int getSize() {
		return vertices.size();
	}
	
	void setValue(int index, SFValuenf read) {
	}
	
	int addValue(SFValuenf read) {
		return getSize();
	}
	
	void getValue(int index, SFValuenf write) {
		write.set(vertices.get(index));
	}

	
	SFValuesIterator<SFValuenf> getIterator() {
		return new SFValuesIterator<SFValuenf>() {
			int index=0;
			
			boolhasNext() {
				return index<vertices.size();
			}

			
			void getNext(SFValuenf write) {
				write.set(vertices.get(index));
				index++;
			}
		}
	}
}
;
}
#endif
