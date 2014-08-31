package shadow.tests.geometries.meshes;

import javax.media.opengl.GL2;

import shadow.data.tools.SFDataBuilder;
import shadow.geometry.geometries.SFMeshGeometry;
import shadow.math.SFMatrix3f;
import shadow.math.SFMatrix4f;
import shadow.math.SFTransform3f;
import shadow.math.SFValue;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipeline.PipelineModule;
import shadow.pipeline.SFPipelineGraphics;
import shadow.pipeline.SFPrimitiveArray;
import shadow.pipeline.SFStructureData;
import shadow.pipeline.openGL20.SFGL2;
import shadow.pipeline.tools.SFCommonPipeline;
import shadow.renderer.data.SFStandardDictionary;
import shadow.renderer.viewer.SFDrawableFrame;
import shadow.system.SFDrawable;
import shadow.system.SFInitiable;
import shadow.system.SFInitiator;
import shadow.system.SFValuesArray;
import shadow.system.data.SFDataAsset;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFObjectsLibraryDataCenter;
import shadow.tests.tools.SFViewer;

public class Example0001GLFrameBlender {
	
	public static void main(String[] args) {
		
		//Prepare the Shadow Framework and keep the library used as Data Center
		SFObjectsLibraryDataCenter library = prepareShadowFramework();
		
		//The library is empty; use a DataBuilder to load a sfb file into it
		SFDataBuilder builder=new SFDataBuilder();
		builder.loadBuilderData("testsData/geometries/input/BlenderTest.sfb", library);
		
		//Prepare the example
		ExampleDrawable drawable=new ExampleDrawable(library);
		
		//Ensure init is called
		SFInitiator.addInitiable(drawable);
		
		//Use a Drawable Frame to show the Drawable Content.
		SFDrawableFrame frame=new SFDrawableFrame("My Frame", 400, 400, drawable);
		frame.setVisible(true);
	}


	private static SFObjectsLibraryDataCenter prepareShadowFramework() {
		// Prepare the Data Dictionary
		SFDataCenter.setDictionary(new SFStandardDictionary());
		// Setup the Data Center 
		SFObjectsLibraryDataCenter objectsLibrary=new SFObjectsLibraryDataCenter();
		SFDataCenter.setDataCenterImplementation(objectsLibrary);
		//Load Pipeline components
		SFCommonPipeline.setPipelineLocation("pipelines/","simpleglpipeline01.txt");
		SFCommonPipeline.loadCommonPipeline();
		return objectsLibrary;
	}


	public static class ExampleDrawable implements SFDrawable,SFInitiable{
		
		private SFStructureData material;
		private SFStructureData light;
		
		protected SFMeshGeometry geometry;
		protected SFMeshGeometry points;
		
		private float angle;
		private float delta;
		
		private int program;
		
		public ExampleDrawable(SFObjectsLibraryDataCenter library) {
			super();
		
			this.points = (SFMeshGeometry)((SFDataAsset<?>)library.getRecord("Points").getObject()).getResource();
			points.init();
			
			this.geometry= (SFMeshGeometry)((SFDataAsset<?>)library.getRecord("SFMeshGeometry000").getObject()).getResource();
			geometry.init();
			
			//Istance of the Mat01 strucure
			SFValue[] colours={new SFVertex3f(0.5f,0,0)};
		
			material = new SFStructureData(colours);

			//Istance of the PLight01 strucure : {intensity,position}
			SFValue[] lights={new SFVertex3f(1.0f,1.0f,1.0f),new SFVertex3f(1.0f,1.0f,1.0f)};
		
			
			light = new SFStructureData(lights);
			
			this.angle = 0f;
			this.delta = 0.001f;		
		}

		
		@Override
		public void init() {
			//Init GPU resources if necessary
			
			//Program is a resource; must be created at init time
			program = SFPipeline.getStaticProgram(geometry.getPrimitive(),
					"BasicPNTransform","BasicMat","BasicLSPN");
		}
		
		@Override
		public void destroy() {
			// Release GPU resources here
			
		}

		@Override
		public void draw() {
			
			//OpenGL part
			GL2 gl=SFGL2.getGL();//This is available thanks to SFDrawableFrame
			
			//ensure standard pipeline is enabled
			gl.glUseProgram(0);
			gl.glEnable(GL2.GL_DEPTH_TEST);
						
			// Apply rotation
			SFTransform3f transform=new SFTransform3f();
			transform.setMatrix(SFMatrix3f.getRotationY( (float) Math.PI * angle));
			angle += delta;
			
			SFMatrix4f modelView = SFMatrix4f.getIdentity();
			SFMatrix4f.toTransform3f(modelView, transform);			
			
			//Get the points position
			SFPrimitiveArray array=points.getArray();
			SFValuesArray data=array.getPrimitiveData(0);
			
			//Get the geometry
			SFPrimitiveArray geometryArray = geometry.getArray();
			
			gl.glLoadMatrixf(modelView.getV(), 0);
			
			//Draw points 
			gl.glPointSize(4);
			gl.glBegin(GL2.GL_POINTS);
				SFVertex3f vertex=new SFVertex3f();
				for (int i = 0; i < data.getElementsCount(); i++) {
					data.getElement(i, vertex);
					gl.glVertex3fv(vertex.getV(),0);
				}
			gl.glEnd();
			gl.glPointSize(1);			
			
			//Shadow Framework part
			
			//Need transforms first...
			
			float[] projection={
					1.0f,0,0,0,
					0.0f,1.0f,0,0,
					0.0f,0,1.0f,0,
					0.0f,0,0,1.0f,
			};
			
			/*Note : you can use a SFCamera and SFCamera.extractTransform() instead of writing the projection on your own.
			writing the projection is supposed to be simpler if the transform is not too complex.*/
			
			//... Than you can use the framework drawing component SFPipelineGraphics
			SFPipelineGraphics graphics = SFPipeline.getSfPipelineGraphics();
			graphics.setupTransform(transform.getV());
			graphics.setupProjection(projection);
			graphics.loadProgram(program);
			graphics.loadStructureData(PipelineModule.MATERIAL, material, 0);
			graphics.loadStructureData(PipelineModule.LIGHT, light, 0);
			graphics.drawPrimitives(geometryArray, 0, geometryArray.getElementsCount());
		}
	}
}
