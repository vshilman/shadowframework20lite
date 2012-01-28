
function SFArrayElementException(element, additionalMessage){
	super(element.getClass().getSimpleName()+genericMessage+":"+additionalMessage);//Warning: Not well Identified 
		this.element=element;
		this.additionalMessage=additionalMessage;
}

SFArrayElementException.prototype = {
};