#ifndef shadow_geometry_geometries_structures_H_
#define shadow_geometry_geometries_structures_H_


class SFQuadsGrid {

//	boolean closeU;
//	boolean closeV;
//	float[] vSplits;
//	float[] uSplits;

//	SFQuadsGrid() {
	}

//	boolean isCloseU() {
//		return closeU;
	}

//	void setCloseU(boolean closeU) {
		this->closeU = closeU;
	}

//	boolean isCloseV() {
//		return closeV;
	}

//	void setCloseV(boolean closeV) {
		this->closeV = closeV;
	}

//	float[] getvSplits() {
//		return vSplits;
	}

//	void setvSplits(float[] vSplits) {
		this->vSplits = vSplits;
	}

//	float[] getuSplits() {
//		return uSplits;
	}

//	void setuSplits(float[] uSplits) {
		this->uSplits = uSplits;
	}

//	float[] getU_splits() {
//		return getuSplits();
	}

//	void setU_splits( float[] uSplits) {
//		setuSplits(uSplits);
	}

//	float[] getV_splits() {
//		return getvSplits();
	}

//	void setV_splits(float[] vSplits) {
//		setvSplits(vSplits);
	}

//	int getNu() {
//		return getuSplits().length;
	}

//	void setNu( int nu) {
//		setU_splits(new float[nu]);
//		float stepU=1.0f/(nu-1.0f);
//		for (int i = 0; i < nu; i++) {
//			getU_splits()[i]=i*stepU;
		}
	}

//	int getNv() {
//		return getvSplits().length;
	}

//	void setNv(int nv) {
//		setV_splits(new float[nv]);
//		float stepV=1.0f/(nv-1.0f);
//		for (int i = 0; i < nv; i++) {
//			getV_splits()[i]=i*stepV;
		}
	}

	static int getPartitionedSplitsSize(int n1,int splitsCount){
//		return (splitsCount-1)*n1+1;
	}

//	float[] generatePartitionedSplits( int n1, float stepn1, float[] vSplits) {
//		float[] vs=new float[(vSplits.length-1)*n1+1];
//		for(int i=0;i<(vSplits.length-1)*n1+1;i++){
//			int I=i/n1;
//			int Ires=i-n1*(I);
//			vs[i]=vSplits[I]+(Ires>0?Ires*stepn1*(vSplits[I+1]-vSplits[I]):0);
		}
//		return vs;
	}
}
;
}
#endif
