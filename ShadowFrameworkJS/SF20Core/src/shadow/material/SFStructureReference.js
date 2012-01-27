
function SFStructureReference(){
}
function SFStructureReference(table){
		this.table=table;
	this.refIndex = table.generateElement();//Warning: Not well Identified 
}

SFStructureReference.prototype = {

	getStructure:function(){
		return this.structure;
	},

	getMaterialIndex:function(){
		return this.refIndex;
	},

	setMaterialIndex:function(materialIndex){
		this.refIndex=materialIndex;
	},

	getTable:function(){
		return this.table;
	}

};