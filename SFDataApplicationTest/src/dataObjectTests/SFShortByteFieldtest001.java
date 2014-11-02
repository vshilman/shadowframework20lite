package dataObjectTests;


import junit.framework.TestCase;

import org.junit.Test;

import shadow.system.data.objects.SFShortByteField;

public class SFShortByteFieldtest001 extends TestCase{

	SFShortByteField shortByte1 = new SFShortByteField((short) 10);
	
	@Test
	public void testA() {
		
		assertEquals((short) 10, shortByte1.getByte(0));
		assertEquals((short) 0, shortByte1.getByte(1));
		
		shortByte1.setByte(1, 2);

		assertEquals((short) 522,  shortByte1.getShortValue());
		assertEquals((short) 2, shortByte1.getByte(1));
		
	}
	
	@Test
	public void testB(){
		
		SFShortByteField shortByte2 = new SFShortByteField((short) 0);
		
		shortByte2 = shortByte1.copyDataObject();
		
		assertEquals((short) 10, shortByte2.getByte(0));
		assertEquals((short) 0, shortByte2.getByte(1));
		
		shortByte2.setByte(1, 2);

		assertEquals((short) 522,  shortByte2.getShortValue());
		assertEquals((short) 2, shortByte2.getByte(1));
		
	}
	
	@Test
	public void testC(){
		
		shortByte1.setStringValue("145");
		
		//System.out.println(shortByte1.getShortValue());
		
		assertEquals((short) 145, shortByte1.getShortValue());
		
		shortByte1.setByte(1, 4);
		
		assertEquals((short) 145, shortByte1.getByte(0));
		assertEquals((short) 4, shortByte1.getByte(1));
		assertEquals((short) 1169, shortByte1.getShortValue());
		
	}
	
}
