package codeconverter.java.jogl;

import java.util.ArrayList;
import java.util.List;

import codeconverter.PieceType;
import codeconverter.codepieces.CodeSequence;
import codeconverter.codepieces.CompositeCodePiece;
import codeconverter.codepieces.Name;
import codeconverter.codepieces.OptionalCode;
import codeconverter.codepieces.UniqueKeyword;
import codeconverter.codepieces.Value;

public class JoglName extends CompositeCodePiece{

	public JoglName(){
		generate(new JavaNamePart());
	}	

	public JoglName(PieceType type){
		generate(new JavaNamePart(type));
	}

	public void generate(JavaNamePart part) {
		add(part,new OptionalCode(new CompositeCodePiece(
				new UniqueKeyword("<"),new CodeSequence(new Name(),","),new UniqueKeyword(">")
		)));
	}

	
	private static class JavaNamePart extends Value{
	
		private static ArrayList<CharInterval> startingIntervals=new ArrayList<Value.CharInterval>();
		private static ArrayList<CharInterval> allIntervals=new ArrayList<Value.CharInterval>();
		private static ArrayList<CharInterval> endingIntervals=new ArrayList<Value.CharInterval>();
		
		static{
			startingIntervals.add(new CharInterval('a','z'));
			startingIntervals.add(new CharInterval('A','Z'));
			startingIntervals.add(new CharInterval('"','"'));
			startingIntervals.add(new CharInterval('\'','\''));
			allIntervals.add(new CharInterval('a','z'));
			allIntervals.add(new CharInterval('A','Z'));
			allIntervals.add(new CharInterval('0','9'));
			//allIntervals.add(new CharInterval('.','.'));
			allIntervals.add(new CharInterval('_','_'));
			allIntervals.add(new CharInterval('[',']'));
			allIntervals.add(new CharInterval('"','"'));
			allIntervals.add(new CharInterval('\'','\''));
		}
	
		public JavaNamePart(PieceType piecetype) {
			super();
			setPieceType(piecetype);
		}
		
		public JavaNamePart() {
			super();
		}
	
		@Override
		public List<CharInterval> getAvailableIntervals(int position) {
			if(position==0)
				return startingIntervals;
			return allIntervals;
		}
		
		@Override
		public List<CharInterval> getEndCharacter() {
			return endingIntervals;
		}

	}
}
