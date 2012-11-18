
//TODO: wrong folder

//Named parameters Object Viewers version does not have names!!

function SFNamedParametersObject(){
	this.dataObjects=new Array();
}

SFNamedParametersObject.prototype["addObject"]=function(dataObject){
			this.dataObjects.push(dataObject); 
		};

SFNamedParametersObject.prototype["clone"]=function(){
			var namedParametricObject=new SFNamedParametersObject();
			for (var i = 0; i < this.dataObjects.length; i++) {
				namedParametricObject.push(this.dataObjects[i].clone());
			}
			return namedParametricObject;
		};

SFNamedParametersObject.prototype["size"]=function(){
			return this.dataObjects.length;
		};	

//Not required		
//public int getIndexOf(String parameter){
//	return objectNames.indexOf(parameter);
//}

//Not required	
/*public SFDataObject getObject(String parameter){
	try {
		return dataObjects.get(objectNames.indexOf(parameter));
	} catch (Exception e) {
		throw new SFException("SFNamedParametersObject : The parameter named '"+parameter+"' " +
				"could not be found since available parameters are "+objectNames);
	}
}*/

//Not required			
/*public SFWritableDataObject getWritableObject(String parameter){
	try {
		return (SFWritableDataObject)dataObjects.get(objectNames.indexOf(parameter));
	} catch (Exception e) {
		throw new SFException(parameter+" is not a writable data Object");
	}
}*/

//Not required
/*public String getName(int index) throws IndexOutOfBoundsException{
	return this.objectNames.get(index);
}*/
			

SFNamedParametersObject.prototype["getDataObject"]=function(index){
			return this.dataObjects[index];
		};

//Not required
/*public void setParameter(String parameterName,String value){
	SFDataObject dataObject=dataObjects.get(getIndexOf(parameterName));
	if(dataObject instanceof SFCharsetObject){
		((SFCharsetObject)dataObject).setStringValue(value);	
	}
}*/

SFNamedParametersObject.prototype["readFromStream"]=function(stream){
			var n=this.dataObjects.length;

			for (var i = 0; i < n; i++) {
				this.dataObjects[i].readFromStream(stream);
			};
		};	
		

SFNamedParametersObject.prototype["writeOnStream"]=function(stream){
			var n=this.dataObjects.length;
			for (var i = 0; i < n; i++) {
				this.dataObjects[i].writeOnStream(stream);
			};
		};
		
			