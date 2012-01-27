
function SFInputStreamJava(stream, keeper){
		this.stream=stream;
		this.keeper=keeper;
}

SFInputStreamJava.prototype = {

	readFloat:function(data, index){
	int Value=(data[index+3]>=0?data[index+3]:256+data[index+3])+				  (data[index+2]>=0?data[index+2]:256+data[index+2])*0x100+				  (data[index+1]>=0?data[index+1]:256+data[index+1])*0x10000+				  (data[index]>=0?data[index]:256+data[index])*0x1000000;//Warning: Not well Identified 
	return Float.intBitsToFloat(Value);//Warning: Not well Identified 
	},

	readShort:function(data, index){
	short Value=(short)((data[index+1]>=0?data[index+1]:256+data[index+1])+				(data[index]>=0?data[index]:256+data[index])*0x100);//Warning: Not well Identified 
		return this.Value;
	},

	readInt:function(data, index){
	int Value=(data[index+3]>=0?data[index+3]:256+data[index+3])+				  (data[index+2]>=0?data[index+2]:256+data[index+2])*0x100+				  (data[index+1]>=0?data[index+1]:256+data[index+1])*0x10000+				  (data[index]>=0?data[index]:256+data[index])*0x1000000;//Warning: Not well Identified 
		return this.Value;
	}

};