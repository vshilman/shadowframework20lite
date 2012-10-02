package shadow.renderer.data;

import java.util.StringTokenizer;

import shadow.geometry.data.SFFixedFloat16;
import shadow.renderer.SFAbstractReferenceNode;
import shadow.renderer.SFNode;
import shadow.renderer.SFTilesSpace;
import shadow.system.data.SFDataCenterListener;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.SFWritableDataObject;
import shadow.system.data.objects.SFBinaryObject;
import shadow.system.data.objects.SFCompositeDataArray;
import shadow.system.data.objects.SFIntShortField;

public class SFTilesSpaceData extends SfAbstractReferenceNodeData{

	private SFIntShortField space=new SFIntShortField(0);
	private SFDataObjectsList<TiledElementData> tiledElements=new SFDataObjectsList<TiledElementData>(
			new TiledElementData());
	
	public SFTilesSpaceData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("transform", transform);
		parameters.addObject("nodes", nodes);
		parameters.addObject("space", space);
		parameters.addObject("tiledElements", tiledElements);
		setData(parameters);
	}
	
	@Override
	protected SFNode buildResource() {
		final SFTilesSpace tiledNode=(SFTilesSpace)(super.buildResource());
		
		for (int i = 0; i < tiledElements.getSize(); i++) {
			final TiledElementData element=tiledElements.get(i);
			element.getReference().retrieveDataset(new SFDataCenterListener<SFDataAsset<SFNode>>() {
				@Override
				public void onDatasetAvailable(String name, SFDataAsset<SFNode> dataset) {
					SFNode node=dataset.getResource();
					tiledNode.addNode(node, element.getX(), element.getY(), element.getZ());
				}
			});
		}
		
		return tiledNode;
	}
	
	@Override
	protected SFAbstractReferenceNode generateReferenceNode() {
		return new SFTilesSpace(space.getShort(0),space.getShort(1));
	}
	
	public void setSpace(int x,int y){
		space.setShort(0, x);
		space.setShort(1, y);
	}
	
	public void addElement(int x,int y,float z,String reference){
		TiledElementData tile=new TiledElementData();
		tile.setup(x, y, z, reference);
		tiledElements.add(tile);
	}
	
	private class TiledElementData extends SFCompositeDataArray implements SFWritableDataObject{
		private SFIntShortField location;
		private SFBinaryObject<SFFixedFloat16> zed;
		private SFLibraryReference<SFNode> reference;
		@Override
		public void generateData() {
			location=new SFIntShortField(0);
			zed=new SFBinaryObject<SFFixedFloat16>(new SFFixedFloat16());
			reference=new SFLibraryReference<SFNode>();
			addDataObject(location);
			addDataObject(zed);
			addDataObject(reference);
		}
		
		public void setup(int x,int y,float z,String reference){
			location.setShort(0, x);
			location.setShort(1, y);
			zed.getBinaryValue().setFloat(z);
			this.reference.setReference(reference);
		}
		
		public int getX(){
			return location.getShort(0);
		}
		
		public int getY(){
			return location.getShort(1);
		}
		
		public float getZ(){
			return zed.getBinaryValue().getFloat();
		}
		
		public SFLibraryReference<SFNode> getReference() {
			return reference;
		}
		
		@Override
		public void setStringValue(String value) {
			StringTokenizer tokenizer=new StringTokenizer("","(,)");
			setup(new Short(tokenizer.nextToken()), new Short(tokenizer.nextToken()), 
					new Float(tokenizer.nextToken()), tokenizer.nextToken());
		}
		
		@Override
		public String toStringValue() {
			return "("+getX()+","+getY()+","+getZ()+","+reference.getReference()+")";
		}
		
		@Override
		public SFCompositeDataArray clone() {
			return new TiledElementData();
		}
	}
}
