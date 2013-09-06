package codeconverter.factories.test;

import tests.tmp.JavaIgnoredPatterns;
import tests.tmp.NothingToIgnorePatterns;
import codeconverter.cpp.CppCodePatternInterpreter;
import codeconverter.cpp.codelines.CppHeaderCodePatternInterpreter;
import codeconverter.filecodelinesgenerators.test.CppCodeLineGenerator;
import codeconverter.filecodelinesgenerators.test.CppHeaderCodeLineGenerator;
import codeconverter.filecodelinesgenerators.test.DefaultCodeLineGenerator;
import codeconverter.java.JavaCodePatternInterpreter;
import codeconverter.js.JsCodePatternInterpreter;

public class TestLanguagesObjectsFactory extends ConcreteLanguagesObjectsFactory {

	public TestLanguagesObjectsFactory() {

		map.put("default", new DefaultCodeLineGenerator());
		map.put("java", new DefaultCodeLineGenerator());
		map.put("js", new DefaultCodeLineGenerator());
		map.put("h", new CppHeaderCodeLineGenerator());
		map.put("cpp", new CppCodeLineGenerator());

		map2.put("java", new JavaCodePatternInterpreter());
		map2.put("js", new JsCodePatternInterpreter());
		map2.put("cpp", new CppCodePatternInterpreter());
		map2.put("h", new CppHeaderCodePatternInterpreter());


		map3.put("java", new JavaIgnoredPatterns());
		map3.put("js", new NothingToIgnorePatterns());

	}


}
