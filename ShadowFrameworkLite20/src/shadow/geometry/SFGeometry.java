package shadow.geometry;

import java.util.ArrayList;

import shadow.pipeline.SFPrimitive;
import shadow.system.SFInitiable;

public abstract class SFGeometry implements SFInitiable{

	public static final int LOD_HINT_DISCARD=-1;
	
	//TODO: define all possible rendering hints : NO, rendering HINT SHOULD BE A Type
	
	private int rendering_hint=0;
	private int available_lods=0;//more a constant then 
	
	private SFGeometry fatherGeometry;
	
	private ArrayList<SFGeometry> sonGeometries=new ArrayList<SFGeometry>();
	
	public boolean changed=true;
	
	
	public abstract SFPrimitive getPrimitive();
	//public abstract String[] getTransforms();
	
	public abstract void drawGeometry(int lod);

	
	public int getRendering_hint() {
		return rendering_hint;
	}
	
	protected void setRendering_hint(int rendering_hint) {
		this.rendering_hint = rendering_hint;
	}
	
	public int getAvailable_lods() {
		return available_lods;
	}
	
	protected void setAvailable_lods(int available_lods) {
		this.available_lods = available_lods;
	}

	public int getSonsCount(){
		return sonGeometries.size();
	}
	
	public int addSon(SFGeometry son){
		 sonGeometries.add(son);
		 son.setFatherGeometry(this);
		return sonGeometries.size()-1;
	}
	
	public SFGeometry getSon(int index){
		return sonGeometries.get(index);
	}
	
	
	public SFGeometry getFatherGeometry() {
		return fatherGeometry;
	}

	public void setFatherGeometry(SFGeometry fatherGeometry) {
		this.fatherGeometry = fatherGeometry;
	}
	
	public abstract void compile();
	
	public abstract void update();

	public abstract void decompile();
	
	public void rebuild(){
		compile();
		for (int i = 0; i < sonGeometries.size(); i++) {
			sonGeometries.get(i).rebuild();
		}
	}

}