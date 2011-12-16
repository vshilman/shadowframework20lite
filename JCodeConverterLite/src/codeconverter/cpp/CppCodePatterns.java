package codeconverter.cpp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import codeconverter.CodePattern;
import codeconverter.CodeSequence;
import codeconverter.ICodePiece;
import codeconverter.Name;
import codeconverter.StaticKeyword;
import codeconverter.VariableDeclarion;
import codeconverter.elements.Modifier;
import codeconverter.elements.Type;

public class CppCodePatterns {

	public static List<CodePattern> getPatterns(){
		
		Modifier modifier=new Modifier();
		modifier.addModifier("public");
		modifier.addModifier("private");
		
		Type type=new Type();
		type.addType("int");
		type.addType("void");
		type.addType("float");
		type.addType("boolean");
		type.addType("short");
		
		List<CodePattern> patterns=new ArrayList<CodePattern>();
		ArrayList<ICodePiece> element;
		
		element=new ArrayList<ICodePiece>();
		Collections.addAll(element, new StaticKeyword("class"),
				new Name(),new StaticKeyword("{"));
		patterns.add(new CodePattern(element/*,PatternType.CLASS_DECLARATION*/));

		element=new ArrayList<ICodePiece>();
		Collections.addAll(element,new CodeSequence(modifier," "),new Name(),new StaticKeyword("("),new CodeSequence(new VariableDeclarion(),", "),new StaticKeyword(")"),
				new StaticKeyword(";"));
		patterns.add(new CodePattern(element/*,PatternType.CONSTRUCTOR_DECLARATION*/));
		
		element=new ArrayList<ICodePiece>();
		Collections.addAll(element,new CodeSequence(modifier," "),type,new Name(),new StaticKeyword(";"));
		patterns.add(new CodePattern(element/*,PatternType.ATTRIBUTE_DECLARATION*/));
		
		element=new ArrayList<ICodePiece>();
		Collections.addAll(element,modifier,new StaticKeyword(":"));
		patterns.add(new CodePattern(element/*,PatternType.VISIBILITY*/));

		element=new ArrayList<ICodePiece>();
		Collections.addAll(element,new CodeSequence(modifier," "),new Name(),new StaticKeyword("("),new CodeSequence(new VariableDeclarion(),", "),new StaticKeyword(")"),
				new StaticKeyword("{"));
		patterns.add(new CodePattern(element/*,PatternType.CONSTRUCTOR_DECLARATION*/));
		
		element=new ArrayList<ICodePiece>();
		Collections.addAll(element,new CodeSequence(modifier," "),new VariableDeclarion(),new StaticKeyword("("),new CodeSequence(new VariableDeclarion(),", "),new StaticKeyword(")"),
				new StaticKeyword(";"));
		patterns.add(new CodePattern(element/*,PatternType.METHOD_DECLARATION*/));
		
		element=new ArrayList<ICodePiece>();
		Collections.addAll(element,new CodeSequence(modifier," "),new VariableDeclarion(),new StaticKeyword("("),new CodeSequence(new VariableDeclarion(),", "),new StaticKeyword(")"),
				new StaticKeyword("{"));
		patterns.add(new CodePattern(element/*,PatternType.METHOD_DECLARATION*/));
		
		element=new ArrayList<ICodePiece>();
		Collections.addAll(element,new StaticKeyword("}"));
		patterns.add(new CodePattern(element/*,PatternType.BLOCK_CLOSE*/));
		
		element=new ArrayList<ICodePiece>();
		Collections.addAll(element,new StaticKeyword("};"));
		patterns.add(new CodePattern(element/*,PatternType.BLOCK_CLOSE*/));

		return patterns;
	}
}
