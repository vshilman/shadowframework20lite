

SFMathStream.prototype = {

	writeVertexByte:function(stream, v){
	byte []bs=new byte[4];//Warning: Not well Identified 
	bs[0]=(byte)(((v.getX()+1)*0.5f)*255);//Warning: Not well Identified 
	bs[1]=(byte)(((v.getY()+1)*0.5f)*255);//Warning: Not well Identified 
	bs[2]=(byte)(((v.getZ()+1)*0.5f)*255);//Warning: Not well Identified 
	bs[3]=(byte)(((v.getW()+1)*0.5f)*255);//Warning: Not well Identified 
	stream.writeBytes(bs);//Warning: Not well Identified 
	},

	writeVertexByte:function(stream, v){
	byte []bs=new byte[3];//Warning: Not well Identified 
	bs[0]=(byte)(((v.getX()+1)*0.5f)*255);//Warning: Not well Identified 
	bs[1]=(byte)(((v.getY()+1)*0.5f)*255);//Warning: Not well Identified 
	bs[2]=(byte)(((v.getZ()+1)*0.5f)*255);//Warning: Not well Identified 
	stream.writeBytes(bs);//Warning: Not well Identified 
	},

	writeVertexByte:function(stream, v){
	byte []bs=new byte[2];//Warning: Not well Identified 
	bs[0]=(byte)(((v.getX()+1)*0.5f)*255);//Warning: Not well Identified 
	bs[1]=(byte)(((v.getY()+1)*0.5f)*255);//Warning: Not well Identified 
	stream.writeBytes(bs);//Warning: Not well Identified 
	},

	writeVertex:function(stream, v){
	stream.writeFloat( v.getX());//Warning: Not well Identified 
	stream.writeFloat( v.getY());//Warning: Not well Identified 
	stream.writeFloat( v.getZ());//Warning: Not well Identified 
	},

	writeVertex:function(stream, v){
	stream.writeFloat( v.getX());//Warning: Not well Identified 
	stream.writeFloat( v.getY());//Warning: Not well Identified 
	},

	writeVertex:function(stream, v){
	stream.writeFloat( v.getX());//Warning: Not well Identified 
	stream.writeFloat( v.getY());//Warning: Not well Identified 
	stream.writeFloat( v.getZ());//Warning: Not well Identified 
	stream.writeFloat( v.getW());//Warning: Not well Identified 
	},

	readVertex3f:function(stream, v){
	v.setX( stream.readFloat());//Warning: Not well Identified 
	v.setY( stream.readFloat());//Warning: Not well Identified 
	v.setZ( stream.readFloat());//Warning: Not well Identified 
		return this.v;
	},

	readVertex2fByte:function(stream){
	SFVertex2f v;
	byte []bs=stream.readBytes( 2);//Warning: Not well Identified 
	v.setX(  (2*rec255*( bs[0]>=0?bs[0]:bs[0]+256 ))-1);//Warning: Not well Identified 
	v.setY(  (2*rec255*( bs[1]>=0?bs[1]:bs[1]+256 ))-1);//Warning: Not well Identified 
		return this.v;
	},

	readVertex3fByte:function(stream){
	SFVertex3f v;
	byte []bs=stream.readBytes( 3);//Warning: Not well Identified 
	v.setX(  (2*rec255*( bs[0]>=0?bs[0]:bs[0]+256 ))-1);//Warning: Not well Identified 
	v.setY(  (2*rec255*( bs[1]>=0?bs[1]:bs[1]+256 ))-1);//Warning: Not well Identified 
	v.setZ(  (2*rec255*( bs[2]>=0?bs[2]:bs[2]+256 ))-1);//Warning: Not well Identified 
		return this.v;
	},

	readVertex4fByte:function(stream){
	SFVertex4f v;
	byte []bs=stream.readBytes( 4);//Warning: Not well Identified 
	v.setX(  (2*rec255*( bs[0]>=0?bs[0]:bs[0]+256 ))-1);//Warning: Not well Identified 
	v.setY(  (2*rec255*( bs[1]>=0?bs[1]:bs[1]+256 ))-1);//Warning: Not well Identified 
	v.setZ(  (2*rec255*( bs[2]>=0?bs[2]:bs[2]+256 ))-1);//Warning: Not well Identified 
	v.setW(  (2*rec255*( bs[3]>=0?bs[3]:bs[3]+256 ))-1);//Warning: Not well Identified 
		return this.v;
	}

};