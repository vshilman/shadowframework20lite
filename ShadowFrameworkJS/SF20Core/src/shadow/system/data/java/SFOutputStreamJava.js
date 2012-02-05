
function SFOutputStreamJava(){
}

SFOutputStreamJava.prototype = {

	writeHeader:function(dataset){
		writeString(((dataset.getCode())));
	},

	writeString:function(s){
		writeInt(s.length());
		writeBytes(s.getBytes());
	},

	writeDataObjectHeader:function(code, N){
		writeShort(code);
		writeShort((short)N);
	},

	writeFloat:function(value){
		 byte[]  data = new  byte[4];
		writeFloat(data,0,value);
		try{
		stream.write(data);
	}
		catch (IOException e){
		keeper.launch(e);
	}
	},

	writeFloats:function(values){
	byte[] data=new byte[4*values.length];//Warning: Not well Identified 
		for ( int  i  =  0 ; i   < values.length ; i++ ){
		writeFloat(data,i*4,values[i]);
	}
		try{
		stream.write(data);
	}
		catch (IOException e){
		keeper.launch(e);
	}
	},

	writeShort:function(value){
		 byte[]  data = new  byte[2];
		writeShort(data,0,value);
		try{
		stream.write(data);
	}
		catch (IOException e){
		keeper.launch(e);
	}
	},

	writeInt:function(value){
		 byte[]  data = new  byte[4];
		writeInt(data,0,value);
		try{
		stream.write(data);
	}
		catch (IOException e){
		keeper.launch(e);
	}
	},

	writeLong:function(value){
	int a=(int)(value & 0xffffffffL);//Warning: Not well Identified 
	int b=(int)((value & 0xffffffff00000000L)>>32L);//Warning: Not well Identified 
		writeInt( a);
		writeInt( b);
	},

	writeInts:function(values){
	byte[] data=new byte[4*values.length];//Warning: Not well Identified 
		for ( int  i  =  0 ; i   < values.length ; i++ ){
		writeInt(data,i*4,values[i]);
	}
		try{
		stream.write(data);
	}
		catch (IOException e){
		keeper.launch(e);
	}
	},

	writeBytes:function(value[]){
		try{
		stream.write(value);
	}
		catch (IOException e){
		keeper.launch(e);
	}
	},

	writeFloat:function(data, index, value){
		 int  intValue = Float.floatToIntBits(value);
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