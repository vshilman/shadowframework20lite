
function SFPipelineRegister(){
}

SFPipelineRegister.prototype = {

	getUse:function(){
		return ,use;
	},

	getFromName:function(name){
		 SFPipelineRegister  globalV = predefinedGlobalV.get(name);
		if,(globalV==null)			throw ,new ,SFException("No Predefined Global Variable having name "+name);
		return ,globalV;
	}

};