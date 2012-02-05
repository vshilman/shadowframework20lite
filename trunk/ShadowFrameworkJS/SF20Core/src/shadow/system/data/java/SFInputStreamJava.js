
function SFInputStreamJava(){
}

SFInputStreamJava.prototype = {

	readString:function(){
		 int  n = readInt();
		 byte[]  data = readBytes(n);
		return ,new ,String(data);
	},

	readShort:function(){
		 short  fs = 0;
		 byte[]  bytes = new  byte[2];
		try{
		stream.read(bytes, 0, 2);
	}
		catch (IOException e){
		keeper.launch(e);
	}
		fs  = readShort(bytes, 0);
		return ,fs;
	},

	readLong:function(){
		 int  fs1 = readInt();
		 int  fs2 = readInt();
	return ((long)fs1)+(((long)fs2)<<32L);//Warning: Not well Identified 
	},

	readInt:function(){
		 int  fs = 0;
		 byte[]  bytes = new  byte[4];
		try{
		stream.read(bytes, 0, 4);
	}
		catch (IOException e){
		keeper.launch(e);
	}
		fs  = readInt(bytes, 0);
		return ,fs;
	},

	readInts:function(n){
		 int[]  fs = new  int[n];
	byte[] bytes=new byte[4*n];//Warning: Not well Identified 
		try{
		stream.read(bytes, 0, 4*n);
	}
		catch (IOException e){
		keeper.launch(e);
	}
		for ( int  i=0 ; i < n ; i++ ){
		fs[i]  = readInt(bytes, i*4);
	}
		return ,fs;
	},

	readBytes:function(n){
		 byte[]  bytes = new  byte[n];
		try{
		stream.read(bytes, 0, n);
	}
		catch (IOException e){
		keeper.launch(e);
	}
		return ,bytes;
	},

	readFloat:function(){
		 float  fs = 0;
		 byte[]  bytes = new  byte[4];
		try{
		stream.read(bytes, 0, 4);
	}
		catch (IOException e){
		keeper.launch(e);
	}
		fs  = readFloat(bytes, 0);
		return ,fs;
	},

	readFloats:function(n){
		 float[]  fs = new  float[n];
	byte[] bytes=new byte[4*n];//Warning: Not well Identified 
		try{
		stream.read(bytes, 0, 4*n);
	}
		catch (IOException e){
		keeper.launch(e);
	}
		for ( int  i=0 ; i < n ; i++ ){
		fs[i]  = readFloat(bytes, i*4);
	}
		return ,fs;
	},

	readFloat:function(data, index){
	int Value=(data[index+3]>=0?data[index+3]:256+data[index+3])+				  (data[index+2]>=0?data[index+2]:256+data[index+2])*0x100+				  (data[index+1]>=0?data[index+1]:256+data[index+1])*0x10000+				  (data[index]>=0?data[index]:256+data[index])*0x1000000;//Warning: Not well Identified 
		return ,Float.intBitsToFloat(Value);
	},

	readShort:function(data, index){
	short Value=(short)((data[index+1]>=0?data[index+1]:256+data[index+1])+				(data[index]>=0?data[index]:256+data[index])*0x100);//Warning: Not well Identified 
		return ,Value;
	},

	readInt:function(data, index){
	int Value=(data[index+3]>=0?data[index+3]:256+data[index+3])+				  (data[index+2]>=0?data[index+2]:256+data[index+2])*0x100+				  (data[index+1]>=0?data[index+1]:256+data[index+1])*0x10000+				  (data[index]>=0?data[index]:256+data[index])*0x1000000;//Warning: Not well Identified 
		return ,Value;
	}

};