package shadow.geometry.vertices;

import java.util.ArrayList;

import shadow.geometry.SFValuesIterator;
import shadow.geometry.SFValuesList;
import shadow.math.SFValuenf;

public class SFArrayListValueList  implements SFValuesList<SFValuenf>{
	
	private ArrayList<SFValuenf> vertices=new ArrayList<SFValuenf>();
	
	public SFArrayListValueList(ArrayList<SFValuenf> vertices) {
		super();
		this.vertices=vertices;
	}
	@Override
	public void init() {
	}
	@Override
	public void destroy() {
	}
	@Override
	public int getSize() {
		return vertices.size();
	}
	@Override
	public void setValue(int index, SFValuenf read) {
	}
	@Override
	public int addValue(SFValuenf read) {
		return getSize();
	}
	@Override
	public void getValue(int index, SFValuenf write) {
		write.set(vertices.get(index));
	}
	
	@Override
	public SFValuesIterator<SFValuenf> getIterator() {
		return new SFValuesIterator<SFValuenf>() {
			private int index=0;
			@Override
			public boolean hasNext() {
				return index<vertices.size();
			}
			
			@Override
			public void getNext(SFValuenf write) {
				write.set(vertices.get(index));
				index++;
			}
		};
	}
}
