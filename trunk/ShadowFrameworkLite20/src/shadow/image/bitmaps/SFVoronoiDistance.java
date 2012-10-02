package shadow.image.bitmaps;

import shadow.math.SFVertex2f;
import shadow.system.SFInitiable;

public interface SFVoronoiDistance extends SFInitiable{

	public float distance(float u,float v,SFVertex2f vertex);
}
