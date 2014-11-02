package shadow.system.data.tools;

import shadow.system.data.java.SFIOExceptionKeeper;

public class DefaultExceptionKeeper implements SFIOExceptionKeeper{
	
	public void launch(java.io.IOException exception) {
		System.err.println(exception);
	}
}
