//Java to JS on 06/02/2012

function SFValue1f(x){
	this.v=new Array();
	this.v[0]=x;
}

inherit(SFValue1f,SFValuenf);
	
		
SFValue1f.prototype["storeOn1f"]=function(f){
			f[0]=this.v[0];
		};
		
SFValue1f.prototype["getX"]=function(){
		return this.v[0];
		};
		
SFValue1f.prototype["setX"]=function(x){
			this.v[0]  = x;
		};
		
SFValue1f.prototype["mult1f"]=function(m){
			this.v[0] *= m;
		};
		
SFValue1f.prototype["add1f"]=function(dX){
			this.v[0] += dX;
		};
		
SFValue1f.prototype["subtract1f"]=function(dX){
			this.v[0] -= dX;
		};
		
SFValue1f.prototype["setByIndex"]=function(index, val){
			this.v[index]=val;
		};
		
SFValue1f.prototype["getByIndex"]=function(index){
			if(index==0)
				return this.v[index];
		};
		
SFValue1f.prototype["cloneValue"]=function(){
			return new SFValue1f(this.v[0]);
		};
