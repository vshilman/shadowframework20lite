#ifndef shadow_renderer_data_H_
#define shadow_renderer_data_H_


#include "shadow/system/SFGraphicsResource.h"
#include "shadow/system/SFInitiator.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFDataObject.h"
#include "shadow/system/data.SFDataset.h"

/**
 * Abstract class for Assets which can be stored and retrieved through SFDataset
 * mechanisms. <br/>
 * Subclasses should be able to provide an implementation of the method required
 * to build up the resource from its data. <br/>
 * Each DataAsset will be built only once. <br/>
 }
 }
 * 
 * @author Alessandro Martinelli
 * 
 * @param <T> the Type of graphical element which is kept by this asset.
 */
abstract class SFGraphicsAsset<T extends SFGraphicsResource> extends SFDataAsset<T> implements SFDataset{

	
	void setupResource() {
		SFInitiator.addInitiable(resource);
	}

	
	void disposeResource() {
		SFInitiator.addDestroyable(resource);
		super.disposeResource();
	}
}
;
}
#endif
