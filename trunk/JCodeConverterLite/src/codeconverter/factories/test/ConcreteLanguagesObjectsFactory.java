package codeconverter.factories.test;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import tests.tmp.IgnoredPatterns;

import codeconverter.BlockDataInterpreter;
import codeconverter.factories.LanguagesObjectsFactory;
import codeconverter.filecodelinesgenerators.CodeLineGenerator;
import codeconverter.filecodelinesgenerators.FileCodeLine;

public class ConcreteLanguagesObjectsFactory implements LanguagesObjectsFactory{

	protected HashMap<String, CodeLineGenerator> map=new HashMap<String, CodeLineGenerator>();
	protected HashMap<String, BlockDataInterpreter> map2=new HashMap<String, BlockDataInterpreter>();
	protected HashMap<String, IgnoredPatterns> map3=new HashMap<String, IgnoredPatterns>();


	@Override
	public CodeLineGenerator getCodeLineGenerator(String ext) {
		if(map.containsKey(ext)){
			return map.get(ext).clone();
		}
		return null;
	}


	@Override
	public BlockDataInterpreter getBlockDataInterpreter(String ext) {
		if(map2.containsKey(ext)){
			return map2.get(ext);
		}
		return null;
	}

	public IgnoredPatterns getIgnoredPatterns(String ext) {
		if(map3.containsKey(ext)){
			return map3.get(ext);
		}
		return null;
	}







}
