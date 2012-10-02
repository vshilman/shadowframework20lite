package shadow.system.data.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;
import shadow.system.data.java.SFInputStreamJava;
import shadow.system.data.java.SFOutputStreamJava;
import shadow.system.data.objects.SFCompositeDataArray;
import shadow.system.data.objects.SFInt;
import shadow.system.data.objects.SFString;

public class Test001_StoringADataObject {

	public static void main(String[] args) {
		
		MyCompositeDataObject dataObject=new MyCompositeDataObject();
		
		dataObject.intData.setIntValue(2);
		dataObject.intData2.setIntValue(2);
		
		//write it
		try {
			FileOutputStream output=new FileOutputStream("examplesData\\Test001.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			dataObject.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		dataObject=new MyCompositeDataObject();
		dataObject.stringData.setString("Delta");
		
		//read it
		try {
			FileInputStream input=new FileInputStream("examplesData\\Test001.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			dataObject.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		//write it
		try {
			FileOutputStream output=new FileOutputStream("examplesData\\Test001.2.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			dataObject.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static class MyCompositeDataObject extends SFCompositeDataArray{
		
		public SFInt intData;
		public SFInt intData2;
		public SFString stringData;
		
		@Override
		public void generateData() {
			intData=new SFInt(4);
			intData2=new SFInt(4);
			stringData=new SFString("Hello");
			getDataObject().add(intData);
			getDataObject().add(intData2);
			getDataObject().add(stringData);
		}
		
		@Override
		public SFCompositeDataArray clone() {
			return new MyCompositeDataObject();
		}
	}
}
