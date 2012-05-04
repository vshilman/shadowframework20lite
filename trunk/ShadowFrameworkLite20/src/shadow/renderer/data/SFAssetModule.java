package shadow.renderer.data;

import shadow.system.data.SFDataObject;
import shadow.system.data.SFDataset;


/**
 * A Generic Class which is the base of all Asset classes;
 * asset classes are used to load and store informations  
 * about graphical elements.
 * 
 * @author Alessandro Martinelli
 *
 */
public abstract class SFAssetModule implements SFDataset{

	private SFDataObject sfDataObject;

	public SFAssetModule() {
		super();
	}

	@Override
	public SFDataset generateNewDatasetInstance() {
		try {
			SFDataset dataset=this.getClass().newInstance();
			return dataset;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	@Override
	public SFDataObject getSFDataObject() {
		return sfDataObject;
	}

	@Override
	public String getType() {
		return getClass().getSimpleName();
	}

	/**
	 * Should be used only by sub-classes to assign the {@link SFDataObject}
	 * @param sfDataObject
	 */
	protected void setData(SFDataObject sfDataObject) {
		this.sfDataObject = sfDataObject;
	}

	public void invalidate() {
	}

}