package shadow.image.bitmaps;

import java.util.ArrayList;
import java.util.List;

import shadow.math.SFVertex2f;

public class SFVoronoiBitmapFunction implements SFBitmapFunction{

	private List<SFVertex2f> centers=new ArrayList<SFVertex2f>();
	private SFVoronoiDistance distance;
	private SFIVoronoiModel model;
	
	public SFVoronoiBitmapFunction() {
		super();
	}

	
	public SFIVoronoiModel getModel() {
		return model;
	}



	public void setModel(SFIVoronoiModel model) {
		this.model = model;
	}



	public List<SFVertex2f> getCenters() {
		return centers;
	}

	public void setCenters(List<SFVertex2f> centers) {
		this.centers.addAll(centers);
	}
	
	public SFVoronoiDistance getDistance() {
		return distance;
	}

	public void setDistance(SFVoronoiDistance distance) {
		this.distance = distance;
	}
	
	private SFVertex2f projectVertex(SFVertex2f toProject,int index1,int index2){
		
		SFVertex2f dir1=new SFVertex2f(toProject);
		SFVertex2f dir2=new SFVertex2f(centers.get(index2));
		
		dir1.subtract(centers.get(index1));
		dir2.subtract(centers.get(index1));

		dir2.normalize2f();
		float scalar=dir1.dot2f(dir2);
		dir2.mult(scalar);
		dir2.add(centers.get(index1));
		
//		System.err.println("toProject "+toProject+" indices "+index1+","+index2);
//		System.err.println("scalar "+scalar);
		
		return dir2;
	}
	
	@Override
	public void destroy() {
	
	}
	
	@Override
	public void init() {
		
	}

	@Override
	public float getValue(float u, float v) {
	
		float distance=1000;
		int index=0;
		float distance2=1000;
		int index2=0;
		 
		for (int i = 0; i < centers.size(); i++) {
			float distancei=this.distance.distance(u,v,centers.get(i));
			if(distancei<distance2){
				distance2=distancei;
				index2=i;
			}
			if(distancei<distance){
				distance2=distance;
				index2=index;
				distance=distancei;
				index=i;
			}
		}
		
		SFVertex2f projected=projectVertex(new SFVertex2f(u,v), index, index2);
		
		return model.getValue(index, index2, distance, distance2, 
				SFVertex2f.getDistance(projected, centers.get(index)),
				SFVertex2f.getDistance(projected, centers.get(index2)));
	}
}
