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
import shadow.system.data.objects.SFEnumObject;
import shadow.system.data.objects.SFInt;
import shadow.system.data.tools.DefaultExceptionKeeper;

public class SFEnumObjectTest001 extends TestCase{
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";
	
	SFInt[] intArray = new SFInt[2];
	
	SFInt int1 = new SFInt(10);
	SFInt int2 = new SFInt(-128);
	
	String[] names = new String[2];
	
	int index = 0;
	
	SFEnumObject<SFInt> enumObject1 = new SFEnumObject<SFInt>(intArray, names, index );
	
	
		@Test
		public void testA(){
			
			intArray[0] = int1;
			intArray[1] = int2;
			
			names[0] = "intero1";
			names[1] = "intero2";
			
			enumObject1.setValue(int2);
			
			//System.out.println(enumObject1.getIndex());
			
			assertEquals(1, enumObject1.getIndex());		
			
			assertEquals(-128, enumObject1.getElement().getIntValue());
			
			enumObject1.setStringValue("intero1");
			
			//System.out.println(enumObject1.getIndex());
			
			assertEquals(0, enumObject1.getIndex());
			assertEquals("intero1", enumObject1.toStringValue());
			
			try {
				FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"SFEnumObjectTest001.sf");
				SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
				
				enumObject1.writeOnStream(outputStream);
				
				output.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			
			try {
				FileInputStream input=new FileInputStream(DIRECTORY+"/"+"SFEnumObjectTest001.sf");
				SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
				
				enumObject1.readFromStream(inputStream);
				
				input.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	
	
	}

