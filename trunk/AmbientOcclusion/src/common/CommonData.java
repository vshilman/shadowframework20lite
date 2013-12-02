package common;


import shadow.renderer.data.utils.SFViewerDatasetFactory;
import shadow.renderer.data.utils.SFViewerObjectsLibrary;
import shadow.system.data.SFDataCenter;

public class CommonData {

	public static SFViewerObjectsLibrary generateDataSettings() {
		SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
		SFViewerObjectsLibrary objectsLibrary=new SFViewerObjectsLibrary();
		SFDataCenter.setDataCenterImplementation(objectsLibrary);
		return objectsLibrary;
	}

}
