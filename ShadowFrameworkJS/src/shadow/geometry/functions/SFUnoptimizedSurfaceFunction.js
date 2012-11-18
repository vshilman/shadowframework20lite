

function SFUnoptimizedSurfaceFunction(){
	
}
	
		
SFUnoptimizedSurfaceFunction.prototype["extractParametrizedModel"]=function(parameters,range,array,block,gridIndex){
	var position=array.getPrimitiveData(gridIndex).generateElements(range.getSize());
	this.updateParametrizedModel(position, parameters, range, array, block, gridIndex);
	return position;
};


SFUnoptimizedSurfaceFunction.prototype["updateParametrizedModel"]=function(position,parameters,range,array,block,gridIndex){
	
	var uv=new SFVertex2f(0, 0);
	var primitiveData=array.getPrimitiveData(gridIndex);
	var value=primitiveData.generateSample();
	for (var i = 0; i < range.getSize(); i++) {
			parameters.getElement(range.getPosition()+i, uv);
		var u=uv.getX();
		var v=uv.getY();
		
		if(block==SFPrimitiveBlock_NORMAL){
			value.setValue(this.getNormal(u, v));
		}else if(block==SFPrimitiveBlock_DU){
			value.setValue(this.getDu(u, v));
		}else if(block==SFPrimitiveBlock_DV){
			value.setValue(this.getDv(u, v));
		}else {
			value.setValue(this.getPosition(u, v)); 
		}
		
		primitiveData.setElement(position+i, value);
	}
	
};

SFUnoptimizedSurfaceFunction.prototype["getPosition"]=function(u,v){
	var pos=new SFVertex3f(this.getX(u, v),this.getY(u, v),this.getZ(u, v));
	return pos;
};

SFUnoptimizedSurfaceFunction.prototype["getPositionW"]=function(u,v,write){
	write.set3f(getX(u, v),getY(u, v),getZ(u, v));
};


SFUnoptimizedSurfaceFunction.prototype["getDu"]=function(u,v){
	var eps=0.001;
	var p1=this.getPosition(u-eps, v);
	var p2=this.getPosition(u+eps, v);
	p2.subtract3f(p1);
	p2.mult(1.0/(2*eps));
	return p2;
};

SFUnoptimizedSurfaceFunction.prototype["getDv"]=function(u,v){
	var eps=0.001;
	var p1=this.getPosition(u, v);
	var p2=this.getPosition(u, v+eps);
	p2.subtract3f(p1);
	p2.mult(1.0/eps);
	return p2;
};

SFUnoptimizedSurfaceFunction.prototype["getNormal"]=function(u,v){
	var normal=this.getDu(u, v).cross(this.getDv(u, v));
	normal.normalize3f();
	return normal;
};

/*
@Override
public void updateRectangularModel(SFRectangularGrid<SFValuenf[]> values, float[] us,
		float[] vs, SFSurfaceInfo[] infos) {
	for (int gridIndex = 0; gridIndex < infos.length; gridIndex++) {
		if(infos[gridIndex]==SFSurfaceInfo.POSITION){
			for (int j = 0; j < values.getWidth(); j++) {
				for (int k = 0; k < values.getHeight(); k++) {
					values.getValue(k, j)[gridIndex].set(getPosition(us[j], vs[k]));
				}
			}
		}else if(infos[gridIndex]==SFSurfaceInfo.NORMAL){
			for (int j = 0; j < values.getWidth(); j++) {
				for (int k = 0; k < values.getHeight(); k++) {
					values.getValue(k, j)[gridIndex].set(getNormal(us[j], vs[k]));
				}
			}
		}else if(infos[gridIndex]==SFSurfaceInfo.DU){
			for (int j = 0; j < values.getWidth(); j++) {
				for (int k = 0; k < values.getHeight(); k++) {
					values.getValue(k, j)[gridIndex].set(getDu(us[j], vs[k]));
				}
			}
		}else if(infos[gridIndex]==SFSurfaceInfo.DV){
			for (int j = 0; j < values.getWidth(); j++) {
				for (int k = 0; k < values.getHeight(); k++) {
					values.getValue(k, j)[gridIndex].set(getDv(us[j], vs[k]));
				}
			}
		}
	}	
}
private static final float eps=0.01f;

public abstract float getX(float u,float v);
public abstract float getY(float u,float v);
public abstract float getZ(float u,float v);


*/