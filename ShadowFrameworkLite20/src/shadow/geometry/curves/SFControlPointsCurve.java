package shadow.geometry.curves;

import shadow.geometry.SFCurve;
import shadow.math.SFValuenf;

public interface SFControlPointsCurve extends SFCurve{
	
	public int getControlPointSize();

	public SFValuenf getControlPoint(int index);

}
