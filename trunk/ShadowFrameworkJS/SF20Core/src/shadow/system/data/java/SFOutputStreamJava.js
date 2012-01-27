
function SFOutputStreamJava(stream, keeper){
		this.stream=stream;
		this.keeper=keeper;
}

SFOutputStreamJava.prototype = {

	writeFloat:function(data, index, value){
	int intValue;
	data[index+0]=(byte)((intValue & 0xff000000)>>24);//Warning: Not well Identified 
	data[index+1]=(byte)((intValue & 0xff0000)>>16);//Warning: Not well Identified 
	data[index+2]=(byte)((intValue & 0xff00)>>8);//Warning: Not well Identified 
	data[index+3]=(byte)((intValue & 0xff));//Warning: Not well Identified 
	},

	writeShort:function(data, index, value){
	data[index+0]=(byte)((value & 0xff00)>>8);//Warning: Not well Identified 
	data[index+1]=(byte)((value & 0xff));//Warning: Not well Identified 
	},

	writeInt:function(data, index, value){
	data[index+0]=(byte)((value & 0xff000000)>>24);//Warning: Not well Identified 
	data[index+1]=(byte)((value & 0xff0000)>>16);//Warning: Not well Identified 
	data[index+2]=(byte)((value & 0xff00)>>8);//Warning: Not well Identified 
	data[index+3]=(byte)((value & 0xff));//Warning: Not well Identified 
	}

};