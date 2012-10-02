package shadow.renderer.contents.tests;


/**
 * @author Alessandro Martinelli
 */
public class Test0500_TestMaterials extends SFAbstractTest{

	private static final String FILENAME="test0500";
	
	/***/
	public static void main(String[] args) {
		execute(new Test0500_TestMaterials());
	}

	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {

		loadLibraryAsDataCenter();
		
		viewNode("PerlinBlueAndYellowRectangle");
	}
	
	@Override
	public void buildTestData() {
		
		String xmlFileName="test0500Input";
		
		compileAndLoadXmlFile(xmlFileName);
		
		store(library);
	}
	
}
