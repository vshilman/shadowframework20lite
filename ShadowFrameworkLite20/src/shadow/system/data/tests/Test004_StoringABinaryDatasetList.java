package shadow.system.data.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;
import shadow.system.data.java.SFInputStreamJava;
import shadow.system.data.java.SFOutputStreamJava;
import shadow.system.data.objects.SFBinaryDataList;
import shadow.system.data.objects.SFBinaryValue;

public class Test004_StoringABinaryDatasetList {

	public static void main(String[] args) {
		
		SFBinaryDataList<MyBinaryValue> dataList=new SFBinaryDataList<MyBinaryValue>(new MyBinaryValue());
		
		for (int i = 0; i < 10; i++) {
			MyBinaryValue value=new MyBinaryValue();
			value.setValue(i);
			dataList.getDataObject().add(value);
		}
		
		//write it
		try {
			FileOutputStream output=new FileOutputStream("examplesData\\Test004.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			dataList.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		dataList=new SFBinaryDataList<MyBinaryValue>(new MyBinaryValue());
		
		//read it
		try {
			FileInputStream input=new FileInputStream("examplesData\\Test004.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			dataList.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < dataList.getDataObject().size(); i++) {
			System.out.println(dataList.getDataObject().get(i).getValue());
		}
	}
	
	public static class MyBinaryValue extends SFBinaryValue{
		@Override
		public SFBinaryValue clone() {
			return new MyBinaryValue();
		}
		@Override
		public int getBitSize() {
			return 13;
		}
		
		@Override
		public void setValue(int value) {
			super.setValue(value);
		}
	}
}
