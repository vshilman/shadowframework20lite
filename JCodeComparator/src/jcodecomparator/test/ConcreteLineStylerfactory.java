package jcodecomparator.test;

import java.util.HashMap;

import jcodecomparator.core.LineBackgroundStylerListener;
import jcodecomparator.core.LineStylerFactory;

import org.eclipse.swt.custom.LineStyleListener;

public class ConcreteLineStylerfactory implements LineStylerFactory{


	protected HashMap<String, LineBackgroundStylerListener> map=new HashMap<>();

	@Override
	public LineBackgroundStylerListener getLineStyler(String type) {
		return map.get(type);
	}






}
