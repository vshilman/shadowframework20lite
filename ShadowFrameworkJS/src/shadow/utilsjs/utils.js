

function inherit(son,father){
	for(var element in father.prototype){
		son.prototype[element]=father.prototype[element];
	}
}


function getType(obj) {
  var typeData = obj.constructor.toString().match(/function\s*(\w+)/);
  return typeData[1];
}


function print(o){
    var str='\n';

    for(var p in o){
        if(typeof o[p] == 'string'){
            str+= p + ':' + o[p]+';    ';
        }else if(typeof o[p] != 'function'){
            str+= p + ':{' + print(o[p]) + '}    ';
        }
    }

    return str+'\n';
}

function htmlprint(o){
    var str='';

    str+='<ul>';
    for(var p in o){
        if(typeof o[p] == 'string'){
            str+= '<li>'+p + ':' + o[p]+';    </li>';
        }else{
            str+= '<li>'+p + ':{' + htmlprint(o[p]) + '}    </li>';
        }
    }
    str+='</ul>';

    return str;
}

function alertp(o){
	alert(print(o));
}


function htmlalertp(o){
	writehtml(htmlprint(o));
	//nonExistingFunction(23);
}

function cloneArray(inputArray){
	var outputArray=new Array();
	for(var i=0;i<inputArray.length;i++){
		outputArray[i]=inputArray[i];
	}
	return outputArray;
}

function contains(array,element){
	for(var i=0;i<array.length;i++){
		if(array[i]===element)
			return true;
	}
	return false;
}

function indexof(array,element){
	for(var i=0;i<array.length;i++){
		if(array[i]===element)
			return i;
	}
	return -1;
}

function getLast(array){
	return array[array.length-1];
}


function remove(array,position){
	var removed=array[position];
	for(var i=position;i<array.length-1;i++){
		array[i]=array[i+1];
	}
	array.length=array.length-1;
	return removed;
}


function addAll(output,input){
	for(var index in input){
		output.push(input[index]);
	}
}

function split(data,splits){
	var splits_=splits.split("");
	var array=new Array();
	array.push(data);
	for(var i=0;i<splits_.length;i++){
		var splitChar=splits_[i];
		var tempArray=new Array();
		for(var j=0;j<array.length;j++){
			var splitted=array[j].split(splitChar);
			for(var k=0;k<splitted.length;k++){
				if(splitted[k].length>0)
					tempArray.push(splitted[k]);
			}
		}
		array=tempArray;
	}
	return array;
}

function splitAndKeep(data,splits){
	var splits_=splits.split("");
	var array=new Array();
	array.push(data);
	for(var i=0;i<splits_.length;i++){
		var splitChar=splits_[i];
		var tempArray=new Array();
		for(var j=0;j<array.length;j++){
			var splitted=array[j].split(splitChar);
			for(var k=0;k<splitted.length;k++){
				if(k>0)
					tempArray.push(splitChar);
				if(splitted[k].length>0)
					tempArray.push(splitted[k]);
			}
		}
		array=tempArray;
	}
	return array;
}

function loadFile(src) {
	var testo = "Errore lettura file!!";
	var request = new XMLHttpRequest();
	request.open("GET", src, false);
	request.onreadystatechange = function() {
		if (request.readyState == 4) {
			testo = request.responseText;
		}
	};
	try{
		request.send();
	}catch(e){
		alert(e);
	}
	return testo;
}


function loadFileCallBack(f,callback){
	
	var reader = new FileReader();
	  
	reader.onload = function (event) {
  		
  		var contents = event.target.result;
  		callback(contents);
	};  
	  
	reader.readAsBinaryString(f);

}

function DirectDataCenterListener(){
	this.dataset=null;
}

DirectDataCenterListener.prototype["onDatasetAvailable"]=function(name, dataset){
	this.dataset=dataset;
};

function getAlreadyAvailableDataset(object){
	
	var listener = new DirectDataCenterListener();
	SFDataCenter_getDataCenter().makeDatasetAvailable(object,listener);
	if(listener.dataset!=null)
		return listener.dataset;
	return null;
}


var gl;

function initGL(canvas) {
	try {
		gl = canvas.getContext("experimental-webgl");
	} catch (e) {
		alert("Could not initialise WebGL, sorry :-(");
	}
}