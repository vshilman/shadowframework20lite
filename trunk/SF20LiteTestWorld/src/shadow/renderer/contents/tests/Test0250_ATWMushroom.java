package shadow.renderer.contents.tests;

public class Test0250_ATWMushroom extends SFAbstractTest{

	private static final String FILENAME = "test0250";
	
	private Test0250_0230Commons general=new Test0250_0230Commons(this);
	
	public static void main(String[] args) {
		execute(new Test0250_ATWMushroom());
	}

	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {

		loadLibraryAsDataCenter();
		
		general.loadViewer("PlainMushroom", "TexturedMushroom", "BumpMappedMushroom");				
	}
	
	@Override
	public void buildTestData() {

		String xmlFileName="test0250InputATWMushroom";

		compileAndLoadXmlFile(xmlFileName);

		general.loadTextures();
		
		store(library);
	}
	
}
