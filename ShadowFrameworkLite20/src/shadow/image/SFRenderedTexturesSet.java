package shadow.image;

import shadow.system.SFInitiable;

public interface SFRenderedTexturesSet extends SFInitiable{

	public void apply(int index,int level) throws ArrayIndexOutOfBoundsException;

	public int getTextureSize();
}
