package jcodecomparator.test;

import jcodecomparator.core.CompareItemGenerator;

/**
 *
 * @author Nicola Pellicano'
 *
 */

public class TestCompareItemConstructorFactory extends ConcreteCompareItemConstructorFactory{

	private CompareItemGenerator cig;

	public TestCompareItemConstructorFactory(CompareItemGenerator cig) {
		super();
		this.cig=cig;
		fillMap();
	}

	public void fillMap(){
		map.put("org.eclipse.jface.text.TextSelection", new TextSelectionConstructor());
		map.put("org.eclipse.jface.viewers.TreeSelection", new TreeSelectionConstructor(cig));
	}
}
