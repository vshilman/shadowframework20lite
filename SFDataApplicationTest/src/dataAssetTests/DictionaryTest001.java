package dataAssetTests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Test;

import android.os.Environment;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFObjectsLibrary;
import shadow.system.data.SFOutputStream;
import shadow.system.data.java.SFInputStreamJava;
import shadow.system.data.java.SFOutputStreamJava;
import shadow.system.data.tools.DefaultExceptionKeeper;
import shadow.system.data.tools.SFDataBuilder;
import tests.utils.DataAsset001;
import tests.utils.DictionaryUtils;

public class DictionaryTest001 extends TestCase{
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";


	@Test
	public void testA() {
		
		long startTime = System.nanoTime();
			
		SFDataCenter.setDictionary(new DictionaryUtils());
		
		SFDataBuilder dataBuilder=new SFDataBuilder();
		
		SFObjectsLibrary library=new SFObjectsLibrary();
		
		dataBuilder.loadBuilderData(DIRECTORY+"/"+"Ordine.sfb", library);
		
		try {
			DataAsset001[] dataAssets={ 
					(DataAsset001)(SFDataCenter.makeDatasetAvailable("TestDataAsset01").getResource()),
					(DataAsset001)(SFDataCenter.makeDatasetAvailable("TestDataAsset02").getResource()),
					(DataAsset001)(SFDataCenter.makeDatasetAvailable("TestDataAsset03").getResource()),
					(DataAsset001)(SFDataCenter.makeDatasetAvailable("TestDataAsset04").getResource()),
			};
			
			for (int i = 0; i < dataAssets.length; i++) {
				System.out.println(dataAssets[i]);
				assertEquals("[1,Michelin]", dataAssets[0].toString());
				assertEquals("[154,Bridgestone]", dataAssets[1].toString());
				assertEquals("[78,Bridgestone]", dataAssets[2].toString());
				assertEquals("[154,KGB]", dataAssets[3].toString());
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test A: "+duration+"ms");
	}
	
	@Test
	public void testB(){
		
		long startTime = System.nanoTime();
		
		DictionaryUtils dictionary = new DictionaryUtils();
		
		SFDataCenter.setDictionary(new DictionaryUtils());
		
		SFDataBuilder dataBuilder=new SFDataBuilder();
		
		SFObjectsLibrary library=new SFObjectsLibrary();
		
		dataBuilder.loadBuilderData(DIRECTORY+"/"+"Ordine.sfb", library);
		
		
			DataAsset001[] dataAssets={ 
					(DataAsset001)(SFDataCenter.makeDatasetAvailable("TestDataAsset01").getResource()),
					(DataAsset001)(SFDataCenter.makeDatasetAvailable("TestDataAsset02").getResource()),
					(DataAsset001)(SFDataCenter.makeDatasetAvailable("TestDataAsset03").getResource()),
					(DataAsset001)(SFDataCenter.makeDatasetAvailable("TestDataAsset04").getResource()),
			};
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"DataWritten.sfb");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			dictionary.writeDataset(outputStream, dataAssets[0]);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		dataAssets[0] = dataAssets[1];
		
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+"DataWritten.sfb");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			dictionary.readDataset(inputStream, "TestDataAsset02");
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(dataAssets[0].toString());
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test B: "+duration+"ms");
	}
}

