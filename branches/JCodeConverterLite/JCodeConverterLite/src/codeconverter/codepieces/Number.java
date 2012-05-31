package codeconverter.codepieces;

import java.util.ArrayList;
import java.util.List;

import codeconverter.PieceType;

public class Number extends Value{

	private static ArrayList<CharInterval> startingIntervals=new ArrayList<Value.CharInterval>();
	private static ArrayList<CharInterval> allIntervals=new ArrayList<Value.CharInterval>();
	private static ArrayList<CharInterval> endingIntervals=new ArrayList<Value.CharInterval>();
	
	static{
		startingIntervals.add(new CharInterval('+','+'));
		startingIntervals.add(new CharInterval('-','-'));
		startingIntervals.add(new CharInterval('0','9'));
		startingIntervals.add(new CharInterval('#','#'));
		allIntervals.add(new CharInterval('0','9'));
		allIntervals.add(new CharInterval('.','.'));
		allIntervals.add(new CharInterval('a','z'));
		allIntervals.add(new CharInterval('A','Z'));
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

	@Override
	public PieceType getPieceType() {
		return PieceType.VALUE;
	}
}
