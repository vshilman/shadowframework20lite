package shadow.renderer.contents.tests;

import java.util.ArrayList;
import java.util.List;

import shadow.animation.SFAnimation;
import shadow.animation.SFAnimator;
import shadow.animation.SFPeriodicAnimation;
import shadow.geometry.curves.SFBasisSpline2;
import shadow.geometry.functions.SFCurvedTubeFunction;
import shadow.geometry.geometries.SFParametrizedGeometry;
import shadow.math.SFValuenf;
import shadow.pipeline.builder.SFPipelineBuilder;
import shadow.pipeline.parameters.SFParameteri;
import shadow.renderer.SFObjectModel;
import shadow.renderer.contents.tests.common.CommonPipeline;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.utils.SFViewerDatasetFactory;
import shadow.renderer.data.utils.SFViewerObjectsLibrary;
import shadow.renderer.viewer.SFAnimationTimer;
import shadow.renderer.viewer.SFViewer;
import shadow.system.SFException;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;

public class Test0134_AnimationSimpleMorphGeometry {

	private static final String root = "testsData";

	/**
	 * TODO missing test description
	 */
	public static void main(String[] args) {

		SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
		CommonPipeline.prepare();
		
		SFAnimationTimer.startTimer();

		// 3) Retrieve the library and make model available so that it can be added to a Viewer
		SFDataCenter.setDataCenterImplementation(new SFViewerObjectsLibrary(root,"test0017.sf"));
		
		// Add a new Component.
				try {
					SFPipelineBuilder builder = new SFPipelineBuilder();
					// @begin Material BrightMat
					builder.generateElement("MaterialComponent", "GlossLightEffectComponent");
					builder.setUseRule("color");
					builder.setUseRule("normal");
					// @end
					builder.buildDefineRule("secondLight", SFParameteri.GLOBAL_FLOAT3, "(-1.0),1.0,0.0");
					builder.buildDefineRule("secondIntensity", SFParameteri.GLOBAL_FLOAT3, "0.8*dot(normal,secondLight)");
					builder.buildDefineRule("bright", SFParameteri.GLOBAL_FLOAT3, "1.4,1.2,1.0");
					builder.buildWriteRule("color", "color*bright*secondIntensity*secondIntensity");
					builder.closeElement();
					
					builder.generateElement("Material", "GlossLightEffect");
					builder.buildComponent("ImprovedBumpMappedMat");
					builder.buildComponent("GlossLightEffectComponent");
					builder.closeElement();
				} catch (SFException e1) {
					e1.printStackTrace();
				}
		
		SFDataCenter.getDataCenter().makeDatasetAvailable("StoneMushroom", new SFDataCenterListener<SFObjectModelData>() {
			@Override
			public void onDatasetAvailable(String name, SFObjectModelData dataset) {
				SFObjectModel model=(SFObjectModel)dataset.getResource();
				model.getModel().getMaterialComponent().setProgram("GlossLightEffect");
				model.getModel().getTransformComponent().setProgram("BasicBumpMapTransform");
				
				final SFParametrizedGeometry geometry=(SFParametrizedGeometry)(model.getModel().getRootGeometry());
				SFCurvedTubeFunction function=(SFCurvedTubeFunction)(geometry.getFunctions()[0]);
				SFBasisSpline2<SFValuenf> curve=(SFBasisSpline2<SFValuenf>)function.getRayCurve();
				final SFValuenf value=curve.getVertices().get(5);
				final SFValuenf value2=curve.getVertices().get(1);
				final SFValuenf value3=curve.getVertices().get(2);
				//new SFVertex3f(0.2f, 0.2f, 0),
				//0.45f, 0.5f, 0
				SFAnimation morphAnimation=new SFAnimation() {
					public void init() {
					}
					
					public SFAnimation clone()  {
						return null;
					}
					
					public void destroy() {
					}
					
					@Override
					public void animate(long time) {
						List<Long> times=new ArrayList<Long>();
						times.add(System.nanoTime());
						
						float t=(time/(1.0f*4000));
						t=4*t*(1-t)*0.3f+0.5f;
						value.set(0.45f*t,0.5f*t,t*0.25f);
						value2.set(0.3f*t,0.1f,-t*0.25f);
						value3.set(0.3f*t,0.2f,-t*0.25f);
						geometry.update();
						
						times.add(System.nanoTime());//1
						for (int i = 1; i < times.size(); i++) {
							long t1=times.get(i-1);
							long t2=times.get(i);
							System.out.println("\t\t T["+i+"]="+((t2-t1)*0.001*0.001)+"ms");
						}
					}
				};
				SFPeriodicAnimation morphAnimationPeriod=new SFPeriodicAnimation(morphAnimation,4000, 0);
				
				SFAnimator.addAnimation(morphAnimationPeriod);

				SFViewer.generateFrame(model,SFViewer.getLightStepController(),SFViewer.getZoomController(),SFViewer.getRotationController(),SFViewer.getWireframeController());
			}
		});
		
	}

}

