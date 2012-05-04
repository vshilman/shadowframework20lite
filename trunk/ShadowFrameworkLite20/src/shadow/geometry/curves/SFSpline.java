package shadow.geometry.curves;

import java.util.Iterator;

import shadow.geometry.SFCurve;
import shadow.math.SFValuenf;
import shadow.system.SFInitiable;

public interface SFSpline<T extends SFValuenf> extends SFInitiable{
	public Iterator<SFCurve<T>> getCurves();
}
