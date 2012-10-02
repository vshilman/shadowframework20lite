package shadow.geometry.vertices;

import java.util.ArrayList;

import shadow.geometry.SFValuesList;
import shadow.math.SFValuenf;
import shadow.math.SFVertex2f;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.data.SFLibraryReference;
import shadow.system.data.SFNamedParametersObject;

public class SFTextureRepeaterValueList extends SFDataAsset<SFValuesList<SFValuenf>>{

	protected SFLibraryReference<SFValuesList<SFValuenf>> vertices = 
			new SFLibraryReference<SFValuesList<SFValuenf>>();

	public SFTextureRepeaterValueList() {
		super();
		SFNamedParametersObject parameters=new SFNamedParametersObject();
		parameters.addObject("vertices", vertices);
		setData(parameters);
	}
	
	@Override
	protected SFValuesList<SFValuenf> buildResource() {

		SFValuesList<SFValuenf> vertices=this.vertices.getDataset().getResource();
		ArrayList<SFValuenf> newVertices=new ArrayList<SFValuenf>();
		for (int i = 0; i < vertices.getSize(); i++) {
			SFVertex2f vertex=new SFVertex2f(0,0);
			vertices.getValue(i, vertex);
			newVertices.add(vertex);
			SFVertex2f vTemp=new SFVertex2f(vertex);
			vTemp.setX(vertex.getX()+1);
			newVertices.add(vTemp);
			vTemp=new SFVertex2f(vertex);
			vTemp.setX(vertex.getX()-1);
			newVertices.add(vTemp);
			vTemp=new SFVertex2f(vertex);
			vTemp.setY(vertex.getY()+1);
			newVertices.add(vTemp);
			vTemp=new SFVertex2f(vertex);
			vTemp.setY(vertex.getY()-1);
			newVertices.add(vTemp);
		}
		return new SFArrayListValueList(newVertices);
	}
}
