/*
 * SFEngine.cpp
 *
 *  Created on: 05/giu/2013
 *      Author: Alessandro
 */

#include "SFEngine.h"

namespace sf{

	SFEngine::~SFEngine() {

	}

	SFEngine::SFEngine() {

	}

	void SFEngine::draw(SFDrawable* drawable){
		SFInitiator::solveInitiables();
		SFUpdater::refresh();
		drawable->draw();
	}

}
