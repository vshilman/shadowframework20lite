package jcodecomparator.test;


import org.eclipse.compare.ITypedElement;
import org.eclipse.ui.plugin.AbstractUIPlugin;

public class TestImageByTypeKeeper extends ConcreteImageByTypeKeeper{


	public TestImageByTypeKeeper() {
		map.put("java",AbstractUIPlugin.imageDescriptorFromPlugin("JCodeComparator", "icons/java22.png").createImage());
		map.put("js",AbstractUIPlugin.imageDescriptorFromPlugin("JCodeComparator", "icons/js22.png").createImage());
		map.put(ITypedElement.TEXT_TYPE,AbstractUIPlugin.imageDescriptorFromPlugin("JCodeComparator", "icons/sample.gif").createImage());
		map.put(ITypedElement.UNKNOWN_TYPE,AbstractUIPlugin.imageDescriptorFromPlugin("JCodeComparator", "icons/unk22.png").createImage());
	}


}
