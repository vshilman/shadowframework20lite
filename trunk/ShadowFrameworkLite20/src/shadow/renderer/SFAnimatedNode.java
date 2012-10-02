package shadow.renderer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import shadow.animation.SFTransformNodeAnimation;
import shadow.pipeline.SFPipelineTransform3f;

public class SFAnimatedNode implements SFNode{

	private SFNode contentNode;
	private List<SFTransformNodeAnimation> animations=new ArrayList<SFTransformNodeAnimation>();
	
	public SFAnimatedNode() {
		super();
	}

	public SFAnimatedNode(SFNode contentNode) {
		super();
		this.contentNode = contentNode;
	}
	
	public void addAnimation(SFTransformNodeAnimation animation){
		this.animations.add(animation);
	}

	public SFNode getContentNode() {
		return contentNode;
	}

	public void setContentNode(SFNode contentNode) {
		this.contentNode = contentNode;
	}

	@Override
	public void addNode(SFNode node) throws SFNodeException {
		contentNode.addNode(node);
	}
	
	@Override
	public SFAnimatedNode copyNode() {
		SFAnimatedNode animatedNode=new SFAnimatedNode();
		animatedNode.contentNode=contentNode.copyNode();
		
		//TODO
		//for (SFTransformNodeAnimation animation : this.animations) {
		//	
		//}
		
		
		return null;
	}
	
	@Override
	public void destroy() {
		contentNode.destroy();
	}
	
	@Override
	public SFModel getModel() {
		return contentNode.getModel();
	}
	
	@Override
	public SFPipelineTransform3f getTransform() {
		return contentNode.getTransform();
	}
	
	@Override
	public void init() {
		contentNode.init();
	}
	
	@Override
	public boolean isDrawable() {
		return contentNode.isDrawable();
	}
	
	@Override
	public Iterator<SFNode> iterator() {
		return contentNode.iterator();
	}
}
