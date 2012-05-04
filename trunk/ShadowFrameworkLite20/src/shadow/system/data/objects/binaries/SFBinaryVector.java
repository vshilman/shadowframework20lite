package shadow.system.data.objects.binaries;

import shadow.math.SFVertex3f;
import shadow.system.data.objects.SFBinaryValue;

public class SFBinaryVector<T extends SFVertex3f> extends SFBinaryValue{

	private int ns[];
	private int mask[];
	private int shifts[];
	private float min;
	private float delta;
	private int bitSize=0;
	
	public SFBinaryVector(float min,float max,int... ns){
		this.ns=ns;
		shifts=new int[ns.length];
		mask=new int[ns.length];
		for (int i = 0; i < ns.length; i++) {
			bitSize+=ns[i];
			if(i==0){
				shifts[i]=0;
			}else{
				shifts[i]=shifts[i-1]+ns[i-1];
			}
			mask[i]=(2<<(ns[i]-1))-1;
		}
		delta=max-min;
		this.min=min;
	}
	
	@Override
	public SFBinaryValue clone() {
		SFBinaryVector<SFVertex3f> tmp=new SFBinaryVector<SFVertex3f>(min, 0, ns);
		tmp.delta=delta;
		return tmp;
	}
	
	@Override
	protected int getBitSize() {
		return bitSize;
	}
	
	public void getValue(T write){
		float[] data=write.get();
		for (int i = 0; i < data.length; i++) {
			int value= (this.value >> shifts[i]) & mask[i] ;
			data[i]=min+(delta/mask[i])*( value );
		}
	}
	
	public void setValue(T read){
		float[] data=read.get();
		this.value=0;
		for (int i = 0; i < data.length; i++) {
			int value=(int)((mask[i]*(data[i]-min))/delta);
			int adding=(  (value & mask[i]) << shifts[i]  );
			this.value+=adding;
		}
	}
	
	
	public static void main(String[] args) {

			SFBinaryVector<SFVertex3f> vertexData=new SFBinaryVector<SFVertex3f>(-(float)(Math.PI),(float)(Math.PI), 10,10,10);
			
			SFVertex3f[] vertices={
					new SFVertex3f(0.5f,0.1f,0.3f),
					new SFVertex3f(0.0f,0.0f,0.0f),
					new SFVertex3f(1.0f,1.0f,1.0f),
					new SFVertex3f(0.0f,-0.5181f,0.0f)
			};
			for (SFVertex3f vertex:vertices) {
				vertexData.setValue(vertex);
				vertexData.getValue(vertex);
				System.out.println("|||| "+vertex+" "+vertexData.value);
			}
			
			vertexData.value=-2444034;
			SFVertex3f vertex=new SFVertex3f();
			vertexData.getValue(vertex);
			//System.out.println(vertex);
			vertexData.setValue(vertices[0]);
			vertexData.setValue(vertex);
			vertexData.getValue(vertex);
			//System.out.println(vertex);
	}
}
