package shadow.renderer.data.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.java.SFJavaDataExporter;
import shadow.renderer.data.java.SFXMLDataExporter;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataObject;
import shadow.system.data.SFDataset;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFObjectsLibrary;
import shadow.system.data.java.SFInputStreamJava;
import shadow.system.data.java.SFOutputStreamJava;
import shadow.system.data.tests.DefaultExceptionKeeper;

public class SFDataUtility {

	public static void saveDataset(String root,String name,SFDataset dataset){
		//write it
		try {
			
			FileOutputStream output=new FileOutputStream(root+"/"+name);
			
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
	

	public static SFDataset loadDataset(String name){
		
		//read it
		try {
			
			FileInputStream input=new FileInputStream(name);
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
			FileOutputStream output=new FileOutputStream(root+"/"+name);
			SFOutputStreamJava outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());

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

		try {
			SFXMLDataExporter exporter=new SFXMLDataExporter();
			(new SFJavaDataExporter(exporter)).writeAsset(asset);
			BufferedWriter writer=new BufferedWriter(new FileWriter(new File(
					root+"/"+filename+".xml")));
			writer.write(exporter.getText());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	public static void saveXMLFile(String root, String filename, SFObjectsLibrary library){

		try {
			SFXMLDataExporter exporter=new SFXMLDataExporter();
			(new SFJavaDataExporter(exporter)).writeLibrary(library);
			BufferedWriter writer=new BufferedWriter(new FileWriter(new File(
					root+"/"+filename+".xml")));
			writer.write(exporter.getText());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
