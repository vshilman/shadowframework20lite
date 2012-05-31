package codeconverter.js.oldJs;

import java.util.LinkedList;
import java.util.List;

public class JsKeywords {

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
