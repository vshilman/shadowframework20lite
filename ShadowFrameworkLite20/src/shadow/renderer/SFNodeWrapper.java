package shadow.renderer;

import java.util.Iterator;

/**
 * A common use class to facilitate the development of special kind of Nodes.
 * 
 * @author Alessandro
 */
public class SFNodeWrapper extends SFTransformNode implements SFNode{

	public class SFNodeIterator0 implements Iterator<SFNode>{
		@Override
		public boolean hasNext() {
			return false;
		}
		@Override
		public SFNode next() {
			return null;
		}
		@Override
		public void remove() {
			
		}
	}
	
	@Override
	public void addNode(SFNode node) throws SFNodeException {
		//Do nothing
	}
	
	@Override
	public SFNode copyNode() {
		return null;
	}
	
	@Override
	public SFModel getModel() {
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
	public boolean isDrawable() {
		return false;
	}
	
	@Override
	public Iterator<SFNode> iterator() {
		return new SFNodeIterator0();
	}
}
