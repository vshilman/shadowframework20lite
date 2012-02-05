
function SFCurvedTubeFunction(){
}

SFCurvedTubeFunction.prototype = {

	evalCurve:function(v, pts, P, dPdv){
	int v_index=(int)(v*pts.size());//Warning: Not well Identified 
	if(v_index==pts.size())			v_index--;//Warning: Not well Identified 
		 float  t = (v*pts.size())-v_index;
		 if ( v_index==0 ){
		 SFVertex3f  A = pts.get(0);
		 SFVertex3f  B = SFVertex3f.middle(pts.get(0),pts.get(1));
		P.set3f(A);
		P.mult(1-t);
		P.addMult3f(t, B);
		dPdv.set3f(B);
		dPdv.subtract3f(A);
	//d2Pdv2.set(0,0,0);//Warning: Not well Identified 
	}
	//else if(v_index==pts.size()-1);//Warning: Not well Identified 
		 SFVertex3f  A = SFVertex3f.middle(pts.get(v_index-1),pts.get(v_index));
		 SFVertex3f  B = pts.get(v_index);
		P.set3f(A);
		P.mult(1-t);
		P.addMult3f(t, B);
		dPdv.set3f(B);
		dPdv.subtract3f(A);
	//d2Pdv2.set(0,0,0);//Warning: Not well Identified 
	//}
		else{
		 SFVertex3f  A = SFVertex3f.middle(pts.get(v_index-1), pts.get(v_index));
		 SFVertex3f  B = pts.get(v_index);
		 SFVertex3f  C = SFVertex3f.middle(pts.get(v_index), pts.get(v_index+1));
		P.set3f(A);
		P.mult((1-t)*(1-t));
		P.addMult3f(2*t*(1-t), B);
		P.addMult3f(t*t, C);
		dPdv.set3f(A);
		dPdv.mult(-2*(1-t));
		dPdv.addMult3f(2-4*t, B);
		dPdv.addMult3f(2*t, C);
	//d2Pdv2.set(A);//Warning: Not well Identified 
		d2Pdv2.mult(2);
		d2Pdv2.addMult3f(-4,B);
		d2Pdv2.addMult3f(2, C);
	}
	},

	evalAll:function(v){
		 if ( v!=lastV ){
		evalCurve(v, Cc, Ccv, dCcdv);
		evalCurve(v, Rc, Rcv, new SFVertex3f());
		Vec1v.set3f(Rcv);
		Vec1v.subtract3f(Ccv);
		Vec2v.set3f(dCcdv.cross(Vec1v));
		Vec2v.normalize3f();
		Vec2v.mult((float)(Math.sqrt(Vec1v.dot3f(Vec1v))));
		lastV  = v;
	}
	},

	getX:function(u, v){
		evalAll(v);
		cos  = (float).(Math.cos(2*Math.PI*u));
		sin  = (float).(Math.sin(2*Math.PI*u));
		return ,Ccv.getX()+cos*Vec1v.getX()+sin*Vec2v.getX();
	},

	getY:function(u, v){
		return ,Ccv.getY()+cos*Vec1v.getY()+sin*Vec2v.getY();
	},

	getZ:function(u, v){
		return ,Ccv.getZ()+cos*Vec1v.getZ()+sin*Vec2v.getZ();
	},

	generateNewDatasetInstance:function(){
	// TODO Auto-generated method stub		return null;//Warning: Not well Identified 
	},

	getCode:function(){
	// TODO Auto-generated method stub		return null;//Warning: Not well Identified 
	},

	readFromStream:function(stream){
	// TODO Auto-generated method stub;//Warning: Not well Identified 
	},

	writeOnStream:function(stream){
	// TODO Auto-generated method stub;//Warning: Not well Identified 
	}

};