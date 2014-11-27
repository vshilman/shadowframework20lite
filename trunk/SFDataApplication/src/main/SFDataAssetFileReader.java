package main;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import shadow.system.data.SFInputStream;
import shadow.system.data.java.SFInputStreamJava;
import shadow.system.data.tools.DefaultExceptionKeeper;
import android.os.Environment;


public class SFDataAssetFileReader {
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFOrders";
	
SFDataAssetBuilder dataAssetBuilder;
	
	SFDataAssetFileReader(SFDataAssetBuilder dataAssetBuilder){
		this.dataAssetBuilder = dataAssetBuilder;
	}
	
	public void readData(String fileName){
		
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+fileName);
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			dataAssetBuilder.setTireDimension(inputStream.readFloat());
			dataAssetBuilder.setTireAmount(inputStream.readInt());
			dataAssetBuilder.setTireMark(inputStream.readString());
			dataAssetBuilder.setTireType(inputStream.readString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
