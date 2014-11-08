#ifndef SFMATRIX3F_H
#define SFMATRIX3F_H

#include "SFValue.h"
#include "SFVertex4f.h"


namespace sf{

class SFMatrix3f:public SFValue{

    float v[9];
    
public:
    SFMatrix3f();

    SFMatrix3f(float a, float b, float c, float d, float e, float f, float g, float h, float i);

    //SFValuenf* cloneValue();
    
    int getSize();
    
    float* getV();


    static SFMatrix3f getRotationZ(float angle);

    static SFMatrix3f getRotationY(float angle);

    static SFMatrix3f getRotationX(float angle);

    static SFMatrix3f getTransposed(SFMatrix3f m);


    static SFMatrix3f getIdentity();

    static SFMatrix3f getScale(float scaleX,float scaleY,float scaleZ);

    static SFMatrix3f getInverse(SFMatrix3f m);

    static SFMatrix3f getRotationMatrix(SFVertex4f q);


    SFMatrix3f MultMatrix(SFMatrix3f m);

    SFVertex3f Mult(SFVertex3f p);

    void transform(SFVertex3f* p);

    /**
     * @return Returns the a.
     */
    float getA();
    /**
     * @param a The a to set.
     */
    void setA(float a);
    /**
     * @return Returns the b.
     */
    float getB();
    /**
     * @param b The b to set.
     */
    void setB(float b);
    /**
     * @return Returns the c.
     */
    float getC();
    /**
     * @param c The c to set.
     */
    void setC(float c);
    /**
     * @return Returns the d.
     */
    float getD();
    /**
     * @param d The d to set.
     */
    void setD(float d) ;
    /**
     * @return Returns the e.
     */
    float getE();
    /**
     * @param e The e to set.
     */
    void setE(float e);
    /**
     * @return Returns the f.
     */
    float getF() ;
    /**
     * @param f The f to set.
     */
    void setF(float f);
    /**
     * @return Returns the g.
     */
    float getG();
    /**
     * @param g The g to set.
     */
    void setG(float g);
    /**
     * @return Returns the h.
     */
    float getH() ;

    void setH(float h);

    float getI();

    void setI(float i);
};

}

#endif // SFMATRIX3F_H
