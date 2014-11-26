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
import shadow.system.data.objects.SFString;
import shadow.system.data.tools.DefaultExceptionKeeper;

public class SFStringTest001 extends TestCase{
	
	SFString string1 = new SFString();
	SFString string2 = new SFString();
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";
	
	
	@Test
	public void testA() {
		
		long startTime = System.nanoTime();
		
		string1.setString("Giacomo");
		string2.setString("Mattia");
		
		assertEquals("Giacomo", string1.getString());
		assertEquals("Mattia", string2.getString());
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test A: "+duration+"ms");
	}
	
	@Test
	public void testB(){
		
		long startTime = System.nanoTime();
		
		string1.setStringValue("Alberto");
		string2.setStringValue("Benedetta");
		
		assertEquals("Alberto", string1.getString());
		assertEquals("Benedetta", string2.getString());
		
		string1.setString("Spagna");
		string2.setString("Italia");
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"SFStringTest001.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			string1.writeOnStream(outputStream);
			string2.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		string1.setString("pizza");
		string1.setString("pasta");
		
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+"SFStringTest001.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			string1.readFromStream(inputStream);
			string2.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		assertEquals("Spagna", string1.getString());
		assertEquals("Italia", string2.getString());	
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test B: "+duration+"ms");
		
	}
	
	 @Test
	 public void testC(){
		 
		 long startTime = System.nanoTime();
		 
		 string1.setString("Ingegneria");
		 string2 = string1.copyDataObject();
		 
		 assertEquals("Ingegneria", string1.getString());
		 assertEquals("Ingegneria", string2.getString());
		 
		 string1.setString("Medicina");
		 
		 assertEquals("Medicina", string1.toStringValue());
		 assertEquals("Ingegneria", string2.toStringValue());
		 
		 long endTime = System.nanoTime();
		 long duration = (endTime - startTime)/1000000;
			
		 System.out.println("Durata Test C: "+duration+"ms");
	 }

}
