package shadow.renderer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;
import shadow.system.SFInitiable;

public class SFObjectModel extends SFTransformNode implements SFNode, SFInitiable{

	private List<SFBone> nodes=new ArrayList<SFBone>();	
	
	private SFModel model=new SFModel();

	private class SFNodeIterator implements Iterator<SFNode>{
		int index=0;
		
		@Override
		public boolean hasNext() {
			return index<nodes.size();
		}
		
		@Override
		public SFBone next() {
			if(!hasNext())
				throw new NoSuchElementException();
			SFBone node=(SFBone)(nodes.get(index));
			index++;
			return node;
		}
		
		@Override
		public void remove() {
			if(index>0){
				index--;
				nodes.remove(index);
			}
			throw new IllegalStateException();
		}
	}
	
	public SFObjectModel() {
	}
	
	@Override
	public void addNode(SFNode node) {
		nodes.add((SFBone)node);
	}
	
	
	public void setModel(SFModel model) {
		this.model = model;
	}
	
	

	@Override
	public SFNode copyNode() {
		SFObjectModel model=new SFObjectModel();
		model.setModel(this.model);
		for (SFBone bone : nodes) {
			model.nodes.add(bone.copyNode());
		}
		SFVertex3f tmpV=new SFVertex3f();
		SFMatrix3f tmpM=new SFMatrix3f();
		this.transform.getPosition(tmpV);
		model.transform.setPosition(tmpV);
		this.transform.getOrientation(tmpM);
		model.transform.setOrientation(tmpM);
		return model;
	}

	@Override
	public SFModel getModel() {
		return model;
	}
	
	public boolean isDrawable(){
		return this.getModel().getRootGeometry()!=null;
	}


	@Override
	public void init() {
		// TODO : SFObjectModel should be initialized here		
	}

	protected List<SFBone> getNodes() {
		return nodes;
	}

	/* (non-Javadoc)
	 * @see shadow.renderer.SFNode#iterator()
	 */
	@Override
	public Iterator<SFNode> iterator() {
		return new SFNodeIterator();
	}

}

