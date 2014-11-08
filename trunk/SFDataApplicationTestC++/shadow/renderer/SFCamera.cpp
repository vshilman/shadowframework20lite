
#include "SFCamera.h"

namespace sf {


	SFCamera::SFCamera(SFVertex3f focus, SFVertex3f dir,
			SFVertex3f left, SFVertex3f up, float leftL,
			float upL,float distance) {
		changed=true;
		getF().set(&focus);
		getDir().set(&dir);
		getLeft().set(&left);
		getUp().set(&up);
		this->setLeftL(leftL);
		this->setUpL(upL);
		this->setDistance(distance);
		update();
	}


	SFResource* SFCamera::getResource() {
		return &resource;
	}

	SFVertex3f SFCamera::getDir() {
		return Dir;
	}

	void SFCamera::setDir(SFVertex3f dir) {
		Dir = dir;
	}

	void SFCamera::setDirLength(float dirLength){
		Dir.normalize3f();
		Dir.mult(dirLength);
}

	void SFCamera::update(){
		//Left.set3f(Dir.getZ(),0,-Dir.getX());
		getLeft().normalize3f();
		//Up=Dir.cross(Left);
		getUp().normalize3f();
		getLeft().mult(getLeftL());
		getUp().mult(getUpL());
		getDir().normalize3f();
		//getDir().mult(1/getDistance());
		changed=true;
}

	float* SFCamera::extractTransform() {

		SFMatrix3f mat=SFMatrix3f(
				getLeft().getX(),getUp().getX(),getDir().getX(),
				getLeft().getY(),getUp().getY(),getDir().getY(),
				getLeft().getZ(),getUp().getZ(),getDir().getZ()
		);

		if(changed){
			//setDelta((float)Math.sqrt(getDir().dot3f(getDir())));
			mat=SFMatrix3f::getInverse(mat);

			matrix[0]=mat.getA();
			matrix[1]=mat.getD();
			matrix[2]=mat.getG()*getDelta();
			matrix[3]=0;

			matrix[4]=mat.getB();
			matrix[5]=mat.getE();
			matrix[6]=mat.getH()*getDelta();
			matrix[7]=0;

			matrix[8]=mat.getC();
			matrix[9]=mat.getF();
			matrix[10]=mat.getI()*getDelta();
			matrix[11]=0;

			matrix[12]=-(matrix[0]*getF().getX()+matrix[4]*getF().getY()+matrix[8]*getF().getZ());
			matrix[13]=-(matrix[1]*getF().getX()+matrix[5]*getF().getY()+matrix[9]*getF().getZ());
			matrix[14]=-(matrix[2]*getF().getX()+matrix[6]*getF().getY()+matrix[10]*getF().getZ());
			matrix[15]=1;

			if(isPerspective()){

				float al=(getDistance()+getDelta())/(getDistance()-getDelta());
				float bl=(-2*getDistance()*getDelta())/(getDistance()-getDelta());

				matrix[0]=getDelta()*matrix[0];
				matrix[1]=getDelta()*matrix[1];
				matrix[3]=matrix[2];
				matrix[2]=al*matrix[2];

				matrix[4]=getDelta()*matrix[4];
				matrix[5]=getDelta()*matrix[5];
				matrix[7]=matrix[6];
				matrix[6]=al*matrix[6];

				matrix[8]=getDelta()*matrix[8];
				matrix[9]=getDelta()*matrix[9];
				matrix[11]=matrix[10];
				matrix[10]=al*matrix[10];

				matrix[12]=getDelta()*matrix[12];
				matrix[13]=getDelta()*matrix[13];
				matrix[15]=matrix[14];
				matrix[14]=al*matrix[14]+bl;

		}
	}

		return matrix;
	}

	bool SFCamera::isPerspective() {
		return isPerspective_;
	}

	void SFCamera::setPerspective(bool isPerspective) {
		this->isPerspective_ = isPerspective;
		changed=true;
	}

	SFVertex3f SFCamera::getWorldPosition(SFVertex3f cameraPosition){

		SFVertex3f position=SFVertex3f(getF());
		position.addMult3f(getDistance()+cameraPosition.getZ(),getDir());
		position.addMult3f((float)(getLeftL()*cameraPosition.getX()),getLeft());
		position.addMult3f((float)(getUpL()*cameraPosition.getY()),getUp());

		return position;
	}

	SFMatrix3f SFCamera::getWorldRotation(SFMatrix3f matrixOrientation){

		SFMatrix3f cameraMatrix=SFMatrix3f(
					getLeft().getX(),getUp().getX(),getDir().getX(),
					getLeft().getY(),getUp().getY(),getDir().getY(),
					getLeft().getZ(),getUp().getZ(),getDir().getZ()
				);

		return SFMatrix3f::getTransposed(cameraMatrix).MultMatrix(matrixOrientation.MultMatrix(cameraMatrix));
	}

	SFVertex3f SFCamera::getF() {
		return F;
	}

	void SFCamera::setF(SFVertex3f f) {
		F = f;
		changed=true;
	}

	SFVertex3f SFCamera::getUp() {
		return Up;
	}

	void SFCamera::setUp(SFVertex3f up) {
		Up = up;
		changed=true;
	}

	SFVertex3f SFCamera::getLeft() {
		return Left;
	}

	void SFCamera::setLeft(SFVertex3f left) {
		Left = left;
		changed=true;
	}

	float SFCamera::getLeftL() {
		return leftL;
	}

	void SFCamera::setLeftL(float leftL) {
		this->leftL = leftL;
			changed=true;
	}

	float SFCamera::getUpL() {
		return upL;
	}

	void SFCamera::setUpL(float upL) {
		this->upL = upL;
		changed=true;
	}

	float SFCamera::getDistance() {
		return distance;
	}

	void SFCamera::setDistance(float distance) {
		this->distance = distance;
			changed=true;
	}

	float SFCamera::getDelta() {
		return delta;
	}

	void SFCamera::setDelta(float delta) {
		this->delta = delta;
		changed=true;
	}

	void SFCamera::setupDimensions(float leftL, float upL) {
		setLeftL(leftL);
		setUpL(upL);
		update();
		changed=true;
	}
}  // namespace sf
