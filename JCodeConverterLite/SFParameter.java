package shadow.pipeline.parameters;


/**
 * @author Alessandro Martinelli
 */
public class SFParameter extends SFParameteri {

	private String name;
	protected short type=GLOBAL_GENERIC;

	public SFParameter() {
		super();
	}

	public SFParameter(String name, short type) {
		super();
		this.name = name;
		this.type = type;
	}


	public SFParameter(String name) {
		super();
		this.name = name;
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
	public long getType() {
		return type;
	}

	public void setType(short type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return name+"("+type+")";
	}
}