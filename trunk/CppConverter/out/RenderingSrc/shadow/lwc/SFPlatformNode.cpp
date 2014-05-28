#ifndef shadow_lwc_H_
#define shadow_lwc_H_

#include "java/util/ArrayList.h"
#include "java/util/Iterator.h"
#include "java/util/LinkedList.h"

#include "shadow/renderer/SFNode.h"
#include "shadow/renderer/SFViewTransform.h"

class SFPlatformNode extends SFNode implements SFWalkableContent{

//	SFIWalkable walkable;
//	SFPortalConnection[] connections=new SFPortalConnection[0];
//	SFIWalkable[] obstacles=new SFIWalkable[0];

	ArrayList<SFNode> nodes=new ArrayList<SFNode>();

	
//	boolean isVisible() {
//		return inLod(getLod());
	}

	
//	void lodEvaluation(SFViewTransform view, float[] distances) {
//		// TODO Auto-generated method stub

	}

	
//	SFIWalkable getWalkable() {
//		return walkable;
	}

//	//Walkable iteration
//	int walkableCount=0;
//	LinkedList<SFWalkableContent> contents=new LinkedList<SFWalkableContent>();

	
//	SFWalkableContent getNextWalkableContent() {
//		walkableCount++;
//		return contents.get(walkableCount-1);
	}

	
//	boolean hasNext() {
//		return walkableCount<contents.size();
	}

	
//	void startWalkableContentIteration() {
//		contents.clear();

//		contents.add(this);
//		for (int i = 0; i < connections.length; i++) {
//			SFWalkableContent addContent=connections[i].getWalkable();
//			if(addContent.isVisible()){
//				addContent.startWalkableContentIteration();
//				while(addContent.hasNext()){
//					contents.add(addContent.getNextWalkableContent());
				}
			}
		}

	}

	
//	Iterator<SFNode> iterator() {
//		ArrayList<SFNode> allNodes=new ArrayList<SFNode>();

//		return allNodes.iterator();
	}

	
//	void init() {
//		//nothing to do
	}

	
//	SFNode copyNode() {
//		return this;//should not be copied
	}

	
//	void destroy() {
//		//Nothing to do
	}

	
//	void addNode(SFNode node) {
//		nodes.add(node);
//		node.getTransform().attachOn(this->getTransform());
//		getResource().setResource(nodes.size()-1, node.getResource());
//		getResource().resourceChanged();
	}

	
//	SFIWalkable[] getObstacles() {
//		return obstacles;
	}

//	void setObstacles(SFIWalkable[] obstacles) {
		this->obstacles = obstacles;
	}
}
;
}
#endif
