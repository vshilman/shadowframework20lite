package shadow.image.bitmaps;


public enum SFBasicVoronoiModels implements SFIVoronoiModel{
	MODEL1{
		@Override
		public float getValue(int index,int index2,float distance,float distance2,
				float normalizedDistance,float normalizedDistance2) {
			return index2*0.05f;
		}
	},MODEL2{
		@Override
		public float getValue(int index,int index2,float distance,float distance2,
				float normalizedDistance,float normalizedDistance2) {
			float T=distance*4;
			return T*T*T*T;
		}
	},MODEL3{
		@Override
		public float getValue(int index,int index2,float distance,float distance2,
				float normalizedDistance,float normalizedDistance2) {
			float T=distance/distance2;
			return T;
		}
	},MODEL4{
		@Override
		public float getValue(int index,int index2,float distance,float distance2,
				float normalizedDistance,float normalizedDistance2) {
			/* Length = d1+d2
			 * Lengthm = (d1+d2)*0.5f
			 * Delta = Lengthm-distance
			 */
			float distanceM=0.5f*(distance+distance2);
			float delta=0.01f;
			if(distanceM-distance>delta){
				return 1;
			}
			
			return 0;
		}
	},MODEL5{
		@Override
		public float getValue(int index,int index2,float distance,float distance2,
				float normalizedDistance,float normalizedDistance2) {

			/* Length = d1+d2
			 * Lengthm = (d1+d2)*0.5f
			 * Delta = Lengthm-distance
			 */
			float distanceM=0.5f*(normalizedDistance+normalizedDistance2);
			float delta=0.01f;
			if(distanceM-normalizedDistance>delta){
				return 1;
			}
			
			return 0;
		}
	},MODEL6{
		@Override
		public float getValue(int index,int index2,float distance,float distance2,
				float normalizedDistance,float normalizedDistance2) {
			return normalizedDistance*4;
		}
	},MODEL7{
		@Override
		public float getValue(int index,int index2,float distance,float distance2,
				float normalizedDistance,float normalizedDistance2) {
			float T=distance/distance2;
			return 1-T*T*T*T*T*T*T;
		}
	};
	
	public static String[] names(){
		String[] names=new String [values().length];
		for (int i = 0; i < names.length; i++) {
			names[i]=values()[i].name();
		}
		return names;
	}
	
	public abstract float getValue(int index,int index2,float distance,float distance2,
			float normalizedDistance,float normalizedDistance2);

	@Override
	public void destroy() {
		
	}
	
	@Override
	public void init() {
		
	}

}
