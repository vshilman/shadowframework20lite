package shadow.geometry.vertices;

import shadow.geometry.SFValuesIterator;
import shadow.geometry.SFValuesList;
import shadow.geometry.curves.data.SFFixedFloat;
import shadow.math.SFValuenf;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.objects.SFBinaryDataList;
import shadow.system.data.objects.SFShort;
import shadow.system.data.utils.SFGenericInfoObjectBuilder;

public class SFVertexFixedListData extends SFDataAsset<SFValuesList<SFValuenf>>{

	private SFShort vertexSize=new SFShort((short)3);
	private SFBinaryDataList<SFFixedFloat> vertices=
		new SFBinaryDataList<SFFixedFloat>(new SFFixedFloat());
	
	public SFVertexFixedListData(){
		setData(SFGenericInfoObjectBuilder.generateObjectBuilder(vertexSize,vertices));
	}
	
	public SFVertexFixedListData(int vertexSize){
		setData(SFGenericInfoObjectBuilder.generateObjectBuilder(this.vertexSize,vertices));
		this.vertexSize.setShortValue((short)vertexSize);
	}
	
	private class SFBinaryVertexListIterator implements SFValuesIterator<SFValuenf>{
		private int index=0;
		
		@Override
		public void getNext(SFValuenf write) {
			int vSize = vertexSize.getShortValue();
			for (int i = 0; i < vSize; i++) {
				write.set(i,vertices.getDataObject().get(index+i).getFloat());	
			}
			index+=vSize;
		}
		
		@Override
		public boolean hasNext() {
			return index<vertices.elementsSize();
		}
	}
	
	private class SFBinaryVertexList implements SFValuesList<SFValuenf>{
		@Override
		public void init() {
			//Nothing special
		}
		@Override
		public int getSize() {
			return vertices.elementsSize()/vertexSize.getShortValue();
		}
		@Override
		public void setValue(int index, SFValuenf read) {
			int vSize=getVertexSize();
			for (int j = 0; j < vSize; j++) {
				vertices.getDataObject().get(vSize*index+j).setFloat(read.get()[j]);
			}
		}
		@Override
		public int addValue(SFValuenf read) {
			for (int i = 0; i < getVertexSize(); i++) {
				vertices.getDataObject().add(new SFFixedFloat(read.get()[i]));
			}
			return getSize();
		}
		@Override
		public void getValue(int index, SFValuenf write) {
			int vSize=getVertexSize();
			for (int i = 0; i < vSize; i++) {
				write.set(i,vertices.getDataObject().get(vSize*index+i).getFloat());
			}
		}
		
		@Override
		public SFValuesIterator<SFValuenf> getIterator() {
			return new SFBinaryVertexListIterator();
		}
	}
	
	public int getVertexSize() {
		return vertexSize.getShortValue();
	}
	
	public void add(SFValuenf vertex){
		for (int i = 0; i < vertex.getSize(); i++) {
			vertices.getDataObject().add(new SFFixedFloat(vertex.get()[i]));
		}
	}
	
	public void add(SFValuenf... vertices){
		int vSize=getVertexSize();
		for (int i = 0; i < vertices.length; i++) {
			SFValuenf vertex=vertices[i];
			for (int j = 0; j < vSize; j++) {
				this.vertices.getDataObject().add(new SFFixedFloat(vertex.get()[j]));
			}
		}
	}
	
	public void add(float... vertices){
		for (int i = 0; i < vertices.length; i++) {
			this.vertices.getDataObject().add(new SFFixedFloat(vertices[i]));
		}
	}
	
	@Override
	protected SFValuesList<SFValuenf> buildResource() {
		return new SFBinaryVertexList();
	}
	
}

