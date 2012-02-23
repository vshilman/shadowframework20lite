package shadow.system.data.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import shadow.system.data.SFDataObject;
import shadow.system.data.SFDataset;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;
import shadow.system.data.java.SFInputStreamJava;
import shadow.system.data.java.SFOutputStreamJava;
import shadow.system.data.objects.SFIntArray;

public class Test002_StoringADataset {

	public static void main(String[] args) {
		
		MyDataset dataset=new MyDataset();
		
		for(int i=0;i<20;i++){
			dataset.intArray.getIntValues()[i]=i;
		}
		
		//write it
		try {
			FileOutputStream output=new FileOutputStream("examplesData\\Test002.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());

			outputStream.writeString(dataset.getType());
			dataset.getSFDataObject().writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		dataset=new MyDataset();
		
		//read it
		try {
			FileInputStream input=new FileInputStream("examplesData\\Test002.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			String type=inputStream.readString();
			dataset.getSFDataObject().readFromStream(inputStream);
			
			for(int i=0;i<20;i++){
				System.out.println(i+" "+dataset.intArray.getIntValues()[i]);
			}
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static class MyDataset implements SFDataset{
		
		@Override
		public SFDataset generateNewDatasetInstance() {
			return new MyDataset();
		}
		
		public String getType(){
			return "MyDataset";
		}
		
		public SFIntArray intArray=new SFIntArray(20); 

		@Override
		public SFDataObject getSFDataObject() {
			return intArray;
		}
		
	}
}
