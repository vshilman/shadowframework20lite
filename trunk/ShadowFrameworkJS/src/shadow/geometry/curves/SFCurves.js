

var SFCurves_rec3=1.0/3.0;

function SFCurves_generateBezier33f(ax,ay,az,dx,dy,dz){
	var bezier=new SFBezier3();
	bezier.getControlPoint(0).setValue(new SFVertex3f(ax,ay,az));
	bezier.getControlPoint(1).setValue(new SFVertex3f(SFCurves_rec3*(ax*2+dx),SFCurves_rec3*(ay*2+dy),SFCurves_rec3*(az*2+dz)));
	bezier.getControlPoint(2).setValue(new SFVertex3f(SFCurves_rec3*(ax+dx*2),SFCurves_rec3*(ay+dy*2),SFCurves_rec3*(az+dz*2)));
	bezier.getControlPoint(3).setValue(new SFVertex3f(dx,dy,dz));
		
	return bezier;	
}


function SFCurves_generateBezier33f2(ax,ay,az,mx,my,mz,dx,dy,dz){
	var bezier=new SFBezier3();
	bezier.getControlPoint(0).setValue(new SFVertex3f(ax,ay,az));
	bezier.getControlPoint(1).setValue(new SFVertex3f(SFCurves_rec3*(mx*2+ax),SFCurves_rec3*(my*2+ay),SFCurves_rec3*(mz*2+az)));
	bezier.getControlPoint(2).setValue(new SFVertex3f(SFCurves_rec3*(dx+mx*2),SFCurves_rec3*(dy+my*2),SFCurves_rec3*(dz+mz*2)));
	bezier.getControlPoint(3).setValue(new SFVertex3f(dx,dy,dz));
	return bezier;	
}


