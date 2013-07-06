package codeconverter.cpp;

import java.util.ArrayList;
import java.util.List;

import codeconverter.PieceType;
import codeconverter.codepieces.ConcreteValue;
import codeconverter.codepieces.Value;

public class CppValue extends ConcreteValue{

	public CppValue(PieceType type) {
		super();
		setIntervals();
		setPieceType(type);
	}

	public CppValue() {
		super();
		setIntervals();
	}


	@Override
	public void setIntervals() {
		startingIntervals.clear();
		allIntervals.clear();
		startingIntervals.add(new CharInterval('a', 'z'));
		startingIntervals.add(new CharInterval('A', 'Z'));
		startingIntervals.add(new CharInterval('"', '"'));
		startingIntervals.add(new CharInterval('\'', '\''));
		allIntervals.add(new CharInterval('a', 'z'));
		allIntervals.add(new CharInterval('A', 'Z'));
		allIntervals.add(new CharInterval('0', '9'));
		allIntervals.add(new CharInterval('.', '.'));
		allIntervals.add(new CharInterval('_', '_'));
		allIntervals.add(new CharInterval('"', '"'));
		allIntervals.add(new CharInterval('[', ']'));
		allIntervals.add(new CharInterval('\'', '\''));

	}








}
