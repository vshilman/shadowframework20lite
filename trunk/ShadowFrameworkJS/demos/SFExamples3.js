     

var baloonSurfaceFunction; 
var rectSurfaceFunction; 
var curtainSurfaceFunction; 


function MyTexCoord(du,dv,qu,qv){
	this.du=du;
	this.dv=dv;
	this.qu=qu;	
	this.qv=qv;	
}

inherit(MyTexCoord,SFUnoptimizedSurfaceFunctionUV);

MyTexCoord.prototype["getTexCoord"]=function(u,v){
	return new SFVertex2f(v*this.du,u*this.dv);
};

MyTexCoord.prototype["init"]=function(){
	//Do nothing
};

MyTexCoord.prototype["destroy"]=function(){
	//Do nothing
};	

MyTexCoord.prototype["getDu"]=function(){
	return this.du;
};	

MyTexCoord.prototype["getDv"]=function(){
	return this.dv;
};

MyTexCoord.prototype["setDu"]=function(du){
	this.du=du;
};	

MyTexCoord.prototype["setDv"]=function(dv){
	this.dv=dv;
};	

function checkBaloonSurface(){
	if(baloonSurfaceFunction===undefined){
		
		var surfaceFunction = new SFCurvedTubeFunction();
				
			var centralCurve=new SFBasisSpline2(false);
				centralCurve.add(new SFVertex3f(0.0, 0.67, 0));
				centralCurve.add(new SFVertex3f(0.0, 0.6, 0));
				centralCurve.add(new SFVertex3f(0.0, 0.4, 0));
				centralCurve.add(new SFVertex3f(0.0, 0.2, 0));
				centralCurve.add(new SFVertex3f(0.0, 0.1, 0));
				centralCurve.add(new SFVertex3f(0, 0, 0));
				//centralCurve.add(new SFVertex3f(0.0, 0.71, 0));
			
			surfaceFunction.setCentralCurve(centralCurve);
		
			var rayCurve=new SFBasisSpline2(false);
				rayCurve.add(new SFVertex3f(0.001, 0.67, 0));
				rayCurve.add(new SFVertex3f(0.2, 0.6, 0));
				rayCurve.add(new SFVertex3f(0.3, 0.4, 0));
				rayCurve.add(new SFVertex3f(0.2, 0.2, 0));
				rayCurve.add(new SFVertex3f(0.02, 0.1, 0));
				rayCurve.add(new SFVertex3f(0.1, 0.05, 0));
				//rayCurve.add(new SFVertex3f(0.01, 0.71, 0));
			
			surfaceFunction.setRayCurve(rayCurve);
			
		baloonSurfaceFunction=surfaceFunction;
	}
}


function checkRectSurface(){
	if(rectSurfaceFunction===undefined){
		
		var surfaceFunction = new SFBicurvedLoftedSurface();
				
			var aCurve=new SFLine(new SFVertex3f(0, 0),new SFVertex3f(0, 0.3));
			
			surfaceFunction.setA(aCurve);
		
			var bCurve=new SFLine(new SFVertex3f(0.3, 0),new SFVertex3f(0.3, 0.3));
			
			surfaceFunction.setB(bCurve);
			
		rectSurfaceFunction=surfaceFunction;
	}
}


function createBaloon(x,y,z,rotationY,r,g,b,scaleFactor){
	
	checkBaloonSurface();
	var surfaceFunction=baloonSurfaceFunction;
	
	var primitive=SFPipeline_getPrimitive("Triangle2PN");
	var gridGeometry=new SFQuadsGridGeometry();
	
	gridGeometry.getQuadsGrid().setNu( 8);
	gridGeometry.getQuadsGrid().setNv( 8);
				
	var surfaceGeometry=new SFParametrizedGeometry(gridGeometry);
	surfaceGeometry.setPrimitive(primitive);
	gridGeometry.setPrimitive(primitive.getConstructionPrimitive());
				
	surfaceGeometry.setMainGeometryFunction(surfaceFunction);
	
	SFInitiator_addInitiable(gridGeometry);
	SFInitiator_addInitiable(surfaceGeometry);
	
	var node=new SFObjectModel();
	
	node.getModel().setRootGeometry(surfaceGeometry);
	
		node.getModel().getTransformComponent().setProgram("BasicPNTransform");
		
		
		var programAsset=new SFProgramModuleStructures("BasicMat");
	
			var matStructure=SFPipeline_getProgramModule("BasicMat").getComponents()[0]
						.getStructures()[0].getStructure();
			
			var matArray=SFPipeline_getSfPipelineMemory().generateStructureData(matStructure); 
			var matData=[new SFVertex3f(r,g,b)];
			
						var matReference=new SFStructureReference(matArray,matArray.generateElement()); 
			
						var mat=new SFStructureData(matReference.getStructure());
						for (var i = 0; i < matData.length; i++) {
							mat.getValue(i).setValue(matData[i]);
						}
						matReference.setStructureData(mat);
			programAsset.getData().push(matReference);
		
		node.getModel().setMaterialComponent(programAsset);
		
		
		//node.getModel().getMaterialComponent().addData(new SFStructureReference(array, 0));
	
		var vertex=new SFVertex3f(x,y,z);
		node.getTransform().setPosition(vertex);
		var matrix=(new SFMatrix3f()).getRotationY(rotationY);
		if(scaleFactor!=undefined)
			matrix.mult(scaleFactor);
		node.getTransform().setOrientation(matrix);
		
	return node;
}


function createTexturedSurface(x,y,z,rotationY,texture,surfaceFunction,texCoordFunction){
	var primitive=SFPipeline_getPrimitive("Triangle2PNTxO");
	var gridGeometry=new SFQuadsGridGeometry();
	
	gridGeometry.getQuadsGrid().setNu( 16);
	gridGeometry.getQuadsGrid().setNv( 16);
				
	var surfaceGeometry=new SFParametrizedGeometry(gridGeometry);
	surfaceGeometry.setPrimitive(primitive);
	gridGeometry.setPrimitive(primitive.getConstructionPrimitive());
				
	surfaceGeometry.setMainGeometryFunction(surfaceFunction);
	if(texCoordFunction===undefined)
		texCoordFunction=new SFSimpleTexCoordGeometryuv(1,1);
	surfaceGeometry.setTexCoordGeometry(texCoordFunction);			
				//var texCoordDataset=this.texCoordFunction.retrieveDataset();
				//if(texCoordDataset!=null)
				//	surfaceGeometry.setTexCoordGeometry(texCoordDataset.getResource());
	
	SFInitiator_addInitiable(gridGeometry);
	SFInitiator_addInitiable(surfaceGeometry);
	
	var node=new SFObjectModel();
	
	node.getModel().setRootGeometry(surfaceGeometry);
	
		node.getModel().getTransformComponent().setProgram("BasicPNTx0Transform");
		node.getModel().getMaterialComponent().setProgram("TexturedMat");
			
			var bitmap=new SFBitmapTexture();
			bitmap.texture=texture;
			
			var texture=new SFTexture(bitmap,0);
			
		node.getModel().getMaterialComponent().getTextures().push(texture);
		//node.getModel().getMaterialComponent().addData(new SFStructureReference(array, 0));
	
		var vertex=new SFVertex3f(x,y,z);
		node.getTransform().setPosition(vertex);
		var matrix=(new SFMatrix3f()).getRotationY(rotationY);
		node.getTransform().setOrientation(matrix);
		
	return node;
}


function createTexturedBaloon(x,y,z,rotationY,texture,scaleFactor){
	checkBaloonSurface();
	var node= createTexturedSurface(x,y,z,rotationY,texture,baloonSurfaceFunction);
	
	if(scaleFactor!=undefined){
		var matrix=new SFMatrix3f();
		node.getTransform().getOrientation(matrix);
		matrix=matrix.MultMatrix(matrix.getAmpl(scaleFactor,scaleFactor,scaleFactor));
		node.getTransform().setOrientation(matrix);
	}
	
	return node;
}




function createTexturedRect(x,y,z,rotationY,texture,scaleFactor){
	checkRectSurface();
	var node= createTexturedSurface(x,y,z,rotationY,texture,rectSurfaceFunction,new MyTexCoord(1,1,0,0));
	
	if(scaleFactor!=undefined){
		var matrix=new SFMatrix3f();
		node.getTransform().getOrientation(matrix);
		matrix=matrix.MultMatrix(matrix.getAmpl(scaleFactor,scaleFactor,scaleFactor));
		node.getTransform().setOrientation(matrix);
	}
	
	return node;
}



function checkCurtainSurface(){
	if(curtainSurfaceFunction===undefined){
		
		var surfaceFunction = new SFTensorProductSurface();
		
			var productCurve=new SFBasisSpline2();
				productCurve.add(new SFVertex3f(0.0, 0.0, 0));
				productCurve.add(new SFVertex3f(0.0, 0.0, 0));
				productCurve.add(new SFVertex3f(0.0, 0.0, 0));
				productCurve.add(new SFVertex3f(0.0, 0.0, 0));
				productCurve.add(new SFVertex3f(0.0, 0.0, 0));
				
			surfaceFunction.setProductCurve(productCurve);
		
			var guidelines=new Array();
			guidelines[0]=new SFBasisSpline2(false);
				guidelines[0].add(new SFVertex3f(0.0, 0.0, 0.0));
				guidelines[0].add(new SFVertex3f(0.0, -0.2, 0.1));
				guidelines[0].add(new SFVertex3f(0.0, -0.4, 0.0));
				guidelines[0].add(new SFVertex3f(0.0, -0.8, 0.0));
			guidelines[1]=new SFBasisSpline2(false);
				guidelines[1].add(new SFVertex3f(0.15, 0.0, 0.1));
				guidelines[1].add(new SFVertex3f(0.15, -0.2, 0.0));
				guidelines[1].add(new SFVertex3f(0.15, -0.4, 0.1));
				guidelines[1].add(new SFVertex3f(0.15, -0.7, 0.0));
			guidelines[2]=new SFBasisSpline2(false);
				guidelines[2].add(new SFVertex3f(0.3, 0.0, 0.0));
				guidelines[2].add(new SFVertex3f(0.3, -0.2, -0.1));
				guidelines[2].add(new SFVertex3f(0.3, -0.4, 0.0));
				guidelines[2].add(new SFVertex3f(0.3, -0.8, -0.1));
			guidelines[3]=new SFBasisSpline2(false);
				guidelines[3].add(new SFVertex3f(0.45, 0.0, 0.0));
				guidelines[3].add(new SFVertex3f(0.45, -0.2, 0.1));
				guidelines[3].add(new SFVertex3f(0.45, -0.4, 0.0));
				guidelines[3].add(new SFVertex3f(0.45, -0.7, 0.1));
			guidelines[4]=new SFBasisSpline2(false);
				guidelines[4].add(new SFVertex3f(0.60, 0.0, -0.1));
				guidelines[4].add(new SFVertex3f(0.60, -0.2, 0.0));
				guidelines[4].add(new SFVertex3f(0.60, -0.4, -0.1));
				guidelines[4].add(new SFVertex3f(0.60, -0.7, 0.0));
			
			surfaceFunction.setGuideLines(guidelines);
		
			
		curtainSurfaceFunction=surfaceFunction;
	}
}




function createCurtainHolder(x,y,z,rotationY,scaleFactor,r,g,b){
	
			var primitive=SFPipeline_getPrimitive("Microtube2");
				
			var array = SFPipeline_getSfPipelineMemory().generatePrimitiveArray(primitive);
		
			var values0 = array.getPrimitiveData(0);
			values0.generateElements(15);
			values0.setElement(0, new SFVertex3f(0,0.01,0));
			values0.setElement(1, new SFVertex3f(0.5,0.01,0));
			values0.setElement(2, new SFVertex3f(1,0.01,0));
			
			var values1 = array.getPrimitiveData(1);
			values1.generateElements(1);
			values1.setElement(0, new SFValue1f(0.01));
			
			var indices=new SFPrimitiveIndices();
			array.generateElements(1);
			for(var i=0;i<1;i++){
				var indices0=[2*i,2*i+2,2*i+1,0,0,0];
				indices.primitiveIndices=indices0;	
				array.setElement(i, indices);
			}
			
			var geometry=new SFMeshGeometry();

			geometry.setArray(array);
			geometry.setPrimitive(array.getPrimitive());

			geometry.getMesh().evaluateRanges();
			geometry.setFirstElement(0);
			geometry.setLastElement(1);
			
			var surfaceGeometry=geometry;

			SFInitiator_addInitiable(surfaceGeometry);

			var node=new SFObjectModel();
			
			node.getModel().setRootGeometry(surfaceGeometry);
			
				node.getModel().getTransformComponent().setProgram("BasicPNTransform");
				
				var programAsset=new SFProgramModuleStructures("BasicMat");
			
					var matStructure=SFPipeline_getProgramModule("BasicMat").getComponents()[0]
								.getStructures()[0].getStructure();
					
					var matArray=SFPipeline_getSfPipelineMemory().generateStructureData(matStructure); 
					var matData=[new SFVertex3f(r,g,b)];
					
								var matReference=new SFStructureReference(matArray,matArray.generateElement()); 
					
								var mat=new SFStructureData(matReference.getStructure());
								for (var i = 0; i < matData.length; i++) {
									mat.getValue(i).setValue(matData[i]);
								}
								matReference.setStructureData(mat);
					programAsset.getData().push(matReference);
				
				node.getModel().setMaterialComponent(programAsset);
			
				var vertex=new SFVertex3f(x,y,z);
				node.getTransform().setPosition(vertex);
				var matrix=(new SFMatrix3f()).getRotationY(rotationY);
				node.getTransform().setOrientation(matrix);
		
	return node;
}


function createTexturedCurtain(x,y,z,rotationY,texture,scaleFactor){
	checkCurtainSurface();
	var node = createTexturedSurface(x,y,z,rotationY,texture,curtainSurfaceFunction,new MyTexCoord(1,1,0,0));
	
	var sceneNode=new SFReferenceNode();
	
	
	if(scaleFactor!=undefined){
		var matrix=new SFMatrix3f();
		node.getTransform().getOrientation(matrix);
		matrix=matrix.MultMatrix(matrix.getAmpl(scaleFactor,scaleFactor,scaleFactor));
		node.getTransform().setOrientation(matrix);
	}
	
	sceneNode.addNode(node);
	var holder=createCurtainHolder(x,y,z,rotationY,scaleFactor,1,1,0);
	sceneNode.addNode(holder);
	
	return sceneNode;
}


function createSupporter(x,y,z,rotationY,scaleFactor,r,g,b){
	
			var primitive=SFPipeline_getPrimitive("Microtube2");
				
			var array = SFPipeline_getSfPipelineMemory().generatePrimitiveArray(primitive);
		
			var values0 = array.getPrimitiveData(0);
			values0.generateElements(15);
			values0.setElement(0, new SFVertex3f(0,0,0));
			values0.setElement(1, new SFVertex3f(0,0.5,0));
			values0.setElement(2, new SFVertex3f(0,1.0,0));
			values0.setElement(3, new SFVertex3f(0,1.05,0));
			values0.setElement(4, new SFVertex3f(-0.05,1.05,0));
			values0.setElement(5, new SFVertex3f(-0.25,1.05,0));
			values0.setElement(6, new SFVertex3f(-0.45,1.05,0));
			values0.setElement(7, new SFVertex3f(-0.5,1.05,0));
			values0.setElement(8, new SFVertex3f(-0.5,1.00,0));
			values0.setElement(9, new SFVertex3f(-0.5,0.75,0));
			values0.setElement(10, new SFVertex3f(-0.5,0.55,0));
			values0.setElement(11, new SFVertex3f(-0.5,0.50,0));
			values0.setElement(12, new SFVertex3f(-0.45,0.50,0));
			values0.setElement(13, new SFVertex3f(-0.25,0.50,0));
			values0.setElement(14, new SFVertex3f(-0.05,0.50,0));
			
			var values1 = array.getPrimitiveData(1);
			values1.generateElements(1);
			values1.setElement(0, new SFValue1f(0.01));
			
			var indices=new SFPrimitiveIndices();
			array.generateElements(7);
			for(var i=0;i<7;i++){
				var indices0=[2*i,2*i+2,2*i+1,0,0,0];
				indices.primitiveIndices=indices0;	
				array.setElement(i, indices);
			}
			
			var geometry=new SFMeshGeometry();

			geometry.setArray(array);
			geometry.setPrimitive(array.getPrimitive());

			geometry.getMesh().evaluateRanges();
			geometry.setFirstElement(0);
			geometry.setLastElement(7);
			
			var surfaceGeometry=geometry;

			SFInitiator_addInitiable(surfaceGeometry);

			var node=new SFObjectModel();
			
			node.getModel().setRootGeometry(surfaceGeometry);
			
				node.getModel().getTransformComponent().setProgram("BasicPNTransform");
				
				var programAsset=new SFProgramModuleStructures("BasicMat");
			
					var matStructure=SFPipeline_getProgramModule("BasicMat").getComponents()[0]
								.getStructures()[0].getStructure();
					
					var matArray=SFPipeline_getSfPipelineMemory().generateStructureData(matStructure); 
					var matData=[new SFVertex3f(r,g,b)];
					
								var matReference=new SFStructureReference(matArray,matArray.generateElement()); 
					
								var mat=new SFStructureData(matReference.getStructure());
								for (var i = 0; i < matData.length; i++) {
									mat.getValue(i).setValue(matData[i]);
								}
								matReference.setStructureData(mat);
					programAsset.getData().push(matReference);
				
				node.getModel().setMaterialComponent(programAsset);
			
				var vertex=new SFVertex3f(x,y,z);
				node.getTransform().setPosition(vertex);
				var matrix=(new SFMatrix3f()).getRotationY(rotationY);
				node.getTransform().setOrientation(matrix);
		
	return node;
}

