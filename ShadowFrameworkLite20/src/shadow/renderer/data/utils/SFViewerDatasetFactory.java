package shadow.renderer.data.utils;

import shadow.animation.data.SFCompositeAnimationData;
import shadow.animation.data.SFMoveAnimationData;
import shadow.animation.data.SFPeriodicAnimationData;
import shadow.animation.data.SFRotateAnimationData;
import shadow.geometry.curves.data.SFBasisSplineData;
import shadow.geometry.curves.data.SFBezier3Data;
import shadow.geometry.curves.data.SFContinuosBezier3Data;
import shadow.geometry.curves.data.SFLineData;
import shadow.geometry.curves.data.SFPolygonalSplineData;
import shadow.geometry.curves.data.SFTransformedCurveData;
import shadow.geometry.curves.data.SFUniformBezier33fData;
import shadow.geometry.functions.data.SFBicurvedLoftedSurfaceData;
import shadow.geometry.functions.data.SFCurvedTubeFunctionData;
import shadow.geometry.functions.data.SFNormalBasedObjPlaneTexCoordGeometryData;
import shadow.geometry.functions.data.SFRadialSurfaceFunctionData;
import shadow.geometry.functions.data.SFRectangle2DFunctionData;
import shadow.geometry.functions.data.SFSimpleObjPlaneTexCoordGeometryData;
import shadow.geometry.functions.data.SFSimpleTexCoordGeometryuvData;
import shadow.geometry.functions.data.SFSplineCurvedTubeFunctionData;
import shadow.geometry.functions.data.SFTensorProductSurfaceData;
import shadow.geometry.geometries.data.SFCompositeMeshGeometryData;
import shadow.geometry.geometries.data.SFDerivedTextCoordData;
import shadow.geometry.geometries.data.SFGroupMeshGeometryData;
import shadow.geometry.geometries.data.SFMeshGeometryData;
import shadow.geometry.geometries.data.SFParametrizedSurfaceGeometryData;
import shadow.geometry.geometries.data.SFPointsCloudData;
import shadow.geometry.geometries.data.SFQuadsGridGeometryData;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.geometry.geometries.data.SFTiledTexCoordData;
import shadow.geometry.vertices.SFRandomizedValueListData2D;
import shadow.geometry.vertices.SFRandomizedValueListData3D;
import shadow.geometry.vertices.SFTextureRepeaterValueList;
import shadow.geometry.vertices.SFVertexListData16;
import shadow.geometry.vertices.SFVertexListDataUnit8;
import shadow.image.bitmaps.data.SFBasicBitmapFunctionsData;
import shadow.image.bitmaps.data.SFBitmapArrayData;
import shadow.image.bitmaps.data.SFBitmapFunctionPerlinNoiseData;
import shadow.image.bitmaps.data.SFBitmapPerlinNoiseData;
import shadow.image.bitmaps.data.SFFunction2DBitmapData;
import shadow.image.bitmaps.data.SFFunctionRandomizerData;
import shadow.image.bitmaps.data.SFVoronoiBitmapFunctionData;
import shadow.image.data.SFBitmapTextureData;
import shadow.image.data.SFDrawnRenderedTextureData;
import shadow.image.data.SFFilteredRenderedTextureData;
import shadow.renderer.data.SF2DCameraData;
import shadow.renderer.data.SFInstancedReferenceNodeData;
import shadow.renderer.data.SFObjectModelData;
import shadow.renderer.data.SFPrimitiveArrayData;
import shadow.renderer.data.SFProgramAssetData;
import shadow.renderer.data.SFReferenceNodeData;
import shadow.renderer.data.SFRendererData;
import shadow.renderer.data.SFStructureArrayData16;
import shadow.renderer.data.SFStructureArrayDataUnit8;
import shadow.renderer.data.SFTilesSpaceData;
import shadow.renderer.data.proxies.SFClonedArrayReference;
import shadow.renderer.data.transforms.SFNoTransformData;
import shadow.renderer.data.transforms.SFRigidTransformData;
import shadow.renderer.data.transforms.SFTranslateAndScaleData;
import shadow.renderer.data.transforms.SFTranslateAndScaleFixed16Data;
import shadow.renderer.data.transforms.SFTranslateFixed16Data;
import shadow.renderer.data.transforms.SFTranslationData;
import shadow.system.data.SFGenericDatasetFactory;
import shadow.system.data.SFObjectsLibrary;

public class SFViewerDatasetFactory extends SFGenericDatasetFactory{
	
	public SFViewerDatasetFactory(){
		addSFDataset(new SFCurvedTubeFunctionData());
		addSFDataset(new SFStructureArrayData16());
		addSFDataset(new SFStructureArrayDataUnit8());
		addSFDataset(new SFQuadsSurfaceGeometryData());
		addSFDataset(new SFObjectModelData());
		addSFDataset(new SFObjectsLibrary());
		addSFDataset(new SFBasisSplineData());
		addSFDataset(new SFVertexListData16());
		addSFDataset(new SFVertexListDataUnit8());
		addSFDataset(new SFProgramAssetData());
		addSFDataset(new SFNoTransformData());
		addSFDataset(new SFTranslateAndScaleFixed16Data());
		addSFDataset(new SFTranslateFixed16Data());
		
		addSFDataset(new SFRigidTransformData());
		addSFDataset(new SFTranslationData());
		addSFDataset(new SFTranslateAndScaleData());
		addSFDataset(new SFPrimitiveArrayData());
		addSFDataset(new SFMeshGeometryData());
		
		addSFDataset(new SFBitmapPerlinNoiseData());
		addSFDataset(new SFBitmapTextureData());
		addSFDataset(new SFFilteredRenderedTextureData());
		addSFDataset(new SFReferenceNodeData());
		addSFDataset(new SFSplineCurvedTubeFunctionData());
		addSFDataset(new SFDrawnRenderedTextureData());
		addSFDataset(new SFRendererData());
		addSFDataset(new SF2DCameraData());
		addSFDataset(new SFBitmapPerlinNoiseData());
		addSFDataset(new SFSimpleTexCoordGeometryuvData());
		addSFDataset(new SFRectangle2DFunctionData());
		addSFDataset(new SFRadialSurfaceFunctionData());
		addSFDataset(new SFClonedArrayReference());
		addSFDataset(new SFBicurvedLoftedSurfaceData());
		addSFDataset(new SFUniformBezier33fData());
		addSFDataset(new SFLineData());
		addSFDataset(new SFCompositeAnimationData());
		addSFDataset(new SFMoveAnimationData());
		addSFDataset(new SFPeriodicAnimationData());
		addSFDataset(new SFInstancedReferenceNodeData());
		addSFDataset(new SFRotateAnimationData());
		
		addSFDataset(new SFDerivedTextCoordData());
		addSFDataset(new SFCompositeMeshGeometryData());
		addSFDataset(new SFSimpleObjPlaneTexCoordGeometryData());
		addSFDataset(new SFNormalBasedObjPlaneTexCoordGeometryData());
		addSFDataset(new SFParametrizedSurfaceGeometryData());
		addSFDataset(new SFFunction2DBitmapData());
		addSFDataset(new SFBitmapFunctionPerlinNoiseData());
		addSFDataset(new SFBasicBitmapFunctionsData());
		addSFDataset(new SFBitmapArrayData());
		addSFDataset(new SFFunctionRandomizerData());
		addSFDataset(new SFQuadsGridGeometryData());
		addSFDataset(new SFRandomizedValueListData2D());
		addSFDataset(new SFVoronoiBitmapFunctionData());
		addSFDataset(new SFTextureRepeaterValueList());
		addSFDataset(new SFTilesSpaceData());
		addSFDataset(new SFTiledTexCoordData());
		addSFDataset(new SFPolygonalSplineData());
		addSFDataset(new SFTransformedCurveData());
		addSFDataset(new SFContinuosBezier3Data());
		addSFDataset(new SFGroupMeshGeometryData());
		addSFDataset(new SFTensorProductSurfaceData());
		addSFDataset(new SFBezier3Data());
		addSFDataset(new SFRandomizedValueListData3D());
		addSFDataset(new SFPointsCloudData());
	}
}
