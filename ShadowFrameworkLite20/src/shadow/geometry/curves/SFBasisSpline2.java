package shadow.geometry.curves;

import java.util.ArrayList;

import shadow.math.SFValuenf;

public class SFBasisSpline2<T extends SFValuenf> extends SFUnOptimizedCurve<T> implements SFControlPointsCurve{

	private ArrayList<SFValuenf> vertices=new ArrayList<SFValuenf>();
	private boolean closed;
	
	public SFBasisSpline2(boolean closed) {
		super(); 
		this.closed = closed;
	}

	@Override
	public int getControlPointSize() {
		return vertices.size();
	}
	
	@Override
	public SFValuenf getControlPoint(int index) {
		return vertices.get(index);
	}
	
	public ArrayList<SFValuenf> getVertices() {
		return vertices;
	}
	
	public void add(SFValuenf value) {
		vertices.add(value);
	}
	
	@Override
	public SFValuenf generateValue() {
		return new SFValuenf(vertices.get(0).get().length);
	}
	
	@Override
	public void getDev2Dt(float T, SFValuenf write) {
		ArrayList<SFValuenf> vertices=this.vertices;
		
		int v_index=(int)(T*vertices.size());
		if(v_index==vertices.size())
			v_index--;
		
		if(closed){
			
			int indexl1=v_index>0?v_index-1:vertices.size()-1;
			int indexm1=v_index<vertices.size()-1?v_index+1:0;
			
			SFValuenf A=SFValuenf.middle(vertices.get(indexl1), vertices.get(v_index));
			SFValuenf B=vertices.get(v_index);
			SFValuenf C=SFValuenf.middle(vertices.get(v_index), vertices.get(indexm1));
			
			write.set(A);
			write.mult(2);
			write.addMult(-4, B);
			write.addMult(2, C);
		}else{
			if(v_index==0){
				write.mult(0);
			}else if(v_index==vertices.size()-1){
				write.mult(0);
			}else{
				SFValuenf A=SFValuenf.middle(vertices.get(v_index-1), vertices.get(v_index));
				SFValuenf B=vertices.get(v_index);
				SFValuenf C=SFValuenf.middle(vertices.get(v_index), vertices.get(v_index+1));
				write.set(A);
				write.mult(2);
				write.addMult(-4, B);
				write.addMult(2, C);
			}
		}
	}
	
	
	@Override
	public void getDevDt(float v, SFValuenf write) {
		ArrayList<SFValuenf> vertices=this.vertices;
		
		int v_index=(int)(v*vertices.size());
		if(v_index==vertices.size())
			v_index--;
		
		float t=(v*vertices.size())-v_index;
		
		if(closed){
			
			int indexl1=v_index>0?v_index-1:vertices.size()-1;
			int indexm1=v_index<vertices.size()-1?v_index+1:0;
			
			SFValuenf A=SFValuenf.middle(vertices.get(indexl1), vertices.get(v_index));
			SFValuenf B=vertices.get(v_index);
			SFValuenf C=SFValuenf.middle(vertices.get(v_index), vertices.get(indexm1));
			
			write.set(A);
			write.mult(-2*(1-t));
			write.addMult(2-4*t, B);
			write.addMult(2*t, C);
		}else{
			if(v_index==0){
				SFValuenf A=vertices.get(0);
				SFValuenf B=SFValuenf.middle(vertices.get(0),vertices.get(1));
				write.set(B);
				write.subtract(A);
			}else if(v_index==vertices.size()-1){
				SFValuenf A=SFValuenf.middle(vertices.get(v_index-1),vertices.get(v_index));
				SFValuenf B=vertices.get(v_index);
				write.set(B);
				write.subtract(A);
			}else{
				SFValuenf A=SFValuenf.middle(vertices.get(v_index-1), vertices.get(v_index));
				SFValuenf B=vertices.get(v_index);
				SFValuenf C=SFValuenf.middle(vertices.get(v_index), vertices.get(v_index+1));
				write.set(A);
				write.mult(-2*(1-t));
				write.addMult(2-4*t, B);
				write.addMult(2*t, C);
			}
		}
	}
	
	
	@Override
	public void getVertex(float T, SFValuenf write) {
		
		int v_index=(int)(T*vertices.size());
		if(v_index==vertices.size())
			v_index--;
		
		float t=(T*vertices.size())-v_index;
		
		if(closed){
			
			int indexl1=v_index>0?v_index-1:vertices.size()-1;
			int indexm1=v_index<vertices.size()-1?v_index+1:0;
			
			SFValuenf A=SFValuenf.middle(vertices.get(indexl1), vertices.get(v_index));
			SFValuenf B=vertices.get(v_index);
			SFValuenf C=SFValuenf.middle(vertices.get(v_index), vertices.get(indexm1));
			
			write.set(A);
			write.mult((1-t)*(1-t));
			write.addMult(2*t*(1-t), B);
			write.addMult(t*t, C);
		}else{
			if(v_index==0){
				SFValuenf A=vertices.get(0);
				SFValuenf B=SFValuenf.middle(vertices.get(0),vertices.get(1));
				write.set(A);
				write.mult(1-t);
				write.addMult(t, B);
			}else if(v_index==vertices.size()-1){
				SFValuenf A=SFValuenf.middle(vertices.get(v_index-1),vertices.get(v_index));
				SFValuenf B=vertices.get(v_index);
				write.set(A);
				write.mult(1-t);
				write.addMult(t, B);
			}else{
				SFValuenf A=SFValuenf.middle(vertices.get(v_index-1), vertices.get(v_index));
				SFValuenf B=vertices.get(v_index);
				SFValuenf C=SFValuenf.middle(vertices.get(v_index), vertices.get(v_index+1));
				
				write.set(A);
				write.mult((1-t)*(1-t));
				write.addMult(2*t*(1-t), B);
				write.addMult(t*t, C);
			}
		}
		
		
	}
	
	@Override
	public float getTMax() {
		return 1;
	}
	
	@Override
	public float getTMin() {
		return 0;
	}
	
	
	@Override
	public void init() {
		
	}
	
	@Override
	public void destroy() {
		//Its correct: if init isn't doing anything, destroy should not do anything
	}
}
