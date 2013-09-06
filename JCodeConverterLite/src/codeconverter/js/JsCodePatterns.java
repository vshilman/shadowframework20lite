package codeconverter.js;

import java.util.ArrayList;
import java.util.List;

import tests.tmp.JsPrototypedMethodDeclaration;

import codeconverter.CodePattern;
import codeconverter.js.codelines.JsAttributeAssignmentPattern;
import codeconverter.js.codelines.JsElse;
import codeconverter.js.codelines.JsFor;
import codeconverter.js.codelines.JsIf;
import codeconverter.js.codelines.JsMethodAccess;
import codeconverter.js.codelines.JsReturnPattern;
import codeconverter.js.codelines.JsVariableAssignment;
import codeconverter.js.codelines.JsVariableDeclaration;
import codeconverter.js.codelines.JsVariableDeclarationAndAssignment;
import codeconverter.js.webgl.WebGlMethodAccess;

public class JsCodePatterns {

	private static List<CodePattern> patterns=new ArrayList<CodePattern>();

	public static List<CodePattern> getPatterns(){


		patterns.add(new JsPrototypedMethodDeclaration());

		patterns.add(new JsClassDeclaration());
		patterns.add(new JsMethodDeclaration());
		patterns.add(new JsElse());
		patterns.add(new JsFor());
		patterns.add(new JsIf());
		patterns.add(new JsConstructorDeclaration());
		patterns.add(new JsReturnPattern());
		patterns.add(new JsAttributeAssignmentPattern());
		patterns.add(new JsVariableDeclaration());
		patterns.add(new JsVariableDeclarationAndAssignment());
		patterns.add(new JsVariableAssignment());
		patterns.add(new JsIsolatedKeywords());
		patterns.add(new WebGlMethodAccess());
		patterns.add(new JsMethodAccess());



		return patterns;
	}

}
