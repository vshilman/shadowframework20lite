package shadow.geometry.functions.data;

import shadow.geometry.curves.SFControlPointsCurve;
import shadow.geometry.functions.SFCurvedTubeFunction;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFDataset;

public class SFCurvedTubeFunctionData extends SFTwoCurvesFunctionData{

	@Override
	public SFDataset generateNewDatasetInstance() {
		return new SFCurvedTubeFunctionData();
	}
	
	@Override
	protected SFCurvedTubeFunction buildResource() {
		final SFCurvedTubeFunction curvedTube=new SFCurvedTubeFunction();
		getFirstCurve().retrieveDataset(new SFDataCenterListener<SFDataAsset<SFControlPointsCurve>>() {
			@Override
			public void onDatasetAvailable(String name,SFDataAsset<SFControlPointsCurve> dataset) {
				curvedTube.setCentralCurve(dataset.getResource());
			}
		});
		
		getSecondCurve().retrieveDataset(new SFDataCenterListener<SFDataAsset<SFControlPointsCurve>>() {
			@Override
			public void onDatasetAvailable(String name,SFDataAsset<SFControlPointsCurve> dataset) {
				curvedTube.setRayCurve(dataset.getResource());
			}
		});
		return curvedTube;
	}
}
