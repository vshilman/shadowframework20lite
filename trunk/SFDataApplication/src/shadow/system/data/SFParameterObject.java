package shadow.system.data;

/**
 * 
 * @author Alessandro Martinelli
 */
public class SFParameterObject implements SFDataObject{

	private int index;
	
	public SFParameterObject(int index) {
		super();
		this.index = index;
	}

	@Override
	public SFDataObject copyDataObject() {
		return new SFParameterObject(index);
	}
	
	public int getIndex() {
		return index;
	}
	
	@Override
	public void readFromStream(SFInputStream stream) {
		this.index=stream.readByte();
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeByte(this.index);
	}
}
