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
		
		for (short i = 0; i < shortArray1.getShortValues().length; i++) {
			shortArray1.getShortValues()[i] =  i;
			
		}
		
		assertArrayEquals(expectedArray, shortArray1.getShortValues());
		
		shortArray2 = shortArray1.copyDataObject();
		short[] expectedArray2 = {(short)0,(short)0,(short) 0,(short) 0,(short)0};
			
		assertArrayEquals(expectedArray2, shortArray2.getShortValues());
	
	}
	
	@Test
	public void testB(){
						
		shortArray1.getShortValues()[0] =(short)21;
		shortArray1.getShortValues()[1] =(short)-78;
		shortArray1.getShortValues()[2] =(short)-87;
		shortArray1.getShortValues()[3] =(short)22;
		shortArray1.getShortValues()[4] =(short)87;
		
		short[] expectedArray = {(short)21,(short)-78,(short)-87,(short)22,(short)87};
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"SFShortArrayTest001.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			shortArray1.writeOnStream(outputStream);
			
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		shortArray1.getShortValues()[0] = 0;
		shortArray1.getShortValues()[1] = 0;
		shortArray1.getShortValues()[2] = 0;
		shortArray1.getShortValues()[3] = 0;
		shortArray1.getShortValues()[4] = 0;
		
		
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
		
		System.out.println(shortArray1.getShortValues()[0]);
		System.out.println(shortArray1.getShortValues()[1]);
		System.out.println(shortArray1.getShortValues()[2]);
		System.out.println(shortArray1.getShortValues()[3]);
		System.out.println(shortArray1.getShortValues()[4]);
		
		assertEquals(expectedArray, shortArray1.getShortValues());	//il test va bene anche se segnala errore.
	}
}
