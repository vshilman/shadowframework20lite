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
import shadow.pipeline.SFPipelineStructureInstance;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.pipeline.SFPrimitiveProgramAssociation;
import shadow.pipeline.SFProgram;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.SFStructureData;
import shadow.pipeline.expression.SFExpressionElement;
import shadow.pipeline.expression.SFExpressionElementInterpreter;
import shadow.pipeline.loader.parser.SFPipelineGridInstance;
import shadow.pipeline.parameters.SFParameteri;
import shadow.pipeline.parameters.SFPipelineRegister;
import shadow.system.SFException;

/* GTL: Geometry-Texturing-Lighting
 */
public class SFGL20ImageProgram extends SFGLSLSet implements SFProgramDataModel, SFGL20GenericProgram {

	private ArrayList<SFPrimitiveProgramAssociation> fragmentShader=new ArrayList<SFPrimitiveProgramAssociation>();
	
	private List<SFParameteri> fset=new LinkedList<SFParameteri>();
	
	ArrayList<SFProgramComponent> materials=new ArrayList<SFProgramComponent>();
	SFProgramComponent light=new SFProgramComponent();
	private boolean registeredUniforms=false;


	private SFGL20UniformData data=new SFGL20UniformData();

	public void clearFragmentShader() {
		fragmentShader.clear();
	}

	public void clearVertexShader() {
		//do nothing
	}

	public void addToFragmentShader(SFProgramComponent component,
			SFPipelineRegister register) {
		fragmentShader
				.add(new SFPrimitiveProgramAssociation(register,component));
	}

	public void addToVertexShader(SFProgramComponent component,
			SFPipelineRegister register) {
		//Do nothing
	}

	public String loadFragmentShaderText() {
		fset.clear();
		return getShaderText(fragmentShader,fset);
	}

	public String loadVertexShaderText() {
		String vShader="varying vec2 texCoord0;\n" +
				"void main(void){\n" +
				"\t gl_Position=gl_Vertex;\n" +
				"\t texCoord0=vec2(0.5,0.5)+gl_Vertex.xy*vec2(0.5,0.5);\n" +
				"}\n";
		return vShader;
	}

	public void write() {
		
		System.err.println("Fragment Program");
		for (int i=0; i < fragmentShader.size(); i++) {
			System.err
					.println("\t\tfragmentShader(i) " + fragmentShader.get(i));
		}
	}

	public String getShaderText(ArrayList<SFPrimitiveProgramAssociation> list,
			List<SFParameteri> shaderParametersSet) {
		return SFGL20ImageProgram.getShaderText(this,list);
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
		//Do nothing
	}


	@Override
	public String toString() {
		this.checkComponent();
		String vShader=loadVertexShaderText();
		String fShader=loadFragmentShaderText();
		String toString="---------\nvShader\n---------:\n" + vShader
				+ "---------\nfShader\n---------:\n" + fShader;
		return toString;
	}


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
		
		return register;
	}
	
	private static SFProgramComponent defaultFragmentColor=new SFProgramComponent() {
		{
			try {
				LinkedList<SFParameteri> set=new LinkedList<SFParameteri>();
				set.add(SFPipelineRegister.getFromName("fColor"));
				SFPipelineRegister glPosition=new SFPipelineRegister(
						SFParameteri.GLOBAL_FLOAT4,"gl_FragColor",
						SFPipelineRegister.WRITE_ON_LIGHTING);
				addCodeString(new SFFunction(glPosition,"fColor",set));
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
						addCodeString(new SFFunction(glPosition,"fColor"+index,set));
					} catch (SFException e) {
						e.printStackTrace();
					}
				}
			};
		}
	}

	private void checkComponent() {
		
//		try {
//			SFPipelineRegister register=SFPipelineRegister.getFromName("Tx0");
//			if (primitive.containRegister(register))
//				if (!vertexShader.contains(defaultTexCoord)) {
//					addToVertexShader(defaultTexCoord,
//							SFPipelineRegister.getFromName("texCoord0"));
//				}
//		} catch (SFException e) {
//			e.printStackTrace();
//		}

		
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

	/* (non-Javadoc)
	 * @see shadow.pipeline.openGL20.SFGL20GenericProgram#setData(shadow.pipeline.SFPipelineStructureInstance, shadow.pipeline.SFStructureData)
	 */
	@Override
	public void setData(SFPipelineStructureInstance structure,
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

	public static String getShaderText(SFGL20GenericProgram program,
			ArrayList<SFPrimitiveProgramAssociation> list) {
		StringWriter writer=new StringWriter();

		for (int i=0; i < list.size(); i++) {

			SFProgramComponent programComponent=list.get(i).getProgram();
			List<SFPipelineRegister> register=programComponent.getRegisters();

			LinkedList<SFParameteri> regSet=new LinkedList<SFParameteri>();
			regSet.addAll(register);

			writer.write(SFGL20ImageProgram.generateShaderParameters(regSet));

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
							+ SFGL20ImageProgram.translateExpression(function,
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
							+ SFGL20ImageProgram.translateExpression(function,
									global,programComponent.getParameterSet())
							+ ";\n");
				}
			}

		}
		writer.write("\n}");

		return writer.toString();
	}
	
	@Override
	public SFPrimitive getPrimitive() {
		return null;
	}

	void setData(SFGL20UniformData data) {
		this.data=data;
	}

	SFGL20UniformData getData() {
		return data;
	}

}
