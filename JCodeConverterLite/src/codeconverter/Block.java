package codeconverter;

import java.util.ArrayList;

/**
 * A Block of CodeModules.
 *
 * As part of the CodeModule's composite Pattern, this stay for a Concrete Node
 *
 * @author Alessandro Martinelli
 */
public class Block  implements CodeModule{

	public Block fatherBlock=null;
	public ArrayList<CodeModule> modules=new ArrayList<CodeModule>();


	//private int firstLine;
	//private int lastLine;


	@Override
	public int getLastLine() {
		if(modules.size()>0){
			return modules.get(modules.size()-1).getLastLine();
		}
		return getFirstLine();
	}

	@Override
	public int getFirstLine() {
		if(modules.size()>0){
			return modules.get(0).getFirstLine();
		}
		return -1;
	}

	@Override
	public int getSize() {
		return modules.size();
	}

	@Override
	public CodeModule getSubModule(int index) {
		return modules.get(index);
	}

	@Override
	public String print() {
		String a="{\n";
			for (int i=0; i < modules.size(); i++) {
				a+=modules.get(i).print()+"\n";
			}
		a+="}\n";
		return a;
	}

	@Override
	public String toString() {
		return "Block";
	}

	public String getCode(){
		String ret="{\n";
		for (int i = 0; i < modules.size(); i++) {
			ret+=modules.get(i).getCode()+"\n";
		}
		ret+="}\n";
		return ret;

	}

	@Override
	public String getExtendedCode() {
		String ret="{\n";
		for (int i = 0; i < modules.size(); i++) {
			ret+=modules.get(i).getExtendedCode()+"\n";
		}
		ret+="}\n";
		return ret;
	}


	/* Not exactly how I suppose it should be done
	 *
	 * This block corrector solves some issues with for cicles and
	 * merges Sub Block with their declaration line into DeclaredBlocks
	 */
	public void correctBlock(){
		for (int i=0; i < modules.size(); i++) {
			if(modules.get(i) instanceof CodeLine){
				String data=((CodeLine)modules.get(i)).getCode();
				//solve for issues, for are tokenized on ; and must be resembled
				if((data.trim().startsWith("for ") || data.trim().startsWith("for("))  && i<modules.size()-2){
					String data2=((CodeLine)modules.get(i+1)).getCode();
					String data3=((CodeLine)modules.get(i+2)).getCode();
					modules.remove(i);
					modules.remove(i);
					modules.remove(i);
					CodeLine line=new CodeLine(data+";"+data2+";"+data3,true);
					line.setFirstLine(modules.get(i).getFirstLine());
					line.setLastLine(modules.get(i+2).getLastLine());
					modules.add(i,line);
				}
				//merges Sub Block with their declaration line into DeclaredBlocks
				if(((CodeLine)modules.get(i)).isBlockDeclaration() && i<modules.size()-1){

					CodeLine blockDeclarationLine = (CodeLine)(modules.get(i));
					Block relatedBlock = (Block)(modules.get(i+1));

					//Start of : Array into Brackets correction
					boolean isDeclaredBlock = true;
					if(relatedBlock.getSize()==1){
						if(relatedBlock.getSubModule(0) instanceof CodeLine){
							CodeLine firstRelatedBlockLine = (CodeLine)(relatedBlock.getSubModule(0));
							if(firstRelatedBlockLine.isBlockDeclaration()){
								isDeclaredBlock=false;//because it is an array declaration!!
								CodeLine codeLine = new CodeLine(blockDeclarationLine.getCode()+
										"{"+firstRelatedBlockLine.getCode()+"}", false);
								codeLine.setFirstLine(blockDeclarationLine.getFirstLine());
								codeLine.setLastLine(firstRelatedBlockLine.getLastLine());
								modules.remove(i);
								modules.remove(i);
								modules.add(i,codeLine);
							}
						}else if(relatedBlock.getSubModule(0) instanceof Block){
							relatedBlock.modules.set(0, relatedBlock.getSubModule(0).getSubModule(0));
						}
					}
					//End of : Array into Brackets correction

					if(isDeclaredBlock){
						DeclaredBlock block=new DeclaredBlock(blockDeclarationLine,relatedBlock);
						modules.remove(i);
						modules.remove(i);
						modules.add(i,block);
						block.getRelatedBlock().correctBlock();
					}
				}
			}
		}

	}


}
