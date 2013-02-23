//Java to JS on 06/02/2012

var Math_PI=3.141592653589793;

function SFValuenf(n){
	this.v=new Array();
	for(var i=0;i<n;i++){
		this.v[i]=0;
	}
}

SFValuenf.prototype["getSize"]=function(){
			return this.v.length;
		};
		
		
SFValuenf.prototype["get"]=function(){
			return this.v;
		};
		

function SFValuenf_middle(A,B){
	var value=new SFValuenf();
	for(var i=0;i<A.v.length;i++){
		value.v[i]=(A.v[i]+B.v[i])*0.5;
	}
	return value;
}

SFValuenf.prototype["toString"]=function(){
			var ret="-"+this.v.length;
			for(var i=0;i<this.v.length;i++){
				ret+=this.v[i]+"-";
			}
			return ret;
		};
		
SFValuenf.prototype["setArray"]=function(data){
			this.v=data;
		};

SFValuenf.prototype["setValue"]=function(value){
				
			for(var i=0;i<value.v.length;i++){
				this.v[i]=value.v[i];
			}
			
		};
		
SFValuenf.prototype["set"]=function(value){
			for(var i=0;i<this.v.length;i++){
				this.v[i]=value.v[i];
			}
		};

SFValuenf.prototype["dot"]=function(value){
			var dot=0;
			for(var i=0;i<this.v.length;i++){
				dot+=this.v[i]*value.v[i];
			}
			return dot;
		};

SFValuenf.prototype["subtract"]=function(value){
			for(var i=0;i<this.v.length;i++){
				this.v[i]-=value.v[i];
			}
		};
		
SFValuenf.prototype["mult"]=function(m){
			for(var i=0;i<this.v.length;i++){
				this.v[i]*=m;
			}
		};

SFValuenf.prototype["add"]=function(value){
			for(var i=0;i<this.v.length;i++){
				this.v[i]+=value.v[i];
			}
		};
		
SFValuenf.prototype["addMult"]=function(m, value){
			for(var i=0;i<this.v.length;i++){
				this.v[i]+=m*value.v[i];
			}
		};
		
SFValuenf.prototype["setByIndex"]=function(index,value){
			this.v[index]=value.v[index];
		};
