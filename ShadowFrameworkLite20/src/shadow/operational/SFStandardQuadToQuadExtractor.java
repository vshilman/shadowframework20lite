package shadow.operational;

import shadow.math.SFValuenf;
import shadow.pipeline.SFPipelineGrid;
import shadow.system.SFArray;
import shadow.system.SFArrayElementException;

public class SFStandardQuadToQuadExtractor implements SFSurfaceQuadsExtractor{

	private SFPipelineGrid grid;
	
	protected SFStandardQuadToQuadExtractor(SFPipelineGrid grid){
		this.grid=grid;
	}
	
	@Override
	public void extractIndices(int[][] output, int positionInOutputs, SFArray<SFValuenf> array, SFValuenf[] data,
			int offset, int Nu, int Nv, int I, int J) throws SFArrayElementException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPrimitivesNumber() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
}
