
#ifndef _SFRigidTransform3fArray_h
#define _SFRigidTransform3fArray_h

#include "../math/SFVertex3f.h"
#include "../math/SFTransform3f.h"
#include "../math/SFMatrix3f.h"
#include "../system/SFArray.h"


namespace sf{

class SFRigidTransform3fArray : public SFArray<SFTransform3f>{
	
public:
    
    virtual ~SFRigidTransform3fArray(){}
    
	virtual void apply(int index)=0;
	
	virtual void attach(SFRigidTransform3fArray* sonArray,int sonIndex,int fatherIndex)=0;
	
	virtual void setElementPosition(int index,SFVertex3f* vertex)=0;
	
	virtual void setElementOrientation(int index,SFMatrix3f* matrix)=0;
    
	virtual void getElementPosition(int index,SFVertex3f* vertex)=0;
	
	virtual void getElementOrientation(int index,SFMatrix3f* matrix)=0;
};

}

#endif
