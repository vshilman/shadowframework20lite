#ifndef shadow_renderer_data_transforms_H_
#define shadow_renderer_data_transforms_H_

#include "shadow/math/SFEulerAngles3f.h"
#include "shadow/math/SFMatrix3f.h"
#include "shadow/renderer/SFTransformResource.h"
#include "shadow/system/data.SFDataAsset.h"
#include "shadow/system/data.SFNamedParametersObject.h"
#include "shadow/system/data.objects.SFFloat.h"
#include "shadow/system/data.objects.SFVertex3fData.h"

namespace sf{
class SFRigidTransformData extends SFDataAsset<SFTransformResource>{

	SFVertex3fData position=new SFVertex3fData();

	SFVertex3fData orientation=new SFVertex3fData();

	SFFloat scale=new SFFloat(1);

	SFRigidTransformData(){
		setup();
	}

	SFRigidTransformData(float x,float y,float z,float scale){
		setup();
		place(x, y, z, scale);
	}

	SFRigidTransformData(float x,float y,float z,float scale,SFMatrix3f orientation){
		setup();
		place(x, y, z, scale,orientation);
	}

	void setup() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("position", position);
		parameters.addObject("orientation", orientation);
		parameters.addObject("scale", scale);
		setData(parameters);
	}

	void place(float x,float y,float z,float scale){
		position.getVertex3f().set3f(x,y,z);
		this->scale.setFloatValue(scale);
	}

	void place(float x,float y,float z,SFMatrix3f orientation){
		position.getVertex3f().set3f(x,y,z);
		SFEulerAngles3f angles=new SFEulerAngles3f();
		angles.setMatrix(orientation);
		this->orientation.getVertex3f().set(angles);
	}

	void place(float x,float y,float z,float scale,SFMatrix3f orientation){
		position.getVertex3f().set3f(x,y,z);
		this->scale.setFloatValue(scale);
		SFEulerAngles3f angles=new SFEulerAngles3f();
		angles.setMatrix(orientation);
		this->orientation.getVertex3f().set(angles);
	}

	
	SFTransformResource buildResource() {
		SFMatrix3f matrix=new SFMatrix3f();
		SFEulerAngles3f angles=new SFEulerAngles3f();
		angles.set(orientation.getVertex3f());
		angles.getMatrix(matrix);
		matrix.mult(scale.getFloatValue());

		SFTransformResource transform=new SFTransformResource();
		transform.setMatrix(matrix);
		transform.setPosition(this->position.getVertex3f());

		return transform;
	}


	
	void updateResource(SFTransformResource resource) {
		// TODO Auto-generated method stub

	}
}
;
}
#endif
