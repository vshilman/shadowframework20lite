package shadow.pipeline.openGL20;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import shadow.image.SFPipelineTexture;
import shadow.math.SFValuenf;
import shadow.pipeline.SFFunction;
import shadow.pipeline.SFPipelineGraphics.Module;
import shadow.pipeline.SFPipelineGrid;
import shadow.pipeline.SFPipelineStructureInstance;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitiveIndices;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.SFProgramModule;
import shadow.pipeline.SFStructureData;
import shadow.pipeline.expression.SFExpressionElement;
import shadow.pipeline.java.SFGL20ExpressionGenerator;
import shadow.pipeline.java.SFGL20GenericProgram;
import shadow.pipeline.java.SFGL20ValuenfArray;
import shadow.pipeline.java.SFPrimitiveProgramAssociation;
import shadow.pipeline.java.SFProgramDataModel;
import shadow.pipeline.parameters.SFParameteri;
import shadow.pipeline.parameters.SFPipelineRegister;

public abstract class SFGL20AbstractProgram extends SFGLSLSet implements SFGL20GenericProgram,
						SFProgramDataModel{

	protected ArrayList<SFPrimitiveProgramAssociation> fragmentShader=new ArrayList<SFPrimitiveProgramAssociation>();
	protected SFProgramModule materials;
	protected SFProgramModule light;
	protected boolean registeredUniforms=false;

	protected SFGL20UniformData data=new SFGL20UniformData();


	public void clearFragmentShader() {
		fragmentShader.clear();
	}

	public void addToFragmentShader(SFProgramComponent component,
			SFPipelineRegister register) {
		fragmentShader.add(new SFPrimitiveProgramAssociation(register,component));
	}

	public String loadFragmentShaderText() {
		return getShaderText(fragmentShader,false);
	}
	

	public void write() {
		System.err.println("Fragment Program");
		for (int i=0; i < fragmentShader.size(); i++) {
			System.err
					.println("\t\tfragmentShader(i) " + fragmentShader.get(i));
		}
	}

	public String getShaderText(ArrayList<SFPrimitiveProgramAssociation> list,boolean vertex) {
		return SFGL20AbstractProgram.getShaderText(this,list,vertex);
	}
	
	public void addToFragmentShader(SFProgramModule module){
		for (int i = 0; i < module.getComponents().length; i++) {
			addToFragmentShader(module.getComponents()[i],null);	
		}
	}
	
	@Override
	public void setLightStep(SFProgramModule lightStep) {
		addToFragmentShader(lightStep);
		this.light=lightStep;
	}
	
	@Override
	public void setMaterial(SFProgramModule material) {
		addToFragmentShader(material);
		this.materials=material;
	}



	@Override
	public String toString() {
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
	


	@Override
	public void load() {

	//	this.checkComponent();

		this.init();
		this.apply();

		if (!registeredUniforms) {
			getData().evaluateUniforms(this);
			registeredUniforms=true;
		}
	}

	@Override
	public void setData(Module module, int index, SFPipelineTexture texture) {
		this.data.setData(module,index,texture);
	}
	
	@Override
	public void setData(Module module, int index, SFStructureData data) {
		this.data.setData(module,index, data);
	}
	
	@Override
	public void setIndexedData(SFPrimitiveIndices indices,
			SFGL20ValuenfArray[] datas,SFPrimitive primitive) {
		this.getData().setIndexedData(indices,datas,primitive);
	}

	@Override
	public void sendVertex(SFValuenf value) {
		this.getData().sendVertex(value);
	}

	@Override
	public void setTransformData(float[] transform) {
		this.getData().setTransformData(transform);
	}
	
	public void setupProjection(float projection[]){
		this.getData().setupProjetion(projection);
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
				&& ((SFPipelineRegister) parameterExpression).getUse() == SFPipelineRegister.WROTE_BY_PRIMITIVE) {

			for(SFParameteri sfParameteri : functionParameters){
				if(!SFGL20GlobalV.requiresDeclaration(sfParameteri)){
					renameMap.put(sfParameteri.getName(),parameterExpression.getName()
							+ sfParameteri.getName());
				}
			}
		}
		SFGL20ExpressionGenerator.setRenameMap(renameMap);

		try {
			expression.traverse(SFGL20ExpressionGenerator.getGenerator(parameter));
		} catch (Exception e) {
			System.err.println(parameter);
			e.printStackTrace();
		}

		String expr=SFGL20ExpressionGenerator.getFunctionString();

		return expr;
	}

	public static String getShaderText(SFGL20AbstractProgram program,
			ArrayList<SFPrimitiveProgramAssociation> list,boolean vertex) {
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
			
			for (SFPipelineStructureInstance instance : instances) {
				writer.write(SFGlobalVSetGL20Implementor
						.generateInstancedStructures(instance,
								getRegister(list,i),globalName,alreadyMappedParameters));
			}

			List<SFPipelineGrid> gridInstance=programComponent.getGrid();
			
			for (SFPipelineGrid sfPipelineGridInstance : gridInstance) {
				short type=sfPipelineGridInstance.getParameterType(0);
				if(type==SFParameteri.GLOBAL_GENERIC)
					type=getRegister(list, i).getType();//TODO
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

		writer.write("\n");

		writer.write("\n\nvoid main(void){\n");
		boolean doneTessellation=false;
		for (int i=0; i < list.size(); i++) {
			// System.err.println(i+" "+list.get(i)+" list.get(i).getShaderCode() "
			// + list.get(i).getShaderCode());

			SFProgramComponent programComponent=list.get(i).getProgram();
			String writeSuffix=getRegisterName(list,i);

			List<SFPipelineRegister> params=programComponent.getRegisters();
			for (SFPipelineRegister param : params) {
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
			for (SFFunction sfFunction : lines) {
				
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
		
		if(vertex){
			writer.write("\n\tgl_Position=vec4(position);");//this is a big trouble i think
		}
		
		for (SFPrimitiveProgramAssociation prAssoc : list) {
			
			if(prAssoc.getProgram().getRegisters().contains(SFPipelineRegister.getFromName("fColor"))){
				writer.write("\n\tgl_FragColor=fColor;");//this is a big trouble i think
			}
			for (int i = 0; i < 4; i++) {
				if(prAssoc.getProgram().getRegisters().contains(SFPipelineRegister.getFromName("fColor"+i))){
					writer.write("\n\tgl_FragData["+i+"]=fColor"+i+";");//this is a big trouble i think
				}
			}
		}
		
		
		
		//writer.write("\n\t fragmen");//this is a big trouble i think
		writer.write("\n}");

		return writer.toString();
	}
	
	
	
	public SFProgramModule getMaterials() {
		return materials;
	}

	public SFProgramModule getLight() {
		return light;
	}

	public SFProgramModule getTransforms(){
		return null;
	}
	
	public SFPrimitive getPrimitive(){
		return null;
	}

	void setData(SFGL20UniformData data) {
		this.data=data;
	}

	protected SFGL20UniformData getData() {
		return data;
	}
}