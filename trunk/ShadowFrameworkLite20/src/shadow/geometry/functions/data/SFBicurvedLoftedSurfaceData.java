package shadow.geometry.functions.data;

import shadow.geometry.SFCurve;
import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.functions.SFBicurvedLoftedSurface;
import shadow.math.SFVertex3f;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFLibraryReference;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFDataset;
import shadow.system.data.objects.SFFloat;
import shadow.system.data.utils.SFGenericInfoObjectBuilder;

public class SFBicurvedLoftedSurfaceData extends SFDataAsset<SFSurfaceFunction>{

	private SFBicurvedLoftedSurface loftCurve;
	
	@Override
	public SFDataset generateNewDatasetInstance() {
		return new SFBicurvedLoftedSurfaceData();
	}
	
	private SFLibraryReference<SFDataAsset<SFCurve<SFVertex3f>>> centralCurve=
			new SFLibraryReference<SFDataAsset<SFCurve<SFVertex3f>>>();
	private SFLibraryReference<SFDataAsset<SFCurve<SFVertex3f>>> rayCurve=
		new SFLibraryReference<SFDataAsset<SFCurve<SFVertex3f>>>();
	private SFFloat maxTa=new SFFloat(1);
	private SFFloat maxTb=new SFFloat(1);
	
	public SFBicurvedLoftedSurfaceData() {
		setData(SFGenericInfoObjectBuilder.generateObjectBuilder(centralCurve,rayCurve));
	}
	
	public SFLibraryReference<SFDataAsset<SFCurve<SFVertex3f>>> getFirstCurve(){
		return centralCurve;
	}
	
	public SFLibraryReference<SFDataAsset<SFCurve<SFVertex3f>>> getSecondCurve(){
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
		getFirstCurve().retrieveDataset(new SFDataCenterListener<SFDataAsset<SFCurve<SFVertex3f>>>() {
			@Override
			public void onDatasetAvailable(String name,SFDataAsset<SFCurve<SFVertex3f>> dataset) {
				loftCurve.setA(dataset.getResource());
			}
		});
		
		getSecondCurve().retrieveDataset(new SFDataCenterListener<SFDataAsset<SFCurve<SFVertex3f>>>() {
			@Override
			public void onDatasetAvailable(String name,SFDataAsset<SFCurve<SFVertex3f>> dataset) {
				loftCurve.setB(dataset.getResource());
			}
		});
		return loftCurve;
	}
}
