package shadow.operational.meshes;

import shadow.geometry.SFCurve;
import shadow.math.SFValuenf;
import shadow.system.SFException;

public class SFMeshEdge {
	
	private int edgeConnectionIndex1;
	private int edgeConnectionIndex2;
	private SFCurve[] curves;
	private int mainTessellation;
	
	public SFMeshEdge(int edgeConnectionIndex1, int edgeConnectionIndex2,int mainTessellation, SFCurve[] curves) {
		super();
		this.edgeConnectionIndex1 = edgeConnectionIndex1;
		this.edgeConnectionIndex2 = edgeConnectionIndex2;
		this.mainTessellation=mainTessellation;
		this.curves=curves;
	}

	public int getEdgeConnectionIndex1() {
		return edgeConnectionIndex1;
	}

	public int getEdgeConnectionIndex2() {
		return edgeConnectionIndex2;
	}

	public SFCurve[] getCurve() {
		return curves;
	}
	
	public int getMainTessellation() {
		return mainTessellation;
	}
	
	public SFValuenf[][] extractValues(int[] gridN) {
		
		if(gridN.length!=curves.length)
			throw new SFException("Not well done");
		
		SFValuenf[][] values=new SFValuenf[curves.length][];
		
		for (int i = 0; i < values.length; i++) {
			
			int tessellation = (gridN[i]-1)*mainTessellation;
			float step=1.0f/tessellation;
			values[i]=new SFValuenf[tessellation+1];
			
			for (int j = 0; j <= tessellation; j++) {
				float t=j*step;
				t=curves[i].getTMin()*(1-t)+curves[i].getTMax()*t;
				values[i][j]=curves[i].generateValue();
				curves[i].getVertex(t, values[i][j]);
			}
		}
		
		return values;
	}

}
