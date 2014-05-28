#ifndef shadow_renderer_data_H_
#define shadow_renderer_data_H_

#include "java/util/StringTokenizer.h"

#include "shadow/image/SFRenderedTexturesSet.h"
#include "shadow/image/SFTexture.h"
#include "shadow/pipeline/SFPrimitive.h"
#include "shadow/system/data.SFInputStream.h"
#include "shadow/system/data.SFLibraryReference.h"
#include "shadow/system/data.SFOutputStream.h"
#include "shadow/system/data.SFWritableDataObject.h"

class SFTextureData extends SFPrimitive implements SFWritableDataObject{

	int textureIndex;

	SFLibraryReference<SFRenderedTexturesSet> reference=new SFLibraryReference<SFRenderedTexturesSet>();

	SFTextureData() {
	}

	void setTextureIndex(int textureIndex) {
		this->textureIndex = textureIndex;
	}

	void setReference(String reference) {
		this->reference.setReference(reference);
	}

	SFLibraryReference<SFRenderedTexturesSet> getReference() {
		return reference;
	}

	SFTexture buildTextureReference() {

		final SFTexture texture=new SFTexture(null, textureIndex);

		texture.setTexturesSet(getReference().getResource());

		return texture;
	}

	
	SFTextureData clone() {
		return new SFTextureData();
	}

	
	void readFromStream(SFInputStream stream) {
		textureIndex=stream.readByte();
		reference.setReference(stream.readName());
	}

	
	void writeOnStream(SFOutputStream stream) {
		stream.writeByte(textureIndex);
		stream.writeName(reference.getReference());
	}

	
	void setStringValue(String value) {
		StringTokenizer tok=new StringTokenizer(value,":");
		reference.setReference(tok.nextToken());
		if(tok.hasMoreTokens())
			textureIndex=new Integer(tok.nextToken());
	}
	
	String toStringValue() {
		return reference.getReference()+":";
	}
}
;
}
#endif
