package codeconverter.filecodelinesgenerators.test;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import tests.blocks.BlockUtilities;

import codeconverter.filecodelinesgenerators.CodeLineGenerator;
import codeconverter.filecodelinesgenerators.FileCodeLine;
import codeconverter.utility.FileStringUtility;

public  class DefaultCodeLineGenerator implements CodeLineGenerator {


	protected DefaultCodeLineGenerator(){

	}


	/* (non-Javadoc)
	 * @see tests.tmp.CodeLineGenerator#generateFileCodeLines(java.io.InputStream)
	 */
	@Override
	public  List<FileCodeLine> generateFileCodeLines(InputStream stream) {

		List<String> list=FileStringUtility.loadTextfromStream(stream);

		List<FileCodeLine> codeLines=new ArrayList<FileCodeLine>();


		//removing //

		String adding="";
		for (int lineIndex=0;lineIndex<list.size();lineIndex++) {
			String string=list.get(lineIndex);
		//for (String string : list) {
			int position=string.indexOf("//");
			if(position>=0)
				string=string.substring(0,position).trim();
			if(string.length()!=0){
				if(string.trim().startsWith("if") || string.trim().startsWith("else")
						|| string.trim().startsWith("for")){
					codeLines.add(new FileCodeLine(string+"{", lineIndex));
					//writer.write(string+"{");
					adding="}";
				}else{
					//writer.write(string+adding);
					codeLines.add(new FileCodeLine(string+adding, lineIndex));
					adding="";
				}
			}
		}

		boolean onComment=false;
		for (int i = 0; i < codeLines.size(); i++) {
			String codeLine=codeLines.get(i).getCode();
			int beginof=codeLine.indexOf("/*");
			int endof=codeLine.indexOf("*/");
			if(beginof==-1 && endof==-1){
				if(onComment){
					codeLines.remove(i);
					i--;
				}
			}else if(beginof!=-1){
				String line=codeLine.substring(0,beginof);
				if(endof!=-1){
					line=line+codeLine.substring(endof+2);
				}else{
					onComment=true;
				}
				codeLines.get(i).setCode(line);
			}else{
				String line=codeLine.substring(endof+2);
				onComment=false;
				codeLines.get(i).setCode(line);
			}
			if(codeLine.trim().length()==0){
				codeLines.remove(i);
				i--;
			}
		}

		return codeLines;

	}

	public CodeLineGenerator clone(){
		return new DefaultCodeLineGenerator();
	}



}
