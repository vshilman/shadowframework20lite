package shadow.pipeline.openGL20;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import shadow.pipeline.SFFunction;
import shadow.pipeline.SFPipelineGridInstance;
import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFPipelineStructureInstance;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.SFStructureData;
import shadow.pipeline.expression.SFExpressionElement;
import shadow.pipeline.expression.SFExpressionElementInterpreter;
import shadow.pipeline.java.SFGL20ExpressionGenerator;
import shadow.pipeline.java.SFGL20Function;
import shadow.pipeline.java.SFGL20GenericProgram;
import shadow.pipeline.java.SFGL20ValuenfArray;
import shadow.pipeline.java.SFPrimitiveProgramAssociation;
import shadow.pipeline.java.SFProgramDataModel;
import shadow.pipeline.parameters.SFParameteri;
import shadow.pipeline.parameters.SFPipelineRegister;
import shadow.system.SFException;

public abstract class SFGL20AbstractProgram extends SFGLSLSet implements SFGL20GenericProgram,
						SFProgramDataModel{

	protected ArrayList<SFPrimitiveProgramAssociation> fragmentShader=new ArrayList<SFPrimitiveProgramAssociation>();
	protected List<SFParameteri> fset=new LinkedList<SFParameteri>();
	protected ArrayList<SFProgramComponent> materials=new ArrayList<SFProgramComponent>();
	protected SFProgramComponent light=new SFProgramComponent();
	protected boolean registeredUniforms=false;

	protected SFGL20UniformData data=new SFGL20UniformData();


	public void clearFragmentShader() {
		fragmentShader.clear();
	}

	public void addToFragmentShader(SFProgramComponent component,
			SFPipelineRegister register) {
		fragmentShader
				.add(new SFPrimitiveProgramAssociation(register,component));
	}

	public String loadFragmentShaderText() {
		fset.clear();
		return getShaderText(fragmentShader,fset);
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
		return SFGL20AbstractProgram.getShaderText(this,list);
	}

	@Override
	public void setLightStep(SFProgramComponent lightStep) throws NullPointerException {
		if(lightStep==null)
			throw new NullPointerException("Cannot Assign a Null LightStep");
		addToFragmentShader(lightStep,null);
		light=lightStep;
	}

	@Override
	public void setMaterial(int index, SFProgramComponent material) {
		addToFragmentShader(material,null);
		materials.add(material);
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
	

	protected static SFProgramComponent defaultFragmentColor=new SFProgramComponent() {
		{
			try {
				LinkedList<SFParameteri> set=new LinkedList<SFParameteri>();
				set.add(SFPipelineRegister.getFromName("fColor"));
				SFPipelineRegister glPosition=new SFPipelineRegister(
						SFParameteri.GLOBAL_FLOAT4,"gl_FragColor",
						SFPipelineRegister.WRITE_ON_LIGHTING);
				addCodeFunction(new SFGL20Function(glPosition,"fColor",set));
			} catch (SFException e) {
				e.printStackTrace();
			}
		}
	};
	
	protected static SFProgramComponent[] fragmentColors=new SFProgramComponent[8];
	
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
						addCodeFunction(new SFGL20Function(glPosition,"fColor"+index,set));
					} catch (SFException e) {
						e.printStackTrace();
					}
				}
			};
		}
	}
	


	protected void checkComponent() {
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
			SFGL20ValuenfArray[] datas,SFPrimitive primitive) {
		this.getData().setIndexedData(indices,datas,primitive);
	}

	@Override
	public void setTransformData(float x, float y, float z, float rx, float ry,
			float rz) {
		this.getData().setTransformData(x,y,z,rx,ry,rz);
	}


	@Override
	public void setTransformData(float[] transform) {
		this.getData().setTransformData(transform);
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
			SFParameteri parameter,SFParameteri parameterExpression, List<SFParameteri> functionParameters) {

		SFGL20ExpressionGenerator.setRefParameter(parameter);

		HashMap<String, String> renameMap=new HashMap<String, String>();

		//System.err.println("parameter "+parameter.getName());
		//System.err.println("Is Parameter WroteByTransfom??"+(((SFPipelineRegister)parameter).getType()==SFPipelineRegister.WROTE_BY_TRANSFORM));

		if (parameterExpression instanceof SFPipelineRegister
				&& ((SFPipelineRegister) parameterExpression).getUse() == SFPipelineRegister.WROTE_BY_TRANSFORM) {

			for (Iterator<SFParameteri> iterator=functionParameters.iterator(); iterator
					.hasNext();) {
				SFParameteri sfParameteri=iterator.next();

				if(!SFGL20GlobalV.requiresDeclaration(sfParameteri)){
					renameMap.put(sfParameteri.getName(),parameterExpression.getName()
							+ sfParameteri.getName());
				}
			}
		}
		SFGL20ExpressionGenerator.setRenameMap(renameMap);

		try {
			SFExpressionElementInterpreter interpreter=SFGL20ExpressionGenerator
					.getGenerator(parameter);
			expression.traverse(interpreter);
		} catch (Exception e) {
			System.err.println(parameter);
			e.printStackTrace();
		}

		String expr=SFGL20ExpressionGenerator.getFunctionString();

		return expr;
	}

	public static String getShaderText(SFGL20AbstractProgram program,
			ArrayList<SFPrimitiveProgramAssociation> list) {
		StringWriter writer=new StringWriter();

		LinkedList<SFPipelineRegister> alreadySetRegisters=new LinkedList<SFPipelineRegister>();
		List<String> alreadyDeclaredElements=new ArrayList<String>();
		List<SFParameteri> alreadyMappedParameters=new LinkedList<SFParameteri>();
		for (int i=0; i < list.size(); i++) {

			SFProgramComponent programComponent=list.get(i).getProgram();
			List<SFPipelineRegister> register=programComponent.getRegisters();

			LinkedList<SFParameteri> regSet=new LinkedList<SFParameteri>();
			for (SFPipelineRegister reg : register) {
				if(!alreadySetRegisters.contains(reg)){
					regSet.add(reg);	
					alreadySetRegisters.add(reg);
				}
			}

			writer.write(SFGL20AbstractProgram.generateShaderParameters(regSet));

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
								getRegister(list,i),globalName,alreadyMappedParameters));
			}

			List<SFPipelineGridInstance> gridInstance=programComponent.getGrid();
			
			for (SFPipelineGridInstance sfPipelineGridInstance : gridInstance) {
				short type=sfPipelineGridInstance.getParameters().get(0).getType();
				if(type==SFParameteri.GLOBAL_GENERIC)
					type=getRegister(list, i).getType();
				writer.write(SFGlobalVSetGL20Implementor
						.generateInstancedGrids(sfPipelineGridInstance,
								type,globalName));
			}

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

				SFPipelineRegister register=getRegister(list,i);
				
				SFParameteri global=sfFunction.getParameter();
				if(global.getName().equalsIgnoreCase("<>"))
					global=register;
				SFExpressionElement function=sfFunction.getFunction();
				if (function.getType() != SFParameteri.GLOBAL_GENERIC) {
					String declaration="";
					String name=global.getName();
					if(register!=null && global!=register){
						name=register.getName()+name;
					}
					if (SFGL20GlobalV.declaredOnWrite(global)) {
						if(!alreadyDeclaredElements.contains(name)){
							String def=SFGlobalVSetGL20Implementor.getDeclarationString(global.getType());
							declaration=def + " ";
							alreadyDeclaredElements.add(name);
						}
					}
					global=getRegister(list,i);// sfFunction.getParameter();
					if (global == null)
						global=sfFunction.getParameter();
					
					if(sfFunction.getParameter().getType()!=SFParameteri.GLOBAL_GENERIC){
						writer.write("\t"
								+ declaration+""
								+ name
								+ "="
								+ SFGL20AbstractProgram.translateExpression(function,
										sfFunction.getParameter(),global,programComponent.getParameterSet())
								+ ";\n");
					}else{
						writer.write("\t"
								+ declaration+""
								+ name
								+ "="
								+ SFGL20AbstractProgram.translateExpression(function,
										global,global,programComponent.getParameterSet())
								+ ";\n");	
					}
				} else {

					String type="vec3";
					if (register.getType() == SFParameteri.GLOBAL_FLOAT2) {
						type="vec2";
					}
					
					if(sfFunction.getParameter().getType()!=SFParameteri.GLOBAL_GENERIC){
						global=sfFunction.getParameter();
					}

					writer.write("\t"
							+ type
							+ " "
							+ writeSuffix
							+ "="
							+ SFGL20AbstractProgram.translateExpression(function,
									global,global,programComponent.getParameterSet())
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