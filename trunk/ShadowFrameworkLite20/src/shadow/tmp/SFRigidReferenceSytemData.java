package shadow.tmp;

import shadow.system.data.objects.SFBinaryDataList;
import shadow.system.data.objects.SFBinaryIntValue;
import shadow.system.data.objects.SFCompositeDataArray;
import shadow.system.data.objects.SFVertex3fData;
import shadow.system.data.objects.binaries.SFBinaryConstrainedPoint3FLQ;
import shadow.system.data.objects.binaries.SFBinaryRotationMatrix3fLQ;

public class SFRigidReferenceSytemData {


	private static final int FATHER_ID_INDEX_BITS=16;
		
	//Data Model
	@SuppressWarnings("unused")
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

}
