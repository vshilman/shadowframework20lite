package shadow.pipeline.openGL20;

import java.util.ArrayList;

import shadow.math.SFMatrix3f;
import shadow.math.SFRigidTransform;
import shadow.math.SFVertex3f;
import shadow.system.SFArray;
import shadow.system.SFArrayElementException;


public class SFGL20RigidTransformsArray extends SFGL20ListData<SFRigidTransform> implements SFArray<SFRigidTransform>{
	
	private SFVertex3f tmpVertex=new SFVertex3f();
	private SFMatrix3f tmpMatrix=new SFMatrix3f();
	
	private ArrayList<SFVertex3f> effectivePositions=new ArrayList<SFVertex3f>();
	private ArrayList<SFMatrix3f> effectiveRotations=new ArrayList<SFMatrix3f>();
	private ArrayList<ArrayList<Integer>> sons=new ArrayList<ArrayList<Integer>>();
	
	@Override
	protected void assignValues(SFRigidTransform writing,
			SFRigidTransform reading) throws SFArrayElementException {
		writing.setFatherRigidTransform(reading.getFatherRigidTransform());
		reading.getPosition(tmpVertex);
		writing.setPosition(tmpVertex);
		reading.getRotation(tmpMatrix);
		writing.setRotation(tmpMatrix);
	}
	
	@Override
	public void setElement(int index, SFRigidTransform element)
			throws SFArrayElementException {
		int oldFather=data.get(index).getFatherRigidTransform();
		super.setElement(index, element);
		
		if(oldFather!=element.getFatherRigidTransform()){
			sons.get(oldFather).remove(new Integer(index));
			sons.get(element.getFatherRigidTransform()).add(index);
		}
		
		updateRealTransform(index,element.getFatherRigidTransform());
	}

	private void updateSons(int index) {
		ArrayList<Integer> sonsR=sons.get(index);
		
		for (int i = 0; i < sonsR.size(); i++) {
			updateRealTransform(sonsR.get(i),index);
		}
	}
	
	private void updateRealTransform(int index,int father){
		if(index==father){
			effectivePositions.get(index).set3f(data.get(index).getPosition());
			effectiveRotations.get(index).set(data.get(index).getRotation());
		}else{
			SFMatrix3f fatherMatrix3f=effectiveRotations.get(father);
			SFVertex3f fatherPosition3f=effectivePositions.get(father);
			
			tmpVertex.set(fatherMatrix3f.Mult(data.get(index).getPosition()));
			tmpVertex.add3f(fatherPosition3f);
			
			effectivePositions.get(index).set3f(tmpVertex);
			effectiveRotations.get(index).set(fatherMatrix3f.Mult(data.get(index).getRotation()));
			updateSons(index);
		}
		
	}
	
	@Override
	protected SFRigidTransform generateGenericElement() {
		effectivePositions.add(new SFVertex3f());
		effectiveRotations.add(new SFMatrix3f());
		sons.add(new ArrayList<Integer>());
		SFRigidTransform transform=new SFRigidTransform();
		sons.get(sons.size()-1).add(sons.size()-1);
		return transform;
	}

	public SFVertex3f getEffectivePosition(int index){
		return effectivePositions.get(index);
	}
	
	public SFMatrix3f getEffectiveRotation(int index){
		return effectiveRotations.get(index);
	}
}
