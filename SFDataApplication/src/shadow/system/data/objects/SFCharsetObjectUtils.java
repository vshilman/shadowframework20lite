package shadow.system.data.objects;

import java.util.ArrayList;
import java.util.StringTokenizer;

import shadow.system.SFException;
import shadow.system.data.SFCharsetObject;

public class SFCharsetObjectUtils {
	
	//NON LA DEVO TESTARE.Dove vedo charset lascio stare tutto.Non mi interessa.

	public static void readFloats(float[] floatValues,String value,String type) throws SFException{
		StringTokenizer tokenizer=new StringTokenizer(value,"(,)",false);
		try {
			int index=0;
			while (tokenizer.hasMoreTokens()) {
				String token=tokenizer.nextToken();
				System.err.println("token "+token);
				floatValues[index]=new Float(token);
				index++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SFException("Malformed "+type+" : "+value);
		}
	}
	
	public static String writeFloats(float[] floatValues){
		String returnString="(";
		if(floatValues.length>0){
			returnString+=floatValues[0];
			for (int i = 1; i < floatValues.length; i++) {
				returnString+=","+floatValues[i];
			}
		}
		return returnString+")";
	}
	
	public static float[] readFloats(String value,String type) throws SFException{
		StringTokenizer tokenizer=new StringTokenizer(value,"(,) \n",false);
		try {
			ArrayList<Float> list=new ArrayList<Float>();
			while (tokenizer.hasMoreTokens()) {
				String token=tokenizer.nextToken();
				list.add(new Float(token));
			}
			float[] values=new float[list.size()];
			for (int i = 0; i < values.length; i++) {
				values[i]=list.get(i);
			}
			return values;
		} catch (Exception e) {
			throw new SFException("Malformed "+type+" : "+value);
		}
	}
	

	public static void readInts(int[] intValues,String value,String type) throws SFException{
		StringTokenizer tokenizer=new StringTokenizer(value,"(,)",false);
		try {
			int index=0;
			while (tokenizer.hasMoreTokens()) {
				String token=tokenizer.nextToken();
				intValues[index]=new Integer(token);
				index++;
			}
		} catch (Exception e) {
			throw new SFException("Malformed "+type+" : "+value);
		}
	}
	
	public static int[] readInts(String value,String type) throws SFException{
		StringTokenizer tokenizer=new StringTokenizer(value,"(,) \n",false);
		try {
			ArrayList<Integer> list=new ArrayList<Integer>();
			while (tokenizer.hasMoreTokens()) {
				String token=tokenizer.nextToken();
				list.add(new Integer(token));
			}
			int[] values=new int[list.size()];
			for (int i = 0; i < values.length; i++) {
				values[i]=list.get(i);
			}
			return values;
		} catch (Exception e) {
			throw new SFException("Malformed "+type+" : "+value);
		}
	}
	
	public static String writeInts(int[] intValues){
		String returnString="(";
		if(intValues.length>0){
			returnString+=intValues[0];
			for (int i = 1; i < intValues.length; i++) {
				returnString+=","+intValues[i];
			}
		}
		return returnString+")";
	}
	
	public static short[] readShorts(String value,String type) throws SFException{
		StringTokenizer tokenizer=new StringTokenizer(value,"(,) \n",false);
		try {
			ArrayList<Short> list=new ArrayList<Short>();
			while (tokenizer.hasMoreTokens()) {
				String token=tokenizer.nextToken();
				list.add(new Short(token));
			}
			short[] values=new short[list.size()];
			for (int i = 0; i < values.length; i++) {
				values[i]=list.get(i);
			}
			return values;
		} catch (Exception e) {
			//TODO : do not throw a message plese!!
			//throw new SFException("Malformed "+type+" : "+value);
			return new short[0];
		}
	}
	
	public static void readShorts(short[] shortValues,String value,String type) throws SFException{
		StringTokenizer tokenizer=new StringTokenizer(value,"(,)",false);
		try {
			short index=0;
			while (tokenizer.hasMoreTokens()) {
				String token=tokenizer.nextToken();
				shortValues[index]=new Short(token);
				index++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SFException("Malformed "+type+" : "+value);
		}
	}
	
	public static String writeShorts(short[] shortValues){
		String returnString="(";
		if(shortValues.length>0){
			returnString+=shortValues[0];
			for (short i = 1; i < shortValues.length; i++) {
				returnString+=","+shortValues[i];
			}
		}
		return returnString+")";
	}
	
	
	
	public static void readCharsetObjects(SFCharsetObject[] objects,String value,String type) throws SFException{
		StringTokenizer tokenizer=new StringTokenizer(value,"(,)",false);
		try {
			short index=0;
			while (tokenizer.hasMoreTokens()) {
				String token=tokenizer.nextToken();
				objects[index].setStringValue(token);
				index++;
			}
		} catch (Exception e) {
			throw new SFException("Malformed "+type+" : "+value);
		}
	}
	
	public static String writeCharsetObjects(SFCharsetObject[] shortValues){
		String returnString="(";
		if(shortValues.length>0){
			returnString+=shortValues[0];
			for (short i = 1; i < shortValues.length; i++) {
				returnString+=","+shortValues[i].toStringValue();
			}
		}
		return returnString+")";
	}
}
