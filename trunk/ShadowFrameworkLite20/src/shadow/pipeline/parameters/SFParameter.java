package shadow.pipeline.parameters;


/**
 * @author Alessandro Martinelli
 */
public class SFParameter extends SFParameteri {

	protected String name;
	protected short type=GLOBAL_GENERIC;

	public SFParameter() {
		super();
	}

	public SFParameter(String name) {
		super();
		this.name = name;
	}
	
	public SFParameter(String name, short type) {
		super();
		this.name = name;
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see shadow.pipeline.SFParameteri#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see shadow.pipeline.SFParameteri#getType()
	 */
	@Override
	public short getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}
	
	
}