

function SFViewerObjectsLibrary(library){
	this.library=library;
}


SFViewerObjectsLibrary.prototype["getFloatValue"]=function(){
			return this.floatValue;
		};

SFViewerObjectsLibrary.prototype["makeDatasetAvailable"]=function(name,listener){
			
			var dataset=this.library.retrieveDataset(name);
			if(dataset==null){
				throw "SFViewerObjectsLibrary: Cannot Find A Dataset named "+name;
			}
			listener.onDatasetAvailable(name, dataset);
		};

SFViewerObjectsLibrary.prototype["getLibrary"]=function(name,listener){
			return this.library;
		};

SFViewerObjectsLibrary.prototype["addLibrary"]=function(library){
			this.library.addLibrary(library.getLibrary());
		};