package codeconverter.factories;

import codeconverter.BlockDataInterpreter;
import codeconverter.filecodelinesgenerators.CodeLineGenerator;

public interface LanguagesObjectsFactory {


	public CodeLineGenerator getCodeLineGenerator(String ext);
	public BlockDataInterpreter getBlockDataInterpreter(String ext);

}
