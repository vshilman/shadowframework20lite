package shadow.renderer.viewer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataObject;
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
			FileOutputStream output=new FileOutputStream(root+"\\"+name);
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());

			//outputStream.writeString(dataset.getCode());
			SFDataCenter.getDataCenter().writeDataset(outputStream, dataset);
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
			SFDataset dataset=SFDataCenter.getDataCenter().readDataset(inputStream);
			input.close();
			return dataset;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public static void saveDataObject(String root,String name,SFDataObject data){
		//write it
		try {
			FileOutputStream output=new FileOutputStream(root+"\\"+name);
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());

			data.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadDataset(String root,String name,SFDataObject data){
		
		//read it
		try {
			FileInputStream input=new FileInputStream(root+"/"+name);
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			//String assetName=inputStream.readString();
			data.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
