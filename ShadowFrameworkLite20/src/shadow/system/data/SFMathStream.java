package shadow.system.data;

import shadow.math.SFVertex2f;
import shadow.math.SFVertex3f;
import shadow.math.SFVertex4f;

public class SFMathStream{

	private static float rec255=1.0f/255;
	
	public static void writeVertexByte(SFOutputStream stream,SFVertex4f v){
		byte []bs=new byte[4];
		bs[0]=(byte)(((v.getX()+1)*0.5f)*255);
		bs[1]=(byte)(((v.getY()+1)*0.5f)*255);
		bs[2]=(byte)(((v.getZ()+1)*0.5f)*255);
		bs[3]=(byte)(((v.getW()+1)*0.5f)*255);
		stream.writeBytes(bs);
	}
	
	public static void writeVertexByte(SFOutputStream stream,SFVertex3f v){
		byte []bs=new byte[3];
		bs[0]=(byte)(((v.getX()+1)*0.5f)*255);
		bs[1]=(byte)(((v.getY()+1)*0.5f)*255);
		bs[2]=(byte)(((v.getZ()+1)*0.5f)*255);
		stream.writeBytes(bs);
	}
	
	public static void writeVertexByte(SFOutputStream stream,SFVertex2f v){
		byte []bs=new byte[2];
		bs[0]=(byte)(((v.getX()+1)*0.5f)*255);
		bs[1]=(byte)(((v.getY()+1)*0.5f)*255);
		stream.writeBytes(bs);
	}
	
	public static void writeVertex(SFOutputStream stream,SFVertex3f v){
		stream.writeFloat( v.getX());
		stream.writeFloat( v.getY());
		stream.writeFloat( v.getZ());
	}
	
	public static void writeVertex(SFOutputStream stream,SFVertex2f v){
		stream.writeFloat( v.getX());
		stream.writeFloat( v.getY());
	}
	
	public static void writeVertex(SFOutputStream stream,SFVertex4f v){
		stream.writeFloat( v.getX());
		stream.writeFloat( v.getY());
		stream.writeFloat( v.getZ());
		stream.writeFloat( v.getW());
	}

	public static SFVertex3f readVertex3f(SFInputStream stream,SFVertex3f v){
		
		v.setX( stream.readFloat());
		v.setY( stream.readFloat());
		v.setZ( stream.readFloat());
		
		return v;
	}
	
	public static SFVertex2f readVertex2fByte(SFInputStream stream){
		SFVertex2f v=new SFVertex2f(0,0);
		byte []bs=stream.readBytes( 2);
		
		v.setX(  (2*rec255*( bs[0]>=0?bs[0]:bs[0]+256 ))-1);
		v.setY(  (2*rec255*( bs[1]>=0?bs[1]:bs[1]+256 ))-1);
		
		return v;
	}
	
	public static SFVertex3f readVertex3fByte(SFInputStream stream){
		SFVertex3f v=new SFVertex3f(0,0,0);
		byte []bs=stream.readBytes( 3);
		
		v.setX(  (2*rec255*( bs[0]>=0?bs[0]:bs[0]+256 ))-1);
		v.setY(  (2*rec255*( bs[1]>=0?bs[1]:bs[1]+256 ))-1);
		v.setZ(  (2*rec255*( bs[2]>=0?bs[2]:bs[2]+256 ))-1);
		
		return v;
	}
	
	public static SFVertex4f readVertex4fByte(SFInputStream stream){
		SFVertex4f v=new SFVertex4f(0,0,0,0);
		byte []bs=stream.readBytes( 4);
		
		v.setX(  (2*rec255*( bs[0]>=0?bs[0]:bs[0]+256 ))-1);
		v.setY(  (2*rec255*( bs[1]>=0?bs[1]:bs[1]+256 ))-1);
		v.setZ(  (2*rec255*( bs[2]>=0?bs[2]:bs[2]+256 ))-1);
		v.setW(  (2*rec255*( bs[3]>=0?bs[3]:bs[3]+256 ))-1);
		
		return v;
	}
}
