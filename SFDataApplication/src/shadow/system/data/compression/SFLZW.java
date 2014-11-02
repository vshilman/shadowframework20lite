package shadow.system.data.compression;

import java.util.ArrayList;

public class SFLZW {
	
	private int dictionarySize;

	class SFOptimizedLZWPattern{
		private short[] value;
		
		public SFOptimizedLZWPattern(short add) {
			super();
			this.value = new short[1];
			this.value[0]=add;
		}

		public SFOptimizedLZWPattern(short[] value,short add) {
			super();
			this.value = new short[value.length+1];
			for (int i = 0; i < value.length; i++) {
				this.value[i]=value[i];
			}
			this.value[value.length]=add;
		}
	}

	class SFLZWPattern extends ArrayList<Short>{

		private static final long serialVersionUID=0;
		
		public SFLZWPattern(int initialCapacity) {
			super(initialCapacity);
		}

		public SFLZWPattern() {
		}
		
		public SFLZWPattern clonePattern() {
			SFLZWPattern pattern = new SFLZWPattern();
			pattern.addAll(this);
			return pattern;
		}

		public boolean equal(SFLZWPattern pattern) {
			if (pattern.size() != this.size())
				return false;

			for (int i = 0; i < pattern.size(); i++) {
				if (pattern.get(i) != this.get(i))
					return false;
			}
			return true;
		}
	}
	
	private class ByteArrayInput{
		private byte[] array;
		private int index=0;
		
		public ByteArrayInput(byte[] array) {
			super();
			this.array = array;
		}
		
		public short read(){
			if(index==array.length)
				return -1;
			index++;
			short val=array[index-1];
			return (short)(val<0?val+256:val);
		}
	}
	
	private class ShortArrayInput{
		private short[] array;
		private int index=0;
		
		public ShortArrayInput(short[] array) {
			super();
			this.array = array;
		}
		
		public short read(){
			if(index==array.length)
				return -1;
			index++;
			return array[index-1];
		}
	}
	
	private class ShortArrayOutput{
		private short[] array;
		private int index=0;
		
		public ShortArrayOutput(short[] array) {
			super();
			this.array = array;
		}
		
		public void write(short value){
			index++;
			array[index-1]=value;
		}
		public int size(){
			return index;
		}
	}
	
	private class ByteArrayOutput{
		private byte[] array;
		private int index=0;
		
		public ByteArrayOutput(byte[] array) {
			super();
			this.array = array;
		}
		
		public void write(byte value){
			index++;
			array[index-1]=value;
		}
		public int size(){
			return index;
		}
	}
	

	private int indexOf(ArrayList<SFLZWPattern> dictionary ,SFLZWPattern pattern) {
		
		if(pattern.size()==1){
			return pattern.get(0);
		}
		
		for (int i = 256; i < dictionary.size(); i++) {
			if (pattern.equals(dictionary.get(i))) {
				return i;
			}
		}
		return -1;
	}

	public int compress(byte[] input, short[] output){

		ArrayList<SFLZWPattern> dictionary = new ArrayList<SFLZWPattern>(2024);
		
		for (int i = 0; i < 256; i++) {
			SFLZWPattern pattern = new SFLZWPattern(1);
			pattern.add((short)i);
			dictionary.add(pattern);
		}
		
		SFLZWPattern pattern = new SFLZWPattern();
		
		ByteArrayInput in=new ByteArrayInput(input);
		ShortArrayOutput out=new ShortArrayOutput(output);
		
		int x = in.read();

		int index = 0;
		while (x != -1) {
			pattern.add((short)x);
			
			index = indexOf(dictionary,pattern);

			if (index < 0) {
				pattern.remove(pattern.size()-1);
				index = indexOf(dictionary,pattern);
				out.write((short)index);
				//writeIndex(out, index);
				pattern.add((short)x);
				dictionary.add(pattern);
				pattern = new SFLZWPattern();
				pattern.add((short)x);
			}

			x = in.read();
		}
		
		index = indexOf(dictionary,pattern);
		out.write((short)index);

		dictionarySize=dictionary.size();
		
		return out.size();
	}

	public int decompress(short[] input, byte[] output){

		ArrayList<SFOptimizedLZWPattern> dictionary = new ArrayList<SFOptimizedLZWPattern>();
		
		for (int i = 0; i < 256; i++) {
			SFOptimizedLZWPattern pattern = new SFOptimizedLZWPattern((short)i);
			dictionary.add(pattern);
		}
		
		ShortArrayInput in=new ShortArrayInput(input);
		ByteArrayOutput out=new ByteArrayOutput(output);
		
		int x = in.read();
		
		short[] buffer={(short)x};
		out.write((byte)dictionary.get(x).value[0]);

		x = in.read();
		
		while (x != -1) {
			
			SFOptimizedLZWPattern substring=dictionary.get(x);
			
			for (int i = 0; i < substring.value.length; i++) {
				out.write((byte)substring.value[i]);
			}
			
			dictionary.add(new SFOptimizedLZWPattern(buffer, substring.value[0]));
			
			buffer=substring.value;
			
			x = in.read();
		}
		
		dictionarySize=dictionary.size();

		return out.size();
	}
	
	public int getDictionarySize(){
		return dictionarySize;
	}
}
