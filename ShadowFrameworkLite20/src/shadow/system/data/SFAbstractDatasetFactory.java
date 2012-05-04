package shadow.system.data;

/**
 * An interface for a Factory able to produce new instances
 * of Dataset from their types name
 * @author Alessandro Martinelli
 */
public interface SFAbstractDatasetFactory {
	public void writeDataset(SFOutputStream stream,SFDataset dataset);
	public SFDataset readDataset(SFInputStream stream);
	public SFDataset createDataset(String type);
}
