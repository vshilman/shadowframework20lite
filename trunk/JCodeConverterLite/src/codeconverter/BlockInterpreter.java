package codeconverter;

import java.util.HashMap;
import java.util.LinkedHashMap;


public class BlockInterpreter{

	private BlockDataInterpreter interpreter;
	
	public BlockInterpreter(BlockDataInterpreter interpreter) {
		super();
		this.interpreter=interpreter;
	}

	public HashMap<CodeModule, CodePattern> getInterpretation(Block block) {
		HashMap<CodeModule, CodePattern> interpretations=new LinkedHashMap<CodeModule, CodePattern>();
		updateInterpretation(interpretations,block);
		return interpretations;
	}
	
	public void updateInterpretation(HashMap<CodeModule, CodePattern> interpretations,Block block){
		
		for (int i=0; i < block.getSize(); i++) {
			CodeModule module=block.getSubModule(i);
			if(module instanceof CodeLine){
				CodeLine codeLine=(CodeLine)(module);
				CodePattern pattern=interpreter.getLineOfCodePattern(codeLine.getCodeLine());
				interpretations.put(module,pattern);
			
			}else if(module instanceof DeclaredBlock){
				DeclaredBlock dBlock=(DeclaredBlock)(module);
				CodePattern pattern=interpreter.getBlockDeclarationPattern(dBlock.getBlockDeclaration().getCodeLine());
				interpretations.put(module,pattern);
				updateInterpretation(interpretations,dBlock.getRelatedBlock());
			}
		}		
	}
	
}
