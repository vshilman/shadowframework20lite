package jcodecomparator.test;

import jcodecomparator.core.DefaultLineStyler;

public class TestLineStyleFactory extends ConcreteLineStylerfactory{

	public TestLineStyleFactory() {
		map.put("java", new DefaultLineStyler(new JavaScanner()));
		map.put("js", new DefaultLineStyler(new JsScanner()));
	}

}
