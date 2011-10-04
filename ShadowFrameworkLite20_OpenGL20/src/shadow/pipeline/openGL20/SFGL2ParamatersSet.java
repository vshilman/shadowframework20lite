package shadow.pipeline.openGL20;

public class SFGL2ParamatersSet {

	public static final int UNIFORM_MODIFIER=0;
	public static final int VARYING_MODIFIER=1;
	public static final int UNDECLARED=2;
	
	public static final int VARIABLE=0;
	public static final int DATA=0;
	public static final int MEASURE=0;
	
	private int modifier;
	private String name;
	
	public String getTypeDeclararion(){
		return "";
	}
	
	public String getGL2HeaderDeclaration(){
		switch (modifier) {
			case UNIFORM_MODIFIER:
					return "uniform "+getTypeDeclararion()+" "+name+";"; 
			case VARYING_MODIFIER:
					return "varying "+getTypeDeclararion()+" "+name+";"; 
			default:
				break;
		}
		return null;
	}
	
	public String getDeclaration(){
		return getTypeDeclararion()+" ";
	}
}

