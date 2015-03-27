package shadow.math;

/**
 * 
 * @deprecated I will deprecate this class. In order to get better solutions.
 * @author Alessandro
 *
 * @param <T>
 */
public class SFBounds<T extends SFValuenf> {

	private T basePoint;
	private T extension;
	
	public SFBounds(T basePoint, T extension) {
		super();
		this.basePoint = basePoint;
		this.extension = extension;
	}

	public T getBasePoint() {
		return basePoint;
	}

	public void setBasePoint(T basePoint) {
		this.basePoint = basePoint;
	}

	public T getExtension() {
		return extension;
	}

	public void setExtension(T extension) {
		this.extension = extension;
	}

	public void includeInBounds(float... xs){
		for (int i = 0; i < xs.length; i++) {
			if(getBasePoint().getV()[i]>xs[i]){
				getExtension().getV()[i]+=getBasePoint().getV()[i]-xs[i];
				getBasePoint().getV()[i]=xs[i];
			}else if(getBasePoint().getV()[i]+getExtension().getV()[i]<xs[i]){
				getExtension().getV()[i]=xs[i]-getBasePoint().getV()[i];
			} 
		}
	}	
	
	public boolean isIn(float maxDistance,float... xs){
		for (int i = 0; i < xs.length; i++) {
			if(basePoint.getV()[0]-maxDistance>xs[0] || 
					basePoint.getV()[0]+extension.getV()[0]+maxDistance<xs[0]){
				return false;
			}
		}
		return true;
	}
}