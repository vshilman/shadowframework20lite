package shadow.renderer.tests.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataset;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;
import shadow.system.data.java.SFInputStreamJava;
import shadow.system.data.java.SFOutputStreamJava;
import shadow.system.data.tests.DefaultExceptionKeeper;

public class SFDataUtility {

	public static void saveDataset(String root,String name,SFDataset dataset){
		//write it
		try {
			FileOutputStream output=new FileOutputStream(root+"/"+name);
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());

			//outputStream.writeString(dataset.getCode());
			outputStream.writeString(dataset.getType());
			dataset.getSFDataObject().writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static SFDataset loadDataset(String root,String name){
		
		//read it
		try {
			FileInputStream input=new FileInputStream(root+"/"+name);
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			//String assetName=inputStream.readString();
			String type=inputStream.readString();
			SFDataset dataset=SFDataCenter.getDataCenter().createDataset(type);
			dataset.getSFDataObject().readFromStream(inputStream);
			
			input.close();
			
			return dataset;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
