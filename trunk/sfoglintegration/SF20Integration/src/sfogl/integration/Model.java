package sfogl.integration;

/**
 * Geometry + Material
 * 
 * @author Alessandro
 */
public class Model {

	private Material material;
	private Mesh geometry=null;

    public void setMaterialComponent(Material material) {
		this.material = material;
	}
	
	public void setRootGeometry(Mesh geometry) {
		this.geometry = geometry;
	}
	
	public void draw(){
        material.getProgram().getShader().apply(SFGL2.getGL());
        material.loadData();
        geometry.draw(material.getProgram().getShader());
	}

    public ShadingProgram getProgram(){
        return material.getProgram();
    }
}
