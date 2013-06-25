package codeconverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class DifferentiationResult {

	private List<CodeModule> uninterpretatesLeft=new ArrayList<CodeModule>();
	private List<CodeModule> uninterpretatesRight=new ArrayList<CodeModule>();
	private Set<CodeModule> differentLeft;
	private Set<CodeModule> differentRight;


	public DifferentiationResult() {
		super();
	}


	public DifferentiationResult(List<CodeModule> uninterpretatesLeft,
			List<CodeModule> uninterpretatesRight,
			Set<CodeModule> differentLeft, Set<CodeModule> differentRight) {
		super();
		this.uninterpretatesLeft = uninterpretatesLeft;
		this.uninterpretatesRight = uninterpretatesRight;
		this.differentLeft = differentLeft;
		this.differentRight = differentRight;
	}


	public List<CodeModule> getUninterpretatesLeft() {
		return uninterpretatesLeft;
	}


	public List<CodeModule> getUninterpretatesRight() {
		return uninterpretatesRight;
	}


	public Set<CodeModule> getDifferentLeft() {
		return differentLeft;
	}


	public Set<CodeModule> getDifferentRight() {
		return differentRight;
	}









}
