#ifndef shadow_tmp_H_
#define shadow_tmp_H_


/////**
//// * A special SFNode which recycle some other Node Model,
//// * applying a different transform to it. 
//// * 
//// * @author Alessandro Martinelli
//// */
class SFShareNode extends SFNodeWrapper{
////	
////	SFNode node;
////	
////	/**
////	 * Create this ShareNode
////	 * @param node the Node use Model and Drawable State will be recycled.
////	 */
////	SFShareNode(SFNode node) {
////		super();
this->node = node;
}
////	

////	SFModel getModel() {
////		return node.getModel();
}
////	
}
;
}
#endif
