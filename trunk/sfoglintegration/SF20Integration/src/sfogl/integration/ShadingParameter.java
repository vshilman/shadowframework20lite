package sfogl.integration;


public class ShadingParameter {

	public enum ParameterType{
		GLOBAL_GENERIC,
		GLOBAL_FLOAT,
		GLOBAL_FLOAT2,
		GLOBAL_FLOAT3,
		GLOBAL_FLOAT4,
		GLOBAL_MATRIX2,
		GLOBAL_MATRIX3,
		GLOBAL_MATRIX4,
		GLOBAL_TEXTURE,
	}
	
	protected String name;
	protected ParameterType type=ParameterType.GLOBAL_GENERIC;

	public ShadingParameter(String name, ParameterType type) {
		super();
		this.name = name;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}

	public ParameterType getType() {
		return type;
	}

	public void setType(ParameterType type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return name+"("+type+")";
	}
}
