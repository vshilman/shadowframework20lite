package dataObjectTests;



import junit.framework.TestCase;
import static org.junit.Assert.*;
import org.junit.Test;

import shadow.system.data.objects.SFFloatArray;

public class SFFloatArrayTest001 extends TestCase {

	int arraySize = 5;
	
	SFFloatArray floatArray1 = new SFFloatArray(arraySize);
	
	@Test
	public void testA() {
		
		float[] expectedArray1 = new float[5];
		
		assertArrayEquals(expectedArray1, floatArray1.getFloatValues(), 0);
		
	}
	
	@Test
	public void testB(){
		
		float [] expectedFloatArray = {(float) 3.11,(float) -2.2,(float) 33,0,0};
		
		for (int i = 0; i < floatArray1.getFloatValues().length; i++) {
			floatArray1.getFloatValues()[i] = 0;
		}
		
		floatArray1.getFloatValues()[0] = (float) 3.11;
		floatArray1.getFloatValues()[1] = (float) -2.2;
		floatArray1.getFloatValues()[2] = (float) 33;
		
		assertArrayEquals(expectedFloatArray, floatArray1.getFloatValues(), 0);
	}

}
