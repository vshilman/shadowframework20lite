package tests.blocks;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import codeconverter.CodePattern;
import codeconverter.ICodePiece;
import codeconverter.ICodePieceSequencer;
import codeconverter.PatternType;
import codeconverter.PieceType;
import codeconverter.elements.IMethodDeclarator;
import codeconverter.elements.MethodDeclaration;
import codeconverter.elements.VariableList;

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
		ArrayList<CodeModule> addictionalLinesOfCode=new ArrayList<CodeModule>();
		

		System.err.println("relatedPatterns "+relatedPatterns.size());
		for (CodeModule codeModule : relatedPatterns.keySet()) {
			System.out.println(" module: "+codeModule+" "+relatedPatterns.get(codeModule));
		}
		
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
					if(classModulePattern!=null){
						if(classModulePattern.getPatternType().contains(PatternType.CONSTRUCTOR_DECLARATION)){
							constructors.add((DeclaredBlock)classModule);
						}else if(classModulePattern.getPatternType().contains(PatternType.METHOD_DECLARATION)){
							methods.add((DeclaredBlock)classModule);
						}else{
							addictionalLinesOfCode.add(classModule);
						}
					}
				}
			}
		}
		
		CodePattern classPattern=relatedPatterns.get(classDefinition);

		if(classPattern!=null){
			
			ICodePiece className=classPattern.getPieceByType(PieceType.NAME).get(0);

			for (int i = 0; i < constructors.size(); i++) {
				writeConstructor(writer,constructors.get(i),relatedPatterns);	
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
	
	
	private void writeConstructor(StringWriter writer,DeclaredBlock constructor,HashMap<CodeModule, CodePattern> relatedPatterns){
		
		CodePattern pattern=relatedPatterns.get(constructor);
		
		IMethodDeclarator methodDeclarator=(IMethodDeclarator)pattern;
		MethodDeclaration method=methodDeclarator.getMethodDeclaration();

		String methodName=method.getMethodName().getName();
		
			VariableList list=method.getList();
			
			String data="";
			if(list.size()>0){
				data+=list.get(0).getName();
				for (int i = 1; i < list.size(); i++) {
					data+=", "+list.get(i).getName();
				}
			}
			
		writer.write("function "+methodName+"("+data+"){\n");
		
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
		
		IMethodDeclarator methodDeclarator=(IMethodDeclarator)pattern;
		MethodDeclaration method=methodDeclarator.getMethodDeclaration();

		String methodName=method.getMethodName().getName();
		
			VariableList list=method.getList();
			
			String data="";
			if(list.size()>0){
				data+=list.get(0).getName();
				for (int i = 1; i < list.size(); i++) {
					data+=", "+list.get(i).getName();
				}
			}
		
		writer.write("\n\t"+methodName+":function("+data+"){\n");
		
			writeAllBlockModules(writer, constructor, relatedPatterns);
			
		writer.write("\t}"+(lastMethod?"\n":",")+"\n");
	}


	private void writeCodeModule(StringWriter writer,CodeModule module,HashMap<CodeModule, CodePattern> relatedPatterns){
		if(module instanceof CodeLine){
			CodePattern piece=relatedPatterns.get(module);
			if(piece!=null){
				if(piece.getPatternType().contains(PatternType.SUPER))
					return;
				writer.write("\t"+piece+"\n");	
			}else{
				writer.write("\t"+(((CodeLine)module).codeLine)+";//Warning: Not well Identified \n");
			}
		}
	} 
	
	private String translatePiece(ICodePiece piece){
		
		if(piece.getPieceType()==PieceType.VARIABLE){
			return piece.toString();
		}
		return piece.toString();
	}

}
