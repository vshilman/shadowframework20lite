package shadow.pipeline;

import java.util.Iterator;

import shadow.math.SFValuenf;
import shadow.pipeline.parameters.SFParameteri;

public class SFStructureData {

	private SFPipelineStructure structure;
	private SFValuenf values[];

	public SFStructureData(SFPipelineStructure structure) {
		super();
		this.structure=structure;
		values=new SFValuenf[structure.size()];
		int index=0;
		for (Iterator<SFParameteri> iterator=structure.getAllParameters()
				.iterator(); iterator.hasNext(); index++) {
			SFParameteri sfParameteri=iterator.next();
			values[index]=SFParameteri.generateValue(sfParameteri);
		}
	}

	public int size() {
		return values.length;
	}

	public SFPipelineStructure getStructure() {
		return structure;
	}

	public SFValuenf getValue(int index) {
		//TODO: why at all?????????????????????????????????
		try {
			return values[index];
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new SFValuenf(3);
	}

	public SFValuenf[] getValues() {
		return values;
	}
}
