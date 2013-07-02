


function SFViewer(buildNodeCallBack){
	this.buildNodeCallBack=buildNodeCallBack;
	this.renderer=new SFRenderer();
	
	var camera=new SFCamera(new SFVertex3f(0,0,0), new SFVertex3f(0,0,1), 
		new SFVertex3f(1,0,0), new SFVertex3f(0,1,0), 1, 1, 20);

	camera.extractTransform();
	this.renderer.setCamera(camera);
	
	var programAsset=new SFProgramModuleStructures("BasicLSPN2");
	
			var lightStructure=SFPipeline_getProgramModule("BasicLSPN2").getComponents()[0]
						.getStructures()[0].getStructure();
			
			var lightArray=SFPipeline_getSfPipelineMemory().generateStructureData(lightStructure); 
			var lightData=[new SFVertex3f(1, 1, 1),new SFVertex3f(1, 1, -1)];
			
						var lightReference=new SFStructureReference(lightArray,lightArray.generateElement()); 
			
						var mat=new SFStructureData(lightReference.getStructure());
						for (var i = 0; i < lightData.length; i++) {
							mat.getValue(i).setValue(lightData[i]);
						}
						try {
							lightReference.setStructureData(mat);
						} catch (exception) {
							e.printStackTrace();
						}
			programAsset.getData().push(lightReference);
			
	this.renderer.setLight(programAsset);
	
}

SFViewer.prototype["init"]=function(){
	SFPipeline_getSfPipelineGraphics().init();
};

SFViewer.prototype["getNode"]=function(){
	return this.node;
};

SFViewer.prototype["draw"]=function(){

	if(this.node===undefined){
		this.node=this.buildNodeCallBack();
	}

	SFInitiator_solveInitiables();
	SFUpdater_refresh();

	gl.clearColor(0.8,0.8,1,1);
	gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT);

	var matrix=this.renderer.getCamera().extractTransform();
	
	gl.enable(gl.DEPTH_TEST);
	
	SFPipeline_getSfPipelineGraphics().setupProjection(matrix);
	SFPipeline_getSfPipelineGraphics().setupTransform(matrix);
	this.renderer.render(this.node);
	
};

var viewer=0;
var library=0;

function timer() {
	//requestAnimFrame(timer);
	SFGL2_setGl(gl);
	viewer.draw();
	setTimeout(timer,50);
}

function setupCanvas(v,canvasName){
	viewer=v;
	var canvas = document.getElementById(canvasName);
	initGL(canvas);
	SFGL2_setGl(gl);
	viewer.init();
	timer();
}


function setupAmbient(){
	//Pipeline Preparation
	var file=loadFile("test0020.sf");
	var builder2=new SFDataPipelineBuilder();
	var inputStream=new SFInputStreamBinary(file);
	builder2.readFromStream(inputStream);
	SFGL20Pipeline_setup();
	builder2.apply(new SFPipelineBuilder());
	
	// In order to work properly load utilities will need the Datacenter to
	// contain a valid DatasetFactory
	SFDataCenter_setDatasetFactory(new SFViewerDatasetFactory());
	// We also add a simple data center 
	var objectsLibrary=new SFViewerObjectsLibrary();
	SFDataCenter_setDataCenterImplementation(objectsLibrary);
	library = objectsLibrary.getLibrary();
	
}

function loadData(contents){
	
	var inputStream=new SFInputStreamBinary(contents);
	var dataset=new SFObjectsLibrary();
	
	var dataset=SFDataCenter_getDataCenter().readDataset(inputStream);
	dataset.getSFDataObject().readFromStream(inputStream);
	
	SFDataCenter_setDataCenterImplementation(new SFViewerObjectsLibrary(dataset));
}
