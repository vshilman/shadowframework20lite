package shadow.renderer.contents.tests;


/**
 * 
 * @author Alessandro Martinelli
 */
public class Test0610_RainbowQuarter extends SFAbstractTest{
	

	private static final String FILENAME="test0610";
	
	public static void main(String[] args) {
		execute(new Test0610_RainbowQuarter());
	}

	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {

		loadLibraryAsDataCenter();
		
		viewNode("RedCube");
		//viewNode("Dummy01");
		
	}
	
	@Override
	public void buildTestData() {
		
		String xmlFileName="test0610Input";
		//String xmlFileName="test0601Input";
		
		compileAndLoadXmlFile(xmlFileName);
		
		store(library);
	}
}
