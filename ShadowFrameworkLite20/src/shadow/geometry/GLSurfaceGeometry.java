package shadow.geometry;

import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;

/**
 * 
 * @author Alessandro Martinelli
 */
public abstract class GLSurfaceGeometry extends SFGeometry{

	/**/
	public abstract void build();
	
	@Override
	public void compile() {
		//TODO
		//Create
		build();
	}
	
	@Override
	public void drawGeometry(int lod) {
		//TODO: Call Rendering Kernel and RENDER!
		//SFRenderingPipeline.drawPrimitives(this);
	}
	
	
	@Override
	public void readFromStream(SFInputStream stream){
		throw new RuntimeException("GLSurfaceGeometry readFromStream!! ");
		// TODO Auto-generated method stub
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream){
		throw new RuntimeException("GLSurfaceGeometry writeOnStream!! ");
		// TODO Auto-generated method stub
	}
}
