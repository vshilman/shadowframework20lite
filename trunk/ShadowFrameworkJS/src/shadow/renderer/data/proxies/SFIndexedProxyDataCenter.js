
function SFIndexedProxyDataCenter(){
	this.allData=new Array();
	this.constants=new Array();
	this.index=0;
	this.size=0;	
}

inherit(SFIndexedProxyDataCenter,SFAlternativeDataCenter);


SFIndexedProxyDataCenter.prototype["setData"]=function(name,index,dataset){


	var dataList = this.allData[name];
		if (dataList === undefined) {
			dataList = new Array();
			this.allData[name]=dataList;
		}
		if (dataList.length == 0) {
			dataList.push(dataset);
		} else {
			var typeName = typeof dataList[0];
			//if ((typeof dataset).compare((typeName))!=0) {
				//throw new RuntimeException("Data is not available!");
			//}
		}
		if (index >= dataList.length) {
			for (var i = dataList.length; i <= index; i++) {
				dataList.push(dataset);
			}
		} else if (dataList.length > 1) {
			dataList[index]= dataset;
		}

		if (this.size <= index)
			this.size = index + 1;	
			
};



SFIndexedProxyDataCenter.prototype["updateSize"]=function(){
	
	for (var key in this.allData) {
		var size = this.allData.get(key).size();
		if (size > this.size) {
			this.size = size;
		}
	}	
};


SFIndexedProxyDataCenter.prototype["getData"]=function(name,index){
	var dataList = this.allData[name];
	//if (dataList == null || dataList.size() == 0) {
	//	throw new RuntimeException("Data Unavailable: " + name + " ");
	//}
	if (index < dataList.length) {
		return dataList[index];
	} else {
		return dataList[dataList.length - 1];
	}
};

SFIndexedProxyDataCenter.prototype["size"]=function(){
	return this.size;
};

SFIndexedProxyDataCenter.prototype["getAllData"]=function(){
	return this.allData;
};

SFIndexedProxyDataCenter.prototype["getIndex"]=function(){
	return this.index;
};

SFIndexedProxyDataCenter.prototype["setIndex"]=function(index){
	this.index=index;
};

SFIndexedProxyDataCenter.prototype["addConstant"]=function(constant){
	this.constants.push(constant);
};

function SFIndexedProxyDataCenter_Listener(cl,listener){
	this.cl=cl;
	this.listener=listener;
}	

SFIndexedProxyDataCenter_Listener.prototype["onDatasetAvailable"]=function(name,dataset){
	if(contains(this.cl.constants,name)){
		SFDataAsset_setUpdateMode(false);
	}
	this.listener.onDatasetAvailable(name, dataset);
	SFDataAsset_setUpdateMode(true);
}

SFIndexedProxyDataCenter.prototype["makeDatasetAvailable"]=function(name,listener){
	if(this.allData[name]==undefined && this.dataCenter!=null){
			this.dataCenter.makeDatasetAvailable(name, new SFIndexedProxyDataCenter_Listener(this,listener));
	}else{
		var dataset =  (this.getData(name, this.index));
		dataset.invalidate();
		listener.onDatasetAvailable(name, dataset);	
	}
};