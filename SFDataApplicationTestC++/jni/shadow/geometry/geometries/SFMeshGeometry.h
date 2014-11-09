/*
 * SFMeshGeometry.h
 *
 *  Created on: 24/set/2013
 *      Author: Alessandro
 */

#ifndef SFMESHGEOMETRY_H_
#define SFMESHGEOMETRY_H_

#include "shadow/system/SFGraphicsResource.h"
#include "shadow/system/SFValuesArray.h"
#include "shadow/geometry/SFGeometry.h"
#include "shadow/pipeline/SFMesh.h"
#include "shadow/pipeline/SFPipeline.h"

namespace sf {

class SFMeshGeometry :public SFGeometry{

	SFPrimitive* primitive;
	SFMesh mesh ;
	int compiledIndex;
	SFResource resource;

public:
	SFMeshGeometry();

	SFMeshGeometry(SFPrimitive* primitive);

	SFMeshGeometry(SFMesh mesh);

	virtual ~SFMeshGeometry();

	SFResource* getResource();

	void setPrimitive(SFPrimitive* primitive);

	SFPrimitive* getPrimitive();

	void drawGeometry(int lod);

	void compile();

	void update();

	void decompile();

	void init();

	void destroy();

	int getCompiledIndex();

	SFMesh getMesh();

	int getFirstElement();

	int getElementsCount();

	void setFirstElement(int firstElement);

	int getLastElement();

	void setLastElement(int lastElement);

	SFPrimitiveArray* getArray();


};

} /* namespace sf */
#endif /* SFMESHGEOMETRY_H_ */
