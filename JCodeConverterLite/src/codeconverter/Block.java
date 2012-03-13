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
	
	/* Not exactly how I suppose it should be done
	 * 
	 * This block corrector solves some issues with for cicles and 
	 * merges Sub Block with their declaration line into DeclaredBlocks
	 */
	public void correctBlock(){
		for (int i=0; i < modules.size(); i++) {
			if(modules.get(i) instanceof CodeLine){
				String data=((CodeLine)modules.get(i)).getCodeLine();
				//solve for issues, for are tokenized on ; and must be resembled
				if((data.trim().startsWith("for ") || data.trim().startsWith("for("))  && i<modules.size()-2){
					String data2=((CodeLine)modules.get(i+1)).getCodeLine();
					String data3=((CodeLine)modules.get(i+2)).getCodeLine();
					modules.remove(i);
					modules.remove(i);
					modules.remove(i);
					modules.add(i,new CodeLine(data+";"+data2+";"+data3,true));
				}
				//merges Sub Block with their declaration line into DeclaredBlocks 
				if(((CodeLine)modules.get(i)).isBlockDeclaration() && i<modules.size()-1){
					DeclaredBlock block=new DeclaredBlock((CodeLine)(modules.get(i)),(Block)(modules.get(i+1)));
					modules.remove(i);
					modules.remove(i);
					modules.add(i,block);
					block.getRelatedBlock().correctBlock();
				}
			}
		}
		
	}
}
