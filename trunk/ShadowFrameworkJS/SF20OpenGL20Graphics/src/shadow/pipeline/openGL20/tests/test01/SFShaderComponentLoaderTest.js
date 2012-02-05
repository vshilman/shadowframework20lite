
function SFShaderComponentLoaderTest(){
}

SFShaderComponentLoaderTest.prototype = {

	main:function(args){
		try{
		SFGL20Pipeline.setup();
		SFProgramComponentLoader.loadComponents(new File("data/pipeline/primitive"));
		System.out.println("Load succesfull");
		 String  tessellator = "BasicTess";
	//String primitiveP="Triangle2";//Warning: Not well Identified 
	//String primitiveN="Triangle";//Warning: Not well Identified 
	//String transformP="BasicP";//Warning: Not well Identified 
	//String transformN="BasicN";//Warning: Not well Identified 
		 String  material = "BasicMat";
		 String  light = "BasicLSPN";
	////SFPipelineRegister registers[]=;//Warning: Not well Identified 
		SFPipelineRegister.getFromName("P"),SFPipelineRegister.getFromName("N");
	//}
	////String[] primitivesP=;//Warning: Not well Identified 
		primitiveP;
	//}
	////String[] primitivesN=;//Warning: Not well Identified 
		primitiveN;
	//}
	////String[][] primitives=;//Warning: Not well Identified 
		primitivesP,primitivesN;
	//}
	////String[] transform=;//Warning: Not well Identified 
		transformP,transformN;
	//}
		 String[] materials={
		material;
	}
		 SFPrimitive  primitive = new  SFPrimitive();
		primitive.addPrimitiveElement(SFPipelineRegister.getFromName("P"), (SFProgramComponent)(SFPipeline.getModule("Triangle2")));
		primitive.addPrimitiveElement(SFPipelineRegister.getFromName("N"), (SFProgramComponent)(SFPipeline.getModule("Triangle")));
		primitive.setAdaptingTessellator((SFProgramComponent)(SFPipeline.getModule("BasicTess")));
		System.out.println("Primitive built");
	//registers,			//primitives, transform			SFProgram program=SFPipeline.getStaticProgram( primitive, materials, light);//Warning: Not well Identified 
		System.out.println("Program Built");
		System.out.println(program);
	}
		catch (FileNotFoundException e){
		e.printStackTrace();
	}
		catch (IOException e){
		e.printStackTrace();
	}
		catch (SFPipelineModuleWrongException e){
		 ArrayList<String>  errors = e.getList();
		for ( int  i  =  0 ; i   < errors.size() ; i++ ){
		System.err.println(errors.get(i));
	}
	}
		catch(SFException moduleException){
		moduleException.printStackTrace();
	}
	}

};