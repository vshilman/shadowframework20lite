//Java to JS on 21/03/2012

function SFStructureStamp(structure){
	var parameters=structure.getAllParameters();
	this.data=new Array();
	this.sum=0;
	for(var i=0;i<parameters.length;i++){
		sfParameteri=parameters[i];
		var type=sfParameteri.getType();
		this.data[i]=static_SFParameteri.getTypeDimension(type);
		this.sum+=data[i];
	}
}


SFStructureStamp.prototype["getData"]=function(){
			return this.data;
		};

SFStructureStamp.prototype["size"]=function(){
			return this.data.length;
		};

SFStructureStamp.prototype["getDimension"]=function(index){
			return this.data[index];
		};

SFStructureStamp.prototype["getSum"]=function(){
			return sum;
		};
