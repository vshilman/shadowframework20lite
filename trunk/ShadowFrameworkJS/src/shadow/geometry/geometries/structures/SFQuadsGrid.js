



function SFQuadsGrid(){
	this.closeU=false;
	this.closeV=false;
}

SFQuadsGrid.prototype["isCloseU"]=function(){
	return this.closeU;
};

SFQuadsGrid.prototype["isCloseV"]=function(){
	return this.closeV;
};

SFQuadsGrid.prototype["getvSplits"]=function(){
	return this.vSplits;
};

SFQuadsGrid.prototype["getuSplits"]=function(){
	return this.uSplits;
};


SFQuadsGrid.prototype["setCloseU"]=function(closeU){
	this.closeU=closeU;
};

SFQuadsGrid.prototype["setCloseV"]=function(closeV){
	this.closeV=closeV;
};

SFQuadsGrid.prototype["setvSplits"]=function(vSplits){
	this.vSplits=vSplits;
};

SFQuadsGrid.prototype["setuSplits"]=function(uSplits){
	this.uSplits=uSplits;
};

SFQuadsGrid.prototype["getU_splits"]=function(){
	return this.getuSplits();
};

SFQuadsGrid.prototype["getV_splits"]=function(){
	return this.getvSplits();
};

SFQuadsGrid.prototype["setU_splits"]=function(uSplits){
	this.setuSplits(uSplits);
};

SFQuadsGrid.prototype["setV_splits"]=function(vSplits){
	this.setvSplits(vSplits);
};


SFQuadsGrid.prototype["getNu"]=function(){
	return this.getuSplits().length;
};


SFQuadsGrid.prototype["setNu"]=function(nu){
	this.setU_splits(new Array());
	var stepU=1.0/(nu-1.0);
	for (var i = 0; i < nu; i++) {
		this.uSplits[i]=i*stepU;
	}
};


SFQuadsGrid.prototype["getNv"]=function(){
	return this.getvSplits().length;
};

	

SFQuadsGrid.prototype["setNv"]=function(nv){
	this.setV_splits(new Array());
	var stepV=1.0/(nv-1.0);
	for (var i = 0; i < nv; i++) {
		this.vSplits[i]=i*stepV;
	}
};

function SFQuadsGrid_getPartitionedSplitsSize(n1,splitsCount){
	return (splitsCount-1)*n1+1;
}

SFQuadsGrid.prototype["generatePartitionedSplits"]=function(n1,stepn1,vSplits){
	var size=(vSplits.length-1)*n1+1;
	var vs=new Array();
	for(var i=0;i<size;i++){
		var I=Math.floor(i/n1);
		var Ires=i-n1*(I);
		vs[i]=vSplits[I]+(Ires>0?Ires*stepn1*(vSplits[I+1]-vSplits[I]):0);
		
	}
	return vs;
};


