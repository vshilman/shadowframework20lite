#ifndef shadow_geometry_vertices_H_
#define shadow_geometry_vertices_H_

#include "shadow/geometry/SFValuesIterator.h"
#include "shadow/geometry/SFValuesList.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/system/SFResource.h"
#include "shadow/system/data.SFInputStream.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.SFOutputStream.h"
#include "shadow/system/data.objects.SFBinaryVertexList.h"
#include "shadow/system/data.objects.SFGenericFixedFloat.h"

namespace sf{
class SFValueListData<T extends SFGenericFixedFloat> extends SFGraphicsAsset<SFValuesList<SFValuenf>>{

	SFBinaryVertexList<T> vertices;

	SFValueListData(T t){
		vertices=new SFBinaryVertexList<T>(t);
		SFNamedParametersObject parameters=new SFNamedParametersObject(){
			
			void readFromStream(SFInputStream stream) {
				super.readFromStream(stream);
			}

			
			void writeOnStream(SFOutputStream stream) {
				super.writeOnStream(stream);
			}
		}
		//parameters.addObject("vertexSize", this->vertexSize);
		parameters.addObject("vertices", vertices);
		setData(parameters);
	}




	void addVertices(float... data){
		vertices.addVertex(data);
	}

	//TODO: is really all this necessary?
	class SFBinaryVListIterator implements SFValuesIterator<SFValuenf>{
		int index=0;


		
		void getNext(SFValuenf write) {
			vertices.getValue(index, write);
			index++;
		}

		
		boolhasNext() {
			return index<vertices.getSize();
		}
	}

	//TODO: should be move to its own file
	class SFBinaryVList implements SFValuesList<SFValuenf>{

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
			return vertices.getVertexSize();
		}
		
		int getSize() {
			return vertices.getSize();
		}
		
		void setValue(int index, SFValuenf read) {
			vertices.setValue(index, read);
//			int vSize=getVertexSize();
//			for (int j = 0; j < vSize; j++) {
//				vertices.getDataObject().get(vSize*index+j).setFloat(read.get()[j]);
}
		}
		
		int addValue(SFValuenf read) {
			vertices.addValue(read);
			return getSize();
		}
		
		void getValue(int index, SFValuenf write) {
			vertices.getValue(index, write);
		}

		
		SFValuesIterator<SFValuenf> getIterator() {
			return new SFBinaryVListIterator();
		}
	}

	
	SFValuesList<SFValuenf> buildResource() {
		System.err.println("ERRRRRRRRRRRRRR "+this+" "+vertices.getSize());
		return new SFBinaryVList();
	}

	
	void updateResource(SFValuesList<SFValuenf> resource) {
		// TODO Auto-generated method stub

	}
}
;
}
#endif
