/*
	The Shadow Framework 1.0 Lead Version - a complete framework for Real Time Graphics based on OpenGL 2.0
    Copyright (C) 2010  Alessandro Martinelli  <alessandro.martinelli@unipv.it>

    This file is part of The Shadow Framework.

    The Shadow Framework is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    The Shadow Framework is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Shadow Framework.  If not, see <http://www.gnu.org/licenses/>.
*/

package shadow.math;

public class SFMatrix3f extends SFValuenf{

	@Override
	public SFValuenf cloneValue() {
		return new SFMatrix3f(v[0],v[1],v[2],v[3],v[4],v[5],v[6],v[7],v[8]);
	}
	
	public static void main(String args[]){
		SFMatrix3f m1=SFMatrix3f.getRotationZ(3);

		System.out.println(m1);

		System.out.println(SFMatrix3f.getInverse(m1));
		System.out.println(SFMatrix3f.getTransposed(m1));
		System.out.println(m1.Mult(SFMatrix3f.getInverse(m1)));
	}

	public static SFMatrix3f getRotationZ(float angle){
		SFMatrix3f m=new SFMatrix3f();
		float cos=(float)(Math.cos(angle));
		float sin=(float)(Math.sin(angle));

		m.v[0]=cos;m.v[1]=sin;m.v[2]=0;
		m.v[3]=-sin;m.v[4]=cos;m.v[5]=0;
		m.v[6]=0;m.v[7]=0;m.v[8]=1;

		return m;
	}

	public static SFMatrix3f getRotationY(float angle){
		SFMatrix3f m=new SFMatrix3f();
		float cos=(float)(Math.cos(angle));
		float sin=(float)(Math.sin(angle));

		m.v[0]=cos;m.v[1]=0;m.v[2]=sin;
		m.v[3]=0;m.v[4]=1;m.v[5]=0;
		m.v[6]=-sin;m.v[7]=0;m.v[8]=cos;

		return m;
	}

	public static SFMatrix3f getRotationX(float angle){
		SFMatrix3f m=new SFMatrix3f();
		float cos=(float)(Math.cos(angle));
		float sin=(float)(Math.sin(angle));

		m.v[0]=1;m.v[1]=0;m.v[2]=0;
		m.v[3]=0;m.v[4]=cos;m.v[5]=sin;
		m.v[6]=0;m.v[7]=-sin;m.v[8]=cos;

		return m;
	}

	public static SFMatrix3f getTransposed(SFMatrix3f m){
		SFMatrix3f n=new SFMatrix3f();

		n.v[0]=m.v[0];n.v[3]=m.v[1];n.v[6]=m.v[2];
		n.v[1]=m.v[3];n.v[4]=m.v[4];n.v[7]=m.v[5];
		n.v[2]=m.v[6];n.v[5]=m.v[7];n.v[8]=m.v[8];

		return n;
	}


	public static SFMatrix3f getIdentity(){
		SFMatrix3f n=new SFMatrix3f();

		n.v[0]=1;n.v[3]=0;n.v[6]=0;
		n.v[1]=0;n.v[4]=1;n.v[7]=0;
		n.v[2]=0;n.v[5]=0;n.v[8]=1;

		return n;
	}

	public static SFMatrix3f getAmpl(float AmplX,float AmplY,float AmplZ){
		SFMatrix3f n=new SFMatrix3f();

		n.v[0]=AmplX;n.v[3]=0;n.v[6]=0;
		n.v[1]=0;n.v[4]=AmplY;n.v[7]=0;
		n.v[2]=0;n.v[5]=0;n.v[8]=AmplZ;

		return n;
	}

	public static SFMatrix3f getInverse(SFMatrix3f m){
		SFMatrix3f n=new SFMatrix3f();

		float delta = m.v[0] * ( m.v[4]*m.v[8]-m.v[5]*m.v[7] ) - m.v[1] * ( m.v[3]*m.v[8]-m.v[5]*m.v[6] ) + m.v[2] * ( m.v[3]*m.v[7]-m.v[4]*m.v[6] );

		if(delta!=0)
		{
			delta=1/delta;

			n.v[0]=delta*(m.v[4]*m.v[8]-m.v[5]*m.v[7]);
			n.v[1]=-delta*(m.v[1]*m.v[8]-m.v[2]*m.v[7]);
			n.v[2]=delta*(m.v[1]*m.v[5]-m.v[2]*m.v[4]);
			n.v[3]=-delta*(m.v[3]*m.v[8]-m.v[5]*m.v[6]);
			n.v[4]=delta*(m.v[0]*m.v[8]-m.v[2]*m.v[6]);
			n.v[5]=-delta*(m.v[0]*m.v[5]-m.v[2]*m.v[3]);
			n.v[6]=delta*(m.v[3]*m.v[7]-m.v[4]*m.v[6]);
			n.v[7]=-delta*(m.v[0]*m.v[7]-m.v[1]*m.v[6]);
			n.v[8]=delta*(m.v[0]*m.v[4]-m.v[1]*m.v[3]);
		}

		return n;
	}
	
	public static SFMatrix3f getRotationMatrix(SFVertex4f q){
		SFMatrix3f m=new SFMatrix3f();
		
		m.setA(1 - 2*(q.getY()*q.getY() + q.getZ()*q.getZ()));  
		m.setB(-2*q.getZ()*q.getW()+2*q.getX()*q.getY());
		m.setC(2*q.getW()*q.getY() +2*q.getX()*q.getZ());

		m.setD(2*q.getZ()*q.getW()+2*q.getX()*q.getY());
		m.setE(1 - 2*(q.getX()*q.getX() + q.getZ()*q.getZ()));
		m.setF(2*q.getY()*q.getZ()-2*q.getX()*q.getW());

		m.setG(2*q.getX()*q.getZ()-2*q.getW()*q.getY());
		m.setH(2*q.getY()*q.getY()-2*q.getW()*q.getX());
		m.setI(1 - 2*(q.getX()*q.getX() + q.getY()*q.getY()));
		
		return m;
	}

	public String toString()
	{
		return "GLMatrix3f \n " + v[0]+" "+ v[1]+" "+ v[2]+" \n"+
				v[3]+" "+ v[4]+" "+ v[5]+" \n"+
				v[6]+" "+ v[7]+" "+ v[8]+" \n";
	}


	public SFMatrix3f Mult(SFMatrix3f m)
	{
		SFMatrix3f n=new SFMatrix3f();

		n.v[0]= v[0]*m.v[0] + v[1]*m.v[3] + v[2]*m.v[6];
		n.v[1]= v[0]*m.v[1] + v[1]*m.v[4] + v[2]*m.v[7];
		n.v[2]= v[0]*m.v[2] + v[1]*m.v[5] + v[2]*m.v[8];

		n.v[3]= v[3]*m.v[0] + v[4]*m.v[3] + v[5]*m.v[6];
		n.v[4]= v[3]*m.v[1] + v[4]*m.v[4] + v[5]*m.v[7];
		n.v[5]= v[3]*m.v[2] + v[4]*m.v[5] + v[5]*m.v[8];

		n.v[6]= v[6]*m.v[0] + v[7]*m.v[3] + v[8]*m.v[6];
		n.v[7]= v[6]*m.v[1] + v[7]*m.v[4] + v[8]*m.v[7];
		n.v[8]= v[6]*m.v[2] + v[7]*m.v[5] + v[8]*m.v[8];

		return n;
	}

	public SFVertex3f Mult(SFVertex3f p){
		SFVertex3f n=new SFVertex3f(3);

		n.setX( v[0]*p.getX() + v[1]*p.getY() + v[2]*p.getZ() );
		n.setY( v[3]*p.getX() + v[4]*p.getY() + v[5]*p.getZ() );
		n.setZ( v[6]*p.getX() + v[7]*p.getY() + v[8]*p.getZ() );

		return n;
	}
	
	public void transform(SFVertex3f p){

		float x=(float)p.getX(),y=(float)p.getY(),z=(float)p.getZ();
		
		p.setX( v[0]*x + v[1]*y + v[2]*z);
		p.setY( v[3]*x + v[4]*y + v[5]*z);
		p.setZ( v[6]*x + v[7]*y + v[8]*z);

	}

	/**
	 * @return Returns the a.
	 */
	public float getA() {
		return v[0];
	}
	/**
	 * @param a The a to set.
	 */
	public void setA(float a) {
		v[0] = a;
	}
	/**
	 * @return Returns the b.
	 */
	public float getB() {
		return v[1];
	}
	/**
	 * @param b The b to set.
	 */
	public void setB(float b) {
		v[1] = b;
	}
	/**
	 * @return Returns the c.
	 */
	public float getC() {
		return v[2];
	}
	/**
	 * @param c The c to set.
	 */
	public void setC(float c) {
		v[2] = c;
	}
	/**
	 * @return Returns the d.
	 */
	public float getD() {
		return v[3];
	}
	/**
	 * @param d The d to set.
	 */
	public void setD(float d) {
		v[3] = d;
	}
	/**
	 * @return Returns the e.
	 */
	public float getE() {
		return v[4];
	}
	/**
	 * @param e The e to set.
	 */
	public void setE(float e) {
		v[4] = e;
	}
	/**
	 * @return Returns the f.
	 */
	public float getF() {
		return v[5];
	}
	/**
	 * @param f The f to set.
	 */
	public void setF(float f) {
		v[5] = f;
	}
	/**
	 * @return Returns the g.
	 */
	public float getG() {
		return v[6];
	}
	/**
	 * @param g The g to set.
	 */
	public void setG(float g) {
		v[6] = g;
	}
	/**
	 * @return Returns the h.
	 */
	public float getH() {
		return v[7];
	}
	/**
	 * @param h The h to set.
	 */
	public void setH(float h) {
		v[7] = h;
	}
	/**
	 * @return Returns the i.
	 */
	public float getI() {
		return v[8];
	}
	/**
	 * @param i The i to set.
	 */
	public void setI(float i) {
		v[8] = i;
	}
	
	public SFMatrix3f() {
		super(9);
		
		v[0]=1;
		v[4]=1;
		v[8]=1;
	}

	public SFMatrix3f(float a, float b, float c, float d, float e, float f, float g, float h, float i) {
		super(9);
		v[0] = a;
		v[1] = b;
		v[2] = c;
		v[3] = d;
		v[4] = e;
		v[5] = f;
		v[6] = g;
		v[7] = h;
		v[8] = i;
	}

}