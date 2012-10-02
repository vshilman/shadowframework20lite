package shadow.system.data.objects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import shadow.system.data.SFDataObject;
import shadow.system.data.SFInputStream;
import shadow.system.data.SFOutputStream;

@SuppressWarnings("unchecked")
public class SFDataList<T extends SFDataObject> implements SFDataObject, List<T>{

	private ArrayList<T> dataObject=new ArrayList<T>();
	private T type;
	
	public SFDataList(T type) {
		super();
		this.type = type;
	}

	public ArrayList<T> getDataObject() {
		return dataObject;
	}

	public int elementsSize() {
		return dataObject.size();
	}
	
	@Override
	public void readFromStream(SFInputStream stream) {
		int n=stream.readShort();
		for (int i = 0; i < n; i++) {
			dataObject.add((T)type.clone());
			dataObject.get(i).readFromStream(stream);
		}
	}
	
	@Override
	public void writeOnStream(SFOutputStream stream) {
		stream.writeShort((short)(dataObject.size()));
		for (int i = 0; i < dataObject.size(); i++) {
			dataObject.get(i).writeOnStream(stream);
		}
	}
	
	@Override
	public SFDataList<T> clone(){
		return new SFDataList<T>(type);
	}
	
	public void add(int index, T element) {
		dataObject.add(index,element);
	}
	
	public boolean add(T e) {
		return dataObject.add(e);
	}
	
	@Override
	public boolean addAll(Collection<? extends T> c) {
		return dataObject.addAll(c);
	}
	
	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		return dataObject.addAll(index,c);
	}
	
	@Override
	public void clear() {
		dataObject.clear();
	}
	
	@Override
	public boolean contains(Object o) {
		return dataObject.contains(o);
	}
	
	@Override
	public boolean containsAll(Collection<?> c) {
		return dataObject.containsAll(c);
	}
	
	@Override
	public boolean equals(Object obj) {
		return dataObject.equals(obj);
	}
	
	@Override
	public T get(int index) {
		return dataObject.get(index);
	}
	
	@Override
	public int hashCode() {
		return dataObject.hashCode();
	}
	
	@Override
	public int indexOf(Object o) {
		return dataObject.indexOf(o);
	}
	
	@Override
	public boolean isEmpty() {
		return dataObject.isEmpty();
	}
	
	@Override
	public Iterator<T> iterator() {
		return dataObject.iterator();
	}
	
	@Override
	public int lastIndexOf(Object o) {
		return dataObject.lastIndexOf(o);
	}
	
	@Override
	public ListIterator<T> listIterator() {
		return dataObject.listIterator();
	}
	
	@Override
	public ListIterator<T> listIterator(int index) {
		return dataObject.listIterator(index);
	}
	
	@Override
	public T remove(int index) {
		return dataObject.remove(index);
	}
	
	@Override
	public boolean remove(Object o) {
		return dataObject.remove(o);
	}
	
	@Override
	public boolean removeAll(Collection<?> c) {
		return dataObject.removeAll(c);
	}
	
	@Override
	public boolean retainAll(Collection<?> c) {
		return dataObject.removeAll(c);
	}
	
	public T set(int index, T element) {
		return dataObject.set(index, element);
	}
	
	@Override
	public int size() {
		return dataObject.size();
	}
	
	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		return dataObject.subList(fromIndex, toIndex);
	}
	
	@Override
	public Object[] toArray() {
		return dataObject.toArray();
	}
	
	public <U extends Object> U[] toArray(U[] a) {
		return dataObject.toArray(a);
	}

}
