
function SFGridParser(){
}

SFGridParser.prototype = {

	extractNames:function(params){
		 StringTokenizer  tokenizer   = new  StringTokenizer(params, ",");
		 LinkedList<String>  strings   = new  LinkedList<String>();
		while (tokenizer.hasMoreTokens()){
		strings.add(tokenizer.nextToken());
	}
		return ,strings;
	}

};