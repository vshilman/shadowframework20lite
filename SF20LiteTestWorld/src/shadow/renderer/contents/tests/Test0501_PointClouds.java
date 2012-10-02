package shadow.renderer.contents.tests;


/**
 * @author Alessandro Martinelli
 */
public class Test0501_PointClouds extends SFAbstractTest{

	private static final String FILENAME="test0501";
	
	/***/
	public static void main(String[] args) {
		execute(new Test0501_PointClouds());
	}

	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {

		loadLibraryAsDataCenter();
		
		viewNode("PointsSpriteReference");
	}
	
	@Override
	public void buildTestData() {
		
		String xmlFileName="test0501Input";
		
		compileAndLoadXmlFile(xmlFileName);
		
		store(library);
	}
	
}
