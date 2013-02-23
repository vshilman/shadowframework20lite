

var SFExpressionBuilder_operatorSum = ",/*-+";
	
var SFExpressionBuilder_availableFunctions=[
	"dot","sqrt","sample","clamp","sin","cos","cross","normalize"
];

function SFExpressionBuilder(){
	this.data=new SFExpressionBuilderData();
	this.stack=new SFExpressionsBuilderStack(this.data);
	this.dispatchValue=false;
}


var SFExpressionBuilder_DEBUG=false;

SFExpressionBuilder.prototype["setup"]=function(){
			this.data.setExpressions(new Array());
			this.dispatchValue = false;
		};

SFExpressionBuilder.prototype["getOperatorsSum"]=function(){
			return SFExpressionBuilder_operatorSum;
		};

SFExpressionBuilder.prototype["getAvailableFunctions"]=function(){
			return SFExpressionBuilder_availableFunctions;
		};
	
SFExpressionBuilder.prototype["hasPriority"]=function(operator,otherOperator){
			var indexOfOperator = SFExpressionBuilder_operatorSum.indexOf(operator);
			var indexOfOtherOperator = SFExpressionBuilder_operatorSum.indexOf(otherOperator);
			return indexOfOtherOperator > indexOfOperator;
		};

SFExpressionBuilder.prototype["firstIndexOfSymbolInExpressions"]=function(symbol){
			for (var i = 0; i < this.data.getExpressions().length; i++) {
				if (this.data.getExpressions()[i].getElement()===(symbol.getElement())) {
					return i;
				}
			}
			return -1;
		};

SFExpressionBuilder.prototype["generateValue"]=function(element,set){
			var type = SFParameteri_GLOBAL_FLOAT;
			
				var register = SFPipelineRegister_getFromName(element);
			
				if(!(register===undefined)){
					type = register.getType();	
				} else {
					var parameter = undefined;

					for(var index in set){
						var sfParameteri=set[index];
						if(sfParameteri===undefined)
							alert("sfParameteri in undefined on element ("+element+") "+index+" "+set);
						else if (sfParameteri.getName()===(element)) {
							parameter = sfParameteri;
						}
					}
	
					if (parameter != undefined) {
						type = parameter.getType();
					} else {
						
						try {
								
							var f = parseFloat(element);
								
							if(f===f){
									
								if(f % 1 ==0)
									element = "" + f + ".0";
								else
									element = "" + f;
								type = SFParameteri_GLOBAL_FLOAT;	
							}
	
						} catch (e) {
								
							// throw new RuntimeException("Unknown Parameter "+element);
						}
					}
				}
			
			this.data.setLastValue(SFExpressionGeneratorKeeper_getKeeper().getGenerator()
					.getExpressionElement(element, type));
			this.dispatchValue = true;

		};

SFExpressionBuilder.prototype["getBuiltExpression"]=function(){
			
			if (this.dispatchValue) {
				if (this.data.getlSymbol() != null){
					this.data.setupLastSymbol(this);

					this.dispatchValue=false;
				}else {
					
					return this.data.getLastValue();
				}
			}

			return this.data.getExpressions()[0];
		};

SFExpressionBuilder.prototype["dispatchFunction"]=function(operator){
			var symbol = SFExpressionGeneratorKeeper_getKeeper().getGenerator()
				.getFunction(operator);
		
			if (this.data.getExpressions().length == 0) {
				if (symbol != null)
					this.data.getExpressions().push(symbol);
				
				this.data.setlSymbol(symbol);
			} else {
				var lastSymbol = getLast(this.data.getExpressions());
				// greater priority
				lastSymbol.addElement(symbol);
				this.data.getExpressions().push(symbol);
				this.data.setlSymbol(symbol);
			}
			this.dispatchValue = false;

		};
	
SFExpressionBuilder.prototype["dispatchOperator"]=function(operator){
			var symbol = SFExpressionGeneratorKeeper_getKeeper().getGenerator()
				.getOperator(operator);
		
			if (this.data.getExpressions().length == 0) {
				if (this.data.getLastValue() != null)
					symbol.addElement(this.data.getLastValue());
				if (symbol != null)
					this.data.getExpressions().push(symbol);
				this.data.setlSymbol(symbol);
			} else {

				var lastOperator = getLast(this.data.getExpressions()).getElement();
				if (this.hasPriority(operator, lastOperator)) {
					var lastSymbol = getLast(this.data.getExpressions());
					// greater priority
					lastSymbol.addElement(symbol);
					this.data.getExpressions().push(symbol);
					
					symbol.addElement(this.data.getLastValue());
					this.data.setlSymbol(symbol);
				} else if (this.hasPriority(lastOperator, operator)) {

					this.lastSymbol = getLast(this.data.getExpressions());
					var j = this.firstIndexOfSymbolInExpressions(symbol);
					if (j >= 0) {
						this.lastSymbol.addElement(this.data.getLastValue());
						for (var k = this.data.getExpressions().length - 1; k > j; k--) {
							remove(this.data.getExpressions(),k);
						}
						this.data.setlSymbol(this.data.getExpressions()[j]);
					} else {
						symbol.addElement(this.data.getExpressions()[0]);
						this.data.getExpressions().length=0;
						this.lastSymbol.addElement(this.data.getLastValue());
						this.data.getExpressions().push(symbol);
						
						this.data.setlSymbol(symbol);
					}
				} else {
					var lastSymbol = getLast(this.data.getExpressions());
					lastSymbol.addElement(this.data.getLastValue());
				}
			}
			this.dispatchValue = false;

		};

		
SFExpressionBuilder.prototype["openExpression"]=function(){
	
			this.stack.pushExpressions();

		};	

		
SFExpressionBuilder.prototype["closeExpression"]=function(){
			
			if (this.dispatchValue) {
				this.data.setupLastSymbol(this);
			}
			this.stack.popExpressions();
			this.dispatchValue = true;

		};	
	
