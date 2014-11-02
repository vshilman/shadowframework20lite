package shadow.system.data;


/**
 * A set of parameters, each one having a name.
 * 
 * @author Alessandro Martinelli
 */
public class SFStaticParametersObject {
	
	private static SFStaticParametersObject object=new SFStaticParametersObject();
	
	private SFStaticParametersObject(){
		
	}
	
	//Parameters Names
	private SFNamedParametersObject parametersObject=new SFNamedParametersObject();

	public static SFNamedParametersObject getParametersObject() {
		return object.parametersObject;
	}

	public static void setParametersObject(SFNamedParametersObject parametersObject) {
		object.parametersObject = parametersObject;
	}
	
}
