package main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import shadow.system.data.SFOutputStream;
import shadow.system.data.java.SFOutputStreamJava;
import shadow.system.data.tools.DefaultExceptionKeeper;
import android.os.Environment;

public class SFDataAssetFileWriter {
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";
	
	SFDataAssetBuilder dataAssetBuilder;
	
	SFDataAssetFileWriter(SFDataAssetBuilder dataAssetBuilder){
		this.dataAssetBuilder = dataAssetBuilder;
	}
	
	public void writeData(String fileName){
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+fileName+".sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			outputStream.writeFloat(dataAssetBuilder.getTireDimension());
			outputStream.writeInt(dataAssetBuilder.getTireAmount());
			outputStream.writeString(dataAssetBuilder.getTireMark());
			outputStream.writeString(dataAssetBuilder.getTireType());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
