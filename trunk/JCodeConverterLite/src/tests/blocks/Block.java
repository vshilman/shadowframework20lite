package tests.blocks;

import java.util.ArrayList;

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
	
	public void correctBlock(){
		for (int i=0; i < modules.size(); i++) {
			if(modules.get(i) instanceof CodeLine){
				String data=((CodeLine)modules.get(i)).codeLine;
				if((data.trim().startsWith("for ") || data.trim().startsWith("for("))  && i<modules.size()-2){
					String data2=((CodeLine)modules.get(i+1)).codeLine;
					String data3=((CodeLine)modules.get(i+2)).codeLine;
					modules.remove(i);
					modules.remove(i);
					modules.remove(i);
					modules.add(i,new CodeLine(data+";"+data2+";"+data3,true));
				}
				if(((CodeLine)modules.get(i)).isBlockDeclaration && i<modules.size()-1){
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
