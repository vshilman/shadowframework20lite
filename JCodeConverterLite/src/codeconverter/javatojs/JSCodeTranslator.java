package codeconverter.javatojs;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import codeconverter.Block;
import codeconverter.CodeLine;
import codeconverter.CodeModule;
import codeconverter.CodePattern;
import codeconverter.CodeTranslator;
import codeconverter.DeclaredBlock;
import codeconverter.ICodePiece;
import codeconverter.PatternType;
import codeconverter.PieceType;

public class JSCodeTranslator implements CodeTranslator{

	
	@Override
	public String translateCode(Block mainBlock,
			HashMap<CodeModule, CodePattern> relatedPatterns) {

		StringWriter writer=new StringWriter();
		
		evaluateClass(writer, mainBlock, relatedPatterns);
		
		return writer.toString();
	}
	
	
	public void evaluateClass(StringWriter writer, Block mainBlock,
			HashMap<CodeModule, CodePattern> relatedPatterns){
		
		writer.write("\n");
		
		DeclaredBlock classDefinition = null;
		ArrayList<DeclaredBlock> constructors=new ArrayList<DeclaredBlock>();
		ArrayList<DeclaredBlock> methods=new ArrayList<DeclaredBlock>();
		ArrayList<CodeLine> attributes=new ArrayList<CodeLine>();
		ArrayList<CodeModule> addictionalLinesOfCode=new ArrayList<CodeModule>();
		

//		System.err.println("relatedPatterns "+relatedPatterns.size());
//		for (CodeModule codeModule : relatedPatterns.keySet()) {
//			System.out.println(" module: "+codeModule+" "+relatedPatterns.get(codeModule));
//		}
		
		//iterate other 
		for (int i = 0; i < mainBlock.getSize(); i++) {
			CodeModule module=mainBlock.getSubModule(i);
			CodePattern pattern=relatedPatterns.get(module);
			if(pattern!=null && pattern.getPatternType().contains(PatternType.CLASS_DECLARATION)){
				classDefinition=(DeclaredBlock)module;
				Block classBlock=classDefinition.getRelatedBlock();
				for (int j = 0; j < classBlock.getSize(); j++) {
					CodeModule classModule=classBlock.getSubModule(j);
					CodePattern classModulePattern=relatedPatterns.get(classModule);
					System.err.println(classModule);
					if(classModulePattern!=null){
						if(classModulePattern.getPatternType().contains(PatternType.CONSTRUCTOR_DECLARATION)){
							constructors.add((DeclaredBlock)classModule);
						}else if(classModulePattern.getPatternType().contains(PatternType.METHOD_DECLARATION)){
							methods.add((DeclaredBlock)classModule);
						}else if(classModulePattern.getPatternType().contains(PatternType.ATTRIBUTE_DECLARATION)){
							attributes.add((CodeLine)classModule);
						}else{
							addictionalLinesOfCode.add(classModule);
						}
					}
				}
			}
		}
		
		CodePattern classPattern=relatedPatterns.get(classDefinition);

		if(classPattern!=null){
			
			ICodePiece className=classPattern.getPieceByType(PieceType.NAME);

			for (int i = 0; i < constructors.size(); i++) {
				writeConstructor(writer,constructors.get(i),attributes,relatedPatterns);	
			}
			if(constructors.size()==0){
				writer.write("function "+className+"(){\n");
				writer.write("}\n");
			}
			
			//where are all the methods gone?
			
			writer.write("\n"+className+".prototype = {\n");
			
			System.err.println("Methods "+methods.size());
			for (int i = 0; i < methods.size(); i++) {
				writeMethod(writer,methods.get(i),relatedPatterns,i==methods.size()-1);	
			}
			writer.write("};");

		}
	}
	
	public String extractMethodSequence(CodePattern pattern){
		
		ICodePiece sequence=pattern.getPieceByType(PieceType.METHOD_VARIABLES);
		List<ICodePiece> list=sequence.getPieces();
		String data="";
		boolean first=true;
		for (ICodePiece iCodePiece : list) {
			if(iCodePiece.getPieceType()==PieceType.VARIABLE){
				if(!first)
					data+=", ";
				data+=iCodePiece.getPieceByType(PieceType.NAME).toString();
				first=false;
			}
		}
		return data;
	}
	

	static HashMap<String, String> typeExpressionMap=new LinkedHashMap<String, String>();
	
	static{
		typeExpressionMap.put("int","0");
		typeExpressionMap.put("float","0");
		typeExpressionMap.put("char","0");
		typeExpressionMap.put("byte","0");
		typeExpressionMap.put("double","0");
		typeExpressionMap.put("double","0");
	}
	
	private void writeAttributes(StringWriter writer,ArrayList<CodeLine> attributes,HashMap<CodeModule, CodePattern> relatedPatterns){
		for (int i=0; i < attributes.size(); i++) {
			CodeLine attributeLine=attributes.get(i);
			CodePattern attribute=relatedPatterns.get(attributeLine);
			ICodePiece type=attribute.getPieceByType(PieceType.VARIABLE).getPieceByType(PieceType.TYPE);
			ICodePiece name=attribute.getPieceByType(PieceType.VARIABLE).getPieceByType(PieceType.NAME);
			ICodePiece expression=attribute.getPieceByType(PieceType.EXPRESSION);
			if(expression==null){
				writer.write("\t\tthis."+name+"="+typeExpressionMap.get(type.toString())+";\n");
			}else{
				writer.write("\t\tthis."+name+"="+expression.toString()+";\n");
			}
		}
	}
	
	private void writeConstructor(StringWriter writer,DeclaredBlock constructor,
			ArrayList<CodeLine> attributes,HashMap<CodeModule, CodePattern> relatedPatterns){
		
		CodePattern pattern=relatedPatterns.get(constructor);
	
		String methodName=pattern.getPieceByType(PieceType.NAME).toString();
			
			String data=extractMethodSequence(pattern);
			
		writer.write("function "+methodName+"("+data+"){\n");
		
			writeAttributes(writer,attributes,relatedPatterns);
			writeAllBlockModules(writer, constructor, relatedPatterns);
		
		writer.write("}\n");
	}


	private void writeAllBlockModules(StringWriter writer,
			DeclaredBlock constructor,
			HashMap<CodeModule, CodePattern> relatedPatterns) {
		for (int i = 0; i < constructor.getRelatedBlock().getSize(); i++) {
			writeCodeModule(writer, constructor.getRelatedBlock().getSubModule(i), relatedPatterns);
		}
	}
	
	private void writeMethod(StringWriter writer,DeclaredBlock constructor,HashMap<CodeModule, CodePattern> relatedPatterns,boolean lastMethod){

		CodePattern pattern=relatedPatterns.get(constructor);

		String methodName=extractVariableName(pattern);
		
			String data=extractMethodSequence(pattern);
//			if(list.size()>0){
//				data+=list.get(0).getPieces().get(1);
//				for (int i = 2; i < list.size(); i+=2) {
//					data+=", "+list.get(i).getPieces().get(1);
//				}
//			}
		
		writer.write("\n\t"+methodName+":function("+data+"){\n");
		
			writeAllBlockModules(writer, constructor, relatedPatterns);
			
		writer.write("\t}"+(lastMethod?"\n":",")+"\n");
	}


	public String extractVariableName(CodePattern pattern) {
		ICodePiece namePiece=pattern.getPieceByType(PieceType.VARIABLE);
		String methodName=namePiece.getPieceByType(PieceType.NAME).toString();
		return methodName;
	}


	private void writeCodeModule(StringWriter writer,CodeModule module,HashMap<CodeModule, CodePattern> relatedPatterns){
		if(module instanceof CodeLine){
			CodePattern piece=relatedPatterns.get(module);
			if(piece!=null){
				if(piece.getPatternType().contains(PatternType.SUPER))
					return;
				writer.write("\t\t"+piece+";\n");	
			}else{
				writer.write("\t"+(((CodeLine)module).getCodeLine())+";//Warning: Not well Identified \n");
			}
		}else{
			DeclaredBlock block=(DeclaredBlock)module;
			CodePattern pattern=relatedPatterns.get(block);
			if(pattern==null || pattern.getPatternType().contains(PatternType.METHOD_DECLARATION) 
					|| pattern.getPatternType().contains(PatternType.CONSTRUCTOR_DECLARATION)){
				writer.write("\t//"+(block.getBlockDeclaration().getCodeLine())+";//Warning: Not well Identified \n");
				writeAllBlockModules(writer,block,relatedPatterns);
				writer.write("\t//}\n");
			}else{
				writer.write("\t\t"+pattern+"{\n");
				writeAllBlockModules(writer,block,relatedPatterns);
				writer.write("\t}\n");
			}
				
		}
	} 
	
//	private String translatePiece(ICodePiece piece){
//		
//		if(piece.getPieceType()==PieceType.VARIABLE){
//			return piece.toString();
//		}
//		return piece.toString();
//	}

}
