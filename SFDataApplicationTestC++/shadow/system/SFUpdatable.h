#ifndef SFUPDATABLE_H
#define SFUPDATABLE_H

#include "SFInitiable.h"

namespace sf{

	class SFUpdatable{

	public:

		virtual ~SFUpdatable(){};

		virtual void update()=0;
	};

}

#endif // SFUPDATABLE_H
