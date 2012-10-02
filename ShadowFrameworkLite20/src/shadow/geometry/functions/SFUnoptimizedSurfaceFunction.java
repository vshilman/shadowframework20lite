package shadow.geometry.functions;

import shadow.geometry.SFSurfaceFunction;
import shadow.math.SFValuenf;
import shadow.math.SFVertex2f;
import shadow.math.SFVertex3f;
import shadow.operational.grid.SFRectangularGrid;
import shadow.pipeline.SFIndexRange;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.SFPrimitiveBlock;
import shadow.system.SFArray;

public abstract class SFUnoptimizedSurfaceFunction implements SFSurfaceFunction{
	
	@Override
	public int extractParametrizedModel(SFArray<SFValuenf> parameters, SFIndexRange range,
			SFPrimitiveArray array, SFPrimitiveBlock block, int gridIndex) {
		int position=array.getPrimitiveData(gridIndex).generateElements(range.getSize());
		updateParametrizedModel(position, parameters, range, array, block, gridIndex);
		return position;
	}
	
	@Override
	public void updateRectangularModel(SFRectangularGrid<SFValuenf[]> values, float[] us,
			float[] vs, SFSurfaceInfo[] infos) {
		for (int gridIndex = 0; gridIndex < infos.length; gridIndex++) {
			if(infos[gridIndex]==SFSurfaceInfo.POSITION){
				for (int j = 0; j < values.getWidth(); j++) {
					for (int k = 0; k < values.getHeight(); k++) {
						values.getValue(k, j)[gridIndex].set(getPosition(us[j], vs[k]));
					}
				}
			}else if(infos[gridIndex]==SFSurfaceInfo.NORMAL){
				for (int j = 0; j < values.getWidth(); j++) {
					for (int k = 0; k < values.getHeight(); k++) {
						values.getValue(k, j)[gridIndex].set(getNormal(us[j], vs[k]));
					}
				}
			}else if(infos[gridIndex]==SFSurfaceInfo.DU){
				for (int j = 0; j < values.getWidth(); j++) {
					for (int k = 0; k < values.getHeight(); k++) {
						values.getValue(k, j)[gridIndex].set(getDu(us[j], vs[k]));
					}
				}
			}else if(infos[gridIndex]==SFSurfaceInfo.DV){
				for (int j = 0; j < values.getWidth(); j++) {
					for (int k = 0; k < values.getHeight(); k++) {
						values.getValue(k, j)[gridIndex].set(getDv(us[j], vs[k]));
					}
				}
			}
		}	
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
			switch (block) {
				case NORMAL: value.set(getNormal(u, v)); break;
				case DU: value.set(getDu(u, v)); break;
				case DV: value.set(getDv(u, v)); break;
				default: value.set(getPosition(u, v)); break;
			}
			primitiveData.setElement(position+i, value);
		}
	}
	
	private static final float eps=0.01f;
	
	public abstract float getX(float u,float v);
	public abstract float getY(float u,float v);
	public abstract float getZ(float u,float v);
	
	public SFVertex3f getPosition(float u,float v){
		return new SFVertex3f(getX(u, v),getY(u, v),getZ(u, v));
	}
	
	public void getPosition(float u,float v,SFVertex3f write){	
		write.set3f(getX(u, v),getY(u, v),getZ(u, v));
	}

	public SFVertex3f getDu(float u,float v){
		SFVertex3f p1=getPosition(u-eps, v);
		SFVertex3f p2=getPosition(u+eps, v);
		p2.subtract3f(p1);
		p2.mult(1.0f/(2*eps));
		return p2;
	}
	
	public SFVertex3f getDv(float u,float v){
		SFVertex3f p1=getPosition(u, v);
		SFVertex3f p2=getPosition(u, v+eps);
		p2.subtract3f(p1);
		p2.mult(1.0f/eps);
		return p2;
	}
	
	public SFVertex3f getNormal(float u,float v){
		SFVertex3f normal=getDu(u, v).cross(getDv(u, v));
		normal.normalize3f();
		return normal;
	}
	
	
}
