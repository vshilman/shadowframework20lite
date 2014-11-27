package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import shadow.system.data.SFInputStream;
import shadow.system.data.java.SFInputStreamJava;
import shadow.system.data.tools.DefaultExceptionKeeper;
import android.os.Environment;

public class SFObjectFileReader {
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFOrders";

	private SFObjectsBuilder objectsBuilder;
	
	SFObjectFileReader(SFObjectsBuilder objectsBuilder){
		this.objectsBuilder = objectsBuilder;
	}

	public void readData(String fileName){
		
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+fileName);
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			objectsBuilder.getTireDimension().readFromStream(inputStream);
			objectsBuilder.getTireAmount().readFromStream(inputStream);
			objectsBuilder.getTireMark().readFromStream(inputStream);
			objectsBuilder.getTireType().readFromStream(inputStream);

			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
