
function SFGL20Vertex2fArray(){
}

SFGL20Vertex2fArray.prototype = {

	assignValues:function(writing, reading){
		writing.set2f(reading);
	},

	generateGenericElement:function(){
		return ,new ,SFVertex2f(0, 0);
	}

};