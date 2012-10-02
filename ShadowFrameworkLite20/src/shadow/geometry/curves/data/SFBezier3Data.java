package shadow.geometry.curves.data;

import shadow.geometry.SFValuesList;
import shadow.geometry.curves.SFBezier3;
import shadow.geometry.curves.SFStandardAbstractCurve;
import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFDataAssetObject;
import shadow.system.data.SFNamedParametersObject;

public class SFBezier3Data extends SFDataAsset<SFStandardAbstractCurve<SFValuenf>> {

	protected SFDataAssetObject<SFValuesList<SFValuenf>> vertices = 
		new SFDataAssetObject<SFValuesList<SFValuenf>>(null);

	public SFBezier3Data(){
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("vertices", vertices);
		setData(parameters);
	}
	
	@Override
	protected SFStandardAbstractCurve<SFValuenf> buildResource() {
		SFValuesList<SFValuenf> values=vertices.getResource();
		SFVertex3f A=new SFVertex3f();
		SFVertex3f B=new SFVertex3f();
		SFVertex3f C=new SFVertex3f();
		SFVertex3f D=new SFVertex3f();
		
		values.getValue(0, A);
		values.getValue(1, B);
		values.getValue(2, C);
		values.getValue(3, D);
		
		SFBezier3<SFValuenf> bezier3=new SFBezier3<SFValuenf>(A,B,C,D);
		
		return bezier3;
	}
}
