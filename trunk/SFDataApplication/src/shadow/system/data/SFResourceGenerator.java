package shadow.system.data;


/**
 * A component which can generate a resource of a specific kind from a SFNamedParametersObject
 * 
 * @author Alessandro Martinelli
 *
 * @param <T>
 */
public interface SFResourceGenerator<T> {

	public abstract T createResource(SFNamedParametersObject sfDataObject);

}
