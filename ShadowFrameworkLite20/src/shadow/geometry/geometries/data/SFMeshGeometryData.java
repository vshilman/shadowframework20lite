package shadow.geometry.geometries.data;

import shadow.geometry.SFGeometry;
import shadow.geometry.geometries.SFMeshGeometry;
import shadow.pipeline.SFPrimitiveArray;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFLibraryReference;
import shadow.renderer.data.SFPrimitiveArrayData;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFShort;

public class SFMeshGeometryData extends SFDataAsset<SFGeometry> {

	private SFLibraryReference<SFPrimitiveArray> linesData=new SFLibraryReference<SFPrimitiveArray>(null);
	private SFShort firstElement=new SFShort((short)0);
	private SFShort lastElement=new SFShort((short)0);
	
	public SFMeshGeometryData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("linesData", linesData);
		parameters.addObject("firstElement", firstElement);
		parameters.addObject("lastElement", lastElement);
		setData(parameters);
	}
	
	@Override
	protected SFGeometry buildResource() {
		final SFMeshGeometry geometry=new SFMeshGeometry();

		linesData.retrieveDataset(new SFDataCenterListener<SFDataAsset<SFPrimitiveArray>>() {
			@Override
			public void onDatasetAvailable(String name,
					SFDataAsset<SFPrimitiveArray> dataset) {
				
				SFPrimitiveArray array=dataset.getResource();
				geometry.setArray(array);
				geometry.setPrimitive(array.getPrimitive());

				geometry.getMesh().evaluateRanges();
				
			}
		});
		geometry.setFirstElement(firstElement.getShortValue());
		geometry.setLastElement(lastElement.getShortValue());
		
		return geometry;
	}
	
	public void setupGeometry(SFPrimitiveArray array,String primitive,int firstElement,int lastElement){
		SFPrimitiveArrayData sfPrimitiveArrayData=new SFPrimitiveArrayData();
		sfPrimitiveArrayData.setPrimitive(primitive);
		sfPrimitiveArrayData.setArray(array);
		this.linesData.setDataset(sfPrimitiveArrayData);
		this.firstElement.setShortValue((short)firstElement);
		this.lastElement.setShortValue((short)lastElement);
	}
	
	public void setupGeometry(String array,int firstElement,int lastElement){
		this.linesData.setReference(array);
		this.firstElement.setShortValue((short)firstElement);
		this.lastElement.setShortValue((short)lastElement);
	}
}
