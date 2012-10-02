package shadow.pipeline;

public enum SFGridModel{
	Triangle(3,3){
		@Override
		public int getGridSize(int n) {
			return ((n+1)*n)>>1;
		}
	},
	Quad(4,4){
		@Override
		public int getGridSize(int n) {
			return n*n;
		}
	},
	Line(1,2){
		@Override
		public int getGridSize(int n) {
			return n;
		}
	};
	private int edges=0;
	private int corners=0;
	
	private SFGridModel(int edges,int corners) {
		this.edges = edges;
		this.corners=corners;
	}
	
	public int getEdges() {
		return edges;
	}
	
	public int getCorners() {
		return corners;
	}

	public abstract int getGridSize(int n);
}

