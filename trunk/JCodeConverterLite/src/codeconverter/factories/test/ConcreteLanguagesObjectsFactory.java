package codeconverter.factories.test;

import java.util.HashMap;
import codeconverter.BlockDataInterpreter;
import codeconverter.factories.LanguagesObjectsFactory;
import codeconverter.filecodelinesgenerators.CodeLineGenerator;

/**
 *
 * @author Nicola Pellicano'
 *
 */


public class ConcreteLanguagesObjectsFactory implements LanguagesObjectsFactory{

	protected HashMap<String, CodeLineGenerator> map=new HashMap<String, CodeLineGenerator>();
	protected HashMap<String, BlockDataInterpreter> map2=new HashMap<String, BlockDataInterpreter>();


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









}
