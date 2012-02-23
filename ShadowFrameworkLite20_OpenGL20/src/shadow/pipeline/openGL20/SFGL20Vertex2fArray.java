package shadow.pipeline.openGL20;

import shadow.math.SFVertex2f;
import shadow.system.SFArrayElementException;

public class SFGL20Vertex2fArray extends SFGL20ListData<SFVertex2f>{

	@Override
	protected void assignValues(SFVertex2f writing, SFVertex2f reading)
			throws SFArrayElementException {
		writing.set2f(reading);
	}
	
	@Override
	protected SFVertex2f generateGenericElement() {
		return new SFVertex2f(0, 0);
	}
	
}
