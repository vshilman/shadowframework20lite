
#include "SFParametricValuesList.h"

namespace sf{



		SFParametricValuesList::SFParametricData::SFParametricData(){
			valueSize=0;
			stringsVector=0;
			dataVector=0;
			dataVectorLength=0;
			stringsVectorLength=0;
		}

		/*void SFParametricValuesList::SFParametricData::finalize(){
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

		string SFParametricValuesList::SFParametricData::getValue(int index){
			return stringsVector[dataVector[index]];
		}

		void SFParametricValuesList::SFParametricData::getValue(int index,SFValuenf* value){
			for (int i = 0; i < valueSize; i++) {
				string vString=getValue(index*valueSize+i);
				float fValue=atof(vString.c_str());
				if(fValue!=0){
					value->getV()[i]=fValue;
				}else{
					value->getV()[i]=SFVerticesParameters::getParameters()->getValue(vString);
				}
			}
		}

		int SFParametricValuesList::SFParametricData::getSize() {
			return dataVectorLength/valueSize;
		}

		void SFParametricValuesList::SFParametricData::readFromStream(SFInputStream* stream) {
			this->valueSize=stream->readByte();
			stringsVectorLength=stream->readShort();
			this->stringsVector=new string[stringsVectorLength];
			for (int i = 0; i < stringsVectorLength; i++) {
				stringsVector[i]=stream->readString();
			}
			dataVectorLength=stream->readShort();
			this->dataVector=stream->readShorts(dataVectorLength);
		}


		/*void SFParametricValuesList::SFParametricData::writeOnStream(SFOutputStream stream) {
			finalize();
			stream.writeByte((short)valueSize);
			stream.writeShort((short)stringsVector.length);
			for (int i = 0; i < stringsVector.length; i++) {
				stream.writeString(stringsVector[i]);
			}
			stream.writeShort((short)dataVector.length);
			stream.writeShorts(dataVector);
		}*/


		SFPrimitiveType* SFParametricValuesList::SFParametricData::clone() {
			return new SFParametricData();
		}

	SFParametricValuesList::SFParametricVListIterator::SFParametricVListIterator(SFParametricData* vertices){
		index=0;
		this->vertices=vertices;
	}

	void SFParametricValuesList::SFParametricVListIterator::getNext(SFValuenf* write) {
		vertices->getValue(index, write);
		index++;
	}

	bool SFParametricValuesList::SFParametricVListIterator::hasNext() {
		return index<vertices->getSize();
	}


	SFParametricValuesList::SFParametricVList::SFParametricVList(SFParametricData* vertices){
		this->vertices=vertices;
	}

	SFResource* SFParametricValuesList::SFParametricVList::getResource() {
		return &resource;
	}

	void SFParametricValuesList::SFParametricVList::init() {
		//Nothing special
	}

	void SFParametricValuesList::SFParametricVList::destroy() {
		//Nothing special
	}

	int SFParametricValuesList::SFParametricVList::getValueSize() {
		return vertices->valueSize;
	}

	int SFParametricValuesList::SFParametricVList::getSize() {
		return vertices->getSize();
	}

	void SFParametricValuesList::SFParametricVList::setValue(int index, SFValuenf read) {
		//TODO : no, setValue is not required on Viewers!!
	}

	void SFParametricValuesList::SFParametricVList::setValue(int index,SFValuenf* read){

	}

	int SFParametricValuesList::SFParametricVList::addValue(SFValuenf* write){
		return 0;
	}

	int SFParametricValuesList::SFParametricVList::addValue(SFValuenf read) {
		//vertices.addValue(read);
		//throw new SFException("SFParametricValuesList can be edited only in the xml way atm");
		return 0;
	}

	void SFParametricValuesList::SFParametricVList::getValue(int index, SFValuenf* write) {
		vertices->getValue(index, write);
	}


	SFValuesIterator* SFParametricValuesList::SFParametricVList::getIterator() {
		return new SFParametricVListIterator(vertices);
	}


	SFParametricValuesList::SFParametricValuesList(){
		vertices=new SFParametricData();
		SFNamedParametersObject* parameters=new SFNamedParametersObject();
		parameters->addObject(vertices);
		setData(parameters);
	}

	SFValuesList* SFParametricValuesList::buildResource() {
		return new SFParametricVList(vertices);
	}

	void SFParametricValuesList::updateResource(SFValuesList* resource) {
		// TODO Auto-generated method stub

	}

}
