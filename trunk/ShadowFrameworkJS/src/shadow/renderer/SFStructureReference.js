

function SFStructureReference(table,index){
	if(table===undefined)
		return;
	this.tableData=table;
	this.structure = this.tableData.getPipelineStructure();
	this.refIndex=index;
}


SFStructureReference.prototype["setValue"]=function(data){
			this.tableData.setElement(refIndex, data);
		};

SFStructureReference.prototype["getStructure"]=function(){
			return this.structure;
		};

SFStructureReference.prototype["getIndex"]=function(){
			return this.refIndex;
		};

SFStructureReference.prototype["setMaterialIndex"]=function(materialIndex){
	this.refIndex=materialIndex;
};


SFStructureReference.prototype["setRefIndex"]=function(refIndex){
	this.refIndex = refIndex;
};	

SFStructureReference.prototype["getTable"]=function(){
	return this.tableData;
};	

SFStructureReference.prototype["setTable"]=function(table){
	this.tableData=table;
};		

SFStructureReference.prototype["setStructureData"]=function(data){
	this.tableData.setElement(this.refIndex,data); 
};	

SFStructureReference.prototype["getStructureData"]=function(data){
	this.tableData.getElement(this.refIndex,data); 
};	
