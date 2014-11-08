
#include "SFVoidVerticesList.h"


namespace sf{

	SFVoidVerticesList::SFVoidVerticesList(){
		data=new SFShortByteField((short)0);
		SFNamedParametersObject* parameters=new SFNamedParametersObject();
		//parameters.addObject("vertexSize", this->vertexSize);
		parameters->addObject(data);
		setData(parameters);
	}

	SFVoidVerticesList::SFBinaryVListIterator::SFBinaryVListIterator(SFValuenf* nullValue){
		index=0;
		this->nullValue=nullValue;
	}

	void SFVoidVerticesList::SFBinaryVListIterator::getNext(SFValuenf* write) {
		write->set(*nullValue);
		index++;
	}


	bool SFVoidVerticesList::SFBinaryVListIterator::hasNext() {
		return index<data->getByte(1);
	}


	SFVoidVerticesList::SFBinaryVList::SFBinaryVList(SFValuenf* nullValue) {
		this->nullValue=nullValue;
	}

	SFResource* SFVoidVerticesList::SFBinaryVList::getResource() {
		return &resource;
	}

	void SFVoidVerticesList::SFBinaryVList::init() {
		//Nothing special
	}

	void SFVoidVerticesList::SFBinaryVList::destroy() {
		//Nothing special
	}


	int SFVoidVerticesList::SFBinaryVList::getValueSize() {
		return data->getByte(0);
	}

	int SFVoidVerticesList::SFBinaryVList::getSize() {
		return data->getByte(1);
	}

	void SFVoidVerticesList::SFBinaryVList::setValue(int index, SFValuenf read) {
		//vertices.setValue(index, read);
//			int vSize=getVertexSize();
//			for (int j = 0; j < vSize; j++) {
//				vertices.getDataObject().get(vSize*index+j).setFloat(read.get()[j]);
	}

	int SFVoidVerticesList::SFBinaryVList::addValue(SFValuenf read) {
		//vertices.addValue(read);
		return getSize();
	}

	void SFVoidVerticesList::SFBinaryVList::getValue(int index, SFValuenf* write) {
		write->set(*nullValue);
	}

	void SFVoidVerticesList::SFBinaryVList::setValue(int index,SFValuenf* read){

	}

	int SFVoidVerticesList::SFBinaryVList::addValue(SFValuenf* write){
		return 0;
	}

	SFValuesIterator* SFVoidVerticesList::SFBinaryVList::getIterator() {
		return new SFBinaryVListIterator(nullValue);
	}


	SFValuesList* SFVoidVerticesList::buildResource() {
		int size = this->data->getByte(0);

		nullValue=new SFValuenf(size);
		for (int i = 0; i < nullValue.getSize(); i++) {
			nullValue.getV()[i]=0;
		}
		return new SFBinaryVList(&nullValue);
	}


	void SFVoidVerticesList::updateResource(SFValuesList* resource) {
		// TODO Auto-generated method stub

	}

}
