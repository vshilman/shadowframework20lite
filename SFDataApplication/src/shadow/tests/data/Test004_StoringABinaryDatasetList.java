package shadow.tests.data;

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
import shadow.system.data.tools.DefaultExceptionKeeper;

public class Test004_StoringABinaryDatasetList {
	
	private static final int BEGIN = 7;
	private static final int END = 9;
	//private static final int END = 27;
	private static final int MIN_STORED_VALUE = -3;
	private static final int MAX_STORED_VALUE = 3;

	public static void main(String[] args) {
		
		for (int i = BEGIN; i <=END; i++) {
			testBinary(i, "Test004."+i);	
		}
		
	}

	private static void testBinary(int size, String name) {
		SFBinaryDataList<MyBinaryValue> dataList=new SFBinaryDataList<MyBinaryValue>(new MyBinaryValue(size));
		
		for (int i = MIN_STORED_VALUE; i < MAX_STORED_VALUE; i++) {
			MyBinaryValue value=new MyBinaryValue(size);
			value.setValue(i);
			dataList.getDataObject().add(value);
		}
		
		//write it
		try {
			FileOutputStream output=new FileOutputStream("testsData/data/"+name+".sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			dataList.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		dataList=new SFBinaryDataList<MyBinaryValue>(new MyBinaryValue(size));
		
		//read it
		try {
			FileInputStream input=new FileInputStream("testsData/data/"+name+".sf");
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
		
		private int bitSize=13;
		
		public MyBinaryValue(int bitSize) {
			super();
			this.bitSize = bitSize;
		}
		@Override
		public SFBinaryValue copyDataObject() {
			return new MyBinaryValue(bitSize);
		}
		@Override
		public int getBitSize() {
			return bitSize;
		}
		
		@Override
		public void setValue(int value) {
			super.setValue(value);
		}
		
		@Override
		public void setStringValue(String value) {
			// TODO Auto-generated method stub
			
		}
		
		public String toStringValue() {
			return "";
		}
	}
}
