package shadow.geometry.vertices;

import java.util.ArrayList;

import shadow.geometry.SFValuesList;
import shadow.math.SFRandomizer;
import shadow.math.SFValuenf;
import shadow.math.SFVertex2f;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFInt;
import shadow.system.data.objects.SFShort;

public class SFRandomizedValueListData2D extends SFDataAsset<SFValuesList<SFValuenf>>{
	
	private SFInt seed=new SFInt(9000);
	private SFShort size=new SFShort((short)0);

	public SFRandomizedValueListData2D() {
		super();
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("size", size);
		parameters.addObject("seed", seed);
		setData(parameters);
	}
	
	@Override
	protected SFValuesList<SFValuenf> buildResource() {
		ArrayList<SFValuenf> vertices=new ArrayList<SFValuenf>();
		SFRandomizer randomizer=new SFRandomizer(seed.getIntValue());
		for (int i = 0; i < size.getShortValue(); i++) {
			vertices.add(new SFVertex2f(randomizer.randomFloat(),randomizer.randomFloat()));
		}
		
		return new SFArrayListValueList(vertices);
	} 
}
