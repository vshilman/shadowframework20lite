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
import shadow.system.data.formats.SFFixedFloatUnit8;
import shadow.system.data.java.SFInputStreamJava;
import shadow.system.data.java.SFOutputStreamJava;
import shadow.system.data.objects.SFBinaryObject;
import shadow.system.data.tools.DefaultExceptionKeeper;

public class SFBinaryObjectTest001 extends TestCase{
	
	SFBinaryObject<SFFixedFloatUnit8> binaryObject = new SFBinaryObject<SFFixedFloatUnit8>(new SFFixedFloatUnit8());
	SFFixedFloatUnit8 float1 = new SFFixedFloatUnit8((float)1.3);

	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";
	
	@Test
	public void testA() {
		
		long startTime = System.nanoTime();
		
		binaryObject.getBinaryValue().setFloat((float)0.5);
		assertEquals(0.5, binaryObject.getBinaryValue().getFloat(), 0);
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"SFBinaryObjectTest001.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			binaryObject.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		binaryObject.getBinaryValue().setFloat((float)0.8);
		
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+"SFBinaryObjectTest001.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			binaryObject.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(0.5, binaryObject.getBinaryValue().getFloat(), 0);
		
		assertEquals(8, binaryObject.getBinaryValue().getBitSize());
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test A: "+duration+"ms");
	}
}
