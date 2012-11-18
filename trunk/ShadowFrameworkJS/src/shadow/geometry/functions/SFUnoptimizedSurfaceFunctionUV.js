

function SFUnoptimizedSurfaceFunctionUV(){
	
}


SFUnoptimizedSurfaceFunctionUV.prototype["updateRectangularModel"]=function(values,us,vs,infos){
	for (var gridIndex = 0; gridIndex < infos.length; gridIndex++) {
		if(infos[gridIndex]==SFSurfaceInfo_POSITION){
			for (var j = 0; j < values.getWidth(); j++) {
				for (var k = 0; k < values.getHeight(); k++) {
					values.getValue(k, j)[gridIndex].set(getTexCoord(us[j], vs[k]));
				}
			}
		}
	}
			
};
	
SFUnoptimizedSurfaceFunctionUV.prototype["extractParametrizedModel"]=function(parameters,range,array,block,gridIndex){
	
	var position=array.getPrimitiveData(gridIndex).generateElements(range.getSize());
	this.updateParametrizedModel(position, parameters, range, array, block, gridIndex);
	return position;
};	
	
SFUnoptimizedSurfaceFunctionUV.prototype["updateParametrizedModel"]=function(position,parameters,range,
	array,block,gridIndex){

		var uv=new SFVertex2f(0, 0);
		var primitiveData=array.getPrimitiveData(gridIndex);
		var value=primitiveData.generateSample();
		for (var i = 0; i < range.getSize(); i++) {
			parameters.getElement(range.getPosition()+i, uv);
			var u=uv.getX();
			var v=uv.getY();
			var texCoord=this.getTexCoord(u,v);
			value.set(texCoord);
			primitiveData.setElement(position+i, value);
		}
		
};
	

