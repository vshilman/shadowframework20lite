package dataObjectTests;

import static org.junit.Assert.*;

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
import shadow.system.data.objects.SFShortArray;
import shadow.system.data.tools.DefaultExceptionKeeper;

public class SFShortArrayTest001 extends TestCase{

	
	short[] array = {(short)0,(short)0,(short)0,(short)0,(short)0};
	SFShortArray shortArray1 = new SFShortArray(array);
	SFShortArray shortArray2 = new SFShortArray(array);
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";
	
	@Test
	public void testA() {
		
		short[] expectedArray = {(short)0,(short)1,(short) 2,(short) 3,(short)4};
		
		for (int i = 0; i < shortArray1.getShortValues().length; i++) {
			shortArray1.getShortValues()[i] = (short) i;
		}
		
		assertArrayEquals(expectedArray, shortArray1.getShortValues());
		
		shortArray2 = shortArray1.copyDataObject();
		
		assertArrayEquals(expectedArray, shortArray2.getShortValues());
	
	}
	
	@Test
	public void testB(){
		
		shortArray1.getShortValues()[0] = (short) 2113;
		shortArray1.getShortValues()[1] = (short) -784;
		shortArray1.getShortValues()[2] = (short) -8752;
		shortArray1.getShortValues()[3] = (short) 22101;
		shortArray1.getShortValues()[4] = (short) 8799;
		
		short[] expectedArray = {(short)2113,(short)-784,(short) -8752,(short) 22101,(short) 8799};
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"SFShortArray001.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			shortArray1.writeOnStream(outputStream);
			
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+"SFShortArrayTest001.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			shortArray1.readFromStream(inputStream);
						
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		assertEquals(expectedArray, shortArray1.getShortValues());	
	}
}
