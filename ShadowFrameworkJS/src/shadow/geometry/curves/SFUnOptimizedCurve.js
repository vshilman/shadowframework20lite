

function SFUnOptimizedCurve(){

}


SFUnOptimizedCurve.prototype["get"]=function(ts,vertices,derivatives){
	this.getVerticesV(ts, vertices);
	this.getDevDtV(ts, derivatives);
};

SFUnOptimizedCurve.prototype["get2"]=function(ts,vertices,derivatives,derivatives2){
	this.getVerticesV(ts, vertices);
	this.getDevDtV(ts, derivatives);
	this.getDev2DtV(ts, derivatives2);
};


SFUnOptimizedCurve.prototype["generateTs"]=function(n){
	var ts=new Array();
	var dt=1.0/(n-1);
	for (var i = 0; i < ts.length; i++) {
		ts[i]=dt*i;
	}
	return ts;
};
	

SFUnOptimizedCurve.prototype["getVertices"]=function(read,n){
	this.getVerticesV(this.generateTs(n), read);
};	
	
SFUnOptimizedCurve.prototype["getDevDt"]=function(read,n){
	this.getDevDtV(this.generateTs(n), read);
};	
	
SFUnOptimizedCurve.prototype["getDev2Dt"]=function(read,n){
	this.getDev2DtV(this.generateTs(n), read);
};	
	
SFUnOptimizedCurve.prototype["getVerticesV"]=function(ts,read){
	for (var i = 0; i < read.length; i++) {
		this.getVertex(ts[i], read[i]);
	}
};	
	
SFUnOptimizedCurve.prototype["getDevDtV"]=function(ts,read){
	for (var i = 0; i < read.length; i++) {
		this.getDevDt(ts[i], read[i]);
	}
};	
	
SFUnOptimizedCurve.prototype["getDev2DtV"]=function(ts,read){
	for (var i = 0; i < read.length; i++) {
		this.getDev2Dt(ts[i], read[i]);
	}
};	
	