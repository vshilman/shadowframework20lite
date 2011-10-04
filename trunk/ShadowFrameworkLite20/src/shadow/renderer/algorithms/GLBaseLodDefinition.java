package shadow.renderer.algorithms;

import shadow.geometry.SFGeometry;
import shadow.renderer.SFLodFilter;
import shadow.renderer.SFNode;

public class GLBaseLodDefinition implements SFLodFilter {

	@Override
	public int acceptGeometry(SFGeometry node) {
		return 0;
	}
	
	@Override
	public boolean acceptNode(SFNode node) {
		return true;
	}
	
}
