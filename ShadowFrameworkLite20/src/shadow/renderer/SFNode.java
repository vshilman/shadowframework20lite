package shadow.renderer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import shadow.geometry.SFGeometry;
import shadow.material.SFLightStep;
import shadow.material.SFStructureReference;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFProgram;
import shadow.system.data.SFDataset;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;


public class SFNode implements SFDataset{

	private ArrayList<SFStructureReference> material=new ArrayList<SFStructureReference>();//any node has a material. sons nodes may inherit material
	private SFGeometry rootGeometry;//geometry which is going to be rendered
	
	private ArrayList<SFProgram> programs=new ArrayList<SFProgram>();
	
	private SFNode parentNode;
	
	private ArrayList<SFNode> nodes=new ArrayList<SFNode>();
	
	public float x,y,z;
	
	public void addNode(SFNode node){
		nodes.add(node);
	}
	
	public List<SFNode> getSonsNodes(){
		return nodes;
	}
	
	@Override
	public String getCode() {
		throw new RuntimeException("not implemented");
	}
	@Override
	public void readFromStream(SFInputStream stream) {
		throw new RuntimeException("not implemented");
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		throw new RuntimeException("not implemented");
	}

	public List<SFStructureReference> getMaterials() {
		return material;
	}

	public void addMaterial(SFStructureReference material) {
		this.material.add(material);
	}

	public SFGeometry getRootGeometry() {
		return rootGeometry;
	}

	public void setRootGeometry(SFGeometry rootGeometry) {
		this.rootGeometry = rootGeometry;
	}

	
	@Override
	public SFDataset generateNewDatasetInstance() {
		return new SFNode();
	}
	
	public SFProgram getProgram(int index,SFLightStep step){
		if(programs.size()>index){
			return programs.get(index);
		}
		
		
		String[] materialsName=new String[material.size()];
		for (int i=0; i < materialsName.length; i++) {
			materialsName[i]=material.get(i).getStructure().getName();
		}
		
		//TODO: its missing its Primitive.
		SFProgram program=SFPipeline.getStaticProgram(rootGeometry.getPrimitive()
				,materialsName,step.getProgramName());
		programs.add(program);
		
		
		return program;
	}
	
	public void cleanPrograms(){
		programs.clear();
		for (Iterator<SFNode> iterator=nodes.iterator(); iterator.hasNext();) {
			SFNode node=(SFNode) iterator.next();
			node.cleanPrograms();
		}
	}
}

