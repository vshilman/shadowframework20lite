package codeconverter.factories;

import codeconverter.BlockDataInterpreter;
import codeconverter.filecodelinesgenerators.CodeLineGenerator;

/**
 *Factory of objects that depend from a specified language
 *
 * @author Nicola Pellicano'
 *
 */

public interface LanguagesObjectsFactory {

	/**
	 *
	 * @param ext
	 * @return the code line generator
	 */

	public CodeLineGenerator getCodeLineGenerator(String ext);

	/**
	 *
	 * @param ext
	 * @return the block's interpreter
	 */

	public BlockDataInterpreter getBlockDataInterpreter(String ext);
}
