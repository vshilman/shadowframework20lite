

function SFPerlineNoiseData(){
	
}

inherit(SFPerlineNoiseData,SFBitmapData);

SFPerlineNoiseData.prototype["prepare"]=function(){
	this.width=new SFShort(0);
	this.height=new SFShort(0);
	this.weights=new SFBinaryFloatArrayObject(1,250);
};