package shadow.system.data;

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
		name=getClass().getSimpleName();
	}

	public String getName() {
		return this.name;
	}
	
	public T createResource(SFNamedParametersObject sfDataObject) {
		return null;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public SFResourceGenerator<T> getResourceGenerator() {
		return resourceGenerator;
	}
	
	public T getResource() {
		return resourceGenerator.createResource(this);
	}
	
	public SFDataAsset<T> copyDataset() {
		SFDataAsset<T> dataset=new SFDataAsset<T>();
		copyDataObject(dataset);
		dataset.resourceGenerator=this.resourceGenerator;
		dataset.name=this.name;
		return dataset;
	}
	

}