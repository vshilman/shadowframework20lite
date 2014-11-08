/*
 * SFDrawableFrame.h
 *
 *  Created on: 11/set/2013
 *      Author: Alessandro
 */

#ifndef SFDRAWABLEFRAME_H_
#define SFDRAWABLEFRAME_H_

#include "../../gui/SFResizeListener.h"
#include "../../system/SFDrawable.h"

namespace sf {

class SFDrawableFrame {

	SFResizeListener* resizeListener;
	SFDrawable* drawable;

public:
	SFDrawableFrame(SFDrawable* drawable);
	virtual ~SFDrawableFrame();
	void draw();
};

} /* namespace sf */
#endif /* SFDRAWABLEFRAME_H_ */
