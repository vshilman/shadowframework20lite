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
package shadow.pipeline.openGL20;

import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.SFProgramModule;
import shadow.pipeline.parameters.SFPipelineRegister;

/* GTL: Geometry-Texturing-Lighting
 */
public class SFGL20ImageProgram  extends SFGL20AbstractProgram {

	public void clearVertexShader() {
		//do nothing
	}

	public void addToVertexShader(SFProgramComponent component,
			SFPipelineRegister register) {
		//Do nothing
	}

	public String loadVertexShaderText() {
		String vShader="varying vec2 texCoord0;\n" +
				"varying vec3 normal;\n" +
				"void main(void){\n" +
				"\t gl_Position=gl_Vertex;\n" +
				"\t texCoord0=vec2(0.5,0.5)+gl_Vertex.xy*vec2(0.5,0.5);\n" +
				"\t normal=vec3(0,0,-1);\n" +
				"}\n";
		return vShader;
	}

	@Override
	public void setPrimitive(SFPrimitive primitive) {
		//Do nothing
	}
	
	@Override
	public void setTransform(SFProgramModule transform) {
		// TODO Auto-generated method stub
	}
	
}
