#ifndef shadow_renderer_data_utils_H_
#define shadow_renderer_data_utils_H_

#include "shadow/system/data.java.SFIOExceptionKeeper.h"

class DefaultExceptionKeeper implements SFIOExceptionKeeper{

	void launch(java.io.IOException exception) {
		System.err.println(exception);
	}
}
;
}
#endif
