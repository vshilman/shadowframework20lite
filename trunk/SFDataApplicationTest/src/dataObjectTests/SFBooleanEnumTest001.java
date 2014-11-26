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
import shadow.system.data.objects.SFBooleanEnum;
import shadow.system.data.tools.DefaultExceptionKeeper;

public class SFBooleanEnumTest001 extends TestCase{

	SFBooleanEnum boolean1 = new SFBooleanEnum();
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";
	
	@Test
	public void testA() {
		
		long startTime = System.nanoTime();
		
		boolean1.setValue(false);
		assertEquals(0, boolean1.getIndex());
		
		boolean1.setValue(true);
		assertEquals(1, boolean1.getIndex());
		
		assertEquals(true, boolean1.getElement().booleanValue());
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"SFBooleanEnumTest001.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			boolean1.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		boolean1.setIndex(0);
		
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+"SFBooleanEnumTest001.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			boolean1.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(1, boolean1.getIndex());
		assertEquals("true", boolean1.toStringValue());
		
		boolean1.setStringValue("false");
		
		assertEquals(0, boolean1.getIndex());
		assertEquals("false", boolean1.toStringValue());

		boolean1.setIndex(1);
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test A: "+duration+"ms");
	}

}
