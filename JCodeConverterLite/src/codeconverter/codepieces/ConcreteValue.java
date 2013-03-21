package codeconverter.codepieces;

import java.util.ArrayList;
import java.util.List;

public abstract class ConcreteValue extends Value{

	protected  ArrayList<CharInterval> startingIntervals=new ArrayList<CharInterval>();
	protected  ArrayList<CharInterval> allIntervals=new ArrayList<CharInterval>();
	protected  ArrayList<CharInterval> endingIntervals=new ArrayList<CharInterval>();
	
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
	
	public abstract void setIntervals();
	
	
	
	
}
