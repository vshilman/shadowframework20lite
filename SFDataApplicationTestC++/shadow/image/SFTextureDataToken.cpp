
#include "SFTextureDataToken.h"

namespace sf{

	SFTextureDataToken::SFTextureDataToken(int width, int height, SFImageFormat format,
			Filter filters, WrapMode wrapS, WrapMode wrapT):SFPipelineTexture(width, height, format, filters, wrapS, wrapT) {
	}

	
	void SFTextureDataToken::apply(int textureLevel) {
		//Do nothing
	}

}
