#ifndef shadow_renderer_SFCamera_H_
#define shadow_renderer_SFCamera_H_

#include "SFMatrix3f.h"
#include "SFVertex3f.h"
#include "SFIResource.h"
#include "SFResource.h"
#include "SFViewTransform.h"

namespace sf{
class SFCamera : public SFIResource, public SFViewTransform{

	SFVertex3f F;
	SFVertex3f Dir;
	SFVertex3f Up;
	SFVertex3f Left;

	float leftL;
	float upL;

	float distance;
	float delta;

	bool isPerspective_;

	bool changed;
	float matrix[16];

	SFResource resource;

public:

	SFCamera(SFVertex3f focus, SFVertex3f dir,
			SFVertex3f left, SFVertex3f up, float leftL,
			float upL,float distance);

	
  	SFResource* getResource();

  	SFVertex3f getDir();

  	void setDir(SFVertex3f dir);

  	void setDirLength(float dirLength);

  	void update();

  	float* extractTransform() ;

  	bool isPerspective();

  	void setPerspective(bool isPerspective);

  	SFVertex3f getWorldPosition(SFVertex3f cameraPosition);

  	SFMatrix3f getWorldRotation(SFMatrix3f matrixOrientation);

  	SFVertex3f getF();

  	void setF(SFVertex3f f);

  	SFVertex3f getUp();

  	void setUp(SFVertex3f up);

  	SFVertex3f getLeft();

  	void setLeft(SFVertex3f left);

  	float getLeftL();

  	void setLeftL(float leftL);

  	float getUpL();

  	void setUpL(float upL);

  	float getDistance();

  	void setDistance(float distance);

  	float getDelta();

  	void setDelta(float delta);

  	void setupDimensions(float leftL, float upL);

};

}
#endif
