package shadow.system.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

import shadow.system.data.objects.SFCompositeDataArray;
import shadow.system.data.objects.SFDataList;
import shadow.system.data.objects.SFDatasetObject;
import shadow.system.data.objects.SFString;

/**
 * A Library of Datasets, each Dataset being identified by name
 * @author Alessandro Martinelli
 */
public class SFObjectsLibrary implements SFDataset, Iterable<SFObjectsLibrary.RecordData>{
	
	/**
	 * A basic structure containing a Library Record
	 * @author Alessandro Martinelli
	 */
	public class RecordData{
		private String name;
		private SFDataset dataset;
		
		
		public RecordData(String name, SFDataset dataset) {
			super();
			this.name = name;
			this.dataset = dataset;
		}
		
		public String getName() {
			return name;
		}
		
		public SFDataset getDataset() {
			return dataset;
		}
	}
	
	
	/*A simple Object used by retrieveDataset. Instead of initializing a new SFLibraryRecord
	 * for each search, 
	 * */ 
	private final SFLibraryRecord searchRecord=new SFLibraryRecord("", null);
	
	private class SFLibraryRecord extends SFCompositeDataArray implements Comparable<SFLibraryRecord>{
		
		private SFString name;
		private SFDatasetObject<SFDataset> object;
		
		public SFLibraryRecord(String name,SFDataset dataset) {
			super();
			this.name.setString(name);
			this.object.setDataset(dataset);
		}

		@Override
		public void generateData() {
			name=new SFString("");
			object=new SFDatasetObject<SFDataset>(null);
			getDataObject().add(name);
			getDataObject().add(object);
		}
		
		@Override
		public int compareTo(SFLibraryRecord o) {
			return name.getString().compareTo(o.name.getString());
		}
		
		@Override
		public SFLibraryRecord clone() {
			return new SFLibraryRecord(name.getString(), object.getDataset());
		}
	} 
	
	private class SFLibraryIterator implements Iterator<RecordData>{
		int index=0;
		private ArrayList<SFLibraryRecord> records=new ArrayList<SFObjectsLibrary.SFLibraryRecord>();
		
		public SFLibraryIterator(ArrayList<SFLibraryRecord> records) {
			super();
			this.records = records;
		}

		@Override
		public boolean hasNext() {
			return index<records.size();
		}
		
		@Override
		public RecordData next() {
			if( index<records.size()){
				SFLibraryRecord record=records.get(index);
				RecordData data=new RecordData(record.name.getString(),record.object.getDataset());
				index++;
				return data;
			}
			throw new NoSuchElementException();
		}
		@Override
		public void remove() {
			if(index>0){
				index--;
				records.remove(index);
			}else{
				throw new IllegalStateException();
			}
		}
	}
	
	private SFDataList<SFLibraryRecord> records=new SFDataList<SFLibraryRecord>(new SFLibraryRecord("",null));
	
	/**
	 * Insert a new record to this Library. 
	 * @param entry a {@link RecordData} containing data to be added to this library
	 */
	public void put(RecordData entry){
		put(entry.name,entry.dataset);
	}
	
	/**
	 * Insert a new record to this Library. 
	 * @param name The name of this record.
	 * @param dataset The dataset called with that name. 
	 */
	public void put(String name,SFDataset dataset) throws NullPointerException{
		if(dataset==null)
			throw new NullPointerException("Dataset cannot be null");
		SFLibraryRecord record=new SFLibraryRecord(name, dataset);
		records.getDataObject().add(record);
		Collections.sort(records.getDataObject());
	}
	
	/**
	 * Look for the {@link Dataset} registered with the given name
	 * @param name the name of the {@link Dataset}
	 * @return
	 */
	public synchronized SFDataset retrieveDataset(String name){
		searchRecord.name.setString(name);
		int index= Collections.binarySearch(records.getDataObject(), searchRecord);
		if(index<0)
			return null;
		return records.getDataObject().get(index).object.getDataset();
	}
	
	public void addLibrary(SFObjectsLibrary library){
		this.records.addAll(library.records);
	}
	
	/**
	 * @return the number of Dataset in this Library
	 */
	public int size(){
		return records.getDataObject().size();
	}
	
	@Override
	public Iterator<RecordData> iterator() {
		return new SFLibraryIterator(records.getDataObject());
	}
	
	@Override
	public SFDataset generateNewDatasetInstance() {
		return new SFObjectsLibrary();
	}
	
	@Override
	public SFDataObject getSFDataObject() {
		return records;
	}
	
	@Override
	public String getType() {
		return this.getClass().getSimpleName();
	}

	public void invalidate() {
	}
	
}
