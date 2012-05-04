package shadow.material;

import shadow.geometry.SFGeometry;
import shadow.renderer.SFLodFilter;
import shadow.renderer.SFNode;

public class SFPassAllLodFilter implements SFLodFilter{

	@Override
	public int acceptGeometry(SFGeometry node) {
		return 1;
	}
	
	@Override
	public boolean acceptNode(SFNode node) {
		return true;
	}
}
