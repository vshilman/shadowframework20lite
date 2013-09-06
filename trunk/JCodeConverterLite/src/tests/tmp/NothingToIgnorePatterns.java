package tests.tmp;

import java.util.ArrayList;
import java.util.List;

import codeconverter.PatternType;

public class NothingToIgnorePatterns implements IgnoredPatterns{

	@Override
	public List<PatternType> getIgnoredtypes(){
		return new ArrayList<PatternType>();
	}

	@Override
	public boolean ignoredContainsAny(List<PatternType> types) {
		return false;
	}


}
