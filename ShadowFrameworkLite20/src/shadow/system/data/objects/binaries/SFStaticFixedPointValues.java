package shadow.system.data.objects.binaries;

public class SFStaticFixedPointValues{

	private static final int VALUES_LQ=251;
	private static final int VALUES_MQ=1001;
	private static final int VALUES_HQ=4001;
	
	private static final SFStaticFixedPointValues valuesLQ=new SFStaticFixedPointValues(VALUES_LQ);
	private static final SFStaticFixedPointValues valuesMQ=new SFStaticFixedPointValues(VALUES_MQ);
	private static final SFStaticFixedPointValues valuesHQ=new SFStaticFixedPointValues(VALUES_HQ);

	private float step;
	private int N;
	

	public static SFStaticFixedPointValues getValueslq() {
		return valuesLQ;
	}

	public static SFStaticFixedPointValues getValuesmq() {
		return valuesMQ;
	}

	public static SFStaticFixedPointValues getValueshq() {
		return valuesHQ;
	}

	public SFStaticFixedPointValues(int n) {
		super();
		N = n;
		this.step = 1.0f/(N-1);
	}
	
	public int getIndex(float f){
		return (int)(f/step);
	}
	
	public float getValue(int index){
		return (float)(step*index);
	}
}
