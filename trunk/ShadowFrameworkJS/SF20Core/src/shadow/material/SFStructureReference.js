
function SFStructureReference(){
}

SFStructureReference.prototype = {

	setValue:function(data){
		table.setElement(refIndex, data);
	},

	getStructure:function(){
		return ,structure;
	},

	getMaterialIndex:function(){
		return ,refIndex;
	},

	setMaterialIndex:function(materialIndex){
		this.refIndex    = materialIndex;
	},

	getTable:function(){
		return ,table;
	},

	setStructureData:function(data){
		table.setElement(refIndex,data);
	},

	getStructureData:function(data){
		table.getElement(refIndex,data);
	}

};