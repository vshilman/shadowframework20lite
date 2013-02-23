package shadow.geometry.vertices;

import java.util.HashMap;

public class SFVerticesParameters {

	private static SFVerticesParameters verticesParameters=new SFVerticesParameters();
	
	private SFVerticesParameters(){
		
	}

	public static SFVerticesParameters getParameters() {
		return verticesParameters;
	}
	
	private HashMap<String,Float> parameters=new HashMap<String, Float>();
	
	public void clear(){
		parameters.clear();
	}
	
	public void setParameter(String parameter,float value){
		parameters.put(parameter,value);
	}
	
	public float getValue(String parameter){
		return parameters.get(parameter);
	}
}
