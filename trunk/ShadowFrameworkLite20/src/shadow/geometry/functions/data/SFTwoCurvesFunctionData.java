package shadow.geometry.functions.data;

import shadow.geometry.SFCurve;
import shadow.geometry.SFSurfaceFunction;
import shadow.math.SFValuenf;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFLibraryReference;
import shadow.system.data.utils.SFGenericInfoObjectBuilder;

public abstract class SFTwoCurvesFunctionData extends
		SFDataAsset<SFSurfaceFunction> {

	private SFLibraryReference<SFDataAsset<SFCurve<SFValuenf>>> firstCurve=
		new SFLibraryReference<SFDataAsset<SFCurve<SFValuenf>>>();
	private SFLibraryReference<SFDataAsset<SFCurve<SFValuenf>>> secondCurve=
		new SFLibraryReference<SFDataAsset<SFCurve<SFValuenf>>>();
	
	public SFTwoCurvesFunctionData() {
		setData(SFGenericInfoObjectBuilder.generateObjectBuilder(firstCurve,secondCurve));
	}
	
	public SFLibraryReference<SFDataAsset<SFCurve<SFValuenf>>> getFirstCurve(){
		return firstCurve;
	}
	
	public SFLibraryReference<SFDataAsset<SFCurve<SFValuenf>>> getSecondCurve(){
		return secondCurve;
	}
	
	public void setFirstCurve(SFDataAsset<SFCurve<SFValuenf>> data){
		firstCurve.setDataset(data);
	}
	
	public void setSecondCurve(SFDataAsset<SFCurve<SFValuenf>> data){
		secondCurve.setDataset(data);
	}
}