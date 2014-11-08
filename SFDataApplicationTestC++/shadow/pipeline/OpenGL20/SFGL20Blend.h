/*
 * SFGL20Blend.h
 *
 *  Created on: 28/set/2013
 *      Author: Alessandro
 */

#ifndef SFGL20BLEND_H_
#define SFGL20BLEND_H_

#include "SFGraphicsHeaders.h"

#include "SFPipelineGraphics.h"

namespace sf {

class SFGL20Blend {

	vector<SFPipelineGraphics::BlendMode> blendStack;
	SFPipelineGraphics::BlendMode blendMode;
public:

	SFGL20Blend();

	void popBlend( );

	void pushBlend( SFPipelineGraphics::BlendMode blendMode);

	void updateBlendMode();
};

} /* namespace sf */
#endif /* SFGL20BLEND_H_ */
