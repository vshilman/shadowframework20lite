#ifndef shadow_renderer_data_H_
#define shadow_renderer_data_H_

#include "shadow/geometry/vertices.SFVerticesParameters.h"
#include "shadow/renderer/data.SFDataParametersSet.SFDataParameter.h"
#include "shadow/system/data.SFDataObjectsList.h"
#include "shadow/system/data.SFInputStream.h"
#include "shadow/system/data.SFOutputStream.h"
#include "shadow/system/data.SFWritableDataObject.h"
#include "shadow/system/data.objects.SFPrimitiveType.h"

class SFDataParametersSet extends SFDataObjectsList<SFDataParameter>{

	static final long serialVersionUID=0;

	static class SFDataParameter extends SFPrimitiveType implements SFWritableDataObject{

		String name;
		float value;

		
		SFPrimitiveType clone() {
			return new SFDataParameter();
		}

		
		void readFromStream(SFInputStream stream) {
			name=stream.readString();
			value=stream.readFloat();
		}

		
		void writeOnStream(SFOutputStream stream) {
			stream.writeString(name);
			stream.writeFloat(value);
		}


		void setStringValue(String data){
			int indexof=data.indexOf("=");
			name=data.substring(0,indexof);
			this->value=new Float(data.substring(indexof+1));
		}

		String toStringValue(){
			return name+"="+value;
		}
	}

	SFDataParametersSet() {
		super(new SFDataParameter());
	}

	void apply(){
		SFVerticesParameters.getParameters().clear();
		for (int i = 0; i < size(); i++) {
			SFDataParameter parameter=get(i);
			SFVerticesParameters.getParameters().setParameter(parameter.name, parameter.value);
		}
	}
}
;
}
#endif
