//Java to JS on 06/02/2012

function SFInitiator(){
	this.initiables=new Array();
	this.destroyables=new Array();
}

var sfInitiatorSingletonInstance=new SFInitiator();

function SFInitiator_addInitiable(initiable){
	if (!(initiable in sfInitiatorSingletonInstance.initiables))			
		sfInitiatorSingletonInstance.initiables.push(initiable);//Warning: Not well Identified 
}


function SFInitiator_addDestroyable(destroyable){
	if (!(initiable in sfInitiatorSingletonInstance.destroyables))			
		sfInitiatorSingletonInstance.destroyables.push(destroyable);//Warning: Not well Identified 
}

function SFInitiator_solveInitiables(){
	
	for(destroyableIndex in sfInitiatorSingletonInstance.destroyables){
		sfInitiatorSingletonInstance.destroyables[destroyableIndex].destroy();
	}
	sfInitiatorSingletonInstance.destroyable=[];
	
	
	for(initiableIndex in sfInitiatorSingletonInstance.initiables){
		sfInitiatorSingletonInstance.initiables[initiableIndex].init();
	}

	sfInitiatorSingletonInstance.initiables=[];
}
