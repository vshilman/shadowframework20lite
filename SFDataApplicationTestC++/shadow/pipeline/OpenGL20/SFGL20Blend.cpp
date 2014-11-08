/*
 * SFGL20Blend.cpp
 *
 *  Created on: 28/set/2013
 *      Author: Alessandro
 */

#include "SFGL20Blend.h"

namespace sf {

	SFGL20Blend::SFGL20Blend() {
		this->blendMode=SFPipelineGraphics::NONE;
	}

	void SFGL20Blend::popBlend( ) {
		this->blendMode=blendStack.at(blendStack.size()-1);
		blendStack.pop_back();
		updateBlendMode();
	}

	void SFGL20Blend::pushBlend( SFPipelineGraphics::BlendMode blendMode) {
		blendStack.push_back(this->blendMode);
		this->blendMode=blendMode;
		updateBlendMode();
	}

	void SFGL20Blend::updateBlendMode() {
		switch (blendMode) {
			case SFPipelineGraphics::NONE:
				glDisable(GL_BLEND);
				break;
			case SFPipelineGraphics::ALPHABLEND:
				glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
					glDisable(GL_BLEND);
				break;
			case SFPipelineGraphics::COLORSUM:
				glBlendFunc(GL_ONE, GL_ONE);
				glEnable(GL_BLEND);
			break;
			default:
				break;
		}
	}

} /* namespace sf */
