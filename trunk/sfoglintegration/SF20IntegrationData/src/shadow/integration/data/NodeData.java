package shadow.integration.data;

import java.util.ArrayList;

import sfogl.integration.Model;
import sfogl.integration.Node;
import shadow.math.SFTransform3f;
import shadow.system.SFContext;
import shadow.system.data.SFDataAsset;
import shadow.system.data.SFLibraryReference;
import shadow.system.data.SFLibraryReferenceList;
import shadow.system.data.SFNamedParametersObject;

public class NodeData extends SFDataAsset<Node>{

	public NodeData() {
		setName("Node");
		addObject("transform", new SFLibraryReference<SFTransform3f>(new Transform3fData()));
		addObject("model", new SFLibraryReference<Model>());
		addObject("nodes", new SFLibraryReferenceList<Node>(new SFLibraryReference<Node>()));
	}
	
	@Override
	public Node createResource(SFContext context,SFNamedParametersObject sfDataObject) {
		
		SFLibraryReference<SFTransform3f> transform =sfDataObject.getDataObject(0);
		SFLibraryReference<Model> model =sfDataObject.getDataObject(1);
		SFLibraryReferenceList<Node> nodes =sfDataObject.getDataObject(0);
		
		Node node = new Node();
		SFTransform3f transform_=transform.getResource(context);
		node.getRelativeTransform().set(transform_);
		node.setModel(model.getResource(context));

		ArrayList<Node> allNodes=new ArrayList<Node>();
		for (int i = 0; i < nodes.size(); i++) {
			allNodes.add(nodes.get(i).getResource(context));
		}
		node.getSonNodes().addAll(allNodes);
		
		return node;
	}
}
