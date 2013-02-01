
import shadow.renderer.data.utils.SFViewerDatasetFactory;
import shadow.renderer.data.utils.SFViewerObjectsLibrary;
import shadow.system.data.SFDataCenter;

public class CommonData {

	public static SFViewerObjectsLibrary generateDataSettings() {
		// In order to work properly load utilities will need the Datacenter to
		// contain a valid DatasetFactory
		SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
		// We also add a simple data center 
		SFViewerObjectsLibrary objectsLibrary=new SFViewerObjectsLibrary();
		SFDataCenter.setDataCenterImplementation(objectsLibrary);
		return objectsLibrary;
	}

}
