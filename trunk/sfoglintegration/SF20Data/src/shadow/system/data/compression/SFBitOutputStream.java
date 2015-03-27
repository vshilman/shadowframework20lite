package shadow.system.data.compression;

import java.util.ArrayList;

/**
 * 
 * 
 * @template Tool
 * 
 * @author Alessandro Martinelli
 */
public class SFBitOutputStream {

	private ArrayList<Byte> bytes=new ArrayList<Byte>();
	
	private int position;
	private int onWrite=0;
	
	public SFBitOutputStream() {
		super();
	}

	public void writeBits(int count,int value){
		
		int first=position;
		position=first+count;
				
		onWrite=onWrite<<count;
		onWrite+=value;
		
		int bytesCount=position>>3;
		
		int diff=position-(bytesCount<<3);
		
		while(bytesCount>bytes.size()){
			
			int delta=bytesCount-bytes.size()-1;
			
			int shift=diff+(delta<<3);
			int valueNew=onWrite>>(shift);
			onWrite-=(valueNew<<(shift));
			
			bytes.add((byte)valueNew);
		}
		
	}
	
	public byte[] getByte(){
		byte[] bs=new byte[this.bytes.size()];
		for (int i = 0; i < bs.length; i++) {
			bs[i]=bytes.get(i);
		}
		return bs;
	}
	
	public ArrayList<Byte> getBytes() {
		return bytes;
	}

}
