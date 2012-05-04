package shadow.renderer;

import java.util.ArrayList;
import java.util.Iterator;

import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;

public class SFReferenceNode extends SFTransformNode implements SFNode{
	
	private ArrayList<SFNode> nodes=new ArrayList<SFNode>();

	@Override
	public void addNode(SFNode node) {
		nodes.add(node);
		node.getTransform().attachOn(this.getTransform());
	}
	
	@Override
	public SFModel getModel() {
		return null;//Reference Nodes has no Model
	}
	
	@Override
	public void init() {
		
	}
	
	@Override
	public SFNode copyNode() {
		SFReferenceNode reference=new SFReferenceNode();
		for (SFNode sfNode : nodes) {
			reference.addNode(sfNode.copyNode());
		}
		SFVertex3f tmpV=new SFVertex3f();
		SFMatrix3f tmpM=new SFMatrix3f();
		this.transform.getPosition(tmpV);
		reference.transform.setPosition(tmpV);
		this.transform.getOrientation(tmpM);
		reference.transform.setOrientation(tmpM);
		return reference;
	}
	
	@Override
	public boolean isDrawable() {
		return false;
	}
	
	@Override
	public Iterator<SFNode> iterator() {
		return nodes.iterator();
		
	}
}
