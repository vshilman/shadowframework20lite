package shadow.tmp;

import shadow.math.SFMatrix3f;
import shadow.math.SFRigidTransform;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipeline;
import shadow.renderer.SFGraphicsAsset;
import shadow.system.SFArray;
import shadow.system.SFArrayElementException;
import shadow.system.data.SFDataset;
import shadow.system.data.objects.SFBinaryDataList;
import shadow.system.data.objects.SFBinaryIntValue;
import shadow.system.data.objects.SFCompositeDataArray;
import shadow.system.data.objects.SFVertex3fData;
import shadow.system.data.objects.binaries.SFBinaryConstrainedPoint3FLQ;
import shadow.system.data.objects.binaries.SFBinaryRotationMatrix3fLQ;

/**
 * A List of Rigid Referencs
 * 
 * @author Alessandro Martinelli
 */
public class SFRigidReferencesSystem extends SFGraphicsAsset implements SFAbstractReferenceSystem {

	private static final int FATHER_ID_INDEX_BITS=16;
	
	//Data Model
	private class SFRigidRefereceSystemData extends SFCompositeDataArray{
		
		private SFVertex3fData minPosition;
		private SFVertex3fData maxPosition;
		private SFBinaryDataList<SFBinaryConstrainedPoint3FLQ> places;
		private SFBinaryDataList<SFBinaryRotationMatrix3fLQ> rotations;
		private SFBinaryDataList<SFBinaryIntValue> fatherID; 
		
		@Override
		public void generateData() {
			minPosition=new SFVertex3fData();
			maxPosition=new SFVertex3fData();
			places=new SFBinaryDataList<SFBinaryConstrainedPoint3FLQ>(new SFBinaryConstrainedPoint3FLQ());
			rotations=new SFBinaryDataList<SFBinaryRotationMatrix3fLQ>(new SFBinaryRotationMatrix3fLQ());
			fatherID=new SFBinaryDataList<SFBinaryIntValue>(new SFBinaryIntValue(FATHER_ID_INDEX_BITS)); 
			
			getDataObject().add(minPosition);
			getDataObject().add(maxPosition);
			getDataObject().add(places);
			getDataObject().add(rotations);
			getDataObject().add(fatherID);
		}
		
		@Override
		public SFRigidRefereceSystemData clone() {
			return new SFRigidRefereceSystemData();
		}

		public SFVertex3fData getMinPosition() {
			return minPosition;
		}

		public SFVertex3fData getMaxPosition() {
			return maxPosition;
		}

		public SFBinaryDataList<SFBinaryConstrainedPoint3FLQ> getPlaces() {
			return places;
		}

		public SFBinaryDataList<SFBinaryRotationMatrix3fLQ> getRotations() {
			return rotations;
		}

		public SFBinaryDataList<SFBinaryIntValue> getFatherID() {
			return fatherID;
		}	
	}
	
	
	private SFArray<SFRigidTransform> graphicsReferences;
	private SFRigidTransform tmpTransform=new SFRigidTransform();
	
	public SFRigidReferencesSystem() {
		SFRigidRefereceSystemData data=new SFRigidRefereceSystemData();
		setData(data);
	}
	
	public SFRigidReferencesSystem(SFVertex3f minPosition,SFVertex3f maxPosition) {
		SFRigidRefereceSystemData data=new SFRigidRefereceSystemData();
		data.getMinPosition().getVertex3f().set3f(minPosition);
		data.getMaxPosition().getVertex3f().set3f(maxPosition);
		setData(data);
	}

	@Override
	public void allocateGraphicsMemory() {
		setGraphicsReferences(SFPipeline.getSfPipelineMemory().generateRigidTransformsArray());
	}
	
	@Override
	public void freeGraphicsMemory() {
		//TODO To be evaluated...
	}
	
	@Override
	public SFDataset generateNewDatasetInstance() {
		return new SFRigidReferencesSystem();
	}
	
	/* (non-Javadoc)
	 * @see shadow.tmp.SFAbstractReferenceSystemù#addReferenceSystem(shadow.math.SFVertex3f, shadow.math.SFMatrix3f)
	 */
	@Override
	public void addReferenceSystem(SFVertex3f position,SFMatrix3f rotation){
		SFRigidRefereceSystemData data=(SFRigidRefereceSystemData)getSFDataObject();
		SFBinaryConstrainedPoint3FLQ binaryConstrainedPlace=new SFBinaryConstrainedPoint3FLQ();
		binaryConstrainedPlace.setVertex3f(position,data.minPosition.getVertex3f(),data.maxPosition.getVertex3f());
		data.getPlaces().getDataObject().add(binaryConstrainedPlace);
		SFBinaryRotationMatrix3fLQ matrix=new SFBinaryRotationMatrix3fLQ();
		matrix.setMatrix3f(rotation);
		data.getRotations().getDataObject().add(matrix);
		SFBinaryIntValue value=(SFBinaryIntValue)(data.getFatherID().getType().clone());
		value.setValue(data.getFatherID().getDataObject().size());//No father, using self index
		data.getFatherID().getDataObject().add(value);
		int index=getGraphicsReferences().generateElement();
		updateGraphicsData( index);
	}

	private void updateGraphicsData( int index) {
		tmpTransform.setFatherRigidTransform(getFatherIndex(index));
		getPosition(index, tmpTransform.getPosition());
		getMatrix3f(index, tmpTransform.getRotation());
		try {
			getGraphicsReferences().setElement(index, tmpTransform);
		} catch (SFArrayElementException e) {
			// TODO What should be done here?
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see shadow.tmp.SFAbstractReferenceSystemù#size()
	 */
	@Override
	public int size(){
		return ((SFRigidRefereceSystemData)getSFDataObject()).fatherID.elementsSize();
	}
	
	/* (non-Javadoc)
	 * @see shadow.tmp.SFAbstractReferenceSystemù#getFatherIndex(int)
	 */
	@Override
	public int getFatherIndex(int index){
		SFRigidRefereceSystemData data=(SFRigidRefereceSystemData)getSFDataObject();
		return data.fatherID.getDataObject().get(index).getValue();
	}
	
	/* (non-Javadoc)
	 * @see shadow.tmp.SFAbstractReferenceSystemù#getPosition(int, shadow.math.SFVertex3f)
	 */
	@Override
	public void getPosition(int index,SFVertex3f write){
		SFRigidRefereceSystemData data=(SFRigidRefereceSystemData)getSFDataObject();
		data.places.getDataObject().get(index).getVertex3f(write, data.minPosition.getVertex3f(),data.maxPosition.getVertex3f());
	}

	
	/* (non-Javadoc)
	 * @see shadow.tmp.SFAbstractReferenceSystemù#getMatrix3f(int, shadow.math.SFMatrix3f)
	 */
	@Override
	public void getMatrix3f(int index,SFMatrix3f write){
		SFRigidRefereceSystemData data=(SFRigidRefereceSystemData)getSFDataObject();
		data.rotations.getDataObject().get(index).getMatrix3f(write);
	}
	
	/* (non-Javadoc)
	 * @see shadow.tmp.SFAbstractReferenceSystemù#setFatherIndex(int, int)
	 */
	@Override
	public void setFatherIndex(int index,int fatherIndex){
		SFRigidRefereceSystemData data=(SFRigidRefereceSystemData)getSFDataObject();
		data.fatherID.getDataObject().get(index).setValue(fatherIndex);
		updateGraphicsData( index);
	}
	
	/* (non-Javadoc)
	 * @see shadow.tmp.SFAbstractReferenceSystemù#setPosition(int, shadow.math.SFVertex3f)
	 */
	@Override
	public void setPosition(int index,SFVertex3f read){
		SFRigidRefereceSystemData data=(SFRigidRefereceSystemData)getSFDataObject();
		data.places.getDataObject().get(index).setVertex3f(read, data.minPosition.getVertex3f(),data.maxPosition.getVertex3f());
		updateGraphicsData( index);
	}

	/* (non-Javadoc)
	 * @see shadow.tmp.SFAbstractReferenceSystemù#setMatrix3f(int, shadow.math.SFMatrix3f)
	 */
	@Override
	public void setMatrix3f(int index,SFMatrix3f read){
		SFRigidRefereceSystemData data=(SFRigidRefereceSystemData)getSFDataObject();
		data.rotations.getDataObject().get(index).setMatrix3f(read);
		updateGraphicsData( index);
	}
	
	/* (non-Javadoc)
	 * @see shadow.tmp.SFAbstractReferenceSystemù#setData(int, int, shadow.math.SFVertex3f, shadow.math.SFMatrix3f)
	 */
	@Override
	public void setData(int index,int fatherIndex,SFVertex3f position,SFMatrix3f matrix){
		SFRigidRefereceSystemData data=(SFRigidRefereceSystemData)getSFDataObject();
		data.fatherID.getDataObject().get(index).setValue(fatherIndex);
		data.places.getDataObject().get(index).setVertex3f(position, data.minPosition.getVertex3f(),data.maxPosition.getVertex3f());
		data.rotations.getDataObject().get(index).setMatrix3f(matrix);
		updateGraphicsData( index);
	}

	private void setGraphicsReferences(SFArray<SFRigidTransform> graphicsReferences) {
		this.graphicsReferences = graphicsReferences;
	}

	private SFArray<SFRigidTransform> getGraphicsReferences() {
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
