
function SFBeginParser(){
}

SFBeginParser.prototype = {

	getElement:function(type){
		try{
		return (SFParsableElement),(typeMap.get(type).newInstance());
	}
		catch (InstantiationException e){
		e.printStackTrace();
		return ,null;
	}
		catch (IllegalAccessException e){
		e.printStackTrace();
		return ,null;
	}
	}

};