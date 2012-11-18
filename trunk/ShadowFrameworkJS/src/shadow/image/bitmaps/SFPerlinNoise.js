

function SFPerlinNoise(){

}

inherit(SFPerlinNoise,SFBitmap);

SFPerlinNoise.prototype["init"]=function(){
		this.setup(this.weights, this.rgb);
};

SFPerlinNoise.prototype["setup"]=function(weights,rgb){
		var width=this.getWidth();
		var height=this.getHeight();

		if(rgb){
			this.setFormat(SFImageFormat_RGB8);
		}else{
			this.setFormat(SFImageFormat_GRAY8);
		}
		
		var size=1;
		if(rgb){
			size=3;
		}
		
		var stepH=1.0/height;
		var stepW=1.0/width;
		
		var dataArray = new Array();
		var index=0;
		
		for(var i=0;i<height;i++){
			for(var j=0;j<width;j++){
				
				var u=stepW*j;
				var v=stepH*i;
				
				var color=0;
				var oct=0;
				for(var k=0;k<weights.length;k++){
					var addColor=this.getOctaveValue(u,v,oct)*weights[k];
					color+=addColor;
					if(k==0){
						oct=1;
					}else{
						oct*=2;
					}
					
				}
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




