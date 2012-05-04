package shadow.operational;

import shadow.math.SFValuenf;
import shadow.pipeline.SFPipelineGrid;
import shadow.system.SFArray;
import shadow.system.SFArrayElementException;

public class SFStandardQuadExtractor implements SFSurfaceQuadsExtractor{

	private SFSurfaceQuadsExtractor effectiveExtractor;
	
	public SFStandardQuadExtractor(SFPipelineGrid grid){
		int size=grid.getCorners().length;
		if(size==3){
			this.effectiveExtractor=new SFStandardQuadToTriangleExtractor(grid);
		}else{
			this.effectiveExtractor=new SFStandardQuadToQuadExtractor(grid);
		}
	}
	
	@Override
	public void extractIndices(int[][] output, int positionInOutputs, SFArray<SFValuenf> array, SFValuenf[] data,
			int offset, int Nu, int Nv, int I, int J) throws SFArrayElementException {
		effectiveExtractor.extractIndices(output, positionInOutputs, array, data, offset, Nu, Nv, I, J);
	}

	
	@Override
	public int getPrimitivesNumber() {
		return effectiveExtractor.getPrimitivesNumber();
	}
	
	@Override
	public void init() {
		
	}
}
