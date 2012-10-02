package shadow.renderer;

import shadow.pipeline.SFPipelineTransform3f;
import shadow.system.SFInitiable;

/**
 * Generic Interface for a Scene-graph Node
 * 
 * @author Alessandro Martinelli
 */
public interface SFNode extends Iterable<SFNode>,SFInitiable{
	
	/**
	 * An exception occurring when there is something wrong with Nodes Composition
	 */
	public class SFNodeException extends RuntimeException{
		private static final long serialVersionUID = 0;
		public SFNodeException(String message) {
			super(message);
		}
	}

	/**
	 * Add a son node to this node
	 * @param node the node to be added
	 * @throws SFNodeException when node cannot be added to this node
	 */
	public void addNode(SFNode node) throws SFNodeException;
	
	/**
	 * Provide a copy of this Node. Copied element should use the same
	 * renderable Model, but must have a separate transform.
	 * Node copying should allow the transfer of 
	 * @return
	 */
	public SFNode copyNode();

	/**
	 * A Transform which any renderer should apply to 
	 * this node and to all its sons node. 
	 * @return
	 */
	public SFPipelineTransform3f getTransform();
	
	/**
	 * Provide the Model element for this Node if this node can be rendered.
	 * Usually, when isDrawable()==false, then getModel()==null
	 * 
	 * @return a Model which can be rendered
	 */
	public SFModel getModel();
	
	/**
	 * Tells if this Node can be drawn or not. Often when isDrawable()==false,
	 * then getModel()==null
	 * 
	 * @return false, if this SFNode can't be drawn.
	 */
	public boolean isDrawable();
}