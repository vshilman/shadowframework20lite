package dataObjectTests;

import junit.framework.TestCase;

import org.junit.Test;

import shadow.system.data.objects.SFIntByteField;

public class SFIntByteFieldTest001 extends TestCase {

	SFIntByteField intByte1 = new SFIntByteField(10);
	SFIntByteField intByte2 = new SFIntByteField(4455);
	SFIntByteField intByte3 = new SFIntByteField(77777);
	
	@Test
	public void testA() {
		
		assertEquals(10, intByte1.getByte(0));
		assertEquals(0, intByte1.getByte(1));
		assertEquals(17, intByte2.getByte(1));
		assertEquals(120, intByte2.getByte(1)+intByte2.getByte(0));
		assertEquals(103, intByte2.getByte(0));
		assertEquals(209, intByte3.getByte(0));
		assertEquals(1, intByte3.getByte(2));
		
		intByte1.setByte(1, 1);
		intByte1.setByte(0, 0);
		
		assertEquals(1, intByte1.getByte(1));
		assertEquals(0, intByte1.getByte(0));
		assertEquals(256, intByte1.getIntValue());
		
		intByte2 = intByte1.copyDataObject();
		
		assertEquals(1, intByte2.getByte(1));
		assertEquals(0, intByte2.getByte(0));
		assertEquals(256, intByte2.getIntValue());
	}
	
	@Test
	public void testB(){
		
	
		intByte1.setStringValue("555");
		intByte2.setStringValue("100");
		
		assertEquals(38, intByte1.getIntValue());
		assertEquals(100, intByte2.getIntValue());
		
	}
}
