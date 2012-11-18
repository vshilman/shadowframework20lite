
var SFDataAsset_updateMode=false;

function SFDataAsset(){
	this.resource=null;
	this.resourceLock=0;
	this.references=new Array();
}

inherit(SFDataAsset,SFAssetModule);


SFDataAsset.prototype["getResource"]=function(){
			if (this.resource == null || SFDataAsset_updateMode) {
				this.resource = this.buildResource();
				SFInitiator_addInitiable(this.resource);
			}
			this.resourceLock++;
			return this.resource;
		};


SFDataAsset.prototype["releaseResource"]=function(){
			if(this.resourceLock<=0 || this.resource==null)
				throw "A Resource has been released, but it was already unavailable";
			this.resourceLock--;
			if(this.resourceLock==0){
				if(this.references!=null){
					for (var i = 0; i < this.references.size(); i++) {
						var asset=(references[i].getDataset());
						asset.releaseResource();
					}
				}
				SFInitiator.addDestroyable(resource);
				resource=null;	
			}
		};


SFDataAsset.prototype["setReferences"]=function(references){
			this.references=references;
		};

SFDataAsset.prototype["updateReferences"]=function(referencesList){
			var references=new Array();
			for (var i = 0; i < referencesList.length; i++) {
				references[i]=referencesList.get(i);
			}
			this.setReferences(references);
		};
		
function SFDataAsset_isUpdateMode(){
	return SFDataAsset_updateMode;
}

function SFDataAsset_setUpdateMode(updateMode){
	SFDataAsset_updateMode=updateMode;
}

