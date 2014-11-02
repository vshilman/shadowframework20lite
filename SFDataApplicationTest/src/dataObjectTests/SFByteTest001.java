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
import shadow.system.data.objects.SFByte;
import shadow.system.data.tools.DefaultExceptionKeeper;


// Il byte permette valori da -128 a +127, se vado fuori range ho errori di compilazione

public class SFByteTest001 extends TestCase{
	
	SFByte byte1 = new SFByte(8);
	SFByte byte2 = new SFByte(-74);
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";

	@Test
	public void testA() {
		
		assertEquals(8, byte1.getByteValue());
		assertEquals(-74, byte2.getByteValue());
		
		byte1.setByteValue(127);
		byte2.setByteValue(-99);
		
		assertEquals(127, byte1.getByteValue());
		assertEquals(-99, byte2.getByteValue());
		
		byte1.setStringValue("-128");
		
		assertEquals(-128, byte1.getByteValue());	
		
		byte1.setStringValue("111");
		byte2.setStringValue("-111");
		
		assertEquals("111", byte1.toStringValue());
		assertEquals("-111", byte2.toStringValue());
	}
	
	@Test
	public void testB(){
		
		byte2 = byte1.copyDataObject();
		
		assertEquals(8, byte1.getByteValue());
		assertEquals(8, byte2.getByteValue());
		
		byte1.setByteValue(13);
		byte2.setByteValue(-101);
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"SFIntTest001.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			byte1.writeOnStream(outputStream);
			byte2.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		byte1.setByteValue(0);
		byte2.setByteValue(0);
		
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+"SFIntTest001.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			byte1.readFromStream(inputStream);
			byte2.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		assertEquals(13, byte1.getByteValue());
		assertEquals(-101, byte2.getByteValue());
	}

}
