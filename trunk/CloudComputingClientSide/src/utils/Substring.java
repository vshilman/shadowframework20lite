package utils;

import java.util.NoSuchElementException;



/**
 * 
 * @author JwTeam
 *
 */
public class Substring {
	
	private String obtainedString;
	private String sequence;
	private String workOnStringPassed;
	private String substring;
	private String[] listOfSubstring;
	private int	sequenceLenght;
	private int howMany;
	private int i;
	private int count;
	private int cursor;
	private boolean hasMore;
	
/**
 * 
 * This Constructor has the main aim to generate a vector of string, which is the string you passed. Every element of the vector contains the part of string before the specified sequence
 * 
 * For Example a string like "I £$£ am £$£ human" with the sequence " £$£" will be break up in [I , am, human].
 * @param string the string you want to break up
 * @param sequence the sequence delimiter for your string
 */
	public Substring(String string, String sequence) {
		super();
		this.sequenceLenght = sequence.length();
		this.sequence=new String(sequence);
		this.obtainedString=new String(string);
		this.listOfSubstring= new String[howMany()];
		this.cursor=0;
		this.hasMore = false;
		this.i=0;
		spezzetta();
	}

	private void spezzetta() {
		workOnStringPassed=obtainedString;
		while(workOnStringPassed.contains(sequence)){
			listOfSubstring[i]=workOnStringPassed.substring(0, workOnStringPassed.indexOf(sequence));
			workOnStringPassed=workOnStringPassed.substring(workOnStringPassed.indexOf(sequence)+sequenceLenght);
			i++;
		}
		listOfSubstring[i]=workOnStringPassed.substring(0);

	}
	
	/**
	 * @see wiki.generic.substring.test.SuperSubString#nextSubString()
	 */
	public String nextSubString() {
		if (hasMore()) {
			substring=listOfSubstring[cursor];
			cursor++;
		}else{
			throw new NoSuchElementException("there's no more substring with the sequence requested!");
		}
		return substring;
	}
	
	
	/**
	 * @see wiki.generic.substring.test.SuperSubString#hasMore()
	 */
	public boolean hasMore(){
		if (listOfSubstring!=null&&cursor<listOfSubstring.length) {
			hasMore=true;
		}else{
			
			hasMore=false;
		}
		return hasMore;
	}
	
	/** 
	 * @see wiki.generic.substring.test.SuperSubString#howMany()
	 */
	public int howMany(){
		howMany=1;
		workOnStringPassed=obtainedString;
		while (workOnStringPassed.contains(sequence)) {
			workOnStringPassed=workOnStringPassed.substring(workOnStringPassed.indexOf(sequence)+sequenceLenght);
			howMany++;
		}
		return howMany;
		
		
	}
	
	/**
	 * @see wiki.generic.substring.test.SuperSubString#howManyMore()
	 */
	public int howManyMore(){
		
		if (hasMore()) {
			count=listOfSubstring.length-cursor;
		}else{
			count=0;
		}
		return count;
	};
	
	
}
