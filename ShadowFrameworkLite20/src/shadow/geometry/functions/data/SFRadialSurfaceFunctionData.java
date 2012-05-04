package shadow.geometry.functions.data;

import shadow.geometry.SFCurve;
import shadow.geometry.functions.SFRadialSurfaceFunction;
import shadow.math.SFValuenf;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFDataset;

public class SFRadialSurfaceFunctionData extends SFTwoCurvesFunctionData{

	private SFRadialSurfaceFunction radialSurface;
	
	@Override
	public SFDataset generateNewDatasetInstance() {
		return new SFRadialSurfaceFunctionData();
	}
	
	@Override
	protected SFRadialSurfaceFunction buildResource() {
		radialSurface=new SFRadialSurfaceFunction();
		getFirstCurve().retrieveDataset(new SFDataCenterListener<SFDataAsset<SFCurve<SFValuenf>>>() {
			@Override
			public void onDatasetAvailable(String name,SFDataAsset<SFCurve<SFValuenf>> dataset) {
				radialSurface.setBorderCurve(dataset.getResource());
			}
		});
		
		getSecondCurve().retrieveDataset(new SFDataCenterListener<SFDataAsset<SFCurve<SFValuenf>>>() {
			@Override
			public void onDatasetAvailable(String name,SFDataAsset<SFCurve<SFValuenf>> dataset) {
				radialSurface.setRayCurve(dataset.getResource());
			}
		});
		return radialSurface;
	}
}
