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
import shadow.system.data.objects.SFShort;
import shadow.system.data.tools.DefaultExceptionKeeper;

public class SFShortTest001 extends TestCase{
	
	SFShort short1 = new SFShort((short) 0);
	SFShort short2 = new SFShort((short) 0);
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";
	
	@Test
	public void testA() {
		
		long startTime = System.nanoTime();
		
		short1.setShortValue((short)8874);
		short2.setShortValue((short)-32767);
		
		assertEquals(8874, short1.getShortValue());
		assertEquals(-32767, short2.getShortValue());
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test A: "+duration+"ms");
	}
	
	@Test
	public void testB(){
		
		long startTime = System.nanoTime();
		
		short1.setShortValue((short)118);
		short2.setShortValue((short) -154.35);
		
		assertEquals((short)118, short1.getShortValue());
		assertEquals((short) -154.35, short2.getShortValue());
		
		short1.setShortValue((short)1111.111);
		short2.setShortValue((short) -88.88);
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"SFShortTest001.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			short1.writeOnStream(outputStream);
			short2.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+"SFShortTest001.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			short1.readFromStream(inputStream);
			short2.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		assertEquals((short)1111.111, short1.getShortValue());
		assertEquals((short) -88.88, short2.getShortValue());
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test B: "+duration+"ms");
	}
	
	@Test
	public void testC(){
		
		long startTime = System.nanoTime();
		
		short1.setShortValue((short)1978);
		short2 = short1.copyDataObject();
		
		assertEquals((short)1978, short1.getShortValue());
		assertEquals((short)1978, short2.getShortValue());
		
		short2.setStringValue("1187");
		
		assertEquals((short)1187, short2.getShortValue());
		assertEquals("1978", short1.toStringValue());
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test C: "+duration+"ms");
		
	}

}
