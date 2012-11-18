//Java to JS on 21/03/2012

function SFStructureData(structure){
	
	this.structure=structure;
	this.values=new Array();
	var index=0;
	var parameters=this.structure.getAllParameters();
	
	for(var i=0;i<parameters.length;i++){
		var sfParameteri=parameters[i];
		this.values[i]=SFParameteri_generateValue(sfParameteri);
	}
}


SFStructureData.prototype["size"]=function(){
			return this.values.length;
		};
		
SFStructureData.prototype["getStructure"]=function(){
			return this.structure;
		};
		
SFStructureData.prototype["getValue"]=function(index){
			return this.values[index];
		};
		
SFStructureData.prototype["getValues"]=function(index){
			return this.values;
		};
		