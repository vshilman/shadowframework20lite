package dataObjectTests;

import junit.framework.TestCase;

import org.junit.Test;

import shadow.math.SFVertex2f;

public class SFVertex2fDataTest001 extends TestCase{

	SFVertex2f vertex1 = new SFVertex2f(30,-5);
	SFVertex2f vertex2 = new SFVertex2f(1,-1);
	SFVertex2f vertex3 = new SFVertex2f((float)1.152,(float)-7.78);
	
	@Test
	public void testA() {
		
		assertEquals(30, vertex1.getX(), 0);
		assertEquals(-5, vertex1.getY(), 0);
		
		vertex1.mult(2);
		
		assertEquals(60, vertex1.getX(), 0);
		assertEquals(-10, vertex1.getY(), 0);
		
		vertex1.setX(30);
		vertex1.setY(-5);
		
		vertex1.add2f(vertex2);
		
		assertEquals(31, vertex1.getX(), 0);
		assertEquals(-6, vertex1.getY(), 0);
		
		vertex1.addMult2f(2, vertex2);
		
		assertEquals(33, vertex1.getX(), 0);
		assertEquals(-8, vertex1.getY(), 0);
		
	}

	@Test
	public void testB(){
		
		vertex1.scale2f(2, 5);
		
		assertEquals(60, vertex1.getX(), 0);
		assertEquals(-25, vertex1.getY(), 0);
		
		assertEquals(85, vertex1.dot2f(vertex2), 0);
		
		assertEquals(65, vertex1.getLength(), 0);
	}
	
	@Test
	public void testC(){
		
		vertex1.set2f(1, 1);
	
		assertEquals(1, vertex1.getX(), 0);
		assertEquals(1, vertex1.getY(), 0);
		
		vertex1.subtract2f(vertex2);
		
		assertEquals(0, vertex1.getX(), 0);
		assertEquals(2, vertex1.getY(), 0);
	}
	
}
