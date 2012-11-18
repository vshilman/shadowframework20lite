

function SFIndexedProxyDataObject(){
	this.proxyDataCenter=new SFIndexedProxyDataCenter();
}

inherit(SFIndexedProxyDataObject,SFPrimitiveType);

SFIndexedProxyDataObject.prototype["clone"]=function(){
	return new SFIndexedProxyDataObject();
}


SFIndexedProxyDataObject.prototype["readFromStream"]=function(stream){
	
		var mapSize=stream.readInt();
		for (var i = 0; i < mapSize; i++) {
			
			var singleData=new Array();
			var key=stream.readString();
			var size=stream.readInt();
			if(size>0){
				var type=stream.readString();
				for (var j = 0; j < size; j++) {
					var dataset=SFDataCenter_getDataCenter().createDataset(type);
					dataset.getSFDataObject().readFromStream(stream);
					singleData.add(dataset);	
				}
			}
			this.proxyDataCenter.getAllData()[key]= singleData;
		}
		
		this.proxyDataCenter.updateSize();
}

