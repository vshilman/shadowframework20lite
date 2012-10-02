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

public class SFVertex2f extends SFValue1f{

	/*@note package visibility*/
	SFVertex2f(int n) {
		super(n);
	}
	
	public SFVertex2f(float x, float y) {
		super(2);
		v[0]=x;
		v[1]=y;
	}
	
	public SFVertex2f(double x, double y) {
		super(2);
		v[0]=(float)x;
		v[1]=(float)y;
	}
	

	public SFVertex2f(SFVertex2f vertex) {
		super(2);
		set(vertex);
	}
	
	public String toString(){
		return "x:"+v[0]+",y:"+v[1];
	}
	
	public float getY(){
		return v[1];
	}
	
	public void setY(float y){
		v[1]=y;
	}
	
	@Override
	public SFValuenf cloneValue() {
		return new SFVertex2f(v[0],v[1]);
	}
	
	public void set2f(float x,float y){
		this.v[0]=x;
		this.v[1]=y;
	}
	
	public void set2d(double x,double y){
		this.v[0]=(float)x;
		this.v[1]=(float)y;
	}
	
	public void mult2f(float m){
		v[0]*=m;
		v[1]*=m;
	}
	
	public void storeOn2f(float []f){
		storeOn1f(f);
		f[1]=v[1];
	}
	
	public void addMult2f(float a,SFVertex2f vx){
		v[0]+=vx.v[0]*a;
		v[1]+=vx.v[1]*a;
	}
	
	public void scale2f(float sx,float sy){
		v[0]*=sx;
		v[1]*=sy;
	}
	
	public void set2f(SFVertex2f vx){
		v[0]=vx.v[0];
		v[1]=vx.v[1];
	}
	
	public float dot2f(SFVertex2f vx){
		return vx.v[0]*v[0]+vx.v[1]*v[1];
	}
	
	public void normalize2f(){
		float lengthRec=1/getLength();
		v[0]*=lengthRec;
		v[1]*=lengthRec;
	}

	public float getLength() {
		return (float)(Math.sqrt(v[0]*v[0]+v[1]*v[1]));
	}
	
	public static float getDistance(SFVertex2f v1,SFVertex2f v2) {
		float x=v1.getX()-v2.getX();
		float y=v1.getY()-v2.getY();
		return (float)(Math.sqrt(x*x+y*y));
	}
	
	public void add2f(SFVertex2f vx){
		v[0]+=vx.v[0];
		v[1]+=vx.v[1];
	}
	
	public void subtract2f(SFVertex2f vx){
		v[0]-=vx.v[0];
		v[1]-=vx.v[1];
	}
	
	public void setByIndex(int index,float val){
		super.setByIndex(index,val);
		if(index==1)
			v[1]=val;
	}
	
	public float getByIndex(int index){
		if(index==0)
			return v[0];
		return v[1];
	}
	
	public static SFVertex2f middle(SFVertex2f A,SFVertex2f B){
		return new SFVertex2f(
				(A.v[0]+B.v[0])*0.5f,(A.v[1]+B.v[1])*0.5f
		);
	}
	
}
