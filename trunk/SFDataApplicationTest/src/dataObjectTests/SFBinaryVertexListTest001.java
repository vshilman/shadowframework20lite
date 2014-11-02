package dataObjectTests;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import junit.framework.TestCase;
import static org.junit.Assert.*;

import org.junit.Test;

import android.os.Environment;
import shadow.math.SFVertex2f;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;
import shadow.system.data.formats.SFFixedFloat16;
import shadow.system.data.java.SFInputStreamJava;
import shadow.system.data.java.SFOutputStreamJava;
import shadow.system.data.objects.SFBinaryVertexList;
import shadow.system.data.tools.DefaultExceptionKeeper;

public class SFBinaryVertexListTest001 extends TestCase{

	SFBinaryVertexList<SFFixedFloat16> vertices=new SFBinaryVertexList<SFFixedFloat16>(new SFFixedFloat16());
	SFVertex2f vertex1 = new SFVertex2f(10,18.78888899745);
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";
	
	@Test
	public void testA() {
		
		vertices.addValue(vertex1);
		
		float [] expectedValues = {(float)10,(float)18.788};
		assertArrayEquals(expectedValues, vertices.getValues(), 0);
		
		assertEquals(16, vertices.getSize());
		
		assertEquals(1, vertices.getVertexCount());
	
		assertEquals("[10.0, 18.788]", vertices.getValue(0,vertex1).toString());
		
	}
	
	@Test
	public void testB(){
		
		SFVertex2f vertex2 = new SFVertex2f(0, 0);
		
		vertices.addValue(vertex2);
		vertices.addValue(vertex1);
		
		float [] newVertex = {(float)11.3,(float)44.4};
		vertices.addVertex(newVertex);
		
		assertEquals(3, vertices.getVertexCount());
		
		assertEquals("[0.0, 0.0]", vertices.getValue(0, vertex2).toString());
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"SFBinaryVertexListTest001.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			vertices.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+"SFBinaryVertexListTest001.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			vertices.readFromStream(inputStream);

			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//assertEquals("[10.0, 18.788]", vertices.getValue(0,vertex1).toString());
	}

	
}
