

function SFObjectsLibrary_RecordData(name,dataset){
	this.name=name;
	this.dataset=dataset;
}

function SFObjectsLibrary_SFLibraryRecord(name,dataset){
	this.dataObject = new Array();
	this.name=new SFString();
	this.object=new SFDatasetObject(dataset);
	this.getDataObject().push(this.name);
	this.getDataObject().push(this.object);
}

inherit(SFObjectsLibrary_SFLibraryRecord,SFCompositeDataArray);

SFObjectsLibrary_SFLibraryRecord.prototype["clone"]=function(){
	return new SFObjectsLibrary_SFLibraryRecord(this.name.getString(), this.object.getDataset());
};


function SFObjectsLibrary(){
	this.searchRecord=new SFObjectsLibrary_SFLibraryRecord("",null);
	this.records=new SFDataList(new SFObjectsLibrary_SFLibraryRecord("",null));
}

SFObjectsLibrary.prototype["put"]=function(name,dataset){
	var record=new SFObjectsLibrary_SFLibraryRecord(name, dataset);
	this.records.getDataObject().add(record);
};

SFObjectsLibrary.prototype["retrieveDataset"]=function(name){
	//var record=new SFObjectsLibrary_SFLibraryRecord(name, dataset);
	//this.records.getDataObject().add(record);

	for(var i=0;i<this.records.size();i++){
		var record=this.records.get(i);
		if(name === record.name.getString()){
			return record.object.getDataset();
		}
	}
	return null;
};

SFObjectsLibrary.prototype["getSFDataObject"]=function(){
	return this.records;
};

SFObjectsLibrary.prototype["generateNewDatasetInstance"]=function(){
	return new SFObjectsLibrary();
};

//	/**
//	 * Look for the {@link Dataset} registered with the given name
//	 * @param name the name of the {@link Dataset}
//	 * @return
//	 */
//	public synchronized SFDataset retrieveDataset(String name){
//		searchRecord.name.setString(name);
//		int index= Collections.binarySearch(records.getDataObject(), searchRecord);
//		if(index<0)
//			return null;
//		return records.getDataObject().get(index).object.getDataset();
//	}
//	
//	public void addLibrary(SFObjectsLibrary library){
//		this.records.addAll(library.records);
//	}
//	
//	/**
//	 * @return the number of Dataset in this Library
//	 */
//	public int size(){
//		return records.getDataObject().size();
//	}
//	
//	@Override
//	public Iterator<RecordData> iterator() {
//		return new SFLibraryIterator(records.getDataObject());
//	}
//	
//	@Override
//	public SFDataset generateNewDatasetInstance() {
//		return new SFObjectsLibrary();
//	}
//	
//	@Override
//	public SFDataObject getSFDataObject() {
//		return records;
//	}
//	
//	@Override
//	public String getType() {
//		return this.getClass().getSimpleName();
//	}
//
//	public void invalidate() {
//	}
//	
//}
