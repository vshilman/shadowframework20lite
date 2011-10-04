package shadow.pipeline.openGL20;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import shadow.pipeline.SFFunction;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.expression.SFExpressionElement;
import shadow.pipeline.expression.SFExpressionElementInterpreter;
import shadow.pipeline.loader.parser.SFPipelineGridInstance;
import shadow.pipeline.loader.parser.SFPipelineStructureInstance;
import shadow.pipeline.parameters.SFParameteri;
import shadow.pipeline.parameters.SFPipelineRegister;

public class SFShaderGL20Implementor {

	private static String generateShaderParameters(LinkedList<SFParameteri> shaderParametersSet){
		return SFGlobalVSetGL20Implementor.generateShaderParameters(shaderParametersSet);
	} 
	
	public static String getShaderText(SFGL20Program program,ArrayList<SFProgramComponent> list) {
		StringWriter writer=new StringWriter();
		
		for(int i=0;i<list.size();i++){

			SFProgramComponent programComponent=list.get(i);
			Collection<SFPipelineRegister> register=programComponent.getRegisters();
			
			
			LinkedList<SFParameteri> regSet=new LinkedList<SFParameteri>();
			regSet.addAll(register);
			
			writer.write(generateShaderParameters(regSet));
			
			SFParameteri global=program.getOutputRegister(programComponent);
			String globalName="";
			if(global!=null)
				globalName=global.getName();
			Collection<SFPipelineStructureInstance> instances=programComponent.getStructures();
			for (Iterator<SFPipelineStructureInstance> iterator = instances.iterator(); iterator.hasNext();) {
				SFPipelineStructureInstance instance = (SFPipelineStructureInstance) iterator
						.next();
				writer.write(SFGlobalVSetGL20Implementor.generateInstancedStructures(instance,
						program.getRegister(programComponent),globalName));
			}
			
			Collection<SFPipelineGridInstance> gridInstances=programComponent.getGrids();
			for (Iterator<SFPipelineGridInstance> iterator=gridInstances.iterator(); iterator.hasNext();) {
				SFPipelineGridInstance sfPipelineGridInstance= iterator.next();
				writer.write(SFGlobalVSetGL20Implementor.generateInstancedGrids(sfPipelineGridInstance,
						program.getRegister(programComponent),globalName));
			}
			
//			SFPipelineStructureInstance[] instances=list.get(i).getStructures();
//			for (int j = 0; j < instances.length; j++) {
//				SFPipelineStructureInstance instance=instances[j];
//				
//			}
		}
		
		writer.write("uniform mat4 projection;\n");
		writer.write("uniform mat4 modelview;\n");
		writer.write("uniform mat4 vectorsModelview;\n");
		writer.write("\n");
		
		writer.write("\n\nvoid main(void){\n");
		boolean doneTessellation=false;
		for(int i=0;i<list.size();i++){
			//System.err.println(i+" "+list.get(i)+" list.get(i).getShaderCode() " +  list.get(i).getShaderCode());
			
			SFProgramComponent programComponent=list.get(i);
			String writeSuffix=program.getOutputName(programComponent);
			
			Collection<SFPipelineRegister> params=programComponent.getRegisters();
			for (Iterator<SFPipelineRegister> iterator = params.iterator(); iterator.hasNext();) {
				SFPipelineRegister param = (SFPipelineRegister) iterator
						.next();
				if(param instanceof SFPipelineRegister && !doneTessellation){
					SFPipelineRegister reg=(SFPipelineRegister)param;
					if(reg.getName().equalsIgnoreCase("uvw")){
						writer.write("\tfloat u=gl_Vertex.x;\n");
						writer.write("\tfloat v=gl_Vertex.y;\n");
						writer.write("\tfloat w=1.0-gl_Vertex.x-gl_Vertex.y;\n");
					}
					if(reg.getName().equalsIgnoreCase("uv")){
						writer.write("\tfloat u=gl_Vertex.x;\n");
						writer.write("\tfloat v=gl_Vertex.y;\n");
					}
					doneTessellation = true;
				}
			}
			
			Collection<SFFunction> lines=programComponent.getShaderCodeLines();
			for (Iterator<SFFunction> iterator = lines.iterator(); iterator.hasNext();) {
				SFFunction sfFunction = (SFFunction) iterator.next();
				SFParameteri global=program.getOutputRegister(programComponent);//sfFunction.getParameter();
				if(global==null)
					global=sfFunction.getParameter();
				SFExpressionElement function=sfFunction.getFunction();
				if(function.getType()!=SFParameteri.GLOBAL_GENERIC){
					String declaration="";
					if(SFGL20GlobalV.declaredOnWrite(global)){
						String def=SFGL20GlobalV.getType(global.getType());
						declaration=def+" ";
					}
					writer.write("\t"+declaration+sfFunction.getParameter().getName()+"="+
							translateExpression(function,global,programComponent.getParameterSet())+";\n");
				}else{
					writer.write("\tvec3 "+writeSuffix+"="+
							translateExpression(function,global,programComponent.getParameterSet())+";\n");	
				}
			}
			
		}
		writer.write("\n}");
		
		return writer.toString();
	}
	
	public static String translateExpression(SFExpressionElement expression,SFParameteri parameter,
			Collection<SFParameteri> functionParameters){

		SFGL20ExpressionGenerator.setRefParameter(parameter);
		
		HashMap<String, String> renameMap=new HashMap<String, String>();
		
		//System.out.println("Is Parameter WroteByTransfom??"+(((SFPipelineRegister)parameter).getType()==SFPipelineRegister.WROTE_BY_TRANSFORM));
		
		if(parameter instanceof SFPipelineRegister && ((SFPipelineRegister)parameter).getUse()==SFPipelineRegister.WROTE_BY_TRANSFORM){
			
			for (Iterator<SFParameteri> iterator = functionParameters.iterator(); iterator.hasNext();) {
				SFParameteri sfParameteri =  iterator.next();
				renameMap.put(sfParameteri.getName(), parameter.getName()+sfParameteri.getName());
			}
		}
		SFGL20ExpressionGenerator.setRenameMap(renameMap);
		
		SFExpressionElementInterpreter interpreter=SFGL20ExpressionGenerator.getGenerator(parameter);
		expression.traverse(interpreter);
	
		String expr=SFGL20ExpressionGenerator.getFunctionString();
		
		return expr;
	}
	
}