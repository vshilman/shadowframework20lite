package shadow.renderer;

import java.util.List;

import shadow.geometry.SFGeometry;
import shadow.image.SFTexture;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineGraphics.Module;
import shadow.pipeline.SFProgram;
import shadow.system.SFInitiable;

public class SFRenderer implements SFInitiable{

	private SFProgramModuleStructures light = new SFProgramModuleStructures();
	private SFCamera camera;
	private int lod;
	
	public SFRenderer() {
		super();
	}

	public void setCamera(SFCamera camera) {
		this.camera = camera;
	}

	public SFCamera getCamera() {
		return camera;
	}

	public void setLight(SFProgramModuleStructures light) {
		this.light=light;
	}


	public int getLod() {
		return lod;
	}


	public void setLod(int lod) {
		this.lod = lod;
	}


	@Override
	public void init() {
		//Do nothing
	}
	
	@Override
	public void destroy() {
		//Its correct: if init isn't doing anything, destroy should not do anything
	}

	/**
	 * GLRenderer is defined to be the main rendering algorithm for all the
	 * ShadowFramework.
	 * 
	 * @param scene
	 */
	public void render(SFNode node) {
		renderNodeContent(node);
		for (SFNode son : node) {
			render(son);
		}
	}

	private void renderNodeContent(SFNode node) {
		
		if(node.isDrawable()){

			setupRenderingData(node.getModel());

			node.getTransform().apply();
			
			SFGeometry rootGeometry=node.getModel().getRootGeometry();
			renderGeometry(rootGeometry);
		}
	}
		

	public void setupRenderingData(SFModel model) {
		// Load rendering program
		SFProgram program=model.getProgram(light);
		SFPipeline.getSfProgramBuilder().loadProgram(program);

		setupMaterialData(Module.TRANSFORM,model.getTransformComponent());
		setupMaterialData(Module.MATERIAL,model.getMaterialComponent());
		setupMaterialData(Module.LIGHT,light);
	}

	public static void setupMaterialData(Module module,SFProgramModuleStructures model) {
		// it is supposed that a Node has only 1 material
		// that is not wrong if SFObject is going to extend Node
		// if(node.getMaterial()!=null)
		//TODO: material data is not loaded
		List<SFStructureReference> materials=model.getData();
		for (int structureIndex = 0; structureIndex < materials.size(); structureIndex++) {
			SFStructureReference sfStructure = materials.get(structureIndex);
			SFPipeline.getSfPipelineGraphics().loadStructureData(module,sfStructure.getTable(), structureIndex, sfStructure.getIndex());
		}
		
		List<SFTexture> textures=model.getTextures();
		for (int i = 0; i < textures.size(); i++) {
			SFTexture sfTextureReference=textures.get(i);
			SFPipeline.getSfPipelineGraphics().loadTexture(module, sfTextureReference.getTexture(), i);
		}
		
	}

	private void renderGeometry(SFGeometry geometry) {
		
		// geometry is drawn
		geometry.drawGeometry(lod);
		// sons geometry are drawn
		for (int i=0; i < geometry.getSonsCount(); i++) {
			renderGeometry(geometry.getSon(i));
		}
	}
}