#ifndef shadow_nodes_H_
#define shadow_nodes_H_

#include "java/util/ArrayList.h"
#include "java/util/Iterator.h"
#include "java/util/List.h"
#include "java/util/NoSuchElementException.h"

#include "shadow/math/SFMatrix3f.h"
#include "shadow/math/SFVertex3f.h"
#include "shadow/renderer/SFModel.h"
#include "shadow/renderer/SFNode.h"

class SFObjectModel extends SFNode{

	ArrayList<SFNode>();

//	class SFNodeIterator implements Iterator<SFNode>{
//		int index=0;

		
//		boolean hasNext() {
//			return index<nodes.size();
		}

		
//		SFNode next() {
//			if(!hasNext())
//				throw new NoSuchElementException();
//			SFNode node=(nodes.get(index));
//			index++;
//			return node;
		}

		
//		void remove() {
//			if(index>0){
//				index--;
//				nodes.remove(index);
			}
//			throw new IllegalStateException();
		}
	}

//	SFObjectModel() {
//		setModel(new SFModel());
//		getResource().setResource(0, getModel().getTransformComponent().getResource());
//		getResource().setResource(1, getModel().getMaterialComponent().getResource());
	}

	
//	void addNode(SFNode node) {
//		nodes.add(node);
	}


	
//	SFNode copyNode() {
//		SFObjectModel model=new SFObjectModel();
//		model.setModel(this->getModel());
//		for (SFNode bone : nodes) {
//			model.nodes.add(bone.copyNode());
		}
//		SFVertex3f tmpV=new SFVertex3f();
//		SFMatrix3f tmpM=new SFMatrix3f();
//		this->transform.getPosition(tmpV);
//		model.transform.setPosition(tmpV);
//		this->transform.getOrientation(tmpM);
//		model.transform.setOrientation(tmpM);
//		return model;
	}

//	boolean isDrawable(){
		this->getModel().getRootGeometry()!=null;
	}

	
//	void init() {
//		//Do nothing
	}

	
//	void destroy() {
//		//Its correct: if init isn't doing anything, destroy should not do anything
	}

//	protected List<SFNode> getNodes() {
//		return nodes;
	}

//	/* (non-Javadoc)
//	 * @see shadow.renderer.SFNode#iterator()
//	 */
	
//	Iterator<SFNode> iterator() {
//		return new SFNodeIterator();
	}

}

;
}
#endif
