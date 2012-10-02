package shadow.geometry.curves.data;

import shadow.geometry.SFCurve;
import shadow.geometry.curves.SFTransformedCurve;
import shadow.math.SFTransform3f;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFLibraryReference;
import shadow.renderer.data.transforms.SFNoTransformData;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFNamedParametersObject;

public class SFTransformedCurveData extends SFDataAsset<SFCurve>{

	private SFLibraryReference<SFTransform3f> transform=new SFLibraryReference<SFTransform3f>(new SFNoTransformData());
	private SFLibraryReference<SFCurve> curve =new SFLibraryReference<SFCurve>();
	
	public SFTransformedCurveData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("transform", transform);
		parameters.addObject("curve", curve);
		setData(parameters);
	}
	
	@Override
	protected SFCurve buildResource() {
		final SFTransformedCurve trCurve=new SFTransformedCurve();
		this.transform.retrieveDataset(new SFDataCenterListener<SFDataAsset<SFTransform3f>>() {
			@Override
			public void onDatasetAvailable(String name, SFDataAsset<SFTransform3f> dataset) {
				trCurve.setTrasnform(dataset.getResource());
			}
		});
		this.curve.retrieveDataset(new SFDataCenterListener<SFDataAsset<SFCurve>>() {
			@Override
			public void onDatasetAvailable(String name, SFDataAsset<SFCurve> dataset) {
				trCurve.setCurve(dataset.getResource());
			}
		});
		return trCurve;
	}
	
}
