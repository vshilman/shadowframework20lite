

function SFVoronoiBitmapFunction(){
	this.centers=new Array();
}



SFVoronoiBitmapFunction.prototype["getModel"]=function(){
	return this.model;
};

SFVoronoiBitmapFunction.prototype["setModel"]=function(model){
	this.model=model;
};

SFVoronoiBitmapFunction.prototype["getCenters"]=function(){
	return this.centers;
};

SFVoronoiBitmapFunction.prototype["setCenters"]=function(centers){
	for(var i=0;i<centers.length;i++){
		this.centers.push(centers[i]);
	}
};

SFVoronoiBitmapFunction.prototype["getDistance"]=function(){
	return this.distance;
};

SFVoronoiBitmapFunction.prototype["setDistance"]=function(distance){
	this.distance=distance;
};

SFVoronoiBitmapFunction.prototype["projectVertex"]=function(toProject,index1,index2){
	
		var dir1=new SFVertex2f(toProject);
		var dir2=new SFVertex2f(centers.get(index2));
		
		dir1.subtract2f(centers.get(index1));
		dir2.subtract2f(centers.get(index1));

		dir2.normalize2f();
		var scalar=dir1.dot2f(dir2);
		dir2.mult2f(scalar);
		dir2.add2f(centers[index1]);
		
		return dir2;
};

SFVoronoiBitmapFunction.prototype["destroy"]=function(){
	//Do nothing
};

SFVoronoiBitmapFunction.prototype["init"]=function(){
	//Do nothing
};
	
SFVoronoiBitmapFunction.prototype["getValue"]=function(u,v){

		var distance=1000;
		var index=0;
		var distance2=1000;
		var index2=0;
		 
		for (var i = 0; i < this.centers.size(); i++) {
			var distancei=this.distance.distance(u,v,this.centers[i]);
			if(distancei<distance2){
				distance2=distancei;
				index2=i;
			}
			if(distancei<distance){
				distance2=distance;
				index2=index;
				distance=distancei;
				index=i;
			}
		}
		
		var projected=this.projectVertex(new SFVertex2f(u,v), index, index2);
		
		return this.model.getValue(index, index2, distance, distance2, 
				SFVertex2f_getDistance(projected, centers[index]),
				SFVertex2f_getDistance(projected, centers[index2]));
};
	

