package shadow.image.bitmaps;

import shadow.math.SFVertex2f;

public enum SFBasicVoronoiDistances implements SFVoronoiDistance{
	DISTANCE{
		@Override
		public float distance(float u, float v, SFVertex2f vertex) {
			float du=vertex.getX()-u;
			float dv=vertex.getY()-v;
			return (float)(Math.sqrt(du*du+dv*dv));
		}
	},DISTANCE2{
		@Override
		public float distance(float u, float v, SFVertex2f vertex) {
			float du=vertex.getX()-u;
			float dv=vertex.getY()-v;
			return (du*du+dv*dv);
		}
	},DISTANCE1{
		@Override
		public float distance(float u, float v, SFVertex2f vertex) {
			float du=vertex.getX()-u;
			float dv=vertex.getY()-v;
			return (float)(Math.abs(du+dv));
		}
	};
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public abstract float distance(float u, float v, SFVertex2f vertex);
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	public static String[] names(){
		String[] names=new String [values().length];
		for (int i = 0; i < names.length; i++) {
			names[i]=values()[i].name();
		}
		return names;
	}
}
