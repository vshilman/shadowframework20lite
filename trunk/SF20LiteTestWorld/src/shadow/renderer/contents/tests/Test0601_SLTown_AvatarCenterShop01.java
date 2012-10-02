package shadow.renderer.contents.tests;



/**
 * 
 * @author Alessandro Martinelli
 */
public class Test0601_SLTown_AvatarCenterShop01 extends SFAbstractTest{

	private static final String FILENAME="test0601";
	
	public static void main(String[] args) {
		execute(new Test0601_SLTown_AvatarCenterShop01());
	}

	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {

		loadLibraryAsDataCenter();
		
		viewNode("SLAvatarCenterShop01");
		//viewNode("Test_Frame");
		//viewNode("ACS01_Walls");
		//viewNode("ACS01RedTextureTile4x05");
		//viewTextureSet("ACLittleTilesRedTexture", 0);
		
	}
	
	@Override
	public void buildTestData() {
		
		String xmlFileName="test0601Input_ClonedReferences";
		//String xmlFileName="test0601Input";
		
		compileAndLoadXmlFile(xmlFileName);
		
		store(library);
	}
}
