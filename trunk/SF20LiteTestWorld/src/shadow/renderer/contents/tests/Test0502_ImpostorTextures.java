package shadow.renderer.contents.tests;


/**
 * @author Alessandro Martinelli
 */
public class Test0502_ImpostorTextures extends SFAbstractTest{

	private static final String FILENAME="test0502";
	
	/***/
	public static void main(String[] args) {
		execute(new Test0502_ImpostorTextures());
	}

	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {

		loadLibraryAsDataCenter();
		
		//viewNode("RedMushroom");
		viewTextureSet("MushroomTextures", 0);
		//viewNode("PointSpritesReference");	
	}
	
	@Override
	public void buildTestData() {
		
		String xmlFileName="test0502Input";
		
		compileAndLoadXmlFile(xmlFileName);
		
		store(library);
	}
	
}
