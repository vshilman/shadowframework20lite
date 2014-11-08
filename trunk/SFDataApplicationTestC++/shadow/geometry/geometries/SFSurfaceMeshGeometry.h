#ifndef shadow_geometry_geometries_SFSurfaceMeshGeometry_H_
#define shadow_geometry_geometries_SFSurfaceMeshGeometry_H_


#include "shadow/geometry/geometries/SFMeshGeometry.h"
#include "shadow/geometry/SFSurfacefunction.h"
#include "shadow/math/SFValuenf.h"
#include "shadow/math/SFVertex2f.h"
#include "shadow/math/SFVertex3f.h"
#include "shadow/pipeline/SFIndexRange.h"
#include "shadow/pipeline/SFPrimitive.h"
#include "shadow/pipeline/SFPrimitiveArray.h"
#include "shadow/pipeline/SFPrimitiveBlock.h"
#include "shadow/system/SFArray.h"

namespace sf{

class FunctionInformations{

	public:
		SFSurfaceFunction* function;
		SFPrimitiveBlock* infos;
		int* gridIndex;
		short size;

		SFArray<SFValuenf>* parametersArray;
		SFIndexRange parametersIndexRange;

		FunctionInformations(SFSurfaceFunction* function, SFPrimitiveBlock* infos,int* gridIndex,short size);
};


class SFSurfaceMeshGeometry : public SFMeshGeometry {

protected:
	int* valuesPositions;

	vector<FunctionInformations> fuctionInformations;

  	static const float eps=0.01f;

public:

  	SFSurfaceMeshGeometry();

  	SFSurfaceMeshGeometry(SFPrimitive* primitive);

  	/**
  	 *
  	 * @param block a Primitive Block to which this Surface Function is Assigned
  	 * @param function
  	 * @throws SFException when this SFQuadsSurfaceGeometry Primitive does not make use of the block
  	 */
  	void setFunction(SFPrimitiveBlock block, SFSurfaceFunction* function,SFPrimitiveBlock info);

  	/**
  	 * Set up the function to be used on TexCoord Evaluation
  	 * @param function
  	 * @throws SFException when this SFQuadsSurfaceGeometry cannot use TexCoord
  	 */
  	void setTexCoordGeometry(SFSurfaceFunction* function);

  	void setMainGeometryFunction(SFSurfaceFunction* function);

  	vector<FunctionInformations>* getFuctionInformations();

	static SFVertex3f getPosition(SFSurfaceFunction* function,float u,float v);

	static void getPosition(SFSurfaceFunction* function,float u,float v,SFVertex3f write);

	static SFVertex3f getDu(SFSurfaceFunction* function,float u,float v);

	static SFVertex3f getDv(SFSurfaceFunction* function,float u,float v);

	static SFVertex3f getNormal(SFSurfaceFunction* function,float u,float v);

	static void getPN(SFSurfaceFunction* function,float u,float v,SFVertex3f P, SFVertex3f N);

	static void getPDuDv(SFSurfaceFunction* function,float u,float v,SFVertex3f P, SFVertex3f Du, SFVertex3f Dv);

	
  	void update();

  	static void updateParametrizedModelPN(SFSurfaceFunction* function,int* position,
  			SFArray<SFValuenf>* parameters, SFIndexRange range,
  			SFPrimitiveArray* array, int* gridIndex);

  	static void updateParametrizedModelPDuDv(SFSurfaceFunction* function,int* position,
  			SFArray<SFValuenf>* parameters, SFIndexRange range,
  			SFPrimitiveArray* array, int* gridIndex);

  	static void updateParametrizedModelDuDv(SFSurfaceFunction* function,int* position,
  			SFArray<SFValuenf>* parameters, SFIndexRange range,
  			SFPrimitiveArray* array, int* gridIndex);

  	static void updateParametrizedModel(SFSurfaceFunction* function,int position,
  			SFArray<SFValuenf>* parameters, SFIndexRange* range,
  			SFPrimitiveBlock block, SFArray<SFValuenf>* primitiveData);

};

}
#endif
