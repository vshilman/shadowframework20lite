package shadow.world;

import java.util.ArrayList;

import shadow.world.temp.SFOccluder;

public class SFOccluderSet {
	private ArrayList<SFOccluder> occluders=new ArrayList<SFOccluder>();

	public SFOccluderSet() {
	}

	public ArrayList<SFOccluder> getOccluders() {
		return occluders;
	}

	public void setOccluders(ArrayList<SFOccluder> occluders) {
		this.occluders = occluders;
	}

	public void addOccluder(SFOccluder occluder){
		getOccluders().add(occluder);
	}

	public int occludersCount() {
		return getOccluders().size();
	}

	public SFOccluder getOccluder(int index) {
		return getOccluders().get(index);
	}
}