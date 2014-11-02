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
import shadow.system.data.objects.SFCompositeDataArray;
import shadow.system.data.objects.SFInt;
import shadow.system.data.objects.SFLong;
import shadow.system.data.objects.SFString;
import shadow.system.data.tools.DefaultExceptionKeeper;
import shadow.tests.data.Test001_StoringADataObject.MyCompositeDataObject;

public class SFCompositeDataArrayTest001 extends TestCase{

	PersonalCompositeDataObject dataObject=new PersonalCompositeDataObject();
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";
	
	@Test
	public void testA() {

		assertEquals(20, dataObject.intData1.getIntValue());
		assertEquals(-96, dataObject.intData2.getIntValue());
		assertEquals("Torta", dataObject.string1.getString());
		assertEquals(14789, dataObject.long1.getLongValue());
		assertEquals((long)87875669, dataObject.long2.getLongValue());
		
		assertEquals(5, dataObject.elementsSize());
	}
	
	@Test
	public void testB() {

		dataObject.intData1.setIntValue(0);
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"SFCompositeDataArraytest001.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			dataObject.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		dataObject.intData1.setIntValue(174);
		
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+"SFCompositeDataArraytest001.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			dataObject.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(0, dataObject.intData1.getIntValue());
		assertEquals(-96, dataObject.intData2.getIntValue());
		assertEquals("Torta", dataObject.string1.getString());
		assertEquals(14789, dataObject.long1.getLongValue());
		assertEquals((long)87875669, dataObject.long2.getLongValue());
		
		
	}
	
public static class PersonalCompositeDataObject extends SFCompositeDataArray{
		
		public SFInt intData1;
		public SFInt intData2;
		public SFString string1;
		public SFLong long1;
		public SFLong long2;
		
		@Override
		public void generateData() {
			intData1 = new SFInt(20);
			intData2=new SFInt(-96);
			string1=new SFString("Torta");
			long1 = new SFLong(14789);
			long2 = new SFLong((long)87875669);
			
			getDataObject().add(intData1);
			getDataObject().add(intData2);
			getDataObject().add(string1);
			getDataObject().add(long1);
			getDataObject().add(long2);
		}
		
		@Override
		public SFCompositeDataArray copyDataObject() {
			return new MyCompositeDataObject();
		}
	}

}


