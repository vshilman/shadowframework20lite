package shadow.renderer.contents.tests;

import shadow.image.SFRenderedTexturesSet;
import shadow.image.SFTexture;
import shadow.renderer.contents.tests.common.CommonTextures;
import shadow.renderer.viewer.SFTextureViewer;

/**
 * 
 * @author Alessandro Martinelli
 */
public class Test0405_VoronoiTextures extends SFAbstractTest{

	private static final String FILENAME="test0405";
	
	/***/
	public static void main(String[] args) {
		execute(new Test0405_VoronoiTextures());
	}
	

	@Override
	public String getFilename() {
		return FILENAME;
	}
	

	@Override
	public void viewTestData() {

		loadLibraryAsDataCenter();
		
		String names[]=getAllNamesInLibraryOfOneType("SFBitmapTextureData");
		
		SFRenderedTexturesSet[] sets=new SFRenderedTexturesSet[names.length];
		for (int i = 0; i < sets.length; i++) {
			sets[i]=getAlreadyAvailableDatasetResource(names[i]);
		}
		
		SFTexture texture=new SFTexture(sets[0], 0);
		SFTextureViewer.generateFrame(texture,CommonTextures.generateTextureSelectionController(texture, names, sets));
		
	}
	
	@Override
	public void buildTestData() {
		
		String xmlFileName="Test0405VoronoiTextures";

		compileAndLoadXmlFile(xmlFileName);
		
		store(library);
	}
	
}
