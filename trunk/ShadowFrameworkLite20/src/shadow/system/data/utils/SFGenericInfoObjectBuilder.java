package shadow.system.data.utils;

import shadow.system.data.SFDataObject;
import shadow.system.data.objects.SFCompositeDataArray;

public class SFGenericInfoObjectBuilder {

	/**
	 * @param objects
	 * @return An SFCompositeDataArray, which should not be used with SFDataList.
	 */
	public static SFCompositeDataArray generateObjectBuilder(final SFDataObject... objects){

		SFCompositeDataArray compositeDataArray=new SFCompositeDataArray() {
			
			@Override
			public void generateData() {
				for (int i = 0; i < objects.length; i++) {
					addDataObject(objects[i]);
				}
			}
			
			@Override
			public SFCompositeDataArray clone() {
				throw new UnsupportedOperationException("Clone Method is not available with SFCompositeDataArray Generated with SFGenericInfoObjectBuilder");
			}
		};
		
		return compositeDataArray;
	}
}
