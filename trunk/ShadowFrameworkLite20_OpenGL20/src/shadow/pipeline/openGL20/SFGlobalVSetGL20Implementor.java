package shadow.pipeline.openGL20;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import shadow.pipeline.SFPipelineGridInstance;
import shadow.pipeline.SFPipelineStructure;
import shadow.pipeline.SFPipelineStructureInstance;
import shadow.pipeline.parameters.SFParameteri;
import shadow.pipeline.parameters.SFPipelineRegister;

public class SFGlobalVSetGL20Implementor{
	
	private static HashMap<Short, String> declarations=new HashMap<Short, String>();
	static{
		declarations.put(SFParameteri.GLOBAL_FLOAT, "float");
		declarations.put(SFParameteri.GLOBAL_FLOAT2, "vec2");
		declarations.put(SFParameteri.GLOBAL_FLOAT3, "vec3");
		declarations.put(SFParameteri.GLOBAL_FLOAT4, "vec4");
		declarations.put(SFParameteri.GLOBAL_MATRIX4, "matrix4f");
		declarations.put(SFParameteri.GLOBAL_TEXTURE, "sampler2D");
	}
	
	public static String generateShaderParameters(List<SFParameteri> set) {
		String parameters="";
		Iterator<SFParameteri> list=set.iterator();
		while(list.hasNext()){
			SFParameteri pr=list.next();
			if(SFGL20GlobalV.requiresDeclaration(pr)){
				String name=pr.getName();
				if(pr instanceof SFPipelineRegister)
					name=SFGL20GlobalV.getRegisterName((SFPipelineRegister)pr);
				String declaration=SFGL20GlobalV.getModifiers(pr)+" "+SFGlobalVSetGL20Implementor.getDeclarationString(pr.getType())+" "+
				name+";\n";
				
				parameters+=declaration;
			}	
		}
		return parameters;
	}

	public static String getDeclarationString(short param_) {
		return declarations.get(param_);
	}	
	
	public static String generateInstancedStructures(SFPipelineStructureInstance instance,
			SFParameteri functionParameter,String suffix,List<SFParameteri> parameters){
		
		SFPipelineStructure structure=instance.getStructure();
		Iterator<SFParameteri> data=instance.getParameters().iterator();
		
		String res="\n";
		List<SFParameteri> sfParameters=structure.getAllParameters();
		
		for (Iterator<SFParameteri> iterator = sfParameters.iterator(); iterator.hasNext();) {
			SFParameteri sfParameteri = (SFParameteri) iterator.next();
			boolean found=false;
			for (SFParameteri parameter : parameters) {
				if(parameter.getName().equalsIgnoreCase(sfParameteri.getName())){
					found=true;
				}
			}
			if(!found){
				short param_=sfParameteri.getType();//Should never be GLOBAL_UNIDENTIFIED!!
	//			if(param_==SFParameteri.GLOBAL_UNIDENTIFIED){
	//				if(param_==SFParameteri.GLOBAL_UNIDENTIFIED){//Still
	//					param_=functionParameter.getType();
	//				}
	//			}
				res+="uniform "+getDeclarationString(param_)+" "+suffix+data.next().getName()+";\n";
				parameters.add(sfParameteri);
			}
			
		}
		
		return res;
	}

	public static String generateInstancedGrids(SFPipelineGridInstance instance,short type,String suffix){
		
		Iterator<SFParameteri> data=instance.getParameters().iterator();
		
		String res="\n";
		
		for (; data.hasNext();) {
			res+="uniform "+getDeclarationString(type)+" "+suffix+data.next().getName()+";\n";
		}
		
		return res;
	}

}