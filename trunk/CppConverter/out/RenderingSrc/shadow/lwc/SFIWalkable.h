#ifndef shadow_lwc_H_
#define shadow_lwc_H_

#include "shadow/math/SFVertex3f.h"
#include "shadow/renderer/SFViewTransform.h"

///**
// * An area which can be walked
// * 
// * @author Alessandro 
// */
//interface SFIWalkable {

//	/**
//	 * Retrieve a position in the world upon a walkable
//	 * given a camera and a 2D (x,y) position upon the camera.
//	 * 
//	 * The best position will be written in position
//	 * 
//	 * @return true, if a good position has been found upon this walkable
//	 */
//	boolean getCameraPointPosition(SFVertex3f position,SFViewTransform camera,float cameraX,float cameraY);

//	/**
//	 * Verify if a position is on the walkable area. If the position
//	 * is outside the walkable area, it will be overwritten 
//	 * with a better position within the area.
//	 *
//	 * @param position
//	 * @return false, if the original position has been modified (beacuse it was not inside the walkable)
//	 */
//	boolean checkIn(SFVertex3f position);

//	/**
//	 * Verify if a position is out of the walkable area. If the position
//	 * is inside the walkable area, it will be overwritten 
//	 * with a position on the area border.
//	 *
//	 * @param position
//	 * @return false, if the original position has been modified (beacuse it was not inside the walkable)
//	 */
//	boolean checkOut(SFVertex3f position);
}
;
}
#endif
