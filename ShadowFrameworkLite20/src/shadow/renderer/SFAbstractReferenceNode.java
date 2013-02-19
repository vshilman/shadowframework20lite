package shadow.renderer;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class SFAbstractReferenceNode extends SFTransformNode {

	protected ArrayList<SFNode> nodes = new ArrayList<SFNode>();

	public SFAbstractReferenceNode() {
		super();
	}

	@Override
	public synchronized void addNode(SFNode node) {
		nodes.add(node);
		node.getTransform().attachOn(this.getTransform());
	}

	public synchronized void removeNode(SFNode node) {
		nodes.remove(node);
	}
	
	@Override
	public SFModel getModel() {
		return null;//Reference Nodes has no Model
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
	public synchronized Iterator<SFNode> iterator() {
		return nodes.iterator();
		
	}

}