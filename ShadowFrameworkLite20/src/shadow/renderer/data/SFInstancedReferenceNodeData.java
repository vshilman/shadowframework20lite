package shadow.renderer.data;

import shadow.renderer.SFAbstractReferenceNode;
import shadow.renderer.SFInstancedReferenceNode;

public class SFInstancedReferenceNodeData extends SfAbstractReferenceNodeData {

	@Override
	protected SFAbstractReferenceNode generateReferenceNode() {
		return new SFInstancedReferenceNode();
	}
}
