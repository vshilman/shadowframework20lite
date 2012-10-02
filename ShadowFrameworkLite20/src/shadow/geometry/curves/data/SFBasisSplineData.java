package shadow.geometry.curves.data;

import shadow.geometry.SFValuesIterator;
import shadow.geometry.SFValuesList;
import shadow.geometry.curves.SFBasisSpline2;
import shadow.geometry.curves.SFControlPointsCurve;
import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.SFDataCenterListener;

public class SFBasisSplineData extends SFCurvesVerticesData implements SFDataCenterListener<SFDataAsset<SFValuesList<SFValuenf>>>{

	public SFBasisSplineData() {
		super();
	}
	
	public SFBasisSplineData(boolean closed) {
		super();
		setClosed(closed);
	}
	
	public SFBasisSplineData(String vertices,boolean closed) {
		super();
		setVertices(vertices);
		setClosed(closed);
	}
	
	public SFBasisSplineData(String vertices) {
		super();
		setVertices(vertices);
	}
	
	public SFBasisSplineData(SFDataAsset<SFValuesList<SFValuenf>> dataset,SFValuenf... vertices) {
		super();
		setVertices(dataset,vertices);
	}
	
	public SFBasisSplineData(SFDataAsset<SFValuesList<SFValuenf>> dataset,boolean closed,SFValuenf... vertices) {
		super();
		setVertices(dataset,vertices);
		setClosed(closed);
	}
	
	private SFBasisSpline2<SFValuenf> spline;

	@Override
	public void onDatasetAvailable(String name,
			SFDataAsset<SFValuesList<SFValuenf>> dataset) {
		SFValuesIterator<SFValuenf> iterator=dataset.getResource().getIterator();
		while (iterator.hasNext()) {
			SFVertex3f vertex=new SFVertex3f();
			iterator.getNext(vertex);
			spline.getVertices().add(vertex);
		}
	}
	
	
	
	@Override
	protected SFControlPointsCurve buildResource() {
		spline=new SFBasisSpline2<SFValuenf>(super.getClosed());
		vertices.retrieveDataset(this);
		return spline;
	}

}
