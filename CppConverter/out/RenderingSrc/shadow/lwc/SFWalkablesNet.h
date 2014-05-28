#ifndef shadow_lwc_H_
#define shadow_lwc_H_

#include "shadow/math/SFVertex3f.h"
#include "shadow/renderer/SFViewTransform.h"

///*
// * Consider to DROP the NET. It seems useless
// */

class SFWalkablesNet /*extends SFNode*/ implements SFIWalkable{

//	SFWalkableContent activeWalkable;

////	class SFWalkableNetNodesIterator implements Iterator<SFNode>{
////		

////		boolean hasNext() {
////			return activeWalkable.hasNext();
////			//return index<activeWalkable.getConnections().length;
}
////		

////		SFNode next() {
////			return activeWalkable.next().getNode();
}
////		

////		void remove() {
////			throw new UnsupportedOperationException();
}
}

////	SFWalkableNetNodesIterator iterator=new SFWalkableNetNodesIterator();

//	SFWalkablesNet(){

	}


////	void addNode(SFNode node) {
////		// Do nothing
}
////	

////	SFNode copyNode() {
////		// Do nothing
////		return null;
}
////	

////	Iterator<SFNode> iterator() {
////		System.err.println("active "+activeWalkable);
////		activeWalkable.clearIterator();
////		return iterator;
}
////	

////	void destroy() {
////		// Do nothing
}
////	

////	void init() {
////		// Do nothing
}

	
//	boolean checkIn(SFVertex3f position) {
//		SFVertex3f positionTemp=new SFVertex3f();
//		positionTemp.set(position);

//		boolean inside=activeWalkable.getWalkable().checkIn(positionTemp);

//		float distance1=SFVertex3f.getDistance(position,positionTemp);

//		if(!inside){

//			boolean changed=false;

//			//SFPortalConnection[] connections=activeWalkable.getConnections();
//			//int connectionsSize=0;

//			boolean iterate=true;
//			activeWalkable.startWalkableContentIteration();

//			while(activeWalkable.hasNext() && iterate){
//				SFWalkableContent walkable=activeWalkable.getNextWalkableContent();
//				SFVertex3f positionTemp2=new SFVertex3f();
//				positionTemp2.set(position);
//				inside=walkable.getWalkable().checkIn(positionTemp2);
//				if(inside){
//					activeWalkable=walkable;
//					iterate=false;
//					position.set(positionTemp2);
				}
//					activeWalkable=walkable;
//					position.set(positionTemp2);
//					changed=true;
				}
			}

//			if(!inside && !changed){
//				position.set(positionTemp);
			}

		}

//			for (int i = 0; i < activeWalkable.getObstacles().length; i++) {
//				SFVertex3f positionTemp2=new SFVertex3f();
//				positionTemp2.set(position);

//				boolean outsideObstacle=activeWalkable.getObstacles()[i].checkOut(positionTemp2);
//				inside=inside & outsideObstacle;

//				if(!outsideObstacle)
//					position.set(positionTemp2);

			}
//			if(inside)
//				position.set(positionTemp);
		}

//		return inside;
	}

//	void setActiveWalkable(SFWalkableContent activeWalkable) {
		this->activeWalkable = activeWalkable;
	}

	
//	boolean checkOut(SFVertex3f position) {
//		//Don't do it
//		return false;
	}

	
//	boolean getCameraPointPosition(SFVertex3f position,SFViewTransform camera, float cameraX,
//			float cameraY) {


//		boolean isIn=activeWalkable.getWalkable().getCameraPointPosition(position,camera, cameraX, cameraY);

//		if(!isIn){

//			//SFPortalConnection[] connections=activeWalkable.getConnections();
//			boolean iterate=true;
//			activeWalkable.startWalkableContentIteration();

//			while(activeWalkable.hasNext() && iterate){
//				SFWalkableContent walkable=activeWalkable.getNextWalkableContent();
//				//SFIWalkable walkable=walkables[connections[activeWalkable][i]].getWalkable();
//				SFVertex3f positionTemp2=new SFVertex3f();
//				isIn=walkable.getWalkable().getCameraPointPosition(positionTemp2,camera, cameraX, cameraY);
//				if(isIn){
//					activeWalkable=walkable;
//					iterate=false;
//					position.set(positionTemp2);
				}
			}

		}

//		return isIn;
	}


}
;
}
#endif
