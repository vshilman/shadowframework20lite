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
package shadow.geometry.functions;

import shadow.geometry.SFCurve;
import shadow.geometry.SFSurfaceFunction;
import shadow.math.SFVertex3f;

public class SFBicurvedLoftedSurface  extends SFSurfaceFunction{
	
	private SFCurve<SFVertex3f> A;
	private SFCurve<SFVertex3f> B;
	
	private float maxTA=1;
	private float maxTB=1;
	
	public SFBicurvedLoftedSurface() {
		
	}
	
	public SFBicurvedLoftedSurface(SFCurve<SFVertex3f> a, SFCurve<SFVertex3f> b) {
		super();
		A = a;
		B = b;
		maxTA=A.getTMax();
		maxTB=B.getTMax();
	}


	private SFVertex3f tmp=new SFVertex3f();
	
	
	@Override
	public void getPosition(float u, float v, SFVertex3f write) {
		super.getPosition(u, v, write);
	}
	
//	@Override
//	public SFVertex3f getDu(float u, float v) {
//		A.getDevDt(u*maxTA, tmp);
//		SFVertex3f tmp2=new SFVertex3f();
//		B.getDevDt(u*maxTB, tmp2);
//		tmp.mult(1-v);
//		tmp.addMult(v, tmp2);
//		return tmp;
//	}
//	
//	@Override
//	public SFVertex3f getDv(float u, float v) {
//		A.getVertex(u*maxTA, tmp);
//		SFVertex3f tmp2=new SFVertex3f();
//		B.getVertex(u*maxTB, tmp2);
//		tmp.mult(-1);
//		tmp.addMult(1, tmp2);
//		return tmp;
//	}
	
	@Override
	public float getX(float u, float v) {
		A.getVertex(u*maxTA, tmp);
		SFVertex3f tmp2=new SFVertex3f();
		B.getVertex(u*maxTB, tmp2);
		tmp.mult(1-v);
		tmp.addMult(v, tmp2);
		return tmp.getX();
	}
	
	@Override
	public float getY(float u, float v) {
		return tmp.getY();
	}
	
	@Override
	public float getZ(float u, float v) {
		return tmp.getZ();
	}
	
	@Override
	public void init() {
		
	}
	
	public SFCurve<SFVertex3f> getA() {
		return A;
	}

	public void setA(SFCurve<SFVertex3f> a) {
		A = a;
	}

	public SFCurve<SFVertex3f> getB() {
		return B;
	}

	public void setB(SFCurve<SFVertex3f> b) {
		B = b;
	}

	public float getMaxTA() {
		return maxTA;
	}

	public void setMaxTA(float maxTA) {
		this.maxTA = maxTA;
	}

	public float getMaxTB() {
		return maxTB;
	}

	public void setMaxTB(float maxTB) {
		this.maxTB = maxTB;
	}


	
}
