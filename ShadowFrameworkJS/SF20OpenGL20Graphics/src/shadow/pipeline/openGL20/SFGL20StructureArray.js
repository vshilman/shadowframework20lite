
function SFGL20StructureArray(){
}

SFGL20StructureArray.prototype = {

	assignValues:function(writing, reading){
		 SFValuenf[]  writingValues = writing.getValues();
		 SFValuenf[]  readingValues = reading.getValues();
	if(writingValues.length!=readingValues.length)			throw new SFArrayElementException(writing, "Different Structure Size");//Warning: Not well Identified 
		for ( int  i  =  0 ; i   < readingValues.length ; i++ ){
	//if(readingValues[i].getSize()!=writingValues[i].getSize());//Warning: Not well Identified 
		throw ,new ,SFArrayElementException(writing, "Different Value Size");
	//}
		writingValues[i].set(readingValues[i].get());
	}
	},

	generateGenericElement:function(){
		return ,new ,SFStructureData(structure);
	},

	getPipelineStructure:function(){
		return ,structure;
	},

	getParameterValue:function(index, parametersIndex, element){
		 SFValuenf  value   = findValue(index, parametersIndex, element);
		element.set(value.get());
	},

	setParameterValue:function(index, parametersIndex, element){
		 SFValuenf  value   = findValue(index, parametersIndex, element);
		value.set(element.get());
	}

};