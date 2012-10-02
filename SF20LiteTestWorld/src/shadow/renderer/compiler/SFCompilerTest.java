package shadow.renderer.compiler;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import shadow.renderer.data.utils.SFViewerDatasetFactory;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataset;
import shadow.system.data.SFInputStream;
import shadow.system.data.java.SFInputStreamJava;
import shadow.system.data.java.SFOutputStreamJava;
import shadow.system.data.tests.DefaultExceptionKeeper;

public class SFCompilerTest {

	public static void main(String[] args) {
		
		String root="testsData";
		
		SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
		
		SFDataset dataset=loadDataset(root, "test0019.sf");

		saveDataset( "test0019.compiled.sf",dataset);
	}
	
	public static void saveDataset(String name,SFDataset dataset){
		//write it
		try {
			FileOutputStream output=new FileOutputStream("src/shadow/renderer/compiler/"+name);
			SFOutputStreamJava outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			SFCompilerOutputStream compiler=new SFCompilerOutputStream(outputStream);

			//outputStream.writeString(dataset.getCode());
			SFDataCenter.getDataCenter().writeDataset(compiler, dataset);
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
}
