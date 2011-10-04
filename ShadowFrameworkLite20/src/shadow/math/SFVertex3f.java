/*
	The Shadow Framework 1.0 Lead Version - a complete framework for Real Time Graphics based on OpenGL 2.0
    Copyright (C) 2010  Alessandro Martinelli  <alessandro.martinelli@unipvx.it>

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

public class SFVertex3f extends SFVertex2f{

	/*@note package visibility*/
	SFVertex3f(int n) {
		super(n);
	}
	
	public SFVertex3f(SFVertex3f vx) {
		super(3);
		set3f(vx);
	}
	
	public SFVertex3f(double x, double y, double z) {
		super(3);
		set3d(x,y,z);
	}

	public float getZ(){
		return v[2];
	}
	
	public void setZ(float z){
		v[2]=z;
	}
	
	public SFVertex3f(float x, float y, float z) {
		super(3);
		set3d(x,y,z);
	}
	
	public SFVertex3f cloneV() {
		return new SFVertex3f(v[0],v[1],v[2]);
	}
	
	public void set3f(float x,float y,float z){
		v[0]=x;
		v[1]=y;
		v[2]=z;
	}

	public void set3d(double x,double y,double z){
		v[0]=(float)x;
		v[1]=(float)y;
		v[2]=(float)z;
	}
	
	public void scale3f(float sx,float sy,float sz){
		v[0]*=sx;
		v[1]*=sy;
		v[2]*=sz;
	}

	public void scale3d(double sx,double sy,double sz){
		v[0]*=(float)sx;
		v[1]*=(float)sy;
		v[2]*=(float)sz;
	}
	
	public void set3f(SFVertex3f vx){
		set2f(vx);
		v[2]=vx.v[2];
	}
	
	public double dot3f(SFVertex3f vx){
		return vx.v[0]*v[0]+vx.v[1]*v[1]+vx.v[2]*v[2];
	}
	
	public SFVertex3f cross(SFVertex3f vx){
		return new SFVertex3f(v[1]*vx.v[2]-v[2]*vx.v[1],
							v[2]*vx.v[0]-v[0]*vx.v[2],
							v[0]*vx.v[1]-v[1]*vx.v[0]);
	}
	
	public void normalize3f(){
		float lengthRec=1/(float)(Math.sqrt(v[0]*v[0]+v[1]*v[1]+v[2]*v[2]));
		v[0]*=lengthRec;
		v[1]*=lengthRec;
		v[2]*=lengthRec;
	}
	
	public void mult(float a){
		mult2f(a);
		v[2]*=a;
	}

	public void storeOn3f(float []f){
		storeOn2f(f);
		f[2]=v[2];
	}

	public void addMult3f(float a,SFVertex3f vx){
		addMult2f(a,vx);
		v[2]+=vx.v[2]*a;
	}
	
	public void addMult3d(double a,SFVertex3f vx){
		addMult2f((float)a,vx);
		v[2]+=vx.v[2]*a;
	}
	
	public void add3f(SFVertex3f vx){
		add2f(vx);
		v[2]+=vx.v[2];
	}

	public void subtract3f(SFVertex3f vx){
		subtract2f(vx);
		v[2]-=vx.v[2];
	}
	
	public String toString(){
		return super.toString()+",z:"+v[2];
	}
	
	public void setByIndex(int index,float val){
		super.setByIndex(index,val);
		if(index==2)
			v[2]=val;
	}
	
	public float getByIndex(int index){
		if(index==2){
			return v[2];
		}
		return super.getByIndex(index);
	}

	public static SFVertex3f middle(SFVertex3f A,SFVertex3f B){
		return new SFVertex3f(
				(A.v[0]+B.v[0])*0.5f,(A.v[1]+B.v[1])*0.5f,(A.v[2]+B.v[2])*0.5f
		);
	}
	
}
