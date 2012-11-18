

function SFStructureReferenceDataObject(floatValue){
	this.tableData=new SFLibraryReference();
	this.refIndex=0;
}

inherit(SFStructureReferenceDataObject,SFPrimitiveType);

SFStructureReferenceDataObject.prototype["setTableData"]=function(tableData){
			this.tableData=tableData;
		};

SFStructureReferenceDataObject.prototype["setRefIndex"]=function(refIndex){
			this.refIndex=refIndex;
		};


SFStructureReferenceDataObject.prototype["readFromStream"]=function(stream){
	this.tableData.setReference(stream.readName());
	this.refIndex=stream.readShort();
};


SFStructureReferenceDataObject.prototype["writeOnStream"]=function(stream){
	stream.writeName(this.tableData.getReference());
	stream.writeShort(this.refIndex);
};
	
	
SFStructureReferenceDataObject.prototype["clone"]=function(stream){
	return new SFStructureReferenceDataObject();
};

SFStructureReferenceDataObject.prototype["buildReference"]=function(){
	var reference=new SFStructureReference();
	reference.setMaterialIndex(this.refIndex);
	var dataset=this.tableData.retrieveDataset();
	var array=dataset.getResource();
	reference.setTable(array);
	return reference;
};
