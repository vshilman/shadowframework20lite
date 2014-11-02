package dataObjectTests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

import android.os.Environment;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;
import shadow.system.data.java.SFInputStreamJava;
import shadow.system.data.java.SFOutputStreamJava;
import shadow.system.data.objects.SFInt;
import shadow.system.data.tools.DefaultExceptionKeeper;
import junit.framework.TestCase;

public class SFIntTest001 extends TestCase {
	
	SFInt int1 = new SFInt(6);
	SFInt int2 = new SFInt(-15);
	SFInt int3 = new SFInt(11);
	SFInt int4 = new SFInt(-2147483648);
	SFInt int5 = new SFInt(2147483647);
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";

	@Test
	public void testA(){
		
		assertEquals(6, int1.getIntValue());
		assertEquals(-15, int2.getIntValue());
		assertEquals(11, int3.getIntValue());
		assertEquals(-2147483648, int4.getIntValue());
		assertEquals(2147483647, int5.getIntValue());
	}
	
	@Test
	public void testB(){
		
		int1.setIntValue(11);
		int2.setIntValue(-17);
		
		assertEquals(11, int1.getIntValue());
		assertEquals(-17, int2.getIntValue());
	}
	
	@Test
	public void testC(){
		
		int1.setIntValue(0);
		int2.setIntValue(1);
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"SFIntTest001.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			int1.writeOnStream(outputStream);
			int2.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int1.setIntValue(7787);
		int2.setIntValue(-998);
		
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+"SFIntTest001.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			int1.readFromStream(inputStream);
			int2.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		assertEquals(0, int1.getIntValue());
		assertEquals(1, int2.getIntValue());	
		
	}
	
	@Test
	public void testD(){
		
		String value = "88";

		int1.setStringValue(value);
		assertEquals(Integer.parseInt(value), int1.getIntValue());
	
	}
	
	@Test
	public void testE(){
		
		int3 = int1.copyDataObject();
		int4 = int3.copyDataObject();
		
		assertEquals(6, int3.getIntValue());
		assertEquals(6, int4.getIntValue());
		
	}
	
	@Test
	public void testF(){
	
		assertEquals("-15", int2.toStringValue());
		assertEquals("11", int3.toStringValue());
		
	}
	
}
