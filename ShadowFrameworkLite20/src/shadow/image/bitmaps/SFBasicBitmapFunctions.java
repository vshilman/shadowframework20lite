package shadow.image.bitmaps;

public enum SFBasicBitmapFunctions implements SFBitmapFunction{
	MATRIX{
		@Override
		public float getValue(float u, float v) {
			float U=4*u;
			float V=v;
			int row=(int)V;
			V=V-(row);
			U=(U+0.8f*row+0.3f*V);
			U=U-((int)U);
			return (1-U)*U*(1-V)*V*(1-U)*U*(1-V)*V*512;
		}
	},
	CIRCLE{
		@Override
		public float getValue(float u, float v) {
			u-=0.5f;
			v-=0.5f;
			return 4*(u*u+v*v);
		}
	},
	SIN{
		@Override
		public float getValue(float u, float v) {
			return (float)((0.5f*Math.sin(2*Math.PI*u)+0.5f)*(0.5f*Math.sin(2*Math.PI*v)+0.5f));
		}
	},
	SUM{
		@Override
		public float getValue(float u, float v) {
			return (float)(u+v);
		}
	};


	public static String[] names(){
		String[] names=new String [values().length];
		for (int i = 0; i < names.length; i++) {
			names[i]=values()[i].name();
		}
		return names;
	}
	
	public abstract float getValue(float u,float v);
	
	@Override
	public void destroy() {
		//nothing to do
	}
	
	@Override
	public void init() {
		//nothing to do
	}
}
