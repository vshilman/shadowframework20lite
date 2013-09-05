package codeconverter;

import java.io.InputStream;
import java.io.StringWriter;

import codeconverter.factories.ComparatorFactory;
import codeconverter.factories.LanguagesObjectsFactory;

public class ComparisonDelegate {

	private ComparatorFactory cf;
	private LanguagesObjectsFactory lof;


	public ComparisonDelegate(ComparatorFactory cf, LanguagesObjectsFactory lof) {
		super();
		this.cf = cf;
		this.lof = lof;
	}


	public static DifferentiationResult compareFiles(String firstTest, String secondTest,InputStream firstStream, InputStream secondStream, boolean leftToRight){




		return null;
	}


}
