package shadow.pipeline;

public class SFPipelineGrid extends SFPipelineStructure{
	
	protected String[] names=new String[0];
	protected short[] corners=new short[0];
	protected short[][] edges=new short[0][];
	protected short[][] paths=new short[0][];
	protected SFFunction[] functions;

	public String[] getNames() {
		return names;
	}
	
	public int getNameIndex(String name){
		
		for (int i = 0; i < names.length; i++) {
			if(name.equalsIgnoreCase(names[i]))
				return i;
		}
		return -1;
	}

	public int getGridSize(){
		return names.length;
	}
	
	public void setNames(String[] names) {
		this.names = names;
	}

	public short[] getCorners() {
		return corners;
	}

	public void setCorners(short[] corners) {
		this.corners = corners;
	}

	public short[][] getEdges() {
		return edges;
	}

	public void setEdges(short[][] edges) {
		this.edges = edges;
	}

	public short[][] getPaths() {
		return paths;
	}

	public void setPaths(short[][] paths) {
		this.paths = paths;
	}

	public int size(){
		return names.length;
	}

	public SFFunction[] getFunctions() {
		return functions;
	}


}
