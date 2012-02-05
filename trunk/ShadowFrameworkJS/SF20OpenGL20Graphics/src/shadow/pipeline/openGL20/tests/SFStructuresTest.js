
function SFStructuresTest(){
}

SFStructuresTest.prototype = {

	main:function(args){
		try{
		SFGL20Pipeline.setup();
		SFProgramComponentLoader.loadComponents(new File("data/pipeline/primitive"));
	//Material			SFPipelineStructure materialStructure=SFPipeline.getStructure("Mat01");//Warning: Not well Identified 
		 List<SFParameteri>  parameters = new  ArrayList<SFParameteri>();
		parameters.add(new SFParameter("mat01",SFParameteri.GLOBAL_FLOAT3));
		parameters.add(new SFParameter("mat02",SFParameteri.GLOBAL_FLOAT3));
		 SFPipelineStructureInstance  materialStructureInstance = new  SFPipelineStructureInstance(materialStructure,parameters);
		materialData  = SFPipeline.getSfPipelineMemory().generateStructureData(materialStructureInstance);
		materialReference  = new .SFStructureReference(materialData);
		 SFStructureData  mat = new  SFStructureData(materialStructureInstance);
	((SFVertex3f)mat.getValue(0)).set3f(1, 0, 0);//Warning: Not well Identified 
	((SFVertex3f)mat.getValue(1)).set3f(0.1f, 0.3f, 0.1f);//Warning: Not well Identified 
		try{
		materialReference.setStructureData(mat);
	}
		catch (SFArrayElementException e){
		e.printStackTrace();
	}
	//Testing...			SFStructureData matTest=new SFStructureData(materialStructureInstance);//Warning: Not well Identified 
		try{
		materialReference.getStructureData(matTest);
	}
		catch (SFArrayElementException e){
		e.printStackTrace();
	}
	System.out.println(((SFVertex3f)matTest.getValue(0)).toString());//Warning: Not well Identified 
	System.out.println(((SFVertex3f)matTest.getValue(1)).toString());//Warning: Not well Identified 
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
	}

};