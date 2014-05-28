#ifndef shadow_geometry_vertices_H_
#define shadow_geometry_vertices_H_

#include "java/util/ArrayList.h"
#include "java/util/StringTokenizer.h"

#include "shadow/geometry/SFValuesIterator.h"
#include "shadow/geometry/SFValuesList.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/renderer/data.SFGraphicsAsset.h"
#include "shadow/system/SFException.h"
#include "shadow/system/SFResource.h"
#include "shadow/system/data.SFCharsetObjectList.h"
#include "shadow/system/data.SFInputStream.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.SFOutputStream.h"
#include "shadow/system/data.objects.SFPrimitiveType.h"

namespace sf{
class SFParametricValuesList  extends SFGraphicsAsset<SFValuesList<SFValuenf>>{

	SFParametricData vertices;

	SFParametricValuesList(){
		vertices=new SFParametricData();
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("vertices", vertices);
		setData(parameters);
	}


	//TODO: is really all this necessary?
	class SFParametricVListIterator implements SFValuesIterator<SFValuenf>{
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
	class SFParametricVList implements SFValuesList<SFValuenf>{

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
			return vertices.valueSize;
		}
		
		int getSize() {
			return vertices.getSize();
		}
		
		void setValue(int index, SFValuenf read) {
			//TODO : no, setValue is not required on Viewers!!
		}
		
		int addValue(SFValuenf read) {
			//vertices.addValue(read);
			throw new SFException("SFParametricValuesList can be edited only in the xml way atm");
		}
		
		void getValue(int index, SFValuenf write) {
			vertices.getValue(index, write);
		}

		
		SFValuesIterator<SFValuenf> getIterator() {
			return new SFParametricVListIterator();
		}
	}

	
	SFValuesList<SFValuenf> buildResource() {
		return new SFParametricVList();
	}

	
	void updateResource(SFValuesList<SFValuenf> resource) {
		// TODO Auto-generated method stub

	}

	class SFParametricData extends SFPrimitiveType implements
					SFCharsetObjectList {

		int valueSize=0;
		ArrayList<String> stringsList=new ArrayList<String>();
		ArrayList<Short> dataList=new ArrayList<Short>();
		String[] stringsVector=new String[0];
		short[] dataVector=new short[0];

		void finalize(){
			if(stringsList.size()>0){
				stringsVector=stringsList.toArray(new String[0]);
				stringsList.clear();
				dataVector=new short[dataList.size()];
				for (int i = 0; i < dataVector.length; i++) {
					dataVector[i]=dataList.get(i);
				}
				dataList.clear();
			}
		}

		void getValue(int index,SFValuenf value){
			for (int i = 0; i < valueSize; i++) {
				String vString=getValue(index*valueSize+i);
				try {
					float fValue=new Float(vString);
					value.getV()[i]=fValue;
				}
					value.getV()[i]=SFVerticesParameters.getParameters().getValue(vString);
				}
			}
		}

		void addValue(String value){
			int indexof=stringsList.indexOf(value);
			if(indexof==-1){
				indexof=stringsList.size();
				stringsList.add(value);
			}
			dataList.add((short)indexof);
		}

		String getValue(int index){
			return stringsVector[dataVector[index]];
		}

		
		void addCharSetObjects(String value) {

			StringTokenizer tokenizer=new StringTokenizer(value,"(,)",false);

			int count = 0;
			while (tokenizer.hasMoreTokens()) {
				String token=tokenizer.nextToken();
				addValue(token);
				count++;
			}
			valueSize=count;
		}

		
		String getCharSetObjectString(int index) {
			finalize();
			String data="(";
			for (int i = 0; i < valueSize; i++) {
				if(i!=0){
					data=data+","+getValue(index*valueSize+i);
				}
					data=data+getValue(index*valueSize);
				}
			}
			return data+")";
		}

		
		int getSize() {
			return dataVector.length/valueSize;
		}

		
		void readFromStream(SFInputStream stream) {
			this->valueSize=stream.readByte();
			this->stringsVector=new String[stream.readShort()];
			for (int i = 0; i < stringsVector.length; i++) {
				stringsVector[i]=stream.readString();
			}
			short dataVectorLength=stream.readShort();
			this->dataVector=stream.readShorts(dataVectorLength);
		}

		
		void writeOnStream(SFOutputStream stream) {
			finalize();
			stream.writeByte((short)valueSize);
			stream.writeShort((short)stringsVector.length);
			for (int i = 0; i < stringsVector.length; i++) {
				stream.writeString(stringsVector[i]);
			}
			stream.writeShort((short)dataVector.length);
			stream.writeShorts(dataVector);
		}

		
		SFPrimitiveType clone() {
			return new SFParametricData();
		}

	}

}
;
}
#endif
