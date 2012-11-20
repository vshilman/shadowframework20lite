package shadow.system.data.compression;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Lzw {
	
	private static char asChar(byte b){
		char c=(char)(b>=0?b:b+256);
		return c;
	}

	public static int[] compressBytes(byte[] data){
		
		ArrayList<String> dictionary=new ArrayList<String>(); 
		for (int i = 0; i < 256; i++) {
			dictionary.add(""+asChar((byte)i));
		}
		
		ArrayList<Integer> output=new ArrayList<Integer>();
		
		for (int i = 0; i < data.length; i++) {
			String s="";
			int lastIndexOf=-1;
			while(dictionary.contains(s) && i<data.length){
				lastIndexOf=dictionary.indexOf(s);
				s=s+asChar((byte)data[i]);
				i++;
			}
			i--;
			dictionary.add(s);
			output.add(lastIndexOf);
		}
		
		int[] out=new int[output.size()];
		for (int i = 0; i < out.length; i++) {
			out[i]=output.get(i);
		}
		
		return out;
	}
	
	public static byte[] decompressBytes(int[] data){
		ArrayList<String> dictionary=new ArrayList<String>(); 
		for (int i = 0; i < 256; i++) {
			dictionary.add(""+asChar((byte)i));
		}
		
		StringWriter writer=new StringWriter();
		for (int i = 0; i < data.length; i++) {
			String newWorld=dictionary.get(data[i]);
			writer.append(newWorld);
			dictionary.add(newWorld);
		}
		
		char[] chars=writer.toString().toCharArray();
		
		return toBytes(chars);
	}
	
	public static byte[] toBytes(char[] b){
		byte[] cs=new byte[b.length];
		for (int i = 0; i < cs.length; i++) {
			cs[i]=(byte)b[i];
		}
		return cs;
	}
	
	public static char[] toChars(byte[] b){
		char[] cs=new char[b.length];
		for (int i = 0; i < cs.length; i++) {
			cs[i]=(char)b[i];
		}
		return cs;
	}
	
	public static void main(String[] args) {
		
		String s="ALELE";
		//String s="MAMA&MA&MA&M";
		
		byte[] data=toBytes(s.toCharArray());
		
		int[] output=compressBytes(data);
		
		System.out.println(Arrays.toString(output));
		
		byte[] b=decompressBytes(output);
		
		System.out.println(Arrays.toString(toChars(b)));
		
	}
}
