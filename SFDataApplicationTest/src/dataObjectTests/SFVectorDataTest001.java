package dataObjectTests;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Test;

import android.os.Environment;
import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;
import shadow.system.data.java.SFInputStreamJava;
import shadow.system.data.java.SFOutputStreamJava;
import shadow.system.data.objects.SFMatrix3fData;
import shadow.system.data.tools.DefaultExceptionKeeper;


public class SFVectorDataTest001 extends TestCase{
	
	public static final String DIRECTORY = Environment.getExternalStorageDirectory().getAbsolutePath()+"/SFData";


	SFMatrix3f matrix1 = new SFMatrix3f(25,1,7,1,0,-7,1,33,(float)17.9);
	SFMatrix3f matrix2 = new SFMatrix3f();
	SFMatrix3f matrix3 = new SFMatrix3f(1,1,2,2,1,2,1,-2,1);
	
	
	SFMatrix3fData matrixData1 = new SFMatrix3fData(matrix1);
	SFMatrix3fData matrixData2 = new SFMatrix3fData(matrix2);
	SFMatrix3fData matrixData3 = new SFMatrix3fData(matrix3);
	
	@Test
	public void testA() {
		
		long startTime = System.nanoTime();
		
		assertEquals(25, matrixData1.getMatrix3f().getA(), 0);
		assertEquals(1, matrixData1.getMatrix3f().getB(), 0);
		assertEquals(7, matrixData1.getMatrix3f().getC(), 0);
		assertEquals(1, matrixData1.getMatrix3f().getD(), 0);
		assertEquals(0, matrixData1.getMatrix3f().getE(), 0);
		assertEquals(-7, matrixData1.getMatrix3f().getF(), 0);
		assertEquals(1, matrixData1.getMatrix3f().getG(), 0);
		assertEquals(33, matrixData1.getMatrix3f().getH(), 0);
		assertEquals((float)17.9, matrixData1.getMatrix3f().getI(), 0);
		
		assertEquals(1, matrixData2.getMatrix3f().getA(), 0);
		assertEquals(1, matrixData2.getMatrix3f().getE(), 0);
		assertEquals(1, matrixData2.getMatrix3f().getI(), 0);
		
		matrixData2.getMatrix3f().setA(0);
		matrixData2.getMatrix3f().setE(0);
		matrixData2.getMatrix3f().setI(0);
		
		assertEquals(0, matrixData2.getMatrix3f().getA(), 0);
		assertEquals(0, matrixData2.getMatrix3f().getE(), 0);
		assertEquals(0, matrixData2.getMatrix3f().getI(), 0);
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
			
		 System.out.println("Durata Test A: "+duration+"ms");
		
	}
	
//	@Test
//	public void testB(){
//	
//		long startTime = System.nanoTime();
//		
//		assertEquals(1, matrix3.getRotationX((float)Math.PI).getA(), 0);
//		assertEquals(1, matrix3.getRotationY((float)Math.PI).getE(), 0);
//		assertEquals(1, matrix3.getRotationZ((float)Math.PI).getI(), 0);
//		assertEquals(-1, matrix3.getRotationZ((float)Math.PI).getA(), 0);
//	
//		long endTime = System.nanoTime();
//		long duration = (endTime - startTime)/1000000;
//		
//		System.out.println("Durata Test B: "+duration+"ms");
//		
//	
//	}
	
	@Test
	public void testC(){
		
		long startTime = System.nanoTime();
		
		matrixData1.getMatrix3f();
		SFMatrix3f.getTransposed(matrix1);
		
		assertEquals(25, SFMatrix3f.getTransposed(matrix1).getA(), 0);
		assertEquals(1, SFMatrix3f.getTransposed(matrix1).getB(), 0);
		assertEquals(1, SFMatrix3f.getTransposed(matrix1).getC(), 0);
		assertEquals(1, SFMatrix3f.getTransposed(matrix1).getD(), 0);
		assertEquals(0, SFMatrix3f.getTransposed(matrix1).getE(), 0);
		assertEquals(33, SFMatrix3f.getTransposed(matrix1).getF(), 0);
		assertEquals(7, SFMatrix3f.getTransposed(matrix1).getG(), 0);
		assertEquals(-7, SFMatrix3f.getTransposed(matrix1).getH(), 0);
		assertEquals((float)17.9, SFMatrix3f.getTransposed(matrix1).getI(), 0);
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
			
		 System.out.println("Durata Test C: "+duration+"ms");
	}
	
	@Test
	public void testD(){
		
		long startTime = System.nanoTime();
		
		matrixData3.getMatrix3f();
		
		assertEquals(-1, SFMatrix3f.getInverse(matrix3).getA(), 0);
		assertEquals(1, SFMatrix3f.getInverse(matrix3).getB(), 0);
		assertEquals(0, SFMatrix3f.getInverse(matrix3).getC(), 0);
		assertEquals(0, SFMatrix3f.getInverse(matrix3).getD(), 0);
		assertEquals((float)1/5, SFMatrix3f.getInverse(matrix3).getE(), 0);
		assertEquals((float)-2/5, SFMatrix3f.getInverse(matrix3).getF(), 0);
		assertEquals(1, SFMatrix3f.getInverse(matrix3).getG(), 0);
		assertEquals((float)-3/5, SFMatrix3f.getInverse(matrix3).getH(), 0);
		assertEquals((float)1/5, SFMatrix3f.getInverse(matrix3).getI(), 0);
		
		matrixData3.getMatrix3f();
		SFMatrix3f.getIdentity();
		
		assertEquals(1, SFMatrix3f.getIdentity().getA(), 0);
		assertEquals(1, SFMatrix3f.getIdentity().getE(), 0);
		assertEquals(1, SFMatrix3f.getIdentity().getI(), 0);
		
		matrixData3.getMatrix3f();
		
		
		assertEquals(-33, SFMatrix3f.getScale(-33, 178, -14).getA(),0);
		assertEquals(877, SFMatrix3f.getScale(-33, 877, -14).getE(), 0);
		assertEquals(-14, SFMatrix3f.getScale(-33, 178, -14).getI(), 0);
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
			
		 System.out.println("Durata Test D: "+duration+"ms");
		
	}
	
	@Test
	public void testE(){
		
		long startTime = System.nanoTime();
		
		//Esempio stampa matrice 
		
		//System.out.println(matrix3.getScale(388, 17, -365).toString());
		
		SFMatrix3f matrix4 = new SFMatrix3f(1,1,3,2,1,3,1,-2,1);
		SFMatrix3f matrix5 = new SFMatrix3f(0,1,2,2,3,-2,3,-1,1);
		
		matrix4 = matrix4.MultMatrix(matrix5);
		
		assertEquals(11, matrix4.getA(), 0);
		assertEquals(1, matrix4.getB(), 0);
		assertEquals(3, matrix4.getC(), 0);
		assertEquals(11, matrix4.getD(), 0);
		assertEquals(2, matrix4.getE(), 0);
		assertEquals(5, matrix4.getF(), 0);
		assertEquals(-1, matrix4.getG(), 0);
		assertEquals(-6, matrix4.getH(), 0);
		assertEquals(7, matrix4.getI(), 0);
		
		SFVertex3f vertex1 = new SFVertex3f(1,1,1);
		
		vertex1 = matrix4.Mult(vertex1);
		
		assertEquals(15, vertex1.getX(), 0);
		assertEquals(18, vertex1.getY(), 0);
		assertEquals(0, vertex1.getZ(), 0);
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
			
		 System.out.println("Durata Test E: "+duration+"ms");
		
	}
	
	@Test
	public void testF(){
		
		long startTime = System.nanoTime();
		
//		SFMatrix3f matrix6 = new SFMatrix3f(1,0,0,0,1,0,0,0,1);
//		SFVectorData specialMatrix = new SFMatrix3fData(matrix6);

		matrixData1.getMatrix3f().setA(188);
		matrixData3.getMatrix3f().setB(-97);
		
		try {
			FileOutputStream output=new FileOutputStream(DIRECTORY+"/"+"SFVectorDataTest001.sf");
			SFOutputStream outputStream=new SFOutputStreamJava(output, new DefaultExceptionKeeper());
			
			matrixData1.writeOnStream(outputStream);
			matrixData3.writeOnStream(outputStream);
			
			output.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
			matrixData1.getMatrix3f().setA(63);
		
		try {
			FileInputStream input=new FileInputStream(DIRECTORY+"/"+"SFVectorDataTest001.sf");
			SFInputStream inputStream=new SFInputStreamJava(input, new DefaultExceptionKeeper());
			
			matrixData1.readFromStream(inputStream);
			matrixData3.readFromStream(inputStream);
			
			input.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		assertEquals(188, matrixData1.getMatrix3f().getA(), 0);
		assertEquals(-97, matrixData3.getMatrix3f().getB(), 0);
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
			
		 System.out.println("Durata Test F: "+duration+"ms");
		
	}
	
}
