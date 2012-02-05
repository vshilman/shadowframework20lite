
function SFList(){
}

SFList.prototype = {

	getId:function(){
		return ,id;
	},

	setId:function(id){
		this.id    = id;
	},

	getN:function(){
		  return  elements.size (  );
	},

	removeElement:function(code){
		this.elements.remove(code);
	},

	addElement:function(code, element){
		this.elements.put(code, element);
	},

	getElement:function(code){
		return ,elements.get(code);
	},

	getAllKeys:function(){
		 Set<String>  keys   = elements.keySet();
	String[] data = new String[keys.size()];//Warning: Not well Identified 
		 Iterator<String>  it   = keys.iterator();
		for ( int  i  =  0 ; i   < data.length ; i++ ){
		 String  l   = it.next();
		data[i]    = l;
	}
		return ,data;
	},

	getKeys:function(){
		  return  elements.keySet (  );
	},

	hasId:function(id){
		 boolean  found   = false;
		 String[]  codes   = getAllKeys();
		for ( int  i  =  0 ; i   < codes.length ; i++ ){
		 if ( codes[i] == id ){
		found    = true;
	}
	}
		return ,found;
	},

	readFromStream:function(stream){
		 int  N   = stream.readInt();
		 String  name   = stream.readString();
		SFMemory.getMemory().loadList(name, this);
		for ( int  i  =  0 ; i   < N ; i++ ){
		 String  elementName   = stream.readString();
		 SFDataset  dataset   = SFStreamer.getStreamer().loadData(stream);
		addElement(elementName, dataset);
	}
	},

	writeOnStream:function(stream){
		stream.writeInt(elements.size());
		stream.writeString(getId());
	Set<Entry<String, SFDataset>> entries = elements.entrySet();//Warning: Not well Identified 
	//for (Iterator<Entry<String, SFDataset>> iterator = entries.iterator();iterator				.hasNext(););//Warning: Not well Identified 
	Entry<String, SFDataset> entry = (Entry<String, SFDataset>) iterator					.next();//Warning: Not well Identified 
		stream.writeString(entry.getKey());
		entry.getValue().writeOnStream(stream);
	//}
	},

	getCode:function(){
		return ,"List";
	},

	generateNewDatasetInstance:function(){
		return ,new ,SFList();
	}

};