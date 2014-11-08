
#include "SFRenderer.h"


namespace sf{

	SFRenderer::SFRenderer() {
		lightsLength=0;
		statesLength=0;
  		baseLod=0;
  		states=0;
  		light=0;
  		baseLodSize=0;
  		stepIndex=0;
  		distances=0;
  		distancesLength=0;
		//this->baseLod=baseLod;
		camera=0;
	}

  	SFResource* SFRenderer::getResource() {
  		return &resource;
	}

  	void SFRenderer::setCamera(SFCamera* camera) {
		this->camera = camera;
	}

  	SFCamera* SFRenderer::getCamera() {
  		return camera;
	}

  	void SFRenderer::setLight(SFProgramModuleStructures* light) {
		this->light=light;
	}

  	SFPipelineRenderingState* SFRenderer::getStates() {
  		return states;
	}

  	void SFRenderer::setStates(SFPipelineRenderingState* states) {
		this->states = states;
	}

  	void SFRenderer::setBaseLod(int baseLod) {
  		int* lods=new int[1];
  		lods[0]=baseLod;
  		this->baseLod = lods;
  		this->baseLodSize=1;
	}


  	void SFRenderer::init() {
  		//Do nothing
	}


  	void SFRenderer::destroy() {

	}

  	void SFRenderer::render(SFNode* node) {

  		for (int i = 0; i < lightsLength; i++) {
  			stepIndex=i;
  			if(states!=0 && statesLength>i){
  				states[i].unapply();
			}
  			render(node,baseLod[stepIndex]);
		}
	}

  	void SFRenderer::render(SFNode* node,int lod) {

  		SFILodSpace* space=node->getLodSpace();

  		if(space!=0){
  			lod=baseLod[stepIndex]+lodQuery(space);
		}

  		if(node->inLod(lod)){
  			renderNodeContent(node,lod);
  			for(unsigned int i=0;i<node->getNodes()->size();i++){
  				render(node->getNodes()->at(i),lod);
  			}
		}
	}

  	void SFRenderer::renderNodeContent(SFNode* node,int lod) {

  		SFModel* model=node->getModel();
  		if(model!=0){

  			setupRenderingData(model);

  			node->getTransform()->apply();

  			SFGeometry* rootGeometry=node->getModel()->getRootGeometry();
  			renderGeometry(rootGeometry,lod);
		}
	}


  	void SFRenderer::setupRenderingData(SFModel* model) {
  		// Load rendering program
  		SFProgram* program=model->getProgram(light+stepIndex);
  		SFPipeline::getSfProgramBuilder()->loadProgram(program);
  		setupMaterialData(SFPipelineGraphics::TRANSFORM,model->getTransformComponent());
  		setupMaterialData(SFPipelineGraphics::MATERIAL,model->getMaterialComponent());
  		setupMaterialData(SFPipelineGraphics::LIGHT,light[stepIndex]);
	}

	void SFRenderer::setupMaterialData(SFPipelineGraphics::Module module,SFProgramModuleStructures model) {
  		// it is supposed that a Node has only 1 material
  		// that is not wrong if SFObject is going to extend Node
  		// if(node.getMaterial()!=null)
  		//TODO: material data is not loaded
  		vector<SFStructureReference*>* materials=model.getData();
  		for (unsigned int structureIndex = 0; structureIndex < materials->size(); structureIndex++) {
  			SFStructureReference* sfStructure = materials->at(structureIndex);
  			SFPipeline::getSfPipelineGraphics()->loadStructureData(module,sfStructure->getTable()->getArray(), structureIndex, sfStructure->getIndex());
		}
  		vector<SFTexture> textures=model.getTextures();
  		for (unsigned int i = 0; i < textures.size(); i++) {
  			SFTexture sfTextureReference=textures.at(i);
  			SFPipeline::getSfPipelineGraphics()->loadTexture(module, sfTextureReference.getTexture(), i);
		}

	}

  	void SFRenderer::renderGeometry(SFGeometry* geometry,int lod) {

  		// geometry is drawn
  		geometry->drawGeometry(lod);
  		// sons geometry are drawn
  		for (int i=0; i < geometry->getSonsCount(); i++) {
  			renderGeometry(geometry->getSon(i),lod);
		}
	}



  	int SFRenderer::lodQuery(SFILodSpace* space){

  		SFVertex3f point=space->getNearestPoint(camera);

  		//cut sides first
  		float* m=camera->extractTransform();

  		float camW=m[3]*point.getX()+m[7]*point.getY()+m[11]*point.getZ()+m[15];

  		camW=1.0f/camW;
  		float camX=m[0]*point.getX()+m[4]*point.getY()+m[8]*point.getZ()+m[12];
  		camX*=camW;
  		if(camX<-1 || camX>1)
  			return -1;
  		float camY=m[1]*point.getX()+m[5]*point.getY()+m[9]*point.getZ()+m[13];
  		camY*=camW;
  		if(camY<-1 || camY>1)
  			return -1;
  		float camZ=m[2]*point.getX()+m[6]*point.getY()+m[10]*point.getZ()+m[14];
  		camZ*=camW;
  		if(camZ<-1 || camZ>1)
  			return -1;

  		float distance=SFVertex3f::getDistance(point, camera->getF());

  		for (int i = 0; i < distancesLength; i++) {
  			if(distances[i]>distance){
  				return i;
			}
		}

  		return distancesLength;
	}

}
