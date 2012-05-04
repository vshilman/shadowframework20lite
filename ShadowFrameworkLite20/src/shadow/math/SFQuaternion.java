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

public class SFQuaternion extends SFVertex4f{

	public SFQuaternion(){
		
	}
	
	public SFQuaternion(SFQuaternion q){
		super(q);
	}
	
	
	public void multTo(SFQuaternion q){
		float w1=v[3]*q.v[3]-v[0]*q.v[0]-v[1]*q.v[1]-v[2]*q.v[2];
		float x1=v[3]*q.v[0]+v[0]*q.v[3]+v[2]*q.v[1]-v[1]*q.v[2];
		float y1=v[3]*q.v[1]+v[1]*q.v[3]+v[0]*q.v[2]-v[2]*q.v[0];
		float z1=v[3]*q.v[2]+v[2]*q.v[3]+v[1]*q.v[0]-v[0]*q.v[1];
		this.v[0]=x1;
		this.v[1]=y1;
		this.v[2]=z1;
		this.v[3]=w1;
	}
	
	
	public SFVertex3f applyRotation(SFVertex3f a){
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
	
	public SFMatrix3f getRotationMatrix(){
		SFMatrix3f m=new SFMatrix3f();
		
		m.setA(1 - 2*(v[1]*v[1] + v[2]*v[2]));   //L'ultimo termine.. y( x ay - y ax ) - z*( z ax - x az )  = (-y*y -z*z)ax  +x*y ay + z*x az 
		m.setB(-2*v[2]*v[3]+2*v[0]*v[1]);
		m.setC(2*v[3]*v[1] +2*v[0]*v[2]);

		m.setD(2*v[2]*v[3]+2*v[0]*v[1]);
		m.setE(1 - 2*(v[0]*v[0] + v[2]*v[2]));
		m.setF(2*v[1]*v[2]-2*v[0]*v[3]);

		m.setG(2*v[0]*v[2]-2*v[3]*v[1]);
		m.setH(2*v[1]*v[1]-2*v[3]*v[0]);
		m.setI(1 - 2*(v[0]*v[0] + v[1]*v[1]));
		
		return m;
	}

}
