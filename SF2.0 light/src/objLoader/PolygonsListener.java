/*
	Alessandro Martinelli's Jogl Tutorial
    Copyright (C) 2008  Alessandro Martinelli  <alessandro.martinelli@unipv.it>

    This file is part of Alessandro Martinelli's Jogl Tutorial.

    Alessandro Martinelli's Jogl Tutorial is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Alessandro Martinelli's Jogl Tutorial is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Alessandro Martinelli's Jogl Tutorials.  If not, see <http://www.gnu.org/licenses/>.
 */

package objLoader;


public interface PolygonsListener {

	public void begin();
	public void sendTriangle(Vertex3f A,Vertex3f B,Vertex3f C,
			Vertex3f An,Vertex3f Bn,Vertex3f Cn,
			Vertex3f At,Vertex3f Bt,Vertex3f Ct);
	public void sendQuad(Vertex3f A,Vertex3f B,Vertex3f C,Vertex3f D,
			Vertex3f An,Vertex3f Bn,Vertex3f Cn,Vertex3f Dn,
			Vertex3f At,Vertex3f Bt,Vertex3f Ct,Vertex3f Dt);
	public void end();
}
