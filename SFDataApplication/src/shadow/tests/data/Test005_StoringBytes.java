package shadow.tests.data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;
import shadow.system.data.java.SFInputStreamJava;
import shadow.system.data.java.SFOutputStreamJava;
import shadow.system.data.tools.DefaultExceptionKeeper;

public class Test005_StoringBytes {

	public static void main(String[] args) {
		
		//write it
		try {
			FileOutputStream output=new FileOutputStream("testsData/data/Test005.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			outputStream.writeByte(2);
			outputStream.writeByte(3);
			outputStream.writeByte(4);
			outputStream.writeByte(-2);
			outputStream.writeByte(-3);
			outputStream.writeByte(-4);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		//read it
		try {
			FileInputStream input=new FileInputStream("testsData/data/Test005.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());

			for (int i = 0; i < 6; i++) {
				System.out.println(inputStream.readByte());
			}
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}