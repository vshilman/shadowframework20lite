package shadow.renderer;

import shadow.geometry.SFGeometry;

public interface SFLodFilter {
	
	public boolean acceptNode(SFNode node);
	
	/***
	 * Define how a geometry should be accepted
	 * @param Node
	 * @return -1 if Geometry should not be accepted. Number of available lods in Geometry if geometry should be accepted
	 */
	public int acceptGeometry(SFGeometry node);
}
