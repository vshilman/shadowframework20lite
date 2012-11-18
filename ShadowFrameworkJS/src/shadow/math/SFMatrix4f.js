
function SFMatrix4f(){
}

SFMatrix4f.prototype["cloneValue"]=function(){
	return new SFMatrix4f(				
			v[0],v[1],v[2],v[3],				
			v[4],v[5],v[6],v[7],				
			v[8],v[9],v[10],v[11],				
			v[12],v[13],v[14],v[15]);
};

SFMatrix4f.prototype["getIdentity"]=function(){
	return new SFMatrix4f(				
			1,0,0,0,
			0,1,0,0,
			0,0,1,0,
			0,0,0,1	);
};
