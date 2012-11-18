

function SFFunction2DBitmap(width,height,rgb,func){

	if(width!=undefined && height!=undefined && rgb!=undefined){
		
		this.setWidth(width);
		this.setHeight(height);
		
		this.rgb=rgb;
			
		this.func=func;
		if(rgb){
			this.setFormat(SFImageFormat_RGB8);
		}else{
			this.setFormat(SFImageFormat_GRAY8);
		}
	}	
}

inherit(SFFunction2DBitmap,SFBitmap);

SFFunction2DBitmap.prototype["getFunction"]=function(){
		return this.func;
};

SFFunction2DBitmap.prototype["setFunction"]=function(func){
		this.func = func;
};

	
SFFunction2DBitmap.prototype["init"]=function(){
		//super.init????
		
		
		var width=this.getWidth();
		var height=this.getHeight();
		
		var size = this.getSize();
		

		var stepH=1.0/height;
		var stepW=1.0/width;
		
		var dataArray = new Array();
		var index=0;
		
		for(var i=0;i<height;i++){
			for(var j=0;j<width;j++){
				
				var u=stepW*j;
				var v=stepH*i;
				
				var color=255*this.func.getValue(u,v);
				if(color>255)
					color=255;
				if(color<0)
					color=0;
				
				dataArray[index]=color;
				index++;
				if(size==3){
					dataArray[index]=color;
					index++;
					dataArray[index]=color;
					index++;
				}
			}
		}
		this.setWidth(width);
		this.setHeight(height);
		var buffer = new Uint8Array(dataArray);
		this.setData(buffer);
};
	

