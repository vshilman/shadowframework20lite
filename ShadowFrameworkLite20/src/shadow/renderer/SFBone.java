package shadow.renderer;

import java.util.Iterator;

import shadow.pipeline.SFPipelineTransform3f;

public class SFBone implements SFNode{

	@Override
	public void addNode(SFNode node) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public SFModel getModel() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public SFPipelineTransform3f getTransform() {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public void init() {
		//Do nothing
	}
	
	@Override
	public void destroy() {
		//Its correct: if init isn't doing anything, destroy should not do anything
	}
	
	@Override
	public SFBone copyNode() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean isDrawable() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Iterator<SFNode> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
