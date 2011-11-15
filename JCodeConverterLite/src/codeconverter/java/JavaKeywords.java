package codeconverter.java;

import java.util.LinkedList;
import java.util.List;

public class JavaKeywords {

	public static List<String> keywords=new LinkedList<String>();
	
	static{
		keywords.add("public");
		keywords.add("private");
		keywords.add("protected");
		keywords.add("package");
		keywords.add("class");
		keywords.add("return");
	}
	
	public static boolean isKeyword(String s){
		return keywords.contains(s);
	}
}
