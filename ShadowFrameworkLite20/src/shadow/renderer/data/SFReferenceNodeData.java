package shadow.renderer.data;

import shadow.renderer.SFAbstractReferenceNode;
import shadow.renderer.SFReferenceNode;

public class SFReferenceNodeData extends SfAbstractReferenceNodeData {

	@Override
	protected SFAbstractReferenceNode generateReferenceNode() {
		return new SFReferenceNode();
	}
	
}
