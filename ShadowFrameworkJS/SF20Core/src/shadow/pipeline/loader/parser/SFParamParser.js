
function SFParamParser(){
}

SFParamParser.prototype = {

	extractNames:function(params){
		 StringTokenizer  tokenizer   = new  StringTokenizer(params, ",");
		 ArrayList<String>  strings   = new  ArrayList<String>();
		while (tokenizer.hasMoreTokens()){
		strings.add(tokenizer.nextToken());
	}
		return ,strings;
	}

};