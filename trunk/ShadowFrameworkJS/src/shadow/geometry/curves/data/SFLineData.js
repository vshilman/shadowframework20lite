


function SFLineData(){
	this.setup();
}

inherit(SFLineData,SFCurvesVerticesData);

SFLineData.prototype["buildResource"]=function(){
	var line=new SFLine<SFValuenf>(0);
	var dataset=vertices.retrieveDataset(this);
	
	var iterator=dataset.getResource().getIterator();
	var A=new SFVertex3f();
	var B=new SFVertex3f();
	iterator.getNext(A);
	iterator.getNext(B);
	line.setControlPoint(A, 0);
	line.setControlPoint(B, 1);
	
	return line;
};

SFLineData.prototype["generateNewDatasetInstance"]=function(){
	return new SFLineData();
};	





