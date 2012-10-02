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
package shadow.pipeline;


public interface SFProgram{
	
	public void setPrimitive(SFPrimitive primitive);
	
	//More materials may be assigned. Materials should not ovveride same values.
	public void setTransform(SFProgramModule transform);
	
	//More materials may be assigned. Materials should not ovveride same values.
	public void setMaterial(SFProgramModule material);
	
	public void setLightStep(SFProgramModule lightStep);
	
	public void load();
}