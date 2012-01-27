package codeconverter.java;

import java.util.LinkedList;
import java.util.List;

import codeconverter.CodePattern;
import codeconverter.CodeSequence;
import codeconverter.ICodeElement;
import codeconverter.ICodePieceSequencer;
import codeconverter.Name;
import codeconverter.PatternType;
import codeconverter.StaticKeyword;

public class JavaPackageDeclaration extends CodePattern{

	private CodeSequence sequence=new CodeSequence(new JavaName(),".");
	private List<String> names=new LinkedList<String>();
	
	public JavaPackageDeclaration() {
		super("name");
		addCodePiece(new StaticKeyword("package "),sequence);
		addCodePattern(PatternType.LIBRARY_DECLARATION);
	}
	
	@Override
	public ICodeElement cloneCodePiece() {
		JavaPackageDeclaration pattern=new JavaPackageDeclaration();
		ICodePieceSequencer sequence=((ICodePieceSequencer )this.sequence.cloneCodePiece());
		for (int i=0; i < sequence.getPieces().size(); i++) {
			JavaName packageName=(JavaName)(sequence.getPieces().get(i));
			pattern.names.add(packageName.getData());
		}
		return pattern;
	}
	
	@Override
	public String toString() {
		String tmp="";
		for (int i=0; i < names.size()-1; i++) {
			tmp+=names.get(i)+".";
		}
		if(names.size()>0)
			tmp+=(names.get(names.size()-1));
		return "package "+tmp+";";
	}
}
