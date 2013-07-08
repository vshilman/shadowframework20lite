package codeconverter.cpp;

import java.util.ArrayList;
import java.util.List;

import codeconverter.PieceType;
import codeconverter.codepieces.ConcreteValue;
import codeconverter.codepieces.Value;


public class CppType extends ConcreteValue{

	public CppType() {
		super();
		setIntervals();
		setPieceType(PieceType.TYPE);
	}

	@Override
	public void setIntervals() {
		startingIntervals.clear();
		endingIntervals.clear();
		startingIntervals.add(new CharInterval('a', 'z'));
		startingIntervals.add(new CharInterval('A', 'Z'));
		allIntervals.add(new CharInterval('a', 'z'));
		allIntervals.add(new CharInterval('A', 'Z'));
		allIntervals.add(new CharInterval('0', '9'));
		allIntervals.add(new CharInterval('_', '_'));
		allIntervals.add(new CharInterval('<', '>'));
		allIntervals.add(new CharInterval('[', ']'));
		allIntervals.add(new CharInterval(',', ','));
		allIntervals.add(new CharInterval('*', '*')); // int* x

	}




}
