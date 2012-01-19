/*
	The Shadow Framework - a complete framework for Real Time Graphics based on OpenGL
    Copyright (C) 2008  Alessandro Martinelli  <alessandro.martinelli@unipv.it>

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

package objLoader;

public class Vertex3f {

	public float x,y,z;
	
	public Vertex3f(Vertex3f v) {
		super();
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
	}
	
	public Vertex3f(double x, double y, double z) {
		super();
		this.x = (float)x;
		this.y = (float)y;
		this.z = (float)z;
	}

	public Vertex3f(float x, float y, float z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vertex3f() {
		super();
	}
	
	public Vertex3f cloneV() {
		return new Vertex3f(x,y,z);
	}

	public void set(double x,double y,double z){
		this.x=(float)x;
		this.y=(float)y;
		this.z=(float)z;
	}
	
	public void set(float x,float y,float z){
		this.x=x;
		this.y=y;
		this.z=z;
	}
	
	public void scale(float sx,float sy,float sz){
		this.x*=sx;
		this.y*=sy;
		this.z*=sz;
	}
	
//	public void set(Vertex2f v){
//		x=v.x;
//		y=v.y;
//		z=0;
//	}
	
	public void set(Vertex3f v){
		x=v.x;
		y=v.y;
		z=v.z;
	}
	
	public double dot(Vertex3f v){
		return v.x*x+v.y*y+v.z*z;
	}
	
	public Vertex3f cross(Vertex3f v){
		return new Vertex3f(y*v.z-z*v.y,
							z*v.x-x*v.z,
							x*v.y-y*v.x);
	}
	
	public static Vertex3f middle(Vertex3f A,Vertex3f B){
		
		return new Vertex3f(
				(A.x+B.x)*0.5f,(A.y+B.y)*0.5f,(A.z+B.z)*0.5f
		);
	}
	
	public void normalize(){
		float lengthRec=1/(float)(Math.sqrt(x*x+y*y+z*z));
		x*=lengthRec;
		y*=lengthRec;
		z*=lengthRec;
	}
	
	public void mult(float a){
		x*=a;
		y*=a;
		z*=a;
	}

	public void storeOn(float []f){
		f[0]=x;
		f[1]=y;
		f[2]=z;
	}

	public void addMult(float a,Vertex3f v){
		x+=v.x*a;
		y+=v.y*a;
		z+=v.z*a;
	}
	
	public void add(Vertex3f v){
		x+=v.x;
		y+=v.y;
		z+=v.z;
	}

	public void subtract(Vertex3f v){
		x-=v.x;
		y-=v.y;
		z-=v.z;
	}
	
	public String toString(){
		return ""+x+"f,"+y+"f,"+z+"f";
	}
	
	public void setByIndex(int index,float val){
		if(index==0)
			x=val;
		else if(index==1)
			y=val;
		else if(index==2)
			z=val;
	}
	
	public double getByIndex(int index){
		if(index==0)
			return x;
		else if(index==1)
			return y;
		
		return z;
	}
}
