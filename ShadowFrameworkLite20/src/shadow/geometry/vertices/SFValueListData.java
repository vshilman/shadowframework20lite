package shadow.geometry.vertices;

import shadow.geometry.SFValuesIterator;
import shadow.geometry.SFValuesList;
import shadow.math.SFValuenf;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFBinaryVertexList;
import shadow.system.data.objects.SFGenericFixedFloat;

public class SFValueListData<T extends SFGenericFixedFloat> extends SFDataAsset<SFValuesList<SFValuenf>>{

	private SFBinaryVertexList<T> vertices;
	
	public SFValueListData(T t){
		vertices=new SFBinaryVertexList<T>(t);
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		//parameters.addObject("vertexSize", this.vertexSize);
		parameters.addObject("vertices", vertices);
		setData(parameters);
	}
	
	
	public void addVertices(float... data){
		vertices.addVertex(data);
	}
	
	//TODO: is really all this necessary?
	private class SFBinaryVListIterator implements SFValuesIterator<SFValuenf>{
		private int index=0;
		
		@Override
		public void getNext(SFValuenf write) {
			vertices.getValue(index, write);
			index++;
		}
		
		@Override
		public boolean hasNext() {
			return index<vertices.getSize();
		}
	}
	
	//TODO: should be move to its own file
	private class SFBinaryVList implements SFValuesList<SFValuenf>{
		@Override
		public void init() {
			//Nothing special
		}
		@Override
		public void destroy() {
			//Nothing special
		}
		@Override
		public int getSize() {
			return vertices.getSize();
		}
		@Override
		public void setValue(int index, SFValuenf read) {
			//TODO : no, setValue is not required on Viewers!!
//			int vSize=getVertexSize();
//			for (int j = 0; j < vSize; j++) {
//				vertices.getDataObject().get(vSize*index+j).setFloat(read.get()[j]);
//			}
		}
		@Override
		public int addValue(SFValuenf read) {
			vertices.addValue(read);
			return getSize();
		}
		@Override
		public void getValue(int index, SFValuenf write) {
			vertices.getValue(index, write);
		}
		
		@Override
		public SFValuesIterator<SFValuenf> getIterator() {
			return new SFBinaryVListIterator();
		}
	}

	@Override
	protected SFValuesList<SFValuenf> buildResource() {
		return new SFBinaryVList();
	}
}
