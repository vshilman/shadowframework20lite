package shadow.pipeline;

import java.util.Iterator;

import shadow.math.SFValuenf;
import shadow.pipeline.parameters.SFParameteri;

public class SFStructureData {

	private SFPipelineStructureInstance structure;
	private SFValuenf values[];

	public SFStructureData(SFPipelineStructureInstance structure,SFParameteri genericsParameters) {
		super();
		this.structure=structure;
		values=new SFValuenf[structure.size()];
		int index=0;
		for (Iterator<SFParameteri> iterator=structure.getAllParameters()
				.iterator(); iterator.hasNext(); index++) {
			SFParameteri sfParameteri=iterator.next();
			
			values[index]=SFParameteri.generateValue(sfParameteri);
			
			if(values[index]==null){
				values[index]=SFParameteri.generateValue(genericsParameters);
			}
		}
	}
	
	public SFStructureData(SFPipelineStructureInstance structure) {
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

	public SFPipelineStructureInstance getStructure() {
		return structure;
	}

	public SFValuenf getValue(int index) {
		return values[index];
	}

	public SFValuenf[] getValues() {
		return values;
	}
}
