#include "SFUpdater.h"


namespace sf{

	SFUpdater::SFUpdater(){
	}

	SFUpdater* SFUpdater::updater=new SFUpdater();


	void SFUpdater::addUpdatable(SFUpdatable* updatables) {
		updater->updatables.push_back(updatables);
	}

	void SFUpdater::refresh() {
		vector<SFUpdatable*>::iterator dIterator;
		for(dIterator = updater->updatables.begin();
			dIterator != updater->updatables.end();dIterator++){
			(*dIterator)->update();
		}
		updater->updatables.clear();
	}
}
