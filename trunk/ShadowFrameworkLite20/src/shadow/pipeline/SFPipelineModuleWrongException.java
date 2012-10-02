package shadow.pipeline;

import java.util.ArrayList;

public class SFPipelineModuleWrongException extends RuntimeException{

	private static final long serialVersionUID=0;
	
	private ArrayList<String> list=new ArrayList<String>();

	public SFPipelineModuleWrongException(){
		super("Cannot compile shader components");
	}
	
	public ArrayList<String> getList() {
		return list;
	}

	public void setList(ArrayList<String> list) {
		this.list = list;
	}
}
