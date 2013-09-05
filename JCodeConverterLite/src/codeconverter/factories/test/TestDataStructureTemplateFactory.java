package codeconverter.factories.test;

import codeconverter.templates.test.data.CppDataStructure;
import codeconverter.templates.test.data.CppHeaderStructure;
import codeconverter.templates.test.data.JavaDataStructure;
import codeconverter.templates.test.data.JsDataStructure;

public class TestDataStructureTemplateFactory extends ConcreteDataStructureTemplateFactory{

	public TestDataStructureTemplateFactory() {
		map.put("java", new JavaDataStructure());
		map.put("js", new JsDataStructure());
		map.put("cpp", new CppDataStructure());
		map.put("h", new CppHeaderStructure());
	}



}
