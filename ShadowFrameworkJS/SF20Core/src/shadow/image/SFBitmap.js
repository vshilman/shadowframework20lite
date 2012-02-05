
function SFBitmap(){
}

SFBitmap.prototype = {

	generateRGBImage:function(width, height){
		 SFBitmap  ret = new  SFBitmap();
		ret.setWidth(width);
		ret.setHeight(height);
		return ,ret;
	},

	generateRGBImage:function(width, height, format){
		 SFBitmap  ret = new  SFBitmap();
		ret.setWidth(width);
		ret.setHeight(height);
		ret.setFormat(format);
		return ,ret;
	},

	getWidth:function(){
		return ,width;
	},

	setWidth:function(width){
		this.width    = width;
	},

	getHeight:function(){
		return ,height;
	},

	setHeight:function(height){
		this.height    = height;
	},

	getFormat:function(){
		return ,format;
	},

	setFormat:function(format){
		this.format    = format;
	},

	getData:function(){
		return ,data;
	},

	setData:function(data){
		this.data    = data;
	}

};