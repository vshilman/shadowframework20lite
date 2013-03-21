package codeconverter.codepieces;

import codeconverter.PieceType;

public class Name extends ConcreteValue{
	
	public Name() {
		super();
		setIntervals();
	}
	

	public Name(PieceType piecetype) {
		super();
		setIntervals();
		setPieceType(piecetype);
	}

	@Override
	public void setIntervals() {
		startingIntervals.clear();
		allIntervals.clear();
		startingIntervals.add(new CharInterval('a','z'));
		startingIntervals.add(new CharInterval('A','Z'));
		allIntervals.add(new CharInterval('a','z'));
		allIntervals.add(new CharInterval('A','Z'));
		allIntervals.add(new CharInterval('0','9'));
		allIntervals.add(new CharInterval('_','_'));		
	}
	
	

	
}
