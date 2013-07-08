package jcodecomparator.test;


import java.util.HashMap;

import jcodecomparator.core.ImageByTypeKeeper;

import org.eclipse.compare.ITypedElement;
import org.eclipse.swt.graphics.Image;

public class ConcreteImageByTypeKeeper implements ImageByTypeKeeper{


	protected HashMap<String,Image> map=new HashMap<String,Image>();


	@Override
	public Image getImageByType(String type) {
		if(map.containsKey(type)){
			return map.get(type);
		}
		return map.get(ITypedElement.UNKNOWN_TYPE);
	}




}
