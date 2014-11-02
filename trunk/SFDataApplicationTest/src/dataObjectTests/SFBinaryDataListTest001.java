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
import shadow.system.data.formats.SFFixedFloat24;
import shadow.system.data.java.SFInputStreamJava;
import shadow.system.data.java.SFOutputStreamJava;
import shadow.system.data.objects.SFBinaryDataList;
import shadow.system.data.tools.DefaultExceptionKeeper;


public class SFBinaryDataListTest001 extends TestCase{

	SFBinaryDataList<SFFixedFloat24> binaryObject = new SFBinaryDataList<SFFixedFloat24>(new SFFixedFloat24());
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";
	
	
	@Test
	public void testA() {
		
		binaryObject.addCharSetObjects("0.5");
		binaryObject.addCharSetObjects("0.87");
		binaryObject.addCharSetObjects("1.78");
		
		assertEquals(3, binaryObject.getSize());
		
		assertEquals("0.5", binaryObject.getCharSetObjectString(0));
		assertEquals("0.87", binaryObject.getCharSetObjectString(1));
		assertEquals("1.78", binaryObject.getCharSetObjectString(2));
		
	}
	
	@Test
	public void testB(){
		
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"SFBinaryDataListTest001.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			binaryObject.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+"SFBinaryDataListTest001.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			binaryObject.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
