package codeconverter.filecodelinesgenerators.test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import codeconverter.filecodelinesgenerators.CodeLineGenaratorFactory;
import codeconverter.filecodelinesgenerators.CodeLineGenerator;
import codeconverter.filecodelinesgenerators.FileCodeLine;

public class MyCodeLineGeneratorFactory implements CodeLineGenaratorFactory{

	protected HashMap<String, CodeLineGenerator> map=new HashMap<String, CodeLineGenerator>();


	@Override
	public CodeLineGenerator getCodeLineGenerator(String ext) {
		return map.get(ext).clone();
	}








}
