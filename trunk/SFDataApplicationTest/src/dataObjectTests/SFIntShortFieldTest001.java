package dataObjectTests;


import junit.framework.TestCase;

import org.junit.Test;

import shadow.system.data.objects.SFIntShortField;

public class SFIntShortFieldTest001 extends TestCase{

	SFIntShortField shortInt1 = new SFIntShortField(55);
	SFIntShortField shortInt2 = new SFIntShortField(871256);
	SFIntShortField shortInt3 = new SFIntShortField(0);
	
	@Test
	public void testA() {
		
		long startTime = System.nanoTime();
		
		assertEquals(55, shortInt1.getShort(0));
		assertEquals(0, shortInt1.getShort(1));
		assertEquals(19288, shortInt2.getShort(0));
		assertEquals(13, shortInt2.getShort(1));
		
		shortInt1.setShort(0, 0);
		shortInt1.setShort(1, 87);
		shortInt2.setShort(1, 0);
		
		assertEquals(87, shortInt1.getShort(1));
		assertEquals(0, shortInt2.getShort(1));
		assertEquals(19288, shortInt2.getShort(0)+shortInt2.getShort(1));
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test A: "+duration+"ms");

	}
	
	@Test
	public void testB(){
		
		long startTime = System.nanoTime();
		
		shortInt3 = shortInt2.copyDataObject();
		assertEquals(19288, shortInt3.getShort(0));
		assertEquals(13, shortInt3.getShort(1));
		
		shortInt3.setStringValue("187");
		
		assertEquals(187, shortInt3.getShort(0));
		assertEquals(0, shortInt3.getShort(1));
		assertEquals("(187,0)", shortInt3.toStringValue());
		assertEquals("(19288,13)", shortInt2.toStringValue());
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime)/1000000;
		
		System.out.println("Durata Test B: "+duration+"ms");
		
	}

}
