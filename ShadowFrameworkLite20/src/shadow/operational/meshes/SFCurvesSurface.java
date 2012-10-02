package shadow.operational.meshes;

import java.util.ArrayList;
import java.util.List;

public class SFCurvesSurface {
	
	private List<SFMeshVertex> vertices=new ArrayList<SFMeshVertex>();
	private List<SFMeshEdge> edges=new ArrayList<SFMeshEdge>();
	private List<SFMeshPatch> patches=new ArrayList<SFMeshPatch>();
	
	public SFCurvesSurface() {
		super();
	}
	
	public List<SFMeshVertex> getVertices() {
		return vertices;
	}
	public List<SFMeshEdge> getEdges() {
		return edges;
	}
	
	public List<SFMeshPatch> getPatches() {
		return patches;
	}

	public void findPatches(){

		if(patches.size()==0){
			
			SFIncidenceMatrix matrix=new SFIncidenceMatrix(vertices.size());
			for (int i = 0; i < edges.size(); i++) {
				matrix.addRelationShip(edges.get(i).getEdgeConnectionIndex1(), edges.get(i).getEdgeConnectionIndex2());
			}
			
			ArrayList<SFMeshPatch> meshes=matrix.generateMeshes(40);	
			this.patches.addAll(meshes);

		}
	}
	
	public int totalEdgesInPatches(){
		int count=0;
		for (SFMeshPatch graphMesh : patches) {
			count+=graphMesh.getEffectiveIndexSize();
		}
		return count;
	}
}
