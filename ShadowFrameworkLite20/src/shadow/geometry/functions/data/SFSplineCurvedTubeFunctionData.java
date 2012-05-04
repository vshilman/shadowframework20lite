package shadow.geometry.functions.data;

import shadow.geometry.SFCurve;
import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.functions.SFSplineCurvedTubeFunction;
import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFLibraryReference;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFDataset;
import shadow.system.data.objects.SFDataList;

public class SFSplineCurvedTubeFunctionData extends
		SFDataAsset<SFSurfaceFunction> {

	private SFDataList<SFLibraryReference<SFDataAsset<SFCurve<SFValuenf>>>> curvesData = new SFDataList<SFLibraryReference<SFDataAsset<SFCurve<SFValuenf>>>>(
			new SFLibraryReference<SFDataAsset<SFCurve<SFValuenf>>>());

	private SFSplineCurvedTubeFunction function;

	public SFSplineCurvedTubeFunctionData() {
		setData(curvesData);
	}

	@Override
	public SFDataset generateNewDatasetInstance() {
		return new SFSplineCurvedTubeFunctionData();
	}

	@Override
	protected SFSurfaceFunction buildResource() {
		function = new SFSplineCurvedTubeFunction();
		SFDataCenterListener<SFDataAsset<SFCurve<SFValuenf>>> listener = new SFDataCenterListener<SFDataAsset<SFCurve<SFValuenf>>>() {
			@Override
			public void onDatasetAvailable(String name,
					SFDataAsset<SFCurve<SFValuenf>> dataset) {
				function.getCurves().add(dataset.getResource());
			}
		};
		for (int i = 0; i < curvesData.size(); i++) {
			curvesData.get(i).retrieveDataset(listener);
		}

		return function;
	}

	public void addCurve(SFDataAsset<SFCurve<SFValuenf>> curve) {
		SFLibraryReference<SFDataAsset<SFCurve<SFValuenf>>> reference = new SFLibraryReference<SFDataAsset<SFCurve<SFValuenf>>>();
		reference.setDataset(curve);
		curvesData.add(reference);
	}

	public void addCurve(String name) {
		SFLibraryReference<SFDataAsset<SFCurve<SFValuenf>>> reference = new SFLibraryReference<SFDataAsset<SFCurve<SFValuenf>>>();
		reference.setReference(name);
		curvesData.add(reference);
	}
}
