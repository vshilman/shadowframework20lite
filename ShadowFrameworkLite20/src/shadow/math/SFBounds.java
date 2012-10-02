package shadow.math;

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
			if(getBasePoint().get()[i]>xs[i]){
				getExtension().get()[i]+=getBasePoint().get()[i]-xs[i];
				getBasePoint().get()[i]=xs[i];
			}else if(getBasePoint().get()[i]+getExtension().get()[i]<xs[i]){
				getExtension().get()[i]=xs[i]-getBasePoint().get()[i];
			} 
		}
	}	
	
	public boolean isIn(float maxDistance,float... xs){
		for (int i = 0; i < xs.length; i++) {
			if(basePoint.get()[0]-maxDistance>xs[0] || 
					basePoint.get()[0]+extension.get()[0]+maxDistance<xs[0]){
				return false;
			}
		}
		return true;
	}
}