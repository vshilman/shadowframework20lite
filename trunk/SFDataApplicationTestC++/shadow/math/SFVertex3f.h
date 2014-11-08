#ifndef SFVERTEX3F_H
#define SFVERTEX3F_H

#include "SFValue.h"
#include <math.h>

namespace sf{

	class SFVertex3f:public SFValue{

        
    protected:
        
		float v[3];

	public:

		SFVertex3f();

		SFVertex3f(double x, double y, double z);

		SFVertex3f(float x, float y, float z);
        
        int getSize();
        
        float* getV();
        
		static float getDistance(SFVertex3f v1,SFVertex3f v2);

		static SFVertex3f* middle(SFVertex3f A,SFVertex3f B);

		void add3f(SFVertex3f vx);

		void addMult3f(float a,SFVertex3f vx);
        
		SFVertex3f* cloneValue();

		SFVertex3f* cross(SFVertex3f vx);

		float dot3f(SFVertex3f vx);

		float getLength();

		float getX();

		float getY();

		float getZ();

		void mult3f(float a);

		void normalize3f();

		void scale3f(float sx,float sy,float sz);

		void set3f(float x,float y,float z);

		void setX(float x);

		void setY(float y);

		void setZ(float z);

		void subtract3f(SFVertex3f vx);
	};

}

#endif // SFVERTEX3F_H
