package model;
import client.SFDataUtils;
import client.SFViewerObjectsLibrary;
import shadow.math.SFMatrix3f;
import shadow.math.SFVertex3f;
import shadow.renderer.SFNode;
import shadow.system.data.SFDataCenter;

public class CharacterDef {

	
	private int id;
	private String name;
	private SFVertex3f position;
	private SFMatrix3f direction;
	private SFNode node;
	
	public SFNode getNode(){
		
		
		return node;
	}
	
	
	public void createNode(){
		
		
		   SFViewerObjectsLibrary library = new SFViewerObjectsLibrary("/", "test0610.sf");
			SFDataCenter.setDataCenterImplementation(library);
			node = (SFDataUtils.getResource("Dummy01"));
			node.getTransform().setPosition(this.position);
			node.getTransform().setOrientation(this.direction);

		
	}
	
	public void setNode(SFNode node){
		
		this.node = node;
		
	}
	

	public void setState(SFVertex3f pos, SFMatrix3f dir){
		
		position = pos;
		direction = dir;
		node.getTransform().setPosition(position);
		node.getTransform().setOrientation(direction);
	}
	
	public SFVertex3f getPosition(){
		
		return position;
		
	}
	
	public SFMatrix3f getDirection(){
		
		return direction;
		
	}
	public void setPosition(SFVertex3f pos){
		
		this.position = pos;
	}
	
	public void setDirection(SFMatrix3f dir){
		
		this.direction = dir;
		
	}
	
}
