package shadow.system.data;



public class SFTemplateDataAsset extends SFDataAsset<Object>{
	
	public SFTemplateDataAsset() {
		addObject("asset", new SFLibraryReference<SFDataAsset<?>>());
		addObject("parameters", new SFNamedParametersObject());//non è writable, ma non ha importanza!!
	}
	
	@Override
	public Object createResource(SFNamedParametersObject sfDataObject) {
		
		SFLibraryReference<SFDataAsset<?>> reference=sfDataObject.getDataObject(0);
		SFDataAsset<SFDataAsset<?>> dataAsset=reference.getDataAsset();
		
		SFNamedParametersObject parameters=sfDataObject.getDataObject(1);
		
		SFStaticParametersObject.setParametersObject(parameters);//so what the 
		Object dataAsset_=dataAsset.getResource();
		
		return dataAsset_;
	}
	
}

