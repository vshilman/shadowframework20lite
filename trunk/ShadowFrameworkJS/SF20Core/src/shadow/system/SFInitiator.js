
function SFInitiator(){
}

SFInitiator.prototype = {

	addInitiable:function(initiable){
	if (!initiator.initiables.contains(initiable))			initiator.initiables.add(initiable);//Warning: Not well Identified 
	},

	solveInitiables:function(){
	//for (Iterator<SFInitiable> iterator=initiator.initiables.iterator();iterator				.hasNext(););//Warning: Not well Identified 
		 SFInitiable  initiable = (SFInitiable) iterator.next();
		initiable.init();
	//}
		initiator.initiables.clear();
	}

};