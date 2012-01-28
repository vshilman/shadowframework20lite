

SFParsableGrid.prototype = {

	finalize:function(){
	names=new String[verticesLoading.size()];//Warning: Not well Identified 
	Set<String> ns;
	int j=0;//Warning: Not well Identified 
	//corners idx		corners=new short[cornersLoading.size()];//Warning: Not well Identified 
	//edges idx		edges=new short[edgesLoading.size()][];//Warning: Not well Identified 
	//paths idx		paths=new short[pathsLoading.size()][];//Warning: Not well Identified 
	verticesLoading.clear();//Warning: Not well Identified 
	edgesLoading.clear();//Warning: Not well Identified 
	cornersLoading.clear();//Warning: Not well Identified 
	pathsLoading.clear();//Warning: Not well Identified 
	SFPipeline.loadGrid(getName(), this);//Warning: Not well Identified 
	},

	getAllCommands:function(){
		return this.allCommands;
	}

};