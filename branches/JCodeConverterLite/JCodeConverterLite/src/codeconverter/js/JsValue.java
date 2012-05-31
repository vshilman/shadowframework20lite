package codeconverter.js;

import java.util.ArrayList;
import java.util.List;

import codeconverter.PieceType;
import codeconverter.codepieces.Value;

public class JsValue extends Value{

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
		allIntervals.add(new CharInterval('.','.'));
		allIntervals.add(new CharInterval('_','_'));
		allIntervals.add(new CharInterval('[',']'));
		allIntervals.add(new CharInterval('"','"'));
		allIntervals.add(new CharInterval('\'','\''));
	}

	public JsValue(PieceType piecetype) {
		super();
		setPieceType(piecetype);
	}
	
	public JsValue() {
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
