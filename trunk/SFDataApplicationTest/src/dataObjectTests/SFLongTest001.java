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
import shadow.system.data.objects.SFLong;
import shadow.system.data.tools.DefaultExceptionKeeper;

public class SFLongTest001 extends TestCase{

	SFLong long1 = new SFLong(214748);
	SFLong long2 = new SFLong(-214748);
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";
	
	@Test
	public void testA() {
		
		long startTime = System.nanoTime();
		
		assertEquals(214748, long1.getLongValue());
		assertEquals(-214748, long2.getLongValue());
		
		long1.setLongValue(10170);
		long2.setLongValue(-4587);
		
		assertEquals(10170, long1.getLongValue());
		assertEquals(-4587, long2.getLongValue());
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test A: "+duration+"ms");
	}
	
	@Test
	public void testB(){
		
		long startTime = System.nanoTime();
		
		long1.setLongValue(124455);
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"SFLongTest001.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			long1.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		long1.setLongValue(-77);
		
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+"SFLongTest001.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			long1.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		assertEquals(124455, long1.getLongValue());	
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test B: "+duration+"ms");
	}
	
	@Test
	public void testC(){
		
		long startTime = System.nanoTime();
		
		long1.setLongValue(878787);
		long2 = long1.copyDataObject();
		
		assertEquals(878787, long2.getLongValue());
		
		long1.setStringValue("888");
		assertEquals(888, long1.getLongValue());
		
		assertEquals("888", long1.toStringValue());
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test C: "+duration+"ms");
	}

}
