package shadow.pipeline.java;

import shadow.math.SFValuenf;
import shadow.system.SFArrayElementException;

public class SFGL20ValuenfArray extends SFGL20ListData<SFValuenf>{

	private int n;
	
	public SFGL20ValuenfArray(int n) {
		super();
		this.n = n;
	}

	@Override
	protected void assignValues(SFValuenf writing, SFValuenf reading)
			throws SFArrayElementException {
		writing.set(reading);
	}
	
	@Override
	protected SFValuenf generateGenericElement() {
		return new SFValuenf(n);
	}

}
