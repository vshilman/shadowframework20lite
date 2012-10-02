package shadow.geometry.geometries.data;

import shadow.geometry.geometries.SFTiledTexCoord;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPrimitive;
import shadow.renderer.SFTilesSpace;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFLibraryReference;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFBinaryArrayObject;
import shadow.system.data.objects.SFIntShortField;
import shadow.system.data.objects.SFString;

public class SFTiledTexCoordData extends SFDataAsset<SFTiledTexCoord>{
	
	private SFIntShortField dimension=new SFIntShortField(0);
	private SFBinaryArrayObject matrix=new SFBinaryArrayObject(1);
	private SFString primitive=new SFString();
	private SFLibraryReference<SFTilesSpace> tilesSpace=new SFLibraryReference<SFTilesSpace>();

	public SFTiledTexCoordData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("primitive", primitive);
		parameters.addObject("dimension", dimension);
		parameters.addObject("tilesSpace", tilesSpace);
		parameters.addObject("matrix", matrix);
		setData(parameters);
	}
	
	public void setMatrix(int[] matrix){
		this.matrix.setBytes(matrix);
	}
	
	public void setPrimitive(String primitive){
		this.primitive.setString(primitive);
	}
	
	public void setDimension(int width,int height){
		dimension.setShort(0, width);
		dimension.setShort(1, height);
	}
	
	public void setTilesSpace(String space){
		tilesSpace.setReference(space);
	}
	
	@Override
	protected SFTiledTexCoord buildResource() {
		final SFTiledTexCoord tileCoord=new SFTiledTexCoord(matrix.getValues(),8,8);
		tilesSpace.retrieveDataset(new SFDataCenterListener<SFDataAsset<SFTilesSpace>>() {
			@Override
			public void onDatasetAvailable(String name,
					SFDataAsset<SFTilesSpace> dataset) {
				tileCoord.setSpace(dataset.getResource().getTilesSet());
			}
		});
		SFPrimitive tilesPrimitive=SFPipeline.getPrimitive(primitive.getString());
		tileCoord.setPrimitive(tilesPrimitive);
		return tileCoord;
	}
}
