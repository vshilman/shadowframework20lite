#ifndef shadow_geometry_vertices_H_
#define shadow_geometry_vertices_H_

#include "shadow/geometry/SFValuesIterator.h"
#include "shadow/geometry/SFValuesList.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/system/SFResource.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFShortByteField.h"

namespace sf{
class SFVoidVerticesList extends SFGraphicsAsset<SFValuesList<SFValuenf>>{

	SFShortByteField data=new SFShortByteField((short)0);
	SFValuenf nullValue;

	SFVoidVerticesList(){

		SFNamedParametersObject parameters=new SFNamedParametersObject();
		//parameters.addObject("vertexSize", this->vertexSize);
		parameters.addObject("data", data);
		setData(parameters);
	}


	//TODO: is really all this necessary?
	class SFBinaryVListIterator implements SFValuesIterator<SFValuenf>{
		int index=0;


		
		void getNext(SFValuenf write) {
			write.set(nullValue);
			index++;
		}

		
		boolhasNext() {
			return index<data.getByte(1);
		}
	}

	//TODO: should be move to its own file
	class SFBinaryVList implements SFValuesList<SFValuenf>{


		SFBinaryVList() {
			super();
		}

		SFResource resource=new SFResource(0);
		
		SFResource getResource() {
			return resource;
		}
		
		void init() {
			//Nothing special
		}
		
		void destroy() {
			//Nothing special
		}

		
		int getValueSize() {
			return data.getByte(0);
		}
		
		int getSize() {
			return data.getByte(1);
		}
		
		void setValue(int index, SFValuenf read) {
			//vertices.setValue(index, read);
//			int vSize=getVertexSize();
//			for (int j = 0; j < vSize; j++) {
//				vertices.getDataObject().get(vSize*index+j).setFloat(read.get()[j]);
}
		}
		
		int addValue(SFValuenf read) {
			//vertices.addValue(read);
			return getSize();
		}
		
		void getValue(int index, SFValuenf write) {
			write.set(nullValue);
		}

		
		SFValuesIterator<SFValuenf> getIterator() {
			return new SFBinaryVListIterator();
		}
	}

	
	SFValuesList<SFValuenf> buildResource() {
		int size = this->data.getByte(0);

		nullValue=new SFValuenf(size);
		for (int i = 0; i < nullValue.getV().length; i++) {
			nullValue.getV()[i]=0;
		}
		return new SFBinaryVList();
	}

	
	void updateResource(SFValuesList<SFValuenf> resource) {
		// TODO Auto-generated method stub

	}
}
;
}
#endif
