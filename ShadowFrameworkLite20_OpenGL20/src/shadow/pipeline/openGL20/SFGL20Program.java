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

import java.util.ArrayList;

import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.SFProgramModule;
import shadow.pipeline.java.SFPrimitiveProgramAssociation;
import shadow.pipeline.parameters.SFPipelineRegister;

/* GTL: Geometry-Texturing-Lighting
 */
public class SFGL20Program extends SFGL20AbstractProgram {

	private ArrayList<SFPrimitiveProgramAssociation> vertexShader=new ArrayList<SFPrimitiveProgramAssociation>();
	protected SFProgramModule transforms;
	protected SFPrimitive primitive;
	
	public void clearVertexShader() {
		vertexShader.clear();
	}
	
	public void addToVertexShader(SFProgramComponent component,
			SFPipelineRegister register) {
		vertexShader.add(new SFPrimitiveProgramAssociation(register,component));
	}

	public String loadVertexShaderText() {
		return getShaderText(vertexShader,true);
	}

	public void write() {
		System.err.println("Vertex Program");
		for (int i=0; i < vertexShader.size(); i++) {
			System.err
					.println("\t\tvertexShader.get(i) " + vertexShader.get(i));
		}
		super.write();
	}

	@Override
	public void setPrimitive(SFPrimitive primitive) {
		this.primitive=primitive;
		for (int i=0; i<primitive.getComponents().length; i++) {
			SFProgramComponent pr=primitive.getComponents()[i];
			//this thing is so annoying... we will fix it
			SFPipelineRegister outputRegister=primitive.getBlocks()[i].getRegister();
			addToVertexShader(pr,outputRegister);
		}
	}
	
	public void addToVertexShader(SFProgramModule module){
		for (int i = 0; i < module.getComponents().length; i++) {
			addToVertexShader(module.getComponents()[i],null);	
		}
	}

	@Override
	public void setTransform(SFProgramModule transform) {
		addToVertexShader(transform);
		transforms=transform;
	}
	
	@Override
	public SFPrimitive getPrimitive() {
		return primitive;
	}
	
	@Override
	public SFProgramModule getTransforms() {
		return transforms;
	}
}
