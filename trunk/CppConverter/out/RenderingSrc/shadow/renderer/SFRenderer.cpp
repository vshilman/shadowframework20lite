#ifndef shadow_renderer_H_
#define shadow_renderer_H_

#include "java/util/List.h"

#include "shadow/geometry/SFGeometry.h"
#include "shadow/image/SFTexture.h"
#include "shadow/math/SFVertex3f.h"
#include "shadow/pipeline/SFPipeline.h"
#include "shadow/pipeline/SFPipelineGraphics.Module.h"
#include "shadow/pipeline/SFProgram.h"
#include "shadow/system/SFGraphicsResource.h"
#include "shadow/system/SFResource.h"

class SFRenderer implements SFGraphicsResource{

//	SFProgramModuleStructures[] light;
//	int[] baseLod;//addictional Lod
//	SFPipelineRenderingState[] states;//addictional Lod
//	int stepIndex;
//	SFCamera camera;
//	//lod management
//	float[] distances=new float[0];

//	SFResource resource=new SFResource(0);

//	SFRenderer() {
//		super();
		}
		this->baseLod=baseLod;
	}

//	SFResource getResource() {
//		return resource;
	}

//	void setCamera(SFCamera camera) {
		this->camera = camera;
	}

//	SFCamera getCamera() {
//		return camera;
	}

//	void setLight(SFProgramModuleStructures[] light) {
		this->light=light;
	}

//	void setLight(SFProgramModuleStructures light) {
		}
		this->light=lights;
	}

//	SFPipelineRenderingState[] getStates() {
//		return states;
	}

//	void setStates(SFPipelineRenderingState[] states) {
		this->states = states;
	}

//	void setBaseLod(int baseLod) {
		}
		this->baseLod = lods;
	}

	
//	void init() {	
//		//Do nothing
	}

	
//	void destroy() {

	}

//	/**
//	 * Start point of the rendering algorithm
//	 * 
//	 * @param scene
//	 */
//	void render(SFNode node) {

//		for (int i = 0; i < light.length; i++) {
//			stepIndex=i;
//			if(states!=null && states.length>i){
//				states[i].unapply();
			}
//			render(node,baseLod[stepIndex]);
		}
	}

//	void render(SFNode node,int lod) {

//		SFILodSpace space=node.getLodSpace();

//		if(space!=null){
//			lod=baseLod[stepIndex]+lodQuery(space);
		}

//		if(node.inLod(lod)){
//			renderNodeContent(node,lod);
//			for (SFNode son : node) {
//				render(son,lod);
			}
		}
	}

//	void renderNodeContent(SFNode node,int lod) {

//		SFModel model=node.getModel();
//		if(model!=null){

//			setupRenderingData(model);

//			node.getTransform().apply();

//			SFGeometry rootGeometry=node.getModel().getRootGeometry();
//			renderGeometry(rootGeometry,lod);
		}
	}


//	void setupRenderingData(SFModel model) {
//		// Load rendering program
//		SFProgram program=model.getProgram(light[stepIndex]);
//		SFPipeline.getSfProgramBuilder().loadProgram(program);
//		setupMaterialData(Module.TRANSFORM,model.getTransformComponent());
//		setupMaterialData(Module.MATERIAL,model.getMaterialComponent());
//		setupMaterialData(Module.LIGHT,light[stepIndex]);
	}

	static void setupMaterialData(Module module,SFProgramModuleStructures model) {
//		// it is supposed that a Node has only 1 material
//		// that is not wrong if SFObject is going to extend Node
//		// if(node.getMaterial()!=null)
//		//TODO: material data is not loaded
//		List<SFStructureReference> materials=model.getData();
//		for (int structureIndex = 0; structureIndex < materials.size(); structureIndex++) {
//			SFStructureReference sfStructure = materials.get(structureIndex);
//			SFPipeline.getSfPipelineGraphics().loadStructureData(module,sfStructure.getTable().getArray(), structureIndex, sfStructure.getIndex());
		}
//		List<SFTexture> textures=model.getTextures();
//		for (int i = 0; i < textures.size(); i++) {
//			SFTexture sfTextureReference=textures.get(i);
//			SFPipeline.getSfPipelineGraphics().loadTexture(module, sfTextureReference.getTexture(), i);
		}

	}

//	void renderGeometry(SFGeometry geometry,int lod) {

//		// geometry is drawn
//		geometry.drawGeometry(lod);
//		// sons geometry are drawn
//		for (int i=0; i < geometry.getSonsCount(); i++) {
//			renderGeometry(geometry.getSon(i),lod);
		}
	}



//	int lodQuery(SFILodSpace space){

//		SFVertex3f point=space.getNearestPoint(camera);

//		//cut sides first
//		float[] m=camera.extractTransform(); 

//		float camW=m[3]*point.getX()+m[7]*point.getY()+m[11]*point.getZ()+m[15];

//		camW=1.0f/camW;
//		float camX=m[0]*point.getX()+m[4]*point.getY()+m[8]*point.getZ()+m[12];
//		camX*=camW;
//		if(camX<-1 || camX>1)
//			return -1;
//		float camY=m[1]*point.getX()+m[5]*point.getY()+m[9]*point.getZ()+m[13];
//		camY*=camW;
//		if(camY<-1 || camY>1)
//			return -1;
//		float camZ=m[2]*point.getX()+m[6]*point.getY()+m[10]*point.getZ()+m[14];
//		camZ*=camW;
//		if(camZ<-1 || camZ>1)
//			return -1;

//		float distance=SFVertex3f.getDistance(point, camera.getF());

//		for (int i = 0; i < distances.length; i++) {
//			if(distances[i]>distance){
//				return i;
			}
		}

//		return distances.length;
	}
}
;
}
#endif
