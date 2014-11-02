package shadow.tests.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;
import shadow.system.data.java.SFInputStreamJava;
import shadow.system.data.java.SFOutputStreamJava;
import shadow.system.data.objects.SFDataList;
import shadow.system.data.objects.SFFloat;
import shadow.system.data.tools.DefaultExceptionKeeper;

public class Test003_StoringADatasetList {

	public static void main(String[] args) {
		
		SFDataList<SFFloat> myDataList=new SFDataList<SFFloat>(new SFFloat(0));
		
		myDataList.getDataObject().add(new SFFloat(23));
		myDataList.getDataObject().add(new SFFloat(20.11f));
		myDataList.getDataObject().add(new SFFloat(12));
		myDataList.getDataObject().add(new SFFloat(11));
		
		//write it
		try {
			FileOutputStream output=new FileOutputStream("testsData/data/Test003.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			myDataList.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		myDataList=new SFDataList<SFFloat>(new SFFloat(0));
		
		//read it
		try {
			FileInputStream input=new FileInputStream("testsData/data/Test003.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			myDataList.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < myDataList.getDataObject().size(); i++) {
			System.out.println(myDataList.getDataObject().get(i).getFloatValue());
		}
	}
	
}
