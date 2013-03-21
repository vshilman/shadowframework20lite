package codeconverter.codepieces;


import codeconverter.PieceType;

public class Number extends ConcreteValue{

	
	public Number() {
		super();
		setIntervals();
	}
	

	@Override
	public PieceType getPieceType() {
		return PieceType.VALUE;
	}

	@Override
	public void setIntervals() {
		startingIntervals.clear();
		allIntervals.clear();
		startingIntervals.add(new CharInterval('+','+'));
		startingIntervals.add(new CharInterval('-','-'));
		startingIntervals.add(new CharInterval('0','9'));
		startingIntervals.add(new CharInterval('#','#'));
		allIntervals.add(new CharInterval('0','9'));
		allIntervals.add(new CharInterval('.','.'));
		allIntervals.add(new CharInterval('a','z'));
		allIntervals.add(new CharInterval('A','Z'));
	}
}
