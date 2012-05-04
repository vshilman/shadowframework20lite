package shadow.geometry.curves.data;

import shadow.geometry.SFCurve;
import shadow.geometry.SFValuesList;
import shadow.math.SFValuenf;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFLibraryReference;
import shadow.system.data.objects.SFShort;
import shadow.system.data.utils.SFGenericInfoObjectBuilder;

public abstract class SFCurvesVerticesData<T extends SFValuenf> extends SFDataAsset<SFCurve<T>> {

	protected SFLibraryReference<SFDataAsset<SFValuesList<SFValuenf>>> vertices = 
		new SFLibraryReference<SFDataAsset<SFValuesList<SFValuenf>>>();
	protected SFShort closed=new SFShort((short)0);
	
	public SFCurvesVerticesData() {
		setData(SFGenericInfoObjectBuilder.generateObjectBuilder(vertices,closed));
	}
	
	public SFCurvesVerticesData(SFDataAsset<SFValuesList<SFValuenf>> vertices) {
		this.vertices.setDataset(vertices);
	}

	public void setVertices(SFDataAsset<SFValuesList<SFValuenf>> dataset, T... vertices) {
		setVertices(dataset);
		for (int i = 0; i < vertices.length; i++) {
			addVertex(vertices[i]);
		}
	}

	public void addVertex(T value){
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