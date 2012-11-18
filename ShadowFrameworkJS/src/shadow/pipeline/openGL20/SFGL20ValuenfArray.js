function SFGL20ValuenfArray(n){
	this.data=new Array();
	this.n=n;
}

inherit(SFGL20ValuenfArray,SFGL20ListData);


SFGL20ValuenfArray.prototype["assignValues"]=function(writing,reading){
	writing.setValue(reading);
};

SFGL20ValuenfArray.prototype["generateGenericElement"]=function(){
		return new SFValuenf(this.n);
};
