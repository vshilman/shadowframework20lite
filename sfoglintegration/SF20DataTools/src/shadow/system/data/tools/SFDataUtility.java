package shadow.system.data.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import shadow.system.data.SFDataAsset;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataObject;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFObjectsLibrary;
import shadow.system.data.java.SFCompilerInputStream;
import shadow.system.data.java.SFCompilerOutputStream;
import shadow.system.data.java.SFInputStreamJava;
import shadow.system.data.java.SFOutputStreamJava;

public class SFDataUtility {

	public static void saveDataset(String root,String name,SFDataAsset<?> dataset){
		saveDataset(dataset, root+"/"+name);
	}


	public static void saveDataset(SFDataAsset<?> dataset, String filename) {
		//write it
		try {
			
			FileOutputStream output=new FileOutputStream(filename);
			
			SFOutputStreamJava outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());

			//outputStream.writeString(dataset.getCode());
			SFDataCenter.getDataCenter().writeDataset(outputStream, dataset);
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void compileDataset(SFDataAsset<?> dataset, String library) {
		//write it
		try {
			
			FileOutputStream output=new FileOutputStream(library);
			
			SFCompilerOutputStream outputStream=new SFCompilerOutputStream(new SFOutputStreamJava(output, new DefaultExceptionKeeper()));

			//outputStream.writeString(dataset.getCode());
			SFDataCenter.getDataCenter().writeDataset(outputStream, dataset);
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static SFDataAsset<?> loadDataset(String root,String name){
		
		return loadDataset(root+"/"+name);
		
	}
	

	public static SFDataAsset<?> loadDataset(String name){
		
		//read it
		try {
			
			FileInputStream input=new FileInputStream(name);
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			SFDataAsset<?> dataset=SFDataCenter.getDataCenter().readDataset(inputStream,null);
			input.close();
			return dataset;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void loadDataset(String name,SFDataAsset<?> dataset){
		
		//read it
		try {
			
			FileInputStream input=new FileInputStream(name);
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
				
				inputStream.readInt();//skip type loading
				//inputStream.readString();//skip type loading
				dataset.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void loadCompiledDataset(String name,SFDataAsset<?> dataset){
		
		//read it
		try {
			
			FileInputStream input=new FileInputStream(name);
			SFCompilerInputStream inputStream=new SFCompilerInputStream(new SFInputStreamJava(input, new DefaultExceptionKeeper()));
				
			inputStream.readInt();//skip type loading
			//	inputStream.readString();//skip type loading
			dataset.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void saveDataObject(String root,String name,SFDataObject data){
		String filename = root+"/"+name;
		//write it
		saveDataObject(filename , data);
	}

	
	public static void loadDataObject(String root,String name,SFDataObject data){
		
		//read it
		String filename = root+"/"+name;
		loadDataObject(filename, data);
		
	}


	public static void saveDataObject(String filename, SFDataObject data) {
		try {
			FileOutputStream output=new FileOutputStream(filename);
			SFOutputStreamJava outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());

			data.writeOnStream(outputStream);
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void loadDataObject( String filename, SFDataObject data) {
		try {
			FileInputStream input=new FileInputStream(filename);
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			data.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveXMLFile(String root, String filename, String name,SFDataObject dataObject){

		try {
			SFXMLDataExporter exporter=new SFXMLDataExporter();
			(new SFJavaDataExporter(exporter)).evaluateDataObject(name,dataObject);
			BufferedWriter writer=new BufferedWriter(new FileWriter(new File(
					root+"/"+filename+".xml")));
			writer.write(exporter.getText());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void saveXMLFile(String root, String filename, SFDataAsset<?> asset){

		String compleFilename=root+"/"+filename+".xml";
		saveXMLFile(asset, compleFilename);
		
	}


	public static void saveXMLFile(SFDataAsset<?> asset, String compleFilename) {
		try {
			SFXMLDataExporter exporter=new SFXMLDataExporter();
			(new SFJavaDataExporter(exporter)).writeAsset(asset);
			BufferedWriter writer=new BufferedWriter(new FileWriter(new File(
					compleFilename)));
			writer.write(exporter.getText());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public static void saveXMLFile(String root, String filename, SFObjectsLibrary library){

		String compleFilename=root+"/"+filename+".xml";
		saveXMLFile(library, compleFilename);
		
	}


	public static void saveXMLFile(SFObjectsLibrary library,
			String compleFilename) {
		try {
			SFXMLDataExporter exporter=new SFXMLDataExporter();
			(new SFJavaDataExporter(exporter)).writeLibrary(library);
			BufferedWriter writer=new BufferedWriter(new FileWriter(new File(
					compleFilename)));
			writer.write(exporter.getText());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
