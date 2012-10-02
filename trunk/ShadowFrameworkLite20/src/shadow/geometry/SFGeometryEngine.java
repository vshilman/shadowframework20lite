package shadow.geometry;

import shadow.pipeline.SFMesh;
import shadow.pipeline.SFPrimitive;

public class SFGeometryEngine {

	public static int getMaxGridsSize(SFPrimitive primitive,int[] gridSizes){
		int maxGridSize=0;
		for (int gridIndex = 0; gridIndex < primitive.getGridsCount(); gridIndex++) {
			gridSizes[gridIndex]=primitive.getGrid(gridIndex).getN();
			if(maxGridSize<gridSizes[gridIndex])
				maxGridSize=gridSizes[gridIndex];
		}
		return maxGridSize;
	}
	
	public static void getGridsSize(SFPrimitive primitive,int[] gridSizes){
		for (int gridIndex = 0; gridIndex < primitive.getGridsCount(); gridIndex++) {
			gridSizes[gridIndex]=primitive.getGrid(gridIndex).getN();
		}
	}
	
	
}
