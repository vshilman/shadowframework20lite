package comparisonTests;


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

public class SFFloatTest002JAVA extends TestCase{
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";

	@Test
	public void testA() {
		
		long startTime = System.nanoTime();
		
		SFFloat float1 = new SFFloat(10.5f);
		SFFloat float2 = new SFFloat(-78.8f);
		
		assertEquals(10.5f, float1.getFloatValue(),0);
		assertEquals(-78.8f, float2.getFloatValue(),0);
		
		float1.setFloatValue(1.5f);
		float2.setFloatValue(-1.5f);
		
		assertEquals(1.5f, float1.getFloatValue(), 0);
		assertEquals(-1.5f, float2.getFloatValue(), 0);
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"SFFloatTest002.sf");
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
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+"SFFloatTest002.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			float1.readFromStream(inputStream);
			float2.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		assertEquals(1.5f, float1.getFloatValue());
		assertEquals(-1.5f, float2.getFloatValue());	
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test A (JAVA): "+duration+"ms");
		
	}

}
