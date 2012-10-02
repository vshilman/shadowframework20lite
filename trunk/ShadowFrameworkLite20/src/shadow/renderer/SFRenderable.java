package shadow.renderer;



public class SFRenderable {

	private SFProgramModuleStructures material=new SFProgramModuleStructures();
	
	
	public SFRenderable() {
		super();
	}

	public SFProgramModuleStructures getMaterialComponent() {
		return material;
	}

	public void setMaterialComponent(SFProgramModuleStructures material) {
		this.material = material;
	}

	
}