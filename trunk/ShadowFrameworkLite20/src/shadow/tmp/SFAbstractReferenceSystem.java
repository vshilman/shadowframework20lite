package shadow.tmp;

import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;

public interface SFAbstractReferenceSystem {

	public abstract void addReferenceSystem(SFVertex3f position,
			SFMatrix3f rotation);

	public abstract int size();

	public abstract int getFatherIndex(int index);

	public abstract void getPosition(int index, SFVertex3f write);

	public abstract void getMatrix3f(int index, SFMatrix3f write);

	public abstract void setFatherIndex(int index, int fatherIndex);

	public abstract void setPosition(int index, SFVertex3f read);

	public abstract void setMatrix3f(int index, SFMatrix3f read);

	public abstract void setData(int index, int fatherIndex,
			SFVertex3f position, SFMatrix3f matrix);

}