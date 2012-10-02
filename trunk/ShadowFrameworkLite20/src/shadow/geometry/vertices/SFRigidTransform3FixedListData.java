package shadow.geometry.vertices;

import shadow.geometry.SFValuesIterator;
import shadow.geometry.SFValuesList;
import shadow.geometry.data.SFFixedFloat16;
import shadow.math.SFTransform3f;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFBinaryDataList;


//TODO : DROP!!!

public class SFRigidTransform3FixedListData extends SFDataAsset<SFValuesList<SFTransform3f>>{

	private SFBinaryDataList<SFFixedFloat16> vertices=
		new SFBinaryDataList<SFFixedFloat16>(new SFFixedFloat16());
	
	public SFRigidTransform3FixedListData(){
		prepare();
	}
	
	private void prepare() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("vertices", vertices);
		setData(parameters);
	}
	
	private class SFBinarySFTransform3fListIterator implements SFValuesIterator<SFTransform3f>{
		private int index=0;
		
		@Override
		public void getNext(SFTransform3f write) {
			float m00=vertices.getDataObject().get(7*index).getFloat();
			float m01=vertices.getDataObject().get(7*index+1).getFloat();
			float m10=vertices.getDataObject().get(7*index+2).getFloat();
			float m11=vertices.getDataObject().get(7*index+3).getFloat();
			float x=vertices.getDataObject().get(7*index+4).getFloat();
			float y=vertices.getDataObject().get(7*index+5).getFloat();
			float z=vertices.getDataObject().get(7*index+6).getFloat();
			writeTransform(write, m00, m01, m10, m11, x, y, z);
			index+=7;
		}
		
		@Override
		public boolean hasNext() {
			return index<vertices.elementsSize();
		}
	}
	
	private class SFBinaryVertex3fList implements SFValuesList<SFTransform3f>{
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
			return vertices.elementsSize()/3;
		}
		@Override
		public void setValue(int index, SFTransform3f read) {
			vertices.getDataObject().get(7*index).setFloat(read.get()[0]);
			vertices.getDataObject().get(7*index+1).setFloat(read.get()[1]);
			vertices.getDataObject().get(7*index+2).setFloat(read.get()[3]);
			vertices.getDataObject().get(7*index+3).setFloat(read.get()[4]);
			vertices.getDataObject().get(7*index+4).setFloat(read.get()[9]);
			vertices.getDataObject().get(7*index+5).setFloat(read.get()[10]);
			vertices.getDataObject().get(7*index+6).setFloat(read.get()[11]);
		}
		@Override
		public int addValue(SFTransform3f read) {
			vertices.getDataObject().add(new SFFixedFloat16(read.get()[0]));
			vertices.getDataObject().add(new SFFixedFloat16(read.get()[1]));
			vertices.getDataObject().add(new SFFixedFloat16(read.get()[3]));
			vertices.getDataObject().add(new SFFixedFloat16(read.get()[4]));
			vertices.getDataObject().add(new SFFixedFloat16(read.get()[9]));
			vertices.getDataObject().add(new SFFixedFloat16(read.get()[10]));
			vertices.getDataObject().add(new SFFixedFloat16(read.get()[11]));
			return getSize();
		}
		@Override
		public void getValue(int index, SFTransform3f write) {
			float m00=vertices.getDataObject().get(7*index).getFloat();
			float m01=vertices.getDataObject().get(7*index+1).getFloat();
			float m10=vertices.getDataObject().get(7*index+2).getFloat();
			float m11=vertices.getDataObject().get(7*index+3).getFloat();
			float x=vertices.getDataObject().get(7*index+4).getFloat();
			float y=vertices.getDataObject().get(7*index+5).getFloat();
			float z=vertices.getDataObject().get(7*index+6).getFloat();
			writeTransform(write, m00, m01, m10, m11, x, y, z);
		}
		
		@Override
		public SFValuesIterator<SFTransform3f> getIterator() {
			return new SFBinarySFTransform3fListIterator();
		}
	}
	
	public void add(SFTransform3f vertex){
		vertices.getDataObject().add(new SFFixedFloat16(vertex.get()[0]));
		vertices.getDataObject().add(new SFFixedFloat16(vertex.get()[1]));
		vertices.getDataObject().add(new SFFixedFloat16(vertex.get()[3]));
		vertices.getDataObject().add(new SFFixedFloat16(vertex.get()[4]));
		vertices.getDataObject().add(new SFFixedFloat16(vertex.get()[9]));
		vertices.getDataObject().add(new SFFixedFloat16(vertex.get()[10]));
		vertices.getDataObject().add(new SFFixedFloat16(vertex.get()[11]));
	}
	
	@Override
	protected SFValuesList<SFTransform3f> buildResource() {
		return new SFBinaryVertex3fList();
	}

	private static void writeTransform(SFTransform3f transform,float m00,float m01,float m10,float m11,
			float x,float y,float z){
		float m02=(float)(Math.sqrt(1-m00*m00-m01*m01));
		float m12=(float)(Math.sqrt(1-m01*m10-m11*m11));
		
		//m10*m10+m11*m11+m12*m12=1 unity
		//m00*m10+m01*m11+m02*m12=0 orthogonality
		
		float m20=m01*m12-m11*m02;
		float m21=m02*m10-m12*m00;
		float m22=m00*m11-m10*m01;
		
		float[] values=transform.get();
		values[0]=m00;
		values[1]=m10;
		values[2]=m20;
		values[3]=m01;
		values[4]=m11;
		values[5]=m21;
		values[6]=m02;
		values[7]=m12;
		values[8]=m22;
		values[9]=x;
		values[10]=y;
		values[11]=z;
	}
}

