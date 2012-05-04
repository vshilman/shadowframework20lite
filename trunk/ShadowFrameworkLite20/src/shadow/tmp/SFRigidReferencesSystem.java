package shadow.tmp;

import shadow.math.SFMatrix3f;
import shadow.math.SFTransform3f;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipeline;
import shadow.system.SFArray;
import shadow.system.SFInitiable;

/**
 * A List of Rigid Referencs
 * 
 * @author Alessandro Martinelli
 */
public class SFRigidReferencesSystem  implements SFAbstractReferenceSystem,SFInitiable {

	
	private SFArray<SFTransform3f> graphicsReferences;
	private SFTransform3f tmpTransform=new SFTransform3f();
	
	public SFRigidReferencesSystem() {
		
	}
	
	public SFRigidReferencesSystem(SFVertex3f minPosition,SFVertex3f maxPosition) {
//		SFRigidRefereceSystemData data=new SFRigidRefereceSystemData();
//		data.getMinPosition().getVertex3f().set3f(minPosition);
//		data.getMaxPosition().getVertex3f().set3f(maxPosition);
//		setData(data);
	}

	public void allocateGraphicsMemory() {
		setGraphicsReferences(SFPipeline.getSfPipelineMemory().generateRigidTransformsArray());
	}
	
	
	/* (non-Javadoc)
	 * @see shadow.tmp.SFAbstractReferenceSystemù#addReferenceSystem(shadow.math.SFVertex3f, shadow.math.SFMatrix3f)
	 */
	@Override
	public void addReferenceSystem(SFVertex3f position,SFMatrix3f rotation){
//		SFRigidRefereceSystemData data=(SFRigidRefereceSystemData)getSFDataObject();
//		SFBinaryConstrainedPoint3FLQ binaryConstrainedPlace=new SFBinaryConstrainedPoint3FLQ();
//		binaryConstrainedPlace.setVertex3f(position,data.minPosition.getVertex3f(),data.maxPosition.getVertex3f());
//		data.getPlaces().getDataObject().add(binaryConstrainedPlace);
//		SFBinaryRotationMatrix3fLQ matrix=new SFBinaryRotationMatrix3fLQ();
//		matrix.setMatrix3f(rotation);
//		data.getRotations().getDataObject().add(matrix);
//		SFBinaryIntValue value=(SFBinaryIntValue)(data.getFatherID().getType().clone());
//		value.setValue(data.getFatherID().getDataObject().size());//No father, using self index
//		data.getFatherID().getDataObject().add(value);
//		int index=getGraphicsReferences().generateElement();
//		updateGraphicsData( index);
	}

	private void updateGraphicsData( int index) {
//		tmpTransform.setFatherRigidTransform(getFatherIndex(index));
//		getPosition(index, tmpTransform.getPosition());
//		getMatrix3f(index, tmpTransform.getRotation());
//		try {
//			getGraphicsReferences().setElement(index, tmpTransform);
//		} catch (SFArrayElementException e) {
//			// TODO What should be done here?
//			e.printStackTrace();
//		}
	}
	
	/* (non-Javadoc)
	 * @see shadow.tmp.SFAbstractReferenceSystemù#size()
	 */
	@Override
	public int size(){
//		return ((SFRigidRefereceSystemData)getSFDataObject()).fatherID.elementsSize();
		return 0;
	}
	
	/* (non-Javadoc)
	 * @see shadow.tmp.SFAbstractReferenceSystemù#getFatherIndex(int)
	 */
	@Override
	public int getFatherIndex(int index){
//		SFRigidRefereceSystemData data=(SFRigidRefereceSystemData)getSFDataObject();
//		return data.fatherID.getDataObject().get(index).getValue();
		return 0;
	}
	
	/* (non-Javadoc)
	 * @see shadow.tmp.SFAbstractReferenceSystemù#getPosition(int, shadow.math.SFVertex3f)
	 */
	@Override
	public void getPosition(int index,SFVertex3f write){
//		SFRigidRefereceSystemData data=(SFRigidRefereceSystemData)getSFDataObject();
//		data.places.getDataObject().get(index).getVertex3f(write, data.minPosition.getVertex3f(),data.maxPosition.getVertex3f());
	}

	
	/* (non-Javadoc)
	 * @see shadow.tmp.SFAbstractReferenceSystemù#getMatrix3f(int, shadow.math.SFMatrix3f)
	 */
	@Override
	public void getMatrix3f(int index,SFMatrix3f write){
//		SFRigidRefereceSystemData data=(SFRigidRefereceSystemData)getSFDataObject();
//		data.rotations.getDataObject().get(index).getMatrix3f(write);
	}
	
	/* (non-Javadoc)
	 * @see shadow.tmp.SFAbstractReferenceSystemù#setFatherIndex(int, int)
	 */
	@Override
	public void setFatherIndex(int index,int fatherIndex){
//		SFRigidRefereceSystemData data=(SFRigidRefereceSystemData)getSFDataObject();
//		data.fatherID.getDataObject().get(index).setValue(fatherIndex);
//		updateGraphicsData( index);
	}
	
	/* (non-Javadoc)
	 * @see shadow.tmp.SFAbstractReferenceSystemù#setPosition(int, shadow.math.SFVertex3f)
	 */
	@Override
	public void setPosition(int index,SFVertex3f read){
//		SFRigidRefereceSystemData data=(SFRigidRefereceSystemData)getSFDataObject();
//		data.places.getDataObject().get(index).setVertex3f(read, data.minPosition.getVertex3f(),data.maxPosition.getVertex3f());
//		updateGraphicsData( index);
	}

	/* (non-Javadoc)
	 * @see shadow.tmp.SFAbstractReferenceSystemù#setMatrix3f(int, shadow.math.SFMatrix3f)
	 */
	@Override
	public void setMatrix3f(int index,SFMatrix3f read){
//		SFRigidRefereceSystemData data=(SFRigidRefereceSystemData)getSFDataObject();
//		data.rotations.getDataObject().get(index).setMatrix3f(read);
//		updateGraphicsData( index);
	}
	
	/* (non-Javadoc)
	 * @see shadow.tmp.SFAbstractReferenceSystemù#setData(int, int, shadow.math.SFVertex3f, shadow.math.SFMatrix3f)
	 */
	@Override
	public void setData(int index,int fatherIndex,SFVertex3f position,SFMatrix3f matrix){
//		SFRigidRefereceSystemData data=(SFRigidRefereceSystemData)getSFDataObject();
//		data.fatherID.getDataObject().get(index).setValue(fatherIndex);
//		data.places.getDataObject().get(index).setVertex3f(position, data.minPosition.getVertex3f(),data.maxPosition.getVertex3f());
//		data.rotations.getDataObject().get(index).setMatrix3f(matrix);
//		updateGraphicsData( index);
	}

	private void setGraphicsReferences(SFArray<SFTransform3f> graphicsReferences) {
		this.graphicsReferences = graphicsReferences;
	}

	private SFArray<SFTransform3f> getGraphicsReferences() {
		if(graphicsReferences==null){
			allocateGraphicsMemory();
			for (int i = 0; i < size(); i++) {
				graphicsReferences.generateElements(size());
			}
		}
		return graphicsReferences;
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
}
