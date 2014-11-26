package dataObjectTests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Test;

import android.os.Environment;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;
import shadow.system.data.java.SFInputStreamJava;
import shadow.system.data.java.SFOutputStreamJava;
import shadow.system.data.objects.SFFloat;
import shadow.system.data.tools.DefaultExceptionKeeper;

public class SFFloatTest001 extends TestCase {

	SFFloat float1 = new SFFloat();
	SFFloat float2 = new SFFloat();
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";
	
	@Test
	public void testA(){
		
		long startTime = System.nanoTime();
		
		float expectedValue = (float) 3.11;
		float expectedValue2 = (float) -0.11;
		
		assertEquals(0, float1.getFloatValue(),0);
		assertEquals(0, float2.getFloatValue(),0);
		float1.setFloatValue(expectedValue);
		float2.setFloatValue(expectedValue2);
		assertEquals(expectedValue, float1.getFloatValue(), 0);
		assertEquals(expectedValue2, float2.getFloatValue(), 0);
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test A: "+duration+"ms");
		
	}
	
	@Test
	public void testB(){
		
		long startTime = System.nanoTime();
		
		float value1 = (float) 44.2112;
		float value2 = (float) 0.001225;
		
		float1.setFloatValue(value1);
		float2.setFloatValue(value2);
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"SFFloatTest001.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			float1.writeOnStream(outputStream);
			float2.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		float1.setFloatValue(88);
		float1.setFloatValue((float)-11.25);
		
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+"SFFloatTest001.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			float1.readFromStream(inputStream);
			float2.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		assertEquals(value1, float1.getFloatValue());
		assertEquals(value2, float2.getFloatValue());	
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test B: "+duration+"ms");
	}
	
	@Test
	public void testC(){
		
		long startTime = System.nanoTime();
		
		String value1 = "44.111";
		String value2 = "-15.225";
		
		float1.setStringValue(value1);
		float2.setStringValue(value2);
		
		assertEquals(Float.parseFloat(value1), float1.getFloatValue(), 0);
		assertEquals(Float.parseFloat(value2), float2.getFloatValue(), 0);
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test C: "+duration+"ms");

	}
	
	@Test
	public void testD(){
		
		long startTime = System.nanoTime();
		
		float value1 = (float) 66.00008;
		
		
		float1.setFloatValue(value1);
		float2 = float1.copyDataObject();
		
		assertEquals(value1, float1.getFloatValue());
		assertEquals(value1, float2.getFloatValue());
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test D: "+duration+"ms");
		
	}
	
	@Test
	public void testE(){
		
		long startTime = System.nanoTime();
		
		float value1 = (float) 138.1075;
		
		
		float1.setFloatValue(value1);
		
		assertEquals(String.valueOf(value1), float1.toStringValue());
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test E: "+duration+"ms");
	
	}
	
	
}
