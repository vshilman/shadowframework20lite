package shadow.pipeline;

import java.util.Map;

public class PipelineEntry<K, V> implements Map.Entry<K, V>{

	private K key;
	private V value;
	
	public PipelineEntry(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}

	@Override
	public K getKey() {
		return key;
	}
	
	@Override
	public V getValue() {
		return value;
	}
	
	public V setValue(V value) {
		this.value=value;
		return value;
	}
}
