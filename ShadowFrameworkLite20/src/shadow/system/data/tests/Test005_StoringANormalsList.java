package shadow.system.data.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import shadow.math.SFVertex3f;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;
import shadow.system.data.java.SFInputStreamJava;
import shadow.system.data.java.SFOutputStreamJava;
import shadow.system.data.objects.SFBinaryDataList;
import shadow.system.data.objects.binaries.SFBinaryUnitVector3FLQ;

public class Test005_StoringANormalsList {

	public static void main(String[] args) {
		
		SFBinaryDataList<SFBinaryUnitVector3FLQ> dataList=new SFBinaryDataList<SFBinaryUnitVector3FLQ>(new SFBinaryUnitVector3FLQ());
		
		for (int i = 0; i < 3; i++) {
			dataList.getDataObject().add(new SFBinaryUnitVector3FLQ());
		}
		SFVertex3f normal=new SFVertex3f(1,0,0);
		dataList.getDataObject().get(0).setVertex3f(normal);
		normal.set3f(0.707f,0.707f,0);
		dataList.getDataObject().get(1).setVertex3f(normal);
		normal.set3f(0,0,1);
		dataList.getDataObject().get(2).setVertex3f(normal);
		
		//write it
		try {
			FileOutputStream output=new FileOutputStream("examplesData\\Test005.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			dataList.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		dataList=new SFBinaryDataList<SFBinaryUnitVector3FLQ>(new SFBinaryUnitVector3FLQ());
		
		//read it
		try {
			FileInputStream input=new FileInputStream("examplesData\\Test005.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			dataList.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		SFVertex3f vertex=new SFVertex3f();
		for (int i = 0; i < dataList.getDataObject().size(); i++) {
			dataList.getDataObject().get(i).getVertex3f(vertex);
			System.out.println(vertex);
		}
	}
	
}
