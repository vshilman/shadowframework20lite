package shadow.system.data;

import shadow.system.SFContext;



public class SFTemplateDataAsset extends SFDataAsset<Object>{
	
	public SFTemplateDataAsset() {
		setName("Template");
		addObject("asset", new SFLibraryReference<SFDataAsset<?>>());
		addObject("parameters", new SFNamedParametersObject());//non � writable, ma non ha importanza!!
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Object createResource(SFContext context,SFNamedParametersObject sfDataObject) {
		
		SFLibraryReference<SFDataAsset<?>> reference=sfDataObject.getDataObject(0);
		SFDataAsset<SFDataAsset<?>> dataAsset=(SFDataAsset<SFDataAsset<?>>)reference.getDataAsset();
		
		SFNamedParametersObject parameters=sfDataObject.getDataObject(1);
		
		SFNamedParametersObject.setParametersObject(parameters);//so what the 
		Object dataAsset_=dataAsset.getResource(context);
		
		return dataAsset_;
	}
	
}

