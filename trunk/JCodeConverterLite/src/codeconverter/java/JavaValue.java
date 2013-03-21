package codeconverter.java;

import codeconverter.PieceType;
import codeconverter.codepieces.ConcreteValue;

public class JavaValue extends ConcreteValue{

	public JavaValue(PieceType piecetype) {
		super();
		setIntervals();
		setPieceType(piecetype);
	}
	
	public JavaValue() {
		super();
		setIntervals();
	}

	@Override
	public void setIntervals() {
		startingIntervals.clear();
		allIntervals.clear();
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

	


}
