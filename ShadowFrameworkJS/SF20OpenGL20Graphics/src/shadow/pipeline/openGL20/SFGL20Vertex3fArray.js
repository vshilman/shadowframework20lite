
function SFGL20Vertex3fArray(){
}

SFGL20Vertex3fArray.prototype = {

	assignValues:function(writing, reading){
		writing.set3f(reading);
	},

	generateGenericElement:function(){
		return ,new ,SFVertex3f(0, 0, 0);
	}

};