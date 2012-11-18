
function SFGL20StructureArray(structure){
	this.data=new Array();
	this.structure=structure;
}

inherit(SFGL20StructureArray,SFGL20ListData);


SFGL20StructureArray.prototype["assignValues"]=function(writing,reading){
	
	var writingValues=writing.getValues();
	var readingValues=reading.getValues();
	
	for (var i = 0; i < readingValues.length; i++) {
		writingValues[i].setValue(readingValues[i]);
	}
};

SFGL20StructureArray.prototype["generateGenericElement"]=function(){
		return new SFStructureData(this.structure);
};	

SFGL20StructureArray.prototype["getPipelineStructure"]=function(){
		return this.structure;
};	

	
SFGL20StructureArray.prototype["getParameterValue"]=function(index,parametersIndex,element){
		var value = findValue(index, parametersIndex, element);
		element.setValue(value.get());
};	
	
SFGL20StructureArray.prototype["findValue"]=function(index,parametersIndex,element){
		var strData=this.data.get(index);
		
		var value=strData.getValue(parametersIndex);
		
		return value;
};		

SFGL20StructureArray.prototype["setParameterValue"]=function(index,parametersIndex,element){
	
	var value = findValue(index, parametersIndex, element);
	value.set(element.get());
};	
	
	
SFGL20StructureArray.prototype["init"]=function(){
	
};	
		
SFGL20StructureArray.prototype["destroy"]=function(){
	
};	
	