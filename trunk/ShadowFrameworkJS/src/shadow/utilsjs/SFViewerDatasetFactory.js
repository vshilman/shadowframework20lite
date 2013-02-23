
function SFViewerDatasetFactory(){
	
	this.datasets=new Array();
	this.assetsList=new Array();
	
	this.addSFDataset(new SFCurvedTubeFunctionData());
	this.addSFDataset(new SFStructureArrayData(new SFFixedFloat16()));
	this.addSFDataset(new SFStructureArrayData(new SFFixedFloatUnit8()));
	this.addSFDataset(new SFQuadsSurfaceGeometryData());
	this.addSFDataset(new SFObjectModelData());
	this.addSFDataset(new SFObjectsLibrary());
	this.addSFDataset(new SFBasisSplineData());
	this.addSFDataset(new SFValueListData(new SFFixedFloat16()));
	this.addSFDataset(new SFValueListData(new SFFixedFloatUnit8()));
	this.addSFDataset(new SFProgramAssetData());
	this.addSFDataset(new SFNoTransformData());
	this.addSFDataset(new SFTranslateAndScaleFixed16Data());
	this.addSFDataset(new SFTranslateFixed16Data());
	
	this.addSFDataset(new SFRigidTransformData());
	this.addSFDataset(new SFTranslationData());
	this.addSFDataset(new SFTranslateAndScaleData());
	this.addSFDataset(new SFPrimitiveArrayData());
	this.addSFDataset(new SFMeshGeometryData());
	
	this.addSFDataset(new SFBitmapPerlinNoiseData());
	this.addSFDataset(new SFBitmapTextureData());
	this.addSFDataset(new SFFilteredRenderedTextureData());
	this.addSFDataset(new SFReferenceNodeData());
	this.addSFDataset(new SFSplineCurvedTubeFunctionData());
	this.addSFDataset(new SFDrawnRenderedTextureData());
	this.addSFDataset(new SFRendererData());
	this.addSFDataset(new SF2DCameraData());
	this.addSFDataset(new SFBitmapPerlinNoiseData());
	this.addSFDataset(new SFSimpleTexCoordGeometryuvData());
	this.addSFDataset(new SFRectangle2DFunctionData());
	this.addSFDataset(new SFRadialSurfaceFunctionData());
	this.addSFDataset(new SFClonedArrayReference());
	this.addSFDataset(new SFBicurvedLoftedSurfaceData());
	this.addSFDataset(new SFUniformBezier33fData());
	this.addSFDataset(new SFLineData());
	this.addSFDataset(new SFCompositeAnimationData());
	this.addSFDataset(new SFMoveAnimationData());
	this.addSFDataset(new SFPeriodicAnimationData());
	this.addSFDataset(new SFInstancedReferenceNodeData());
	this.addSFDataset(new SFRotateAnimationData());
	
	this.addSFDataset(new SFDerivedTextCoordData());
	this.addSFDataset(new SFCompositeMeshGeometryData());
	this.addSFDataset(new SFSimpleObjPlaneTexCoordGeometryData());
	this.addSFDataset(new SFNormalBasedObjPlaneTexCoordGeometryData());
	this.addSFDataset(new SFParametrizedSurfaceGeometryData());
	this.addSFDataset(new SFFunction2DBitmapData());
	this.addSFDataset(new SFBitmapFunctionPerlinNoiseData());
	this.addSFDataset(new SFBasicBitmapFunctionsData());
	this.addSFDataset(new SFBitmapArrayData());
	this.addSFDataset(new SFFunctionRandomizerData());
	this.addSFDataset(new SFQuadsGridGeometryData());
	this.addSFDataset(new SFRandomizedValueListData2D());
	this.addSFDataset(new SFVoronoiBitmapFunctionData());
	
	this.addSFDataset(new SFPolygonalSplineData());
	this.addSFDataset(new SFTransformedCurveData());
	this.addSFDataset(new SFContinuosBezier3Data());
	this.addSFDataset(new SFGroupMeshGeometryData());
	this.addSFDataset(new SFTensorProductSurfaceData());
	this.addSFDataset(new SFBezier3Data());
	this.addSFDataset(new SFRandomizedValueListData3D());

	this.addSFDataset(new SFParametricValuesList());
	this.addSFDataset(new SFParametricGeometryInstance());
	this.addSFDataset(new SFObjectsArrayData());
	this.addSFDataset(new SFUnit8Transform2DData());
	
	
	//this.addSFDataset(new SFPointsCloudData());
	//this.addSFDataset(new SFTextureRepeaterValueList());
	//this.addSFDataset(new SFTilesSpaceData());
	//this.addSFDataset(new SFTiledTexCoordData());
	
}

inherit(SFViewerDatasetFactory,SFGenericDatasetFactory);

//		addSFDataset(new SFCurvedTubeFunctionData());
//		addSFDataset(new SFStructureArrayData16());
//		addSFDataset(new SFStructureArrayDataUnit8());
//		addSFDataset(new SFQuadsSurfaceGeometryData());
//		addSFDataset(new SFObjectModelData());
//		addSFDataset(new SFObjectsLibrary());
//		addSFDataset(new SFBasisSplineData());
//		addSFDataset(new SFBitmapPerlinNoiseData());
//		addSFDataset(new SFBitmapTextureData());
//		addSFDataset(new SFFilteredRenderedTextureData());
//		addSFDataset(new SFReferenceNodeData());
//		addSFDataset(new SFSplineCurvedTubeFunctionData());
//		addSFDataset(new SFDrawnRenderedTextureData());
//		addSFDataset(new SFRendererData());
//		addSFDataset(new SF2DCameraData());
//		addSFDataset(new SFBitmapPerlinNoiseData());
//		addSFDataset(new SFSimpleTexCoordGeometryuvData());
//		addSFDataset(new SFRectangle2DFunctionData());
//		addSFDataset(new SFRadialSurfaceFunctionData());
//		addSFDataset(new SFClonedArrayReference());
//		addSFDataset(new SFBicurvedLoftedSurfaceData());
//		addSFDataset(new SFUniformBezier33fData());
//		addSFDataset(new SFLineData());
//		addSFDataset(new SFCompositeAnimationData());
//		addSFDataset(new SFMoveAnimationData());
//		addSFDataset(new SFPeriodicAnimationData());
//		addSFDataset(new SFInstancedReferenceNodeData());
//		addSFDataset(new SFRotateAnimationData());
//		addSFDataset(new SFPrimitiveArrayData());
//		addSFDataset(new SFMeshGeometryData());
//		addSFDataset(new SFDerivedTextCoordData());
//		addSFDataset(new SFCompositeMeshGeometryData());
//		addSFDataset(new SFSimpleObjPlaneTexCoordGeometryData());
//		addSFDataset(new SFNormalBasedObjPlaneTexCoordGeometryData());
//		addSFDataset(new SFParametrizedSurfaceGeometryData());
//		addSFDataset(new SFFunction2DBitmapData());
//		addSFDataset(new SFBitmapFunctionPerlinNoiseData());
//		addSFDataset(new SFBasicBitmapFunctionsData());
//		addSFDataset(new SFBitmapArrayData());
//		addSFDataset(new SFVertexListData16());
//		addSFDataset(new SFVertexListDataUnit8());
//		addSFDataset(new SFFunctionRandomizerData());
//		addSFDataset(new SFQuadsGridGeometryData());
//		addSFDataset(new SFRandomizedValueListData2D());
//		addSFDataset(new SFVoronoiBitmapFunctionData());
//		addSFDataset(new SFTextureRepeaterValueList());
//		addSFDataset(new SFTilesSpaceData());
//		addSFDataset(new SFTiledTexCoordData());
//		addSFDataset(new SFPolygonalSplineData());
//		addSFDataset(new SFRigidTransformData());
//		addSFDataset(new SFTranslationData());
//		addSFDataset(new SFTranslateAndScaleData());
//		addSFDataset(new SFNoTransformData());
//		addSFDataset(new SFTranslateAndScaleFixed16Data());
//		addSFDataset(new SFTranslateFixed16Data());
//		addSFDataset(new SFTransformedCurveData());
//		addSFDataset(new SFContinuosBezier3Data());
//		addSFDataset(new SFGroupMeshGeometryData());
//		addSFDataset(new SFTensorProductSurfaceData());
//		addSFDataset(new SFBezier3Data());
//		addSFDataset(new SFProgramAssetData());
//		addSFDataset(new SFRandomizedValueListData3D());
//		addSFDataset(new SFPointsCloudData());