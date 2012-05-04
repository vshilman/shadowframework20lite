package shadow.pipeline.parameters;

import shadow.math.SFMatrix2f;
import shadow.math.SFMatrix3f;
import shadow.math.SFMatrix4f;
import shadow.math.SFValue1f;
import shadow.math.SFValuenf;
import shadow.math.SFVertex2f;
import shadow.math.SFVertex3f;
import shadow.math.SFVertex4f;

public abstract class SFParameteri {

	public static final short GLOBAL_GENERIC = 100;
	public static final short GLOBAL_FLOAT = 0;
	public static final short GLOBAL_FLOAT2 = 1;
	public static final short GLOBAL_FLOAT3 = 2;
	public static final short GLOBAL_FLOAT4 = 3;
	public static final short GLOBAL_MATRIX2 = 4;
	public static final short GLOBAL_MATRIX3 = 5;
	public static final short GLOBAL_MATRIX4 = 6;
	public static final short GLOBAL_TEXTURE = 9;

	public abstract String getName();
	public abstract short getType();
	
	public static SFValuenf generateValue(SFParameteri sfParameteri){
		short type=sfParameteri.getType();
		return generateValue(type);
	}
	
	public static SFValuenf generateValue(short type) {
		switch(type){
			//case GLOBAL_UNIDENTIFIED: TODO: what's happening to GLOBAL_UNIDENTIFIED?? 
			case GLOBAL_FLOAT: return new SFValue1f(0);
			case GLOBAL_FLOAT2: return new SFVertex2f(0,0);
			case GLOBAL_FLOAT3: return new SFVertex3f(0,0,0);
			case GLOBAL_FLOAT4: return new SFVertex4f(0,0,0,0);
			case GLOBAL_MATRIX2: return SFMatrix2f.getIdentity();
			case GLOBAL_MATRIX3: return SFMatrix3f.getIdentity();
			case GLOBAL_MATRIX4: return SFMatrix4f.getIdentity();
			case GLOBAL_TEXTURE: return new SFValue1f(0); //TODO is Global_TEXTURE going to be correct this way? 
		}
		return null;
	}
	
	public static int getTypeDimension(short type){
		switch(type){
			case GLOBAL_GENERIC: return 0;
			case GLOBAL_FLOAT: return 1;
			case GLOBAL_FLOAT2: return 2;
			case GLOBAL_FLOAT3: return 3;
			case GLOBAL_FLOAT4: return 4;
			case GLOBAL_MATRIX2: return 4;
			case GLOBAL_MATRIX3: return 9;
			case GLOBAL_MATRIX4: return 16;
			case GLOBAL_TEXTURE: return 1;
		}
		return 0;
	}
}