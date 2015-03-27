package shadow.system.data;

import shadow.system.SFContext;

/**
 * A set of Data which can 
 * 
 * @author Alessandro Martinelli
 *
 * @param <T>
 */
public class SFDataAsset<T> extends SFNamedParametersObject implements SFResourceGenerator<T>{

	protected String name;

	protected SFResourceGenerator<T> resourceGenerator;

	public SFDataAsset() {
		super();
		this.resourceGenerator=this;
	}

	public String getName() {
		return this.name;
	}
	
	@Override
	public T createResource(SFContext context,
			SFNamedParametersObject sfDataObject) {
		return null;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public SFResourceGenerator<T> getResourceGenerator() {
		return resourceGenerator;
	}
	
	public T getResource(SFContext context) {
		return resourceGenerator.createResource(context,this);
	}
	
	public SFDataAsset<T> copyDataset() {
		SFDataAsset<T> dataset=new SFDataAsset<T>();
		copyDataObject(dataset);
		dataset.resourceGenerator=this.resourceGenerator;
		dataset.name=this.name;
		return dataset;
	}
	
}