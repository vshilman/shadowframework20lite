package shadow.renderer;

import shadow.pipeline.SFPipelineTransform3f;
import shadow.system.SFInitiable;

/**
 * Generic Interface for a Scenegraph Node
 * 
 * @author Alessandro Martinelli
 */
public interface SFNode extends Iterable<SFNode>,SFInitiable{

	public void addNode(SFNode node);
	
	public SFNode copyNode();

	public SFPipelineTransform3f getTransform();
	
	public SFModel getModel();
	
	public boolean isDrawable();
}