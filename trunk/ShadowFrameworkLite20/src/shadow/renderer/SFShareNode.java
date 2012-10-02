package shadow.renderer;

/**
 * A special SFNode which recycle some other Node Model,
 * applying a different transform to it. 
 * 
 * @author Alessandro Martinelli
 */
public class SFShareNode extends SFNodeWrapper{
	
	public SFNode node;
	
	/**
	 * Create this ShareNode
	 * @param node the Node use Model and Drawable State will be recycled.
	 */
	public SFShareNode(SFNode node) {
		super();
		this.node = node;
	}
	
	@Override
	public SFModel getModel() {
		return node.getModel();
	}
	
	@Override
	public boolean isDrawable() {
		return node.isDrawable();
	}
}
