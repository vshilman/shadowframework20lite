package codeconverter.java;

import java.util.LinkedList;
import java.util.List;

import codeconverter.CodeSequence;
import codeconverter.ICodePiece;
import codeconverter.OptionalCode;

public class JavaClassKeywords extends OptionalCode{
	
	private ICodePiece element;
	
	
	public JavaClassKeywords(ICodePiece element) {
		super(element);
		this.element = element;
		
	}

	List<String> names=new LinkedList<String>();
	private CodeSequence sequence=new CodeSequence(new JavaName(),",");
	
	
	@Override
	public int elementMatch(String data, int matchPosition) {
	
		int position=matchPosition;
		int nextIndex=0;
		
		nextIndex=element.elementMatch(data,position);      
		if(nextIndex==-1){
			return position;
		}
        nextIndex = sequence.elementMatch(data, nextIndex+1);
        
		return nextIndex;
	}
	
	public String getNames(){
		int i;
        String name = "";
    	 for(i = 0;  i < sequence.getPieces().size(); i++){
    	   
    		 if(i == 0){
    	    	 
    	    	 name = " " + this.element.toString() + " " ;
    	    	 
    	     }
    		 name +=  this.sequence.getPieces().get(i).toString();
    	
    		 if(i != sequence.getPieces().size()-1){
    			
    			name+= ", ";
    		}
    	
    	 }
    	
     
	 
	return name;
	 
	 
	}
	
	public CodeSequence getSequence(){
		
		return sequence;
		
	}
	
	public void loadSet(CodeSequence sequence){
		
		this.sequence = sequence;
		
	}
}
