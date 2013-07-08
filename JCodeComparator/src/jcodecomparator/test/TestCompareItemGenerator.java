package jcodecomparator.test;

import jcodecomparator.compare.CompilationUnitCompareItem;
import jcodecomparator.compare.WorkspaceFileCompareItem;
import jcodecomparator.core.ImageByTypeKeeper;

public class TestCompareItemGenerator extends ConcreteCompareItemGenerator{

	private ImageByTypeKeeper ibtk;


	public TestCompareItemGenerator( ImageByTypeKeeper ibtk) {
		super();
		this.ibtk=ibtk;
		fillMap();

	}

	private void fillMap(){
		map.put("org.eclipse.core.internal.resources.File", new WorkspaceFileCompareItem(ibtk));
		map.put("org.eclipse.jdt.internal.core.CompilationUnit", new CompilationUnitCompareItem(ibtk));

	}



}
