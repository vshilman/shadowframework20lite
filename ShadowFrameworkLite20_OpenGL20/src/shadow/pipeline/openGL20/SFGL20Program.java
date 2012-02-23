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

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import javax.media.opengl.GL2;

import shadow.math.SFValuenf;
import shadow.pipeline.SFFunction;
import shadow.pipeline.SFPipelineGridInstance;
import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFPipelineStructureInstance;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.pipeline.SFPrimitiveProgramAssociation;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.SFStructureData;
import shadow.pipeline.expression.SFExpressionElement;
import shadow.pipeline.expression.SFExpressionElementInterpreter;
import shadow.pipeline.parameters.SFParameteri;
import shadow.pipeline.parameters.SFPipelineRegister;
import shadow.system.SFException;

/* GTL: Geometry-Texturing-Lighting
 */
public class SFGL20Program extends SFGLSLSet implements SFGL20GenericProgram,
		SFProgramDataModel {

	private ArrayList<SFPrimitiveProgramAssociation> vertexShader=new ArrayList<SFPrimitiveProgramAssociation>();
	private ArrayList<SFPrimitiveProgramAssociation> fragmentShader=new ArrayList<SFPrimitiveProgramAssociation>();
	private List<SFParameteri> vset=new LinkedList<SFParameteri>();
	private List<SFParameteri> fset=new LinkedList<SFParameteri>();
	SFPrimitive primitive;
	private ArrayList<SFProgramComponent> materials=new ArrayList<SFProgramComponent>();
	private SFProgramComponent light=new SFProgramComponent();
	private boolean registeredUniforms=false;

	// private HashMap<ArrayList<SFProgramComponent>,
	// ArrayList<SFPipelineRegister>> registers=new
	// HashMap<ArrayList<SFProgramComponent>, ArrayList<SFPipelineRegister>>();
	// private HashMap<SFProgramComponent, String> outputName=new
	// HashMap<SFProgramComponent, String>();
	// private HashMap<SFProgramComponent, SFPipelineRegister> registers=new
	// HashMap<SFProgramComponent, SFPipelineRegister>();

	private SFGL20UniformData data=new SFGL20UniformData();

	public void clearFragmentShader() {
		fragmentShader.clear();
	}

	public void clearVertexShader() {
		vertexShader.clear();
	}

	public void addToFragmentShader(SFProgramComponent component,
			SFPipelineRegister register) {
		fragmentShader
				.add(new SFPrimitiveProgramAssociation(register,component));
	}

	public void addToVertexShader(SFProgramComponent component,
			SFPipelineRegister register) {
		vertexShader.add(new SFPrimitiveProgramAssociation(register,component));
	}

	public String loadFragmentShaderText() {
		fset.clear();
		return getShaderText(fragmentShader,fset);
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
		System.err.println("Fragment Program");
		for (int i=0; i < fragmentShader.size(); i++) {
			System.err
					.println("\t\tfragmentShader(i) " + fragmentShader.get(i));
		}
	}

	public String getShaderText(ArrayList<SFPrimitiveProgramAssociation> list,
			List<SFParameteri> shaderParametersSet) {
		return SFGL20Program.getShaderText(this,list);
	}

	public int getUniformCode(GL2 gl, SFParameteri uniform) {
		return gl.glGetUniformLocationARB(this.getProgram(),uniform.getName());
	}

	@Override
	public void setLightStep(SFProgramComponent lightStep) {
		addToFragmentShader(lightStep,null);
		light=lightStep;
	}

	@Override
	public void setMaterial(int index, SFProgramComponent material) {
		addToFragmentShader(material,null);
		materials.add(material);
	}

	@Override
	public void setPrimitive(SFPrimitive primitive) {
		// TODO Auto-generated method stub

		this.primitive=primitive;

		addToVertexShader(primitive.getTessellator(),null);

		List<Entry<SFPipelineRegister, SFProgramComponent>> wroteRegisters=primitive
				.getPrimitiveMap();
		// Map<SFPipelineRegister, SFProgramComponent>
		// parameters=primitive.getPrimitiveMap();
		Iterator<Entry<SFPipelineRegister, SFProgramComponent>> iterator=wroteRegisters
				.iterator();
		for (int i=0; iterator.hasNext(); i++) {
			Entry<SFPipelineRegister, SFProgramComponent> entry=iterator.next();
			SFProgramComponent pr=entry.getValue();
			SFPipelineRegister outputRegister=entry.getKey();

			// outputName == registerName, no need to make it more complex
			// registers.put(pr,outputRegister);
			addToVertexShader(pr,outputRegister);
		}
	}

	/*
	 * @Override public void setPrimitive(SFPipelineRegister outputRegister, int
	 * i, SFProgramComponent primitive) { outputName.put(primitive,
	 * outputRegister.getName()+i); registers.put(primitive,outputRegister);
	 * addToVertexShader(primitive); }
	 */

	/*
	 * @Override public void setTransforms(SFPipelineRegister
	 * outPipelineRegister,SFProgramComponent transform) {
	 * outputName.put(transform, outPipelineRegister.getName());
	 * registers.put(transform,outPipelineRegister);
	 * addToVertexShader(transform); }
	 */

	@Override
	public String toString() {
		this.checkComponent();
		String vShader=loadVertexShaderText();
		String fShader=loadFragmentShaderText();
		String toString="---------\nvShader\n---------:\n" + vShader
				+ "---------\nfShader\n---------:\n" + fShader;
		return toString;
	}

	// public ArrayList<SFProgramComponent> getVertexShader() {
	// return vertexShader;
	// }
	// public ArrayList<SFProgramComponent> getFragmentShader() {
	// return fragmentShader;
	// }
	//

	// public SFPipelineRegister getOutputRegister(SFProgramComponent component)
	// {
	// SFPipelineRegister register=registers.get(component);
	// return register;
	// }
	//
	// public String getOutputName(SFProgramComponent component) {
	// String suffix=outputName.get(component);
	// if(suffix==null)
	// suffix="";
	// return suffix;
	// }

	// public SFPipelineRegister getRegister(SFProgramComponent component) {
	// SFPipelineRegister register=registers.get(component);
	// if(register==null){
	// try {
	// register=SFPipelineRegister.getFromName("<>");
	// } catch (SFException e) {
	// //register=null; //
	// }
	// }
	//
	// return register;
	// }

	public static String getRegisterName(
			ArrayList<SFPrimitiveProgramAssociation> list, int i) {
		SFPipelineRegister register=list.get(i).getRegister();
		String suffix="";
		if (register != null)
			suffix=register.getName();
		return suffix;
	}

	public static SFPipelineRegister getRegister(
			ArrayList<SFPrimitiveProgramAssociation> list, int i) {
		SFPipelineRegister register=list.get(i).getRegister();
		// if(register==null){
		// try {
		// register=SFPipelineRegister.getFromName("<>");
		// } catch (SFException e) {
		// //register=null; //
		// }
		// }
		return register;
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
				addCodeString(new SFGL20Function(register,"modelview*P",
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
				addCodeString(new SFGL20Function(register,"vectorsModelview*N",set));
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
				addCodeString(new SFGL20Function(register,"vectorsModelview*du",set));
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
				addCodeString(new SFGL20Function(register,"vectorsModelview*dv",set));
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
				addCodeString(new SFGL20Function(register,"Tx0",set));
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
				addCodeString(new SFGL20Function(glPosition,"projection*position",
						set));
			} catch (SFException e) {
				e.printStackTrace();
			}
		}
	};

	private static SFProgramComponent defaultFragmentColor=new SFProgramComponent() {
		{
			try {
				LinkedList<SFParameteri> set=new LinkedList<SFParameteri>();
				set.add(SFPipelineRegister.getFromName("fColor"));
				SFPipelineRegister glPosition=new SFPipelineRegister(
						SFParameteri.GLOBAL_FLOAT4,"gl_FragColor",
						SFPipelineRegister.WRITE_ON_LIGHTING);
				addCodeString(new SFGL20Function(glPosition,"fColor",set));
			} catch (SFException e) {
				e.printStackTrace();
			}
		}
	};
	
	private static SFProgramComponent[] fragmentColors=new SFProgramComponent[8];
	
	static{
		for (int i = 0; i < fragmentColors.length; i++) {
			final int index=i;
			fragmentColors[i]=new SFProgramComponent(){
				{
					try {
						LinkedList<SFParameteri> set=new LinkedList<SFParameteri>();
						set.add(SFPipelineRegister.getFromName("fColor"+index));
						SFPipelineRegister glPosition=new SFPipelineRegister(
								SFParameteri.GLOBAL_FLOAT4,"gl_FragData["+index+"]",
								SFPipelineRegister.WRITE_ON_LIGHTING);
						addCodeString(new SFGL20Function(glPosition,"fColor"+index,set));
					} catch (SFException e) {
						e.printStackTrace();
					}
				}
			};
		}
	}

	private void checkComponent() {
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
		
		try {
			SFPipelineRegister register=SFPipelineRegister.getFromName("fColor");
			if (light.getRegisters().contains(register))
				if (!fragmentShader.contains(defaultFragmentColor))
					addToFragmentShader(defaultFragmentColor,null);
		} catch (SFException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < fragmentColors.length; i++) {
			try {
				SFPipelineRegister register=SFPipelineRegister.getFromName("fColor"+i);
				if (light.getRegisters().contains(register))
					if (!fragmentShader.contains(fragmentColors[i]))
						addToFragmentShader(fragmentColors[i],null);
			} catch (SFException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void load() {

		this.checkComponent();

		this.init();
		this.apply();

		if (!registeredUniforms) {
			getData().evaluateUniforms(this);
			registeredUniforms=true;
		}
	}

	@Override
	public void setData(SFPipelineStructure structure,
			SFStructureData data) {
		this.data.setData(structure,data);
	}

	@Override
	public void setIndexedData(SFPrimitiveIndices indices,
			SFGL20ListData<SFValuenf>[] datas, Integer[][] uniforms,
			SFPipelineRegister[] registers) {
		this.getData().setIndexedData(indices,datas,uniforms,registers);
	}

	@Override
	public void setTransformData(float x, float y, float z, float rx, float ry,
			float rz) {
		this.getData().setTransformData(x,y,z,rx,ry,rz);
	}
	
	public void setupProjection(float projection[]){
		this.getData().setupProjetion(projection);
	}

	@Override
	public SFProgramComponent getLightStep() {
		return light;
	}

	@Override
	public SFProgramComponent getMaterial(int index) {
		return materials.get(index);
	}

	@Override
	public int getMaterialsSize() {
		return materials.size();
	}

	@Override
	public SFPrimitive getPrimitive() {
		return primitive;
	}

	public List<SFProgramComponent> getMaterials() {
		return materials;
	}

	public SFProgramComponent getLight() {
		return light;
	}

	static String generateShaderParameters(
			LinkedList<SFParameteri> shaderParametersSet) {
		return SFGlobalVSetGL20Implementor
				.generateShaderParameters(shaderParametersSet);
	}

	public static String translateExpression(SFExpressionElement expression,
			SFParameteri parameter, List<SFParameteri> functionParameters) {

		SFGL20ExpressionGenerator.setRefParameter(parameter);

		HashMap<String, String> renameMap=new HashMap<String, String>();

		//System.err.println("parameter "+parameter.getName());
		//System.err.println("Is Parameter WroteByTransfom??"+(((SFPipelineRegister)parameter).getType()==SFPipelineRegister.WROTE_BY_TRANSFORM));

		if (parameter instanceof SFPipelineRegister
				&& ((SFPipelineRegister) parameter).getUse() == SFPipelineRegister.WROTE_BY_TRANSFORM) {

			for (Iterator<SFParameteri> iterator=functionParameters.iterator(); iterator
					.hasNext();) {
				SFParameteri sfParameteri=iterator.next();
				
				renameMap.put(sfParameteri.getName(),parameter.getName()
						+ sfParameteri.getName());
			}
		}
		SFGL20ExpressionGenerator.setRenameMap(renameMap);

		SFExpressionElementInterpreter interpreter=SFGL20ExpressionGenerator
				.getGenerator(parameter);
		expression.traverse(interpreter);

		String expr=SFGL20ExpressionGenerator.getFunctionString();

		return expr;
	}

	public static String getShaderText(SFGL20Program program,
			ArrayList<SFPrimitiveProgramAssociation> list) {
		StringWriter writer=new StringWriter();

		for (int i=0; i < list.size(); i++) {

			SFProgramComponent programComponent=list.get(i).getProgram();
			List<SFPipelineRegister> register=programComponent.getRegisters();

			LinkedList<SFParameteri> regSet=new LinkedList<SFParameteri>();
			regSet.addAll(register);

			writer.write(SFGL20Program.generateShaderParameters(regSet));

			SFParameteri global=getRegister(list,i);
			String globalName="";
			if (global != null)
				globalName=global.getName();
			List<SFPipelineStructureInstance> instances=programComponent
					.getStructures();
			for (Iterator<SFPipelineStructureInstance> iterator=instances
					.iterator(); iterator.hasNext();) {
				SFPipelineStructureInstance instance=(SFPipelineStructureInstance) iterator
						.next();
				writer.write(SFGlobalVSetGL20Implementor
						.generateInstancedStructures(instance,
								getRegister(list,i),globalName));
			}

			SFPipelineGridInstance gridInstance=programComponent.getGrid();
			if (gridInstance != null)
				writer.write(SFGlobalVSetGL20Implementor
						.generateInstancedGrids(gridInstance,
								getRegister(list,i),globalName));

			// SFPipelineStructureInstance[]
			// instances=list.get(i).getStructures();
			// for (int j = 0; j < instances.length; j++) {
			// SFPipelineStructureInstance instance=instances[j];
			//
			// }
		}

		writer.write("uniform mat4 projection;\n");
		writer.write("uniform mat4 modelview;\n");
		writer.write("uniform mat4 vectorsModelview;\n");
		writer.write("\n");

		writer.write("\n\nvoid main(void){\n");
		boolean doneTessellation=false;
		for (int i=0; i < list.size(); i++) {
			// System.err.println(i+" "+list.get(i)+" list.get(i).getShaderCode() "
			// + list.get(i).getShaderCode());

			SFProgramComponent programComponent=list.get(i).getProgram();
			String writeSuffix=getRegisterName(list,i);

			List<SFPipelineRegister> params=programComponent.getRegisters();
			for (Iterator<SFPipelineRegister> iterator=params.iterator(); iterator
					.hasNext();) {
				SFPipelineRegister param=(SFPipelineRegister) iterator.next();
				if (param instanceof SFPipelineRegister && !doneTessellation) {
					SFPipelineRegister reg=(SFPipelineRegister) param;
					if (reg.getName().equalsIgnoreCase("uvw")) {
						writer.write("\tfloat u=gl_Vertex.x;\n");
						writer.write("\tfloat v=gl_Vertex.y;\n");
						writer.write("\tfloat w=1.0-gl_Vertex.x-gl_Vertex.y;\n");
					}
					if (reg.getName().equalsIgnoreCase("uv")) {
						writer.write("\tfloat u=gl_Vertex.x;\n");
						writer.write("\tfloat v=gl_Vertex.y;\n");
					}
					doneTessellation=true;
				}
			}

			List<SFFunction> lines=programComponent.getShaderCodeLines();
			for (Iterator<SFFunction> iterator=lines.iterator(); iterator
					.hasNext();) {
				SFFunction sfFunction=(SFFunction) iterator.next();

				SFParameteri global=getRegister(list,i);// sfFunction.getParameter();

				if (global == null)
					global=sfFunction.getParameter();
				SFExpressionElement function=sfFunction.getFunction();
				if (function.getType() != SFParameteri.GLOBAL_GENERIC) {
					String declaration="";
					if (SFGL20GlobalV.declaredOnWrite(global)) {
						String def=SFGL20GlobalV.getType(global.getType());
						declaration=def + " ";
					}
					writer.write("\t"
							+ declaration
							+ sfFunction.getParameter().getName()
							+ "="
							+ SFGL20Program.translateExpression(function,
									global,programComponent.getParameterSet())
							+ ";\n");
				} else {
					SFPipelineRegister register=getRegister(list,i);

					String type="vec3";
					if (register.getType() == SFParameteri.GLOBAL_FLOAT2) {
						type="vec2";
					}

					writer.write("\t"
							+ type
							+ " "
							+ writeSuffix
							+ "="
							+ SFGL20Program.translateExpression(function,
									global,programComponent.getParameterSet())
							+ ";\n");
				}
			}

		}
		writer.write("\n}");

		return writer.toString();
	}

	void setData(SFGL20UniformData data) {
		this.data=data;
	}

	SFGL20UniformData getData() {
		return data;
	}

}
