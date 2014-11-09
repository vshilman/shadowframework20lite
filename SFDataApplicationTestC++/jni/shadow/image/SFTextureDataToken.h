#ifndef shadow_image_SFTextureDataToken_H_
#define shadow_image_SFTextureDataToken_H_

#include "SFPipelineTexture.h"

namespace sf{
class SFTextureDataToken : public SFPipelineTexture{

public:
	SFTextureDataToken(int width, int height, SFImageFormat format,
			Filter filters, WrapMode wrapS, WrapMode wrapT);

	void apply(int textureLevel);

};

}
#endif
