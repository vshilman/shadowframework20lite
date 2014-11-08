#ifndef SFINITIATOR_H
#define SFINITIATOR_H

#include <vector>
#include "SFInitiable.h"

using namespace std;

namespace sf{

	class SFInitiator{

	private:
		static SFInitiator* initiator;

		vector<SFInitiable*> initiables;
		vector<SFInitiable*> destroyables;

		SFInitiator();

	public:

		static void addInitiable(SFInitiable* initiable);

		static void addDestroyable(SFInitiable* initiable);

		static void solveInitiables() ;

	};
}

#endif // SFINITIATOR_H
