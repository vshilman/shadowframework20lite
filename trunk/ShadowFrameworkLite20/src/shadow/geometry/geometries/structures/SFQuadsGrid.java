package shadow.geometry.geometries.structures;


public class SFQuadsGrid {
	
	private boolean closeU;
	private boolean closeV;
	private float[] vSplits;
	private float[] uSplits;

	public SFQuadsGrid() {
	}

	public boolean isCloseU() {
		return closeU;
	}

	public void setCloseU(boolean closeU) {
		this.closeU = closeU;
	}

	public boolean isCloseV() {
		return closeV;
	}

	public void setCloseV(boolean closeV) {
		this.closeV = closeV;
	}

	public float[] getvSplits() {
		return vSplits;
	}

	public void setvSplits(float[] vSplits) {
		this.vSplits = vSplits;
	}

	public float[] getuSplits() {
		return uSplits;
	}

	public void setuSplits(float[] uSplits) {
		this.uSplits = uSplits;
	}

	public float[] getU_splits() {
		return getuSplits();
	}

	public void setU_splits( float[] uSplits) {
		setuSplits(uSplits);
	}

	public float[] getV_splits() {
		return getvSplits();
	}

	public void setV_splits(float[] vSplits) {
		setvSplits(vSplits);
	}

	public int getNu() {
		return getuSplits().length;
	}

	public void setNu( int nu) {
		setU_splits(new float[nu]);
		float stepU=1.0f/(nu-1.0f);
		for (int i = 0; i < nu; i++) {
			getU_splits()[i]=i*stepU;
		}
	}

	public int getNv() {
		return getvSplits().length;
	}

	public void setNv(int nv) {
		setV_splits(new float[nv]);
		float stepV=1.0f/(nv-1.0f);
		for (int i = 0; i < nv; i++) {
			getV_splits()[i]=i*stepV;
		}
	}
	
	public static int getPartitionedSplitsSize(int n1,int splitsCount){
		return (splitsCount-1)*n1+1;
	}

	public float[] generatePartitionedSplits( int n1, float stepn1, float[] vSplits) {
		float[] vs=new float[(vSplits.length-1)*n1+1];
		for(int i=0;i<(vSplits.length-1)*n1+1;i++){
			int I=i/n1;
			int Ires=i-n1*(I);
			vs[i]=vSplits[I]+(Ires>0?Ires*stepn1*(vSplits[I+1]-vSplits[I]):0);
		}
		return vs;
	}
}