package shadow.renderer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;
import shadow.system.SFInitiable;

public class SFObjectModel extends SFTransformNode implements SFNode, SFInitiable{

	private List<SFNode> nodes=new ArrayList<SFNode>();	
	
	private SFModel model=new SFModel();

	private class SFNodeIterator implements Iterator<SFNode>{
		int index=0;
		
		@Override
		public boolean hasNext() {
			return index<nodes.size();
		}
		
		@Override
		public SFNode next() {
			if(!hasNext())
				throw new NoSuchElementException();
			SFNode node=(nodes.get(index));
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
	public synchronized void addNode(SFNode node) {
		try {
			nodes.add(node);
		} catch (ClassCastException e) {
			throw new SFNodeException("Only SFBone can be added to SFObjectModels");		
		}
	}
	
	
	public void setModel(SFModel model) {
		this.model = model;
	}
	
	

	@Override
	public SFNode copyNode() {
		SFObjectModel model=new SFObjectModel();
		model.setModel(this.model);
		for (SFNode bone : nodes) {
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
		//Do nothing
	}
	
	@Override
	public void destroy() {
		//Its correct: if init isn't doing anything, destroy should not do anything
	}

	protected List<SFNode> getNodes() {
		return nodes;
	}

	/* (non-Javadoc)
	 * @see shadow.renderer.SFNode#iterator()
	 */
	@Override
	public synchronized Iterator<SFNode> iterator() {
		return new SFNodeIterator();
	}

}

