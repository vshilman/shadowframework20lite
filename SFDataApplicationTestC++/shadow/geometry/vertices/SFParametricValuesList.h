#ifndef shadow_geometry_vertices_SFParametricValuesList_H_
#define shadow_geometry_vertices_SFParametricValuesList_H_

#include "shadow/geometry/SFValuesIterator.h"
#include "shadow/geometry/SFValuesList.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/renderer/data/SFGraphicsAsset.h"
#include "shadow/system/SFResource.h"
#include "shadow/system/data/SFInputStream.h"
#include "shadow/system/data/SFNamedParametersObject.h"
#include "shadow/system/data/SFOutputStream.h"
#include "shadow/system/data/objects/SFPrimitiveType.h"

#include <cstdlib>

namespace sf{
class SFParametricValuesList : public SFGraphicsAsset<SFValuesList>{


	class SFParametricData : public SFPrimitiveType {

		//vector<string> stringsList;
		//vector<short> dataList;
		string* stringsVector;
		short* dataVector;
		short stringsVectorLength;
		short dataVectorLength;

	public:
		int valueSize;
		SFParametricData();

		/*void finalize(){
			if(stringsList.size()>0){
				stringsVector=new string[stringsList.size()];//stringsList.toArray(new String[0]);
				for(unsigned int i=0;i<stringsList.size();i++){
					stringsVector[i]=stringsList[i];
				}
				stringsVectorLength=stringsList.size();
				stringsList.clear();
				dataVector=new short[dataList.size()];
				for (int i = 0; i < dataList.size(); i++) {
					dataVector[i]=dataList[i];
				}
				dataVectorLength=dataList.size();
				dataList.clear();
			}
		}*/

		string getValue(int index);

		void getValue(int index,SFValuenf* value);

		int getSize();

		void readFromStream(SFInputStream* stream);


		/*void writeOnStream(SFOutputStream stream) {
			finalize();
			stream.writeByte((short)valueSize);
			stream.writeShort((short)stringsVector.length);
			for (int i = 0; i < stringsVector.length; i++) {
				stream.writeString(stringsVector[i]);
			}
			stream.writeShort((short)dataVector.length);
			stream.writeShorts(dataVector);
		}*/


		SFPrimitiveType* clone();

	};


	//TODO: is really all this necessary?
	class SFParametricVListIterator : public SFValuesIterator{
		int index;
		SFParametricData* vertices;

	public:

		SFParametricVListIterator(SFParametricData* vertices);

		void getNext(SFValuenf* write);

		bool hasNext();
	};

	//TODO: should be move to its own file
	class SFParametricVList : public SFValuesList{

		SFParametricData* vertices;
		SFResource resource;
		
	public:
		SFParametricVList(SFParametricData* vertices);

		SFResource* getResource();
		
		void init();
		
		void destroy();
		
		int getValueSize();
		
		int getSize();
		
		void setValue(int index, SFValuenf read);
		
		void setValue(int index,SFValuenf* read);

		int addValue(SFValuenf* write);

		int addValue(SFValuenf read);
		
		void getValue(int index, SFValuenf* write);
		
		SFValuesIterator* getIterator();
	};


	SFParametricData* vertices;

	SFParametricValuesList();
	
	SFValuesList* buildResource();
	
	void updateResource(SFValuesList* resource);

};

}
#endif
