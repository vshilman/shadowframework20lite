package temp.javatojs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import codeconverter.CodeMatch;
import codeconverter.ICodeElement;
import codeconverter.elements.ClassDefinition;
import codeconverter.elements.Method;
import codeconverter.elements.VariableList;
import codeconverter.java.templates.JavaClassTemplate;

public class JavaToJavascriptConverter {

	/**
	 * 
	 * @param data
	 * @param matches
	 * @param matched
	 *            used to identify unmatched lines of code, so that they can be
	 *            reported at the end of file.
	 * @return
	 */
	public ArrayList<String> covertMatches(List<String> data,
			List<CodeMatch> matches, boolean[] matched) {

		ArrayList<String> outputFile=new ArrayList<String>();

		for (Iterator<CodeMatch> iterator=matches.iterator(); iterator
				.hasNext();) {
			CodeMatch codeMatch=iterator.next();
			
			ICodeElement template=codeMatch.getMatcher();
			if (template instanceof JavaClassTemplate) {
				((JavaClassTemplate) template).setup();
				writeClass(outputFile,
						((JavaClassTemplate) template).getClassDefinition());
			}
		}

		return outputFile;
	}
	
	public String convertToString(VariableList variables){
		String a="";
		if(variables.size()>0)
			a+=variables.get(0).getName();
		for (int i=1; i < variables.size(); i++) {
			a+=", "+variables.get(i).getName();
		}
		return a;
	}

	public void writeClass(ArrayList<String> outputFile,
			ClassDefinition classDefinition) {

		for (int i=0; i < classDefinition.getConstructors().size(); i++) {
			Method method=classDefinition.getConstructors().get(i)
				.getMethodDeclaration();
			
			classDefinition.getConstructors().get(i).setup();
			outputFile.add("function "
					+ method.getDeclaration().getMethodName().getName() + "("+
						convertToString(method.getDeclaration().getList())+") {");
					
			outputFile.add("};");
		}

		outputFile.add(classDefinition.getName() + ".prototype = {");

		for (int i=0; i < classDefinition.getMethods().size(); i++) {
			Method method=classDefinition.getMethods().get(i).getMethodDeclaration();
			classDefinition.getMethods().get(i).setup();
			outputFile.add(method.getDeclaration().getMethodName().getName() + ":function("+
						convertToString(method.getDeclaration().getList())+") {");
			outputFile.add("}"+(i==classDefinition.getMethods().size()-1?"":","));
		}

		outputFile.add("};");
	}

}
