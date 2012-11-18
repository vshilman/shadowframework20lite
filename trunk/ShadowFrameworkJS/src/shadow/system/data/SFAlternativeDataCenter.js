



function SFAlternativeDataCenter(){
	this.dataCenter=null;
}

SFAlternativeDataCenter.prototype["setup"]=function(){
	this.dataCenter=SFDataCenter_getDataCenter().getDataCenterImplementation();
	SFDataCenter_setDataCenterImplementation(this);
};
	
SFAlternativeDataCenter.prototype["unset"]=function(){
	if(this.dataCenter!=null){
		SFDataCenter_setDataCenterImplementation(this.dataCenter);
	}
	this.dataCenter=null;
};
	
