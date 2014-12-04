package comparisonTests;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Test;

import android.os.Environment;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;
import shadow.system.data.java.SFInputStreamJava;
import shadow.system.data.java.SFOutputStreamJava;
import shadow.system.data.objects.SFFloat;
import shadow.system.data.tools.DefaultExceptionKeeper;

public class StressingTest001JAVA extends TestCase{

	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";
	
	@Test
	public void testA() {
		
		long startTime = System.nanoTime();
		
		SFFloat float1 = new SFFloat(45.5f);
		
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"StressingTest001.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			float1.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		float1.setFloatValue(88);
		float1.setFloatValue((float)-11.25);
		
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+"StressingTest001.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			float1.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"StressingTest001.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			float1.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		float1.setFloatValue(88);
		float1.setFloatValue((float)-11.25);
		
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+"StressingTest001.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			float1.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"StressingTest001.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			float1.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		float1.setFloatValue(88);
		float1.setFloatValue((float)-11.25);
		
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+"StressingTest001.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			float1.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"StressingTest001.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			float1.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		float1.setFloatValue(88);
		float1.setFloatValue((float)-11.25);
		
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+"StressingTest001.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			float1.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"StressingTest001.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			float1.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		float1.setFloatValue(88);
		float1.setFloatValue((float)-11.25);
		
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+"StressingTest001.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			float1.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"StressingTest001.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			float1.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		float1.setFloatValue(88);
		float1.setFloatValue((float)-11.25);
		
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+"StressingTest001.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			float1.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"StressingTest001.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			float1.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		float1.setFloatValue(88);
		float1.setFloatValue((float)-11.25);
		
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+"StressingTest001.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			float1.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"StressingTest001.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			float1.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		float1.setFloatValue(88);
		float1.setFloatValue((float)-11.25);
		
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+"StressingTest001.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			float1.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"StressingTest001.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			float1.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		float1.setFloatValue(88);
		float1.setFloatValue((float)-11.25);
		
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+"StressingTest001.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			float1.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"StressingTest001.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			float1.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		float1.setFloatValue(88);
		float1.setFloatValue((float)-11.25);
		
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+"StressingTest001.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			float1.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"StressingTest001.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			float1.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		float1.setFloatValue(88);
		float1.setFloatValue((float)-11.25);
		
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+"StressingTest001.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			float1.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"StressingTest001.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			float1.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		float1.setFloatValue(88);
		float1.setFloatValue((float)-11.25);
		
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+"StressingTest001.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			float1.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		float1.setFloatValue(-33.5f);
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"StressingTest001.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			float1.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		float1.setFloatValue(88);
		float1.setFloatValue((float)-11.25);
		
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+"StressingTest001.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			float1.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		assertEquals(-33.5f, float1.getFloatValue());
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test A (JAVA): "+duration+"ms");
		
	}

}
