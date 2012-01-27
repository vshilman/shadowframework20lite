
function SFArrayElementException(element, additionalMessage){
	super(element.getClass().getSimpleName()+genericMessage+":"+additionalMessage);//Warning: Not well Identified 
		this.element=element;
		this.additionalMessage=additionalMessage;
}

SFArrayElementException.prototype = {

	getElement:function(){
		return this.element;
	},

	getAdditionalMessage:function(){
		return this.additionalMessage;
	}

};