package shadow.renderer;

import java.util.Iterator;
import java.util.List;

import shadow.geometry.SFGeometry;
import shadow.material.SFLightStep;
import shadow.material.SFStructureReference;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFProgram;

public class SFRenderer {

	private SFRenderingAlgorithm algorithm;// =new GLBaseRenderingAlgorithm();

	
	public SFRenderer() {
		super();
	}


	public SFRenderingAlgorithm getAlgorithm() {
		return algorithm;
	}


	public void setAlgorithm(SFRenderingAlgorithm algorithm) {
		this.algorithm=algorithm;
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
				render(scene,filter,i,lightStep);
			lightStep.closeStep(); 
			i++;
		}

		System.out.println("(Debug) SFRenderer \t\t : \t\t LightsNumber:"+i);
	}


	private void render(SFNode node, SFLodFilter definition,
			int lightStepIndex, SFLightStep lightStep) {
		
		if (definition.acceptNode(node)) {	
			renderNodeContent(node,definition,lightStepIndex,lightStep);
			List<SFNode> sonNodes=node.getSonsNodes();
			for (SFNode son : sonNodes) {
				render(son,definition,lightStepIndex,lightStep);
			}
		}
	}

	private void renderNodeContent(SFNode node, SFLodFilter definition,
			int lightSteIndex, SFLightStep lightStep) {

		// it is supposed that a Node has only 1 material
		// that is not wrong if SFObject is going to extend Node
		// if(node.getMaterial()!=null)
		//TODO: material data is not loaded
		List<SFStructureReference> materials=node.getMaterials();
		for (SFStructureReference sfMaterial : materials) {
			
			int index=sfMaterial.getMaterialIndex();
			SFPipeline.getSfPipelineGraphics().loadStructureData(sfMaterial.getTable(), index);
			//SFPipeline.getSfPipelineGraphics().loadStructure(table.getCode(),table.getData(),index);	
		}

		// Place the object Node
		// only positions are going to be set. Other transforms resides into
		// geometry level
		SFPipeline.getSfPipelineGraphics().translateModel(node.x,node.y,node.z);

		// Load rendering program
		SFProgram program=node.getProgram(lightSteIndex,lightStep);
		program.load();

		// render the NODE root Geometry
		SFGeometry rootGeometry=node.getRootGeometry();
		renderGeometry(rootGeometry,definition);
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