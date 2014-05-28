#ifndef shadow_tmp_H_
#define shadow_tmp_H_

#include "shadow/geometry/SFValuesIterator.h"
#include "shadow/geometry/SFValuesList.h"
#include "shadow/geometry/data.SFFixedFloat16.h"
#include "shadow/math/SFTransform3f.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/system/SFResource.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFBinaryDataList.h"


//TODO : DROP!!!

namespace sf{
class SFRigidTransform3FixedListData extends SFGraphicsAsset<SFValuesList<SFTransform3f>>{

	SFBinaryDataList<SFFixedFloat16> vertices=
		new SFBinaryDataList<SFFixedFloat16>(new SFFixedFloat16());

	SFRigidTransform3FixedListData(){
		prepare();
	}

	void prepare() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("vertices", vertices);
		setData(parameters);
	}

	class SFBinarySFTransform3fListIterator implements SFValuesIterator<SFTransform3f>{
		int index=0;

		
		void getNext(SFTransform3f write) {
			float m00=vertices.getDataObject().get(7*index).getFloat();
			float m01=vertices.getDataObject().get(7*index+1).getFloat();
			float m10=vertices.getDataObject().get(7*index+2).getFloat();
			float m11=vertices.getDataObject().get(7*index+3).getFloat();
			float x=vertices.getDataObject().get(7*index+4).getFloat();
			float y=vertices.getDataObject().get(7*index+5).getFloat();
			float z=vertices.getDataObject().get(7*index+6).getFloat();
			writeTransform(write, m00, m01, m10, m11, x, y, z);
			index+=7;
		}

		
		boolhasNext() {
			return index<vertices.elementsSize();
		}
	}

	class SFBinaryVertex3fList implements SFValuesList<SFTransform3f>{

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
			// TODO Auto-generated method stub
			return 0;
		}
		
		int getSize() {
			return vertices.elementsSize()/3;
		}
		
		void setValue(int index, SFTransform3f read) {
			vertices.getDataObject().get(7*index).setFloat(read.getV()[0]);
			vertices.getDataObject().get(7*index+1).setFloat(read.getV()[1]);
			vertices.getDataObject().get(7*index+2).setFloat(read.getV()[3]);
			vertices.getDataObject().get(7*index+3).setFloat(read.getV()[4]);
			vertices.getDataObject().get(7*index+4).setFloat(read.getV()[9]);
			vertices.getDataObject().get(7*index+5).setFloat(read.getV()[10]);
			vertices.getDataObject().get(7*index+6).setFloat(read.getV()[11]);
		}
		
		int addValue(SFTransform3f read) {
			vertices.getDataObject().add(new SFFixedFloat16(read.getV()[0]));
			vertices.getDataObject().add(new SFFixedFloat16(read.getV()[1]));
			vertices.getDataObject().add(new SFFixedFloat16(read.getV()[3]));
			vertices.getDataObject().add(new SFFixedFloat16(read.getV()[4]));
			vertices.getDataObject().add(new SFFixedFloat16(read.getV()[9]));
			vertices.getDataObject().add(new SFFixedFloat16(read.getV()[10]));
			vertices.getDataObject().add(new SFFixedFloat16(read.getV()[11]));
			return getSize();
		}
		
		void getValue(int index, SFTransform3f write) {
			float m00=vertices.getDataObject().get(7*index).getFloat();
			float m01=vertices.getDataObject().get(7*index+1).getFloat();
			float m10=vertices.getDataObject().get(7*index+2).getFloat();
			float m11=vertices.getDataObject().get(7*index+3).getFloat();
			float x=vertices.getDataObject().get(7*index+4).getFloat();
			float y=vertices.getDataObject().get(7*index+5).getFloat();
			float z=vertices.getDataObject().get(7*index+6).getFloat();
			writeTransform(write, m00, m01, m10, m11, x, y, z);
		}

		
		SFValuesIterator<SFTransform3f> getIterator() {
			return new SFBinarySFTransform3fListIterator();
		}
	}

	void add(SFTransform3f vertex){
		vertices.getDataObject().add(new SFFixedFloat16(vertex.getV()[0]));
		vertices.getDataObject().add(new SFFixedFloat16(vertex.getV()[1]));
		vertices.getDataObject().add(new SFFixedFloat16(vertex.getV()[3]));
		vertices.getDataObject().add(new SFFixedFloat16(vertex.getV()[4]));
		vertices.getDataObject().add(new SFFixedFloat16(vertex.getV()[9]));
		vertices.getDataObject().add(new SFFixedFloat16(vertex.getV()[10]));
		vertices.getDataObject().add(new SFFixedFloat16(vertex.getV()[11]));
	}

	
	SFValuesList<SFTransform3f> buildResource() {
		return new SFBinaryVertex3fList();
	}

	
	void updateResource(SFValuesList<SFTransform3f> resource) {

	}

	static void writeTransform(SFTransform3f transform,float m00,float m01,float m10,float m11,
			float x,float y,float z){
		float m02=(float)(Math.sqrt(1-m00*m00-m01*m01));
		float m12=(float)(Math.sqrt(1-m01*m10-m11*m11));

		//m10*m10+m11*m11+m12*m12=1 unity
		//m00*m10+m01*m11+m02*m12=0 orthogonality

		float m20=m01*m12-m11*m02;
		float m21=m02*m10-m12*m00;
		float m22=m00*m11-m10*m01;

		float[] values=transform.getV();
		values[0]=m00;
		values[1]=m10;
		values[2]=m20;
		values[3]=m01;
		values[4]=m11;
		values[5]=m21;
		values[6]=m02;
		values[7]=m12;
		values[8]=m22;
		values[9]=x;
		values[10]=y;
		values[11]=z;
	}
}

;
}
#endif
