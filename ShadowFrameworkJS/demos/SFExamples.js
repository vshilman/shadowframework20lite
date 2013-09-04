     

var baloonSurfaceFunction; 
var rectSurfaceFunction; 

function checkBaloonSurface(){
	if(baloonSurfaceFunction===undefined){
		
		var surfaceFunction = new SFCurvedTubeFunction();
				
			var centralCurve=new SFBasisSpline2(false);
				centralCurve.add(new SFVertex3f(0, 0, 0));
				centralCurve.add(new SFVertex3f(0.0, 0.1, 0));
				centralCurve.add(new SFVertex3f(0.0, 0.2, 0));
				centralCurve.add(new SFVertex3f(0.0, 0.4, 0));
				centralCurve.add(new SFVertex3f(0.0, 0.6, 0));
				centralCurve.add(new SFVertex3f(0.0, 0.67, 0));
				//centralCurve.add(new SFVertex3f(0.0, 0.71, 0));
			
			surfaceFunction.setCentralCurve(centralCurve);
		
			var rayCurve=new SFBasisSpline2(false);
				rayCurve.add(new SFVertex3f(0.1, 0.05, 0));
				rayCurve.add(new SFVertex3f(0.02, 0.1, 0));
				rayCurve.add(new SFVertex3f(0.2, 0.2, 0));
				rayCurve.add(new SFVertex3f(0.3, 0.4, 0));
				rayCurve.add(new SFVertex3f(0.2, 0.6, 0));
				rayCurve.add(new SFVertex3f(0.001, 0.67, 0));
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


function createBaloon(x,y,z,rotationY,r,g,b){
	
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
		node.getTransform().setOrientation(matrix);
		
	return node;
}




function createTexturedBaloon(x,y,z,rotationY,texture){
	
	checkBaloonSurface();
	var surfaceFunction=baloonSurfaceFunction;
	
	var primitive=SFPipeline_getPrimitive("Triangle2PNTxO");
	var gridGeometry=new SFQuadsGridGeometry();
	
	gridGeometry.getQuadsGrid().setNu( 16);
	gridGeometry.getQuadsGrid().setNv( 16);
				
	var surfaceGeometry=new SFParametrizedGeometry(gridGeometry);
	surfaceGeometry.setPrimitive(primitive);
	gridGeometry.setPrimitive(primitive.getConstructionPrimitive());
				
	surfaceGeometry.setMainGeometryFunction(surfaceFunction);
	surfaceGeometry.setTexCoordGeometry(new SFSimpleTexCoordGeometryuv(2,2));			
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




function createTexturedRect(x,y,z,rotationY,texture){
	
	checkRectSurface();
	var surfaceFunction=rectSurfaceFunction;
	
	var primitive=SFPipeline_getPrimitive("Triangle2PNTxO");
	var gridGeometry=new SFQuadsGridGeometry();
	
	gridGeometry.getQuadsGrid().setNu( 16);
	gridGeometry.getQuadsGrid().setNv( 16);
				
	var surfaceGeometry=new SFParametrizedGeometry(gridGeometry);
	surfaceGeometry.setPrimitive(primitive);
	gridGeometry.setPrimitive(primitive.getConstructionPrimitive());
				
	surfaceGeometry.setMainGeometryFunction(surfaceFunction);
	surfaceGeometry.setTexCoordGeometry(new SFSimpleTexCoordGeometryuv(1,1));			
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
