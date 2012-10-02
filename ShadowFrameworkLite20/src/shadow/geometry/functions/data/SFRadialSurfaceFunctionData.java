package shadow.geometry.functions.data;

import shadow.geometry.curves.SFControlPointsCurve;
import shadow.geometry.functions.SFRadialSurfaceFunction;
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
		getFirstCurve().retrieveDataset(new SFDataCenterListener<SFDataAsset<SFControlPointsCurve>>() {
			@Override
			public void onDatasetAvailable(String name,SFDataAsset<SFControlPointsCurve> dataset) {
				radialSurface.setBorderCurve(dataset.getResource());
			}
		});
		
		getSecondCurve().retrieveDataset(new SFDataCenterListener<SFDataAsset<SFControlPointsCurve>>() {
			@Override
			public void onDatasetAvailable(String name,SFDataAsset<SFControlPointsCurve> dataset) {
				radialSurface.setRayCurve(dataset.getResource());
			}
		});
		return radialSurface;
	}
}
