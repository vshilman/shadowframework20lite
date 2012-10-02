package shadow.geometry.geometries.data;

import shadow.geometry.SFGeometry;
import shadow.geometry.geometries.SFPointsCloud;
import shadow.pipeline.SFPrimitiveArray;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFLibraryReference;
import shadow.renderer.data.SFPrimitiveArrayData;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFShort;

public class SFPointsCloudData extends SFDataAsset<SFGeometry> {

	private SFLibraryReference<SFPrimitiveArray> primitiveData=new SFLibraryReference<SFPrimitiveArray>(null);
	private SFShort firstPoint=new SFShort((short)0);
	private SFShort pointsSize=new SFShort((short)0);
	
	public SFPointsCloudData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("primitiveData", primitiveData);
		parameters.addObject("firstPoint", firstPoint);
		parameters.addObject("pointsSize", pointsSize);
		setData(parameters);
	}
	
	@Override
	protected SFGeometry buildResource() {
		final SFPointsCloud geometry=new SFPointsCloud();

		primitiveData.retrieveDataset(new SFDataCenterListener<SFDataAsset<SFPrimitiveArray>>() {
			@Override
			public void onDatasetAvailable(String name,
					SFDataAsset<SFPrimitiveArray> dataset) {
				
				SFPrimitiveArray array=dataset.getResource();
				geometry.setArray(array);
				geometry.setPrimitive(array.getPrimitive());
				
			}
		});
		geometry.setFirstPoint(firstPoint.getShortValue());
		geometry.setPointsSize(pointsSize.getShortValue());
		
		return geometry;
	}
	
	public void setupGeometry(SFPrimitiveArray array,String primitive,int firstPoint,int pointsSize){
		SFPrimitiveArrayData sfPrimitiveArrayData=new SFPrimitiveArrayData();
		sfPrimitiveArrayData.setPrimitive(primitive);
		sfPrimitiveArrayData.setArray(array);
		this.primitiveData.setDataset(sfPrimitiveArrayData);
		this.firstPoint.setShortValue((short)firstPoint);
		this.pointsSize.setShortValue((short)pointsSize);
	}
	
	public void setupGeometry(String array,int firstElement,int pointsSize){
		this.primitiveData.setReference(array);
		this.firstPoint.setShortValue((short)firstElement);
		this.pointsSize.setShortValue((short)pointsSize);
	}
}
