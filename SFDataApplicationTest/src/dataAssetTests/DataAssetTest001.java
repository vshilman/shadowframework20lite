package dataAssetTests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Test;

import shadow.system.data.SFDataCenter;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;
import shadow.system.data.java.SFInputStreamJava;
import shadow.system.data.java.SFOutputStreamJava;
import shadow.system.data.objects.SFFloat;
import shadow.system.data.objects.SFInt;
import shadow.system.data.objects.SFString;
import shadow.system.data.tools.DefaultExceptionKeeper;
import tests.utils.DataAsset001;
import tests.utils.DictionaryUtils;
import android.os.Environment;


public class DataAssetTest001 extends TestCase{

	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";
	
	
	@Test
	public void testA(){
		
		long startTime = System.nanoTime();

		DictionaryUtils dictionary = new DictionaryUtils();
		SFDataCenter.setDictionary(dictionary);
		DataAsset001 dataAsset = new DataAsset001();
		
		assertEquals("DataAsset001", dataAsset.getName());
		assertEquals(2, dataAsset.getSize());
		
		SFString string = new SFString("Normal");
		dataAsset.addObject("type", string);
		
		assertEquals(3, dataAsset.getSize());
		assertEquals(0, dataAsset.getIndex("amount"));
		assertEquals(1, dataAsset.getIndex("mark"));
		assertEquals(2, dataAsset.getIndex("type"));
		
		//DIRECTORY+"/"+
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"DataAssetWritten.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			dictionary.writeDataset(outputStream, dataAsset);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		SFInt int1 = new SFInt(55);
		SFFloat float1= new SFFloat(87.7f);
		
		dataAsset.setDataObject(0, int1);
		dataAsset.setDataObject(1, float1);
		
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+"DataAssetWritten.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			dictionary.readDataset(inputStream, getName());
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(0, dataAsset.getIndex("amount"));
		assertEquals(1, dataAsset.getIndex("mark"));
		assertEquals(-1, dataAsset.getIndex("int1"));
		assertEquals(-1, dataAsset.getIndex("float1"));
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test A: "+duration+"ms");
	}
	
	
	@Test
	public void testB(){
		
		long startTime = System.nanoTime();
		
		DictionaryUtils dictionary = new DictionaryUtils();
		SFDataCenter.setDictionary(dictionary);
		DataAsset001 dataAsset = new DataAsset001();
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"DataAssetWritten2.sfb");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			dataAsset.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		SFInt int1 = new SFInt(55);
		SFFloat float1= new SFFloat(87.7f);
		
		dataAsset.setDataObject(0, int1);
		dataAsset.setDataObject(1, float1);
		
		assertEquals(0, dataAsset.getIndex("amount"));
		assertEquals(1, dataAsset.getIndex("mark"));
		
		
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+"DataAssetWritten2.sfb");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			dataAsset.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals(0, dataAsset.getIndex("amount"));
		assertEquals(1, dataAsset.getIndex("mark"));
		assertEquals(-1, dataAsset.getIndex("int1"));
		assertEquals(-1, dataAsset.getIndex("float1"));
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test B: "+duration+"ms");
	}
}
