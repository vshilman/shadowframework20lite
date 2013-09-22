package codeconverter.cpp;


import codeconverter.PieceType;
import codeconverter.codepieces.ConcreteValue;

public class CppPreprocessorDirective extends ConcreteValue{

	public CppPreprocessorDirective(PieceType type) {
		super();
		setIntervals();
		setPieceType(type);
	}

	public CppPreprocessorDirective() {
		super();
		setIntervals();
	}




	@Override
	public void setIntervals() {
		startingIntervals.clear();
		allIntervals.clear();
		startingIntervals.add(new CharInterval('#', '#'));
		allIntervals.add(new CharInterval('a', 'z'));
		allIntervals.add(new CharInterval('A', 'Z'));
	}









}
