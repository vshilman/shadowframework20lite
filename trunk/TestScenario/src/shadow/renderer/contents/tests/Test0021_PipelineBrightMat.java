package shadow.renderer.contents.tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import shadow.geometry.SFGeometry;
import shadow.geometry.curves.data.SFBasisSplineData;
import shadow.geometry.curves.data.SFCurvesVerticesData;
import shadow.geometry.functions.data.SFCurvedTubeFunctionData;
import shadow.geometry.geometries.SFQuadsSurfaceGeometry;
import shadow.geometry.geometries.SFSimpleTexCoordGeometryuv;
import shadow.geometry.vertices.SFVertexFixedListData;
import shadow.math.SFValuenf;
import shadow.math.SFVertex3f;
import shadow.pipeline.SFPipeline;
import shadow.pipeline.SFPipelineModuleWrongException;
import shadow.pipeline.SFPrimitive;
import shadow.pipeline.SFPrimitive.PrimitiveBlock;
import shadow.pipeline.SFProgramComponent;
import shadow.pipeline.SFStructureArray;
import shadow.pipeline.builder.SFPipelineBuilder;
import shadow.pipeline.data.SFDataPipelineBuilder;
import shadow.pipeline.loader.SFProgramComponentLoader;
import shadow.pipeline.openGL20.SFGL20Pipeline;
import shadow.pipeline.parameters.SFParameteri;
import shadow.renderer.SFNode;
import shadow.renderer.SFObjectModel;
import shadow.renderer.SFProgramStructureReference;
import shadow.renderer.SFStructureReference;
import shadow.renderer.contents.tests.common.CommonMaterial;
import shadow.renderer.data.SFDataAsset;
import shadow.renderer.viewer.SFDataUtility;
import shadow.renderer.viewer.SFViewer;
import shadow.renderer.viewer.SFViewerDatasetFactory;
import shadow.renderer.viewer.SFViewerObjectsLibrary;
import shadow.system.SFException;
import shadow.system.data.SFDataCenter;
import shadow.system.data.SFDataCenterListener;

public class Test0021_PipelineBrightMat {

	private static final String root = "testsData";

	/**
	 * TODO missing test description
	 */
	public static void main(String[] args) {

		SFDataPipelineBuilder builder=new SFDataPipelineBuilder();
		
		try {
			SFProgramComponentLoader.loadComponents(new File("data/primitive"),builder);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SFPipelineModuleWrongException e) {
			List<String> string=e.getList();
			for (String string2 : string) {
				System.err.println(string2);
			}
			e.printStackTrace();
		}
		
		//Add a new Component.
		try {
			//@begin Material BrightMat
			builder.generateElement("Material", "BrightMat");
			//@param diffColor,ambColor as Mat01
			List<String> parameters=new ArrayList<String>();
			Collections.addAll(parameters, "diffColor", "ambColor");
			builder.buildParamRule("Mat01", parameters);
			//@define bright:3 1.8,1.8,1.8
			builder.buildDefineRule("bright", SFParameteri.GLOBAL_FLOAT, "1.8");
			//@write color diffColor*bright
			builder.buildWriteRule("color", "diffColor*bright");
			//@end
			builder.closeElement();
		} catch (SFException e1) {
			e1.printStackTrace();
			
		}

		// 2) Store the Surface Function in file 'testsData\test0001.sf'
		SFDataUtility.saveDataObject(root, "test0021.sf", builder);
		
		SFDataPipelineBuilder builder2=new SFDataPipelineBuilder();
		SFDataUtility.loadDataset(root, "test0021.sf", builder2);
	
		SFGL20Pipeline.setup();
		
		builder2.apply(new SFPipelineBuilder());

		SFDataCenter.setDatasetFactory(new SFViewerDatasetFactory());
		SFDataCenter.setDataCenterImplementation(new SFViewerObjectsLibrary(
				root, "test0010b.sf"));
		
		SFDataCenter.getDataCenter().makeDatasetAvailable("MushroomsScene01b",
				new SFDataCenterListener<SFDataAsset<SFNode>>() {
					@Override
					public void onDatasetAvailable(String name,SFDataAsset<SFNode> dataset) {
						SFNode mainNode=dataset.getResource();
						for (SFNode node : mainNode) {
							SFObjectModel objectModel=(SFObjectModel)node;
							objectModel.getModel().addMaterial("BrightMat");
						}
						SFViewer viewer=SFViewer.generateFrame(dataset.getResource(),
								SFViewer.getRotationController(),SFViewer.getWireframeController(),SFViewer.getZoomController());
						viewer.setRotateModel(true, 0.03f);
					}
				});	
	}


}
