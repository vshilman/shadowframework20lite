/*
 * SFDrawableFrame.cpp
 *
 *  Created on: 11/set/2013
 *      Author: Alessandro
 */

#include "SFDrawableFrame.h"

#include "../../system/SFEngine.h"

namespace sf {

	SFDrawableFrame::SFDrawableFrame(SFDrawable* drawable) {
		this->drawable=drawable;
		this->resizeListener=0;
	}

	SFDrawableFrame::~SFDrawableFrame() {
		// TODO Auto-generated destructor stub
	}

	void SFDrawableFrame::draw() {

		//Not required here
		//SFGL2::setGl(gl);

		SFEngine::draw(drawable);
	}

} /* namespace sf */
