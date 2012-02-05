
function SFQuadsSurfaceGeometry(){
}

SFQuadsSurfaceGeometry.prototype = {

	initSplits:function(N_u, N_v){
		this.Nu  = N_u;
		this.Nv  = N_v;
		u_splits  = new .float[N_u];
		v_splits  = new .float[N_v];
		 float  stepU = 1.0f/(N_u-1.0f);
		 float  stepV = 1.0f/(N_v-1.0f);
		for ( int  i  =  0 ; i   < N_u ; i++ ){
		u_splits[i]  = i*stepU;
	}
		for ( int  i  =  0 ; i   < N_v ; i++ ){
		v_splits[i]  = i*stepV;
	}
	},

	compile:function(){
	//clearElements();//Warning: Not well Identified 
		 int  pr = extractor.getPrimitivesNumber();
		updateElements(Nu,Nv,pr);
		 SFPrimitive  primitive = getPrimitive();
	//Remember to introduce 'block' concept into pipeline		List<Entry<SFPipelineRegister, SFProgramComponent>> blocks=primitive.getPrimitiveMap();//Warning: Not well Identified 
	//for each blocks, what should i do?		SFPrimitiveIndices[] indices=new SFPrimitiveIndices[pr];//Warning: Not well Identified 
		indices[0]  = new .SFPrimitiveIndices(primitive);
		for ( int  k  =  1 ; k   < pr ; k++ ){
		indices[k]  = indices[0].clone();
	}
		try{
		 int  index = 0;
		getArray().generateElements(pr*(Nu-1)*(Nv-1));
	//for (Entry<SFPipelineRegister, SFProgramComponent> entry : blocks);//Warning: Not well Identified 
	//Iterate over all the blocks..				SFPipelineRegister register=entry.getKey();//Warning: Not well Identified 
		 SFArray<SFValuenf>  values = getArray().getPrimitiveData(index);
		 Blocks  block   = getBlock(register);
		compileBlockIndices(pr, indices, index, values,block);
		index++;
	//}
	}
		catch (SFArrayElementException e){
	// TODO Auto-generated catch block			e.printStackTrace();//Warning: Not well Identified 
	}
	},

	getBlock:function(register){
		 String  registerName = register.getName();
		 char[]  rName = registerName.toCharArray();
		 Blocks  block = Blocks.POSITION;
		 if ( rName[0]=='N' ){
		block  = Blocks.NORMAL;
	}
		 else if ( rName.length>1 && rName[0]=='T' && rName[1]=='x' ){
		block  = Blocks.TXO;
	}
		 else if ( rName.length>1 && rName[0]=='d' && rName[1]=='u' ){
		block  = Blocks.DU;
	}
		 else if ( rName.length>1 && rName[0]=='d' && rName[1]=='v' ){
		block  = Blocks.DV;
	}
		return ,block;
	},

	updateElements:function(Nu, Nv, pr){
		 int  countElement = 0;
		for ( int  i  =  0 ; i   < (Nu-1)*(Nv-1) ; i++ ){
	for(int k=0;k<pr;k++)				countElement++;//Warning: Not well Identified 
	}
	//if(getLastElement()-getFirstElement()!=countElement);//Warning: Not well Identified 
	if(getLastElement()>getFirstElement())				getArray().eraseElements(getFirstElement(),getLastElement()-getFirstElement());//Warning: Not well Identified 
		 int  firstElement = getArray().generateElements(countElement);
		setFirstElement(firstElement);
		setLastElement(firstElement+countElement);
	//}
	},

	getDataArray:function(Nu, Nv, n1, block){
	SFValuenf vertices[]=new SFValuenf[((Nv-1)*n1+1)*((Nu-1)*n1+1)];//Warning: Not well Identified 
		 float  stepn1 = 1.0f/n1;
		 int  index = 0;
		for ( int  i=0 ; i < (Nv-1)*n1+1 ; i++ ){
		 int  I = i/n1;
		 int  Ires = i-n1*(I);
	float v=v_splits[I]+(Ires>0?Ires*stepn1*(v_splits[I+1]-v_splits[I]):0);//Warning: Not well Identified 
		for ( int  j=0 ; j < (Nu-1)*n1+1 ; j++ ){
		 int  J = j/n1;
		 int  Jres = j-n1*(J);
	float u=u_splits[J]+(Jres>0?Jres*stepn1*(u_splits[J+1]-u_splits[J]):0);//Warning: Not well Identified 
		switch (block){
	case POSITION: vertices[index]=function.getPosition(u, v);//Warning: Not well Identified 
		break;
	case NORMAL: vertices[index]=function.getNormal(u, v);//Warning: Not well Identified 
		break;
	case DU: vertices[index]=function.getDu(u, v);//Warning: Not well Identified 
		break;
	case DV: vertices[index]=function.getDv(u, v);//Warning: Not well Identified 
		break;
	case TXO: SFVertex3f vertex=function.getPosition(u, v);//Warning: Not well Identified 
		vertices[index]  = texCoord.getTexCoord(u, v,vertex.getX(),vertex.getY(),vertex.getZ());
		break;
	}
		index++;
	}
	}
		return ,vertices;
	},

	getU_splits:function(){
		return ,u_splits;
	},

	setU_splits:function(uSplits){
		u_splits    = uSplits;
	},

	getV_splits:function(){
		return ,v_splits;
	},

	setV_splits:function(vSplits){
		v_splits    = vSplits;
	}

};