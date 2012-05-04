package shadow.geometry.functions.data;

import shadow.geometry.SFCurve;
import shadow.geometry.functions.SFCurvedTubeFunction;
import shadow.math.SFValuenf;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFDataset;

public class SFCurvedTubeFunctionData extends SFTwoCurvesFunctionData{

	private SFCurvedTubeFunction curvedTube;
	@Override
	public SFDataset generateNewDatasetInstance() {
		return new SFCurvedTubeFunctionData();
	}
	
	@Override
	protected SFCurvedTubeFunction buildResource() {
		curvedTube=new SFCurvedTubeFunction();
		getFirstCurve().retrieveDataset(new SFDataCenterListener<SFDataAsset<SFCurve<SFValuenf>>>() {
			@Override
			public void onDatasetAvailable(String name,SFDataAsset<SFCurve<SFValuenf>> dataset) {
				curvedTube.setCentralCurve(dataset.getResource());
			}
		});
		
		getSecondCurve().retrieveDataset(new SFDataCenterListener<SFDataAsset<SFCurve<SFValuenf>>>() {
			@Override
			public void onDatasetAvailable(String name,SFDataAsset<SFCurve<SFValuenf>> dataset) {
				curvedTube.setRayCurve(dataset.getResource());
			}
		});
		return curvedTube;
	}
}
