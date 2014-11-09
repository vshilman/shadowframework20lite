/*
 * SFEngine.h
 *
 *  Created on: 05/giu/2013
 *      Author: Alessandro
 */

#ifndef SFENGINE_H_
#define SFENGINE_H_

#include "SFInitiator.h"
#include "SFUpdater.h"
#include "SFDrawable.h"


namespace sf{
	class SFEngine {
	public:
		virtual ~SFEngine();
		SFEngine();

		static void draw(SFDrawable* drawable);
	};
}


#endif /* SFENGINE_H_ */
