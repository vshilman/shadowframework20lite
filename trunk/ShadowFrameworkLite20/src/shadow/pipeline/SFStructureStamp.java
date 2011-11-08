package shadow.pipeline;

import java.util.Iterator;
import java.util.List;

import shadow.pipeline.parameters.SFParameteri;

public class SFStructureStamp {

	private int[] data;
	private int sum;

	public SFStructureStamp(SFPipelineStructure structure) {
		List<SFParameteri> parameters=structure.getAllParameters();
		this.data=new int[parameters.size()];
		int i=0;
		sum=0;
		for (Iterator<SFParameteri> iterator=parameters.iterator(); iterator
				.hasNext(); i++) {
			SFParameteri sfParameteri=(SFParameteri) iterator.next();
			short type=sfParameteri.getType();
			data[i]=SFParameteri.getTypeDimension(type);
			sum+=data[i];
		}
	}
	
	public int[] getData() {
		return data;
	}

	public int size() {
		return data.length;
	}

	public int getDimension(int index) {
		return data[index];
	}

	public int getSum() {
		return sum;
	}
	
}
