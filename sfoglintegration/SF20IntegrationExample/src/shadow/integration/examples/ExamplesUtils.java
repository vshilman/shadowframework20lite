package shadow.integration.examples;

import shadow.integration.data.SFGraphicsDictionary;
import shadow.system.data.SFCompositeLibrary;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFObjectsLibrary;
import shadow.system.data.tools.SFDataBuilder;
import shadow.system.data.tools.SFDataUtility;

public class ExamplesUtils {

	static void reloadContent(String sffilename) {
		//Retrieve
		SFCompositeLibrary compositeLibrary=new SFCompositeLibrary();
		SFObjectsLibrary samplesLibrary=new SFObjectsLibrary();
		compositeLibrary.add("samples", samplesLibrary);
		SFDataCenter.setDictionary(new SFGraphicsDictionary(compositeLibrary));
		SFDataUtility.loadDataObject(sffilename, samplesLibrary);
	}

	static void compileAndExport(String sfbfilename,String sffilename) {
		//Compile
		SFObjectsLibrary samplesLibrary = ExamplesUtils.compile(sfbfilename);
		
		//Store
		SFDataUtility.saveDataObject(sffilename, samplesLibrary);
	}

	static SFObjectsLibrary compile(String sfbfilename) {
		SFCompositeLibrary compositeLibrary=new SFCompositeLibrary();
		SFObjectsLibrary samplesLibrary=new SFObjectsLibrary();
		compositeLibrary.add("samples", samplesLibrary);
		SFDataCenter.setDictionary(new SFGraphicsDictionary(compositeLibrary));
		SFDataBuilder builder=new SFDataBuilder();
		builder.loadBuilderData(sfbfilename, samplesLibrary);
		return samplesLibrary;
	}

}
