//Java to JS on 06/02/2012

function SFUpdater(){
	this.updatables=new Array();
}

var sfUpdaterSingletonInstance=new SFUpdater();


function SFUpdater_addUpdatable(updatable){
	if (!(updatable in sfUpdaterSingletonInstance.updatables))			
		sfUpdaterSingletonInstance.updatables.push(initiable);//Warning: Not well Identified 
}



function SFUpdater_removeUpdatable(updatable){
	for(var i=0; i<sfUpdaterSingletonInstance.updatables.length; i++) {
        if(sfUpdaterSingletonInstance.updatables[i] == updatable) {
        	sfUpdaterSingletonInstance.updatables.splice(i, 1);
            break;
        }
    }
}

function SFUpdater_refresh(){
	for(updatable in sfUpdaterSingletonInstance.updatables){
		sfUpdaterSingletonInstance.updatables[updatable].update();
	}
	sfUpdaterSingletonInstance.updatables=[];
}
