package shadow.renderer;

import shadow.system.data.SFDataset;

public abstract class SFAsset implements SFDataset {

	//so, what should an asset 
	
	public abstract void allocateBuffers();
	
	public abstract void deallocateBuffers();
	
	
	/*@Override
	public void readFromStream(SFInputStream stream) {

		int nPrograms = stream.readInt();

		for (int i = 0; i < nPrograms; i++) {
			String programName=stream.readString();
			//SFProgramComponent component=(SFProgramComponent)SFPipeline.getModule(programName);
			programs.add(programName);
		}
		allocateBuffers();
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		
		stream.writeInt(programs.size());
		
		for (Iterator<String> iterator = programs.iterator(); iterator.hasNext();) {
			String programName = (String) iterator.next();
			stream.writeString(programName);
		}
	}*/
	

//	//TODO: this is not ok
//	private void prepareData() {
//		this.structuresData.clear();
//		this.gridsData.clear();
//	}
//	
//	public SFArray<SFValuenf> getStructuresBuffer(int index){
//		return structuresData.get(index);
//	}
	
	
	//TODO: all allocate buffers must be restored... somehow.... somewhere

//	protected void allocateBuffers(SFProgramComponent component) {
//		List<SFPipelineStructureInstance> structures=component.getStructures();
//		for (Iterator<SFPipelineStructureInstance> iterator = structures.iterator(); iterator.hasNext();) {
//			SFPipelineStructureInstance sfPipelineStructureInstance = (SFPipelineStructureInstance) iterator
//					.next();
//			SFPipelineStructure sfStructure=sfPipelineStructureInstance.getStructure();
//			//Ouch! Missing Bounds Data...
//			//TODO: need Bounds and Hint should be read from Array..
//			
//			//TODO: this is going to be more easy...
//			int n=sfStructure.floatSize();
//			SFValuenf min=new SFValuenf(n);
//			SFValuenf max=new SFValuenf(n);
//			min=new SFValuenf(n);
//			max=new SFValuenf(n);
//			float[] minf=new float[n];
//			float[] maxf=new float[n];
//			for(int i=0;i<n;i++){
//				minf[i]=-1000;
//				maxf[i]=-1000;
//			}
//			SFBounds<SFValuenf> bounds=new SFBounds<SFValuenf>(min,max);
//			SFArray<SFValuenf> structureData=SFPipeline.getSingleton().generateStructureData(bounds, 
//					new SFValuenf(sfStructure.floatSize()), 0/*dataHint*/);
//			this.structuresData.add(structureData);
//		}
//		
//		List<SFPipelineGridInstance> grids=component.getGrids();
//		for (Iterator<SFPipelineGridInstance> iterator = grids.iterator(); iterator.hasNext();) {
//			SFPipelineGridInstance sfPipelineGridInstance = (SFPipelineGridInstance) iterator
//					.next();
//			SFPipelineGrid sfGrid=sfPipelineGridInstance.getGrid();
//			//TODO: really this way??
//			short type=component.getShaderCodeLines().iterator().next().getFunction().getType();
//			switch(type){
//				case SFParameteri.GLOBAL_FLOAT2:
//					SFArray<SFVertex2f> gridsData2f=SFPipeline.getSingleton().generateVertices2f(null, 0);
//					this.gridsData.add(gridsData2f);
//				break;
//				case SFParameteri.GLOBAL_FLOAT3:
//					SFArray<SFVertex3f> gridsData3f=SFPipeline.getSingleton().generateVertices3f(null, 0);
//					this.gridsData.add(gridsData3f);
//				break;
//			}
//		}
//	}

	
//	public int allocateStructureData(int structure){
//		SFArray<SFValuenf> array=structuresData.get(structure);
//		int index=array.generateElement();
//		return index;
//	}
//	
//	public void setStructureData(int index,int structure,float[][] data){
//		SFArray<SFValuenf> array=structuresData.get(structure);
//		
//		int size=0;
//		for(int i=0;i<data.length;i++){
//			size+=data[i].length;
//		}
//		
//		float dataEff[]=new float[size];
//		int j=0;
//		for(int i=0;i<data.length;i++){
//			for(int k=0;k<data[i].length;k++){
//				dataEff[j]=data[i][k];
//				j++;
//			}
//		}
//		
//		SFValuenf vnf=new SFValuenf(dataEff.length);
//		vnf.set(dataEff);
//		array.setElement(index,vnf);
//	}
	
}
