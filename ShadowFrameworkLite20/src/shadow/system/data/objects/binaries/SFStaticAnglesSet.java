package shadow.system.data.objects.binaries;

import java.io.PrintStream;

public class SFStaticAnglesSet {

	private static final int LQ_ANGLES_VALUES=64;
	private static final int LQ_ANGLES_BITSIZE=6;
	private static final int LQ_ANGLES_MASK=0x3F;
	
	private static final int MQ_ANGLES_VALUES=256;
	private static final int MQ_ANGLES_BITSIZE=8;
	private static final int MQ_ANGLES_MASK=0xFF;
	
	private static final int HQ_ANGLES_VALUES=1024;
	private static final int HQ_ANGLES_BITSIZE=10;
	private static final int HQ_ANGLES_MASK=0x3FF;
	
	private static final SFStaticAnglesSet anglesLQ=new SFStaticAnglesSet(LQ_ANGLES_VALUES,LQ_ANGLES_BITSIZE,LQ_ANGLES_MASK);
	private static final SFStaticAnglesSet anglesMQ=new SFStaticAnglesSet(MQ_ANGLES_VALUES,MQ_ANGLES_BITSIZE,MQ_ANGLES_MASK);
	private static final SFStaticAnglesSet anglesHQ=new SFStaticAnglesSet(HQ_ANGLES_VALUES,HQ_ANGLES_BITSIZE,HQ_ANGLES_MASK);
	
	private static final float COSINE_PRECISION=0.05f; 
	
	private int size;
	private int bitSize;
	private int mask;
	private double step;
	private final float cosines[];
	
	private SFStaticAnglesSet(int size,int bitSize,int mask) {
		super();
		this.size = size;
		this.bitSize = bitSize;
		this.mask = mask;
		cosines=new float[size+1];
		this.step=Math.PI*0.5f/size;
		for (int j = 0; j <= size; j++) {
			cosines[j]=(float)(Math.cos(j*step));
		}
	}
	
	
	
	public static SFStaticAnglesSet getAngleslq() {
		return anglesLQ;
	}



	public static SFStaticAnglesSet getAngleshq() {
		return anglesHQ;
	}


	public static SFStaticAnglesSet getAnglesmq() {
		return anglesMQ;
	}

	protected float getAngle(int value){
		return (float)(value*step);
	}
	
	protected float getCos(int value){
		
		int cosineIndex=value & mask;
		int cosinePlan=(value>>bitSize & 0x3);
		switch (cosinePlan) {
			case 0: return cosines[cosineIndex];
			case 1: return -cosines[size-cosineIndex];
			case 2: return -cosines[cosineIndex];
			default: return cosines[size-cosineIndex];
		}
	}
	
	protected float getSin(int value){
		return getCos(value-size);
	}
	
	protected int getIndexByAngle(double angle){
		return (int)(angle/step);
	}
	
	/**
	 * Tells if a==b, apart of a precision which is related with this Angles Set 
	 * @param a
	 * @param b
	 * @return
	 */
	public boolean equal(float a,float b){
		return (a-b)*(a-b)<COSINE_PRECISION*COSINE_PRECISION*step*step;
	}
	
	protected int getIndexByTrigonometricValues(double cos,double sin){

		if(cos*cos+sin*sin>1.1f)
			throw new RuntimeException("Bad Angles");
		
		double val=cos;
		int quadrant=0;
		if(sin<0){
			val=-sin;
			quadrant=3;
			if(cos<0){
				val=-cos;
				quadrant=2;
			}	
		}else if(cos<0){
			val=sin;
			quadrant=1;
		}

		int index=size>>1;
		int deltaIndex=size;
		while(deltaIndex>0){
			deltaIndex=deltaIndex>>1;
			if(equal((float)val,cosines[index])){
				deltaIndex=0;
			}else if(val>cosines[index]){
				index-=deltaIndex;
			}else{ 
				index+=deltaIndex; 
			}	
		}
		
		return (quadrant<<bitSize)+index;
	}
	
	public static void main(String[] args) {
		
		PrintStream out=System.out;
		//PrintStream out=new PrintStream(new File("filename"));
		
//		for (int i = 0; i <= 256; i++) {
//			System.out.println("["+i+"]:"+getAngleslq().getCos(i)+" "+getAngleslq().getSin(i));
//		}
		
//		for (int i = 0; i <= 4096; i++) {
//			System.out.println("["+i+"]:"+getAngleshq().getAngle(i)+" "+getAngleshq().getCos(i)+" "+getAngleshq().getSin(i));
//		}

//		for (int i = -256*2; i <= 256*2; i+=32) {
//			out.println("["+i+"]:"+getAngleslq().getCos(i)+" "+getAngleslq().getSin(i));
//		}
		
//		for (int i = 0; i <= 256; i+=16) {
//			float angle=getAngleslq().getAngle(i);
//			float cos=getAngleslq().getCos(i);
//			float sin=getAngleslq().getSin(i);
//			out.println("["+i+"]:"+angle+" "+cos+" "+sin+"");
//			out.println("index? "+getAngleslq().getIndexByAngle(angle)+" "+getAngleslq().getIndexByTrigonometricValues(cos, sin));
//		}
		
		for (int i = 0; i <= 4096; i+=256) {
			float angle=getAngleshq().getAngle(i);
			float cos=getAngleshq().getCos(i);
			float sin=getAngleshq().getSin(i);
			out.println("["+i+"]:"+angle+" "+cos+" "+sin+"");
			out.println("index? "+getAngleshq().getIndexByAngle(angle)+" "+getAngleshq().getIndexByTrigonometricValues(cos, sin));
		}
	}
}
