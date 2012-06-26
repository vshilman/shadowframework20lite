package shadow.renderer.viewer;

import shadow.math.SFVertex3f;

public class CharacterDef {

	
	private int id;
	private String name;
	private SFVertex3f position;
	
	
	
	public void setPosition(SFVertex3f pos){
		
		position = pos;
		
		
	}
	
	public SFVertex3f getPosition(){
		
		return position;
		
	}
	
}
