package shadow.operational.meshes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class SFIncidenceMatrix {

	private TreeSet<Integer>[] matrix;
	
	@SuppressWarnings("unchecked")
	public SFIncidenceMatrix(int n) {
		this.matrix=(TreeSet<Integer>[])new TreeSet<?>[n];
		for (int i = 0; i < this.matrix.length; i++) {
			this.matrix[i]=new TreeSet<Integer>();
		}
	}
	
	public void addRelationShip(int index1,int index2){
		matrix[index1].add(index2);	
		matrix[index2].add(index1);	
	}
	
	public ArrayList<SFMeshPatch> generateMeshes(int maxSize){
		ArrayList<SFMeshPatch> graphMeshes=new ArrayList<SFMeshPatch>();
		ArrayList<SFMeshPatch> outputMeshes=new ArrayList<SFMeshPatch>();
		
		int n=matrix.length;
		
		SFMeshPatch mesh=new SFMeshPatch(n+1);
		mesh.add(0);
		graphMeshes.add(mesh);
		
		for (int i = 0; i < n; i++) {
			SFMeshPatch[] tempMeshes=new SFMeshPatch[graphMeshes.size()];
			int tempIndex=0;
			for (SFMeshPatch graphMesh : graphMeshes) {
				tempMeshes[tempIndex]=graphMesh;
				tempIndex++;
			}
			graphMeshes.clear();
			
			for (int j = 0; j < tempMeshes.length; j++) {
				int lastIndex=tempMeshes[j].getLast();

				TreeSet<Integer> relatedSet=matrix[lastIndex];
				
				if(relatedSet.size()==0 && tempMeshes[j].getEffectiveIndexSize()>2){
					if(!outputMeshes.contains(tempMeshes[j]))
						outputMeshes.add(tempMeshes[j]);
				}else{
					for (Integer integer : relatedSet) {
						SFMeshPatch tempMesh=tempMeshes[j].clone();
						int index=tempMesh.indexof(integer);
						if(index==-1){
							tempMesh.add(integer);
							graphMeshes.add(tempMesh);
						}else{
							if(index<tempMesh.getEffectiveIndexSize()-2){
								tempMesh.cut(index);
								if(!outputMeshes.contains(tempMesh))
									outputMeshes.add(tempMesh);
							}
						}
					}	
				}
				
			}
			
		}
		
		Collections.sort(outputMeshes);
		
		//Another try
		for (int i = 0; i < outputMeshes.size(); i++) {
			SFMeshPatch graphMesh = outputMeshes.get(i);
			boolean remove=false;
			
			if(graphMesh.getEffectiveIndexSize()<=maxSize){
				
				int size=graphMesh.getEffectiveIndexSize();
				
				for (int k1 = i+1; k1 < outputMeshes.size(); k1++) {
					for (int k2 = k1+1; k2 < outputMeshes.size(); k2++) {
						boolean doRemove=true;
						
						for (int j = 0; j < size; j++) {
							int index1=graphMesh.get(j);
							int index2=graphMesh.get(j+1);
						
							if(!outputMeshes.get(k1).hasEdge(index1, index2) && !outputMeshes.get(k2).hasEdge(index1, index2)){
								doRemove=false;
							}
						}
						if(doRemove){
							remove=true;
							k1=outputMeshes.size();
							k2=outputMeshes.size();
						}	
					}
				}

			}else{
				remove=true;
			}
			
			if(remove){
				outputMeshes.remove(graphMesh);
				i--;
			}
		}
		
//		for (GraphMesh graphMesh : outputMeshes) {
//
//			System.out.println("finally.. "+graphMesh);
//		}
		
		return outputMeshes;
	}
}
