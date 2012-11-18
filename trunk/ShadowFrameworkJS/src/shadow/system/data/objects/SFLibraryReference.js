//Java to JS on 20/03/2012


var SFLibraryReference_NULL_REFERENCE = "0";
var SFLibraryReference_MISSING_REFERENCE = "0=0";


function SFLibraryReferenceListener(reference){
	this.reference=reference;
}

SFLibraryReferenceListener.prototype["onDatasetAvailable"]=function(name,dataset){
	this.reference.dataset = dataset;
};

function SFLibraryReference(){
	this.datasetName=SFLibraryReference_NULL_REFERENCE;
	this.dataset=null;
}

inherit(SFLibraryReference,SFPrimitiveType);


SFLibraryReference.prototype["clone"]=function(){
			return new SFLibraryReference();
		};
		
SFLibraryReference.prototype["readFromStream"]=function(stream){
			this.datasetName = stream.readName();
			if (this.datasetName===SFLibraryReference_MISSING_REFERENCE) {
				this.datasetName=SFLibraryReference_NULL_REFERENCE;
				this.dataset=null;
			}else if (this.datasetName===SFLibraryReference_NULL_REFERENCE) {
				this.dataset = SFDataCenter_getDataCenter().readDataset(stream);
			}
			
		};

SFLibraryReference.prototype["setReference"]=function(name){
			this.datasetName=name;
		};
		
		
SFLibraryReference.prototype["getReference"]=function(){
			return this.datasetName;
		};
				
SFLibraryReference.prototype["setDataset"]=function(dataset){
			this.dataset=dataset;
		};
	
SFLibraryReference.prototype["getDataset"]=function(dataset){
			return this.dataset;
		};

SFLibraryReference.prototype["retrieveDataset"]=function(){
			if (!(this.datasetName===SFLibraryReference_NULL_REFERENCE)) {
				
				SFDataCenter_getDataCenter().makeDatasetAvailable(this.datasetName,
						new SFLibraryReferenceListener(this));
				//return this.dataset;
			}
			return this.dataset;
		};
		
SFLibraryReference.prototype["releaseReference"]=function(){
			
		};
		
SFLibraryReference.prototype["onDatasetAvailable"]=function(name,dataset){
			this.dataset = dataset;
		};
		
SFLibraryReference.prototype["onDatasetAvailable"]=function(name){
			
		};
		
		