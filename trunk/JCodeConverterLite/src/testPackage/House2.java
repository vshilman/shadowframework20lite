package testPackage;

import codeconverter.CodePattern;
import codeconverter.ICodeElement;
import codeconverter.ICodePiece;


public abstract class House2 extends CodePattern implements ICodePiece{

	public int metodo(int b_a, float b, int c) {		
		return b_a;		
	}
	
	@Override
	public ICodeElement cloneCodePiece() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int elementMatch(String data, int matchPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	public House2(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	
}