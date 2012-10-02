package shadow.renderer.contents.tests;

import shadow.geometry.curves.data.SFUniformBezier33fData;
import shadow.geometry.functions.data.SFBicurvedLoftedSurfaceData;
import shadow.geometry.geometries.data.SFCompositeMeshGeometryData;
import shadow.geometry.geometries.data.SFQuadsSurfaceGeometryData;
import shadow.geometry.geometries.data.SFTiledTexCoordData;
import shadow.renderer.data.SFObjectModelData;

public class Test0081_TiledTextureFunction extends SFAbstractTest{

	private static final String FILENAME="test0081";

	/**
	 * TODO missing test description
	 */
	public static void main(String[] args) {
		execute(new Test0081_TiledTextureFunction());
	}
	
	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	
	@Override
	public void viewTestData() {
		loadLibraryAsDataCenter();
		
		viewNode("CompleteTiledModel");
	}
	
	@Override
	public void buildTestData() {

		copyAssets("test0080", library);
		
		SFBicurvedLoftedSurfaceData bicurvedLoftSurface = new SFBicurvedLoftedSurfaceData();
		SFUniformBezier33fData spline1Data=new SFUniformBezier33fData();
		spline1Data.addBezier33f(-1,-1,0,1,-1,0);
		SFUniformBezier33fData spline2Data=new SFUniformBezier33fData();
		spline2Data.addBezier33f(-1,+1,0,1,1,0);
		bicurvedLoftSurface.getFirstCurve().setDataset(spline1Data);
		bicurvedLoftSurface.getSecondCurve().setDataset(spline2Data);
		library.put("TilesLoftSurface",bicurvedLoftSurface);
		
		int[] matrix={
			0,1,3,4,	0,0,15,16,
			5,6,8,9,	0,0,20,21,
			10,11,13,14,0,0,0,0,
			15,16,18,19,0,0,0,0,
			20,21,23,24,0,1,2,0,
			0,1,2,0,5,6,8,9,
			5,6,8,9,15,16,18,19,
			15,16,18,19,0,21,23,0
		};
		
		SFTiledTexCoordData tileCoord=new SFTiledTexCoordData();//(matrix,tilesSpace.getTilesSet(),8,8);
		tileCoord.setMatrix(matrix);
		tileCoord.setDimension(8, 8);
		tileCoord.setTilesSpace("PebblesTilesSpace");
		tileCoord.setPrimitive("Triangle2Tx0");
		library.put("TiledTexCoord",tileCoord);
		
		SFQuadsSurfaceGeometryData geometry=new SFQuadsSurfaceGeometryData();
		geometry.setup("TilesLoftSurface", 9, 9, "Triangle2Frame");
		library.put("TiledGeometry",geometry);
		
		SFCompositeMeshGeometryData compositeMeshGeometry=new SFCompositeMeshGeometryData();
		compositeMeshGeometry.setPrimitive("Triangle2BumpMap");
		compositeMeshGeometry.addGeometry("TiledGeometry");
		compositeMeshGeometry.addGeometry("TiledTexCoord");
		library.put("CompleteTiledGeometry",compositeMeshGeometry);

		SFObjectModelData objectModel=new SFObjectModelData();
		objectModel.setGeometry("CompleteTiledGeometry");
		objectModel.getMaterialComponent().addTexture(1,"PebblesTiledTexture");
		objectModel.getMaterialComponent().addTexture(0,"PebblesTiledTexture");
		objectModel.getMaterialComponent().setProgram("ImprovedBumpMappedMat");
		objectModel.getTransformComponent().setProgram("BasicBumpMapTransform");
		library.put("CompleteTiledModel",objectModel);
		
		
		store(library);
	}
	
	
}
