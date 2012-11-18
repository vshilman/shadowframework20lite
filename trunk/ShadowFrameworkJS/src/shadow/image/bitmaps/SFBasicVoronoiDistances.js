

function SFBasicVoronoiDistances(getDistanceFunction){
	this.getDistanceFunction=getDistsanceFunction;	
}


SFBasicVoronoiDistances.prototype["init"]=function(){
	//Nothing To Do	
};

SFBasicVoronoiDistances.prototype["destroy"]=function(){
	//Nothing To Do
};

SFBasicVoronoiDistances.prototype["distance"]=function(u,v,vertex){
	return this.getDistanceFunction(u,v,vertex);
};


SFBasicVoronoiDistances_DISTANCE_distance=function(u,v,vertex){
	var du=vertex.getX()-u;
	var dv=vertex.getY()-v;
	return Math.sqrt(du*du+dv*dv);
};
var SFBasicVoronoiDistances_DISTANCE = new SFBasicBitmapFunctions(SFBasicVoronoiDistances_DISTANCE_distance);


SFBasicVoronoiDistances_DISTANCE2_distance=function(u,v,vertex){
	var du=vertex.getX()-u;
	var dv=vertex.getY()-v;
	return (du*du+dv*dv);
};
var SFBasicVoronoiDistances_DISTANCE2 = new SFBasicBitmapFunctions(SFBasicVoronoiDistances_DISTANCE2_distance);


SFBasicVoronoiDistances_DISTANCE1_distance=function(u,v,vertex){
	var du=vertex.getX()-u;
	var dv=vertex.getY()-v;
	return Math.abs(du+dv);
};
var SFBasicVoronoiDistances_DISTANCE1 = new SFBasicBitmapFunctions(SFBasicVoronoiDistances_DISTANCE1_distance);

var SFBasicVoronoiDistances_valueso=[
	SFBasicVoronoiDistances_DISTANCE,
	SFBasicVoronoiDistances_DISTANCE1,
	SFBasicVoronoiDistances_DISTANCE2
];

function SFBasicVoronoiDistances_values(){
	return SFBasicVoronoiDistances_valueso;
}
	

