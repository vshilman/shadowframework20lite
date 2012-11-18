//Java to JS on 20/03/2012

//NOTE some List methods have not been inserted
		
function SFDataList(type){
	this.dataObject = new Array();
	this.type = type;
}

SFDataList.prototype["getDataObject"]=function(){
			return this.dataObject;
		};
		
SFDataList.prototype["elementsSize"]=function(){
			return this.dataObject.length;
		};
		
SFDataList.prototype["readFromStream"]=function(stream){
	
			var n = stream.readShort();
			
			for (var i = 0; i < n; i++) {
				this.dataObject.push(this.type.clone());
				this.dataObject[i].readFromStream(stream);
			}
		};
		
SFDataList.prototype["writeOnStream"]=function(stream){
			stream.writeShort(this.dataObject.length);
			for (var i = 0; i < this.dataObject.length; i++) {
				this.dataObject[i].writeOnStream(stream);
			}
		};
		
SFDataList.prototype["sonsObject"]=function(son){
			return this.dataObject[son];
		};
		
SFDataList.prototype["clone"]=function(){
			return new SFDataList(this.type);
		};
		
SFDataList.prototype["add"]=function(index,element){
			this.dataObject.push(index,element);
		};
		
SFDataList.prototype["add"]=function(e){
			return dataObject.push(e);
		};
		
SFDataList.prototype["size"]=function(e){
			return this.dataObject.length;
		};

SFDataList.prototype["get"]=function(index){
			return this.dataObject[index];
		};
