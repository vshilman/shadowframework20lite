package shadow.system.data.tests;

import shadow.system.data.java.SFIOExceptionKeeper;

public class DefaultExceptionKeeper implements SFIOExceptionKeeper{
	
	public void launch(java.io.IOException exception) {
		System.err.println(exception);
	}
}
