package shadow.geometry.functions.data;

import shadow.geometry.SFCurve;
import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.curves.SFControlPointsCurve;
import shadow.geometry.functions.SFTensorProductSurface;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFLibraryReference;
import shadow.renderer.data.SFLibraryReferenceList;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFNamedParametersObject;

public class SFTensorProductSurfaceData extends SFDataAsset<SFSurfaceFunction>{

	private SFLibraryReferenceList<SFCurve> guideLines=new SFLibraryReferenceList<SFCurve>(new SFLibraryReference<SFCurve>());
	private SFLibraryReference<SFControlPointsCurve> productCurve=new SFLibraryReference<SFControlPointsCurve>();
	
	public SFTensorProductSurfaceData(){
		SFNamedParametersObject namedParameters=new SFNamedParametersObject();
		namedParameters.addObject("guideLines", guideLines);
		namedParameters.addObject("productCurve", productCurve);
		setData(namedParameters);
	}
	
	
	@Override
	protected SFSurfaceFunction buildResource() {
		final SFTensorProductSurface surface=new SFTensorProductSurface();
		
		final SFCurve[] guideLines=(SFCurve[])(new SFCurve[this.guideLines.size()]);
		for (int i = 0; i < guideLines.length; i++) {
			final int index=i;
			this.guideLines.get(i).retrieveDataset(new SFDataCenterListener<SFDataAsset<SFCurve>>() {
				@Override
				public void onDatasetAvailable(String name, SFDataAsset<SFCurve> dataset) {
					guideLines[index]=dataset.getResource();
				}
			});
		}
		
		productCurve.retrieveDataset(new SFDataCenterListener<SFDataAsset<SFControlPointsCurve>>() {
			@Override
			public void onDatasetAvailable(String name,
					SFDataAsset<SFControlPointsCurve> dataset) {
				surface.setProductCurve(dataset.getResource());
			}
		});
		
		surface.setGuideLines(guideLines);
		return surface;
	}
}
