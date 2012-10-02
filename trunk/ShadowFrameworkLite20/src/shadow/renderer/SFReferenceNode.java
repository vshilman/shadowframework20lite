package shadow.renderer;


import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;

public class SFReferenceNode extends SFAbstractReferenceNode implements SFNode{
	
	@Override
	public SFNode copyNode() {
		SFReferenceNode reference=new SFReferenceNode();
		cloneOn(reference);
		return reference;
	}

	protected void cloneOn(SFReferenceNode reference) {
		for (SFNode sfNode : nodes) {
			reference.addNode(sfNode.copyNode());
		}
		SFVertex3f tmpV=new SFVertex3f();
		SFMatrix3f tmpM=new SFMatrix3f();
		this.transform.getPosition(tmpV);
		reference.transform.setPosition(tmpV);
		this.transform.getOrientation(tmpM);
		reference.transform.setOrientation(tmpM);
	}
}
