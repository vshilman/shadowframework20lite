package shadow.geometry.curves.data;

import shadow.geometry.SFCurve;
import shadow.geometry.SFValuesIterator;
import shadow.geometry.SFValuesList;
import shadow.geometry.curves.SFLine;
import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;
import shadow.renderer.data.SFDataAsset;
import shadow.system.data.SFDataCenterListener;

public class SFLineData extends SFCurvesVerticesData<SFValuenf> implements SFDataCenterListener<SFDataAsset<SFValuesList<SFValuenf>>>{

	public SFLineData() {
		super();
	}
	
	public SFLineData(SFDataAsset<SFValuesList<SFValuenf>> dataset,SFValuenf A,SFValuenf B) {
		super();
		setVertices(dataset, A, B);
	}
	
	private SFLine<SFValuenf> line;

	@Override
	public void onDatasetAvailable(String name,
			SFDataAsset<SFValuesList<SFValuenf>> dataset) {
		SFValuesIterator<SFValuenf> iterator=dataset.getResource().getIterator();
		SFVertex3f A=new SFVertex3f();
		SFVertex3f B=new SFVertex3f();
		iterator.getNext(A);
		iterator.getNext(B);
		line.getVertices()[0]=(A);
		line.getVertices()[1]=(B);
	}
	
	@Override
	protected SFCurve<SFValuenf> buildResource() {
		line=new SFLine<SFValuenf>(0);
		vertices.retrieveDataset(this);
		return line;
	}

}
