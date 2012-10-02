package shadow.pipeline.java;

import java.util.ArrayList;

import shadow.system.SFArray;
import shadow.system.SFArrayElementException;

public abstract class SFGL20ListData<E> implements SFArray<E>{

	protected ArrayList<E> data=new ArrayList<E>();
	
	protected abstract E generateGenericElement();
	protected abstract void assignValues(E writing,E reading) throws SFArrayElementException;
	
	@Override
	public void eraseElements(int index, int elementsCount) {
		for (int i = 0; i < elementsCount; i++) {
			data.remove(index);
		}
	}
	
	@Override
	public E generateSample() {
		return generateGenericElement();
	}
	
	//Used only by implementation
	public E getValue(int index){
		return data.get(index);
	}
	
	@Override
	public int generateElement() {
		E e=generateGenericElement();
		data.add(e);
		return data.size()-1;
	}
	
	@Override
	public int generateElements(int count) {
		int index=data.size();
		for (int i = 0; i < count; i++) {
			data.add(generateGenericElement());
		}
		return index;
	}
	
	public void getElement(int index, E element) throws SFArrayElementException {
		assignValues(element, data.get(index));
	}
	
	@Override
	public int getElementsCount() {
		return data.size();
	}
	
	public void setElement(int index, E element) throws SFArrayElementException {
		assignValues(data.get(index),element);
	}
	
}
