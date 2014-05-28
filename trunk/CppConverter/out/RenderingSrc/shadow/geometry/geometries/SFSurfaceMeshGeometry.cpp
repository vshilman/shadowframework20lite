#ifndef shadow_geometry_geometries_H_
#define shadow_geometry_geometries_H_

#include "java/util/ArrayList.h"
#include "java/util/Arrays.h"

#include "shadow/geometry/SFSurfaceFunction.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/math/SFVertex2f.h"
#include "shadow/math/SFVertex3f.h"
#include "shadow/pipeline/SFIndexRange.h"
#include "shadow/pipeline/SFPrimitive.h"
#include "shadow/pipeline/SFPrimitiveArray.h"
#include "shadow/pipeline/SFPrimitiveBlock.h"
#include "shadow/system/SFArray.h"
#include "shadow/system/SFException.h"

class SFSurfaceMeshGeometry extends SFMeshGeometry {

//	protected int[] valuesPositions;

	class FunctionInformations{

//		protected SFSurfaceFunction function;
//		protected SFPrimitiveBlock[] infos;
//		protected int[] gridIndex;

//		protected SFArray<SFValuenf> parametersArray;
//		protected SFIndexRange parametersIndexRange;

//		FunctionInformations(SFSurfaceFunction function, SFPrimitiveBlock[] infos,int[] gridIndex) {
//			super();
			this->function = function;
			this->infos = infos;
			this->gridIndex=gridIndex;
		}
	}

//	protected ArrayList<FunctionInformations> fuctionInformations=new ArrayList<FunctionInformations>();

//	SFSurfaceMeshGeometry() {
//		super();
	}

//	SFSurfaceMeshGeometry(SFPrimitive primitive) {
//		super(primitive);
	}

//	/**
//	 * 
//	 * @param block a Primitive Block to which this Surface Function is Assigned
//	 * @param function
//	 * @throws SFException when this SFQuadsSurfaceGeometry Primitive does not make use of the block
//	 */
//	void setFunction(SFPrimitiveBlock block, SFSurfaceFunction function,SFPrimitiveBlock info) throws SFException {
//		int blockIndex=-1;
//		for (int i = 0; i < getPrimitive().getGridsCount(); i++) {
//			if(getPrimitive().getBlock(i)==block){
//				blockIndex=i;
			}
		}
		}
//		if(blockIndex==-1){	
//			throw new SFException("Cannot assign a function to a QuadsSurfaceGeometry on the Primitive Block '"+block+"' because this QuadsSurfaceGeometry primitive does not use " +
//				"the Primitive Block '"+block+"'");
		}
			}
//			fuctionInformations.add(new FunctionInformations(function, infos,gridIndices));
		}
	}

//	/**
//	 * Set up the function to be used on TexCoord Evaluation
//	 * @param function
//	 * @throws SFException when this SFQuadsSurfaceGeometry cannot use TexCoord 
//	 */
//	void setTexCoordGeometry(SFSurfaceFunction function) throws SFException {
//		setFunction(SFPrimitiveBlock.TXO, function, SFPrimitiveBlock.TXO);
	}

//	void setMainGeometryFunction(SFSurfaceFunction function) {

//		ArrayList<Integer> blocksSizes=new ArrayList<Integer>();
//		for (int gridIndex = 0; gridIndex < getPrimitive().getGridsCount(); gridIndex++) {
//			int size=getPrimitive().getGrid(gridIndex).getN();
//			if(!blocksSizes.contains(size)){
//				blocksSizes.add(size);
			}
		}

//		for (int k = 0; k < blocksSizes.size(); k++) {

//			int n=blocksSizes.get(k);

//			ArrayList<Integer> gridIndices=new ArrayList<Integer>();
//			ArrayList<SFPrimitiveBlock> infosArray=new ArrayList<SFPrimitiveBlock>();
//			for (int gridIndex = 0; gridIndex < getPrimitive().getGridsCount(); gridIndex++) {
//				if(getPrimitive().getGrid(gridIndex).getN()==n){

//					SFPrimitiveBlock block=getPrimitive().getBlock(gridIndex);

//					if(block==SFPrimitiveBlock.POSITION || block==SFPrimitiveBlock.NORMAL
//							|| block==SFPrimitiveBlock.DU || block==SFPrimitiveBlock.DV){
//						gridIndices.add(gridIndex);
//						infosArray.add(block);
					}
				}
			}
//			SFPrimitiveBlock[] infos=infosArray.toArray(new SFPrimitiveBlock[infosArray.size()]);
//			int[] gridIndex=new int[gridIndices.size()];
//			for (int i = 0; i < gridIndex.length; i++) {
//				gridIndex[i]=gridIndices.get(i);
			}

//			fuctionInformations.add(new FunctionInformations(function, infos,gridIndex));

		}
	}

//	ArrayList<FunctionInformations> getFuctionInformations() {
//		return fuctionInformations;
	}

//	static final float eps=0.01f;

	static SFVertex3f getPosition(SFSurfaceFunction function,float u,float v){
//		return new SFVertex3f(function.getX(u, v),function.getY(u, v),function.getZ(u, v));
	}

	static void getPosition(SFSurfaceFunction function,float u,float v,SFVertex3f write){
//		write.set3f(function.getX(u, v),function.getY(u, v),function.getZ(u, v));
	}

	static SFVertex3f getDu(SFSurfaceFunction function,float u,float v){
//		SFVertex3f p1=getPosition(function,u-eps, v);
//		SFVertex3f p2=getPosition(function,u+eps, v);
//		p2.subtract3f(p1);
//		p2.mult(1.0f/(2*eps));
//		return p2;
	}

	static SFVertex3f getDv(SFSurfaceFunction function,float u,float v){
//		SFVertex3f p1=getPosition(function,u, v-eps);
//		SFVertex3f p2=getPosition(function,u, v+eps);
//		p2.subtract3f(p1);
//		p2.mult(1.0f/(2*eps));
//		return p2;
	}

	static SFVertex3f getNormal(SFSurfaceFunction function,float u,float v){
//		SFVertex3f normal=getDu(function,u, v).cross(getDv(function,u, v));
//		normal.normalize3f();
//		return normal;
	}

	static void getPN(SFSurfaceFunction function,float u,float v,SFVertex3f P, SFVertex3f N){
//		P.set3f(function.getX(u, v),function.getY(u, v),function.getZ(u, v));
//		SFVertex3f p1=getPosition(function,u+eps, v);
//		SFVertex3f p2=getPosition(function,u, v+eps);
//		p2.subtract3f(P);
//		p2.mult(1.0f/(eps));
//		p1.subtract3f(P);
//		if(p1.dot(p1)==0){
//			p1=getPosition(function,u+eps, v+eps);
//			p1.subtract3f(getPosition(function,u, v+eps));
		}
//		p1.mult(1.0f/(eps));
//		N.set(p1.cross(p2));
//		N.normalize3f();
//		//System.err.println("p1 "+p1+" "+p2+" "+N+" ("+u+","+v+")");
	}

	static void getPDuDv(SFSurfaceFunction function,float u,float v,SFVertex3f P, SFVertex3f Du, SFVertex3f Dv){
//		P.set3f(function.getX(u, v),function.getY(u, v),function.getZ(u, v));
//		SFVertex3f p1=getPosition(function,u+eps, v);
//		SFVertex3f p2=getPosition(function,u, v+eps);
//		p2.subtract3f(P);
//		//p2.mult(1.0f/(eps));
//		p1.subtract3f(P);
//		if(p1.dot(p1)==0){
//			p1=getPosition(function,u+eps, v+eps);
//			p1.subtract3f(getPosition(function,u, v+eps));
		}
//		//p1.mult(1.0f/(eps));
//		p1.normalize3f();
//		p2.normalize3f();
//		Du.set(p1);
//		Dv.set(p2);
	}

	
//	void update() {
//		super.update();

//		if(getArray()==null)
//			return;

//		for (int i = 0; i < fuctionInformations.size(); i++) {
//			FunctionInformations fInfo=fuctionInformations.get(i);
//			SFArray<SFValuenf> parameters=fInfo.parametersArray;
//			SFIndexRange range=fInfo.parametersIndexRange;

//			SFPrimitiveBlock[] infos=fInfo.infos;

//			if(infos.length==2 && infos[0]==SFPrimitiveBlock.POSITION && infos[1]==SFPrimitiveBlock.NORMAL){
//				//case 1 : normal and position
//				updateParametrizedModelPN(fInfo.function,valuesPositions, parameters, range,getArray(),fInfo.gridIndex);
			}
//				//case 3 : normal and position
//				updateParametrizedModelPDuDv(fInfo.function,valuesPositions, parameters, range,getArray(),fInfo.gridIndex);
			}
//				//case 3 : normal and position
//				updateParametrizedModelDuDv(fInfo.function,valuesPositions, parameters, range,getArray(),fInfo.gridIndex);
			}
//				//general
//				for (int j = 0; j < infos.length; j++) {
//					updateParametrizedModel(fInfo.function,valuesPositions[j], parameters, range, infos[j],getArray().getPrimitiveData(fInfo.gridIndex[j]));
				}
			}

		}

	}

//	static void updateParametrizedModelPN(SFSurfaceFunction function,int[] position,
//			SFArray<SFValuenf> parameters, SFIndexRange range,
//			SFPrimitiveArray array, int[] gridIndex) {
//		SFVertex2f uv=new SFVertex2f(0, 0);
//		SFVertex3f P=new SFVertex3f(0, 0, 0);
//		SFVertex3f N=new SFVertex3f(0, 0, 0);
//		SFArray<SFValuenf> primitiveDataP=array.getPrimitiveData(gridIndex[0]);
//		SFArray<SFValuenf> primitiveDataN=array.getPrimitiveData(gridIndex[1]);
//		int Pposition=position[gridIndex[0]];
//		int Nposition=position[gridIndex[1]];
//		for (int i = 0; i < range.getSize(); i++) {
//			parameters.getElement(range.getPosition()+i, uv);
//			getPN(function, uv.getX(), uv.getY(), P, N);
//			System.err.println("u,v " +uv.getX()+" "+ uv.getY()+" "+Arrays.toString(P.getV())+" "+Arrays.toString(N.getV()));
//			primitiveDataP.setElement(Pposition+i, P);
//			primitiveDataN.setElement(Nposition+i, N);
		}
	}

//	static void updateParametrizedModelPDuDv(SFSurfaceFunction function,int[] position,
//			SFArray<SFValuenf> parameters, SFIndexRange range,
//			SFPrimitiveArray array, int[] gridIndex) {
//		SFVertex2f uv=new SFVertex2f(0, 0);
//		SFVertex3f P=new SFVertex3f(0, 0, 0);
//		SFVertex3f Du=new SFVertex3f(0, 0, 0);
//		SFVertex3f Dv=new SFVertex3f(0, 0, 0);
//		SFArray<SFValuenf> primitiveDataP=array.getPrimitiveData(gridIndex[0]);
//		SFArray<SFValuenf> primitiveDataDu=array.getPrimitiveData(gridIndex[1]);
//		SFArray<SFValuenf> primitiveDataDv=array.getPrimitiveData(gridIndex[2]);
//		int Pposition=position[gridIndex[0]];
//		int Duposition=position[gridIndex[1]];
//		int Dvposition=position[gridIndex[2]];
//		for (int i = 0; i < range.getSize(); i++) {
//			parameters.getElement(range.getPosition()+i, uv);
//			getPDuDv(function, uv.getX(), uv.getY(), P, Du, Dv);
//			primitiveDataP.setElement(Pposition+i, P);
//			primitiveDataDu.setElement(Duposition+i, Du);
//			primitiveDataDv.setElement(Dvposition+i, Dv);
		}
	}

//	static void updateParametrizedModelDuDv(SFSurfaceFunction function,int[] position,
//			SFArray<SFValuenf> parameters, SFIndexRange range,
//			SFPrimitiveArray array, int[] gridIndex) {
//		SFVertex2f uv=new SFVertex2f(0, 0);
//		SFVertex3f P=new SFVertex3f(0, 0, 0);
//		SFVertex3f Du=new SFVertex3f(0, 0, 0);
//		SFVertex3f Dv=new SFVertex3f(0, 0, 0);
//		SFArray<SFValuenf> primitiveDataDu=array.getPrimitiveData(gridIndex[0]);
//		SFArray<SFValuenf> primitiveDataDv=array.getPrimitiveData(gridIndex[1]);
//		int Duposition=position[gridIndex[0]];
//		int Dvposition=position[gridIndex[1]];
//		for (int i = 0; i < range.getSize(); i++) {
//			parameters.getElement(range.getPosition()+i, uv);
//			getPDuDv(function, uv.getX(), uv.getY(), P, Du, Dv);
//			primitiveDataDu.setElement(Duposition+i, Du);
//			primitiveDataDv.setElement(Dvposition+i, Dv);
		}
	}

//	static void updateParametrizedModel(SFSurfaceFunction function,int position,
//			SFArray<SFValuenf> parameters, SFIndexRange range,
//			SFPrimitiveBlock block, SFArray<SFValuenf> primitiveData) {
//		SFVertex2f uv=new SFVertex2f(0, 0);
//		SFValuenf value=primitiveData.generateSample();
//		switch (block) {
//			case NORMAL:
//					for (int i = 0; i < range.getSize(); i++) {
//						parameters.getElement(range.getPosition()+i, uv);
//						value.set(getNormal(function,uv.getX(), uv.getY()));
//						primitiveData.setElement(position+i, value);
					}
//				break;
//			case DU:
//					for (int i = 0; i < range.getSize(); i++) {
//						parameters.getElement(range.getPosition()+i, uv);
//						value.set(getDu(function,uv.getX(), uv.getY()));
//						primitiveData.setElement(position+i, value);
					}
//				break;
//			case DV:
//				for (int i = 0; i < range.getSize(); i++) {
//					parameters.getElement(range.getPosition()+i, uv);
//					value.set(getDv(function,uv.getX(), uv.getY()));
//					primitiveData.setElement(position+i, value);
				}
//			break;

//			default:
//				for (int i = 0; i < range.getSize(); i++) {
//					parameters.getElement(range.getPosition()+i, uv);
//					value.set(getPosition(function,uv.getX(), uv.getY()));
//					primitiveData.setElement(position+i, value);
				}
//				break;
		}
	}

}
;
}
#endif
