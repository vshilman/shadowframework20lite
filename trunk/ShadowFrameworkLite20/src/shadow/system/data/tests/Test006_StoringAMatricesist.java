package shadow.system.data.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import shadow.math.SFMatrix3f;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;
import shadow.system.data.java.SFInputStreamJava;
import shadow.system.data.java.SFOutputStreamJava;
import shadow.system.data.objects.SFBinaryDataList;
import shadow.system.data.objects.binaries.SFBinaryRotationMatrix3fLQ;

public class Test006_StoringAMatricesist {

	public static void main(String[] args) {
		
		SFBinaryDataList<SFBinaryRotationMatrix3fLQ> dataList=new SFBinaryDataList<SFBinaryRotationMatrix3fLQ>(new SFBinaryRotationMatrix3fLQ());
		
		for (int i = 0; i < 3; i++) {
			dataList.getDataObject().add(new SFBinaryRotationMatrix3fLQ());
		}
		SFMatrix3f matrix=SFMatrix3f.getIdentity();
		System.out.println(matrix);
		dataList.getDataObject().get(0).setMatrix3f(matrix);
		matrix=SFMatrix3f.getRotationY(0.4f);
		System.out.println(matrix);
		dataList.getDataObject().get(1).setMatrix3f(matrix);
		matrix=SFMatrix3f.getRotationZ(0.4f);
		System.out.println(matrix);
		dataList.getDataObject().get(2).setMatrix3f(matrix);

		stamp(dataList, matrix);
		
		//write it
		try {
			FileOutputStream output=new FileOutputStream("examplesData\\Test006.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			dataList.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		dataList.getDataObject().clear();
		
		//read it
		try {
			FileInputStream input=new FileInputStream("examplesData\\Test006.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			dataList.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		stamp(dataList, matrix);
	}

	private static void stamp(
			SFBinaryDataList<SFBinaryRotationMatrix3fLQ> dataList,
			SFMatrix3f matrix) {
		System.out.println("Stamp:");
		for (int i = 0; i < dataList.getDataObject().size(); i++) {
			dataList.getDataObject().get(i).getMatrix3f(matrix);
			System.out.println(matrix);
		}
	}
	
}
