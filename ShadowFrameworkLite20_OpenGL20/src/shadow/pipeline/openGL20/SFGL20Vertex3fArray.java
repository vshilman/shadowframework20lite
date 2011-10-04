package shadow.pipeline.openGL20;

import shadow.math.SFVertex3f;
import shadow.pipeline.SFArrayElementException;

public class SFGL20Vertex3fArray extends SFGL20ListData<SFVertex3f>{

	@Override
	protected void assignValues(SFVertex3f writing, SFVertex3f reading)
			throws SFArrayElementException {
		writing.set3f(reading);
	}
	
	@Override
	protected SFVertex3f generateGenericElement() {
		return new SFVertex3f(0, 0, 0);
	}
	
}
