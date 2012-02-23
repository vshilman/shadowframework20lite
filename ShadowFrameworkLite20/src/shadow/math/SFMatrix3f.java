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

		m.A=cos;m.B=sin;m.C=0;
		m.D=-sin;m.E=cos;m.F=0;
		m.G=0;m.H=0;m.I=1;

		return m;
	}

	public static SFMatrix3f getRotationY(float angle){
		SFMatrix3f m=new SFMatrix3f();
		float cos=(float)(Math.cos(angle));
		float sin=(float)(Math.sin(angle));

		m.A=cos;m.B=0;m.C=sin;
		m.D=0;m.E=1;m.F=0;
		m.G=-sin;m.H=0;m.I=cos;

		return m;
	}

	public static SFMatrix3f getRotationX(float angle){
		SFMatrix3f m=new SFMatrix3f();
		float cos=(float)(Math.cos(angle));
		float sin=(float)(Math.sin(angle));

		m.A=1;m.B=0;m.C=0;
		m.D=0;m.E=cos;m.F=sin;
		m.G=0;m.H=-sin;m.I=cos;

		return m;
	}

	public static SFMatrix3f getTransposed(SFMatrix3f m){
		SFMatrix3f n=new SFMatrix3f();

		n.A=m.A;n.D=m.B;n.G=m.C;
		n.B=m.D;n.E=m.E;n.H=m.F;
		n.C=m.G;n.F=m.H;n.I=m.I;

		return n;
	}


	public static SFMatrix3f getIdentity(){
		SFMatrix3f n=new SFMatrix3f();

		n.A=1;n.D=0;n.G=0;
		n.B=0;n.E=1;n.H=0;
		n.C=0;n.F=0;n.I=1;

		return n;
	}

	public static SFMatrix3f getAmpl(float AmplX,float AmplY,float AmplZ){
		SFMatrix3f n=new SFMatrix3f();

		n.A=AmplX;n.D=0;n.G=0;
		n.B=0;n.E=AmplY;n.H=0;
		n.C=0;n.F=0;n.I=AmplZ;

		return n;
	}

	public static SFMatrix3f getInverse(SFMatrix3f m){
		SFMatrix3f n=new SFMatrix3f();

		float delta = m.A * ( m.E*m.I-m.F*m.H ) - m.B * ( m.D*m.I-m.F*m.G ) + m.C * ( m.D*m.H-m.E*m.G );

		if(delta!=0)
		{
			delta=1/delta;

			n.A=delta*(m.E*m.I-m.F*m.H);
			n.B=-delta*(m.B*m.I-m.C*m.H);
			n.C=delta*(m.B*m.F-m.C*m.E);
			n.D=-delta*(m.D*m.I-m.F*m.G);
			n.E=delta*(m.A*m.I-m.C*m.G);
			n.F=-delta*(m.A*m.F-m.C*m.D);
			n.G=delta*(m.D*m.H-m.E*m.G);
			n.H=-delta*(m.A*m.H-m.B*m.G);
			n.I=delta*(m.A*m.E-m.B*m.D);
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

	public void set(SFMatrix3f m){
		A=m.A;B=m.B;C=m.C;
		D=m.D;E=m.E;F=m.F;
		G=m.G;H=m.H;I=m.I;
	}

	public String toString()
	{
		return "GLMatrix3f \n " + A+" "+ B+" "+ C+" \n"+
								D+" "+ E+" "+ F+" \n"+
								G+" "+ H+" "+ I+" \n";
	}


	public SFMatrix3f Mult(SFMatrix3f m)
	{
		SFMatrix3f n=new SFMatrix3f();

		n.A= A*m.A + B*m.D + C*m.G;
		n.B= A*m.B + B*m.E + C*m.H;
		n.C= A*m.C + B*m.F + C*m.I;

		n.D= D*m.A + E*m.D + F*m.G;
		n.E= D*m.B + E*m.E + F*m.H;
		n.F= D*m.C + E*m.F + F*m.I;

		n.G= G*m.A + H*m.D + I*m.G;
		n.H= G*m.B + H*m.E + I*m.H;
		n.I= G*m.C + H*m.F + I*m.I;

		return n;
	}

	public SFVertex3f Mult(SFVertex3f p){
		SFVertex3f n=new SFVertex3f(3);

		n.setX( A*p.getX() + B*p.getY() + C*p.getZ() );
		n.setY( D*p.getX() + E*p.getY() + F*p.getZ() );
		n.setZ( G*p.getX() + H*p.getY() + I*p.getZ() );

		return n;
	}
	
	public void transform(SFVertex3f p){

		float x=(float)p.getX(),y=(float)p.getY(),z=(float)p.getZ();
		
		p.setX( A*x + B*y + C*z);
		p.setY( D*x + E*y + F*z);
		p.setZ( G*x + H*y + I*z);

	}

	/**
	 * @return Returns the a.
	 */
	public float getA() {
		return A;
	}
	/**
	 * @param a The a to set.
	 */
	public void setA(float a) {
		A = a;
	}
	/**
	 * @return Returns the b.
	 */
	public float getB() {
		return B;
	}
	/**
	 * @param b The b to set.
	 */
	public void setB(float b) {
		B = b;
	}
	/**
	 * @return Returns the c.
	 */
	public float getC() {
		return C;
	}
	/**
	 * @param c The c to set.
	 */
	public void setC(float c) {
		C = c;
	}
	/**
	 * @return Returns the d.
	 */
	public float getD() {
		return D;
	}
	/**
	 * @param d The d to set.
	 */
	public void setD(float d) {
		D = d;
	}
	/**
	 * @return Returns the e.
	 */
	public float getE() {
		return E;
	}
	/**
	 * @param e The e to set.
	 */
	public void setE(float e) {
		E = e;
	}
	/**
	 * @return Returns the f.
	 */
	public float getF() {
		return F;
	}
	/**
	 * @param f The f to set.
	 */
	public void setF(float f) {
		F = f;
	}
	/**
	 * @return Returns the g.
	 */
	public float getG() {
		return G;
	}
	/**
	 * @param g The g to set.
	 */
	public void setG(float g) {
		G = g;
	}
	/**
	 * @return Returns the h.
	 */
	public float getH() {
		return H;
	}
	/**
	 * @param h The h to set.
	 */
	public void setH(float h) {
		H = h;
	}
	/**
	 * @return Returns the i.
	 */
	public float getI() {
		return I;
	}
	/**
	 * @param i The i to set.
	 */
	public void setI(float i) {
		I = i;
	}
	
	public void setByIndex(int index,float val){
		if(index==0)
			A=val;
		else if(index==1)
			B=val;
		else if(index==2)
			C=val;
		else if(index==3)
			D=val;
		else if(index==4)
			E=val;
		else if(index==5)
			F=val;
		else if(index==6)
			G=val;
		else if(index==7)
			H=val;
		else if(index==8)
			I=val;
	}
	
	public float getByIndex(int index){
		if(index==0)
			return A;
		else if(index==1)
			return B;
		else if(index==2)
			return C;
		else if(index==3)
			return D;
		else if(index==4)
			return E;
		else if(index==5)
			return F;
		else if(index==6)
			return G;
		else if(index==7)
			return H;
		
		return I; 
	}
	
	
	private float A,B,C;
	private float D,E,F;
	private float G,H,I;
	
	
	public SFMatrix3f() {
		super(9);
		
		A=1;
		E=1;
		I=1;
	}

	public SFMatrix3f(float a, float b, float c, float d, float e, float f, float g, float h, float i) {
		super(9);
		A = a;
		B = b;
		C = c;
		D = d;
		E = e;
		F = f;
		G = g;
		H = h;
		I = i;
	}

}