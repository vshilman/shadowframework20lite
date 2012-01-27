
function SFExpressionParser(){
}

SFExpressionParser.prototype = {

	setGenerator:function(generator){
		this.generator=generator;
	},

	getParser:function(){
		return this.parser;
	},

	main:function(args){
	//String operation="u*u*u*A+v*v*v*A";//Warning: Not well Identified 
	//Perfetto				String operation="projection*modelview*A";//Warning: Not well Identified 
	//String operation="a+c:0:1";//Warning: Not well Identified 
	//String operation="(_a+_b)/_c*(_d*_a/(_b+_e))";//Warning: Not well Identified 
	//String operation="NA°NB*F+C";//Warning: Not well Identified 
	//Still no troubles would say				//Adesso devo assolutamente considerare la funzionalità di re-arrange of types.		//Così importante....				//PERFETTO!!				parser.setGenerator(new SFBasicExpressionGenerator());//Warning: Not well Identified 
	SFParameter A=new SFParameter("A",SFParameter.GLOBAL_FLOAT3);//Warning: Not well Identified 
	SFParameter B=new SFParameter("B",SFParameter.GLOBAL_FLOAT3);//Warning: Not well Identified 
	SFParameter C=new SFParameter("C",SFParameter.GLOBAL_FLOAT3);//Warning: Not well Identified 
	SFParameter modelview=new SFParameter("modelview",SFParameter.GLOBAL_MATRIX4);//Warning: Not well Identified 
	SFParameter projection=new SFParameter("projection",SFParameter.GLOBAL_MATRIX4);//Warning: Not well Identified 
	List<SFParameteri> set;
	set.add(A);//Warning: Not well Identified 
	set.add(B);//Warning: Not well Identified 
	set.add(C);//Warning: Not well Identified 
	set.add(projection);//Warning: Not well Identified 
	set.add(modelview);//Warning: Not well Identified 
	SFExpressionElement element;
	element.traverse(interpreter);//Warning: Not well Identified 
	System.out.println();//Warning: Not well Identified 
	element.traverse(interpreter);//Warning: Not well Identified 
	}

};