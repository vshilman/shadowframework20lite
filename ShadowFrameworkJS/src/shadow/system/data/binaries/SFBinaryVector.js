
function SFBinaryVector(min,max,ns){
	this.setup(min,max,ns);
}

inherit(SFBinaryVector,SFBinaryValue);


SFBinaryVector.prototype["setup"]=function(min,max,ns){
			this.ns=ns;
			this.shifts=new Array();
			this.mask=new Array();
			this.bitSize=0;
			for (var i = 0; i < ns.length; i++) {
				this.bitSize+=ns[i];
				if(i==0){
					this.shifts[i]=0;
				}else{
					this.shifts[i]=this.shifts[i-1]+this.ns[i-1];
				}
				this.mask[i]=(2<<(ns[i]-1))-1;
			}
			this.delta=max-min;
			this.min=min;
		};

SFBinaryVector.prototype["clone"]=function(){
			var tmp=new SFBinaryVector(this.min, 0, this.ns);
			tmp.delta=this.delta;
			return tmp;
		};

SFBinaryVector.prototype["getBitSize"]=function(){
			return this.bitSize;
		};

SFBinaryVector.prototype["getValue"]=function(write){
			var data=write.get();
			for (var i = 0; i < data.length; i++) {
				var value= (this.value >> this.shifts[i]) & this.mask[i] ;
				data[i]=this.min+(this.delta/this.mask[i])*( value );
			};
		};

SFBinaryVector.prototype["setValue"]=function(read){
			var data=read.get();
			this.value=0;
			for (var i = 0; i < data.length; i++) {
				var value=(int)((this.mask[i]*(data[i]-this.min))/this.delta);
				var adding=(  (value & this.mask[i]) << this.shifts[i]  );
				this.value+=adding;
			};
		};

		
	