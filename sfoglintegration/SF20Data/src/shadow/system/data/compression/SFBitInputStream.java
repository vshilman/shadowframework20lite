package shadow.system.data.compression;


/**
 * 
 * @template Tool
 * 
 * @author Alessandro Martinelli
 */
public class SFBitInputStream {

	private byte data[];
	
	private int position;
	
	public SFBitInputStream(byte[] data) {
		super();
		this.data = data;
	}

	private int getByte(int index){
		return data[index]<0?data[index]+256:data[index];
	}

	public int readBits(int count){
		
		if(count>8){
			
			int value=0;
			int index=0;
			while(index+8<count){
				value=value<<8;
				value+=readBits(8);
				index+=8;
			}
			value=value<<(count-index);
			value+=readBits(count-index);
			return value;
		}
		
		int first=position;
		int last=first+count-1;
		position=first+count;
		
		int bId1=first>>3;
		int bId2=last>>3;

		first-=bId1<<3;
		last-=bId2<<3;
		
		if(bId1==bId2){
			
			int data=getByte(bId1);
			
			data = (data << first) & 0xff;
			data = data >> (8-(count));
			
			return data;
		}else if(bId2==bId1+1){

			int data1=getByte(bId1);
			int data2=getByte(bId2);

			data1 = (data1 << first) & 0xff;
			data1 = (data1 >> first) ;
			data1 = data1 << (last+1);
			
			data2 = (data2 >> (7-last));
			
			return data1+data2;
		}	
		
		return 0;
	}
	
}
