package codeconverter.templates.test.data;

public class TestDataStructureTemplateFactory extends ConcreteDataStructureTemplateFactory{

	public TestDataStructureTemplateFactory() {
		map.put("java", new JavaDataStructure());
		map.put("js", new JsDataStructure());
		map.put("cpp", new CppDataStructure());
		map.put("h", new CppHeaderStructure());
	}



}
