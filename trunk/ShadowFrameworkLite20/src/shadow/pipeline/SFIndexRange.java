package shadow.pipeline;

import shadow.system.SFException;

public class SFIndexRange {

	private int position;
	private int size;
	
	public SFIndexRange(int position) {
		super();
		this.position = -1;
	}
	
	public SFIndexRange(int position, int size) {
		super();
		this.position = position;
		this.size = size;
	}
	
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	public void insertIndex(int index){
		if(this.position==-1){
			this.position=index;
			this.size=1;
		}else{
			int last=position+size;
			if(index<position){
				position=index;
				size=last-position;
			}else if(index>=last){
				size=index-position+1;
			}
		}
		
	}
}
