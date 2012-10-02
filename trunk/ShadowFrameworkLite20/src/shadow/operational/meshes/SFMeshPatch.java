package shadow.operational.meshes;

import java.util.Arrays;

public class SFMeshPatch implements Comparable<SFMeshPatch>{
	private int[] indices;
	private int effectiveIndexSize;
	
	public SFMeshPatch(int maxIndices){
		this.indices=new int[maxIndices];
		this.effectiveIndexSize=0;
	}
	
	@Override
	public SFMeshPatch clone(){
		SFMeshPatch mesh=new SFMeshPatch(indices.length);
		for(int i=0;i<effectiveIndexSize;i++){
			mesh.add(indices[i]);
		}
		return mesh;
	}
	
	
	public void cut(int index) {
		for (int i = 0; i < effectiveIndexSize-index; i++) {
			indices[i]=indices[index+i];
		}
		effectiveIndexSize-=index;
	}
	
	public int get(int index){
		return indices[roundIndex(index)];
	}
	
	public int getEffectiveIndexSize() {
		return effectiveIndexSize;
	}
	
	public void add(int... indices){
		for (int i = 0; i < indices.length; i++) {
			add(indices[i]);
		}
	}

	public void add(int index){
		indices[effectiveIndexSize]=index;
		effectiveIndexSize++;
	}
	
	public int indexof(int index){
		for (int i = 0; i < effectiveIndexSize; i++) {
			if(indices[i]==index)
				return i;
		}
		return -1;
	}

	public int getFirst() {
		return indices[0];
	}
	
	public int getLast() {
		return indices[effectiveIndexSize-1];
	}

	public int roundIndex(int index){
		while(index>=effectiveIndexSize)
			index-=effectiveIndexSize;
		while(index<0)
			index+=effectiveIndexSize;
		return index;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof SFMeshPatch){
			SFMeshPatch mesh=(SFMeshPatch)obj;
			if(mesh.effectiveIndexSize!=effectiveIndexSize)
				return false;
			
			for (int i = 0; i <effectiveIndexSize; i++) {
				if(indices[i]==mesh.indices[0]){
					boolean doneFirst=true;
					for (int j = 1; j <effectiveIndexSize; j++) {
						if(indices[roundIndex(i+j)]!=mesh.indices[j]){
							doneFirst=false;
						}
					}
					boolean doneSecond=true;
					for (int j = 1; j <effectiveIndexSize; j++) {
						if(indices[roundIndex(i-j)]!=mesh.indices[j]){
							doneSecond=false;
						}
					}
					return doneFirst || doneSecond;
				}
			}
			
			return false;
			
		}
		
		return super.equals(obj);
	}
	
	/**
	 * Verify if the two geometries have an arc in common
	 * @param mesh
	 * @return
	 */
	public boolean commonArc(SFMeshPatch mesh){
		for (int i = 0; i <effectiveIndexSize; i++) {
			for (int j = 0; j <mesh.effectiveIndexSize; j++) {
				if(indices[i]==mesh.indices[j]){
					if(indices[roundIndex(i+1)]==mesh.indices[mesh.roundIndex(j+1)]){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void revertOrder(){
		
		for (int i = 0; i < effectiveIndexSize-1-i; i++) {
			int temp=indices[i];
			indices[i]=indices[effectiveIndexSize-1-i];
			indices[effectiveIndexSize-1-i]=temp;
		}
	}
	
	
	@Override
	public int compareTo(SFMeshPatch o) {
		return -((Integer)this.effectiveIndexSize).compareTo(o.effectiveIndexSize);
	}
	
	public static SFMeshPatch mixMeshes(SFMeshPatch mesh1,SFMeshPatch mesh2){
		int index1=-1;
		int index2=-1;
		int size=0;
		for (int i = 0; i <mesh1.effectiveIndexSize; i++) {
			for (int j = 0; j <mesh2.effectiveIndexSize; j++) {
				if(mesh1.indices[i]==mesh2.indices[j]){
					index1=i;
					index2=j;
					size=1;
					for (int k = 1; k < mesh1.effectiveIndexSize && k<mesh2.effectiveIndexSize; k++) {
						if(mesh1.indices[mesh1.roundIndex(i+k)]==mesh2.indices[mesh2.roundIndex(j+k)]){
							size++;
						}
					}
					if(size==1){//try the other side
						size=-1;
						for (int k = 1; k < mesh1.effectiveIndexSize && k<mesh2.effectiveIndexSize; k++) {
							if(mesh1.indices[mesh1.roundIndex(i+k)]==mesh2.indices[mesh2.roundIndex(j-k)]){
								size--;
							}
						}
					}
					if(size>1 || size<-1){
						i=mesh1.effectiveIndexSize;
						j=mesh2.effectiveIndexSize;
						//this is the end...
					}
					/*if(indices[roundIndex(i+1)]==mesh.indices[mesh.roundIndex(j+1)]){
						return true;
					}*/
				}
			}
		}
		
		/*if(size==mesh1.effectiveIndexSize || size==mesh2.effectiveIndexSize){
			//mix should not exist in this case
			return null;
		}*else */if(size>=-1 && size<=1){
			return null;
		}else if(size<-1){
			SFMeshPatch mesh=new SFMeshPatch(mesh1.effectiveIndexSize+mesh2.effectiveIndexSize);

			size=-size;
			for(int i=0;i<mesh2.effectiveIndexSize-(size-2);i++){
				mesh.add(mesh2.indices[mesh2.roundIndex(index2+size-i)]);
				//mesh.add(mesh2.indices[mesh2.roundIndex(index2-i)]);
			}
			
			for(int i=0;i<mesh1.effectiveIndexSize-(size-2)-2;i++){
				mesh.add(mesh1.indices[mesh1.roundIndex(index1-i-1)]);
			}
			
			return mesh;
			
		}else if(size>1){
			SFMeshPatch mesh=new SFMeshPatch(mesh1.effectiveIndexSize+mesh2.effectiveIndexSize);
			
			for(int i=0;i<mesh2.effectiveIndexSize-(size-2);i++){
				mesh.add(mesh2.indices[mesh2.roundIndex(index2-i)]);
			}
			
			for(int i=0;i<mesh1.effectiveIndexSize-(size-2)-2;i++){
				mesh.add(mesh1.indices[mesh1.roundIndex(index1+size+i)]);
			}
			
			return mesh;
		}
		return null;
		
	}
	
	public boolean hasEdge(int index1,int index2){
		for (int i = 0; i < effectiveIndexSize; i++) {
			if(indices[i]==index1 && indices[roundIndex(i+1)]==index2){
				return true;
			}
			if(indices[i]==index2 && indices[roundIndex(i+1)]==index1){
				return true;
			}
		}
		return false;
	}
	
	
	public SFMeshPatch[] generateBreak(int index,int size){
		SFMeshPatch mesh1=new SFMeshPatch(this.indices.length);
		SFMeshPatch mesh2=new SFMeshPatch(this.indices.length);
		for (int i = -1; i < size+1; i++) {
			mesh1.add(indices[roundIndex(index+i)]);
		}
		for (int i = size; i < this.effectiveIndexSize; i++) {
			mesh2.add(indices[roundIndex(index+i)]);
		}
		SFMeshPatch[] meshes={mesh1,mesh2};
		return meshes;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(Arrays.copyOf(indices, effectiveIndexSize));
	}
}