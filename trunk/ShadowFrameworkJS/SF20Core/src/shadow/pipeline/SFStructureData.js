
function SFStructureData(structure, genericsParameters){
		this.structure=structure;
	values=new SFValuenf[structure.size()];//Warning: Not well Identified 
	int index=0;//Warning: Not well Identified 
}
function SFStructureData(structure){
		this.structure=structure;
	values=new SFValuenf[structure.size()];//Warning: Not well Identified 
	int index=0;//Warning: Not well Identified 
}

SFStructureData.prototype = {

	size:function(){
	return values.length;//Warning: Not well Identified 
	},

	getStructure:function(){
		return this.structure;
	},

	getValue:function(index){
		return this.values[index];
	},

	getValues:function(){
		return this.values;
	}

};