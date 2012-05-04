package shadow.renderer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import shadow.geometry.SFGeometry;
import shadow.material.SFLightStep;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFProgram;
import shadow.system.SFInitiable;

public class SFRenderer implements SFInitiable{

	private SFRenderingAlgorithm algorithm;// =new GLBaseRenderingAlgorithm();
	private SFCamera camera;
	
	private ArrayList<SFProgramStructureReference> lights=new ArrayList<SFProgramStructureReference>();//any node has a material. sons nodes may inherit material
	
	
	public SFRenderer() {
		super();
	}

	public SFRenderingAlgorithm getAlgorithm() {
		return algorithm;
	}

	public void setCamera(SFCamera camera) {
		this.camera = camera;
	}

	public SFCamera getCamera() {
		return camera;
	}

	

	public void setAlgorithm(SFRenderingAlgorithm algorithm) {
		this.algorithm=algorithm;
	}
	
	public void addLights(SFProgramStructureReference light){
		lights.add(light);
	}

	public void clearLights(){
		lights.clear();
	}
	
	@Override
	public void init() {
		
	}

	/**
	 * GLRenderer is defined to be the main rendering algorithm for all the
	 * ShadowFramework.
	 * 
	 * @param scene
	 */
	// NOTE: the Shadow Framework Renderer is responsible for rendering,
	// it does not take into account any aspect related to scene management,
	// structure etc.
	public void render(SFNode scene) {
		// for each step in the rendering algorithm
		Iterator<SFLightStep> lights=algorithm.getSteps().iterator();
		Iterator<SFLodFilter> filters=algorithm.getFilters().iterator();
		int i=0;
		
		while(lights.hasNext() && filters.hasNext()){
			SFLightStep lightStep=lights.next();
			SFLodFilter filter=filters.next();
			lightStep.prepareStep();
				//TODO: light data is loaded, but there's work to be done here.
				//TODO: do this on lightStep preparation, it's done on a light step point of view.
				//SFPipeline.getSfPipelineGraphics().loadStructure(lightStep.getProgramName(),lightStep.getStructuresBuffer(0),0);	
				render(scene,filter,lightStep);
			lightStep.closeStep(); 
			i++;
		}

	}


	private void render(SFNode node, SFLodFilter definition, SFLightStep lightStep) {
		
		if (definition.acceptNode(node)) {	
			renderNodeContent(node,definition,lightStep);
			for (SFNode son : node) {
				render(son,definition,lightStep);
			}
		}
	}

	private void renderNodeContent(SFNode node, SFLodFilter definition, SFLightStep lightStep) {
		
		if(node.isDrawable()){

			setupRenderingData(node.getModel(), lightStep);

			// Place the object Node
			// only positions are going to be set. Other transforms resides into
			// geometry level
			node.getTransform().apply();

			// render the NODE root Geometry
			SFGeometry rootGeometry=node.getModel().getRootGeometry();
			renderGeometry(rootGeometry,definition);
		}
	}

	public void setupRenderingData(SFModel model,
			SFLightStep lightStep) {
		// Load rendering program
		SFProgram program=model.getProgram(lightStep);
		SFPipeline.getSfProgramBuilder().loadProgram(program);
		
		for (SFProgramStructureReference sfLight : lights) {
			
			int index=sfLight.getStructure().getMaterialIndex();
			SFPipeline.getSfPipelineGraphics().loadStructureData(sfLight.getStructure().getTable(), index);
				
			//SFPipeline.getSfPipelineGraphics().loadStructure(table.getCode(),table.getData(),index);	
		}

		setupMaterialData(model);
	}

	public static void setupMaterialData(SFRenderable model) {
		// it is supposed that a Node has only 1 material
		// that is not wrong if SFObject is going to extend Node
		// if(node.getMaterial()!=null)
		//TODO: material data is not loaded
		List<SFStructureReference> materials=model.getMaterialsStructures();
		for (SFStructureReference sfMaterial : materials) {
			
			int index=sfMaterial.getMaterialIndex();
			SFPipeline.getSfPipelineGraphics().loadStructureData(sfMaterial.getTable(), index);
				
			//SFPipeline.getSfPipelineGraphics().loadStructure(table.getCode(),table.getData(),index);	
		}
		
		List<SFTextureReference> textures=model.getTextures();
		for (SFTextureReference sfTextureReference : textures) {
			sfTextureReference.apply();
		}
	}

	private void renderGeometry(SFGeometry geometry, SFLodFilter definition) {
		int lod=definition.acceptGeometry(geometry);
		if (lod != SFGeometry.LOD_HINT_DISCARD) {
			// geometry is drawn
			geometry.drawGeometry(lod);
			// sons geometry are drawn
			for (int i=0; i < geometry.getSonsCount(); i++) {
				renderGeometry(geometry.getSon(i),definition);
			}
		}
	}
}