package codeconverter.js;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import codeconverter.CodePattern;
import codeconverter.ICodePiece;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.Name;
import codeconverter.codepieces.UniqueKeyword;

//public class TmpJsCodePatterns {
//
//	static{
//	}
//	
//	public static List<CodePattern> getPatterns(){
//	
//		List<CodePattern> patterns=new ArrayList<CodePattern>();
//		ArrayList<ICodePiece> element;
//		
//		element=new ArrayList<ICodePiece>();
//		Collections.addAll(element, new Name(),new StaticKeyword("."),new StaticKeyword("prototype"),
//				new StaticKeyword("="),new StaticKeyword("{"));
//		patterns.add(new CodePattern(element/*,PatternType.CLASS_DECLARATION*/));
//		
//		element=new ArrayList<ICodePiece>();
//		Collections.addAll(element,new StaticKeyword("}"));
//		patterns.add(new CodePattern(element/*,PatternType.BLOCK_CLOSE*/));
//
//		element=new ArrayList<ICodePiece>();
//		Collections.addAll(element,new StaticKeyword("};"));
//		patterns.add(new CodePattern(element/*,PatternType.BLOCK_CLOSE*/));
//
//		element=new ArrayList<ICodePiece>();
//		Collections.addAll(element,new StaticKeyword("},"));
//		patterns.add(new CodePattern(element/*PatternType.BLOCK_CLOSE*/));
//
//		element=new ArrayList<ICodePiece>();
//		Collections.addAll(element,new StaticKeyword("function"),new Name(),new StaticKeyword("("),
//				new CodeSequence(new Name(),", "),new StaticKeyword(")"),
//				new StaticKeyword("{"));
//		patterns.add(new CodePattern(element/*,PatternType.METHOD_DECLARATION*/));
//
//		element=new ArrayList<ICodePiece>();
//		Collections.addAll(element,new StaticKeyword("this."),new Name(),new StaticKeyword("="),new Name(),new StaticKeyword(";"));
//		patterns.add(new CodePattern(element/*,PatternType.ASSIGNMENT*/));
//		
//		element=new ArrayList<ICodePiece>();
//		Collections.addAll(element,new Name(),new StaticKeyword(":"),new StaticKeyword("function"),new StaticKeyword("("),
//				new CodeSequence(new Name(),", "),new StaticKeyword(")"),
//				new StaticKeyword("{"));
//		patterns.add(new CodePattern(element/*,PatternType.METHOD_DECLARATION*/));
//
//		element=new ArrayList<ICodePiece>();
//		Collections.addAll(element,new StaticKeyword("return"),new Name(),new StaticKeyword(";"));
//		patterns.add(new CodePattern(element/*,PatternType.RETURN*/));
//
//		element=new ArrayList<ICodePiece>();
//		Collections.addAll(element,new StaticKeyword("return"),new StaticKeyword("this."),new Name(),new StaticKeyword(";"));
//		patterns.add(new CodePattern(element/*,PatternType.RETURN*/));
//		
//		return patterns;
//	}
//}
