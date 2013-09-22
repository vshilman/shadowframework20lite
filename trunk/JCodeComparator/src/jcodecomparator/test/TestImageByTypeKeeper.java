package jcodecomparator.test;


import org.eclipse.compare.ITypedElement;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 *
 * @author Nicola Pellicano'
 *
 */

public class TestImageByTypeKeeper extends ConcreteImageByTypeKeeper{


	public TestImageByTypeKeeper() {
		map.put("java",AbstractUIPlugin.imageDescriptorFromPlugin("JCodeComparator", "icons/java22.png").createImage());
		map.put("js",AbstractUIPlugin.imageDescriptorFromPlugin("JCodeComparator", "icons/js22.png").createImage());
		map.put("cpp", AbstractUIPlugin.imageDescriptorFromPlugin("JCodeComparator", "icons/cpp22.png").createImage());
		map.put("h", AbstractUIPlugin.imageDescriptorFromPlugin("JCodeComparator", "icons/cpp22.png").createImage());
		map.put(ITypedElement.TEXT_TYPE,AbstractUIPlugin.imageDescriptorFromPlugin("JCodeComparator", "icons/sample.gif").createImage());
		map.put(ITypedElement.UNKNOWN_TYPE,AbstractUIPlugin.imageDescriptorFromPlugin("JCodeComparator", "icons/unk22.png").createImage());
	}


}
