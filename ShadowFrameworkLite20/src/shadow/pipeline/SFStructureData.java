package shadow.pipeline;

import java.util.Iterator;

import shadow.math.SFValuenf;
import shadow.pipeline.parameters.SFParameteri;

public class SFStructureData {

	private SFPipelineStructure structure;
	private SFValuenf values[];

	public SFStructureData(SFPipelineStructure structure,SFParameteri genericsParameters) {
		super();
		this.structure=structure;
		values=new SFValuenf[structure.size()];
		for (int index = 0; index < structure.getAllParameters().size();index++) {
			SFParameteri sfParameteri=structure.getAllParameters().get(index);	
			values[index]=SFParameteri.generateValue(sfParameteri);
			
			if(values[index]==null){
				values[index]=SFParameteri.generateValue(genericsParameters);
			}
		}
	}
	
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
