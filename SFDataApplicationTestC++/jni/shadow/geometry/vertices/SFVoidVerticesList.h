#ifndef shadow_geometry_vertices_SFVoidVerticesList_H_
#define shadow_geometry_vertices_SFVoidVerticesList_H_

#include "shadow/geometry/SFValuesIterator.h"
#include "shadow/geometry/SFValuesList.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/renderer/data/SFGraphicsAsset.h"
#include "shadow/system/SFResource.h"
#include "shadow/system/data/SFNamedParametersObject.h"
#include "shadow/system/data/objects/SFShortByteField.h"

namespace sf{
class SFVoidVerticesList : public SFGraphicsAsset<SFValuesList>{

	SFShortByteField* data;
	SFValuenf nullValue;

public:
	SFVoidVerticesList();

	//TODO: is really all this necessary?
	class SFBinaryVListIterator : public SFValuesIterator{
		int index;
		SFValuenf* nullValue;

	public:
		SFBinaryVListIterator(SFValuenf* nullValue);

		void getNext(SFValuenf* write);
		
		bool hasNext();
	};

	//TODO: should be move to its own file
	class SFBinaryVList : public SFValuesList{

		SFResource resource;
		SFValuenf* nullValue;

	public:

		SFBinaryVList(SFValuenf* nullValue);
		
		SFResource* getResource();
		
		void init();
		
		void destroy();
		
		int getValueSize();
		
		int getSize();
		
		void setValue(int index, SFValuenf read);
		
		int addValue(SFValuenf read);
		
		void getValue(int index, SFValuenf* write);

		void setValue(int index,SFValuenf* read);

		int addValue(SFValuenf* write);
		
		SFValuesIterator* getIterator();
	};

	
	SFValuesList* buildResource();
	
	void updateResource(SFValuesList* resource);

};

}
#endif
