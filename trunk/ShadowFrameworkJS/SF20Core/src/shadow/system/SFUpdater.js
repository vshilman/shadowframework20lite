
function SFUpdater(){
}

SFUpdater.prototype = {

	addUpdatable:function(updatable){
	if(!updater.updatables.contains(updatable))			updater.updatables.add(updatable);//Warning: Not well Identified 
	},

	removeUpdatable:function(updatable){
		if(updater.updatables.contains(updatable))			updater.updatables.remove(updatable);
	},

	refresh:function(){
	//for (Iterator<SFUpdatable> iterator=updater.updatables.iterator();iterator				.hasNext(););//Warning: Not well Identified 
		 SFUpdatable  initiable = (SFUpdatable) iterator.next();
		initiable.update();
	//}
	}

};