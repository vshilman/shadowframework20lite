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
import shadow.system.data.objects.SFInt;
import shadow.system.data.tools.DefaultExceptionKeeper;

public class SFIntTest002JAVA extends TestCase{
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";

	@Test
	public void testA() {
		
		long startTime = System.nanoTime();
		
		SFInt int1 = new SFInt(6);
		SFInt int2 = new SFInt(-15);
		SFInt int3 = new SFInt(11);
		
		assertEquals(6, int1.getIntValue());
		assertEquals(-15, int2.getIntValue());
		assertEquals(11, int3.getIntValue());
		
		int1.setIntValue(11);
		int2.setIntValue(-17);
		int3.setIntValue(0);
		
		assertEquals(11, int1.getIntValue());
		assertEquals(-17, int2.getIntValue());
		assertEquals(0, int3.getIntValue());
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"SFIntTest002.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			int1.writeOnStream(outputStream);
			int2.writeOnStream(outputStream);
			int3.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int1.setIntValue(111);
		int2.setIntValue(111);
		int3.setIntValue(-111);
		
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+"SFIntTest002.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			int1.readFromStream(inputStream);
			int2.readFromStream(inputStream);
			int3.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		assertEquals(11, int1.getIntValue());
		assertEquals(-17, int2.getIntValue());	
		assertEquals(0, int3.getIntValue());	
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test A (JAVA): "+duration+"ms");
		
	}

}
