package shadow.system.data.objects;

public class SFBooleanEnum extends SFEnumObject<Boolean>{

	private static Boolean[] alternatives={false,true};
	private static String[] names={"false","true"};
	
	public SFBooleanEnum() {
		super(alternatives,names);
	}
	
	@Override
	public SFEnumObject<Boolean> copyDataObject() {
		SFBooleanEnum enumObject=new SFBooleanEnum();
		enumObject.setIndex(getIndex());
		return enumObject;
	}
}
