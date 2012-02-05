
function SFStreamer(){
}

SFStreamer.prototype = {

	loadDataset:function(dataset){
	//for (Iterator<SFDataset> iterator2 = dataset.iterator();iterator2				.hasNext(););//Warning: Not well Identified 
		 SFDataset  sfDataset   = (SFDataset) iterator2.next();
		streamer.map.put(sfDataset.getCode(), sfDataset);
	//}
	},

	loadData:function(stream){
		 String  type   = stream.readString();
		 SFDataset  sfd   = map.get(type);
		 SFDataset  tmp   = sfd.generateNewDatasetInstance();
		tmp.readFromStream(stream);
		return ,tmp;
	},

	getStreamer:function(){
		return ,streamer;
	}

};