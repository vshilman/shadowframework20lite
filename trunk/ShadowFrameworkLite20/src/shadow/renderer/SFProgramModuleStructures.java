package shadow.renderer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import shadow.image.SFTexture;
import shadow.system.SFInitiable;

public class SFProgramModuleStructures implements SFInitiable{
	
	private ArrayList<SFStructureReference> data=new ArrayList<SFStructureReference>();
	private List<SFTexture> textures = new LinkedList<SFTexture>();
	private String program;
	
	public SFProgramModuleStructures() {
	}
	
	public SFProgramModuleStructures clone(){
		SFProgramModuleStructures element=new SFProgramModuleStructures(program);
		for (SFStructureReference reference : data) {
			element.data.add(reference);
		}
		for (SFTexture reference : textures) {
			element.textures.add(reference);
		}
		return element;
	}
	
	public SFProgramModuleStructures(String program) {
		this.program=program;
	}

	public ArrayList<SFStructureReference> getData() {
		return data;
	}

	public void addData(SFStructureReference reference) {
		this.data.add(reference);
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public List<SFTexture> getTextures() {
		return textures;
	}
	
	@Override
	public void destroy() {
	}
	
	@Override
	public void init() {	
	}
}