package codeconverter.filecodelinesgenerators.test;

public class TestCodeLineGeneratorFactory extends MyCodeLineGeneratorFactory {

	public TestCodeLineGeneratorFactory() {

		map.put("default", new DefaultCodeLineGenerator());
		map.put("java", new DefaultCodeLineGenerator());
		map.put("js", new DefaultCodeLineGenerator());
		map.put("h", new CppHeaderCodeLineGenerator());
		map.put("cpp", new CppCodeLineGenerator());

	}


}
