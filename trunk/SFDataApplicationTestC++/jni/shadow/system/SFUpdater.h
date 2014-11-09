#ifndef SFUPDATER_H
#define SFUPDATER_H

#include <vector>
#include "SFUpdatable.h"

using namespace std;

namespace sf{

	class SFUpdater{

	private:
		static SFUpdater* updater;

		vector<SFUpdatable*> updatables;

		SFUpdater();

	public:

		static void addUpdatable(SFUpdatable* initiable);

		static void refresh();
	};
}

#endif // SFUPDATER_H
