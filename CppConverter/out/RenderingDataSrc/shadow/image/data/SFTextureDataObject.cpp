#ifndef shadow_image_data_H_
#define shadow_image_data_H_

#include "shadow/image/SFImageFormat.h"
#include "shadow/image/SFPipelineTexture.h"
#include "shadow/image/SFPipelineTexture.Filter.h"
#include "shadow/image/SFPipelineTexture.WrapMode.h"
#include "shadow/image/SFTextureDataToken.h"
#include "shadow/system/data.SFWritableDataObject.h"
#include "shadow/system/data.java.SFStringTokenizerInputStream.h"
#include "shadow/system/data.java.SFStringWriterStream.h"
#include "shadow/system/data.objects.SFCompositeDataArray.h"
#include "shadow/system/data.objects.SFIntByteField.h"
#include "shadow/system/data.objects.SFIntShortField.h"

namespace sf{
class SFTextureDataObject extends SFCompositeDataArray implements SFWritableDataObject{

	SFIntShortField widthHeight;
	SFIntByteField params;

	void generateData() {
		widthHeight=new SFIntShortField(0);
		params=new SFIntByteField(0);

		addDataObject(widthHeight);
		addDataObject(params);
	}

	
	SFCompositeDataArray clone() {
		return new SFTextureDataObject();
	}

	int getWidth() {
		return widthHeight.getShort(0);
	}

	void setWidth(int width) {
		widthHeight.setShort(0, width);
	}

	int getHeight() {
		return widthHeight.getShort(1);
	}

	void setHeight(int height) {
		widthHeight.setShort(1, height);
	}

	SFImageFormat getFormat() {
		int format=params.getByte(0);
		return SFImageFormat.values()[format];
	}

	Filter getFilter() {
		int filter=params.getByte(1);
		return Filter.values()[filter];
	}

	WrapMode getWrapModeS() {
		int filter=params.getByte(2);
		return WrapMode.values()[filter];
	}

	WrapMode getWrapModeT() {
		int filter=params.getByte(3);
		return WrapMode.values()[filter];
	}

	void setFormat(SFImageFormat format) {
		for (int i = 0; i < SFImageFormat.values().length; i++) {
			if(format==SFImageFormat.values()[i]){
				params.setByte(0, i);
				return;
			}
		}
	}

	void setFilter(Filter filter) {
		for (int i = 0; i < Filter.values().length; i++) {
			if(filter==Filter.values()[i]){
				params.setByte(1, i);
				return;
			}
		}
	}

	void setWrapModeS(WrapMode wrapMode) {
		for (int i = 0; i < WrapMode.values().length; i++) {
			if(wrapMode==WrapMode.values()[i]){
				params.setByte(2, i);
				return;
			}
		}
	}

	void setWrapModeT(WrapMode wrapMode) {
		for (int i = 0; i < WrapMode.values().length; i++) {
			if(wrapMode==WrapMode.values()[i]){
				params.setByte(3, i);
				return;
			}
		}
	}

	SFPipelineTexture getTexture(){
		SFPipelineTexture token=new SFTextureDataToken(getWidth(),getHeight(),getFormat(),getFilter(),getWrapModeS(),getWrapModeT());
		return token;
	}

	void setTexture(SFPipelineTexture texture){
		setWidth(texture.getWidth());
		setHeight(texture.getHeight());
		setFormat(texture.getFormat());
		setFilter(texture.getFilters());
		setWrapModeS(texture.getWrapS());
		setWrapModeT(texture.getWrapT());
	}

	
	void setStringValue(String value) {
		SFStringTokenizerInputStream stream=new SFStringTokenizerInputStream(value);
		setWidth(stream.readInt());
		setHeight(stream.readInt());
		setFormat(SFImageFormat.valueOf(stream.readString()));
		setFilter(Filter.valueOf(stream.readString()));
		setWrapModeS(WrapMode.valueOf(stream.readString()));
		setWrapModeT(WrapMode.valueOf(stream.readString()));
	}

	
	String toStringValue() {
		SFStringWriterStream stream=new SFStringWriterStream();
		stream.writeInt(getWidth());
		stream.writeInt(getHeight());
		stream.writeString(getFormat().toString());
		stream.writeString(getFilter().toString());
		stream.writeString(getWrapModeS().toString());
		stream.writeString(getWrapModeT().toString());
		return stream.getString();
	}

}
;
}
#endif
