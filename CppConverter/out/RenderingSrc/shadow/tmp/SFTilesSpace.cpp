#ifndef shadow_tmp_H_
#define shadow_tmp_H_

#include "shadow/math/SFMatrix3f.h"
#include "shadow/math/SFVertex3f.h"
#include "shadow/nodes/SFReferenceNode.h"
#include "shadow/renderer/SFModel.h"
#include "shadow/renderer/SFNode.h"
#include "shadow/renderer/SFTilesSet.h"

///**
// * A Node which usually used to generate Tiled Textures. 
// * 
// * This tiles space will contain [sizeX]*[sizeY] tiles, 
// * each one with its own models added. The same model
// * can be added more times on different tile.
// * 
// * 
// * the addNode in SFTilesSpace has a different role in SFTilesSpace,
// * adding the node once for each tile, so you can use it to add
// * generic models to be used on each tile.
// * 
// * @author Alessandro Martinelli
// */
class SFTilesSpace extends SFReferenceNode {

//	SFTilesSet tilesSet=new SFTilesSet();

//	/**
//	 * Initialize this Tile Space
//	 * @param sizeX the number of tiles on X direction
//	 * @param sizeY the number of tiles on Y direction
//	 */
//	SFTilesSpace(int sizeX, int sizeY) {
//		super();
//		tilesSet.setSizes( sizeX, sizeY);
	}

//	SFTilesSpace() {
//		super();
	}



//	SFTilesSet getTilesSet() {
//		return tilesSet;
	}

//	/**
//	 * Add the node to each tiles.
//	 */
	
//	void addNode(SFNode node) {
//		for (int i = 0; i < tilesSet.getSizeX(this); i++) {
//			for (int j = 0; j < tilesSet.getSizeY(this); j++) {
//				addNode(node,i,j,0);		
			}
		}
	}

//	/**
//	 * Add a Node in this tiles space.
//	 * The Node will not be directly added, a new SFShareNode(node)
//	 * will be added instead.
//	 * @param node	The node to be added
//	 * @param i the x-index in the tiles space
//	 * @param j the y-index in the tiles space
//	 */
//	void addNode(SFNode node,int i,int j,float z) {
//		node=node.copyNode();
//		node.getTransform().setPosition(new SFVertex3f(getStartingXPos(i), getStartingYPos(j), z));
//		node.getTransform().setOrientation(SFMatrix3f.getScale(tilesSet.getStepX(), tilesSet.getStepY(), 1));
//		super.addNode(node);
	}

//	float getStartingYPos(int j) {
//		return -1+(2*j+1)*tilesSet.getStepY();
	}

//	float getStartingXPos(int i) {
//		return -1+(2*i+1)*tilesSet.getStepX();
	}

//	/**
//	 * Add another clone of an already added node.
//	 * @param index the index of a previously added node
//	 * @param i the x-index in the tiles space
//	 * @param j the y-index in the tiles space
//	 * @throws IndexOutOfBoundsException If index is not a valid index of a previously added Node
//	 */
//	void addNode(int index,int i,int j,float z) throws IndexOutOfBoundsException{
//		addNode(this->nodes.get(index),i,j,z);
	}


	
//	SFNode copyNode() {
//		SFTilesSpace tilesSpace=new SFTilesSpace();
//		cloneOn(tilesSpace);
//		return tilesSpace;
	}

	
//	SFModel getModel() {
//		return null;
	}

	
//	void init() {
//		//Nothing to do
	}

}
;
}
#endif
