#ifndef shadow_renderer_data_utils_H_
#define shadow_renderer_data_utils_H_

#include "shadow/animation/data.SFCompositeAnimationData.h"
#include "shadow/animation/data.SFMoveAnimationData.h"
#include "shadow/animation/data.SFPeriodicAnimationData.h"
#include "shadow/animation/data.SFRotateAnimationData.h"
#include "shadow/geometry/curves.data.SFBasisSpline1Data.h"
#include "shadow/geometry/curves.data.SFBasisSpline2Data.h"
#include "shadow/geometry/curves.data.SFBasisSpline3Data.h"
#include "shadow/geometry/curves.data.SFBezier2SplineData.h"
#include "shadow/geometry/curves.data.SFBezier3SplineData.h"
#include "shadow/geometry/curves.data.SFCurvesListData.h"
#include "shadow/geometry/curves.data.SFIndexCurveData.h"
#include "shadow/geometry/curves.data.SFInstancedCurveData.h"
#include "shadow/geometry/curves.data.SFPolygonalSplineData.h"
#include "shadow/geometry/curves.data.SFRBasisSpline2Data.h"
#include "shadow/geometry/curves.data.SFRBasisSpline3Data.h"
#include "shadow/geometry/curves.data.SFRBezierSpline2Data.h"
#include "shadow/geometry/curves.data.SFRBezierSpline3Data.h"
#include "shadow/geometry/curves.data.SFTransformedCurveData.h"
#include "shadow/geometry/curves.data.SFVListCurvesListData.h"
#include "shadow/geometry/curves.data.SFValuesListData.h"
#include "shadow/geometry/functions.data.SFBicurvedLoftedSurfaceData.h"
#include "shadow/geometry/functions.data.SFCurvedTubeFunctionData.h"
#include "shadow/geometry/functions.data.SFFunctionRandomizerData.h"
#include "shadow/geometry/functions.data.SFGroupCurvesGeometryData.h"
#include "shadow/geometry/functions.data.SFInstancedCurvesSurfaceFunctionData.h"
#include "shadow/geometry/functions.data.SFRadialSurfaceFunctionData.h"
#include "shadow/geometry/functions.data.SFRectangle2DFunctionData.h"
#include "shadow/geometry/functions.data.SFSimpleTexCoordGeometryuvData.h"
#include "shadow/geometry/functions.data.SFTensorProductSurfaceData.h"
#include "shadow/geometry/geometries.data.SFCompositeMeshGeometryData.h"
#include "shadow/geometry/geometries.data.SFCurvesGeometryData.h"
#include "shadow/geometry/geometries.data.SFCurvesMeshData.h"
#include "shadow/geometry/geometries.data.SFGroupMeshGeometryData.h"
#include "shadow/geometry/geometries.data.SFMeshCurveData.h"
#include "shadow/geometry/geometries.data.SFMeshGeometryData.h"
#include "shadow/geometry/geometries.data.SFPNMeshCurveData.h"
#include "shadow/geometry/geometries.data.SFPNTxOCurvesMeshData.h"
#include "shadow/urvesMeshData/C/PNCurveDataobject.h"
#include "shadow/geometry/geometries.data.SFParametricGeometryInstance.h"
#include "shadow/geometry/geometries.data.SFParametrizedSurfaceGeometryData.h"
#include "shadow/geometry/geometries.data.SFQuadsGridGeometryData.h"
#include "shadow/geometry/geometries.data.SFQuadsSurfaceFunctionsGeometryData.h"
#include "shadow/geometry/geometries.data.SFQuadsSurfaceGeometryData.h"
#include "shadow/geometry/geometries.data.SFTiledTexCoordData.h"
#include "shadow/geometry/vertices.SFParametricValuesList.h"
#include "shadow/geometry/vertices.SFRandomizedValueListData2D.h"
#include "shadow/geometry/vertices.SFRandomizedValueListData3D.h"
#include "shadow/geometry/vertices.SFTextureRepeaterValueList.h"
#include "shadow/geometry/vertices.SFVertexListData16.h"
#include "shadow/geometry/vertices.SFVertexListDataUnit8.h"
#include "shadow/geometry/vertices.SFVoidVerticesList.h"
#include "shadow/gui/data.SFBlankDrawerData.h"
#include "shadow/gui/data.SFBorderIconDrawerData.h"
#include "shadow/gui/data.SFCompositeDrawerData.h"
#include "shadow/gui/data.SFDrawerContainerData.h"
#include "shadow/gui/data.SFIconDrawerData.h"
#include "shadow/gui/data.SFObjectModelDrawerData.h"
#include "shadow/gui/data.SFRectDrawableElementData.h"
#include "shadow/gui/drawers.SFScreenRectData.h"
#include "shadow/image/bitmaps.data.SFBitmapArrayData.h"
#include "shadow/image/bitmaps.data.SFFunction2DBitmapData.h"
#include "shadow/image/data.SFBitmapTextureData.h"
#include "shadow/image/data.SFDrawnRenderedTextureData.h"
#include "shadow/image/data.SFFilteredRenderedTextureData.h"
#include "shadow/renderer/data.SF2DCameraData.h"
#include "shadow/renderer/data.SFInstancedReferenceNodeData.h"
#include "shadow/renderer/data.SFObjectModelData.h"
#include "shadow/renderer/data.SFObjectsArrayData.h"
#include "shadow/renderer/data.SFPrimitiveArrayData.h"
#include "shadow/renderer/data.SFProgramAssetData.h"
#include "shadow/renderer/data.SFReferenceNodeData.h"
#include "shadow/renderer/data.SFRendererData.h"
#include "shadow/renderer/data.SFStructureArrayData16.h"
#include "shadow/renderer/data.SFStructureArrayDataFloatShort.h"
#include "shadow/renderer/data.SFStructureArrayDataUnit8.h"
#include "shadow/renderer/data.proxies.SFClonedArrayReference.h"
#include "shadow/renderer/data.transforms.SFNoTransformData.h"
#include "shadow/renderer/data.transforms.SFRigidTransformData.h"
#include "shadow/renderer/data.transforms.SFTranslateAndScaleData.h"
#include "shadow/renderer/data.transforms.SFTranslateAndScaleFixed16Data.h"
#include "shadow/renderer/data.transforms.SFTranslateFixed16Data.h"
#include "shadow/renderer/data.transforms.SFTranslationData.h"
#include "shadow/renderer/data.transforms.SFUnit8Transform2DData.h"
#include "shadow/system/data.SFGenericDatasetFactory.h"
#include "shadow/system/data.SFObjectsLibrary.h"
#include "shadow/system/data.SFTemplateDataAsset.h"
#include "shadow/tmp/SFSplineCurvedTubeFunctionData.h"
import tmp.SFTemplateSampleDataAsset;

class SFStandardDatasetFactory extends SFGenericDatasetFactory{

	SFStandardDatasetFactory(){

		addSFDataset(new SFCurvedTubeFunctionData());
		addSFDataset(new SFStructureArrayData16());
		addSFDataset(new SFStructureArrayDataUnit8());
		addSFDataset(new SFQuadsSurfaceGeometryData());
		addSFDataset(new SFObjectModelData());
		addSFDataset(new SFObjectsLibrary());
		addSFDataset(new SFBasisSpline2Data());
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
		//addSFDataset(new SFBitmapPerlinNoiseData());
		addSFDataset(new SFBitmapTextureData());
		addSFDataset(new SFFilteredRenderedTextureData());
		addSFDataset(new SFReferenceNodeData());
		addSFDataset(new SFSplineCurvedTubeFunctionData());
		addSFDataset(new SFDrawnRenderedTextureData());
		addSFDataset(new SFRendererData());
		addSFDataset(new SF2DCameraData());
		//addSFDataset(new SFBitmapPerlinNoiseData());
		addSFDataset(new SFSimpleTexCoordGeometryuvData());
		addSFDataset(new SFRectangle2DFunctionData());
		addSFDataset(new SFRadialSurfaceFunctionData());
		addSFDataset(new SFClonedArrayReference());
		addSFDataset(new SFBicurvedLoftedSurfaceData());
		//addSFDataset(new SFUniformBezier33fData());
		//addSFDataset(new SFLineData());
		addSFDataset(new SFCompositeAnimationData());
		addSFDataset(new SFMoveAnimationData());
		addSFDataset(new SFPeriodicAnimationData());
		addSFDataset(new SFInstancedReferenceNodeData());
		addSFDataset(new SFRotateAnimationData());

		//addSFDataset(new SFDerivedTextCoordData());
		addSFDataset(new SFCompositeMeshGeometryData());
		//addSFDataset(new SFSimpleObjPlaneTexCoordGeometryData());
		//addSFDataset(new SFNormalBasedObjPlaneTexCoordGeometryData());
		addSFDataset(new SFParametrizedSurfaceGeometryData());
		addSFDataset(new SFFunction2DBitmapData());
		//addSFDataset(new SFBitmapFunctionPerlinNoiseData());
		//addSFDataset(new SFBasicBitmapFunctionsData());
		addSFDataset(new SFBitmapArrayData());
		addSFDataset(new SFFunctionRandomizerData());
		addSFDataset(new SFQuadsGridGeometryData());
		addSFDataset(new SFRandomizedValueListData2D());
		//addSFDataset(new SFVoronoiBitmapFunctionData());

		addSFDataset(new SFPolygonalSplineData());
		addSFDataset(new SFTransformedCurveData());
		//addSFDataset(new SFContinuosBezier3Data());
		addSFDataset(new SFGroupMeshGeometryData());
		addSFDataset(new SFTensorProductSurfaceData());
		//addSFDataset(new SFBezier3Data());
		addSFDataset(new SFRandomizedValueListData3D());

		addSFDataset(new SFParametricValuesList());
		addSFDataset(new SFParametricGeometryInstance());
		addSFDataset(new SFObjectsArrayData());
		addSFDataset(new SFUnit8Transform2DData());

		//addSFDataset(new SFPointsCloudData());
		addSFDataset(new SFTextureRepeaterValueList());
		//addSFDataset(new SFTilesSpaceData());
		addSFDataset(new SFTiledTexCoordData());

		addSFDataset(new SFCurvesMeshData());
		addSFDataset(new SFCurvesGeometryData());
		addSFDataset(new SFMeshCurveData());
		addSFDataset(new SFPNMeshCurveData());
		addSFDataset(new SFPNTxOCurvesMeshData());
		addSFDataset(new PNCurveDataobject());

		addSFDataset(new SFTemplateDataAsset());
		addSFDataset(new SFTemplateSampleDataAsset());
		addSFDataset(new SFBasisSpline3Data());
		addSFDataset(new SFCurvesListData());
		addSFDataset(new SFInstancedCurvesSurfaceFunctionData());
		addSFDataset(new SFGroupCurvesGeometryData());
		addSFDataset(new SFQuadsSurfaceFunctionsGeometryData());
		addSFDataset(new SFInstancedCurveData());
		addSFDataset(new SFVListCurvesListData());
		addSFDataset(new SFValuesListData());
		addSFDataset(new SFIndexCurveData());
		addSFDataset(new SFStructureArrayDataFloatShort());

		addSFDataset(new SFBasisSpline1Data());
		addSFDataset(new SFBasisSpline3Data());
		addSFDataset(new SFBezier2SplineData());
		addSFDataset(new SFBezier3SplineData());
		addSFDataset(new SFRBasisSpline2Data());
		addSFDataset(new SFRBasisSpline3Data());
		addSFDataset(new SFRBezierSpline2Data());
		addSFDataset(new SFRBezierSpline3Data());

		addSFDataset(new SFObjectModelDrawerData());
		addSFDataset(new SFIconDrawerData());
		addSFDataset(new SFBorderIconDrawerData());
		addSFDataset(new SFBlankDrawerData());
		addSFDataset(new SFCompositeDrawerData());
		addSFDataset(new SFDrawerContainerData());
		addSFDataset(new SFRectDrawableElementData());
		addSFDataset(new SFScreenRectData());

		addSFDataset(new SFVoidVerticesList());
	}

}
;
}
#endif
