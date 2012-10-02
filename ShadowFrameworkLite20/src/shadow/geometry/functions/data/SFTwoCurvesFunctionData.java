package shadow.geometry.functions.data;

import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.curves.SFControlPointsCurve;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFLibraryReference;
import shadow.system.data.SFNamedParametersObject;

public abstract class SFTwoCurvesFunctionData extends
		SFDataAsset<SFSurfaceFunction> {

	private SFLibraryReference<SFControlPointsCurve> firstCurve=
		new SFLibraryReference<SFControlPointsCurve>();
	private SFLibraryReference<SFControlPointsCurve> secondCurve=
		new SFLibraryReference<SFControlPointsCurve>();
	
	public SFTwoCurvesFunctionData() {
		SFNamedParametersObject namedParameters=new SFNamedParametersObject();
		namedParameters.addObject("firstCurve", firstCurve);
		namedParameters.addObject("secondCurve", secondCurve);
		setData(namedParameters);
		setReferences(firstCurve,secondCurve);
	}
	
	public SFLibraryReference<SFControlPointsCurve> getFirstCurve(){
		return firstCurve;
	}
	
	public SFLibraryReference<SFControlPointsCurve> getSecondCurve(){
		return secondCurve;
	}
	
	public void setFirstCurve(SFDataAsset<SFControlPointsCurve> data){
		firstCurve.setDataset(data);
	}
	
	public void setSecondCurve(SFDataAsset<SFControlPointsCurve> data){
		secondCurve.setDataset(data);
	}
}