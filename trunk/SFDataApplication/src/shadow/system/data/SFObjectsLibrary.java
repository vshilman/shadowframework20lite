package shadow.system.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import shadow.system.data.objects.SFDataList;

/**
 * A Library of Datasets, each Dataset being identified by name
 * @author Alessandro Martinelli
 */
public class SFObjectsLibrary extends SFDataAsset<SFObjectsLibrary> implements Iterable<SFObjectsLibrary.RecordData>, SFLibrary{
	
	/**
	 * A basic structure containing a Library Record
	 * @author Alessandro Martinelli
	 */
	public class RecordData{
		private String name;
		private SFDataAsset<?> dataset;
		
		public RecordData(String name, SFDataAsset<?> dataset) {
			super();
			this.name = name;
			this.dataset = dataset;
		}
		
		public String getName() {
			return name;
			
		}
		
		public SFDataAsset<?> getDataset() {
			return dataset;
		}
	}
	
	
	/*A simple Object used by retrieveDataset. Instead of initializing a new SFLibraryRecord
	 * for each search, 
	 * */ 
	private final SFLibraryRecord searchRecord=new SFLibraryRecord("", null);
	
	public class SFLibraryRecord implements SFWritableDataObject{
		
		private String name;
		private SFDataAsset<?> dataset;
		
		public SFLibraryRecord(String name,SFDataAsset<?> dataset) {
			super();
			this.name=name;
			this.dataset=dataset;
		}
		
		@Override
		public void readFromStream(SFInputStream stream) {
			name=stream.readString();
			this.dataset=SFDataCenter.getDataCenter().readDataset(stream,name);
		}
		
		@Override
		public void writeOnStream(SFOutputStream stream) {
			stream.writeString(name);
			SFDataCenter.getDataCenter().writeDataset(stream, dataset);
		}

		@Override
		public boolean equals(Object object) {
			if(object instanceof SFLibraryRecord)
				return this.name.compareTo(((SFLibraryRecord)object).name)==0;
			return false;
		}
		
		@Override
		public SFLibraryRecord copyDataObject() {
			return new SFLibraryRecord(name, dataset);
		}

		public String getName() {
			return name;
		}

		public SFDataAsset<?> getObject() {
			return dataset;
		}
		
		@Override
		public void setStringValue(String value) {
			//nothing to do..
		}
		@Override
		public String toStringValue() {
			return "";
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
				RecordData data=new RecordData(record.name,record.dataset);
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
	
	public SFObjectsLibrary() {
		addObject("records", records);
	}
	
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
	public void put(String name,SFDataAsset<?> dataset) throws NullPointerException{
		if(dataset==null)
			throw new NullPointerException("Dataset cannot be null");
		
		SFLibraryRecord record=new SFLibraryRecord(name, dataset);
		
		if(records.getDataObject().contains(record)){
			int indexof=records.getDataObject().indexOf(record);
			records.getDataObject().remove(indexof);
		}
		
		records.getDataObject().add(record);

		//Collections.sort(records.getDataObject());
	}
	
	
	/* (non-Javadoc)
	 * @see shadow.system.data.SFLibrary#retrieveDataset(java.lang.String)
	 */
	@Override
	public synchronized SFDataAsset<?> retrieveDataset(String name){
		searchRecord.name=name;
		int index= records.getDataObject().indexOf(searchRecord);
		if(index<0)
			return null;
		return records.getDataObject().get(index).dataset;
	}
	
	public void addLibrary(SFObjectsLibrary library){
		this.records.getDataObject().addAll(library.records.getDataObject());
		//Collections.sort(records.getDataObject());
	}
	

	public boolean removeRecord(String name){
		for (int i = 0; i < records.size(); i++) {
			if(records.get(i).name.equals(name)){
				records.getDataObject().remove(i);
				return true;
			}
		}
		return false;
	}
	
	public SFLibraryRecord getRecord(String name){
		for (int i = 0; i < records.size(); i++) {
			if(records.get(i).name.equals(name))
				return records.get(i);
		}
		return null;
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
	
	
}
