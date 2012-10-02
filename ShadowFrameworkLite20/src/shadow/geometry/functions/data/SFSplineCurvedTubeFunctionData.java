package shadow.geometry.functions.data;

import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.curves.SFControlPointsCurve;
import shadow.geometry.functions.SFSplineCurvedTubeFunction;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFLibraryReference;
import shadow.renderer.data.SFLibraryReferenceList;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFDataset;
import shadow.system.data.SFNamedParametersObject;

public class SFSplineCurvedTubeFunctionData extends
		SFDataAsset<SFSurfaceFunction> {

	private SFLibraryReferenceList<SFControlPointsCurve> curvesData = new SFLibraryReferenceList<SFControlPointsCurve>(new SFLibraryReference<SFControlPointsCurve>());

	private SFSplineCurvedTubeFunction function;

	public SFSplineCurvedTubeFunctionData() {
		SFNamedParametersObject parametersObject=new SFNamedParametersObject();
		parametersObject.addObject("curvesData", curvesData);
		setData(parametersObject);
	}

	@Override
	public SFDataset generateNewDatasetInstance() {
		return new SFSplineCurvedTubeFunctionData();
	}

	@Override
	protected SFSurfaceFunction buildResource() {
		function = new SFSplineCurvedTubeFunction();
		
		for (int i = 0; i < curvesData.size(); i++) {
			curvesData.get(i).retrieveDataset(new SFDataCenterListener<SFDataAsset<SFControlPointsCurve>>() {
				@Override
				public void onDatasetAvailable(String name, SFDataAsset<SFControlPointsCurve> dataset) {
					function.getCurves().add(dataset.getResource());	
				}
			});
		}

		return function;
	}

	public void addCurve(SFDataAsset<SFControlPointsCurve> curve) {
		SFLibraryReference<SFControlPointsCurve> reference=new SFLibraryReference<SFControlPointsCurve>();
		reference.setDataset(curve);
		curvesData.add(reference);
	}

}
