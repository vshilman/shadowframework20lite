package shadow.geometry.geometries;

import java.util.ArrayList;

import shadow.geometry.SFSurfaceFunction;
import shadow.geometry.SFSurfaceFunction.SFSurfaceInfo;
import shadow.math.SFValuenf;
import shadow.operational.grid.SFRectangularGrid;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveBlock;
import shadow.system.SFException;

public class SFSurfaceMeshGeometry extends SFMeshGeometry {

	private class FunctionInformations{
		private SFSurfaceFunction function;
		private SFSurfaceInfo[] infos;
		private FunctionInformations(SFSurfaceFunction function, SFSurfaceInfo[] infos) {
			super();
			this.function = function;
			this.infos = infos;
		}
	}
	
	private ArrayList<FunctionInformations> fuctionInformations=new ArrayList<FunctionInformations>();
	
	public SFSurfaceMeshGeometry() {
		super();
	}

	public SFSurfaceMeshGeometry(SFPrimitive primitive) {
		super(primitive);
	}
	
	public void update(SFRectangularGrid<SFValuenf[]> values,float[] us,float[] vs){
		for (int i = 0; i < fuctionInformations.size(); i++) {
			FunctionInformations fInfo=fuctionInformations.get(i);
			fInfo.function.updateRectangularModel(values, us, vs, fInfo.infos);
		}
	}

	/**
	 * 
	 * @param block a Primitive Block to which this Surface Function is Assigned
	 * @param function
	 * @throws SFException when this SFQuadsSurfaceGeometry Primitive does not make use of the block
	 */
	public void setFunction(SFPrimitiveBlock block, SFSurfaceFunction function,SFSurfaceInfo info) throws SFException {
		SFPrimitiveBlock[] blocks=getPrimitive().getBlocks();
		SFSurfaceInfo[] infos=new SFSurfaceInfo[blocks.length];
		boolean found=false;
		for (int i = 0; i < blocks.length; i++) {
			if(blocks[i]==block){
				infos[i]=info;
				found=true;
			}else{
				infos[i]=null;
			}
		}
		if(!found){	
			throw new SFException("Cannot assign a function to a QuadsSurfaceGeometry on the Primitive Block '"+block+"' because this QuadsSurfaceGeometry primitive does not use " +
				"the Primitive Block '"+block+"'");
		}else{
			fuctionInformations.add(new FunctionInformations(function, infos));
		}
	}

	/**
	 * Set up the function to be used on TexCoord Evaluation
	 * @param function
	 * @throws SFException when this SFQuadsSurfaceGeometry cannot use TexCoord 
	 */
	public void setTexCoordGeometry(SFSurfaceFunction function) throws SFException {
		setFunction(SFPrimitiveBlock.TXO, function, SFSurfaceInfo.POSITION);
	}

	public void setMainGeometryFunction(SFSurfaceFunction function) {
		SFPrimitiveBlock[] blocks=getPrimitive().getBlocks();
		SFSurfaceInfo[] infos=new SFSurfaceInfo[blocks.length];
		for (int i = 0; i < blocks.length; i++) {
			if(blocks[i]==SFPrimitiveBlock.POSITION){
				infos[i]=SFSurfaceInfo.POSITION;
			}else if(blocks[i]==SFPrimitiveBlock.NORMAL){
				infos[i]=SFSurfaceInfo.NORMAL;
			}else if(blocks[i]==SFPrimitiveBlock.DU){
				infos[i]=SFSurfaceInfo.DU;
			}else if(blocks[i]==SFPrimitiveBlock.DV){
				infos[i]=SFSurfaceInfo.DV;
			}
		}
		fuctionInformations.add(new FunctionInformations(function, infos));
	}

}