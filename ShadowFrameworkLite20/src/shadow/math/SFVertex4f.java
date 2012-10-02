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

public class SFVertex4f extends SFVertex3f{

	public SFVertex4f(float x, float y, float z, float w) {
		super(4);
		this.set4f(x,y,z,w);
	}
	
	public SFVertex4f(double x, double y, double z, double w) {
		super(4);
		this.set4d(x,y,z,w);
	}

	public SFVertex4f(SFVertex4f q){
		super(4);
		set4f(q.v[0],q.v[1],q.v[2],q.v[3]);
	}

	public SFVertex4f() {
		super(4);
	}
	
	public float getW(){
		return v[3];
	}
	
	public void setW(float w){
		v[3]=w;
	}
	
	@Override
	public SFValuenf cloneValue() {
		return new SFVertex4f(v[0],v[1],v[2],v[3]);
	}

	public void set4d(double x,double y,double z,double w){
		super.set3d(x,y,z);
		this.v[3]=(float)w;
	}
	
	public void set4f(float x,float y,float z,float w){
		super.set3d(x,y,z);
		this.v[3]=w;
	}
	
	public void scale4f(float sx,float sy,float sz,float sw){
		super.scale3f(sx,sy,sz);
		this.v[3]*=sw;
	}
	
	public void set(SFVertex4f vx){
		super.set3f(vx);
		v[3]=vx.v[3];
	}
	
	
	public double dot(SFVertex4f vx){
		return vx.v[0]*v[0]+vx.v[1]*v[1]+vx.v[2]*v[2]+vx.v[3]*v[3];
	}
	
	public static SFVertex4f middle(SFVertex4f A,SFVertex4f B){
		return new SFVertex4f(
				(A.v[0]+B.v[0])*0.5f,(A.v[1]+B.v[1])*0.5f,(A.v[2]+B.v[2])*0.5f,(A.v[3]+B.v[3])*0.5f
		);
	}
	
	public void normalize4f(){
		float lengthRec=1/(float)(Math.sqrt(v[0]*v[0]+v[1]*v[1]+v[2]*v[2]+v[3]*v[3]));
		v[0]*=lengthRec;
		v[1]*=lengthRec;
		v[2]*=lengthRec;
		v[3]*=lengthRec;
	}
	
	public void mult(float a){
		v[0]*=a;
		v[1]*=a;
		v[2]*=a;
		v[3]*=a;
	}

	public void storeOn(float[] f){
		storeOn3f(f);
		f[3]=v[3];
	}

	public void addMult(float a,SFVertex4f vx){
		addMult3f(a,vx);
		v[3]+=vx.v[3]*a;
	}
	
	public String toString(){
		return super.toString()+","+v[3]+"f";
	}
	
	public void setByIndex(int index,float val){
		if(index==3)
			v[3]=val;
		else
			super.setByIndex(index,val);
	}
	
	public float getByIndex(int index){
		if(index==3)
			return v[3];
		else
			return super.getByIndex(index);
	}
	
	
	public void add4f(SFVertex4f q){
		add3f(q);
		v[3]+=q.v[3];
	}
	
	public void subtract4f(SFVertex4f q){
		subtract3f(q);
		v[3]-=q.v[3];
	}

	
	/**Multiplication is performed considering both Vertex4f as quaterinions
	 * @param q
	 */
	public void multQuaternions(SFVertex4f q){
		float w1=v[3]*q.v[3]-v[0]*q.v[0]-v[1]*q.v[1]-v[2]*q.v[2];
		float x1=v[3]*q.v[0]+v[0]*q.v[3]+v[2]*q.v[1]-v[1]*q.v[2];
		float y1=v[3]*q.v[1]+v[1]*q.v[3]+v[0]*q.v[2]-v[2]*q.v[0];
		float z1=v[3]*q.v[2]+v[2]*q.v[3]+v[1]*q.v[0]-v[0]*q.v[1];
		this.v[0]=x1;
		this.v[1]=y1;
		this.v[2]=z1;
		this.v[3]=w1;
	}
	
	public SFVertex3f applayQuaternionRotation(SFVertex3f a){
		SFVertex3f b=new SFVertex3f(a.v[0],a.v[1],a.v[2]);
		
		float x1=(float)(v[1]*a.v[2]-v[2]*a.v[1]);
		float y1=(float)(v[2]*a.v[0]-v[0]*a.v[2]);
		float z1=(float)(v[0]*a.v[1]-v[1]*a.v[0]);
		
		float x2=v[1]*z1-v[2]*y1;
		float y2=v[2]*x1-v[0]*z1;
		float z2=v[0]*y1-v[1]*x1;
		
		float wr=1-v[3];
		
		b.v[0]+=x1+wr*x2;
		b.v[1]+=y1+wr*y2;
		b.v[2]+=z1+wr*z2;
		
		return b;
	}
}
