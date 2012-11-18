//Java to JS on 06/02/2012

function SFBounds(basePoint,extension){
	this.basePoint=basePoint;
	this.extension=extension;
}

SFBounds.prototype["getBasePoint"]=function(){
			return this.basePoint;
		};
		
SFBounds.prototype["setBasePoint"]=function(basePoint){
			this.basePoint=basePoint;
		};

SFBounds.prototype["getExtension"]=function(){
			return this.extension;
		};
		
SFBounds.prototype["setExtension"]=function(extension){
			this.extension=extension;
		};
		
SFBounds.prototype["setExtension"]=function(extension){
			this.extension=extension;
		};

SFBounds.prototype["includeInBounds"]=function(xs){
			for(var i=0;i<xs.length;i++){
				if(this.getBasePoint().get()[i]>xs[i]){
					this.getExtension().get()[i]+=this.getBasePoint().get()[i]-xs[i];
					this.getBasePoint().get()[i]=xs[i];
				}else if(getBasePoint().get()[i]+this.getExtension().get()[i]<xs[i]){
					this.getExtension().get()[i]=xs[i]-this.getBasePoint().get()[i];
				} 
			}
		};

SFBounds.prototype["isIn"]=function(maxDistance,xs){
			for (var i = 0; i < xs.length; i++) {
				if(this.basePoint.get()[0]-maxDistance>xs[0] || 
						this.basePoint.get()[0]+this.extension.get()[0]+maxDistance<xs[0]){
					return false;
				}
			}
			return true;
		};	
		
