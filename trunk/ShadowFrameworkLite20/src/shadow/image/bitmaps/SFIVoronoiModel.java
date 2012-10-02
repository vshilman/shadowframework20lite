package shadow.image.bitmaps;

import shadow.system.SFInitiable;

public interface SFIVoronoiModel extends SFInitiable{
	public float getValue(int index,int index2,float distance,float distance2,
			float normalizedDistance,float normalizedDistance2);
}
