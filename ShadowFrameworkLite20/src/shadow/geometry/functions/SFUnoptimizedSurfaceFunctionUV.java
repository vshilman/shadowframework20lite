package shadow.geometry.functions;

import shadow.geometry.SFSurfaceFunction;
import shadow.math.SFValuenf;
import shadow.math.SFVertex2f;
import shadow.operational.grid.SFRectangularGrid;
import shadow.pipeline.SFIndexRange;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.SFPrimitiveBlock;
import shadow.system.SFArray;

public abstract class SFUnoptimizedSurfaceFunctionUV implements SFSurfaceFunction{

	public abstract SFVertex2f getTexCoord(float u, float v);
	
	@Override
	public void updateRectangularModel(SFRectangularGrid<SFValuenf[]> values, float[] us,
			float[] vs, SFSurfaceInfo[] infos) {
		for (int gridIndex = 0; gridIndex < infos.length; gridIndex++) {
			if(infos[gridIndex]==SFSurfaceInfo.POSITION){
				for (int j = 0; j < values.getWidth(); j++) {
					for (int k = 0; k < values.getHeight(); k++) {
						values.getValue(k, j)[gridIndex].set(getTexCoord(us[j], vs[k]));
					}
				}
			}
		}	
	}
	
	@Override
	public int extractParametrizedModel(SFArray<SFValuenf> parameters, SFIndexRange range,
			SFPrimitiveArray array, SFPrimitiveBlock block, int gridIndex) {
		
		int position=array.getPrimitiveData(gridIndex).generateElements(range.getSize());
		
		updateParametrizedModel(position, parameters, range, array, block, gridIndex);
		
		return position;
	}
	
	@Override
	public void updateParametrizedModel(int position, SFArray<SFValuenf> parameters,
			SFIndexRange range, SFPrimitiveArray array, SFPrimitiveBlock block, int gridIndex) {
		
		SFVertex2f uv=new SFVertex2f(0, 0);
		SFArray<SFValuenf> primitiveData=array.getPrimitiveData(gridIndex);
		SFValuenf value=primitiveData.generateSample();
		for (int i = 0; i < range.getSize(); i++) {
			parameters.getElement(range.getPosition()+i, uv);
			float u=uv.getX();
			float v=uv.getY();
			value.set(getTexCoord(u,v));
			primitiveData.setElement(position+i, value);
		}
	}
}
