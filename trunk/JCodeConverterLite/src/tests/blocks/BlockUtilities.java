package tests.blocks;

import java.util.ArrayList;
import java.util.Collections;

import codeconverter.Block;
import codeconverter.CodeLine;

public class BlockUtilities {

	public static ArrayList<Integer> findPosition(char[] characters,char c){
		ArrayList<Integer> positions=new ArrayList<Integer>();
		for (int i=0; i < characters.length; i++) {
			if(characters[i]==c)
				positions.add(i);
		}
		return positions;
	}

	static void addCodeLinesToBlock(Block block,char[] totalChar, int startingPosition,int endingPosition){
		if(endingPosition>startingPosition){

			char[] temp=buildSubString(totalChar,startingPosition,
					endingPosition);

			ArrayList<Integer> semicolons=findPosition(temp,';');

			if(semicolons.size()>0){
				insertNewCodeLine(block,temp,false,0,semicolons.get(0),startingPosition);
				for (int i=1; i < semicolons.size(); i++) {
					insertNewCodeLine(block,temp,false,semicolons.get(i-1)+1,semicolons.get(i)-1-semicolons.get(i-1),startingPosition);
				}
				int startingIndex=semicolons.get(semicolons.size()-1)+1;
				insertNewCodeLine(block,temp,true,startingIndex,temp.length-startingIndex,startingPosition);
			}else{
				insertNewCodeLine(block,temp,true,0,temp.length,startingPosition);
			}
		}

	}

	public static char[] buildSubString(char[] totalChar, int startingPosition,
			int endingPosition) {
		char[] temp=new char[endingPosition+1-startingPosition];
		for (int i=0; i < temp.length; i++) {
			temp[i]=totalChar[i+startingPosition];
		}
		return temp;
	}

	public static void insertNewCodeLine(Block block, char[] temp,
			boolean isDeclaration, int startingPos, int length, int realStart) {
		String string=new String(temp,startingPos,length);

		if(string.trim().length()!=0){
			block.modules.add(new CodeLine(string.trim(),isDeclaration));
		}
	}

	public static Block generateBlocks(char[] totalStringChars) {

		ArrayList<Integer> findBlockOpen=findPosition(totalStringChars,'{');
		ArrayList<Integer> findBlockClose=findPosition(totalStringChars,'}');

		ArrayList<Integer> blocksSeparations=new ArrayList<Integer>();
		blocksSeparations.addAll(findBlockClose);
		blocksSeparations.addAll(findBlockOpen);
		Collections.sort(blocksSeparations);

		Block fileBlock=new Block();
		Block actualBlock=fileBlock;
		int lastPosition=0;

		for (int i=0; i < blocksSeparations.size(); i++) {

			int indexSeparation=blocksSeparations.get(i);
			addCodeLinesToBlock(actualBlock,totalStringChars,lastPosition,indexSeparation-1);

			if(totalStringChars[indexSeparation]=='{'){
				Block block=new Block();
				actualBlock.modules.add(block);
				block.fatherBlock=actualBlock;
				actualBlock=block;
			}else if(totalStringChars[indexSeparation]=='}'){
				actualBlock=actualBlock.fatherBlock;
			}else{
				System.err.println("error");
			}

			lastPosition=indexSeparation+1;
		}

		fileBlock.correctBlock();

		return fileBlock;
	}

}
