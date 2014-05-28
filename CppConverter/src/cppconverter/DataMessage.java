package cppconverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class DataMessage {

	private String originalMessage;
	private String translation;
	private ArrayList<String> properties=new ArrayList<String>();
	private ArrayList<String> omTokens=new ArrayList<String>();
	private ArrayList<String> trTokens=new ArrayList<String>();
	
	HashMap<String, String> propValues=new HashMap<String, String>();
	
	public DataMessage(String originalMessage, String translation) {
		super();
		//System.err.println("Generating Log message ["+originalMessage+"] "+"["+translation+"]");
		this.originalMessage = originalMessage;
		this.translation = translation;
		
		ArrayList<String> omTokens=tokenize(originalMessage);
		ArrayList<String> trTokens=tokenize(translation);
		
		//System.err.println("omTokens "+omTokens);
		//System.err.println("trTokens "+trTokens);
		
		int startIndex=1;
		if(originalMessage.startsWith("$")){
			startIndex=0;
		}
		
		for (int i = startIndex; i < omTokens.size(); i++) {
			for (int j = startIndex; j < trTokens.size(); j++) {
				if(omTokens.get(i).equalsIgnoreCase(trTokens.get(j))){
					properties.add(omTokens.get(i));
					j=trTokens.size();
					i++;
					j = trTokens.size();
				}
			}
		}
		//System.err.println("\t\t\t properties "+properties);
		this.omTokens=omTokens;
		this.trTokens=trTokens;
	}
	
	public String getOriginalMessage() {
		return originalMessage;
	}
	
	public ArrayList<String> tokenize(String message){
		ArrayList<String> msn=new ArrayList<String>();
		StringTokenizer tokenizer=new StringTokenizer(message,"$");
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			msn.add(token);
		}
		return msn;
	}

	public HashMap<String, String> getPropValues() {
		return propValues;
	}

	public String checkString(String message,HashMap<String, String> propValues){
		
		String lastProperty=null;
		
		this.propValues.clear();

		this.propValues.putAll(propValues);
		
		return evalString(message, lastProperty);
	}
	
	public String checkString(String message){
		
		//message.trim();
			String lastProperty=null;
			
			propValues.clear();
			
			return evalString(message, lastProperty);
	}

	private String evalString(String message, String lastProperty) {
		int index=0;
		String ret;
		for (int i = 0; i < omTokens.size(); i++) {
			String token=omTokens.get(i);
			//System.err.println("token "+token+" "+properties);
			if(properties.contains(token)){
				lastProperty=token;
				//System.err.println("new Property "+lastProperty);
			}else{
				//System.err.println("token ["+token+"] in "+message);
				int position=message.indexOf(token,index);
				//System.err.println("position "+position);
				if(position>=0){
					if(lastProperty!=null){
						//System.err.println("\t\tindex "+index+" position "+position+" "+message+" token "+token);
						String propertyValue=message.substring(index,position);
						propValues.put(lastProperty, propertyValue);
					}
					index=position+token.length();
				}else{
					return null;
				}
			}
		}
		int position=message.length();
		//System.err.println("index "+index+" position "+position);
		if(lastProperty!=null && index!=position){
			String propertyValue=message.substring(index,position);
			propValues.put(lastProperty, propertyValue);
		}			
		
		ArrayList<String> usedValues=new ArrayList<String>();
		
		ret = "";
		for (int i = 0; i < this.trTokens.size(); i++) {
			String token=trTokens.get(i);
			if(properties.contains(token)){
				String value=propValues.get(token);
				if(value==null){
					return null;
				}
				ret+=propValues.get(token);
				usedValues.add(value);
			}else{
				ret+=token;
			}
		}
		
		if(usedValues.size()!=propValues.size())
			return null;
		return ret;
	}

}