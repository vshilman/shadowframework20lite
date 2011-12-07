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
package shadow.geometry;

import shadow.math.SFVertex3f;
import shadow.system.data.SFDataset;

public abstract class SFSurfaceFunction implements SFDataset{

	private static final float eps=0.01f;
	
	public abstract float getX(float u,float v);
	public abstract float getY(float u,float v);
	public abstract float getZ(float u,float v);
	
	public SFVertex3f getPosition(float u,float v){	
		System.out.println("getting position "+u+" "+v);	
		return new SFVertex3f(getX(u, v),getY(u, v),getZ(u, v));
	}

	public SFVertex3f getDu(float u,float v){
		System.out.println("getting du "+u+" "+v);
		SFVertex3f p1=getPosition(u-eps, v);
		SFVertex3f p2=getPosition(u+eps, v);
		p2.subtract3f(p1);
		p2.mult(1.0f/(2*eps));
		return p2;
	}
	
	public SFVertex3f getDv(float u,float v){
		System.out.println("getting dv "+u+" "+v);
		SFVertex3f p1=getPosition(u, v);
		SFVertex3f p2=getPosition(u, v+eps);
		p2.subtract3f(p1);
		p2.mult(1.0f/eps);
		return p2;
	}
	
	public SFVertex3f getNormal(float u,float v){
		System.out.println("getting normal "+u+" "+v);
		SFVertex3f normal=getDu(u, v).cross(getDv(u, v));
		normal.normalize3f();
		return normal;
	}
}