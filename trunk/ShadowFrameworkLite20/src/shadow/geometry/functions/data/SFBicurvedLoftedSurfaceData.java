package shadow.geometry.functions.data;

import shadow.geometry.SFCurve;
import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.functions.SFBicurvedLoftedSurface;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFLibraryReference;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFDataset;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFFloat;

public class SFBicurvedLoftedSurfaceData extends SFDataAsset<SFSurfaceFunction>{

	private SFBicurvedLoftedSurface loftCurve;
	
	@Override
	public SFDataset generateNewDatasetInstance() {
		return new SFBicurvedLoftedSurfaceData();
	}
	
	private SFLibraryReference<SFCurve> centralCurve=
			new SFLibraryReference<SFCurve>();
	private SFLibraryReference<SFCurve> rayCurve=
		new SFLibraryReference<SFCurve>();
	private SFFloat maxTa=new SFFloat(1);
	private SFFloat maxTb=new SFFloat(1);
	
	public SFBicurvedLoftedSurfaceData() {
		SFNamedParametersObject namedParameters=new SFNamedParametersObject();
		namedParameters.addObject("centralCurve", centralCurve);
		namedParameters.addObject("rayCurve", rayCurve);
		setData(namedParameters);
	}
	
	public SFLibraryReference<SFCurve> getFirstCurve(){
		return centralCurve;
	}
	
	public SFLibraryReference<SFCurve> getSecondCurve(){
		return rayCurve;
	}
	
	public SFFloat getMaxTA(){
		return maxTa;
	}
	
	public SFFloat getMaxTB(){
		return maxTb;
	}
	
	
	@Override
	protected SFBicurvedLoftedSurface buildResource() {
		loftCurve=new SFBicurvedLoftedSurface();
		getFirstCurve().retrieveDataset(new SFDataCenterListener<SFDataAsset<SFCurve>>() {
			@Override
			public void onDatasetAvailable(String name,SFDataAsset<SFCurve> dataset) {
				loftCurve.setA(dataset.getResource());
			}
		});
		
		getSecondCurve().retrieveDataset(new SFDataCenterListener<SFDataAsset<SFCurve>>() {
			@Override
			public void onDatasetAvailable(String name,SFDataAsset<SFCurve> dataset) {
				loftCurve.setB(dataset.getResource());
			}
		});
		return loftCurve;
	}
}
