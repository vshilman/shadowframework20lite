package shadow.system.data;


public abstract class SFAlternativeDataCenter implements SFIDataCenter{

	protected SFIDataCenter dataCenter = null;

	public SFAlternativeDataCenter() {
		super();
	}

	public void setup() {
		dataCenter=SFDataCenter.getDataCenter().getDataCenterImplementation();
		SFDataCenter.setDataCenterImplementation(this);
	}

	public void unset() {
		if(dataCenter!=null){
			SFDataCenter.setDataCenterImplementation(dataCenter);
		}
	}

}