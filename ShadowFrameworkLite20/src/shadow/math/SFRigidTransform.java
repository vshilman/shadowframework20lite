package shadow.math;

public class SFRigidTransform {

	private SFVertex3f position=new SFVertex3f();
	private SFMatrix3f rotation=new SFMatrix3f();
	private int fatherRigidTransform=0;
	
	public SFRigidTransform() {
		super();
	}

	public void getPosition(SFVertex3f write) {
		write.set(position);
	}

	public void setPosition(SFVertex3f position) {
		this.position = position;
	}

	public void getRotation(SFMatrix3f read) {
		read.set(rotation);
	}

	public void setRotation(SFMatrix3f write) {
		rotation.set(write);
	}

	public int getFatherRigidTransform() {
		return fatherRigidTransform;
	}

	public void setFatherRigidTransform(int fatherRigidTransform) {
		this.fatherRigidTransform = fatherRigidTransform;
	}

	public SFVertex3f getPosition() {
		return position;
	}

	public SFMatrix3f getRotation() {
		return rotation;
	}
	
	
}
