package tests.blocks;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import codeconverter.Block;
import codeconverter.CodeLine;
import codeconverter.filecodelinesgenerators.CodeLineGenaratorFactory;
import codeconverter.filecodelinesgenerators.CodeLineGenerator;
import codeconverter.filecodelinesgenerators.FileCodeLine;
import codeconverter.filecodelinesgenerators.test.TestCodeLineGeneratorFactory;
import codeconverter.utility.FileStringUtility;

public class BlockUtilities {

	/* Structure used by generateBlocksFromFile
	 */
	private static class FileCodeLinePosition implements Comparable<FileCodeLinePosition>{
		int fileLine;//Line withing the file
		int position;//position of a char within that line
		char value;// the char at that position in that fileLine
		public FileCodeLinePosition(int fileLine, int position,char value) {
			super();
			this.fileLine = fileLine;
			this.position = position;
			this.value=value;
		}
		@Override
		public int compareTo(FileCodeLinePosition o) {
			if(o.fileLine!=fileLine)
				return fileLine-o.fileLine;
			return position-o.position;
		}
		public FileCodeLinePosition less(){
			return new FileCodeLinePosition(fileLine, position-1, value);
		}
		public FileCodeLinePosition plus(){
			return new FileCodeLinePosition(fileLine, position+1, value);
		}
		public FileCodeLinePosition add(int characters){
			return new FileCodeLinePosition(fileLine, position+characters, value);
		}
	}
	/* Structure used by generateBlocksFromFile
	 */
/*	private static class FileCodeLine{
		String code;//A line of Code
		int line;//its position within a file
		public FileCodeLine(String code, int line) {
			super();
			this.code = code;
			this.line = line;
		}
	}
*/
//	public static ArrayList<Integer> findPosition(char[] characters,char c){
//		ArrayList<Integer> positions=new ArrayList<Integer>();
//		for (int i=0; i < characters.length; i++) {
//			if(characters[i]==c)
//				positions.add(i);
//		}
//		return positions;
//	}


		static void addCodeLinesToBlock(Block block,List<FileCodeLine> codeLines, FileCodeLinePosition startingPosition,FileCodeLinePosition endingPosition){
			if(endingPosition.compareTo(startingPosition)>0){

				List<FileCodeLine> temp=buildSubLines(codeLines, startingPosition, endingPosition);
//				char[] temp=buildSubString(totalChar,startingPosition,
//						endingPosition);

				ArrayList<FileCodeLinePosition> semicolons=findPositions(temp,';');

				if(semicolons.size()>0){
					insertNewCodeLine(block,temp,false,new FileCodeLinePosition(startingPosition.fileLine, 0, ' '),semicolons.get(0));
					for (int i=1; i < semicolons.size(); i++) {
						insertNewCodeLine(block,temp,false,semicolons.get(i-1).plus(),semicolons.get(i));
					}
					FileCodeLinePosition startingIndex=semicolons.get(semicolons.size()-1).plus();
					insertNewCodeLine(block,temp,true,startingIndex,endingPosition);
				}else{
					int position=startingPosition.position;
					startingPosition=startingPosition.add(-position);
					if(endingPosition.fileLine==startingPosition.fileLine)
						endingPosition=endingPosition.add(-position);
					insertNewCodeLine(block,temp,true,startingPosition,endingPosition);
				}
			}

		}

	public static List<FileCodeLine> buildSubLines(List<FileCodeLine> codeLines, FileCodeLinePosition startingPosition,
			FileCodeLinePosition endingPosition) {
		ArrayList<FileCodeLine> newLines=new ArrayList<FileCodeLine>();
		for (int i = 0; i < codeLines.size(); i++) {
			int lineIndex=codeLines.get(i).getLine();
			String codeLine=codeLines.get(i).getCode();
			if(lineIndex==startingPosition.fileLine && lineIndex==endingPosition.fileLine){
				codeLine=codeLine.substring(startingPosition.position,endingPosition.position);
			}else if(lineIndex==startingPosition.fileLine){
				codeLine=codeLine.substring(startingPosition.position);
			}else if(lineIndex==endingPosition.fileLine){
				codeLine=codeLine.substring(0,endingPosition.position);
			}
			if(lineIndex>=startingPosition.fileLine && lineIndex<=endingPosition.fileLine){
				newLines.add(new FileCodeLine(codeLine,lineIndex));
			}
		}
		return newLines;
	}

	public static void insertNewCodeLine(Block block, List<FileCodeLine> temp,
			boolean isDeclaration, FileCodeLinePosition startingPos, FileCodeLinePosition end) {
		//String string=new String(temp,startingPos,length);

		String string="";
		int firstLine=end.fileLine;
		int lastLine=startingPos.fileLine;
		for (int i = 0; i < temp.size(); i++) {
			int line=temp.get(i).getLine();
			if(line==startingPos.fileLine && line==end.fileLine ){//same line
				string+=temp.get(i).getCode().substring(startingPos.position,end.position);
			}else if(line==startingPos.fileLine){//first line
				String adding=temp.get(i).getCode().substring(startingPos.position);
				if(adding.trim().length()>0)
					firstLine=startingPos.fileLine;
				string+=adding;
			}else if(line==end.fileLine){//last line
				String adding=temp.get(i).getCode().substring(0,end.position);
				if(adding.trim().length()>0)
					lastLine=end.fileLine;
				string+=adding;
			}else if(line>startingPos.fileLine && line<end.fileLine){//internal lines
				if(firstLine==end.fileLine){
					firstLine=line;
				}
				lastLine=line;
				string+=temp.get(i).getCode();
			}
		}

		if(string.trim().length()!=0){
			CodeLine codeLine=new CodeLine(string.trim(),isDeclaration);
			codeLine.setFirstLine(firstLine);
			codeLine.setLastLine(lastLine);
			block.modules.add(codeLine);
		}
	}

	public static ArrayList<FileCodeLinePosition> findPositions(List<FileCodeLine> codeLines,char c){
		ArrayList<FileCodeLinePosition> positions=new ArrayList<FileCodeLinePosition>();
			for(int j=0; j < codeLines.size(); j++){
				char[] characters=codeLines.get(j).getCode().toCharArray();
				int lineIndex=codeLines.get(j).getLine();
				for (int i=0; i < characters.length; i++) {
					if(characters[i]==c){
						positions.add(new FileCodeLinePosition(lineIndex, i, c));
					}
				}
			}
		return positions;
	}


	/**
	 * Generate a List<FileCodeLine> from a Stream Content.
	 *
	 * Comments lines should get removed (but cross your finger :) )
	 *
	 * @param filename
	 * @return
	 */
//	public static List<FileCodeLine> generateFileCodeLines(InputStream stream) {
//
//		List<String> list=FileStringUtility.loadTextfromStream(stream);
//
//		List<FileCodeLine> codeLines=new ArrayList<BlockUtilities.FileCodeLine>();
//
//		//Removing comments starting with //
//
//		//StringWriter writer=new StringWriter();
//		String adding="";
//		for (int lineIndex=0;lineIndex<list.size();lineIndex++) {
//			String string=list.get(lineIndex);
//		//for (String string : list) {
//			int position=string.indexOf("//");
//			if(position>=0)
//				string=string.substring(0,position).trim();
//			if(string.length()!=0){
//				if(string.trim().startsWith("if") || string.trim().startsWith("else")
//						|| string.trim().startsWith("for")){
//					codeLines.add(new FileCodeLine(string+"{", lineIndex));
//					//writer.write(string+"{");
//					adding="}";
//				}else{
//					//writer.write(string+adding);
//					codeLines.add(new FileCodeLine(string+adding, lineIndex));
//					adding="";
//				}
//			}
//		}
//
////		String totalString=writer.toString();
////		int beginof=totalString.indexOf("/*");
////		int endof=totalString.indexOf("*/");
////		while(beginof!=-1 && endof!=-1){
////			String tmp=totalString.substring(0,beginof);
////			tmp+=totalString.substring(endof+2);
////			totalString=tmp;
////			beginof=totalString.indexOf("/*");
////			endof=totalString.indexOf("*/");
////		}
////		return totalString;
//
//		//Removing comments between /* and */
//
//		boolean onComment=false;
//		for (int i = 0; i < codeLines.size(); i++) {
//			String codeLine=codeLines.get(i).code;
//			int beginof=codeLine.indexOf("/*");
//			int endof=codeLine.indexOf("*/");
//			if(beginof==-1 && endof==-1){
//				if(onComment){
//					codeLines.remove(i);
//					i--;
//				}
//			}else if(beginof!=-1){
//				String line=codeLine.substring(0,beginof);
//				if(endof!=-1){
//					line=line+codeLine.substring(endof+2);
//				}else{
//					onComment=true;
//				}
//				codeLines.get(i).code=line;
//			}else{
//				String line=codeLine.substring(endof+2);
//				onComment=false;
//				codeLines.get(i).code=line;
//			}
//			if(codeLine.trim().length()==0){
//				codeLines.remove(i);
//				i--;
//			}
//		}
//
//		return codeLines;
//
//	}


	public static Block generateBlocksFromStreamWithExtension(String ext,InputStream stream) {

		//List<FileCodeLine> files=generateFileCodeLines(stream);

		CodeLineGenaratorFactory cgd=new TestCodeLineGeneratorFactory();
		List<FileCodeLine> files=cgd.getCodeLineGenerator(ext).generateFileCodeLines(stream);

		return generalGenerateBlockFromStream(files);

	}


	public static Block generateBlocksFromStream(InputStream stream){
		return generateBlocksFromStreamWithExtension("default", stream);
	}

	private static Block generalGenerateBlockFromStream(List<FileCodeLine> files) {
		ArrayList<FileCodeLinePosition> findBlockOpen=findPositions(files,'{');
		ArrayList<FileCodeLinePosition> findBlockClose=findPositions(files,'}');

		ArrayList<FileCodeLinePosition> blocksSeparations=new ArrayList<FileCodeLinePosition>();
		blocksSeparations.addAll(findBlockClose);
		blocksSeparations.addAll(findBlockOpen);
		Collections.sort(blocksSeparations);

		Block fileBlock=new Block();
		Block actualBlock=fileBlock;
		FileCodeLinePosition lastPosition=new FileCodeLinePosition(0,0,' ');

		for (int i=0; i < blocksSeparations.size(); i++) {

			FileCodeLinePosition indexSeparation=blocksSeparations.get(i);
			addCodeLinesToBlock(actualBlock,files,lastPosition,indexSeparation);
			//addCodeLinesToBlock(actualBlock,totalStringChars,lastPosition,indexSeparation-1);

			if(indexSeparation.value=='{'){
			//if(totalStringChars[indexSeparation]=='{'){
				Block block=new Block();
				actualBlock.modules.add(block);
				block.fatherBlock=actualBlock;

				actualBlock=block;
			}else if(indexSeparation.value=='}'){
			//}else if(totalStringChars[indexSeparation]=='}'){
				actualBlock=actualBlock.fatherBlock;
			}else{
				System.err.println("error");
			}

			lastPosition=indexSeparation.plus();
			//lastPosition=indexSeparation+1;
		}

		fileBlock.correctBlock();

		return fileBlock;
		//Block fileBlock=BlockUtilities.generateBlocksFromFile(filename);
	}



	/**
	 * @deprecated NO more used!!!
	 * @param totalStringChars
	 * @return
	 */
	public static Block generateBlocks(/*char[] totalStringChars*/) {

//		ArrayList<Integer> findBlockOpen=findPosition(totalStringChars,'{');
//		ArrayList<Integer> findBlockClose=findPosition(totalStringChars,'}');
//
//		ArrayList<Integer> blocksSeparations=new ArrayList<Integer>();
//		blocksSeparations.addAll(findBlockClose);
//		blocksSeparations.addAll(findBlockOpen);
//		Collections.sort(blocksSeparations);
//
//		Block fileBlock=new Block();
//		Block actualBlock=fileBlock;
//		int lastPosition=0;
//
//		for (int i=0; i < blocksSeparations.size(); i++) {
//
//			int indexSeparation=blocksSeparations.get(i);
//			addCodeLinesToBlock(actualBlock,totalStringChars,lastPosition,indexSeparation-1);
//
//			if(totalStringChars[indexSeparation]=='{'){
//				Block block=new Block();
//				actualBlock.modules.add(block);
//				block.fatherBlock=actualBlock;
//				actualBlock=block;
//			}else if(totalStringChars[indexSeparation]=='}'){
//				actualBlock=actualBlock.fatherBlock;
//			}else{
//				System.err.println("error");
//			}
//
//			lastPosition=indexSeparation+1;
//		}
//
//		fileBlock.correctBlock();
//
//		return fileBlock;

		return null;
	}

}
