package shadow.renderer;

import java.util.LinkedList;
import java.util.List;


public class SFRenderable {

	protected List<String> materialsComponents = new LinkedList<String>();
	private List<SFStructureReference> materialsStructures = new LinkedList<SFStructureReference>();
	private List<SFTextureReference> textures = new LinkedList<SFTextureReference>();
	
	public SFRenderable() {
		super();
	}

	public List<String> getMaterialsComponents() {
		return materialsComponents;
	}

	public List<SFStructureReference> getMaterialsStructures() {
		return materialsStructures;
	}

	public void addMaterial(String programName) {
		materialsComponents.add(programName);
	}

	public void addMaterial(SFProgramStructureReference material) {
		materialsComponents.add(material.getProgramName());
		materialsStructures.add(material.getStructure());
	}

	public void setMaterialsStructures(List<SFStructureReference> getMaterialsStructures) {
		this.materialsStructures = getMaterialsStructures;
	}

	public void setMaterialsComponents(List<String> materialsComponents) {
		this.materialsComponents = materialsComponents;
	}

	public List<SFTextureReference> getTextures() {
		return textures;
	}

	protected String[] getMaterialsNames() {
		String[] materialsName=new String[materialsComponents.size()];
		for (int i=0; i < materialsName.length; i++) {
			materialsName[i]=materialsComponents.get(i);
		}
		return materialsName;
	}

}