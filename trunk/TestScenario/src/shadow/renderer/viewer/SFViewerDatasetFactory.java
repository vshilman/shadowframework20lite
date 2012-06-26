package shadow.renderer.viewer;

import shadow.geometry.curves.data.SFBasisSplineData;
import shadow.geometry.curves.data.SFLineData;
import shadow.geometry.curves.data.SFUniformBezier33fData;
import shadow.geometry.data.SFSimpleObjPlaneTexCoordGeometryData;
import shadow.geometry.data.SFSimpleTexCoordGeometryuvData;
import shadow.geometry.functions.data.SFBicurvedLoftedSurfaceData;
import shadow.geometry.functions.data.SFCurvedTubeFunctionData;
import shadow.geometry.functions.data.SFRadialSurfaceFunctionData;
import shadow.geometry.functions.data.SFRectangle2DFunctionData;
import shadow.geometry.functions.data.SFSplineCurvedTubeFunctionData;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.geometry.vertices.SFVertexFixedListData;
import shadow.image.bitmaps.data.SFSimplePerlinNoiseData;
import shadow.image.data.SFBitmapTextureData;
import shadow.image.data.SFDrawnRenderedTextureData;
import shadow.image.data.SFFilteredRenderedTextureData;
import shadow.renderer.data.SF2DCameraData;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.SFOneStepAlgorithmData;
import shadow.renderer.data.SFReferenceNodeData;
import shadow.renderer.data.SFRendererData;
import shadow.renderer.data.SFStructureArrayData;
import shadow.renderer.data.SFStructureReferenceData;
import shadow.renderer.data.proxies.SFClonedArrayReference;
import shadow.system.data.SFGenericDatasetFactory;
import shadow.system.data.SFObjectsLibrary;

public class SFViewerDatasetFactory extends SFGenericDatasetFactory{
	
	public SFViewerDatasetFactory(){
		addSFDataset(new SFCurvedTubeFunctionData());
		addSFDataset(new SFStructureArrayData());
		addSFDataset(new SFQuadsSurfaceGeometryData());
		addSFDataset(new SFObjectModelData());
		addSFDataset(new SFObjectsLibrary());
		addSFDataset(new SFBasisSplineData());
		addSFDataset(new SFSimplePerlinNoiseData());
		addSFDataset(new SFBitmapTextureData());
		addSFDataset(new SFFilteredRenderedTextureData());
		addSFDataset(new SFReferenceNodeData());
		addSFDataset(new SFSplineCurvedTubeFunctionData());
		addSFDataset(new SFDrawnRenderedTextureData());
		addSFDataset(new SFRendererData());
		addSFDataset(new SF2DCameraData());
		addSFDataset(new SFOneStepAlgorithmData());
		addSFDataset(new SFSimplePerlinNoiseData());
		addSFDataset(new SFSimpleTexCoordGeometryuvData());
		addSFDataset(new SFStructureReferenceData());
		addSFDataset(new SFVertexFixedListData());
		addSFDataset(new SFRectangle2DFunctionData());
		addSFDataset(new SFRadialSurfaceFunctionData());
		addSFDataset(new SFClonedArrayReference());
		addSFDataset(new SFSimpleObjPlaneTexCoordGeometryData());
		addSFDataset(new SFBicurvedLoftedSurfaceData());
		addSFDataset(new SFUniformBezier33fData());
		addSFDataset(new SFLineData());
	}
}
