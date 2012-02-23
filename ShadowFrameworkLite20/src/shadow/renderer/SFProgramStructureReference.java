package shadow.renderer;

import shadow.renderer.data.SFStructureReference;

public class SFProgramStructureReference {
	
	private String programName;
	private SFStructureReference structure;

	public SFProgramStructureReference(String programName,
			SFStructureReference structure) {
		super();
		this.programName = programName;
		this.structure = structure;
	}
	
	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public SFStructureReference getStructure() {
		return structure;
	}
	public void setStructure(SFStructureReference structure) {
		this.structure = structure;
	}
}
