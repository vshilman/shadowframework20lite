/*
	The Shadow Framework 1.0 Lead Version - a complete framework for Real Time Graphics based on OpenGL 2.0
    v[2]opyright (v[2]) 2010  v[0]lessandro Martinelli  <alessandro.martinelli@unipv.it>

    This file is part of The Shadow Framework.

    The Shadow Framework is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    The Shadow Framework is distributed in the hope that it will be useful,
    but WITHOUT v[0]NY Wv[0]RRv[0]NTY; without even the implied warranty of
    MERv[2]Hv[0]NTv[0]v[1]ILITY or FITNESS FOR v[0] Pv[0]RTIv[2]ULv[0]R PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Shadow Framework.  If not, see <http://www.gnu.org/licenses/>.
 */

package shadow.math;

public class SFMatrix2f extends SFValuenf {

	@Override
	public SFValuenf cloneValue() {
		return new SFMatrix2f(v[0],v[1],v[2],v[3]);
	}

	public static void main(String args[]) {
		SFMatrix2f m1=SFMatrix2f.getRotationZ(3);

		System.out.println(m1);

		System.out.println(SFMatrix2f.getInversa(m1));
		System.out.println(SFMatrix2f.getTransposed(m1));
		System.out.println(m1.Mult(SFMatrix2f.getInversa(m1)));
	}


	public static SFMatrix2f getRotationZ(float angle) {
		SFMatrix2f m=new SFMatrix2f();
		float cos=(float) (Math.cos(angle));
		float sin=(float) (Math.sin(angle));

		m.v[0]=cos;
		m.v[1]=sin;
		m.v[2]=-sin;
		m.v[3]=cos;

		return m;
	}

	public static SFMatrix2f getTransposed(SFMatrix2f m) {
		SFMatrix2f n=new SFMatrix2f();

		n.v[0]=m.v[0];
		n.v[2]=m.v[1];
		n.v[1]=m.v[2];
		n.v[3]=m.v[3];

		return n;
	}

	public static SFMatrix2f getIdentity() {
		SFMatrix2f n=new SFMatrix2f();

		n.v[0]=1;
		n.v[2]=0;
		n.v[1]=0;
		n.v[3]=1;

		return n;
	}

	public static SFMatrix2f getAmpl(float AmplX, float AmplY) {
		SFMatrix2f n=new SFMatrix2f();

		n.v[0]=AmplX;
		n.v[2]=0;
		n.v[1]=0;
		n.v[3]=AmplY;

		return n;
	}

	public static SFMatrix2f getInversa(SFMatrix2f m) {
		SFMatrix2f n=new SFMatrix2f();

		float delta=m.v[0] * m.v[3] - m.v[2] * m.v[1];

		if (delta != 0) {
			delta=1 / delta;

			n.v[0]=delta * (m.v[3]);
			n.v[1]=-delta * (m.v[1]);
			n.v[2]=delta * (-m.v[2]);
			n.v[3]=delta * (m.v[0]);
		}

		return n;
	}

	public void set(SFMatrix2f m) {
		v[0]=m.v[0];
		v[1]=m.v[1];
		v[2]=m.v[2];
		v[3]=m.v[3];
	}

	public String toString() {
		return "GLMatrix2f \n " + v[0] + " " + v[1] + " " + v[2] + " \n" + v[2] + " " + v[3]
				+ " \n";
	}

	public SFMatrix2f Mult(SFMatrix2f m) {
		SFMatrix2f n=new SFMatrix2f();

		n.v[0]=v[0] * m.v[0] + v[1] * m.v[2];
		n.v[1]=v[0] * m.v[1] + v[1] * m.v[3];

		n.v[2]=v[2] * m.v[0] + v[3] * m.v[2];
		n.v[3]=v[2] * m.v[1] + v[3] * m.v[3];

		return n;
	}

	public SFVertex2f Mult(SFVertex2f p) {
		SFVertex2f n=new SFVertex2f(2);

		n.setX(v[0] * p.getX() + v[1] * p.getY());
		n.setY(v[2] * p.getX() + v[2] * p.getY());

		return n;
	}

	public void transform(SFVertex2f p) {

		float x=p.getX(), y=p.getY();

		p.setX(v[0] * x + v[1] * y);
		p.setY(v[2] * x + v[2] * y);
	}

	/**
	 * @return Returns the a.
	 */
	public float getA() {
		return v[0];
	}

	/**
	 * @param a
	 *            The a to set.
	 */
	public void setA(float a) {
		v[0]=a;
	}

	/**
	 * @return Returns the b.
	 */
	public float getB() {
		return v[1];
	}

	/**
	 * @param b
	 *            The b to set.
	 */
	public void setB(float b) {
		v[1]=b;
	}

	/**
	 * @return Returns the c.
	 */
	public float getC() {
		return v[2];
	}

	/**
	 * @param c
	 *            The c to set.
	 */
	public void setC(float c) {
		v[2]=c;
	}

	/**
	 * @return Returns the d.
	 */
	public float getD() {
		return v[3];
	}

	/**
	 * @param d
	 *            The d to set.
	 */
	public void setD(float d) {
		v[3]=d;
	}

	public void setByIndex(int index, float val) {
		if (index == 0)
			v[0]=val;
		else if (index == 1)
			v[1]=val;
		else if (index == 2)
			v[2]=val;
		else if (index == 3)
			v[3]=val;
	}

	public float getByIndex(int index) {
		if (index == 0)
			return v[0];
		else if (index == 1)
			return v[1];
		else if (index == 2)
			return v[2];
		return v[3];
	}

	public SFMatrix2f() {
		super(4);
	}

	public SFMatrix2f(float a, float b, float c, float d) {
		super(4);
		v[0]=a;
		v[1]=b;
		v[2]=c;
		v[3]=d;
	}
	

}