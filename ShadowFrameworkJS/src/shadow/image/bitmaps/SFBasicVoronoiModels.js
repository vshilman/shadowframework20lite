

function SFBasicVoronoiModels(getValueFunction){
	this.getValueFunction=getValueFunction;
}


SFBasicVoronoiModels.prototype["init"]=function(){
	//Nothing To Do	
};

SFBasicVoronoiModels.prototype["destroy"]=function(){
	//Nothing To Do
};


SFBasicVoronoiModels.prototype["getValue"]=function(index,index2,distance, distance2,
				 normalizedDistance, normalizedDistance2){
	return this.getValueFunction(index,index2,distance, distance2,
				 normalizedDistance, normalizedDistance2);
};


SFBasicVoronoiModels_MODEL1_getValue=function(index,index2,distance, distance2,
				 normalizedDistance, normalizedDistance2){
	return index2*0.05;
};
var SFBasicVoronoiModels_MODEL1 = new SFBasicBitmapFunctions(SFBasicVoronoiModels_MODEL1_getValue);


SFBasicVoronoiModels_MODEL2_getValue=function(index,index2,distance, distance2,
				 normalizedDistance, normalizedDistance2){
	var T=distance*4;
	return T*T*T*T;
};
var SFBasicVoronoiModels_MODEL2 = new SFBasicBitmapFunctions(SFBasicVoronoiModels_MODEL2_getValue);


SFBasicVoronoiModels_MODEL3_getValue=function(index,index2,distance, distance2,
				 normalizedDistance, normalizedDistance2){
	var T=distance/distance2;
			return T;
};
var SFBasicVoronoiModels_MODEL3 = new SFBasicBitmapFunctions(SFBasicVoronoiModels_MODEL3_getValue);


SFBasicVoronoiModels_MODEL4_getValue=function(index,index2,distance, distance2,
				 normalizedDistance, normalizedDistance2){
	var distanceM=0.5*(distance+distance2);
			var delta=0.01;
			if(distanceM-distance>delta){
				return 1;
			}
			
			return 0;
};	
var SFBasicVoronoiModels_MODEL4 = new SFBasicBitmapFunctions(SFBasicVoronoiModels_MODEL4_getValue);



SFBasicVoronoiModels_MODEL5_getValue=function(index,index2,distance, distance2,
				 normalizedDistance, normalizedDistance2){
	var distanceM=0.5*(normalizedDistance+normalizedDistance2);
			var delta=0.01;
			if(distanceM-normalizedDistance>delta){
				return 1;
			}
			
			return 0;
};
var SFBasicVoronoiModels_MODEL5 = new SFBasicBitmapFunctions(SFBasicVoronoiModels_MODEL5_getValue);



SFBasicVoronoiModels_MODEL6_getValue=function(index,index2,distance, distance2,
				 normalizedDistance, normalizedDistance2){
	return normalizedDistance*4;
};
var SFBasicVoronoiModels_MODEL6 = new SFBasicBitmapFunctions(SFBasicVoronoiModels_MODEL6_getValue);


SFBasicVoronoiModels_MODEL7_getValue=function(index,index2,distance, distance2,
				 normalizedDistance, normalizedDistance2){
	return normalizedDistance*4;
};
var SFBasicVoronoiModels_MODEL7= new SFBasicBitmapFunctions(SFBasicVoronoiModels_MODEL7_getValue);



var SFBasicVoronoiModels_valueso=[
	SFBasicVoronoiModels_MODEL1,
	SFBasicVoronoiModels_MODEL2,
	SFBasicVoronoiModels_MODEL3,
	SFBasicVoronoiModels_MODEL4,
	SFBasicVoronoiModels_MODEL5,
	SFBasicVoronoiModels_MODEL6,
	SFBasicVoronoiModels_MODEL7,
];

function SFBasicVoronoiModels_values(){
	return SFBasicVoronoiModels_valueso;
}
	
