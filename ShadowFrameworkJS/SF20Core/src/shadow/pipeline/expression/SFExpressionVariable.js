
function SFExpressionVariable(){
}

SFExpressionVariable.prototype = {

	getParameter:function(set, name){
	//for (Iterator<SFParameteri> iterator = set.iterator();iterator.hasNext(););//Warning: Not well Identified 
		 SFParameteri  sfParameteri   = iterator.next();
		if(sfParameteri.getName().equalsIgnoreCase(name)){
		return ,sfParameteri;
	}
	//}
		return ,null;
	},

	addSubExpression:function(element){
	//Nothing to do;//Warning: Not well Identified 
	},

	evaluateType:function(){
		try{
		 SFPipelineRegister  register = SFPipelineRegister.getFromName(this.getElement());
		setType(register.getType());
	}
		catch (SFException e){
		 SFParameteri  parameter = getParameter(parameters,this.getElement());
		 if ( parameter!=null ){
		setType(parameter.getType());
	}
		else{
		try{
		 Double  f = new  Double(this.getElement());
		this.setElement(""+f);
		setType(SFParameteri.GLOBAL_FLOAT);
	}
		catch (NumberFormatException e1){
		throw ,new ,SFExpressionException("Unknown Parameter "+this.getElement());
	}
	}
	}
	}

};