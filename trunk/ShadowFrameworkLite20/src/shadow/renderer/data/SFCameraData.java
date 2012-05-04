package shadow.renderer.data;

import shadow.renderer.SFCamera;
import shadow.system.data.SFDataset;
import shadow.system.data.objects.SFFloat;
import shadow.system.data.objects.SFVertex3fData;
import shadow.system.data.utils.SFGenericInfoObjectBuilder;

public class SFCameraData extends SFDataAsset<SFCamera>{

	private SFVertex3fData focus=new SFVertex3fData();
	private SFVertex3fData left=new SFVertex3fData();
	private SFVertex3fData up=new SFVertex3fData();
	private SFVertex3fData dir=new SFVertex3fData();
	private SFFloat distance=new SFFloat(0);
	private SFFloat leftL=new SFFloat(0);
	private SFFloat upL=new SFFloat(0);
	
	public SFCameraData() {
		setData(SFGenericInfoObjectBuilder.generateObjectBuilder(focus,left,up,dir,distance,leftL,upL));
	}
	
	@Override
	public SFDataset generateNewDatasetInstance() {
		return new SFCameraData();
	}
	
	@Override
	protected SFCamera buildResource() {
		return new SFCamera(focus.getVertex3f(), dir.getVertex3f(), left.getVertex3f(),
				up.getVertex3f(), leftL.getFloatValue(), upL.getFloatValue(), distance.getFloatValue());
	}
	
	public void setCamera(SFCamera camera){
		focus.getVertex3f().set(camera.getF());
		left.getVertex3f().set(camera.getLeft());
		dir.getVertex3f().set(camera.getDir());
		up.getVertex3f().set(camera.getUp());
		distance.setFloatValue(camera.getDistance());
		leftL.setFloatValue(camera.getLeftL());
		upL.setFloatValue(camera.getUpL());
	}
}
