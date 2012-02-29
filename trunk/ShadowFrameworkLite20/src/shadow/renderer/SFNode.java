package shadow.renderer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import shadow.geometry.SFGeometry;
import shadow.material.SFLightStep;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFProgram;
import shadow.renderer.data.SFStructureReference;
import shadow.system.data.SFDataset;
import shadow.system.data.objects.SFCompositeDataArray;
import shadow.system.data.objects.SFDataList;
import shadow.system.data.objects.SFDatasetObject;
import shadow.system.data.objects.SFLibrayReference;
import shadow.system.data.objects.SFString;
import shadow.system.data.objects.SFVertex3fData;

public class SFNode extends SFAsset implements Iterable<SFNode>{

	//private ArrayList<SFProgramStructureReference> material=new ArrayList<SFProgramStructureReference>();//any node has a material. sons nodes may inherit material
	
	private ArrayList<SFProgram> programs=new ArrayList<SFProgram>();
	
	private class SFNodeData extends SFCompositeDataArray {
		private SFVertex3fData position;
		private SFLibrayReference rootGeometryReference;
		private SFDataList<SFDatasetObject> nodes;
		private SFDataList<SFString> materialsProgramComponents;
		private SFDataList<SFStructureReference> materialsStructures;
		
		@Override
		public void generateData() {
			position=new SFVertex3fData();
			addDataObject(position);
			rootGeometryReference=new SFLibrayReference();
			addDataObject(rootGeometryReference);
			nodes=new SFDataList<SFDatasetObject>(new SFDatasetObject(null));
			addDataObject(nodes);
			materialsProgramComponents=new SFDataList<SFString>(new SFString(""));
			addDataObject(materialsProgramComponents);
			materialsStructures=new SFDataList<SFStructureReference>(new SFStructureReference());
			addDataObject(materialsStructures);
		}
		
		@Override
		public SFNodeData clone() {
			return new SFNodeData();
		}
	}
	
	private class SFNodeIterator implements Iterator<SFNode>{
		int index=0;
		
		@Override
		public boolean hasNext() {
			return index<data.nodes.size();
		}
		
		@Override
		public SFNode next() {
			if(!hasNext())
				throw new NoSuchElementException();
			SFNode node=(SFNode)(data.nodes.get(index).getDataset());
			index++;
			return node;
		}
		@Override
		public void remove() {
			if(index>0){
				index--;
				data.nodes.getDataObject().remove(index);
			}
			throw new IllegalStateException();
		}
	}
	
	private SFNodeData data=new SFNodeData();
	
	public SFNode(){
		super.setData(data);
	}
	
	public void addNode(SFNode node){
		data.nodes.add(new SFDatasetObject(node));
	}
	
	public List<SFString> getMaterialsComponents() {
		return data.materialsProgramComponents;
	}
	
	public List<SFStructureReference> getMaterialsStructures() {
		return data.materialsStructures;
	}

	public void addMaterial(SFProgramStructureReference material) {
		this.data.materialsProgramComponents.add(new SFString(material.getProgramName()));
		this.data.materialsStructures.add(material.getStructure());
	}

	public SFGeometry getRootGeometry() {
		return (SFGeometry)(data.rootGeometryReference.retrieveDataset());
	}
	
	public boolean isDrawable(){
		return this.getRootGeometry()!=null;
	}

	public void setGeometryReference(String name){
		data.rootGeometryReference.setReference(name);
	}

	public SFVertex3f getPosition() {
		return data.position.getVertex3f();
	}

	public void setRootGeometry(SFGeometry geometry){
		data.rootGeometryReference.setDataset(geometry);
	}
	
	@Override
	public SFDataset generateNewDatasetInstance() {
		return new SFNode();
	}
	
	public SFProgram getProgram(int index,SFLightStep step){
		
		if(programs.size()>index){
			return programs.get(index);
		}
		
		String[] materialsName=new String[data.materialsProgramComponents.size()];
		for (int i=0; i < materialsName.length; i++) {
			materialsName[i]=data.materialsProgramComponents.get(i).getLabel();
		}
		
		//TODO: its missing its Primitive.
		SFProgram program=SFPipeline.getStaticProgram(getRootGeometry().getPrimitive()
				,materialsName,step.getProgramName());
		programs.add(program);
		
		return program;
	}

	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
	public void cleanPrograms(){
		programs.clear();
		for (Iterator<SFNode> iterator=iterator(); iterator.hasNext();) {
			SFNode node=(SFNode) iterator.next();
			node.cleanPrograms();
		}
	}
	
	@Override
	public Iterator<SFNode> iterator() {
		return new SFNodeIterator();
	}

}

