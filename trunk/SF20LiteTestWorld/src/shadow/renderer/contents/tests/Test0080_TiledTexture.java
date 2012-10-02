package shadow.renderer.contents.tests;

import shadow.image.SFImageFormat;
import shadow.image.SFPipelineTexture.Filter;
import shadow.image.SFPipelineTexture.WrapMode;
import shadow.image.data.SFDrawnRenderedTextureData;
import shadow.renderer.data.SF2DCameraData;
import shadow.renderer.data.SFRendererData;

public class Test0080_TiledTexture extends SFAbstractTest{

	private static final String FILENAME = "test0080";
	
	public static void main(String[] args) {
		execute(new Test0080_TiledTexture());
	}

	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {
		loadLibraryAsDataCenter();
		
		viewTextureSet("PebblesTiledTexture", 0);
	}

	@Override
	public void buildTestData() {

		copyAssets("test0079", library);
				
		SFDrawnRenderedTextureData drawnTexture=new SFDrawnRenderedTextureData();
		drawnTexture.setNode("PebblesTilesSpace");
		drawnTexture.setRenderer(new SFRendererData("BumpMaps",new SF2DCameraData(0.5f, 0.5f)));
		drawnTexture.addOutputTexture(1024, 1024, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
		drawnTexture.addOutputTexture(1024, 1024, SFImageFormat.RGB8, Filter.LINEAR, WrapMode.REPEAT, WrapMode.REPEAT);
		drawnTexture.useDefaultDepth(true);
		
		library.put("PebblesTiledTexture",drawnTexture);
		store(library);
	}
}
