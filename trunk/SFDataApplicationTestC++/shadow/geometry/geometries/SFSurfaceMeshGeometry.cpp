

#include "shadow/geometry/geometries/SFSurfaceMeshGeometry.h"

namespace sf{


	FunctionInformations::FunctionInformations(SFSurfaceFunction* function, SFPrimitiveBlock* infos,int* gridIndex,short size) {
		this->function = function;
		this->infos = infos;
		this->gridIndex=gridIndex;
		this->size=size;
		parametersArray=0;
	}


	SFSurfaceMeshGeometry::SFSurfaceMeshGeometry() {
  		valuesPositions=0;
	}

	SFSurfaceMeshGeometry::SFSurfaceMeshGeometry(SFPrimitive* primitive):SFMeshGeometry(primitive){
  		valuesPositions=0;
	}

  	/**
  	 *
  	 * @param block a Primitive Block to which this Surface Function is Assigned
  	 * @param function
  	 * @throws SFException when this SFQuadsSurfaceGeometry Primitive does not make use of the block
  	 */
  	void SFSurfaceMeshGeometry::setFunction(SFPrimitiveBlock block, SFSurfaceFunction* function,SFPrimitiveBlock info) {
  		int blockIndex=-1;
  		for (int i = 0; i < getPrimitive()->getGridsCount(); i++) {
  			if(getPrimitive()->getBlock(i)->getRegister()==block.getRegister()){
  				blockIndex=i;
			}
		}
  		if(blockIndex!=-1){
  			SFPrimitiveBlock* infos=new SFPrimitiveBlock[1];
  			infos[0]=info;
  			int* gridIndices=new int[1];
  			gridIndices[0]=blockIndex;
  			fuctionInformations.push_back(FunctionInformations(function, infos,gridIndices,1));
		}
	}

  	/**
  	 * Set up the function to be used on TexCoord Evaluation
  	 * @param function
  	 * @throws SFException when this SFQuadsSurfaceGeometry cannot use TexCoord
  	 */
  	void SFSurfaceMeshGeometry::setTexCoordGeometry(SFSurfaceFunction* function) {
  		setFunction(*SFPrimitiveBlock::TXO, function, *SFPrimitiveBlock::TXO);
	}

  	void SFSurfaceMeshGeometry::setMainGeometryFunction(SFSurfaceFunction* function) {

  		vector<int> blocksSizes;
  		for (int gridIndex = 0; gridIndex < getPrimitive()->getGridsCount(); gridIndex++) {
  			int size=getPrimitive()->getGrid(gridIndex)->getN();
  			if(find(blocksSizes.begin(),blocksSizes.end(),size)==blocksSizes.end()){
  				blocksSizes.push_back(size);
			}
		}

  		for (unsigned int k = 0; k < blocksSizes.size(); k++) {

  			int n=blocksSizes.at(k);

  			vector<int> gridIndices;
  			vector<SFPrimitiveBlock> infosArray;
  			for (int gridIndex = 0; gridIndex < getPrimitive()->getGridsCount(); gridIndex++) {
  				if(getPrimitive()->getGrid(gridIndex)->getN()==n){

  					SFPrimitiveBlock* block=getPrimitive()->getBlock(gridIndex);

  					if(block==SFPrimitiveBlock::POSITION || block==SFPrimitiveBlock::NORMAL
  							|| block==SFPrimitiveBlock::DU || block==SFPrimitiveBlock::DV){
  						gridIndices.push_back(gridIndex);
  						infosArray.push_back(*block);
					}
				}
			}
  			SFPrimitiveBlock* infos=new SFPrimitiveBlock[infosArray.size()];//infosArray.toArray(new SFPrimitiveBlock[infosArray.size()]);
  			int* gridIndex=new int[gridIndices.size()];
  			for (unsigned int i = 0; i < gridIndices.size(); i++) {
  				gridIndex[i]=gridIndices.at(i);
  				gridIndex[i]=gridIndices.at(i);
			}

  			fuctionInformations.push_back(FunctionInformations(function, infos,gridIndex,gridIndices.size()));

		}
	}

  	vector<FunctionInformations>* SFSurfaceMeshGeometry::getFuctionInformations() {
  		return &fuctionInformations;
	}
	SFVertex3f SFSurfaceMeshGeometry::getPosition(SFSurfaceFunction* function,float u,float v){
  		return SFVertex3f(function->getX(u, v),function->getY(u, v),function->getZ(u, v));
	}

	void SFSurfaceMeshGeometry::getPosition(SFSurfaceFunction* function,float u,float v,SFVertex3f write){
  		write.set3f(function->getX(u, v),function->getY(u, v),function->getZ(u, v));
	}

	SFVertex3f SFSurfaceMeshGeometry::getDu(SFSurfaceFunction* function,float u,float v){
  		SFVertex3f p1=getPosition(function,u-eps, v);
  		SFVertex3f p2=getPosition(function,u+eps, v);
  		p2.subtract3f(p1);
  		p2.mult(1.0f/(2*eps));
  		return p2;
	}

	SFVertex3f SFSurfaceMeshGeometry::getDv(SFSurfaceFunction* function,float u,float v){
  		SFVertex3f p1=getPosition(function,u, v-eps);
  		SFVertex3f p2=getPosition(function,u, v+eps);
  		p2.subtract3f(p1);
  		p2.mult(1.0f/(2*eps));
  		return p2;
	}

	SFVertex3f SFSurfaceMeshGeometry::getNormal(SFSurfaceFunction* function,float u,float v){
  		SFVertex3f* normal=getDu(function,u, v).cross(getDv(function,u, v));
  		normal->normalize3f();
  		SFVertex3f normal_=*normal;
  		delete normal;
  		return normal_;
	}

	void SFSurfaceMeshGeometry::getPN(SFSurfaceFunction* function,float u,float v,SFVertex3f P, SFVertex3f N){
  		P.set3f(function->getX(u, v),function->getY(u, v),function->getZ(u, v));
  		SFVertex3f p1=getPosition(function,u+eps, v);
  		SFVertex3f p2=getPosition(function,u, v+eps);
  		p2.subtract3f(P);
  		p2.mult(1.0f/(eps));
  		p1.subtract3f(P);
  		if(p1.dot(p1)==0){
  			p1=getPosition(function,u+eps, v+eps);
  			p1.subtract3f(getPosition(function,u, v+eps));
		}
  		p1.mult(1.0f/(eps));
  		SFVertex3f* cross=p1.cross(p2);
  		N.set(*cross);
  			delete cross;
  		N.normalize3f();
  		//System.err.println("p1 "+p1+" "+p2+" "+N+" ("+u+","+v+")");
	}


	void SFSurfaceMeshGeometry::getPDuDv(SFSurfaceFunction* function,float u,float v,SFVertex3f P, SFVertex3f Du, SFVertex3f Dv){
  		P.set3f(function->getX(u, v),function->getY(u, v),function->getZ(u, v));
  		SFVertex3f p1=getPosition(function,u+eps, v);
  		SFVertex3f p2=getPosition(function,u, v+eps);
  		p2.subtract3f(P);
  		//p2.mult(1.0f/(eps));
  		p1.subtract3f(P);
  		if(p1.dot(p1)==0){
  			p1=getPosition(function,u+eps, v+eps);
  			p1.subtract3f(getPosition(function,u, v+eps));
		}
  		//p1.mult(1.0f/(eps));
  		p1.normalize3f();
  		p2.normalize3f();
  		Du.set(p1);
  		Dv.set(p2);
	}

	
  	void SFSurfaceMeshGeometry::update() {
  		SFMeshGeometry::update();

  		if(getArray()==0)
  			return;

  		for (unsigned int i = 0; i < fuctionInformations.size(); i++) {
  			FunctionInformations fInfo=fuctionInformations.at(i);
  			SFArray<SFValuenf>* parameters=fInfo.parametersArray;
  			SFIndexRange range=fInfo.parametersIndexRange;

  			SFPrimitiveBlock* infos=fInfo.infos;
  			short size=fInfo.size;

  			if(size==2 && infos[0].getRegister()==SFPrimitiveBlock::POSITION->getRegister() && infos[1].getRegister()==SFPrimitiveBlock::NORMAL->getRegister()){
  				//case 1 : normal and position
  				updateParametrizedModelPN(fInfo.function,valuesPositions, parameters, range,getArray(),fInfo.gridIndex);
  			}else if(size==3 && infos[0].getRegister()==SFPrimitiveBlock::POSITION->getRegister() && infos[1].getRegister()==SFPrimitiveBlock::DU->getRegister() && infos[2].getRegister()==SFPrimitiveBlock::DV->getRegister()){
  							//case 3 : normal and position
  				updateParametrizedModelPDuDv(fInfo.function,valuesPositions, parameters, range,getArray(),fInfo.gridIndex);
  			}else if(size==2 && infos[0].getRegister()==SFPrimitiveBlock::DU->getRegister() && infos[1].getRegister()==SFPrimitiveBlock::DV->getRegister()){
  							//case 3 : normal and position
  				updateParametrizedModelDuDv(fInfo.function,valuesPositions, parameters, range,getArray(),fInfo.gridIndex);
			}else{
  				//general
  				for (int j = 0; j < size; j++) {
  					SFArray<SFValuenf>* primitiveData=getArray()->getPrimitiveData(fInfo.gridIndex[j]);
  					updateParametrizedModel(fInfo.function,valuesPositions[j], parameters, &range, infos[j],primitiveData);
  				}
			}

		}

	}

  	void SFSurfaceMeshGeometry::updateParametrizedModelPN(SFSurfaceFunction* function,int* position,
  			SFArray<SFValuenf>* parameters, SFIndexRange range,
  			SFPrimitiveArray* array, int* gridIndex) {
  		SFVertex2f uv=SFVertex2f(0.0f, 0.0f);
  		SFVertex3f P=SFVertex3f(0.0f, 0.0f, 0.0f);
  		SFVertex3f N=SFVertex3f(0.0f, 0.0f, 0.0f);
  		SFArray<SFValuenf>* primitiveDataP=array->getPrimitiveData(gridIndex[0]);
  		SFArray<SFValuenf>* primitiveDataN=array->getPrimitiveData(gridIndex[1]);
  		int Pposition=position[gridIndex[0]];
  		int Nposition=position[gridIndex[1]];
  		for (int i = 0; i < range.getSize(); i++) {
  			parameters->getElement(range.getPosition()+i, &uv);
  			getPN(function, uv.getX(), uv.getY(), P, N);
  			//System.err.println("u,v " +uv.getX()+" "+ uv.getY()+" "+Arrays.toString(P.getV())+" "+Arrays.toString(N.getV()));
  			primitiveDataP->setElement(Pposition+i, &P);
  			primitiveDataN->setElement(Nposition+i, &N);
		}
	}

  	void SFSurfaceMeshGeometry::updateParametrizedModelPDuDv(SFSurfaceFunction* function,int* position,
  			SFArray<SFValuenf>* parameters, SFIndexRange range,
  			SFPrimitiveArray* array, int* gridIndex) {
  		SFVertex2f uv=SFVertex2f(0.0f, 0.0f);
  		SFVertex3f P=SFVertex3f(0.0f, 0.0f, 0.0f);
  		SFVertex3f Du=SFVertex3f(0.0f, 0.0f, 0.0f);
  		SFVertex3f Dv=SFVertex3f(0.0f, 0.0f, 0.0f);
  		SFArray<SFValuenf>* primitiveDataP=array->getPrimitiveData(gridIndex[0]);
  		SFArray<SFValuenf>* primitiveDataDu=array->getPrimitiveData(gridIndex[1]);
  		SFArray<SFValuenf>* primitiveDataDv=array->getPrimitiveData(gridIndex[2]);
  		int Pposition=position[gridIndex[0]];
  		int Duposition=position[gridIndex[1]];
  		int Dvposition=position[gridIndex[2]];
  		for (int i = 0; i < range.getSize(); i++) {
  			parameters->getElement(range.getPosition()+i, &uv);
  			getPDuDv(function, uv.getX(), uv.getY(), P, Du, Dv);
  			primitiveDataP->setElement(Pposition+i, &P);
  			primitiveDataDu->setElement(Duposition+i, &Du);
  			primitiveDataDv->setElement(Dvposition+i, &Dv);
		}
	}

  	void SFSurfaceMeshGeometry::updateParametrizedModelDuDv(SFSurfaceFunction* function,int* position,
  			SFArray<SFValuenf>* parameters, SFIndexRange range,
  			SFPrimitiveArray* array, int* gridIndex) {
  		SFVertex2f uv=SFVertex2f(0.0f, 0.0f);
  		SFVertex3f P=SFVertex3f(0.0f, 0.0f, 0.0f);
  		SFVertex3f Du=SFVertex3f(0.0f, 0.0f, 0.0f);
  		SFVertex3f Dv=SFVertex3f(0.0f, 0.0f, 0.0f);
  		SFArray<SFValuenf>* primitiveDataDu=array->getPrimitiveData(gridIndex[0]);
  		SFArray<SFValuenf>* primitiveDataDv=array->getPrimitiveData(gridIndex[1]);
  		int Duposition=position[gridIndex[0]];
  		int Dvposition=position[gridIndex[1]];
  		for (int i = 0; i < range.getSize(); i++) {
  			parameters->getElement(range.getPosition()+i, &uv);
  			getPDuDv(function, uv.getX(), uv.getY(), P, Du, Dv);
  			primitiveDataDu->setElement(Duposition+i, &Du);
  			primitiveDataDv->setElement(Dvposition+i, &Dv);
		}
	}

  	void SFSurfaceMeshGeometry::updateParametrizedModel(SFSurfaceFunction* function,int position,
  			SFArray<SFValuenf>* parameters, SFIndexRange* range,
  			SFPrimitiveBlock block, SFArray<SFValuenf>* primitiveData) {
  		SFVertex2f uv(0.0f, 0.0f);
  		SFValuenf* value=primitiveData->generateSample();
  		if(block.getRegister()== SFPrimitiveBlock::NORMAL->getRegister()){
  					for (int i = 0; i < range->getSize(); i++) {
  						parameters->getElement(range->getPosition()+i, &uv);
  						value->set(getNormal(function,uv.getX(), uv.getY()));
  						primitiveData->setElement(position+i, value);
					}
  		}else if(block.getRegister()==SFPrimitiveBlock::DU->getRegister()){

  					for (int i = 0; i < range->getSize(); i++) {
  						parameters->getElement(range->getPosition()+i, &uv);
  						value->set(getDu(function,uv.getX(), uv.getY()));
  						primitiveData->setElement(position+i, value);
					}
  		}else if(block.getRegister()==SFPrimitiveBlock::DV->getRegister()){
  				for (int i = 0; i < range->getSize(); i++) {
  					parameters->getElement(range->getPosition()+i, &uv);
  					value->set(getDv(function,uv.getX(), uv.getY()));
  					primitiveData->setElement(position+i, value);
				}
  		}else{
  				for (int i = 0; i < range->getSize(); i++) {
  					parameters->getElement(range->getPosition()+i, &uv);
  					value->set(getPosition(function,uv.getX(), uv.getY()));
  					primitiveData->setElement(position+i, value);
				}
		}
	}

}
