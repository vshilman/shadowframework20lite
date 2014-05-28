#ifndef shadow_lwc_H_
#define shadow_lwc_H_

#include "shadow/renderer/SFViewTransform.h"


//interface SFWalkableContent{

//	/**
//	 * Evaluates the lod for this content.
//	 * 
//	 * @param camera
//	 */
//	void lodEvaluation(SFViewTransform view,float[] distances);

//	/**
//	 * Tell if this Walkable Content is visible
//	 * Visibility should change according to subsequent call to lodEvaluation
//	 * 
//	 * @return true if the node is visible
//	 */
//	boolean isVisible();

//	SFIWalkable getWalkable();

//	void startWalkableContentIteration();

//	SFWalkableContent getNextWalkableContent();

//	boolean hasNext();

//	SFIWalkable[] getObstacles();

//	//NOTE: here we add walkable iteration

////	SFNode node;
////	SFIWalkable walkable;
////	SFIWalkable[] obstacles=new SFIWalkable[0];
////	SFPortalConnection[] connections=new SFPortalConnection[0];
////	
////	int connectionIndex=-1;
////	SFWalkableContent iterationContent=null;
////	
////	boolean hasNext() {
////		return hasNext(null);
}
////	
////	boolean hasNext(SFWalkableContent father) {
////
////		iterationContent=null;
////		if(connectionIndex==-1){
////			connectionIndex=0;
////			iterationContent=this;
}
////		
////		while(iterationContent==null && connectionIndex<connections.length){
////			SFWalkableContent content=connections[connectionIndex].getWalkable();
////		
////			if(content!=father){	
////				if(content.hasNext(this))
////					iterationContent=content.next();
}
////				
////			if(iterationContent==null){
////				connectionIndex++;
}
}
////		
////		return iterationContent!=null;
}
////	
////	SFWalkableContent next() {
////		return iterationContent;
}
////	
////	void clearIterator(){
////		clearIterator(null);
}
////	
////	void clearIterator(SFWalkableContent content){
////		connectionIndex=-1;
////		for (int i = 0; i < connections.length; i++) {
////			if(connections[i].getWalkable()!=content){
////				connections[i].getWalkable().clearIterator(this);
}
}
}
////	
////	
////	SFWalkableContent(SFNode node, SFIWalkable walkable) {
////		super();
this->node = node;
this->walkable = walkable;
}
////
////	SFNode getNode() {
////		return node;
}
////	
////	void setNode(SFNode node) {
this->node = node;
}
////	
////	SFIWalkable getWalkable() {
////		return walkable;
}
////	
////	void setWalkable(SFIWalkable walkable) {
this->walkable = walkable;
}
////
////	SFIWalkable[] getObstacles() {
////		return obstacles;
}
////
////	void setObstacles(SFIWalkable[] obstacles) {
this->obstacles = obstacles;
}
////
////	SFPortalConnection[] getConnections() {
////		return connections;
}
////
////	void setConnections(SFPortalConnection[] connections) {
this->connections = connections;
}

}
;
}
#endif
