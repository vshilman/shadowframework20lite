package shadow.geometry.curves.data;

import shadow.geometry.SFCurve;
import shadow.math.SFValuenf;
import shadow.system.data.objects.SFCompositeDataArray;

/**
 * A Module which contains data represting a curve
 * @author Alessandro Martinelli
 */
public abstract class SFCurveData<T extends SFValuenf> extends SFCompositeDataArray {
	/**
	 * Retrieve the Curve which is represented from this CompositeDataArray data
	 * @return an instance of a Curve which can represent this SFCurveData
	 */
	public abstract SFCurve<T> getCurve();
}
