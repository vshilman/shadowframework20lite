package main;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Environment;
import shadow.system.data.SFOutputStream;
import shadow.system.data.java.SFOutputStreamJava;
import shadow.system.data.tools.DefaultExceptionKeeper;

public class SFObjectFileWriter {
	
	private SFObjectsBuilder objectsBuilder;
	
	
	SFObjectFileWriter(SFObjectsBuilder objectsBuilder){
		this.objectsBuilder = objectsBuilder;
	}

	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFOrders";
	
	public void saveData(String fileName){
		
		try {
			
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+fileName+".sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			objectsBuilder.getTireDimension().writeOnStream(outputStream);
			objectsBuilder.getTireAmount().writeOnStream(outputStream);
			objectsBuilder.getTireMark().writeOnStream(outputStream);
			objectsBuilder.getTireType().writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
