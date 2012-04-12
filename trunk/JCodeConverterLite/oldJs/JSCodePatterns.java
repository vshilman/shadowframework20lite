package codeconverter.js.oldJs;

import java.util.ArrayList;
import java.util.List;

import codeconverter.CodePattern;

public class JSCodePatterns {

	private static List<CodePattern> patterns=new ArrayList<CodePattern>();
	
	public static List<CodePattern> getPatterns(){
	
		patterns.add(new JsBlockClose());
		patterns.add(new JsReturnPattern());
		patterns.add(new JsAttributeAssignmentPattern());
		patterns.add(new JsConstructorDeclaration());
		patterns.add(new JsMethodDeclaration());
		patterns.add(new JsClassDeclaration());
		
		return patterns;
	}

}