package shadow.geometry.curves.data;

import shadow.geometry.SFValuesList;
import shadow.geometry.curves.SFControlPointsCurve;
import shadow.math.SFValuenf;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFLibraryReference;
import shadow.system.data.SFNamedParametersObject;
import shadow.system.data.objects.SFShort;

public abstract class SFCurvesVerticesData extends SFDataAsset<SFControlPointsCurve> {

	protected SFLibraryReference<SFValuesList<SFValuenf>> vertices = 
		new SFLibraryReference<SFValuesList<SFValuenf>>();
	protected SFShort closed=new SFShort((short)0);
	
	public SFCurvesVerticesData() {
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("vertices", vertices);
		parameters.addObject("closed", closed);
		setData(parameters);
		setReferences(vertices);
	}
	
	public SFCurvesVerticesData(SFDataAsset<SFValuesList<SFValuenf>> vertices) {
		this.vertices.setDataset(vertices);
	}

	public void setVertices(SFDataAsset<SFValuesList<SFValuenf>> dataset, SFValuenf... vertices) {
		setVertices(dataset);
		for (int i = 0; i < vertices.length; i++) {
			addVertex(vertices[i]);
		}
	}

	public void addVertex(SFValuenf value){
		this.vertices.getDataset().getResource().addValue(value);
	}
	
	public void setClosed(boolean closed){
		this.closed.setShortValue((short)(closed?1:0));
	}
	
	public boolean getClosed(){
		return this.closed.getShortValue()==1;
	}

	public void setVertices(SFDataAsset<SFValuesList<SFValuenf>> vertices) { 
		this.vertices.setDataset(vertices);
	}
	
	public void setVertices(String name) { 
		this.vertices.setReference(name);
	}
	
}