//Java to JS on 21/03/2012

function SFExpressionGeneratorKeeper(){
	this.generator=new SFBasicExpressionGenerator();
}

SFExpressionGeneratorKeeper.prototype["getGenerator"]=function(){
			return this.generator;
		};

SFExpressionGeneratorKeeper.prototype["setGenerator"]=function(generator){
			this.generator = generator;
		};

//Was originally a Singleton
var SFExpressionGeneratorKeeper_keeper=new SFExpressionGeneratorKeeper();

function SFExpressionGeneratorKeeper_getKeeper(){
	return SFExpressionGeneratorKeeper_keeper;
}
