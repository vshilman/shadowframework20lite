

function SFExpressionParser() {
	this.brackets="()";
}

SFExpressionParser.prototype["parseString"]=function(operation,set,builder){
	
	builder.setup();
	var operatorsSum = builder.getOperatorsSum();
	var functions = builder.getAvailableFunctions();
	
	var tok = splitAndKeep(operation,this.brackets);

	for(var index in tok){
	
		var token = tok[index];
		var indexB = this.brackets.indexOf(token);

		if (indexB == 0) {
			builder.openExpression();
		} else if (indexB == 1) {
			builder.closeExpression();
		} else {

			var tok2=splitAndKeep(token,operatorsSum);
			
			for(var indexJ in tok2){
				token = tok2[indexJ];

				if (!contains(operatorsSum,token)) {

					if (!contains(functions,token)) {

						builder.generateValue(token, set);
					} else {

						builder.dispatchFunction(token);
					}

				} else {
					builder.dispatchOperator(token);
				}
			}
		}
	}
	
};

var SFExpressionParser_parser = new SFExpressionParser();

function SFExpressionParser_getParser(){
	return SFExpressionParser_parser;
}


