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
import shadow.system.data.objects.SFVertex3fData;

public class Test007_StoringAVertex3fData {

	public static void main(String[] args) {
		
		MyCompositeDataObject3f dataObject=new MyCompositeDataObject3f();
		
		dataObject.floatData.getVertex3f().set3f(1.1f, 0, 0);
		
		//write it
		try {
			FileOutputStream output=new FileOutputStream("examplesData\\Test007.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			dataObject.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		dataObject=new MyCompositeDataObject3f();
		dataObject.floatData.getVertex3f().set3f(5, 5, 5);
		
		//read it
		try {
			FileInputStream input=new FileInputStream("examplesData\\Test007.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			dataObject.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.err.println(dataObject.floatData.getVertex3f());
	}
	
	
	public static class MyCompositeDataObject3f extends SFCompositeDataArray{
		
		public SFVertex3fData floatData;
		
		@Override
		public void generateData() {
			floatData=new SFVertex3fData();
			floatData.getVertex3f().set3f(0,0,0);
			getDataObject().add(floatData);
		}
		@Override
		public SFCompositeDataArray clone() {
			return new MyCompositeDataObject3f();
		}
	}
}
