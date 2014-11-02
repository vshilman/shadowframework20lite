package dataObjectTests;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Test;

import android.os.Environment;
import shadow.math.SFValue;
import shadow.math.SFVertex2f;
import shadow.math.SFVertex3f;
import shadow.math.SFVertex4f;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;
import shadow.system.data.formats.SFFixedFloat16;
import shadow.system.data.java.SFInputStreamJava;
import shadow.system.data.java.SFOutputStreamJava;
import shadow.system.data.objects.SFBinaryVertexArrayList;
import shadow.system.data.tools.DefaultExceptionKeeper;

public class SFBinaryVertexArrayListTest001 extends TestCase{

	SFBinaryVertexArrayList<SFFixedFloat16> vertexArray1 = new SFBinaryVertexArrayList<SFFixedFloat16>(new SFFixedFloat16());
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";
	
	SFVertex2f vertex1 = new SFVertex2f(11.58, 77.797);
	SFVertex3f vertex2 = new SFVertex3f(1.78, -0.5, 7.974);
	SFVertex4f vertex3 = new SFVertex4f((float)0.5, (float)-0.5,(float)1.5,(float)-1.5);
	SFVertex2f vertex4 = new SFVertex2f(0, 1.5);
	SFVertex2f vertex5 = new SFVertex2f(-1.5, 0);
	
	SFValue[] valuesArray = {vertex1, vertex2, vertex3};
	SFValue[] valuesArray2 = {vertex4, vertex5};
	
	@Test
	public void testA() {
		
		vertexArray1.addValue(valuesArray);
		vertexArray1.addValue(valuesArray2);
		
		assertEquals(16, vertexArray1.getBitSize());
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"SFBinaryVertexArrayListTest001.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			vertexArray1.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+"SFBinaryVertexArrayListTest001.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			vertexArray1.readFromStream(inputStream);
			
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
	}
	
	@Test
	public void testB(){
		
		vertexArray1.addValue(valuesArray);

		assertEquals(3, vertexArray1.getVertexSize().length);
	
		System.out.println(vertexArray1.getVertexCount());	
	}

}
