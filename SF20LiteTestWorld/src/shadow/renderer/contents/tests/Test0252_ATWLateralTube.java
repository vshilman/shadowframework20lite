package shadow.renderer.contents.tests;

public class Test0252_ATWLateralTube extends SFAbstractTest{

	private static final String FILENAME = "test0252";
	
	private Test0250_0230Commons general=new Test0250_0230Commons(this);
	
	public static void main(String[] args) {
		execute(new Test0252_ATWLateralTube());
	}

	@Override
	public String getFilename() {
		return FILENAME;
	}
	
	@Override
	public void viewTestData() {

		loadLibraryAsDataCenter();
		
		general.loadViewer("PlainLateralTube", "TexturedLateralTube", "BumpMappedLateralTube");				
	}
	
	@Override
	public void buildTestData() {

		String xmlFileName="test0252InputATWLateralTube";

		compileAndLoadXmlFile(xmlFileName);

		general.loadTextures();
		
		store(library);
	}
	
}
