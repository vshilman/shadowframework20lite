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
import java.util.LinkedList;
import java.util.List;

import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.java.SFGL20Function;
import shadow.pipeline.java.SFPrimitiveProgramAssociation;
import shadow.pipeline.parameters.SFParameteri;
import shadow.pipeline.parameters.SFPipelineRegister;
import shadow.system.SFException;

/* GTL: Geometry-Texturing-Lighting
 */
public class SFGL20Program extends SFGL20AbstractProgram {

	private ArrayList<SFPrimitiveProgramAssociation> vertexShader=new ArrayList<SFPrimitiveProgramAssociation>();
	private List<SFParameteri> vset=new LinkedList<SFParameteri>();
	SFPrimitive primitive;

	public void clearVertexShader() {
		vertexShader.clear();
	}
	
	public void addToVertexShader(SFProgramComponent component,
			SFPipelineRegister register) {
		vertexShader.add(new SFPrimitiveProgramAssociation(register,component));
	}

	public String loadVertexShaderText() {
		vset.clear();
		return getShaderText(vertexShader,vset);
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

		addToVertexShader(primitive.getTessellator(),null);

		for (int i=0; i<primitive.getBlocks().length; i++) {
			SFProgramComponent pr=primitive.getComponents()[i];
			SFPipelineRegister outputRegister=primitive.getBlocks()[i].getRegister();
			addToVertexShader(pr,outputRegister);
		}
	}

	private static SFProgramComponent defaultPositionTransform=new SFProgramComponent() {
		{
			try {
				LinkedList<SFParameteri> set=new LinkedList<SFParameteri>();
				set.add(SFPipelineRegister.getFromName("modelview"));
				set.add(SFPipelineRegister.getFromName("P"));
				SFPipelineRegister register=SFPipelineRegister
						.getFromName("position");
				addRegister(register);
				addCodeFunction(new SFGL20Function(register,"modelview*P",
						set));
			} catch (SFException e) {
				e.printStackTrace();
			}
		}
	};

	private static SFProgramComponent defaultNormalTransform=new SFProgramComponent() {
		{
			try {
				LinkedList<SFParameteri> set=new LinkedList<SFParameteri>();
				set.add(SFPipelineRegister.getFromName("vectorsModelview"));
				set.add(SFPipelineRegister.getFromName("N"));
				SFPipelineRegister register=SFPipelineRegister
						.getFromName("normal");
				addRegister(register);
				addCodeFunction(new SFGL20Function(register,"vectorsModelview*N",set));
			} catch (SFException e) {
				e.printStackTrace();
			}
		}
	};
	
	private static SFProgramComponent defaultDuTransform=new SFProgramComponent() {
		{
			try {
				LinkedList<SFParameteri> set=new LinkedList<SFParameteri>();
				set.add(SFPipelineRegister.getFromName("vectorsModelview"));
				set.add(SFPipelineRegister.getFromName("du"));
				SFPipelineRegister register=SFPipelineRegister
						.getFromName("duVector");
				addRegister(register);
				addCodeFunction(new SFGL20Function(register,"vectorsModelview*du",set));
			} catch (SFException e) {
				e.printStackTrace();
			}
		}
	};
	
	private static SFProgramComponent defaultDvTransform=new SFProgramComponent() {
		{
			try {
				LinkedList<SFParameteri> set=new LinkedList<SFParameteri>();
				set.add(SFPipelineRegister.getFromName("vectorsModelview"));
				set.add(SFPipelineRegister.getFromName("dv"));
				SFPipelineRegister register=SFPipelineRegister
						.getFromName("dvVector");
				addRegister(register);
				addCodeFunction(new SFGL20Function(register,"vectorsModelview*dv",set));
			} catch (SFException e) {
				e.printStackTrace();
			}
		}
	};

	private static SFProgramComponent defaultTexCoord=new SFProgramComponent() {
		{
			try {
				LinkedList<SFParameteri> set=new LinkedList<SFParameteri>();
				set.add(SFPipelineRegister.getFromName("Tx0"));
				SFPipelineRegister register=SFPipelineRegister
						.getFromName("texCoord0");
				addRegister(register);
				addCodeFunction(new SFGL20Function(register,"Tx0",set));
			} catch (SFException e) {
				e.printStackTrace();
			}
		}
	};

	private static SFProgramComponent defaultGLPosition=new SFProgramComponent() {
		{
			try {
				LinkedList<SFParameteri> set=new LinkedList<SFParameteri>();
				set.add(SFPipelineRegister.getFromName("position"));
				set.add(SFPipelineRegister.getFromName("projection"));
				SFPipelineRegister glPosition=new SFPipelineRegister(
						SFParameteri.GLOBAL_FLOAT4,"gl_Position",
						SFPipelineRegister.WRITE_ON_TRANSFORM);
				addCodeFunction(new SFGL20Function(glPosition,"projection*position",
						set));
			} catch (SFException e) {
				e.printStackTrace();
			}
		}
	};


	protected void checkComponent() {
		if (!vertexShader.contains(defaultPositionTransform)) {
			try {
				addToVertexShader(defaultPositionTransform,
						SFPipelineRegister.getFromName("position"));
			} catch (SFException e) {
				e.printStackTrace();
			}
		}
		try {
			SFPipelineRegister register=SFPipelineRegister.getFromName("N");
			if (primitive.containRegister(register))
				if (!vertexShader.contains(defaultNormalTransform)) {
					addToVertexShader(defaultNormalTransform,
							SFPipelineRegister.getFromName("normal"));
				}
		} catch (SFException e) {
			e.printStackTrace();
		}
		try {
			SFPipelineRegister register=SFPipelineRegister.getFromName("du");
			if (primitive.containRegister(register))
				if (!vertexShader.contains(defaultDuTransform)) {
					addToVertexShader(defaultDuTransform,
							SFPipelineRegister.getFromName("duVector"));
				}
		} catch (SFException e) {
			e.printStackTrace();
		}
		try {
			SFPipelineRegister register=SFPipelineRegister.getFromName("dv");
			if (primitive.containRegister(register))
				if (!vertexShader.contains(defaultDvTransform)) {
					addToVertexShader(defaultDvTransform,
							SFPipelineRegister.getFromName("dvVector"));
				}
		} catch (SFException e) {
			e.printStackTrace();
		}
		try {
			SFPipelineRegister register=SFPipelineRegister.getFromName("Tx0");
			if (primitive.containRegister(register))
				if (!vertexShader.contains(defaultTexCoord)) {
					addToVertexShader(defaultTexCoord,
							SFPipelineRegister.getFromName("texCoord0"));
				}
		} catch (SFException e) {
			e.printStackTrace();
		}
		if (!vertexShader.contains(defaultGLPosition))
			addToVertexShader(defaultGLPosition,null);
		
		super.checkComponent();
	}

	@Override
	public SFPrimitive getPrimitive() {
		return primitive;
	}


}
